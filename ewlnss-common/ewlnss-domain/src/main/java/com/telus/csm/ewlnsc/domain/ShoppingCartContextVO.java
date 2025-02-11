/**
 * 
 */
package com.telus.csm.ewlnsc.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.ccm.domain.GetFullCustomerInfoAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.CreateInvoiceAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetBillingAccountAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditEligibilityAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditProfileByCustomerIdAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetDepositInfoResponse;
import com.telus.csm.ewlnsc.adapter.domain.quote.QuoteResponse;
import com.telus.csm.ewlnsc.adapter.oqs.domain.GetProductsByCustomerIdAdapterResponse;
import com.telus.csm.ewlnsc.adapter.oqs.domain.Product;
import com.telus.csm.ewlnsc.adapter.oqs.domain.ProductCharacteristicValue;
import com.telus.csm.ewlnsc.adapter.oqs.domain.ProductOrder;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetProductQualificationAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProductInstanceInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.adapter.wbk.domain.GetBookingRequirementResponse;
import com.telus.csm.ewlnsc.domain.price.PriceResponseVO;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.AccessoryOffer;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.SweetenerOfferSummary;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.EnterpriseSalesServiceUtil;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumercustomermgmtsvcrequestresponse_v2.FullCustomer;
/**
 * @author x145592
 *
 */
public class ShoppingCartContextVO {
	private final LoggerUtil logger = LoggerUtil.getLogger(this.getClass());

	private ShoppingCartVO shoppingCartVO;
	
	private GetAssignedAndPendingProductResponseVO assignedAndPendingProductResponseVO;
	private GetAssignedAndPendingProductResponseVO opAssignedAndPendingProductResponseVO;
	private ServiceAddressResponseVO serviceAddressResponseVO;
	private PriceResponseVO priceResponseVO;
	private GetProductQualificationAdapterResponse productQualificationAdapterResponseVO;
	private CheckProductFeasibilityAdapterResponse feasibilityResponseVO;
	private GetFullCustomerInfoAdapterResponse fullCustomerInfoAdapterResponse;
	private GetBillingAccountAdapterResponse billingAccountVO;
	private com.telus.csm.ewlnsc.domain.schemasclone.GetSalesOfferDetailResponseVO salesOfferDetailResponseVO;
	private GetSweetenerOfferListResponseVO2 availableSweetenerOfferListResponseVO;
	private List<GetSalesOfferDetailResponseVO2> offerList = new ArrayList<GetSalesOfferDetailResponseVO2>();
	private QuoteResponse quoteResponse;
	private GetDepositInfoResponse omsDepositResponse;
	private ProductOrder productOrder;
	private GetOffersResponseVO2 availableAccessoryResponse;
	private GetAvailableProductItemDelegateResponse availableProductItemDelegateResponse;
	private GetDepositInfoResponse getDepositInfoResponse;

	private CreateInvoiceAdapterResponse createInvoiceAdapterResponse;
	private GetCreditEligibilityAdapterResponse getCreditEligibilityAdapterResponse;
	private GetCreditProfileByCustomerIdAdapterResponse getCreditProfileByCustomerIdAdapterResponse;

	private OPShoppingCartDelegateResponseVO opShoppingCartDelegateResponseVO;
	private GetBookingRequirementResponse bookingRequirementResponse;
	
	public final static String ACQUISITION_TYPE_FOR_PURCHASE = "SA";
	public final static String ACQUISITION_TYPE_FOR_RENT = "RE";
	public final static String ACQUISITION_TYPE_FOR_OWNED = "POE";
	
	public ShoppingCartContextVO(final ShoppingCartVO param) {
		shoppingCartVO = param;
	}
	public ShoppingCartVO getShoppingCartVO() {
		return shoppingCartVO;
	}
	
	public GetAssignedAndPendingProductResponseVO getAssignedAndPendingProductResponseVO() {
		return assignedAndPendingProductResponseVO;
	}

	public void setAssignedAndPendingProductResponseVO(
			GetAssignedAndPendingProductResponseVO assignedAndPendingProductResponseVO) {
		this.assignedAndPendingProductResponseVO = assignedAndPendingProductResponseVO;
	}
	
	public String getCustomerId() {
		if (shoppingCartVO != null && shoppingCartVO.getCustomer()!=null) {
			return shoppingCartVO.getCustomer().getCustomerId();
		}
		return null;
	}
    
	public String getAccountNumber() {
		if (shoppingCartVO != null && shoppingCartVO.getBillingAccount().getBillingAccountNumber()!=null) {
			return shoppingCartVO.getBillingAccount().getBillingAccountNumber();
		}
		return null;
		
	}
	
	public ServiceAddressResponseVO getServiceAddressResponseVO() {
		return serviceAddressResponseVO;
	}
	public void setServiceAddressResponseVO(ServiceAddressResponseVO serviceAddressResponseVO) {
		this.serviceAddressResponseVO = serviceAddressResponseVO;
	}
	
	public PriceResponseVO getPriceResponseVO() {
		return priceResponseVO;
	}
	public void setPriceResponseVO(PriceResponseVO priceResponseVO) {
		this.priceResponseVO = priceResponseVO;
	}
	
