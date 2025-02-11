package com.telus.csm.ewlnsc.delegate;

import static com.telus.csm.esdg.database.EsdgDatabaseAdapter_1.INTERACTION_ID_PREFIX;
import static com.telus.csm.esdg.database.EsdgDatabaseAdapter_1.SALES_ITEM_ID_PREFIX;
import static com.telus.csm.esdg.database.EsdgDatabaseAdapter_1.removeInteractionPrefix;
import static com.telus.csm.ewlnsc.delegate.ShoppingCartEsdgTransformer.transformToCartItem;
import static com.telus.csm.ewlnsc.delegate.ShoppingCartEsdgTransformer.transformToInteractionDO;
import static com.telus.csm.ewlnsc.delegate.ShoppingCartEsdgTransformer.transformToOrderDO;
import static com.telus.csm.ewlnsc.delegate.ShoppingCartEsdgTransformer.transformToShoppingCart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.tangosol.net.NamedCache;
import com.tangosol.util.Filter;
import com.tangosol.util.ValueExtractor;
import com.tangosol.util.extractor.ReflectionExtractor;
import com.tangosol.util.filter.EqualsFilter;
import com.telus.csm.esdg.database.EsdgDatabaseAdapter_1;
import com.telus.csm.esdg.domain.EsdgInteractionDO_1;
import com.telus.csm.esdg.domain.EsdgOrderDO_1;
import com.telus.csm.esdg.domain.EsdgSalesItemDO;
import com.telus.csm.ess.rest.domain.CreateShoppingCart;
import com.telus.csm.ess.rest.domain.OperationHeader;
import com.telus.csm.ess.rest.domain.ShoppingCart;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.EssAppException;
import com.telus.csm.ewlnsc.domain.SalesItemPerkVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.transformer.ShoppingCartTransformer;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsc.util.ShoppingCartStatus;
import com.telus.csm.ewlnsc.util.cacheadapter.CacheAdapterFactory;
import com.telus.csm.ewlnsc.util.cacheadapter.ESSCacheAdapter;

/**
 * This class is responsible for saving / retrieving ShoppingCart (and its CartItem ) to/from database via coherence 
 */
public class ShoppingCartDelegate {

	public static final String CACHE_NAME_SALES_INTERACTION = "ESDG_SalesInteraction_Data";
	public static final String CACHE_NAME_SALES_CONTEXT_ORDER = "ESDG_SalesContext_Order";
	public static final String CACHE_NAME_SHOPPING_CART_ORDERID = "ESDG_ShoppingCart_OrderId";

	private static final Logger LOGGER = Logger.getLogger(ShoppingCartDelegate.class);
	
	private static final boolean DO_JSON_CONVERSION = true;
	private static final boolean NO_JSON_CONVERSION = false;
	private static final boolean INCLUDE_ABANDON_STATUS = true;
	private static final boolean NO_ABANDON_STATUS = false;
	public static final String SalesItemCacheName = "ESDG_SalesItem_Data";
	
	private static ShoppingCartDelegate singleton = null;
	
	private EsdgInteractionRepository interactionRepository;
	private EsdgOrderRepository orderRepository;
	private EsdgDatabaseAdapter_1 esdgDatabaseAdapter;

	//this cache store the shoppingCartId to salesId relationship
	private ESSCacheAdapter shoppingCartOrderIdCache;
	private ESSCacheAdapter salesItemCacheAdapter;
	
	public final static String ITEM_TYPE_PERK = "PERK";

	private ShoppingCartDelegate() {
		interactionRepository = new EsdgInteractionRepository();
		orderRepository = new EsdgOrderRepository();
		esdgDatabaseAdapter =  new EsdgDatabaseAdapter_1();
		shoppingCartOrderIdCache = (ESSCacheAdapter) CacheAdapterFactory.getCacheAdapter( CACHE_NAME_SHOPPING_CART_ORDERID );
		salesItemCacheAdapter = new ESSCacheAdapter(SalesItemCacheName);
		salesItemCacheAdapter.setCacheName(SalesItemCacheName);
		salesItemCacheAdapter.init();
	}
	
	public static synchronized ShoppingCartDelegate getInstance() {

		if (singleton == null) {
			singleton = new ShoppingCartDelegate();
		}
		return singleton;
	}

	
	//====================== ShoppingCart related interfaces ========================
	
