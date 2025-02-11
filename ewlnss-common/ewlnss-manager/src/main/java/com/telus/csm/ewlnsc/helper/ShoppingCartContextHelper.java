package com.telus.csm.ewlnsc.helper;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.HSIC;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SING;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.TTV;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.CPE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.telus.csm.ewlnsc.adapter.IConsumerBillingAccountManagementServiceAdapter;
import com.telus.csm.ewlnsc.adapter.IConsumerCustomerMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.IFeasibilityServiceAdapter;
import com.telus.csm.ewlnsc.adapter.IWLNCreditEligibilityProxyServiceAdapter;
import com.telus.csm.ewlnsc.adapter.ccm.domain.GetFullCustomerInfoAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetBillingAccountAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditEligibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditProfileByCustomerIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetProductQualificationAdapterRequest;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetProductQualificationAdapterResponse;
import com.telus.csm.ewlnsc.delegate.GetAvailableProductItemDelegate;
import com.telus.csm.ewlnsc.delegate.ShoppingCartDelegate;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHSweetenerTypeVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductRequestVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductResponseVO;
import com.telus.csm.ewlnsc.domain.GetAvailableProductItemDelegateResponse;
import com.telus.csm.ewlnsc.domain.GetOffersResponseVO2;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO2;
import com.telus.csm.ewlnsc.domain.GetSweetenerOfferListResponseVO2;
import com.telus.csm.ewlnsc.domain.ProductPromotionDiscountsVO;
import com.telus.csm.ewlnsc.domain.ProductTypeBaseVO;
import com.telus.csm.ewlnsc.domain.PromotionsVO;
import com.telus.csm.ewlnsc.domain.RelatedImmediatePromotionVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressRequestVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Offer;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.AccessoryOffer;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.SweetenerOfferSummary;
import com.telus.csm.ewlnsc.domain.schemasclone.GetSalesOfferDetailRequestVO;
import com.telus.csm.ewlnsc.task.GetAssignedAndPendingProductTask;
import com.telus.csm.ewlnsc.task.GetAvailablePromotionsTask;
import com.telus.csm.ewlnsc.task.GetBillingAccountTask;
import com.telus.csm.ewlnsc.task.GetCreditEligibilityTask;
import com.telus.csm.ewlnsc.task.GetCreditProfileByCustomerIdTask;
import com.telus.csm.ewlnsc.task.GetFullCustomerInfoTask;
import com.telus.csm.ewlnsc.task.GetProductQualificationTask;
import com.telus.csm.ewlnsc.task.GetSalesOfferDetailForShoppingCartTask;
import com.telus.csm.ewlnsc.task.GetServiceAddressDetailsTask;
import com.telus.csm.ewlnsc.transformer.EnterpriseWLNCommonTransformer;
import com.telus.csm.ewlnsc.transformer.FeasibilitySvcTransformer;
import com.telus.csm.ewlnsc.transformer.ShoppingCartCtxHelperTransformer;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.workmanager.ICommonJWorkManager;
import com.telus.csm.ewlnsc.util.workmanager.WorkManagerFactory;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;

import commonj.work.Work;

@Component
public class ShoppingCartContextHelper extends SpringBeanAutowiringSupport {
	
	private @Autowired BeanFactory beanFactory;
	
	private ICommonJWorkManager workManager;
	
	@Autowired
	private com.telus.csm.ewlnsc.adapter.IWirelineSalesEJBAdapter wirelineSalesEJBAdapter;
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(ShoppingCartContextHelper.class);
	