    public List<OrderProductVO> getOrderedProducts() {
    	List<OrderProductVO> orderedProductList = new ArrayList<OrderProductVO>();
   	for(CartItemVO cartItem : shoppingCartVO.getCartItemList()) {
    	   if (cartItem.isSalesOrderItem()) {
    		   if (cartItem.getProducts()!=null) {
    			
    			if (cartItem.getProducts().getHomePhoneProduct() != null) {
    				OrderProductVO orderedProduct = new OrderProductVO();
    				orderedProduct.setAssignedProductID("WSS1");
  				    orderedProduct.setAssignedProductIDSourceSystemCd("VESTA");   				
  				    orderedProduct.setEstimatedUsageChargeAmt(new BigDecimal("0"));
    				//TODO: forbornInd?
    				orderedProduct.setForborneInd(isForborneInd());
    				orderedProduct.setLossRentalEquipmentChargeAmt(getLossRentalEquipmentChargeAmt(cartItem.getProducts()));
    				orderedProduct.setOfferNameCd("HomePhone"); //TODO: verify?
    				orderedProduct.setPayChannelNumberList(getPayChannelNumberList());	
    				orderedProduct.setPreviouslyAssessedDepositAmt(BigDecimal.valueOf(0));
    				orderedProduct.setProductName("Home Phone");
    				orderedProduct.setProductOrderCanceledInd(false);
    				orderedProduct.setPurchasedEquipmentCnt(getAcquiredEquipmentCount(cartItem.getProducts().getHomePhoneProduct().getEquipments()));
    				orderedProduct.setRentedEquipmentCnt(getRentedEquipmentCount(cartItem.getProducts().getHomePhoneProduct().getEquipments()));
 		
    				//TODO : serviceTypeCd
   				    orderedProduct.setServiceTypeCd("HSIC");
   				   if (getPriceResponseVO() != null && getPriceResponseVO().getBaseMonthlyCharge() != null && getPriceResponseVO().getBaseMonthlyCharge().getValueAmt() != null) {
   					   BigDecimal value = new BigDecimal(Float.toString(getPriceResponseVO().getBaseMonthlyCharge().getValueAmt()));
  				    	orderedProduct.setTotalRecurringChargeAmt(value);	
  				    } else {
  				    	orderedProduct.setTotalRecurringChargeAmt(BigDecimal.valueOf(0));
  				    }	
    				orderedProductList.add(orderedProduct);
    			} 
    			if (cartItem.getProducts().getInternetProduct() != null) {
    				OrderProductVO orderedProduct = new OrderProductVO();
      				orderedProduct.setAssignedProductID("WSS2");
  				    orderedProduct.setAssignedProductIDSourceSystemCd("VESTA");   				
  				    orderedProduct.setEstimatedUsageChargeAmt(new BigDecimal("0"));
    				//TODO: forbornInd?
    				orderedProduct.setForborneInd(isForborneInd());
    				orderedProduct.setLossRentalEquipmentChargeAmt(getLossRentalEquipmentChargeAmt(cartItem.getProducts()));
    				orderedProduct.setOfferNameCd("HighSpeedInternet"); //TODO: verify?
    				
    				orderedProduct.setPayChannelNumberList(getPayChannelNumberList());	
    				orderedProduct.setPreviouslyAssessedDepositAmt(BigDecimal.valueOf(0));
    				orderedProduct.setProductName("High Speed Internet");
    				orderedProduct.setProductOrderCanceledInd(false);
    				orderedProduct.setPurchasedEquipmentCnt(getAcquiredEquipmentCount(cartItem.getProducts().getInternetProduct().getEquipments()));
    				orderedProduct.setRentedEquipmentCnt(getRentedEquipmentCount(cartItem.getProducts().getInternetProduct().getEquipments()));
    				//TODO : serviceTypeCd
   				    orderedProduct.setServiceTypeCd("HSIC");
   				 
   				    if (getPriceResponseVO() != null && getPriceResponseVO().getBaseMonthlyCharge() != null && getPriceResponseVO().getBaseMonthlyCharge().getValueAmt() != null) {
   				    	BigDecimal value = new BigDecimal(Float.toString(getPriceResponseVO().getBaseMonthlyCharge().getValueAmt()));
   				    	orderedProduct.setTotalRecurringChargeAmt(value);	
   				    } else {
   				    	orderedProduct.setTotalRecurringChargeAmt(BigDecimal.valueOf(0));
   				    }
    				orderedProductList.add(orderedProduct);   				
    			} 
    			if (cartItem.getProducts().getTelevisionProduct() != null){
    				OrderProductVO orderedProduct = new OrderProductVO();
      				orderedProduct.setAssignedProductID("WSS3");
  				    orderedProduct.setAssignedProductIDSourceSystemCd("VESTA");   				
  				    orderedProduct.setEstimatedUsageChargeAmt(new BigDecimal("0"));
    				//TODO: forbornInd?
    				orderedProduct.setForborneInd(isForborneInd());
    				orderedProduct.setLossRentalEquipmentChargeAmt(getLossRentalEquipmentChargeAmt(cartItem.getProducts()));
    				orderedProduct.setOfferNameCd("TTV"); //TODO: verify?
    				orderedProduct.setPayChannelNumberList(getPayChannelNumberList());	
    				orderedProduct.setPreviouslyAssessedDepositAmt(BigDecimal.valueOf(0));
    				orderedProduct.setProductName("TTV");
    				orderedProduct.setProductOrderCanceledInd(false);
    				orderedProduct.setPurchasedEquipmentCnt(getAcquiredEquipmentCount(cartItem.getProducts().getTelevisionProduct().getEquipments()));
    				orderedProduct.setRentedEquipmentCnt(getRentedEquipmentCount(cartItem.getProducts().getTelevisionProduct().getEquipments()));
    				//TODO : serviceTypeCd
   				    orderedProduct.setServiceTypeCd("TTV");
   				   if (getPriceResponseVO() != null && getPriceResponseVO().getBaseMonthlyCharge() != null && getPriceResponseVO().getBaseMonthlyCharge().getValueAmt() != null) {
   					   BigDecimal value = new BigDecimal(Float.toString(getPriceResponseVO().getBaseMonthlyCharge().getValueAmt()));
  				    	orderedProduct.setTotalRecurringChargeAmt(value);	
  				    } else {
  				    	orderedProduct.setTotalRecurringChargeAmt(BigDecimal.valueOf(0));
  				    }	
    				orderedProductList.add(orderedProduct);
    		}
   		 }	
    	}		
   	}
    	return orderedProductList;
    }
   