	private void validateShoppingCartIds( ShoppingCartVO shoppingCartVO , boolean isNew) {
		
		if ( shoppingCartVO.getCartItemList()!=null ) {
			
			StringBuilder overall = new StringBuilder();

			List<String> relationIds = new ArrayList<String>();
			for( CartItemVO cartItem : shoppingCartVO.getCartItemList() ) {
			
				StringBuilder sb = new StringBuilder();
				
				boolean hasError = false;

				//relationId must not be empty
				if ( StringUtils.isBlank( cartItem.getCartItemRelationId() ) ) {
					hasError = true;
					sb.append("missing carItemRelationId, ");
				} 
				else {
					//relationId must not be duplicate
					if ( relationIds.contains( cartItem.getCartItemRelationId() ) ) {
						hasError = true;
						sb.append("duplicate carItemRelationId, ");
					}
				}
				
				if (isNew ) {
					//for new shoppingCart, cartItemId must be blank, as we will assign Id
					if ( StringUtils.isNotBlank( cartItem.getCartItemId() ) ) {
						hasError = true;
						sb.append("carItemId must be empty,");
					}
				}
				
				if (hasError) {
					sb.append( cartItem.toIdentityString() ).append(";");
					overall.append( sb.toString() );
				}
			}
			
			if (overall.length()>0) {
				throw new EssAppException( "SHOPPING_CART_IDS_VALIDATION_ERROR", overall.toString() );
			}
		}
	}
	

	/**
	 * Initial saving of ShoppingCart
	 * 
	 * @param shoppingCartVO
	 * @return
	 */
	public String saveNewShoppingCart(ShoppingCartVO shoppingCartVO) {
		
		long startTime = System.currentTimeMillis();
		
		try {
		
			validateShoppingCartIds( shoppingCartVO, true );
			
			EsdgInteractionDO_1 esdgInteraction = createNewInteraction( shoppingCartVO );
				
			String shoppingCartId = makeupShoppingCartId( esdgInteraction.getSalesInteractionId() );
			shoppingCartVO.setShoppingCartId(shoppingCartId);
			
			LOGGER.debug( "new shoppingCart is created: interactionId="+esdgInteraction.getSalesInteractionId() + ", shoppingCartId="+ shoppingCartId );
			
			//backup cartItemList
			List<CartItemVO> cartItemListBackup = null;
			if (  CollectionUtils.isNotEmpty( shoppingCartVO.getCartItemList()) ) {
				cartItemListBackup = new ArrayList<CartItemVO> ( shoppingCartVO.getCartItemList() );
			}
			
			//extract order type cartItem
			List<CartItemVO> salesOrders = splitOrderTypeCartItemsFromShoppingCart(shoppingCartVO);
			assignIdToOrderTypeCartItem( shoppingCartVO, salesOrders );
			
			//extract non order type cartItem
			assignIdToNonOrderTypeCartItems( shoppingCartVO );
			
	
			//save shoppingCart to cache
			esdgInteraction.setJsonSalesTXNData( JsonUtil.getJsonFromObject(shoppingCartVO) );
			interactionRepository.put( esdgInteraction );
			
			//save salesOrder cartItems to cache
			saveSalesOrderCartItems( esdgInteraction, salesOrders );
			
			//restore cartItemList
			if ( cartItemListBackup!=null) {
				shoppingCartVO.setCartItemList( cartItemListBackup );
			}
			
			return shoppingCartVO.getShoppingCartId();
			
		} finally {
			
			LOGGER.debug( "saveNewShoppingCart timeElapsed(ms):" + (System.currentTimeMillis()- startTime )  );
		}
	}
	
	/**
	 * Update ShoppingCart
	 * 
	 * @param shoppingCartVO
	 * @return
	 */
	public boolean updateShoppingCart(ShoppingCartVO shoppingCartVO) {
		
		long startTime = System.currentTimeMillis();
		
		try {

			boolean result = true;
			
			validateShoppingCartIds( shoppingCartVO, false );
			
			String shoppingCartId = shoppingCartVO.getShoppingCartId();
			
			EsdgInteractionDO_1 esdgInteraction = interactionRepository.get(shoppingCartId);
			
			if ( esdgInteraction==null ) {
				
				throw new EssAppException( "TODO", "Unable to find shoppingCart by Id:" + shoppingCartId );
			
			} else {
			
				//backup cartItemList
				List<CartItemVO> cartItemListBackup = null;
				if (  CollectionUtils.isNotEmpty( shoppingCartVO.getCartItemList()) ) {
					cartItemListBackup = new ArrayList<CartItemVO> ( shoppingCartVO.getCartItemList() );
				}
				
				//get consolidated salesOrder cartItems for shoppingCart: from shopping cart, and from cache, combined
				List<CartItemVO> salesOrders = getConsolidatedSalesOrders(shoppingCartVO);
				assignIdToOrderTypeCartItem( shoppingCartVO, salesOrders );
				
				assignIdToNonOrderTypeCartItems( shoppingCartVO );
	
				//prepare the cache object
				esdgInteraction = transformToInteractionDO( shoppingCartVO, DO_JSON_CONVERSION );
				esdgInteraction.setWriteToDatabase( true );
				esdgInteraction.setDataGenerationTimeInMills( System.currentTimeMillis() );  // a new order version to be created based on this timestamp
		
				//save shoppingCart to cache
				interactionRepository.put( esdgInteraction );
		
				//save salesOrder cartItem to cache
				if ( salesOrders.isEmpty()==false ) {
					saveSalesOrderCartItems( esdgInteraction, salesOrders );
				}
				
				//restore cartItemList
				if ( cartItemListBackup!=null) {
					shoppingCartVO.setCartItemList( cartItemListBackup );
				}
			}
			
			return result;
			
		} finally {
			
			LOGGER.debug( "updateShoppingCart timeElapsed(ms):" + (System.currentTimeMillis()- startTime )  );
		}

	}

