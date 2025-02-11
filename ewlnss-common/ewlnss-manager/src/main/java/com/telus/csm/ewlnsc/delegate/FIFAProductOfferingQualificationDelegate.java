package com.telus.csm.ewlnsc.delegate;

import static com.telus.csm.ewlnsc.transformer.FIFAProductOfferingQualificationTransformer.getProductType;
import static com.telus.csm.ewlnsc.transformer.FIFAProductOfferingQualificationTransformer.populateAddOnPlan;
import static com.telus.csm.ewlnsc.transformer.FIFAProductOfferingQualificationTransformer.populateEquipments;
import static com.telus.csm.ewlnsc.transformer.FIFAProductOfferingQualificationTransformer.populateFeatures;
import static com.telus.csm.ewlnsc.transformer.FIFAProductOfferingQualificationTransformer.populateHomeSecurityChildOfferings;
import static com.telus.csm.ewlnsc.transformer.FIFAProductOfferingQualificationTransformer.populateOfferSummary;
import static com.telus.csm.ewlnsc.transformer.FIFAProductOfferingQualificationTransformer.populateSelectedEquipment;
import static com.telus.csm.ewlnsc.transformer.FIFAProductOfferingQualificationTransformer.transform;
import static com.telus.csm.ewlnsc.transformer.FIFAProductOfferingQualificationTransformer.transformChildOfferRequest;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.HOME_SECURITY;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.HSIC;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SHOPPING_CART_ITEM_CTX_TYPE_ADDON;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SHOPPING_CART_ITEM_CTX_TYPE_EQUIPMENT;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SHOPPING_CART_ITEM_CTX_TYPE_FEATURE;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SHOPPING_CART_ITEM_CTX_TYPE_PACK;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SHOPPING_CART_ITEM_CTX_TYPE_SUMMARY;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SING;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.TTV;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.telus.csm.ewlnsc.adapter.IFIFAProductOfferingRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetFIFAPoductOfferingAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetFIFAPoductOfferingAdapterResponse;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.FFHProductPlanAddOnTypeVO;
import com.telus.csm.ewlnsc.domain.FIFAProductOfferingsVO;
import com.telus.csm.ewlnsc.domain.GetReferencePDSResponseDO;
import com.telus.csm.ewlnsc.domain.ProductTypeBaseVO;
import com.telus.csm.ewlnsc.domain.ProductTypeVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.task.FIFAProductOfferingServiceTask;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.workmanager.ICommonJWorkManager;
import com.telus.csm.ewlnsc.util.workmanager.WorkManagerFactory;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.csm.ffpo.rest.domain.ProductOfferingQualificationItem;

import commonj.work.Work;

public class FIFAProductOfferingQualificationDelegate {
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(FIFAProductOfferingQualificationDelegate.class);
	private static ICommonJWorkManager workManager;
	
	private static List<String> ADDON_CATEGORY_TYPES = Arrays.asList( SHOPPING_CART_ITEM_CTX_TYPE_ADDON, SHOPPING_CART_ITEM_CTX_TYPE_PACK );
	
	private static Comparator<GetFIFAPoductOfferingAdapterResponse> offerResponseComparator;
	
	static { 

		//initialize static instance when class is loaded, to avoid create new instance over and over again
		offerResponseComparator = new Comparator<GetFIFAPoductOfferingAdapterResponse>() { 
				
				@Override
				public int compare( GetFIFAPoductOfferingAdapterResponse o1, GetFIFAPoductOfferingAdapterResponse o2 ) {
					
					int result = o1.getCategoryType().compareTo( o2.getCategoryType() );
					
					if ( result!=0) { //the two are for different categories
						if (SHOPPING_CART_ITEM_CTX_TYPE_SUMMARY.equals( o1.getCategoryType() ) ) {
							result = -1;
						} else if (SHOPPING_CART_ITEM_CTX_TYPE_SUMMARY.equals( o2.getCategoryType() ) ) {
							result = 1;
						} else {
							//just return the original result, we don't really care about the order for rest of category
						}
					} else if (o1.getCategoryName()!=null && o2.getCategoryName()!=null ){
						result = o1.getCategoryName().compareTo(o2.getCategoryName());
					}
					
					return result;
				}
			};
	}