	private Double getLossRentalEquipmentChargeAmt(ProductTypeVO product) {
		return Double.valueOf(0);
	}
	private List<Long> getPayChannelNumberList() {
		List<Long> payChannelNumberList = new ArrayList<Long>();
		//Hardcoded for now to avoid NPE
		payChannelNumberList.add(Long.valueOf(603360733));
		
		if (fullCustomerInfoAdapterResponse!=null) {
			FullCustomer fullCustomer = fullCustomerInfoAdapterResponse.getFullCustomer();
			if (fullCustomer.getDefaultPostpaidPayChannelId() != null) payChannelNumberList.add(fullCustomer.getDefaultPostpaidPayChannelId());
		}
		return payChannelNumberList;
	}
	public GetProductQualificationAdapterResponse getProductQualificationAdapterResponseVO() {
		return productQualificationAdapterResponseVO;
	}
	public void setProductQualificationAdapterResponseVO(GetProductQualificationAdapterResponse productQualificationAdapterResponseVO) {
		this.productQualificationAdapterResponseVO = productQualificationAdapterResponseVO;
	}
	private BigDecimal getMonthlyRecurringCharge(ProductTypeVO product) {
		// TODO Auto-generated method stub
		return BigDecimal.valueOf(0);
	}
	private boolean isForborneInd() {
		// TODO Auto-generated method stub
		return false;
	}
	private Integer getAcquiredEquipmentCount(List<FFHEquipmentTypeVO> equipmentList) {
		Integer equipmentCounter = 0;
		if (equipmentList != null ) {
			for (FFHEquipmentTypeVO equipment : equipmentList) {
				if (equipment.getAcquisitionType().isBuyIndicator()) {
					equipmentCounter++;
				} 
			}
		} 
	    return equipmentCounter;

	}
	private Integer getRentedEquipmentCount(List<FFHEquipmentTypeVO> equipmentList) {
		Integer equipmentCounter = 0;
		if (equipmentList != null ) {
			for (FFHEquipmentTypeVO equipment : equipmentList) {
				if (equipment.getAcquisitionType().isRentalEquipmentIndicator()) {
					equipmentCounter++;
				} 
			}
		} 
	    return equipmentCounter;
	}

	public GetFullCustomerInfoAdapterResponse getFullCustomerInfoAdapterResponse() {
		return fullCustomerInfoAdapterResponse;
	}
	public void setFullCustomerInfoAdapterResponse(GetFullCustomerInfoAdapterResponse fullCustomerInfoAdapterResponse) {
		this.fullCustomerInfoAdapterResponse = fullCustomerInfoAdapterResponse;
	}
	
	public List<GetSalesOfferDetailResponseVO2> getOfferList() {
		return offerList;
	}
	public void setOfferList(List<GetSalesOfferDetailResponseVO2> offerList) {
		this.offerList = offerList;
	}
	public com.telus.csm.ewlnsc.domain.schemasclone.GetSalesOfferDetailResponseVO getSalesOfferDetailResponseVO() {
		return salesOfferDetailResponseVO;
	}
	public void setSalesOfferDetailResponseVO(
			com.telus.csm.ewlnsc.domain.schemasclone.GetSalesOfferDetailResponseVO salesOfferDetailResponseVO) {
		this.salesOfferDetailResponseVO = salesOfferDetailResponseVO;
	}
	
	
	public GetSweetenerOfferListResponseVO2 getAvailableSweetenerOfferListResponseVO() {
		return availableSweetenerOfferListResponseVO;
	}
	public void setAvailableSweetenerOfferListResponseVO(
			GetSweetenerOfferListResponseVO2 availableSweetenerOfferListResponseVO) {
		this.availableSweetenerOfferListResponseVO = availableSweetenerOfferListResponseVO;
	}
	public String getOrderId() {
		String result = null;
		if (shoppingCartVO != null) {
			result = shoppingCartVO.getOperationHeader().getSalesTransactionId();
			List<NoteVO> notes = shoppingCartVO.getNote();
			if (notes != null) {
				for (NoteVO note : notes) {
					if ( "EXTERNAL_ORDER_ID".equalsIgnoreCase(note.getType())) {
						result = note.getText();
					}
				}
			}
		}
		
		return result;
	}
	/**
	 * 
	 * @return
	 */
	public boolean isManualOrder() {
		List<NoteVO> notes = shoppingCartVO.getNote();
		boolean fifaOrder = false;
		if (notes !=null ) {
			for (NoteVO note: notes) {
				if ("FIFA_RESILIENCY".equals(note.getText())) {
					fifaOrder=true;
					break;
				}
			}
		}
		return fifaOrder || "MANUAL".equalsIgnoreCase(shoppingCartVO.getFulfillmentOption().getFulfillmentOptionTypeTxt());
	}
	public GetBillingAccountAdapterResponse getBillingAccountVO() {
		return billingAccountVO;
	}
	public void setBillingAccountVO(GetBillingAccountAdapterResponse billingAccountVO) {
		this.billingAccountVO = billingAccountVO;
	}
	