	/**
	 * Retrieve ShoppingCart
	 * 
	 * @param shoppingCartId
	 * @return
	 */
	public ShoppingCartVO getShoppingCart(String shoppingCartId) {
		
		long startTime = System.currentTimeMillis();
		
		try {
			LOGGER.info( "Retrieving by cacheKey=" + shoppingCartId);
	
			EsdgInteractionDO_1 esdgInteraction = interactionRepository.get( shoppingCartId );
			if(esdgInteraction!=null){
				
				if ( esdgInteraction.getJsonSalesTXNData()!=null ) {
	
					ShoppingCartVO shoppingCartVO = transformToShoppingCart( esdgInteraction );
					
					List<CartItemVO> cartItemOrders = getSalesOrderCartItemsByCartId( shoppingCartId, false );
					if ( cartItemOrders.isEmpty()==false  ) {
						if (shoppingCartVO.getCartItemList()==null) {
							shoppingCartVO.setCartItemList ( new ArrayList<CartItemVO>() );
						}
						shoppingCartVO.getCartItemList().addAll( cartItemOrders );
					}
					
					final ShoppingCart shoppingCartRest = ShoppingCartTransformer.transformShoppingCartToRest(shoppingCartVO);
					final CreateShoppingCart csh = new CreateShoppingCart();
					csh.setShoppingCart(shoppingCartRest);
					if (shoppingCartVO.getOperationHeader() != null) {
						csh.setOperationHeader(new OperationHeader());
						csh.getOperationHeader().setSalesTransactionId(shoppingCartVO.getOperationHeader().getSalesTransactionId());
					}
					LOGGER.info("shopping cart content is: " + JsonUtil.getJsonFromObjectNonNUll(csh));
					
					return shoppingCartVO;
					
				} else {
					
					LOGGER.info("Found salesInteraction, but no txnData." );
				}
			} else {
				
				LOGGER.warn("Did not find a shopping cart with cacheKey=" + shoppingCartId);
			}
			return null;

		} finally {
			
			LOGGER.debug( "getShoppingCart timeElapsed(ms):" + (System.currentTimeMillis()- startTime )  );
		}
	}

	//====================== CartItem SalesOrder related interface ========================
	/**
	 * Retrieve SalesOrder type of cartItem for given cartItemId
	 * 
	 * @param cartItemId
	 * @return
	 */
	public CartItemVO getSalesOrderCartItem( String cartItemId ) {
		
		EsdgOrderDO_1 salesOrderDO = orderRepository.get(cartItemId);
		if(salesOrderDO!=null){
			
			CartItemVO cartItemVO = transformToCartItem( salesOrderDO );
			
			return cartItemVO;
			
		} else {
			LOGGER.info( "Did not find a shopping cart with cacheKey=" + cartItemId );
		}
		return null;
	}
	
	/**
	 * Retrieve SalesOrder type of cartItem for given shoppingCartId
	 * 
	 * @param shoppingCartId
	 * @return
	 */
	public List<CartItemVO> getSalesOrderCartItemsByCartId( String shoppingCartId ) {
		
		return getSalesOrderCartItemsByCartId( shoppingCartId, NO_ABANDON_STATUS );
	}
	
	/**
	 * Create 
	 * @param shoppingCartId
	 * @param cartItemVO
	 * @return
	 */
	public String saveNewSalesOrderCartItem( String shoppingCartId, CartItemVO cartItemVO ) {
		
		String cartItemId = null;
		if ( cartItemVO.isSalesOrderItem() ) {
			
			EsdgInteractionDO_1 esdgInteraction = interactionRepository.get( shoppingCartId );
			if ( esdgInteraction!=null) {
				
				String saleOrderId = generateOrderId( esdgInteraction.getSalesInteractionId(), null, cartItemVO );
				if ( saleOrderId !=null) {
					cartItemVO.setCartItemId( makeupCartItemId(saleOrderId) );
				}
	
				LOGGER.info("saving orderCartItem shoppingCartId=" + esdgInteraction.getSalesInteractionId() 
				+ ", cartItemId=" + cartItemVO.getCartItemId() + ", relationId=" + cartItemVO.getCartItemRelationId() );
				
				orderRepository.put( transformToOrderDO( esdgInteraction, cartItemVO ) );
				
			} else {
				
				LOGGER.error("unable to find shoppingCart, id=" + shoppingCartId);
			}
		} else {
			
			LOGGER.error("cartItem is SalesOrder type, shoppingCartId=" + shoppingCartId + ", cartItemRelationId=" +  cartItemVO.getCartItemRelationId() );
			
		}
		return cartItemId;
	}
	