	public FIFAProductOfferingQualificationDelegate() {
		
		try {
			workManager = WorkManagerFactory.getCommonJWorkManager();
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	public FIFAProductOfferingsVO getProductOfferingQualification(ShoppingCartVO request) {

		String functionName = "getProductOfferingQualification";
		logger.enter(functionName);

		FIFAProductOfferingsVO productOfferingsVO = new FIFAProductOfferingsVO();
		CartItemVO cartItemVO = request.getCartItemList().get(0);
		String productType = getProductType(cartItemVO);

		List<String> includeProductOfferingsList = getIncludedProductOffringsInSC(cartItemVO, productType);
		
		int contractTerm = 0;
		String term = getProductTypeBaseVO( cartItemVO, productType ).getSelectedContractTermCd();
		if ( term!=null ) {
			contractTerm = Integer.valueOf( term);
		}

		List<GetFIFAPoductOfferingAdapterResponse> fifaProductOfferingResponseList = getFifaProductOfferQualificationList( request, productType);
		
		if (fifaProductOfferingResponseList != null) {
			
			//sort the list to make sure first element is always offer summary
			sort(fifaProductOfferingResponseList);
			
			Iterator<GetFIFAPoductOfferingAdapterResponse> it = fifaProductOfferingResponseList.iterator();
			
			//first element is always offer summary
			populateOfferSummary(productOfferingsVO, it.next(), getProductTypeBaseVO(cartItemVO, productType), productType );
			
			if (HOME_SECURITY.equals( productType) ) {
				
				GetFIFAPoductOfferingAdapterResponse fifaOfferResponse = it.next();
				populateHomeSecurityChildOfferings( productOfferingsVO, fifaOfferResponse, includeProductOfferingsList , getSelectedEqupmentList(cartItemVO,productType), contractTerm  );
				
			} else {// for product type HSIC, TTV, SING 
			
				//the rest are ADDON / PACKS, EQUIPMENT
				while( it.hasNext() ) {
					
					GetFIFAPoductOfferingAdapterResponse fifaOfferResponse = it.next();
	
					String categoryType = fifaOfferResponse.getCategoryType();
					
					if ( ADDON_CATEGORY_TYPES.contains(categoryType ) ) {
						
						populateAddOnPlan(productOfferingsVO,	fifaOfferResponse, includeProductOfferingsList);
						
					} else if (SHOPPING_CART_ITEM_CTX_TYPE_FEATURE.equalsIgnoreCase(categoryType)) {
						//this is probably a dead code
						populateFeatures(productOfferingsVO, fifaOfferResponse, includeProductOfferingsList);
					} 
					else if(SHOPPING_CART_ITEM_CTX_TYPE_EQUIPMENT.equalsIgnoreCase(categoryType) ) {
						//This is not tested as of July 26
						populateEquipments(productOfferingsVO, fifaOfferResponse, includeProductOfferingsList, getSelectedEqupmentList(cartItemVO,productType) );
					}
				}
				populateSelectedEquipment(productOfferingsVO, getSelectedEqupmentList(cartItemVO,productType));
			}
		}
		
		
		return productOfferingsVO;
	}

	/**
	 * Equipments are applicable for TTV and HSIC only
	 * @param cartItemVO
	 * @param productType
	 * @return
	 */
	private List<FFHEquipmentTypeVO> getSelectedEqupmentList(CartItemVO cartItemVO, String productType) {
		if( cartItemVO == null || cartItemVO.getProducts()== null ) {
			return null;
		}
		ProductTypeVO productTypeVO = cartItemVO.getProducts();
		return (productTypeVO.getInternetProduct() != null && ! CollectionUtils.isEmpty(productTypeVO.getInternetProduct().getEquipments())) ? productTypeVO.getInternetProduct().getEquipments()
				: (productTypeVO.getTelevisionProduct() != null && ! CollectionUtils.isEmpty(productTypeVO.getTelevisionProduct().getEquipments())) ? productTypeVO.getTelevisionProduct().getEquipments()
				: (productTypeVO.getHomeSecurityProduct() != null && ! CollectionUtils.isEmpty(productTypeVO.getHomeSecurityProduct().getEquipments())) ? productTypeVO.getHomeSecurityProduct().getEquipments()
				: null ;

	}

	private List<String> getIncludedProductOffringsInSC(CartItemVO cartItemVO, String productType) {
		
		List<String> result = new ArrayList<String>();
		if (cartItemVO == null || cartItemVO.getProducts() == null) {
			return result;
		}
		List<FFHProductPlanAddOnTypeVO> addOns = SING.equalsIgnoreCase(productType)	? cartItemVO.getProducts().getHomePhoneProduct().getAddOns()
				: HSIC.equalsIgnoreCase(productType)? cartItemVO.getProducts().getInternetProduct().getAddOns()
				: TTV.equalsIgnoreCase(productType)	? cartItemVO.getProducts().getTelevisionProduct().getAddOns()
				: HOME_SECURITY.equalsIgnoreCase(productType)	? cartItemVO.getProducts().getHomeSecurityProduct().getAddOns()
				: null;
		
		if (!CollectionUtils.isEmpty(addOns)) {
			for (FFHProductPlanAddOnTypeVO productPlanAddOnTypeVO : addOns) {
				String offferingId = productPlanAddOnTypeVO.getProductCatalogueIdentifier().getProductCatalogueId();
				result.add(offferingId);
			}

		}
		return result;
	}

	//////////////// parallel calls to Fifa OQ service /////////////
	
	private List<GetFIFAPoductOfferingAdapterResponse> getFifaProductOfferQualificationList(ShoppingCartVO request, String productType ) {
		
		IFIFAProductOfferingRestSvcAdapter adapter = AdapterFactory.getAdapter(IFIFAProductOfferingRestSvcAdapter.class);
		List<Work> workTaskList = new ArrayList<Work>();

		
		if (HOME_SECURITY.equalsIgnoreCase(productType) ) {
			
			FIFAOfferCategory dummySummaryCategory = new FIFAOfferCategory(productType, SHOPPING_CART_ITEM_CTX_TYPE_SUMMARY );
			GetFIFAPoductOfferingAdapterRequest mainOfferRequest = transform( request , dummySummaryCategory );
			workTaskList.add( new FIFAProductOfferingServiceTask( adapter, mainOfferRequest, SHOPPING_CART_ITEM_CTX_TYPE_SUMMARY ,null,productType ));
			
			FIFAOfferCategory dummyAddonCategory = new FIFAOfferCategory(productType, SHOPPING_CART_ITEM_CTX_TYPE_ADDON);
			GetFIFAPoductOfferingAdapterRequest childOfferRequest = transformChildOfferRequest( request,dummyAddonCategory );
			workTaskList.add( new FIFAProductOfferingServiceTask( adapter, childOfferRequest, null ,null,productType ) );
			
		} else { // for non home security product
			
			List<Map<String, String>> refFifaIdList = getFifaCategoryIdListFromRefPds();
			
			List<FIFAOfferCategory> offerCategories = new ArrayList<FIFAOfferCategory> ();
			
			FIFAOfferCategory dummySummaryCategory = new FIFAOfferCategory(productType, SHOPPING_CART_ITEM_CTX_TYPE_SUMMARY );
			offerCategories.add( dummySummaryCategory ); 
			
		
			//get all Fifa ADDON offer categoryId:   
			offerCategories.addAll( lookupCategoryList( refFifaIdList, productType, SHOPPING_CART_ITEM_CTX_TYPE_ADDON ) ) ;
	
			//and PACKS for TTV
			if( TTV.equalsIgnoreCase(productType)) {
				offerCategories.addAll( lookupCategoryList( refFifaIdList, productType, SHOPPING_CART_ITEM_CTX_TYPE_PACK ) ) ;
			}
			
			//for each Fifa offer category, call OfferingQualifcation
			for (FIFAOfferCategory offerCategory : offerCategories) {
				GetFIFAPoductOfferingAdapterRequest getFIFAPoductOfferingAdapterRequest = transform( request, offerCategory );
				workTaskList.add( new FIFAProductOfferingServiceTask( adapter, getFIFAPoductOfferingAdapterRequest, offerCategory.getType(),null, offerCategory.getProduct()));
			}
		}
		
		return  executeParallelCall( workTaskList );
	}
	

//	@SuppressWarnings("unused")
//	private String getTTVType(ProductOfferingQualification productOfferingQualification) {
//
//		if (productOfferingQualification == null) {
//			return null;
//		}
//
//		ProductOfferingQualificationItem poqItem = productOfferingQualification.getProductOfferingQualificationItem() != null
//			? productOfferingQualification.getProductOfferingQualificationItem().get(0)	: null;
//
//		if (poqItem != null && poqItem.getProductOffering() != null) {
//			TProductOffering productOffering = poqItem.getProductOffering();
//			if (!CollectionUtils.isEmpty(productOffering.getCategory())) {
//				for (CategoryRef cat : productOffering.getCategory()) {
//					if (cat.getId().equalsIgnoreCase("9150253640113241856")) {
//						return OFFER_QUAL_SUMMARY_OPTIK_TV;
//					} else if (cat.getId().equalsIgnoreCase("9146775320213795833")) {
//						return OFFER_QUAL_SUMMARY_PIK_TV;
//					}
//				}
//			}
//		}
//		return null;
//	}


	private List<GetFIFAPoductOfferingAdapterResponse> executeParallelCall(List<Work> taskList) {
		
		String functionName = "executeParallelCall";
		logger.enter(functionName);
		
		List< GetFIFAPoductOfferingAdapterResponse> results = new ArrayList<GetFIFAPoductOfferingAdapterResponse>();
		
		List<String> uniqueOfferingIds = new ArrayList<String>();

		Collection<Work> responseTaskList = new ArrayList<Work>();
		try {
			responseTaskList = workManager.processTasks(taskList);
		} catch (Exception e) {
			logger.error("", functionName, e.getMessage(), e);
			throw new RuntimeException(e);
		}
		
		for (Work resultTask : responseTaskList) {
			
			GetFIFAPoductOfferingAdapterResponse response = ((FIFAProductOfferingServiceTask) resultTask).getResult();
			
			if (response.hasError()) {
				
				logger.error("error occurred in fifa product offerring qualification svc -" + response.getMessageList());
				
			} else {
				
				logOfferResponseSummary( "before filtering", response );

				String categoryType = response.getCategoryType();
				
				filterDuplicateProductOfferings( "category [" +response.getCategoryId() + ":  "+  categoryType + "]", uniqueOfferingIds,  response);
				
				results.add( response );
			}
		}
		
		logger.exit(functionName);
		return results;
	}

	//QC83856 fix, filter out duplicate add-on from Fifa OQ response
	private void filterDuplicateProductOfferings( String category, List<String> uniqueOfferingIds, GetFIFAPoductOfferingAdapterResponse response ) {
		
		List<String> offerSummaries = new ArrayList<String> ();
		List<String> duplicateSummaries = new ArrayList<String> ();
		
		List<ProductOfferingQualificationItem> duplicates = new ArrayList<ProductOfferingQualificationItem>();
		
		if (CollectionUtils.isNotEmpty( response.getProductOfferingQualification().getProductOfferingQualificationItem() ) ) {
			
			for( ProductOfferingQualificationItem item: response.getProductOfferingQualification().getProductOfferingQualificationItem()) {

				String offerSummaryInfo = item.getProductOffering().getId() + ": "+ item.getProductOffering().getName();
				
				if (uniqueOfferingIds.contains( item.getProductOffering().getId()) ) {
					duplicates.add( item );
					duplicateSummaries.add( offerSummaryInfo );
				} else {
					uniqueOfferingIds.add ( item.getProductOffering().getId() );
					offerSummaries.add( offerSummaryInfo );
				}
			}
			
			if ( duplicates.isEmpty()==false ) {
				//remove all duplicates
				response.getProductOfferingQualification().getProductOfferingQualificationItem().removeAll( duplicates );
			}
			
			//log the summaries
			logger.debug( "filter " + category, String.format(" added offering(%d): %s" , offerSummaries.size(),  offerSummaries.toString() ) );

			if ( duplicates.isEmpty()==false ) {
				logger.debug( "filter " + category, String.format(" removed duplicate offering(%d): %s", duplicateSummaries.size(), duplicateSummaries.toString() ) );
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<Map<String, String>> getFifaCategoryIdListFromRefPds() {
		
		GetReferencePDSResponseDO rsp = ReferencePDSDataSvcBusDelegator.getInstance().getReferencePDSTableObjectByName("NGC_FIFA_CATGY_ID_MAP" );
		
		Map<String, Object> refPdsMap = rsp.getRefpdsTable();
		
		return  (List<Map<String, String>>) refPdsMap.get("NGC_FIFA_CATGY_ID_MAP");
	}

	public List<FIFAOfferCategory> lookupCategoryList( List<Map<String, String>> refFifaIdList, String productType, String categoryType ) {
		
		List<FIFAOfferCategory> result = new ArrayList<FIFAOfferCategory> ();
		
		for (Map<String, String> elem : refFifaIdList) {
			
			if ( productType.equals(elem.get( "PROD") ) 
				&& categoryType.equals( elem.get( "CATGY_TYP" ) )	) {
				
				FIFAOfferCategory category = new FIFAOfferCategory();
				category.setProduct(elem.get( "PROD"));
				category.setType( elem.get( "CATGY_TYP" ));
				category.setId(  elem.get( "FIFA_CATGY_ID") );
				category.setName( elem.get( "FIFA_CATGY_DESC" ) );
				result.add(category );
				
			}
		}
		
		return result;
	}
	
	private void sort( List<GetFIFAPoductOfferingAdapterResponse> fifaProductOfferingResponseList ) {
		
		Collections.sort( fifaProductOfferingResponseList , offerResponseComparator );
		
		log( "after sorting", fifaProductOfferingResponseList);
	}

	private void log( String context, List<GetFIFAPoductOfferingAdapterResponse> fifaProductOfferingResponseList ) {
		
		for(GetFIFAPoductOfferingAdapterResponse offerReposne : fifaProductOfferingResponseList  ) {
			logOfferResponseSummary(context, offerReposne);
		}
	}

	private void logOfferResponseSummary( String context, GetFIFAPoductOfferingAdapterResponse offerReposne) {
		logger.debug( context,  
			String.format( "offeringQualificationResponse: offerId=%s, category[id=%s, type[%s / %s: %s], offer item count: %d",
			offerReposne.getMainOfferId(), offerReposne.getCategoryId(), offerReposne.getProductType(), offerReposne.getCategoryType(), offerReposne.getCategoryName()
			,offerReposne.getOfferCount() ) );
	}
	
	public static class FIFAOfferCategory {
		
		private String product;
		private String type;
		private String id;
		private String name;
		
		public FIFAOfferCategory() {}
		
		public FIFAOfferCategory(String productType, String categoryType) {
			setProduct(productType);
			setType(categoryType);
		}
		public String getProduct() {
			return product;
		}
		public void setProduct(String product) {
			this.product = product;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return String.format("FifaOfferCategory [product=%s, type=%s, id=%s, name=%s]", product, type, id, name);
		}
	}

	private ProductTypeBaseVO getProductTypeBaseVO(CartItemVO cartItemVO, String productType) {
		
		ProductTypeBaseVO result = null;
		result = SING.equalsIgnoreCase(productType)	? cartItemVO.getProducts().getHomePhoneProduct()
				: HSIC.equalsIgnoreCase(productType)? cartItemVO.getProducts().getInternetProduct()
				: TTV.equalsIgnoreCase(productType)	? cartItemVO.getProducts().getTelevisionProduct()
				: HOME_SECURITY.equalsIgnoreCase(productType)? cartItemVO.getProducts().getHomeSecurityProduct()
				: null;
	
		return result;
	}
}
