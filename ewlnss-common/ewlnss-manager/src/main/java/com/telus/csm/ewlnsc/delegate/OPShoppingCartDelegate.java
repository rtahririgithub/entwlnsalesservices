package com.telus.csm.ewlnsc.delegate;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.StringHolder;

import com.telus.csm.ewlnsc.adapter.IOrderQueryRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.CreateInvoiceAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CreateInvoiceAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetDepositInfoRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetDepositInfoResponse;
import com.telus.csm.ewlnsc.adapter.domain.quote.QuoteRequest;
import com.telus.csm.ewlnsc.adapter.domain.quote.QuoteResponse;
import com.telus.csm.ewlnsc.adapter.oqs.domain.GetOrderSummaryByOrderIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.oqs.domain.GetOrderSummaryByOrderIdAdapterResponse;
import com.telus.csm.ewlnsc.adapter.wbk.domain.CancelBookingAdapterRequest;
import com.telus.csm.ewlnsc.adapter.wbk.domain.CancelBookingAdapterResponse;
import com.telus.csm.ewlnsc.adapter.wbk.domain.ConfirmBookingAdapterRequest;
import com.telus.csm.ewlnsc.adapter.wbk.domain.ConfirmBookingAdapterResponse;
import com.telus.csm.ewlnsc.adapter.wbk.domain.GetBookingRequirementRequest;
import com.telus.csm.ewlnsc.adapter.wbk.domain.GetBookingRequirementResponse;
import com.telus.csm.ewlnsc.adapter.woscs.domain.CreateOrderAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.CreateOrderAdapterResponse;
import com.telus.csm.ewlnsc.adapter.woscs.domain.SubmitOrderAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.SubmitOrderAdapterResponse;
import com.telus.csm.ewlnsc.adapter.woscs.domain.UpdateOrderAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.UpdateOrderAdapterResponse;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ValidateProductConfigAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ValidateProductConfigAdapterResponse;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.OPShoppingCartDelegateResponseVO;
import com.telus.csm.ewlnsc.domain.OrderCommentVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.helper.EnterpriseWLNOrderUtil;
import com.telus.csm.ewlnsc.helper.OperationHeaderUtil;
import com.telus.csm.ewlnsc.transformer.opshoppingcart.OPShoppingCartTransformer;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.tmi.xmlschema.xsd.customer.customerbill.customer_billing_sub_domain_v2.BillingAccount;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;


public class OPShoppingCartDelegate {
	private static final LoggerUtil logger = LoggerUtil.getLogger(OPShoppingCartDelegate.class);

	public OPShoppingCartDelegateResponseVO retrieveOrder(ShoppingCartVO shoppingCartVO,Integer opOrderId) {
		
		OPShoppingCartDelegateResponseVO result = new OPShoppingCartDelegateResponseVO();
		
		if (shoppingCartVO == null || CollectionUtils.isEmpty(shoppingCartVO.getExternalOrderDetailList())) {
			return result;
		}
		
		IOrderQueryRestSvcAdapter orderQueryRestSvcAdapter = AdapterFactory.getAdapter(IOrderQueryRestSvcAdapter.class, new AdapterFeatureDriver());  

		GetOrderSummaryByOrderIdAdapterRequest getOrderSummaryByOrderIdAdapterRequest = new GetOrderSummaryByOrderIdAdapterRequest();

		getOrderSummaryByOrderIdAdapterRequest.setOrderId(String.valueOf(opOrderId));
		getOrderSummaryByOrderIdAdapterRequest.setPriceOffering(true);
		getOrderSummaryByOrderIdAdapterRequest.setTxnId(shoppingCartVO.getOperationHeader().getSalesTransactionId());
		
		GetOrderSummaryByOrderIdAdapterResponse adapterResponse  = orderQueryRestSvcAdapter.getOrderSummaryByOrderId(getOrderSummaryByOrderIdAdapterRequest);
		result.setMessageList(adapterResponse.getMessageList());
		result.setProductOrder(adapterResponse.getProductOrderDetail());
		
		return result;
	}	