	/**
	 * Update salesOrder cartItem 
	 * @param shoppingCartId
	 * @param cartItemVO
	 * @return
	 */
	public boolean updateSalesOrderCartItem( String shoppingCartId, CartItemVO cartItemVO ) {
		
		boolean result=false;

		if ( cartItemVO.isSalesOrderItem() ) {
			
			EsdgInteractionDO_1 esdgInteraction = interactionRepository.get( shoppingCartId );
			if ( esdgInteraction!=null) { //safeguard
	
				if ( StringUtils.isBlank( cartItemVO.getCartItemId()) ) {
					String saleOrderId = generateOrderId( esdgInteraction.getSalesInteractionId(), null, cartItemVO );
					if ( saleOrderId !=null) {
						cartItemVO.setCartItemId( makeupCartItemId(saleOrderId) );
					}
				}
				
				LOGGER.info("saving orderCartItem shoppingCartId=" + esdgInteraction.getSalesInteractionId() 
				+ ", cartItemId=" + cartItemVO.getCartItemId() + ", relationId=" + cartItemVO.getCartItemRelationId() );
				
				orderRepository.put( transformToOrderDO( esdgInteraction, cartItemVO ) );
				
				result = true;
				
			} else {
				LOGGER.error("unable to find shoppingCart, id=" + shoppingCartId);
			}
		}
		else {
			LOGGER.error("cartItem is SalesOrder type, shoppingCartId=" + shoppingCartId + ", cartItemRelationId=" +  cartItemVO.getCartItemRelationId() );
		}

		return result;
	}

	//====================== utility methods ========================
	/**
	 * find SalesOrder type cart items from shoppingCart, and split them from shoppingCart
	 *  
	 * @param shoppingCartVO
	 * @return list of CartItemVO that is SalesOrder type 
	 */
	public static List<CartItemVO> splitOrderTypeCartItemsFromShoppingCart( ShoppingCartVO shoppingCartVO ) {
		
		List<CartItemVO> result = extractSalesOrders( shoppingCartVO );
		
		if ( result.isEmpty()==false ) {
			shoppingCartVO.getCartItemList().removeAll( result );
		}
		
		return result;
	}
	
	/**
	 * find SalesOrder type cart items from shoppingCart
	 * 
	 * @param shoppingCartVO
	 * @return list of CartItemVO that is SalesOrder type 
	 */
	public static List<CartItemVO> extractSalesOrders( ShoppingCartVO shoppingCartVO ) {
		
		List<CartItemVO> result = new ArrayList<CartItemVO>();
		
		if( CollectionUtils.isNotEmpty( shoppingCartVO.getCartItemList() ) ) {
			for( CartItemVO cartItem: shoppingCartVO.getCartItemList() ) {
				if ( cartItem.isSalesOrderItem() ) {
					if (StringUtils.isBlank( cartItem.getStatus() ) ) {
						//set default Order status
						cartItem.setStatus(ShoppingCartStatus.CartItemStatus.PREPARED.getCode());
					}
					result.add( cartItem );
				}
			}
		} 
		
		return result;
	}
	
	/**
	 * find non salesOrder type cart items from shoppingCart and assign id to them:
	 * 
	 * cartItem could be GWP, discconnectOrder, perk
	 * 
	 * @param shoppingCartVO
	 * @return list of CartItemVO that is SalesOrder type 
	 */
	private void assignIdToNonOrderTypeCartItems( ShoppingCartVO shoppingCartVO ) {
		
		if( CollectionUtils.isNotEmpty( shoppingCartVO.getCartItemList() ) ) {
			
			for( CartItemVO cartItem: shoppingCartVO.getCartItemList() ) {
				
				if ( cartItem.isSalesOrderItem()==false ) {
					
					//only assign id to cartItem that does not has cartItemId
					if ( StringUtils.isBlank( cartItem.getCartItemId() ) ) {
						
						String salesItemId = generateCartItemId( cartItem.getCartItemRelationId() );
						
						if ( salesItemId !=null) {
							
							LOGGER.debug("new cartItem id is generated for " + cartItem.toIdentityString() + ": " + salesItemId );
							
							cartItem.setCartItemId( makeupCartItemId(salesItemId) );
						}
					}
				}
			}
		} 
	}
	
	//====================== supporting methods ========================
	/**
	 * <li> 1 Search salesId for given shoppingCartId from database, 
	 * <li> 2 Then retrieve those salesOrder via cache
	 * <li> 3 convert back to CartItemVO
	 * 
	 * @param shoppingCartId
	 * @param includeAbandon
	 * @return
	 */
	private List<CartItemVO> getSalesOrderCartItemsByCartId( String shoppingCartId, boolean includeAbandon ) {
		
		List<CartItemVO> result = null;
		
		List<List<String>> salesOrderIds = searchOrderIdsByCartId( shoppingCartId ) ;
		
		//first get all non-abandon order
		List<EsdgOrderDO_1> esdgOrders = orderRepository.getAll( salesOrderIds.get(0) );

		if (includeAbandon && salesOrderIds.get(1).isEmpty()==false ) {
			esdgOrders.addAll( orderRepository.getAll( salesOrderIds.get(1) ) );
		}
		
		//now convert EsdgOrderDO_1 to CartItemVO
		if ( CollectionUtils.isNotEmpty( esdgOrders ) ) {
			
			result = new ArrayList<CartItemVO> ( esdgOrders.size() ) ;
			
			for( EsdgOrderDO_1 order : esdgOrders ) {
				CartItemVO cartItemVO = transformToCartItem( order );
				result.add( cartItemVO );
			}
			
		} else {
			result = new ArrayList<CartItemVO>(0);
		}

		return result;
	}

