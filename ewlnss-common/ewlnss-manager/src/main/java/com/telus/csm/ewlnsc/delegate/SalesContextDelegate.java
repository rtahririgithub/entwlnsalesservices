/**
 * 
 */
package com.telus.csm.ewlnsc.delegate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ess.common.domain.ESDGCartItemVO;
import com.telus.csm.ess.common.domain.ESDGShoppingCartVO;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.cacheadapter.CacheAdapterFactory;
import com.telus.csm.ewlnsc.util.cacheadapter.ICacheAdapter;

/**
 * @author x145592
 *
 */
public class SalesContextDelegate {

	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(SalesContextDelegate.class);
	private static final String SHOPPING_CART_CACHEKEY_PREFIX = "ES_";
	private static SalesContextDelegate singleton = null;
	private	static final DateFormat UNIQUE_KEY_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	private ICacheAdapter cacheAdapter;

	private SalesContextDelegate() {
		cacheAdapter = CacheAdapterFactory.getCacheAdapter(EnterpriseWLNSalesServicesConstants.ESDG_SALES_CONTEXT_CACHE_NAME);
	}
	
	public static synchronized SalesContextDelegate getInstance() {

		if (singleton == null) {
			singleton = new SalesContextDelegate();
		}
		return singleton;
	}

	public String putShoppingCart(final ShoppingCartVO shoppingCartVO) {
		if(StringUtils.isEmpty(shoppingCartVO.getShoppingCartId())) {
			String shoppingCartId = makeCacheKey(createUniqueKey());
			shoppingCartVO.setShoppingCartId(shoppingCartId);
		} 
		final String cacheKey = shoppingCartVO.getShoppingCartId();
		
		ESDGShoppingCartVO cacheShoppingCart = new ESDGShoppingCartVO();
		String jsonSales = JsonUtil.getJsonFromObject(shoppingCartVO);
		cacheShoppingCart.setJsonShoppingCart(jsonSales);

		cacheAdapter.put(cacheKey, cacheShoppingCart);
		
		LOGGER.info("putShoppingCart", "under key=" + cacheKey + " order was saved to coherence: " + jsonSales);
		
		return shoppingCartVO.getShoppingCartId();
	}
	
	public String putCartItem(CartItemVO cartItemVO) {
		
		String result = null;
		String cacheKey = null;
// TODO Petru		
//		if (cartItemVO.getSalesIdentifier() == null || StringUtils.isEmpty(cartItemVO.getSalesIdentifier().getSalesItemId())) {
//			result = "ESITEM_" + createUniqueKey();
//		} else {
//			result = cartItemVO.getSalesIdentifier().getSalesItemId();
//		}
//
//		String cacheKey = "SALESID_" + cartItemVO.getSalesId() + "_SALESITEMID_" + result;
		
		ESDGCartItemVO cacheCartItem = new ESDGCartItemVO();
		String jsonSalesItem = JsonUtil.getJsonFromObject(cartItemVO);
		cacheCartItem.setJsonCartItem(jsonSalesItem);

		cacheAdapter.put(cacheKey, cacheCartItem);
		
		LOGGER.info("putCartItem", "under key=" + cacheKey + " CartItem was saved to coherence: " + jsonSalesItem);

		return result;
	}

	public ShoppingCartVO getShoppingCartByShoppingCartId(String shoppingCartId) {
		//String cacheKey = makeCacheKey(shoppingCartId);
		LOGGER.info("getShoppingCartByShoppingCartId", "Retrieving by cacheKey=" + shoppingCartId);
		ESDGShoppingCartVO salesFromCache = (ESDGShoppingCartVO) cacheAdapter.get(shoppingCartId);
		if(salesFromCache!=null){
			LOGGER.debug("getShoppingCartByShoppingCartId", "Retrieved:\n" + salesFromCache.getJsonShoppingCart());
			ShoppingCartVO shoppingCartVO = JsonUtil.parseJsonToObject(salesFromCache.getJsonShoppingCart(), ShoppingCartVO.class);
			return shoppingCartVO;
			
		} else {
			LOGGER.info("getShoppingCartByShoppingCartId", "Did not find a shopping cart with cacheKey=" + shoppingCartId);
		}
		return null;
	}

	private String createUniqueKey() {
		String result;
		
		final Date now = Calendar.getInstance().getTime();        
		result = new StringBuffer()
			.append(UNIQUE_KEY_DATE_FORMAT.format(now))
			.append(UUID.randomUUID())
			.toString().replaceAll("-", "");
		
		// make sure the new context id is not longer than 50 chars (SRPDS restriction)
		if (result.length() > 50) {
			result = result.substring(0, 49);
		}

		return result;
	}
	
	private String makeCacheKey(String shoppingCartId) {
		return SHOPPING_CART_CACHEKEY_PREFIX + shoppingCartId;
	}

}