	public QuoteResponse getQuoteResponse() {
		return quoteResponse;
	}
	public void setQuoteResponse(QuoteResponse quoteResponse) {
		this.quoteResponse = quoteResponse;
	}
	public GetDepositInfoResponse getOmsDepositResponse() {
		return omsDepositResponse;
	}
	public void setOmsDepositResponse(GetDepositInfoResponse omsDepositResponse) {
		this.omsDepositResponse = omsDepositResponse;
	}
	
	public GetAssignedAndPendingProductResponseVO getOpAssignedAndPendingProductResponseVO() {
		return opAssignedAndPendingProductResponseVO;
	}
	public void setOpAssignedAndPendingProductResponseVO(
			GetAssignedAndPendingProductResponseVO opAssignedAndPendingProductResponseVO) {
		this.opAssignedAndPendingProductResponseVO = opAssignedAndPendingProductResponseVO;
	}
	public List<SweetenerOfferSummary> getSweetenersInShoppingCart(){
		List<SweetenerOfferSummary> sweeteners = new ArrayList<SweetenerOfferSummary>();
		if( availableSweetenerOfferListResponseVO != null ) {
			sweeteners = availableSweetenerOfferListResponseVO.getSweetenerOfferSummaryList();
		}
		
		return sweeteners;
	}
	
	public List<SweetenerOfferSummary> getSweetnersByProduct(String productType, String cartItemRelationId){
		List<SweetenerOfferSummary> sweeteners = new ArrayList<SweetenerOfferSummary>();
		
		CartItemVO cartItem = getCartItemByCartItemRelationId(cartItemRelationId);
		if(cartItem != null && availableSweetenerOfferListResponseVO != null) {
					ProductTypeVO product = cartItem.getProducts();
					
					//HSIC
					if(EnterpriseWLNSalesServicesConstants.HSIC.equals(productType)) {
						if(product.getInternetProduct() != null && product.getInternetProduct().getSweeteners() != null) {
							for(FFHSweetenerTypeVO sweetener : product.getInternetProduct().getSweeteners()) {
								for(SweetenerOfferSummary sweeteenerOfferSummary : availableSweetenerOfferListResponseVO.getSweetenerOfferSummaryList()) {
									if(StringUtils.isNotBlank(sweetener.findPromotionId()) && sweetener.findPromotionId().equals(String.valueOf(sweeteenerOfferSummary.getPromotionId()))) {
										sweeteners.add(sweeteenerOfferSummary);
									}
								}
							}
							return sweeteners;
						}
					}
					
					//TV
					if(EnterpriseWLNSalesServicesConstants.TTV.equals(productType)) {
						sweeteners = new ArrayList<SweetenerOfferSummary>();
						if(product.getTelevisionProduct() != null && product.getTelevisionProduct().getSweeteners() != null) {
							for(FFHSweetenerTypeVO sweetener : product.getTelevisionProduct().getSweeteners()) {
								for(SweetenerOfferSummary sweeteenerOfferSummary : availableSweetenerOfferListResponseVO.getSweetenerOfferSummaryList()) {
									if(StringUtils.isNotBlank(sweetener.findPromotionId()) && sweetener.findPromotionId().equals(String.valueOf(sweeteenerOfferSummary.getPromotionId()))) {
										sweeteners.add(sweeteenerOfferSummary);
									}
								}
							}
							return sweeteners;
						}
					}
					
					
					//SL
					if(EnterpriseWLNSalesServicesConstants.SING.equals(productType)) {
						sweeteners = new ArrayList<SweetenerOfferSummary>();
						if(product.getHomePhoneProduct() != null && product.getHomePhoneProduct().getSweeteners() != null) {
							for(FFHSweetenerTypeVO sweetener : product.getHomePhoneProduct().getSweeteners()) {
								for(SweetenerOfferSummary sweeteenerOfferSummary : availableSweetenerOfferListResponseVO.getSweetenerOfferSummaryList()) {
									if(StringUtils.isNotBlank(sweetener.findPromotionId()) && sweetener.findPromotionId().equals(String.valueOf(sweeteenerOfferSummary.getPromotionId()))) {
										sweeteners.add(sweeteenerOfferSummary);
									}
								}
							}
							return sweeteners;
						}
					}
		}
		
		
		return sweeteners;
	}
	
	public GetSalesOfferDetailResponseVO2 getOfferByCartItemOfferId(String cartItemOfferId) {
		if(!CollectionUtils.isEmpty(offerList)){
			for (GetSalesOfferDetailResponseVO2 offer : offerList) {
				if((offer.getOffer()!=null &&  String.valueOf(offer.getOffer().getOfferId()).equals(cartItemOfferId)) || (cartItemOfferId.equals(offer.getCartItemOfferId()) && offer.getMessageList()!=null)){
					return offer;
				}
			}
		}
		return null;
	}

	public CartItemVO getCartItemByCartItemRelationId(String cartItemRelationId) {
		if(shoppingCartVO != null && shoppingCartVO.getCartItemList() != null ) {
			for(CartItemVO cartItem : shoppingCartVO.getCartItemList()) {
				if(cartItem.getCartItemRelationId() != null) {
					if(cartItem.getCartItemRelationId().equals(cartItemRelationId)) {
						return cartItem;
					}
				}
			}
		}
		
		return null;
	}
	