	private void assignIdToOrderTypeCartItem(ShoppingCartVO shoppingCartVO, List<CartItemVO> cartOrders) {
		
		if ( cartOrders.isEmpty()==false ) {
			for ( CartItemVO cartItem : cartOrders) {
				if ( StringUtils.isBlank( cartItem.getCartItemId()) ) {
					String saleOrderId = generateOrderId( shoppingCartVO, cartItem );
					if ( saleOrderId !=null) {
						cartItem.setCartItemId( saleOrderId );
					}
				}
			}
		}
	}
	

	private boolean saveSalesOrderCartItems( EsdgInteractionDO_1 esdgInteraction, List<CartItemVO> cartOrders ) {
		
		boolean result=true;
		ArrayList<String[]> salesIdList = new ArrayList<String[]>();
		
		if (cartOrders.isEmpty()==false ) {
			
			HashMap<String, Object> orderMap = new HashMap<String, Object>();
			for ( CartItemVO cartItem : cartOrders) {

				//this info goes to SalesOrder cache
				orderMap.put(cartItem.getCartItemId(), transformToOrderDO( esdgInteraction, cartItem ) );
				
				//this info goes shoppingCart cache
				salesIdList.add( new String[] { cartItem.getCartItemId(), cartItem.getStatus() } ) ;

				LOGGER.info("saving "  + cartItem.toIdentityString() );
			}
			
			orderRepository.putAll( orderMap  );
		}

		//save to shoppingCart cache  
		shoppingCartOrderIdCache.put( removeInteractionPrefix( esdgInteraction.getSalesInteractionId() ) , salesIdList);
		return result;
	}
	
	private String makeupShoppingCartId(String salesInteractionId) { 
		
		return INTERACTION_ID_PREFIX + salesInteractionId;
	}
	
	private String makeupCartItemId(String salesItemId ) { 
		return SALES_ITEM_ID_PREFIX + salesItemId;
	}

	
	
	/**
	 * Search salesId for given shoppingCartId from database
	 * 
	 * @param shoppingCartId
	 * @param includeAbandon
	 * @return two salesId Lists: 
	 * 	<li>first one is non-abandon order salesId list
	 * 	<li>second one is abandon order salesId list 
	 */
	@SuppressWarnings("unchecked")
	private List<List<String>> searchOrderIdsByCartId( String shoppingCartId ) {
		
		List<String[]> orderInfoList = null;
		
		StringBuilder sb = new StringBuilder("search for shoppingCart salesId for ").append( shoppingCartId );

		String interactionId = removeInteractionPrefix(shoppingCartId);
		
		sb.append( ", internactionId=").append( interactionId );
		
		if ( shoppingCartOrderIdCache.isInCache( interactionId ) ) {
			
			sb.append( " in cache: ").append( shoppingCartOrderIdCache.getCacheName() );
			
			orderInfoList = (List<String[]> ) shoppingCartOrderIdCache.get( interactionId );
			
		} else {

			sb.append( "in database" );
		
			orderInfoList = esdgDatabaseAdapter.searchSalesIdByInteractionId( interactionId );
		}
			
		List<String> orderIds = new ArrayList<String>();
		List<String> abandonOrderIds = new ArrayList<String>();
		
		if (orderInfoList.size()>0 ) {
			
			sb.append(";  found: ");
			
			for( String[] orderInfo: orderInfoList ) {
				
				String salesId = orderInfo[0];

				if ( isAbandonedOrder( orderInfo[1]) ) {
					abandonOrderIds.add(salesId);
				} else {
					orderIds.add(salesId);
				}
				
				sb.append( salesId ).append(":").append( orderInfo[1] ).append(", ");
			}
			
			sb.append ("; abandon " ).append(  abandonOrderIds );
			LOGGER.info( sb.toString() );
		}
		
		return Arrays.asList ( orderIds, abandonOrderIds )  ;
	}
	
	/**
	 * This method only creates salesInteraction record in DB, it DOES not save entire shoppingCard object
	 * 
	 * @param shoppingCartVO
	 * @return EsdgInteractionDO_1, which contain newly generated shoppingCartId (salesInteractionId );
	 */
	private EsdgInteractionDO_1 createNewInteraction( ShoppingCartVO shoppingCartVO ) {
		
		EsdgInteractionDO_1 esdgInteraction = transformToInteractionDO( shoppingCartVO, NO_JSON_CONVERSION );
		
		esdgDatabaseAdapter.saveSalesInteraction( esdgInteraction );
			
		return esdgInteraction;
		
	}
	