	public OPShoppingCartDelegateResponseVO createOrder(ShoppingCartContextVO shoppingCartContextVO, CreateOrderAdapterRequest createOrderAdapterRequest) {
		ShoppingCartVO shoppingCartVO = shoppingCartContextVO.getShoppingCartVO();

		OperationHeader operationHeader = OperationHeaderUtil.buildOperationHeader(shoppingCartVO.getOperationHeader().getSalesTransactionId(), shoppingCartVO);

		CreateOrderAdapterResponse createOrderAdapterResponse = ExternalOrderManager.createOrder(operationHeader, createOrderAdapterRequest);

		OPShoppingCartDelegateResponseVO response = OPShoppingCartTransformer.transformToOPShoppingCartDelegateResponseVO(createOrderAdapterResponse);

		return response;
	}
	
	public static boolean canCreateOPShoppingCart(ShoppingCartContextVO shoppingCartContextVO, StringHolder error) {
		String funcName = "canCreateOPShoppingCart";
		if(shoppingCartContextVO.getShoppingCartVO().getFulfillmentOption() != null && EnterpriseWLNSalesServicesConstants.FULFILLMENT_MANUAL.equalsIgnoreCase(shoppingCartContextVO.getShoppingCartVO().getFulfillmentOption().getFulfillmentOptionTypeTxt())) {
			String errMsg = "Cannot proceed to OP with FulfillmentOption: " + shoppingCartContextVO.getShoppingCartVO().getFulfillmentOption().getFulfillmentOptionTypeTxt();
			logger.info(funcName, errMsg);
			return false;
		}
		//customer exists
		if(shoppingCartContextVO.getShoppingCartVO().getCustomer() == null || StringUtils.isEmpty(shoppingCartContextVO.getShoppingCartVO().getCustomer().getCustomerId())) {
			String errMsg = "Customer is missing";
			logger.info(funcName, errMsg);
			return false;
		}
		//account exists
		if(shoppingCartContextVO.getShoppingCartVO().getBillingAccount() == null || StringUtils.isEmpty(shoppingCartContextVO.getShoppingCartVO().getBillingAccount().getBillingAccountNumber())) {
			String errMsg = "BillingAccount is missing";
			logger.info(funcName, errMsg);
			return false;
		}
		//account active
		if(shoppingCartContextVO.getBillingAccountVO() == null || shoppingCartContextVO.getBillingAccountVO().getBillingAccount() == null) {
			String errMsg = "BillingAccount is not found";
			logger.info(funcName, errMsg);
			return false;
		}
		if(!isActiveAccount(shoppingCartContextVO.getBillingAccountVO().getBillingAccount())) {
			String errMsg = "BillingAccount [" + shoppingCartContextVO.getBillingAccountVO().getBillingAccount().getBillingAccountNumber() + "] is not active. StatusCode: " + shoppingCartContextVO.getBillingAccountVO().getBillingAccount().getStatusCode();
			logger.info(funcName, errMsg);
			return false;
		}
		
		//marketing offer check
		if(shoppingCartContextVO.getShoppingCartVO().getCartItemList() == null) {
			return false;
		}
		boolean hasMarketingOffer = false;
		for(CartItemVO cartItm: shoppingCartContextVO.getShoppingCartVO().getCartItemList()) {
			if(cartItm.getProductMarketOffering() != null 
					&& cartItm.getProductMarketOffering().getOfferHeader() != null
					&& !StringUtils.isEmpty(cartItm.getProductMarketOffering().getOfferHeader().getOfferId())) {
				hasMarketingOffer = true;
				break;
			}
		}
		if(!hasMarketingOffer) {
			String errMsg = "No market offer";
			logger.info(funcName, errMsg);
			return false;
		}
		//product is available
		boolean productAvailable = false;
		for(CartItemVO cartItm: shoppingCartContextVO.getShoppingCartVO().getCartItemList()) {
			if(cartItm.getProducts() != null 
					&& (cartItm.getProducts().getHomePhoneProduct() != null
							|| cartItm.getProducts().getInternetProduct() != null
							|| cartItm.getProducts().getMobilityProductVO() != null
							|| cartItm.getProducts().getTelevisionProduct() != null)) {
				productAvailable = true;
				break;
			}
		}
		if(!productAvailable) {
			String errMsg = "No product";
			logger.info(funcName, errMsg);
			return false;
		}			
		
		if(shoppingCartContextVO.getGetCreditProfileByCustomerIdAdapterResponse() != null) {
			String creditValueCd = shoppingCartContextVO.getCreditValueCode();
			if(!StringUtils.isEmpty(creditValueCd) 
					&& !(creditValueCd.equalsIgnoreCase(EnterpriseWLNSalesServicesConstants.CREDIT_VALUE_ESTABLISHED_CUSTOMER) 
							|| creditValueCd.equalsIgnoreCase(EnterpriseWLNSalesServicesConstants.NON_CREDIT_RESTRICTED_PROFILE))) { //not "E"
				String errMsg = "creditValueCd [" + creditValueCd + "] is not allowed.";
				logger.info(funcName, errMsg);
				return false;
			}
		}
		
		if(shoppingCartContextVO.getGetCreditEligibilityAdapterResponse() != null) {
			if(shoppingCartContextVO.getGetCreditEligibilityAdapterResponse().getCollectionInd() || shoppingCartContextVO.getGetCreditEligibilityAdapterResponse().getFraudInd()){
				String errMsg = "Customer has been flagged as 'in-treatment' or 'fraud'";
				logger.info(funcName, errMsg);
				return false;
			}
		}
		return true;
	}