	public SweetenerOfferSummary getInstallionCreditSweetener() {
		
		// Weekday installation credit changes - Gary - 2019-08-01
		// List of codes.
//		String installCreditConfig = ApplicationProperties.getConfigString("${common/installCredit/sweetnerCodes}");
//		String[] installCreditSweetenners = installCreditConfig.split(",");
//
//		if (installCreditSweetenners == null || installCreditSweetenners.length == 0) {
//			return null;
//		}
//
//		for (int i = 0; i < installCreditSweetenners.length; i++) {
//			if (availableSweetenerOfferListResponseVO != null
//					&& availableSweetenerOfferListResponseVO.getSweetenerOfferSummaryList() != null) {
//				for (SweetenerOfferSummary sweeteenerOfferSummary : availableSweetenerOfferListResponseVO
//						.getSweetenerOfferSummaryList()) {
//					if (sweeteenerOfferSummary.getProgramCode().equalsIgnoreCase(installCreditSweetenners[i])) {
//						return sweeteenerOfferSummary;
//					}
//				}
//			}
//		}
//		return null;
		
		if (this.availableSweetenerOfferListResponseVO != null &&
			this.availableSweetenerOfferListResponseVO.getSweetenerOfferSummaryList() != null){
			
			for (SweetenerOfferSummary sweeteenerOfferSummary : this.availableSweetenerOfferListResponseVO.getSweetenerOfferSummaryList()){
				if (sweeteenerOfferSummary.hasInstallCredit()){
					return sweeteenerOfferSummary;
				}
			}
		}
		
		return null;
		
	}
	
	public boolean isEligibleForInstallCredit(Date appointmentDate ) {
		//1. Install date must be >= trunc(sysdate)  +1
		//2. Install date must be <= trunc(sysdate) + 5
		//3. Install date dayOfWeek must be in (Mon, Tues, Wed, Thurs, or Fri) 
		if (appointmentDate != null) {
			Date sysdatePlusOne = EnterpriseSalesServiceUtil.addDays(Calendar.getInstance().getTime(), 1);
			sysdatePlusOne = EnterpriseSalesServiceUtil.removeTime(sysdatePlusOne);
			
			Date sysdatePlusFive = EnterpriseSalesServiceUtil.addDays(Calendar.getInstance().getTime(), 5);
			sysdatePlusFive = EnterpriseSalesServiceUtil.removeTime(sysdatePlusFive);
			
			if(appointmentDate.compareTo(sysdatePlusOne) >= 0 && appointmentDate.compareTo(sysdatePlusFive) <=0 &&  EnterpriseSalesServiceUtil.isWeekday(appointmentDate)) {
				return true;
			}
		}
		
		return false;
	}
	
	public ProductOrder getProductOrder() {
		return productOrder;
	}
	
	public void setProductOrder(ProductOrder productOrder) {
		this.productOrder = productOrder;
	}
	public FFHEquipmentTypeVO getEquipmentByProductCatalogId(String productcatalogId, String productParentCatalogId) {
		for (CartItemVO cartItem : shoppingCartVO.getCartItemList()) {
			FFHEquipmentTypeVO equipment = getEquipmentByCatalogId(cartItem.getProducts(), productcatalogId, productParentCatalogId); 
			if (equipment != null) {
				return equipment;
			}
		}
		return null;
	}
	
	
	
	public FFHEquipmentTypeVO getEquipmentByCatalogId(ProductTypeVO product, String productcatalogId, String productParentCatalogId){
		  if (product.getInternetProduct() != null) {
			  FFHEquipmentTypeVO equipment = getEquipmentByCatalogId(product.getInternetProduct(), productcatalogId, productParentCatalogId);
			  if (equipment != null) {
				  return  equipment;
			  }
		  }	else   if (product.getTelevisionProduct() != null) {
			  FFHEquipmentTypeVO equipment = getEquipmentByCatalogId(product.getTelevisionProduct(), productcatalogId, productParentCatalogId);
			  if (equipment != null) {
				  return  equipment;
			  }
		  } else if (product.getHomePhoneProduct() != null) {
			  FFHEquipmentTypeVO equipment = getEquipmentByCatalogId(product.getHomePhoneProduct(), productcatalogId, productParentCatalogId);
			  if (equipment != null) {
				  return  equipment;
			  }
		  } 
		  return null;
	  }		
	public CartItemVO getCartItemForInternetProduct(String productCatalogId) {
		for (CartItemVO cartItem : shoppingCartVO.getCartItemList()) {
			InternetProductTypeVO product = cartItem.getProducts().getInternetProduct();
			if (product != null) {
				List<ProductComponentVO> productCatalogIds = product.getProductComponents();
				if (productCatalogIds != null) {
					for (ProductComponentVO productComponent : productCatalogIds) {
						if (productCatalogId.equalsIgnoreCase(productComponent.getProductCatalogueId())) {
							return cartItem;
						}
					}
				}
			}
		}
		return null;
	}
	
	public FFHEquipmentTypeVO  getEquipmentByCatalogId(InternetProductTypeVO product, String productcatalogId, String productParentCatalogId){
		   if (StringUtils.isNotEmpty(productParentCatalogId) && StringUtils.isNotEmpty(productcatalogId)) {
				for (FFHEquipmentTypeVO equipment : product.getEquipments()) {
				   if (productcatalogId.equalsIgnoreCase(equipment.getProductCatalogueIdentifier().getProductCatalogueId()) && productParentCatalogId.equalsIgnoreCase(equipment.getProductCatalogueIdentifier().getParentProductCatalogueId())) {
					   return equipment;
				   }
			    }
			}			  
		   return null;
	} 
	