	/**
	 * Generates SalesId for given cartItem and its parent - shoppingCart 
	 * 
	 * @param shoppingCartId
	 * @param profile
	 * @param cartItemRelationId
	 * @return
	 */
	private String generateOrderId( ShoppingCartVO shoppingCart, CartItemVO cartItem  ) {
		
		String salesInteractionId =removeInteractionPrefix ( shoppingCart.getShoppingCartId() ) ;
		
		String salesTransactionId = null;
		if ( shoppingCart.getOperationHeader()!=null) {
			salesTransactionId = shoppingCart.getOperationHeader().getSalesTransactionId();
		} 
		
		return generateOrderId( salesInteractionId, salesTransactionId, cartItem );
	}
	
	/**
	 * Generates SalesId for given cartItem and salesInteractionId 
	 * 
	 * @param salesInteractionId
	 * @param salesTransactionId
	 * @param cartItem
	 * @return
	 */
	private String generateOrderId( String salesInteractionId, String salesTransactionId, CartItemVO cartItem  ) {
		
		
		String salesContextExternalRefId = cartItem.getCartItemRelationId();
		
		EsdgOrderDO_1 orderDO = esdgDatabaseAdapter.insertSalesContext(
				salesInteractionId,	 
				null, 	//salesInteractionExternalRefId - field for creating new salesInteraction, no use here 
				salesContextExternalRefId, // field for new SalesContext
				null, 	//channelOrgTypeCd - field for creating new salesInteraction, no use here
				null, 	//channelOrgId - field for creating new salesInteraction, no use here
				null, 	//outletId - field for creating new salesInteraction, no use here
				null, 	//salesPersonId - field for creating new salesInteraction, no use here
				null, 	//salesPersonTypeCd - field for creating new salesInteraction, no use here
				
				salesTransactionId, //field is only used for logging purpose, does not go to database
				
				null, 	// - billingAccountNum - field for creating new salesInteraction, no use here
				null, 	// - contactPhoneNum - field for creating new salesInteraction, no use here				
				"UnknownTN", //subscriberPhoneNum
				"TELUS"	//brandCd - Wireline has only one brand
				);			

		String newCartItemId = null;

		//add context to cache
		if( orderDO != null ) {
			newCartItemId = orderDO.getSalesContextId();
			orderDO.setDataGenerationTimeInMills(0); // dummy order
			orderDO.setWriteToDatabase(false);  // do not write back to DB
			
			orderRepository.put( orderDO );
		}
		
		return newCartItemId;
	}
	
	/**
	 * Generates SalesId for given cartItem and salesInteractionId 
	 * 
	 * @param cartRelationId
	 * @return
	 */
	private String generateCartItemId( String cartRelationId ) {
		
		String newCartItemId = esdgDatabaseAdapter.getNewSalesItemId( cartRelationId );

		return newCartItemId;
	}
	
	
	/**
	 * This method consolidate orderType cartItem, from in-memory shoppingCart and from database (via cache )  
	 *  
	 * @param shoppingCartVO
	 * @return
	 */
	private List<CartItemVO> getConsolidatedSalesOrders( ShoppingCartVO shoppingCartVO ) {

		List<CartItemVO> cartSalesOrders = splitOrderTypeCartItemsFromShoppingCart( shoppingCartVO );
		
		//get all salesOrder from DB , including abandon status
		List<CartItemVO> savedSalesOrders = getSalesOrderCartItemsByCartId( shoppingCartVO.getShoppingCartId(), INCLUDE_ABANDON_STATUS );
		
		//for each cartItem in shoppingCart, compare with saved ones,  
		for ( CartItemVO cartItem: cartSalesOrders ) {
			
			Iterator<CartItemVO> savedOrderItemIterator = savedSalesOrders.iterator();
			
			//searching saved orderItem that matches shoppingCart orderItem's cartItemRelationId 
			while( savedOrderItemIterator.hasNext() ) {
				
				CartItemVO savedItem = savedOrderItemIterator.next();
				
				if (cartItem.getCartItemRelationId().equals(savedItem.getCartItemRelationId() ) ) {
					
					//the savedItem matches cartItem according to cartItemRelationId, so reuse the cartItemId
					cartItem.setCartItemId( savedItem.getCartItemId() );
					
					//populate status from saved one if the new one does not have status
					if (StringUtils.isBlank(cartItem.getStatus() ) ) {
						cartItem.setStatus(savedItem.getStatus() );
					}
					
					LOGGER.debug( "cartItem relationId=" + cartItem.getCartItemRelationId()
						+ " found matching savedSalesOrder salesId=" + savedItem.getCartItemId() + ", status=" + savedItem.getStatus() );

					//remove it from savedOrderItems, so that round comparison won't waste time on this item
					savedOrderItemIterator.remove();
					
					//stop searching
					break;
				}
			}
		}
		
		//the remaining element in this list are not in the latest shopping cart 
		if ( savedSalesOrders.isEmpty()==false ) {
			
			Iterator<CartItemVO> orphanOrderIterator = savedSalesOrders.iterator();
			while( orphanOrderIterator.hasNext() ) {
				
				CartItemVO orphanOrder = orphanOrderIterator.next();
				
				if ( isAbandonedOrder( orphanOrder.getStatus() ) ) {
					//its status is already abandon, no need to set and save it again
					orphanOrderIterator.remove();
				} else {
					LOGGER.debug( orphanOrder.toIdentityString() + " is not in shoppingCart, mark it as abandon, current status:" + orphanOrder.getStatus() );

					//mark this orphanOrder as abandon, and leave it in the list
					orphanOrder.setStatus( ShoppingCartStatus.CartItemStatus.ABANDON.getCode() );
				}
			}
			//add non abandon orphan orders, so that they can be saved with new status
			cartSalesOrders.addAll( savedSalesOrders );
		}
		
		return cartSalesOrders;
	}