	public ShoppingCartContextHelper(){
		try {
			workManager = WorkManagerFactory.getCommonJWorkManager();
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public ShoppingCartContextVO execute(String shoppingCartId){
		return execute(shoppingCartId, false);
	}
	
	public ShoppingCartContextVO execute(String shoppingCartId, boolean isPostTask) {
		/**
		 * 1. Getting the ShoppingCartVO from the cache.
		 */
		
		//ShoppingCartVO shoppingCartVO = SalesContextDelegate.getInstance().getShoppingCartByShoppingCartId(shoppingCartId);
		ShoppingCartVO shoppingCartVO = ShoppingCartDelegate.getInstance().getShoppingCart(shoppingCartId);


		ShoppingCartContextVO shoppingCartContextVO=null;
		
		if(shoppingCartVO!=null){
			shoppingCartContextVO = this.execute(shoppingCartVO, isPostTask);
		}else{
			shoppingCartContextVO = new ShoppingCartContextVO(null);
		}
		
		return shoppingCartContextVO;
	}
	
	public ShoppingCartContextVO execute(ShoppingCartVO shoppingCartVO){
		return execute(shoppingCartVO, false);
	}
	
	public ShoppingCartContextVO execute(ShoppingCartVO shoppingCartVO, boolean isPostTask) {
		String functionName="execute()";
		ShoppingCartContextVO shoppingCartContextVO = new ShoppingCartContextVO(shoppingCartVO);

		/**
		 * 2. Get the assigned and pending products from consolidatedAcc
		 * a) check if the billingAccount is present in the ShoppingCartVO
		 */

		List<Work> workTaskList = new ArrayList<Work>();

		if (!shoppingCartVO.isWirelineProspectCustomer() && 
				(shoppingCartVO.getBillingAccount() != null && !StringUtils.isEmpty(shoppingCartVO.getBillingAccount().getBillingAccountNumber()))) {
			GetAssignedAndPendingProductRequestVO consolidatedaccProfileAdapterReq = ShoppingCartCtxHelperTransformer.transformConsolidatedAccRequest(shoppingCartVO);
			GetAssignedAndPendingProductTask consolidatedAccountTask  = new GetAssignedAndPendingProductTask(consolidatedaccProfileAdapterReq);
			workTaskList.add(consolidatedAccountTask);
		}

		if ((!shoppingCartVO.isWirelineProspectCustomer() || shoppingCartVO.isCustomerCreditCompleted() || isPostTask) && 
				shoppingCartVO.getBillingAccount() != null && !StringUtils.isEmpty(shoppingCartVO.getBillingAccount().getBillingAccountNumber())) {
			logger.debug(functionName, "BillingAccountNumber was passed in the request, calling SalesCustomerInfoSvc.getConsolidateAcc and CBAM.getBillingAccount. ");
			GetBillingAccountAdapterRequest getBillingAccountReq = ShoppingCartCtxHelperTransformer.transformGetBillingAccount(shoppingCartVO);
			IConsumerBillingAccountManagementServiceAdapter adapter = AdapterFactory.getAdapter(IConsumerBillingAccountManagementServiceAdapter.class);
			GetBillingAccountTask getBanTask = new GetBillingAccountTask(getBillingAccountReq, adapter);
			workTaskList.add(getBanTask);
		}
		
		/**
		 * 3. Get the serviceAddressDetails
		 */
		if (shoppingCartVO.getServiceAddress() != null) {
			ServiceAddressRequestVO serviceAddressRequestVO = ShoppingCartCtxHelperTransformer.transformServiceAddressRequest(shoppingCartVO);
			GetServiceAddressDetailsTask serviceAddressDetailsTask = new GetServiceAddressDetailsTask(serviceAddressRequestVO);
			workTaskList.add(serviceAddressDetailsTask);
		}

		/**
		 * 4. Get the productQualification
		 */
		if (shoppingCartVO.getServiceAddress() != null) {
			GetProductQualificationAdapterRequest productQualificationRequest = ShoppingCartCtxHelperTransformer.transformToProductQualificationRequest(shoppingCartVO);
			GetProductQualificationTask productQualificationTask = new GetProductQualificationTask(productQualificationRequest);
			workTaskList.add(productQualificationTask);
		}

		/**
		 * 5. Get the customerInfo
		 */
		if ((!shoppingCartVO.isWirelineProspectCustomer() || shoppingCartVO.isCustomerCreditCompleted() || isPostTask) && 
				(shoppingCartVO.getCustomer() != null && shoppingCartVO.getCustomer().getCustomerId() != null && !shoppingCartVO.getCustomer().getCustomerId().isEmpty())) {
			GetFullCustomerInfoAdapterRequest fullCustomerInfoAdapterRequest = new GetFullCustomerInfoAdapterRequest(Long.valueOf(shoppingCartVO.getCustomer().getCustomerId()), shoppingCartVO.getOperationHeader().getSalesTransactionId());
			IConsumerCustomerMgmtSvcAdapter getFullCustomerInfoAdapter = (IConsumerCustomerMgmtSvcAdapter) AdapterFactory.getAdapter(IConsumerCustomerMgmtSvcAdapter.class);
			GetFullCustomerInfoTask fullCustomerInfoTask = new GetFullCustomerInfoTask(fullCustomerInfoAdapterRequest, getFullCustomerInfoAdapter);
			workTaskList.add(fullCustomerInfoTask);
		}

		/**
		 * 7. Get the salesOfferDetailTask2
		 */
		if ( (shoppingCartVO.getCartItemList() != null) && (!shoppingCartVO.getCartItemList().isEmpty()) ) {
			for (CartItemVO cartItemVO : shoppingCartVO.getCartItemList()) {
				if (cartItemVO.isSalesOrderItem()) {
				final GetSalesOfferDetailHelper getSalesOfferDetailHelper = beanFactory.getBean(GetSalesOfferDetailHelper.class);
					GetSalesOfferDetailRequestVO salesOfferDetailRequestVO = ShoppingCartCtxHelperTransformer.tranformShoppingCartVOToGetSalesOfferDetailRequestVO(shoppingCartVO, cartItemVO, isPostTask);
					GetSalesOfferDetailForShoppingCartTask salesOfferDetailTask = new GetSalesOfferDetailForShoppingCartTask(salesOfferDetailRequestVO, getSalesOfferDetailHelper);
					workTaskList.add(salesOfferDetailTask);
				}
			}
		}

		/**
		 * 11. Assigned Pending v2.
		 */
		if (!shoppingCartVO.isWirelineProspectCustomer() && shoppingCartVO.getCartItemList() != null && !shoppingCartVO.getCartItemList().isEmpty()) {
			GetAssignedAndPendingProductRequestVO taskRequest = new GetAssignedAndPendingProductRequestVO();
			if(shoppingCartVO.getBillingAccount()!=null && shoppingCartVO.getCustomer()!=null && !StringUtils.isEmpty(shoppingCartVO.getCustomer().getCustomerId())){
				taskRequest.setBillingAccountNumber(shoppingCartVO.getBillingAccount().getBillingAccountNumber());
				taskRequest.setCustomerId(shoppingCartVO.getCustomer().getCustomerId());
				taskRequest.setOperationHeader(OperationHeaderUtil.buildOperationHeader(
						shoppingCartVO.getOperationHeader().getSalesTransactionId(), shoppingCartVO));
				
				//QC74221 - GetAssignedAndPendingProductTask2 refractoring
				GetAssignedAndPendingProductTask getAsssignedAndPendingProductTask
					= new GetAssignedAndPendingProductTask(taskRequest, null, wirelineSalesEJBAdapter);
				workTaskList.add(getAsssignedAndPendingProductTask);
			}

		}
		
		/*
		 * 12. Some more for post task
		 */
		if(isPostTask && shoppingCartVO.getCustomer() != null && !StringUtils.isEmpty(shoppingCartVO.getCustomer().getCustomerId())) {
			GetCreditProfileByCustomerIdAdapterRequest credProfileRequest = ShoppingCartCtxHelperTransformer.transformToGetCreditProfileByCustomerIdAdapterRequest(shoppingCartVO);
			GetCreditProfileByCustomerIdTask getCreditProfileByCustomerIdTask = new GetCreditProfileByCustomerIdTask(credProfileRequest);
			workTaskList.add(getCreditProfileByCustomerIdTask);
			
			GetCreditEligibilityAdapterRequest creditEligibilityAdapterReq = ShoppingCartCtxHelperTransformer.transformToGetCreditEligibilityAdapterRequest(shoppingCartVO);
			IWLNCreditEligibilityProxyServiceAdapter creditEligibilityAdapter = AdapterFactory.getAdapter(IWLNCreditEligibilityProxyServiceAdapter.class);
			GetCreditEligibilityTask getCreditEligibilityTask = new GetCreditEligibilityTask(creditEligibilityAdapter, creditEligibilityAdapterReq);
			workTaskList.add(getCreditEligibilityTask);
		}

		Collection<Work> resultList = executeParallelCall(workTaskList);
		
		processResults(resultList, shoppingCartContextVO);
		
		// trigger second paralel call for promotions
		workTaskList = new ArrayList<Work>();
		
		/**
		 * 9. Get the GetAvailablePromotionsTask task without coupon code.
		 */
		if (shoppingCartVO.getCartItemList() != null && !CollectionUtils.isEmpty(shoppingCartContextVO.getOfferList()) && !shoppingCartContextVO.getOfferList().get(0).hasError()) {
			final GetAvailablePromotionsTask availablePromoTask = new GetAvailablePromotionsTask(shoppingCartContextVO, isPostTask, false);
			workTaskList.add(availablePromoTask);
			
			/**
			 * 10. Get the GetAvailablePromotionsTask task for a given coupon code.
			 */
			if (isCouponInCart(shoppingCartVO.getCartItemList())) {
				final GetAvailablePromotionsTask availablePromoTask2 = new GetAvailablePromotionsTask(shoppingCartContextVO, isPostTask, true);
				workTaskList.add(availablePromoTask2);
			}
		}
		
		Collection<Work> resultList2 = executeParallelCall(workTaskList);
		processResults(resultList2, shoppingCartContextVO);
		
		//Feasibility Service cannot be called within the first round of parallel calls since it needs the contextVO object already populated.
		//moving call to Feasibility to be independent and then enrich the contextVO with the Feasibility service call.
		
		/**
		 * 10. Get Feasibility
		 */
		if(!CollectionUtils.isEmpty(shoppingCartVO.getCartItemList())){ //Alejandro: October 20, 2018 - Call FeasibilitySvc only when there are products in the ShoppingCart
			List<CartItemVO> cartItemList = shoppingCartVO.getCartItemList();
			boolean hasWirelineItem = false;
			
			//NWLN-10255 Only check feasibility when it has HS/TV/SING
			for (CartItemVO cartItem : cartItemList) {
				if (cartItem.getProducts() != null && (cartItem.getProducts().getInternetProduct() != null
						|| cartItem.getProducts().getTelevisionProduct() != null
						|| cartItem.getProducts().getHomePhoneProduct() != null)) {
					hasWirelineItem = true;
					break;
				}
			}
			//if the cart only has CPE products, skip feasibility
			if(hasWirelineItem) {
				CheckProductFeasibilityAdapterRequest feasibilitySvcRequest = FeasibilitySvcTransformer.transform(shoppingCartContextVO);
				IFeasibilityServiceAdapter feasibilityAdapter = AdapterFactory.getAdapter(IFeasibilityServiceAdapter.class, new AdapterFeatureDriver());
				CheckProductFeasibilityAdapterResponse checkProductFeasibilityResponse = feasibilityAdapter.checkProductFeasibility(feasibilitySvcRequest);
				
				if(checkProductFeasibilityResponse!=null){
					shoppingCartContextVO.setFeasibilityResponseVO(checkProductFeasibilityResponse);
				}
			}
		}
		
		/**
		 * 11. Call GetAvailableProductItemDelegateResponse
		 */
		GetAvailableProductItemDelegate availableProductItemDelegate = new GetAvailableProductItemDelegate();
		GetAvailableProductItemDelegateResponse availableProductItemDelegateResponse = availableProductItemDelegate.execute(shoppingCartVO.getOperationHeader().getSalesTransactionId(), shoppingCartContextVO);
		
		if(availableProductItemDelegateResponse!=null && availableProductItemDelegateResponse.getResponse()!=null){
			shoppingCartContextVO.setAvailableProductItemDelegateResponse(availableProductItemDelegateResponse);
		}
		
		return shoppingCartContextVO;		
	}
	
	private Collection<Work> executeParallelCall(List<Work> taskList){
		String functionName = "executeParallelCall";
		logger.enter(functionName);
		Collection<Work> responseTaskList = new ArrayList<Work>();
		try {
			responseTaskList = workManager.processTasks(taskList);
		}
		catch(Exception e){
			logger.error("",functionName,e.getMessage(),e);
			throw new RuntimeException(e);
		}
		logger.exit(functionName);
		return responseTaskList;
	}
	
	private void processResults(Collection<Work> workResultList, ShoppingCartContextVO shoppingCartCtxVO) {
		GetAssignedAndPendingProductTask assignedAndPendingProductTask = null;
		GetAssignedAndPendingProductTask opAssignedAndPendingProductTask = null;
		GetServiceAddressDetailsTask serviceAddressDetailsTask = null;
		GetProductQualificationTask productQualificationTask = null;
		GetFullCustomerInfoTask fullCustomerInfoTask = null;
		GetSalesOfferDetailForShoppingCartTask salesOfferDetailForShoppingCartTask = null;
		GetBillingAccountTask getBillingAccountTask = null;
		GetCreditProfileByCustomerIdTask getCreditProfileByCustomerIdTask = null;
		GetCreditEligibilityTask getCreditEligibilityTask = null;
		GetAvailablePromotionsTask getAvailablePromotionsTask = null;
		
		for (Work resultTask : workResultList) {
			if (resultTask != null) {
				if (resultTask instanceof GetAssignedAndPendingProductTask) {
					//QC74221 - GetAssignedAndPendingProductTask2 refractoring
					GetAssignedAndPendingProductTask t = (GetAssignedAndPendingProductTask) resultTask;
					if (t.isCallingOMS()) {
						opAssignedAndPendingProductTask = t;
					} else {
						assignedAndPendingProductTask = t;
					}
					
				} else if (resultTask instanceof GetServiceAddressDetailsTask) {
					serviceAddressDetailsTask = (GetServiceAddressDetailsTask) resultTask;
				} else if (resultTask instanceof GetProductQualificationTask) {
					productQualificationTask = (GetProductQualificationTask) resultTask;
				} else if (resultTask instanceof GetFullCustomerInfoTask) {
					fullCustomerInfoTask = (GetFullCustomerInfoTask) resultTask;
				} else if (resultTask instanceof GetSalesOfferDetailForShoppingCartTask) {
					salesOfferDetailForShoppingCartTask = (GetSalesOfferDetailForShoppingCartTask) resultTask;
				} else if (resultTask instanceof GetBillingAccountTask) {
					getBillingAccountTask = (GetBillingAccountTask) resultTask;
				} else if (resultTask instanceof GetCreditProfileByCustomerIdTask){
					getCreditProfileByCustomerIdTask = (GetCreditProfileByCustomerIdTask) resultTask;
				} else if (resultTask instanceof GetCreditEligibilityTask){
					getCreditEligibilityTask = (GetCreditEligibilityTask) resultTask;
				} else if (resultTask instanceof GetAvailablePromotionsTask) {
					getAvailablePromotionsTask = (GetAvailablePromotionsTask) resultTask;
					if (getAvailablePromotionsTask != null) {
						splitPromotions(getAvailablePromotionsTask.getResult(), shoppingCartCtxVO);
					}
				}
			}

			if (assignedAndPendingProductTask != null) {
				if (assignedAndPendingProductTask.getRuntimeException() != null) {
					logger.error("SalesCustomerInfoService.getConsolidatedAccount returned error.");
					GetAssignedAndPendingProductResponseVO assignedAndPendingResponse = EnterpriseWLNCommonTransformer
							.transformExceptionForAssignedAndPendingProducts(
									assignedAndPendingProductTask.getRuntimeException(),
									shoppingCartCtxVO.getShoppingCartVO().getOperationHeader().getSalesTransactionId());
					shoppingCartCtxVO.setAssignedAndPendingProductResponseVO(assignedAndPendingResponse);
				} else {
					shoppingCartCtxVO.setAssignedAndPendingProductResponseVO(assignedAndPendingProductTask.getResult());
				}
			}

			if (serviceAddressDetailsTask != null) {
				shoppingCartCtxVO.setServiceAddressResponseVO(serviceAddressDetailsTask.getResult());
			}

			if (productQualificationTask != null) {
				if (productQualificationTask.getRuntimeException() != null) {
					logger.error("SalesCustomerInfoService.productQualification returned error.");
					GetProductQualificationAdapterResponse productQualificationResponse = EnterpriseWLNCommonTransformer
							.transformExceptionForProductQualification(productQualificationTask.getRuntimeException(),
									shoppingCartCtxVO.getShoppingCartVO().getOperationHeader().getSalesTransactionId());
					shoppingCartCtxVO.setProductQualificationAdapterResponseVO(productQualificationResponse);
				} else {
					shoppingCartCtxVO.setProductQualificationAdapterResponseVO(productQualificationTask.getResult());
				}
			}

			if (fullCustomerInfoTask != null) {
				shoppingCartCtxVO.setFullCustomerInfoAdapterResponse(fullCustomerInfoTask.getResult());
			}

			if (salesOfferDetailForShoppingCartTask != null) {
				if (salesOfferDetailForShoppingCartTask.getResult().getOffer() != null || !CollectionUtils.isEmpty(salesOfferDetailForShoppingCartTask.getResult().getMessageList())) {
					shoppingCartCtxVO.getOfferList()
					.add(salesOfferDetailForShoppingCartTask.getResult());
					salesOfferDetailForShoppingCartTask = null;
				}

			}

			if (getBillingAccountTask != null) {
				shoppingCartCtxVO.setBillingAccountVO(getBillingAccountTask.getResult());
			}
			
			if(opAssignedAndPendingProductTask != null) {
				shoppingCartCtxVO.setOpAssignedAndPendingProductResponseVO(opAssignedAndPendingProductTask.getResult());
			}

			if(getCreditProfileByCustomerIdTask != null) {
				shoppingCartCtxVO.setGetCreditProfileByCustomerIdAdapterResponse(getCreditProfileByCustomerIdTask.getResult());
			}

			if(getCreditEligibilityTask != null) {
				shoppingCartCtxVO.setGetCreditEligibilityAdapterResponse(getCreditEligibilityTask.getResult());
			}
		}
	}
	
	/**
	 * Split and populate the promotions from PQ to shopping cart context.
	 * 
	 * @param promVO
	 * @param scCtxVO
	 */
	private void splitPromotions(final PromotionsVO promVO, final ShoppingCartContextVO scCtxVO) {
		// populate accessories if exist in the promotionsVO
		if (promVO != null && promVO.getAccessoryResponse() != null && promVO.getAccessoryResponse().getAccessoryOfferSummaryList() != null) {
			// first time it need to initialize the internal objects structure
			if (scCtxVO.getAvailableAccessoryResponse() == null) {
				scCtxVO.setAvailableAccessoryResponse(new GetOffersResponseVO2());
				scCtxVO.getAvailableAccessoryResponse().setAccessoryOfferSummaryList(new ArrayList<AccessoryOffer>());
			} else if (scCtxVO.getAvailableAccessoryResponse().getAccessoryOfferSummaryList() == null) {
				scCtxVO.getAvailableAccessoryResponse().setAccessoryOfferSummaryList(new ArrayList<AccessoryOffer>());
			}
			// append to accessories list
			scCtxVO.getAvailableAccessoryResponse().getAccessoryOfferSummaryList().addAll(promVO.getAccessoryResponse().getAccessoryOfferSummaryList());
		}

		// populate the sweeteners if exist in the promotionsVO
		if (promVO != null && promVO.getSweetenerOfferListResponse() != null && promVO.getSweetenerOfferListResponse().getSweetenerOfferSummaryList() != null) {
			// first time it need to initialize the internal objects structure
			if (scCtxVO.getAvailableSweetenerOfferListResponseVO() == null) {
				scCtxVO.setAvailableSweetenerOfferListResponseVO(new GetSweetenerOfferListResponseVO2());
				scCtxVO.getAvailableSweetenerOfferListResponseVO().setSweetenerOfferSummaryList(new ArrayList<SweetenerOfferSummary>());
			} else if (scCtxVO.getAvailableSweetenerOfferListResponseVO().getSweetenerOfferSummaryList() == null) {
				scCtxVO.getAvailableSweetenerOfferListResponseVO().setSweetenerOfferSummaryList(new ArrayList<SweetenerOfferSummary>());
			}
			// append to sweeteners list
			scCtxVO.getAvailableSweetenerOfferListResponseVO().getSweetenerOfferSummaryList().addAll(promVO.getSweetenerOfferListResponse().getSweetenerOfferSummaryList());
		}
		
		// populate the product discounts
		if (promVO != null) {
			final Map<String, ProductPromotionDiscountsVO> prodDiscMap = promVO.getProductPromotionDiscountsOferMap();
			if (prodDiscMap != null && !CollectionUtils.isEmpty(scCtxVO.getOfferList())) {
				final List<GetSalesOfferDetailResponseVO2> offerList = scCtxVO.getOfferList();
				for (final GetSalesOfferDetailResponseVO2 offerElem : offerList) {
					Offer offer = offerElem.getOffer();
					if (offer != null) {
						for (final WirelineOfferProduct elem : offer.getOfferProduct().getWirelineOfferProductList()) {
							String offerID = offer.getOfferId()+"";
							if (prodDiscMap.get(offerID) != null) {
								String prodCd = elem.getProductTypeCode();
								if (SING.equalsIgnoreCase(prodCd)) {
									elem.getDiscountList().addAll(prodDiscMap.get(offerID).getSingPromotionDiscountList());
								} else if (HSIC.equalsIgnoreCase(prodCd)) {
									elem.getDiscountList().addAll(prodDiscMap.get(offerID).getHsicPromotionDiscountList());
								} else if (TTV.equalsIgnoreCase(prodCd)) {
									elem.getDiscountList().addAll(prodDiscMap.get(offerID).getTtvPromotionDiscountList());
								} else if (CPE.equalsIgnoreCase(prodCd)) {
									// NWLN-10301 add all CPE discount to offer
									elem.getDiscountList().addAll(prodDiscMap.get(offerID).getCpePromotionDiscountList());
								}
							}
						}
					}
				}
			}
		}
	}
	
	private GetSalesOfferDetailResponseVO toGetSalesOfferDetailResponseVO(
			com.telus.csm.ewlnsc.domain.schemasclone.GetSalesOfferDetailResponseVO result,String offerId) {
		GetSalesOfferDetailResponseVO response = new GetSalesOfferDetailResponseVO();
		response.setOffer(result.getOffer());
		response.setProgramPromotion(result.getProgramPromotion());
		response.setRecontractEligibleProductCodeList(result.getRecontractEligibleProductCodeList());
		response.setTransactionId(result.getTransactionId());
		response.setMessageList(getMessageList(result.getMessageList()));
		response.setCartItemOfferId(offerId);
		return response;
	}

	private List<MessageList> getMessageList(List<com.telus.csm.ewlnsc.domain.schemasclone.SalesResponseMessage.MessageList> messageList) {
		List<MessageList> resultMessageList = new ArrayList<MessageList>();
		
		if(!CollectionUtils.isEmpty(messageList)){
			for(com.telus.csm.ewlnsc.domain.schemasclone.SalesResponseMessage.MessageList msgList : messageList){
				MessageList resultMsgList = new MessageList();
				resultMsgList.setContextData(msgList.getContextData());
				resultMsgList.setErrorCode(msgList.getErrorCode());
				resultMsgList.setMessageList(msgList.getMessageList());
				resultMessageList.add(resultMsgList);
			}
		}
		
		return resultMessageList;
	}
	
	private boolean isCouponInCart(final List<CartItemVO> ciList) {
		boolean result = false;
		
		for (final CartItemVO elem : ciList) {
			if (getCouponId(elem.getRelatedPromotionList()) != null) {
				result = true;
				break;
			}
			
			// coupon not found yet, check if there is a coupon at the sweetener level
			if (elem.isSalesOrderItem() && elem.getProducts() != null) {
				final List<ProductTypeBaseVO> prodVOs = new ArrayList<ProductTypeBaseVO>();
				
				prodVOs.add((elem.getProducts().getHomePhoneProduct() != null) ? elem.getProducts().getHomePhoneProduct() : null);
				prodVOs.add((elem.getProducts().getInternetProduct() != null) ? elem.getProducts().getInternetProduct() : null);
				prodVOs.add((elem.getProducts().getTelevisionProduct() != null) ? elem.getProducts().getTelevisionProduct() : null);
				
				result = getCouponFromProducts(prodVOs);
			}
		}
		
		return result;
	}

	private boolean getCouponFromProducts(final List<ProductTypeBaseVO> prodVOs) {
		boolean result = false;
		
		for (final ProductTypeBaseVO prodElem : prodVOs) {
			if (!result && prodElem != null && prodElem.getSweeteners() != null) {
				for (final FFHSweetenerTypeVO sweetElem : prodElem.getSweeteners()) {
					result = (getCouponId(sweetElem.getRelatedPromotionList()) != null) ? true : false;
					if (result) {
						break;
					}
				}
			}
		}
		
		return result;
	}

	private String getCouponId(final List<RelatedImmediatePromotionVO> param) {
		String result = null;
		
		if (param != null) {
			for (final RelatedImmediatePromotionVO ripElem : param) {
				if (ripElem.getSelectedCoupon() != null && ripElem.getSelectedCoupon().getId() != null) {
					result = ripElem.getSelectedCoupon().getId();
				}
			}
		}
		
		return result;
	}
}