	private static boolean isActiveAccount(BillingAccount billingAccount) {
		return Constants.ACC_STATUS_OPEN.equals(billingAccount.getStatusCode()) 
				|| Constants.ACC_STATUS_TENTATIVE.equals(billingAccount.getStatusCode()) 
				|| Constants.ACC_STATUS_SUSPENDED.equals(billingAccount.getStatusCode()) 
				|| Constants.ACC_STATUS_ACTIVE.equals(billingAccount.getStatusCode());
	}

	
	public OPShoppingCartDelegateResponseVO validateOrder(ShoppingCartContextVO shoppingCartContextVO, Integer opOrderId) {
		ValidateProductConfigAdapterResponse validateProductConfigAdapterResponse = null;

		if (canValidateOPShoppingCart(shoppingCartContextVO,opOrderId)) {
			ValidateProductConfigAdapterRequest validateProductConfigAdapterRequest = transformValidateProductConfig(opOrderId);

			OperationHeader operationHeader = OperationHeaderUtil.buildOperationHeader(shoppingCartContextVO.getShoppingCartVO().getOperationHeader().getSalesTransactionId(), shoppingCartContextVO.getShoppingCartVO());

			validateProductConfigAdapterResponse = ExternalOrderManager.validateProductConfig(operationHeader, validateProductConfigAdapterRequest);
		}

		OPShoppingCartDelegateResponseVO response = OPShoppingCartTransformer.transformToOPShoppingCartDelegateResponseVO(validateProductConfigAdapterResponse);
		return response;
	}

	private boolean canValidateOPShoppingCart(ShoppingCartContextVO shoppingCartContextVO,Integer opOrderId) {
		if (!canValidateOpShoppingCartId(shoppingCartContextVO,opOrderId)) {
			return false;
		}

//		if (!canValidateInternetEquipments(shoppingCartContextVO)) {
//			return false;
//		}
//
		return true ;
	}

	private boolean canValidateOpShoppingCartId(ShoppingCartContextVO shoppingCartContextVO, Integer opOrderId) {
		if(!EnterpriseWLNOrderUtil.opIdExistInExternalOrderDetailList(opOrderId, shoppingCartContextVO.getShoppingCartVO())){
			logger.info("canValidateOpShoppingCartId", "OpShoppingCartId is missing.");
			return false;
		}
	
		return true;
	}
	