	protected boolean isAbandonedOrder( String status ) {
		return ShoppingCartStatus.CartItemStatus.ABANDON.getCode().equals( status );
	}


	/**
	 * Repository class that is responsible for persist SalesInteraction
	 */
	static class EsdgInteractionRepository {
		
		private static final Logger LOGGER = Logger.getLogger(EsdgInteractionRepository.class);
		
		private ESSCacheAdapter cacheAdapter;

		private String stripKey( String interactionId ) {
			return removeInteractionPrefix(interactionId);
		}
		
		EsdgInteractionRepository() {
			cacheAdapter = (ESSCacheAdapter) CacheAdapterFactory.getCacheAdapter( CACHE_NAME_SALES_INTERACTION );
		}
		
		/**
		 * Save SaleInteraction to coherence
		 * 
		 * @param esdgInteraction
		 */
		public void put(EsdgInteractionDO_1 esdgInteraction) {
			
			String key = stripKey( esdgInteraction.getSalesInteractionId() );
			esdgInteraction.setSalesInteractionId(key);
			cacheAdapter.put( key, esdgInteraction );
			
			LOGGER.debug( "[" + cacheAdapter.getCacheName() + "] put salesInteractionId=" + key+ ", status=" + esdgInteraction.getStatus() 
			+ ", json: " + esdgInteraction.getJsonSalesTXNData() 
			);
		}

		/**
		 * Get SaleInteraction from coherence
		 * 
		 * @param interactionId
		 * @return
		 */
		public EsdgInteractionDO_1 get( String interactionId ) {
			
			String key = stripKey (interactionId );
			
			EsdgInteractionDO_1 esdgInteraction = (EsdgInteractionDO_1) cacheAdapter.get( key );
			
			if ( esdgInteraction!=null ) {
				LOGGER.debug( "salesInteractionId=" + key+ ", status=" + esdgInteraction.getStatus() 
				);
			} else {
				LOGGER.warn( "[" + cacheAdapter.getCacheName() + "] object not found , key:" + key );
			}
			return esdgInteraction;
		}
	}
	
	/**
	 * Repository class that is responsible for persist SalesOrder
	 */
	static class EsdgOrderRepository {
		

		private static final Logger LOGGER = Logger.getLogger(EsdgOrderRepository.class);
		
		private ESSCacheAdapter cacheAdapter;

		EsdgOrderRepository() {
			cacheAdapter = (ESSCacheAdapter) CacheAdapterFactory.getCacheAdapter( CACHE_NAME_SALES_CONTEXT_ORDER );
		}
		
		public static String stripKey( String salesId ) {
			return salesId;
		}
		
		/**
		 * Save SaleOrder to to coherence 
		 * 
		 * @param esdgOrder
		 */
		public void put( EsdgOrderDO_1 esdgOrder ) {
			
			String key = stripKey(esdgOrder.getSalesContextId());
			
			cacheAdapter.put( esdgOrder.getSalesContextId(), esdgOrder );

			LOGGER.debug( "interactionId=" + esdgOrder.getSalesInteractionId() + ", salesContextId=" + key 
					+ ",  status=" + esdgOrder.getOrderStatus() + ", json: " + esdgOrder.getJsonOrder() );
		}
		
		public void putAll(Map<String, Object> cacheMap ) {
			
			cacheAdapter.saveAllToCache(cacheMap);
			
			LOGGER.debug( "[" + cacheAdapter.getCacheName() + "] put objects, keys " + cacheMap.keySet() );
		}
		
		/**
		 * Get SaleOrder from database via coherence cache store
		 * @param salesId
		 * @return
		 */
		public EsdgOrderDO_1 get( String salesId ) {
			
			String key = stripKey(salesId);
			
			EsdgOrderDO_1 esdgOrder = (EsdgOrderDO_1)cacheAdapter.get(key);
			
			if ( esdgOrder!=null ) {
				LOGGER.debug( "get interactionId=" + esdgOrder.getSalesInteractionId() + ", salesContextId=" + key 
						+ "status=" + esdgOrder.getOrderStatus() + ", json: " + esdgOrder.getJsonOrder() );
			} else {
				LOGGER.warn( "[" + cacheAdapter.getCacheName() + "] object not found , key:" + key );
			}
			
			return esdgOrder;
		}