	public FFHEquipmentTypeVO  getEquipmentByCatalogId(TelevisionProductTypeVO product, String productcatalogId, String productParentCatalogId){
		   if (StringUtils.isNotEmpty(productParentCatalogId) && StringUtils.isNotEmpty(productcatalogId)) {
				for (FFHEquipmentTypeVO equipment : product.getEquipments()) {
				   if (productcatalogId.equalsIgnoreCase(equipment.getProductCatalogueIdentifier().getProductCatalogueId()) && productcatalogId.equalsIgnoreCase(equipment.getProductCatalogueIdentifier().getParentProductCatalogueId())) {
					   return equipment;
				   }
			    }
			}			  
		   return null;
	} 
	
	public FFHEquipmentTypeVO  getEquipmentByCatalogId(HomePhoneProductTypeVO product, String productcatalogId, String productParentCatalogId){
		   if (StringUtils.isNotEmpty(productParentCatalogId) && StringUtils.isNotEmpty(productcatalogId)) {
				for (FFHEquipmentTypeVO equipment : product.getEquipments()) {
				   if (productcatalogId.equalsIgnoreCase(equipment.getProductCatalogueIdentifier().getProductCatalogueId()) && productcatalogId.equalsIgnoreCase(equipment.getProductCatalogueIdentifier().getParentProductCatalogueId())) {
					   return equipment;
				   }
			    }
			}			  
		   return null;
	} 	
	
	public CartItemVO getCartItemByEquipmentProductCatalogId(String productcatalogId, String productParentCatalogId) {
		for (CartItemVO cartItem : shoppingCartVO.getCartItemList()) {
			FFHEquipmentTypeVO equipment = getEquipmentByCatalogId(cartItem.getProducts(), productcatalogId, productParentCatalogId); 
			if (equipment != null) {
				return cartItem;
			}
		}
		return null;
	}

	public GetOffersResponseVO2 getAvailableAccessoryResponse() {
		return availableAccessoryResponse;
	}

	public void setAvailableAccessoryResponse(GetOffersResponseVO2 availableAccessoryResponse) {
		this.availableAccessoryResponse = availableAccessoryResponse;
	}
	public GetAvailableProductItemDelegateResponse getAvailableProductItemDelegateResponse() {
		return availableProductItemDelegateResponse;
	}
	public void setAvailableProductItemDelegateResponse(GetAvailableProductItemDelegateResponse availableProductItemDelegateResponse) {
		this.availableProductItemDelegateResponse = availableProductItemDelegateResponse;
	}

	public List<AccessoryOffer> getAvailableGWPAccessoryPromotionsInShoppingCart (CartItemVO cartItemVO) {
		List<AccessoryOffer> accessoryOfferSummaryList = null;
		
		if ( (availableAccessoryResponse != null) &&
			 (availableAccessoryResponse.getAccessoryOfferSummaryList() != null) &&
			 (!availableAccessoryResponse.getAccessoryOfferSummaryList().isEmpty())) {
			accessoryOfferSummaryList = new ArrayList<AccessoryOffer>();

			for (AccessoryOffer accessoryOffer : availableAccessoryResponse.getAccessoryOfferSummaryList()) {
				if ( (accessoryOffer != null) &&
					 (accessoryOffer.getPromotionId() != null) &&
					 (!accessoryOffer.getPromotionId().isEmpty()) ) {
					if ( (cartItemVO != null) &&
						 (cartItemVO.isGwpOrderItem()) &&
						 (cartItemVO.getRelatedPromotionList() != null) &&
						 (!cartItemVO.getRelatedPromotionList().isEmpty()) ) {
						for (RelatedImmediatePromotionVO promoVO : cartItemVO.getRelatedPromotionList()) {
							if ( (promoVO != null) &&
								 (promoVO.getId() != null) &&
								 (!promoVO.getId().isEmpty()) &&
								 (promoVO.getId().equalsIgnoreCase(accessoryOffer.getPromotionId())) ) {
								accessoryOfferSummaryList.add(accessoryOffer);
							}
						}
					}
				}
			}
		}
		
		return accessoryOfferSummaryList;
	}
	