	public GetOrderSummaryByOrderIdAdapterResponse getOrderSummaryByOrderId(ShoppingCartContextVO shoppingCartContextVO) {
		ShoppingCartVO shoppingCartVO = shoppingCartContextVO.getShoppingCartVO();
		OperationHeader operationHeader = OperationHeaderUtil.buildOperationHeader(shoppingCartVO.getOperationHeader().getSalesTransactionId(), shoppingCartVO);
		GetOrderSummaryByOrderIdAdapterRequest getOrderSummaryByOrderIdAdapterRequest = OPShoppingCartTransformer.transformToGetOrderSummaryByOrderIdAdapterRequest(shoppingCartContextVO);
		GetOrderSummaryByOrderIdAdapterResponse getOrderSummaryByOrderIdAdapterResponse = ExternalOrderManager.getOrderSummaryByOrderId(operationHeader, getOrderSummaryByOrderIdAdapterRequest);
		return getOrderSummaryByOrderIdAdapterResponse;
	}
	
//
//	private boolean canValidateInternetEquipments(ShoppingCartContextVO shoppingCartContextVO) {
//		if ( (shoppingCartContextVO.getShoppingCartVO().getCartItemList() != null) && (!shoppingCartContextVO.getShoppingCartVO().getCartItemList().isEmpty()) ) {
//			for (CartItemVO cartItem : shoppingCartContextVO.getShoppingCartVO().getCartItemList()) {
//				if ( (cartItem != null) && (cartItem.getProducts() != null) && (cartItem.getProducts().getInternetProduct() != null) ) {
//					if ( (cartItem.getProducts().getInternetProduct().getEquipments() == null) || (cartItem.getProducts().getInternetProduct().getEquipments().isEmpty()) ) {
//						return false;
//					}
//
//					for (FFHEquipmentTypeVO equipmentVO : cartItem.getProducts().getInternetProduct().getEquipments()) {
//						if (equipmentVO == null) { 
//							return false;
//						}
//
//						if ( (equipmentVO.getMaterialItemCode() == null) || (equipmentVO.getMaterialItemCode().isEmpty()) ) {
//							return false;
//						}
//
//						if ( (equipmentVO.getDeliveryMethodType() == null) || (equipmentVO.getDeliveryMethodType().isEmpty()) ) {
//							return false;
//						}
//
//						if ( (equipmentVO.getProductCatalogueIdentifier() == null) || (equipmentVO.getProductCatalogueIdentifier().getProductCatalogueId() == null) ||
//							 (equipmentVO.getProductCatalogueIdentifier().getProductCatalogueId().isEmpty()) ||
//							 (equipmentVO.getProductCatalogueIdentifier().getParentProductCatalogueId() == null) ||
//							 (equipmentVO.getProductCatalogueIdentifier().getParentProductCatalogueId().isEmpty())) {
//							return false;
//						}
//					}
//				}
//			}
//		}
//
//		return true;
//	}

	private ValidateProductConfigAdapterRequest transformValidateProductConfig(Integer opOrderId){
		ValidateProductConfigAdapterRequest validateProductConfigAdapterRequest = new ValidateProductConfigAdapterRequest();
		validateProductConfigAdapterRequest.setOrderId(opOrderId);
		validateProductConfigAdapterRequest.setReturnOrderDetails(false);
		return validateProductConfigAdapterRequest;
	}
	
	public ConfirmBookingAdapterResponse confirmBooking(ShoppingCartContextVO shoppingCartContextVO, String bookingId, Integer opOrderId) {
		ShoppingCartVO shoppingCartVO = shoppingCartContextVO.getShoppingCartVO();
		OperationHeader operationHeader = OperationHeaderUtil.buildOperationHeader(shoppingCartVO.getOperationHeader().getSalesTransactionId(), shoppingCartVO);
		ConfirmBookingAdapterRequest confirmBookingAdapterRequest = OPShoppingCartTransformer.transformToConfirmBookingAdapterRequest(shoppingCartContextVO, bookingId,opOrderId);
		ConfirmBookingAdapterResponse confirmBookingResponse = ExternalOrderManager.confirmBooking(operationHeader, confirmBookingAdapterRequest);
		return confirmBookingResponse;
	}
	
	public CancelBookingAdapterResponse cancelBooking(ShoppingCartVO shoppingCartVO, String bookingId, String cancellationSystem) {
		OperationHeader operationHeader = OperationHeaderUtil.buildOperationHeader(shoppingCartVO.getOperationHeader().getSalesTransactionId(), shoppingCartVO);
		CancelBookingAdapterRequest cancelBookingAdapterRequest = OPShoppingCartTransformer.transformToCancelBookingAdapterRequest(shoppingCartVO, bookingId,cancellationSystem);
		CancelBookingAdapterResponse response = ExternalOrderManager.cancelBooking(operationHeader, cancelBookingAdapterRequest);
		return response;
	}
	
	public GetDepositInfoResponse getDepositInfo(ShoppingCartContextVO shoppingCartContextVO, Integer opOrderId) {
		ShoppingCartVO shoppingCartVO = shoppingCartContextVO.getShoppingCartVO();
		OperationHeader operationHeader = OperationHeaderUtil.buildOperationHeader(shoppingCartVO.getOperationHeader().getSalesTransactionId(), shoppingCartVO);
		GetDepositInfoRequest getDepositInfoRequest = OPShoppingCartTransformer.transformToGetDepositInfoRequest(opOrderId);
		GetDepositInfoResponse getDepositInfoResponse = ExternalOrderManager.getDepositInfo(operationHeader, getDepositInfoRequest);
		return getDepositInfoResponse;
	}
	