		public List<EsdgOrderDO_1> getAll( List<String> salesIdList ) {
			
			List<EsdgOrderDO_1> result = new ArrayList<EsdgOrderDO_1>();
			List<String> keys = new ArrayList<String>(salesIdList.size());
			for( String itemId : salesIdList ) {
				keys.add( stripKey(itemId) ) ;
			}
			
			if (keys.isEmpty()==false) {
				Map<String, Object> resultSet = cacheAdapter.getAllFromCache(keys);
				if ( MapUtils.isNotEmpty(resultSet) ) {
				
					for (Object elem:  resultSet.values() ) {
						result.add( (EsdgOrderDO_1) elem );
					}
					
					LOGGER.debug( "asked for keys" + keys + ", found " + resultSet.keySet() );
				} else {
					LOGGER.debug( "asked for keys" + keys + ", found nothing");
				}
			}
			
			return result;
		}
		
		/**
		 * get objects that only exists in cache
		 * 
		 * @param salesIdList
		 * @return
		 */
		public List<EsdgOrderDO_1> getWithoutLoad( List<String> salesIdList ) {
			
			List<String> inCacheKeys = new ArrayList<String>(salesIdList.size());
			
			//check if each key already in cache, only include the key that is in the cache
			for( String itemId : salesIdList ) {
				String interactionId = stripKey(itemId);
				if ( cacheAdapter.isInCache(interactionId) )  {
					inCacheKeys.add( interactionId ) ;
				}
			}
			
			List<EsdgOrderDO_1> result = new ArrayList<EsdgOrderDO_1>( inCacheKeys.size() );
			
			if (inCacheKeys.isEmpty()==false) {
				Map<String, Object> resultSet = cacheAdapter.getAllFromCache(inCacheKeys);
				if ( MapUtils.isNotEmpty(resultSet) ) {
				
					for (Object elem:  resultSet.values() ) {
						result.add( (EsdgOrderDO_1) elem );
					}
					
					LOGGER.debug( "asked for keys" + inCacheKeys + ", found " + resultSet.keySet() );
				} else {
					LOGGER.info( "asked for keys" + inCacheKeys + ", found nothing");
				}
			}
			
			return result;
		}
		
		
		/**
		 * query SalesOrder by interactionId - currently is not used
		 * 
		 * @param interactionId
		 * @return
		 */
		private static final ValueExtractor VE_INTERACTION_ID =  new ReflectionExtractor("getSalesInteractionId");
		
		@SuppressWarnings("unchecked")
		public List<EsdgOrderDO_1> getOrderByInteractionId( String interactionId) {
			
			NamedCache cache = cacheAdapter.getCache();
			
			long startTime = System.currentTimeMillis();

			List<EsdgOrderDO_1> result = new ArrayList<EsdgOrderDO_1>();
			
			try {
				cache.addIndex( VE_INTERACTION_ID, false, null);
				
				Filter filter = new EqualsFilter( VE_INTERACTION_ID, interactionId );
				
				Set<Map.Entry<String, EsdgOrderDO_1> > resultSet = cache.entrySet(filter );
				
				if (resultSet.isEmpty()==false ) {
					List<String> keys = new ArrayList<String>( resultSet.size() );
					Iterator<Map.Entry<String, EsdgOrderDO_1>> it = resultSet.iterator() ;
					while ( it.hasNext() ) {
						Map.Entry<String, EsdgOrderDO_1> entry = resultSet.iterator().next();
						keys.add( entry.getKey());
						result.add( entry.getValue() );
					}
					
					LOGGER.debug( "getOrderByInteractionId : " + interactionId + " results " + keys );
					
				}
				return result;
				
			} finally {
				LOGGER.debug( cache.getCacheName() + ": getOrderByInteractionId() timeElapsed(ms):" + (System.currentTimeMillis()- startTime )  );
			}		
		}
	}
	 
    public  SalesItemPerkVO getPerkByCartItemId( String salesOrderId ) {
    	SalesItemPerkVO perkDO = null;
		try {
			List<String> itemIdList = esdgDatabaseAdapter.searchSalesItemIdsBySalesOrderId( salesOrderId, ITEM_TYPE_PERK ) ;
			if ( CollectionUtils.isNotEmpty( itemIdList ) ) {
				if (itemIdList.size()>1 ) {
					LOGGER.warn( "Found multiple Perk items in database, return only the first one, salesItemId=" + itemIdList.get(0) ); 
				}
				EsdgSalesItemDO esdgItemDO = (EsdgSalesItemDO) salesItemCacheAdapter.get( itemIdList.get(0) );
				if ( esdgItemDO!=null ) {
					perkDO = JsonUtil.parseJsonToObject(esdgItemDO.getJsonSalesTXNData(), SalesItemPerkVO.class);
				}
			}
				
		} catch (RuntimeException ex) {
			LOGGER.error("--ESDG-- Failed to get salesItemPerk. salesOrderId= " + salesOrderId, ex);
		} finally {					
		}
		
		return perkDO;
	}
	
}