	public List<SubscribedServiceIdentifierVO> getSubscribedServiceList() {
		ArrayList<SubscribedServiceIdentifierVO> subscribedServiceList = new ArrayList<SubscribedServiceIdentifierVO>();
		if(shoppingCartVO.getCartItemList() != null) {
			for(CartItemVO item: shoppingCartVO.getCartItemList()) {
				if(item.isSalesOrderItem() && item.getExistingServiceIdentifier() != null) {
					for(SubscribedServiceVO serv: item.getExistingServiceIdentifier()) {
						SubscribedServiceIdentifierVO servIdent = new SubscribedServiceIdentifierVO();
						servIdent.setServiceId(serv.getServiceId());
						servIdent.setServiceReferenceId(serv.getServiceReferenceId());
						subscribedServiceList.add(servIdent);
					}
				}
			}
		}
		return subscribedServiceList;
	}
	/**
	 * 
	 * @return
	 */
	public List<Product> getAssignedProducts(){
		List<Product> assignedProductsByServiceId = new ArrayList<Product>();
		if (opAssignedAndPendingProductResponseVO != null) {
			List<SubscribedServiceIdentifierVO> subscribedServiceIds = getSubscribedServiceList();
			GetProductsByCustomerIdAdapterResponse getProductInstance = opAssignedAndPendingProductResponseVO.getGetProductsByCustomerIdAdapterResponse();
			if (subscribedServiceIds != null && getProductInstance != null) {
				List<Product> assignedProducts = getProductInstance.getProducts();
				if (assignedProducts != null) {
					for (Product product : assignedProducts) {
						if (existingServiceId(getServiceId(product), subscribedServiceIds)) assignedProductsByServiceId.add(product);
					}
				}
					
			}
		}
		return assignedProductsByServiceId;
	}
	/**
	 * 
	 * @param serviceId
	 * @param subscribedServiceIds
	 * @return
	 */
	private boolean existingServiceId(String serviceId, List<SubscribedServiceIdentifierVO> subscribedServiceIds) {
		if (serviceId != null && subscribedServiceIds != null) {
			for (SubscribedServiceIdentifierVO serviceIdVO : subscribedServiceIds) {
				if (serviceId.equalsIgnoreCase(serviceIdVO.getServiceId())) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 
	 * @param product
	 * @return
	 */
	private String getServiceId(Product product) {
		List<Product> components = product.getProductComponents();
		if (components != null && components.size() > 0) {
			for (Product component : components) {
					if (component.getProductCharacteristics() != null && component.getProductCharacteristics().size() > 0) {
						if ("generalProductInfo".equalsIgnoreCase(component.getName())) {
						for (ProductCharacteristicValue charValue : component.getProductCharacteristics()) {
							if (charValue != null && "serviceId".equalsIgnoreCase(charValue.getName())) {
								return charValue.getValue();
							}
						}
					}
				}
			}
		}

		return null;
	}
	
	public boolean isLikeToLikeReContracting() {
		
		List<ProductComponentVO> cartProductComponents = getAllCartProductComponents();
		if(!cartProductComponents.isEmpty()) {
			return isComponentsMatch(cartProductComponents);
		}
		
		return false;
	}
	
	private List<ProductComponentVO> getAllCartProductComponents(){
		List<ProductComponentVO> cartProductComponents = new ArrayList<ProductComponentVO>();
		if(shoppingCartVO == null || shoppingCartVO.getCartItemList() == null) {
			return cartProductComponents;
		}
		
		for( CartItemVO cartItem : shoppingCartVO.getCartItemList() ) {
			if(cartItem.getProducts() == null) {
				continue;
			}
			HomePhoneProductTypeVO singProduct = cartItem.getProducts().getHomePhoneProduct();
			InternetProductTypeVO internetProduct = cartItem.getProducts().getInternetProduct();
			TelevisionProductTypeVO tvProduct = cartItem.getProducts().getTelevisionProduct();
			
			if(singProduct != null && singProduct.getProductComponents() != null) {
				cartProductComponents.addAll(singProduct.getProductComponents());
			}
			
			if(internetProduct != null && internetProduct.getProductComponents() != null) {
				cartProductComponents.addAll(internetProduct.getProductComponents());
			}
			
			if(tvProduct != null && tvProduct.getProductComponents() != null) {
				cartProductComponents.addAll(tvProduct.getProductComponents());
			}
			
		}
		
		return cartProductComponents;
	}
	
	private boolean isComponentsMatch(List<ProductComponentVO> cartProductComponents) {
		
		if(opAssignedAndPendingProductResponseVO == null ||  opAssignedAndPendingProductResponseVO.getGetProductsByCustomerIdAdapterResponse() == null) {
			return false;
		}

		if (opAssignedAndPendingProductResponseVO != null
				&& opAssignedAndPendingProductResponseVO.getGetProductsByCustomerIdAdapterResponse() != null) {
			List<Product> existingAssignedComponents = opAssignedAndPendingProductResponseVO
					.getGetProductsByCustomerIdAdapterResponse().getProducts();
			for (ProductComponentVO selectedProductComponent : cartProductComponents) {
				if (existingAssignedComponents != null) {
					Product productCompoent = findComponent(selectedProductComponent.getProductCatalogueId(),
							selectedProductComponent.getParentProductCatalogueId(), existingAssignedComponents);
					if (productCompoent == null) {
						return false;
					}
				}
			}
		}

		return true;
	}
	
	public ProductInstanceInfoRestVO findProductInstanceInfoRestVO(String productType) {
			
			if (productType == null ) {
				return null;
			}
			
			List<SubscribedProductInfoRestVO> productList = new ArrayList<SubscribedProductInfoRestVO>();
			
			if( opAssignedAndPendingProductResponseVO != null && opAssignedAndPendingProductResponseVO.getSubscribedProductList() != null ) {
				productList.addAll(opAssignedAndPendingProductResponseVO.getSubscribedProductList());
			}
	
			if( opAssignedAndPendingProductResponseVO != null && opAssignedAndPendingProductResponseVO.getPendingProductList() != null ) {
				productList.addAll(opAssignedAndPendingProductResponseVO.getPendingProductList());
			}
			
			if ( !productList.isEmpty()) {
				for (SubscribedProductInfoRestVO product : productList) {
					if (productType.equalsIgnoreCase(product.getProductTypeCd())) {
						if(product.getProductInstance() != null && !product.getProductInstance().isEmpty()) {
							return product.getProductInstance().get(0);
						}
					}
				}
			}
	
			return null;
	}

	private Product findComponent(String productCatId, String parentCatId, List<Product> products ) {
		if (products != null && parentCatId != null) {
			for (Product product : products) {
				if (productCatId.equalsIgnoreCase(product.getCatalogId())
						&& product.getParentCatalogId().equalsIgnoreCase(parentCatId)) {
					return product;
				} 
				if (product.getProductComponents() != null) {
						findComponent(productCatId, parentCatId, product.getProductComponents());
				}
			}
		}
		return null;
	}
	
	public CreateInvoiceAdapterResponse getCreateInvoiceAdapterResponse() {
		return createInvoiceAdapterResponse;
	}
	public void setCreateInvoiceAdapterResponse(CreateInvoiceAdapterResponse createInvoiceAdapterResponse) {
		this.createInvoiceAdapterResponse = createInvoiceAdapterResponse;
	}
	public void setGetDepositInfoResponse(GetDepositInfoResponse response) {
		this.getDepositInfoResponse = response;
		
	}
	public GetDepositInfoResponse getGetDepositInfoResponse() {
		return getDepositInfoResponse;
	}
	public GetCreditEligibilityAdapterResponse getGetCreditEligibilityAdapterResponse() {
		return getCreditEligibilityAdapterResponse;
	}
	public void setGetCreditEligibilityAdapterResponse(
			GetCreditEligibilityAdapterResponse getCreditEligibilityAdapterResponse) {
		this.getCreditEligibilityAdapterResponse = getCreditEligibilityAdapterResponse;
	}
	public GetCreditProfileByCustomerIdAdapterResponse getGetCreditProfileByCustomerIdAdapterResponse() {
		return getCreditProfileByCustomerIdAdapterResponse;
	}
	public void setGetCreditProfileByCustomerIdAdapterResponse(
			GetCreditProfileByCustomerIdAdapterResponse getCreditProfileByCustomerIdAdapterResponse) {
		this.getCreditProfileByCustomerIdAdapterResponse = getCreditProfileByCustomerIdAdapterResponse;
	}

	public String getCreditValueCode() {
		String creditValueCd;
		String functionName = "getCreditValueCode";
		if(getGetCreditProfileByCustomerIdAdapterResponse() !=null){
			GetCreditProfileByCustomerIdAdapterResponse creditProfileByCustIdResponseVO = getGetCreditProfileByCustomerIdAdapterResponse();
			if(creditProfileByCustIdResponseVO.getCreditProfile()!=null && creditProfileByCustIdResponseVO.getCreditProfile().getCreditWorthiness()!=null && !StringUtils.isBlank(creditProfileByCustIdResponseVO.getCreditProfile().getCreditWorthiness().getCreditValueCd())){
				creditValueCd = creditProfileByCustIdResponseVO.getCreditProfile().getCreditWorthiness().getCreditValueCd();
				logger.info(functionName, "CreditValueCode found in CreditProfileByCustId Response with value=" + creditValueCd);
				
				if(!EnterpriseWLNSalesServicesConstants.CREDIT_VALUE_ESTABLISHED_CUSTOMER.equalsIgnoreCase(creditValueCd)){
					logger.info(functionName, "CreditValueCd is: " + creditValueCd + ". Using GetFullCustomerInfoAdapterResponse ");
					
					GetFullCustomerInfoAdapterResponse fullCustomerAdapterResponse = getFullCustomerInfoAdapterResponse();
					
					if(fullCustomerAdapterResponse!=null && fullCustomerAdapterResponse.getFullCustomer()!=null && fullCustomerAdapterResponse.getFullCustomer().getCustomerMasterSourceId()!=null){
						if(!EnterpriseSalesServiceUtil.isWirelineBan(String.valueOf(fullCustomerAdapterResponse.getFullCustomer().getCustomerMasterSourceId()))){
							logger.info(functionName, "Customer does not have wireline accounts as per GetFullCustomerInfoAdapterResponse, setting creditValueCd as 'E'");
							creditValueCd = EnterpriseWLNSalesServicesConstants.CREDIT_VALUE_ESTABLISHED_CUSTOMER;
						}
					}
				}
			}else{
				creditValueCd = EnterpriseWLNSalesServicesConstants.NON_CREDIT_RESTRICTED_PROFILE;
				logger.info(functionName, "No creditValueCode was found in CreditProfileByCustId Response");
			}
		}else{
			logger.info(functionName, "No creditValueCode was found in CreditProfileByCustId Response or the call for GetCreditProfileByCustomerId failed, defaulting creditValueCode to 'E'");
			creditValueCd = EnterpriseWLNSalesServicesConstants.NON_CREDIT_RESTRICTED_PROFILE;
		}
		logger.exit(functionName);

		return creditValueCd;
	}
	/**
	 * 
	 * @return
	 */
	public OPShoppingCartDelegateResponseVO getOpShoppingCartDelegateResponseVO() {
		return opShoppingCartDelegateResponseVO;
	}
	/**
	 * 
	 * @param opShoppingCartDelegateResponseVO
	 */
	public void setOpShoppingCartDelegateResponseVO(OPShoppingCartDelegateResponseVO opShoppingCartDelegateResponseVO) {
		this.opShoppingCartDelegateResponseVO = opShoppingCartDelegateResponseVO;
	}
	public GetBookingRequirementResponse getBookingRequirementResponse() {
		return bookingRequirementResponse;
	}
	public void setBookingRequirementResponse(GetBookingRequirementResponse bookingRequirementResponse) {
		this.bookingRequirementResponse = bookingRequirementResponse;
	}
	
	public CheckProductFeasibilityAdapterResponse getFeasibilityResponseVO() {
		return feasibilityResponseVO;
	}
	public void setFeasibilityResponseVO(CheckProductFeasibilityAdapterResponse feasibilityResponseVO) {
		this.feasibilityResponseVO = feasibilityResponseVO;
	}
	
	
}