	public CreateInvoiceAdapterResponse createInvoice(ShoppingCartContextVO shoppingCartContextVO, Integer opOrderId) {
		ShoppingCartVO shoppingCartVO = shoppingCartContextVO.getShoppingCartVO();
		OperationHeader operationHeader = OperationHeaderUtil.buildOperationHeader(shoppingCartVO.getOperationHeader().getSalesTransactionId(), shoppingCartVO);
		CreateInvoiceAdapterRequest createInvoiceAdapterRequest = OPShoppingCartTransformer.transformToCreateInvoiceAdapterRequest(opOrderId);
		CreateInvoiceAdapterResponse createInvoiceAdapterResponse = ExternalOrderManager.createInvoice(operationHeader, createInvoiceAdapterRequest);
		return createInvoiceAdapterResponse;
	}
	
	public SubmitOrderAdapterResponse submitOrder(ShoppingCartContextVO shoppingCartContextVO, Integer opOrderId, boolean forceBackOffice) {
		ShoppingCartVO shoppingCartVO = shoppingCartContextVO.getShoppingCartVO();
		OperationHeader operationHeader = OperationHeaderUtil.buildOperationHeader(shoppingCartVO.getOperationHeader().getSalesTransactionId(), shoppingCartVO);
		SubmitOrderAdapterRequest submitOrderAdapterRequest = OPShoppingCartTransformer.transformToSubmitOrderAdapterRequest(opOrderId, forceBackOffice);
		SubmitOrderAdapterResponse submitOrderAdapterResponse = ExternalOrderManager.submitOrder(operationHeader, submitOrderAdapterRequest);
		return submitOrderAdapterResponse;
	}
	
	public GetBookingRequirementResponse getBookingRequirement(ShoppingCartContextVO shoppingCartContextVO, Integer opOrderId) {
		ShoppingCartVO shoppingCartVO = shoppingCartContextVO.getShoppingCartVO();
		OperationHeader operationHeader = OperationHeaderUtil.buildOperationHeader(shoppingCartVO.getOperationHeader().getSalesTransactionId(), shoppingCartVO);
		GetBookingRequirementRequest getBookingRequirementRequest = OPShoppingCartTransformer.transformToGetBookingRequirementRequest(opOrderId);
		GetBookingRequirementResponse getBookingRequirementResponse = ExternalOrderManager.getBookingRequirement(operationHeader, getBookingRequirementRequest);
		return getBookingRequirementResponse;
	}
	
	public QuoteResponse getQuote(ShoppingCartContextVO shoppingCartContextVO, Integer opOrderId) {
		ShoppingCartVO shoppingCartVO = shoppingCartContextVO.getShoppingCartVO();
		OperationHeader operationHeader = OperationHeaderUtil.buildOperationHeader(shoppingCartVO.getOperationHeader().getSalesTransactionId(), shoppingCartVO);
		QuoteRequest getQuoteRequest = OPShoppingCartTransformer.transformToGetQuoteRequest(opOrderId);
		QuoteResponse getQuoteResponse = ExternalOrderManager.getQuote(operationHeader, getQuoteRequest);
		return getQuoteResponse;
	}

	public OPShoppingCartDelegateResponseVO updateOrder(ShoppingCartContextVO shoppingCartContextVO, List<OrderCommentVO> comments, com.telus.csm.ewlnsc.adapter.oqs.domain.ProductOrder productOrder,int opOrderId) {
		ShoppingCartVO shoppingCartVO = shoppingCartContextVO.getShoppingCartVO();

		OperationHeader operationHeader = OperationHeaderUtil.buildOperationHeader(shoppingCartVO.getOperationHeader().getSalesTransactionId(), shoppingCartVO);

		UpdateOrderAdapterRequest updateOrderAdapterRequest = OPShoppingCartTransformer.transformToUpdateOrderAdapterRequest(shoppingCartContextVO, comments, productOrder,opOrderId);

		UpdateOrderAdapterResponse updateOrderAdapterResponse = ExternalOrderManager.updateOrder(operationHeader, updateOrderAdapterRequest);

		OPShoppingCartDelegateResponseVO response = OPShoppingCartTransformer.transformToOPShoppingCartDelegateResponseVO(updateOrderAdapterResponse);

		return response;
	}
}