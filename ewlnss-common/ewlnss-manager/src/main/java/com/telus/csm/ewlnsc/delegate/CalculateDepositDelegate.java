package com.telus.csm.ewlnsc.delegate;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.telus.csm.ewlnsc.adapter.IOrderDepositCalculatorProxySvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.CalculateDepositRequest;
import com.telus.csm.ewlnsc.adapter.domain.CalculateDepositResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.AccountProfileRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.EquipmentInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProductInstanceInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.domain.CalculateDepositRequestVO;
import com.telus.csm.ewlnsc.domain.CalculateDepositResponseVO;
import com.telus.csm.ewlnsc.domain.OrderProductVO;
import com.telus.csm.ewlnsc.domain.ProdductDepositVO;
import com.telus.csm.ewlnsc.domain.SubscribedProductSummaryVO;
import com.telus.csm.ewlnsc.domain.SubscribedProductSummaryVO.ProductInstance.EquipmentList.Equipment;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.tmi.xmlschema.srv.cmo.ordermgmt.orderdepositcalculatorproxyservicerequestresponse_v1.CalculateDeposit;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditdeposittypes_v1.AssignedOrderProductList;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditdeposittypes_v1.AssignedProduct;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditdeposittypes_v1.BaseProduct;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditdeposittypes_v1.CurrentOrderProductList;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditdeposittypes_v1.CurrentProduct;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditdeposittypes_v1.DepositCalulationResult;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditdeposittypes_v1.OrderData;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditdeposittypes_v1.PayChannelNumberList;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditdeposittypes_v1.PendingOrderProductList;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditdeposittypes_v1.PendingProduct;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditdeposittypes_v1.ProductDepositResult;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditdeposittypes_v1.ProductDepositResultList;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v7.AuditInfo;


@Component
@Scope(SCOPE_PROTOTYPE)
public class CalculateDepositDelegate {
    
	IOrderDepositCalculatorProxySvcAdapter orderDepositCalculatorProxySvcAdapter = AdapterFactory.getAdapter(IOrderDepositCalculatorProxySvcAdapter.class);
	
	public final static String ACQUISITION_TYPE_FOR_PURCHASE = "SA";
	public final static String ACQUISITION_TYPE_FOR_RENT = "RE";
	public final static String ACQUISITION_TYPE_FOR_OWNED = "POE";
	
	public static CalculateDepositDelegate getInstance(){
		return new CalculateDepositDelegate();
	}
	public  CalculateDepositResponseVO execute(final CalculateDepositRequestVO calculateDepositRequestVO) {
		CalculateDepositRequest createCustomerRequestDO = transform(calculateDepositRequestVO);
		CalculateDepositResponse calculateDepositResponse =  null;
		if(EnterpriseWLNSalesServicesConstants.DEPOSIT_CALCULATION_TYPE_ESTIMATED.equals(calculateDepositRequestVO.getCalulationTypeCode())) {
			calculateDepositResponse = orderDepositCalculatorProxySvcAdapter.calculateDeposit(createCustomerRequestDO );
		}else {
			//TODO::Call OP adapter.
		}
		
		return transform(calculateDepositResponse);
	}

	private CalculateDepositRequest transform(CalculateDepositRequestVO calculateDepositRequestVO) {
		CalculateDepositRequest calculateDepositRequest = new CalculateDepositRequest(buildCalculateDepositRequest(calculateDepositRequestVO));
		return calculateDepositRequest;
	}

	private CalculateDeposit buildCalculateDepositRequest(CalculateDepositRequestVO calculateDepositRequestVO) {
		CalculateDeposit calculateDeposit = new CalculateDeposit();
		calculateDeposit.setOrderData(buildOrderData(calculateDepositRequestVO));
		calculateDeposit.setCalculationTypeCd(calculateDepositRequestVO.getCalulationTypeCode());
		calculateDeposit.setApplicationID(calculateDepositRequestVO.getApplicationId());
		AuditInfo auditInfo = new AuditInfo();
		auditInfo.setUserId(calculateDepositRequestVO.getUserId());
		auditInfo.setOriginatorApplicationId(calculateDepositRequestVO.getApplicationId());
		calculateDeposit.setAuditInfo(auditInfo);
		calculateDeposit.setOrderData(buildOrderData(calculateDepositRequestVO));
		return calculateDeposit;
	}

	private OrderData buildOrderData(CalculateDepositRequestVO calculateDepositRequestVO) {
		OrderData orderData = new OrderData();
		orderData.setAssignedOrderProductList(buildAssignedProductList(calculateDepositRequestVO));
		orderData.setCurrentOrderProductList(buildCurrentOrderProductList(calculateDepositRequestVO));
		orderData.setPendingOrderProductList(buildPendingProductList(calculateDepositRequestVO));
		orderData.setOrderID(calculateDepositRequestVO.getOrderId());
		orderData.setOrderIdSourceSystemCd(calculateDepositRequestVO.getSourceSystemId());
		orderData.setCustomerID(calculateDepositRequestVO.getCustomerId());
		return orderData;
	}
	/**
	 * 
	 * @param calculateDepositRequestVO
	 * @return
	 */
	private PendingOrderProductList buildPendingProductList(CalculateDepositRequestVO calculateDepositRequestVO) {
		List<AccountProfileRestVO> subscribedProductsSummary = calculateDepositRequestVO.getCustomerProducts();
				
		PendingOrderProductList pendingOrderProductList = new PendingOrderProductList();
		List<PendingProduct>  pList = new ArrayList<PendingProduct>();
		int cnt=0;
		if(subscribedProductsSummary!=null && !subscribedProductsSummary.isEmpty()){
			for (AccountProfileRestVO accountProfile : subscribedProductsSummary) {
			  if (accountProfile.getSubscribedProductList() != null ) {	
				for(SubscribedProductInfoRestVO sps: accountProfile.getSubscribedProductList()){	
					cnt++;
					if(sps.getProductInstance()!=null){									
						ProductInstanceInfoRestVO prod = sps.getProductInstance().get(0);
						if(prod.getIncludedForDepositCalcInd()!=null && prod.getIncludedForDepositCalcInd()) {
							BaseProduct product = null;
							if( prod.getProductInstanceId() !=null 
								&& (sps.getProductInPendingOrderStatusInd() || sps.getProductInPendingOrderStatusInd())) {
											product = new PendingProduct();
											if (prod.getProductInstanceId() !=null ) {
												product.setAssignedProductIDSourceSystemCd("OMS");
												product.setAssignedProductID(prod.getProductInstanceId());
											} else {			
												product.setAssignedProductIDSourceSystemCd("VESTA");
												//per conversion with Ada on Apr 14th, this should be the same as current order
												product.setAssignedProductID("WSS1"+cnt);
											}
											
																		
										product.setProductName(prod.getProductName());	
										product.setPurchasedEquipmentCnt(getEquipmentCount(prod, ACQUISITION_TYPE_FOR_PURCHASE));	
										product.setRentedEquipmentCnt(getEquipmentCount(prod, ACQUISITION_TYPE_FOR_RENT));	
										product.setOfferNameCd(null);								
										product.setPayChannelNumberList(getExistingPayChannelNumberList(sps));								
										product.setPreviouslyAssessedDepositAmt(null);
										product.setServiceTypeCd(prod.getProductName());
										pList.add((PendingProduct)product);
								}
							}
						}
					}
			  } 
			 }
			}
		pendingOrderProductList.setPendingProduct(pList);		
		return pendingOrderProductList;
	}
	/**
	 * 
	 * @param calculateDepositRequestVO
	 * @return
	 */
	private CurrentOrderProductList buildCurrentOrderProductList(CalculateDepositRequestVO calculateDepositRequestVO) {
		List<OrderProductVO> orderedProductList = calculateDepositRequestVO.getOrderedProductsList();
		CurrentOrderProductList currentOrderProductList = new CurrentOrderProductList();
		List<CurrentProduct> currentProductList = new ArrayList<CurrentProduct>();
		currentOrderProductList.setCurrentProduct(currentProductList);
		for (OrderProductVO orderedProduct : orderedProductList) {
			CurrentProduct currentProduct = new CurrentProduct();
			currentProduct.setAssignedProductID(orderedProduct.getAssignedProductID());
			currentProduct.setAssignedProductIDSourceSystemCd(orderedProduct.getAssignedProductIDSourceSystemCd());
			currentProduct.setEstimatedUsageChargeAmt(orderedProduct.getEstimatedUsageChargeAmt());
			currentProduct.setForborneInd(orderedProduct.isForborneInd());
			currentProduct.setLossRentalEquipmentChargeAmt(orderedProduct.getLossRentalEquipmentChargeAmt());
			currentProduct.setOfferNameCd(orderedProduct.getOfferNameCd());
			PayChannelNumberList payChannelNumberList = new PayChannelNumberList();
			payChannelNumberList.setPayChannelNum(orderedProduct.getPayChannelNumberList());
			currentProduct.setPayChannelNumberList(payChannelNumberList);
			currentProduct.setPreviouslyAssessedDepositAmt(orderedProduct.getPreviouslyAssessedDepositAmt());
			currentProduct.setProductName(orderedProduct.getProductName());
			currentProduct.setProductOrderCanceledInd(Boolean.valueOf(orderedProduct.isProductOrderCanceledInd()));
			currentProduct.setPurchasedEquipmentCnt(orderedProduct.getPurchasedEquipmentCnt());
			currentProduct.setServiceTypeCd(orderedProduct.getServiceTypeCd());
			currentProduct.setTotalRecurringChargeAmt(orderedProduct.getTotalRecurringChargeAmt());
			currentProduct.setPurchasedEquipmentCnt(orderedProduct.getPurchasedEquipmentCnt());
			currentProduct.setRentedEquipmentCnt(orderedProduct.getRentedEquipmentCnt());
			currentProductList.add(currentProduct);
		}
		return currentOrderProductList;
	}
	/**
	 * 
	 * @param calculateDepositRequestVO
	 * @return
	 */
	private AssignedOrderProductList buildAssignedProductList(CalculateDepositRequestVO calculateDepositRequestVO) {
		List<AccountProfileRestVO> subscribedProductsSummary = calculateDepositRequestVO.getCustomerProducts();
		AssignedOrderProductList assignedOrderProductList = new AssignedOrderProductList();
		List<AssignedProduct> aList = new ArrayList<AssignedProduct>();
		int cnt = 0;
		if (subscribedProductsSummary != null && !subscribedProductsSummary.isEmpty()) {
			for (AccountProfileRestVO accountProfile : subscribedProductsSummary) {
				if (accountProfile.getSubscribedProductList() != null) {
					for (SubscribedProductInfoRestVO sps : accountProfile.getSubscribedProductList()) {
					cnt++;
					if (sps.getProductInstance() != null) {
						ProductInstanceInfoRestVO prod = sps.getProductInstance().get(0);
						if (prod.getIncludedForDepositCalcInd() != null && prod.getIncludedForDepositCalcInd()) {
							AssignedProduct product = null;
							if (prod.getProductInstanceId() != null && (!sps.getProductInPendingOrderStatusInd()
									&& !sps.getProductInPendingOrderStatusInd())) {
								product = new AssignedProduct();
								product.setAssignedProductIDSourceSystemCd("OMS");
								product.setAssignedProductID(prod.getProductInstanceId());
								product.setProductName(prod.getProductName());
								product.setPurchasedEquipmentCnt(
										getEquipmentCount(prod, ACQUISITION_TYPE_FOR_PURCHASE));
								product.setRentedEquipmentCnt(getEquipmentCount(prod, ACQUISITION_TYPE_FOR_RENT));
								product.setOfferNameCd(null);
								product.setPayChannelNumberList(getExistingPayChannelNumberList(sps));
								product.setPreviouslyAssessedDepositAmt(null);
								product.setServiceTypeCd(prod.getProductName());
								aList.add((AssignedProduct) product);
								// updateAlignProductPCN(sps); }
							}
						}

					}
				}
			}
			}
		}
		assignedOrderProductList.setAssignedProduct(aList);
		return assignedOrderProductList;
	}
	/**
	 * 
	 * @param calculateDepositResponse
	 * @return
	 */
	private CalculateDepositResponseVO transform(CalculateDepositResponse calculateDepositResponse) {
		CalculateDepositResponseVO calculateDepositResponseVO = new CalculateDepositResponseVO();
		DepositCalulationResult depositCalculation = calculateDepositResponse.getDepositCalulationResult();
		calculateDepositResponseVO.setTotalDepositAmt(depositCalculation.getTotalDepositAmt());
		calculateDepositResponseVO.setDepositAdjustmentAmt(depositCalculation.getDepositAdjustmentAmt());
		calculateDepositResponseVO.setCalculationResultMessageCd(depositCalculation.getCalculationResultMessageCd());
		calculateDepositResponseVO.setCalculationResultReasonCd(depositCalculation.getCalculationResultReasonCd());
		calculateDepositResponseVO.setDepositOnHandAmt(depositCalculation.getDepositOnHandAmt());
		calculateDepositResponseVO.setProductDepositList(buildProductDepositList(depositCalculation.getProductDepositResultList()));
		
		return calculateDepositResponseVO;
	}
	/**
	 * 
	 * @param productDepositResultList
	 * @return
	 */
	private List<ProdductDepositVO> buildProductDepositList(ProductDepositResultList productDepositResultList) {
		List<ProdductDepositVO> productDepistList = new ArrayList<ProdductDepositVO>();
		
		if(productDepositResultList != null) {
			for(ProductDepositResult productDeposit : productDepositResultList.getProductDepositResult()) {
				ProdductDepositVO prodductDepositVO = new ProdductDepositVO();
				prodductDepositVO.setAssessedDepositAmt(productDeposit.getAssessedDepositAmt());
				prodductDepositVO.setAssignedProductID(productDeposit.getAssignedProductID());
				prodductDepositVO.setMonthlyChargeAmt(productDeposit.getMonthlyChargeAmt());
				prodductDepositVO.setProductNameCd(productDeposit.getProductNameCd());
				prodductDepositVO.setServiceTypeCd(productDeposit.getServiceTypeCd());
				productDepistList.add(prodductDepositVO);
			}
		}
		return productDepistList;
	}

	/**
	 * 
	 * @param prod
	 * @param acquisitionType
	 * @return
	 */
	private Integer getEquipmentCount( ProductInstanceInfoRestVO prod, String acquisitionType){
		int count = 0;
		if(prod.getEquipmentList()!=null && prod.getEquipmentList()!= null && !prod.getEquipmentList().isEmpty()){
			for(EquipmentInfoRestVO equipment:prod.getEquipmentList()){
				if(acquisitionType!=null && acquisitionType.equalsIgnoreCase(equipment.getEquipmentAcquisitionTypeCd())){
					count++;
				}
			}
		}		
		return new Integer(count);		
	}
	/**
	 * 
	 * @param sps
	 * @return
	 */
	private PayChannelNumberList getExistingPayChannelNumberList(SubscribedProductInfoRestVO sps){
		PayChannelNumberList list = new PayChannelNumberList();
		Set<Long> payChannelNumSet = new HashSet<Long>();
		//Filter out UNKNOWN_ACCOUNT_NUMBER which is defined in SalesPreSaleService: "99999".
		if(sps.getOneTimePayChannelNum()!=null && !sps.getOneTimePayChannelNum().isEmpty() 
				//&& !sps.getOneTimePayChannelNumber().equals(Constants.UNKNOWN_ACCOUNT_NUMBER)
				){			
			payChannelNumSet.add(new Long(sps.getOneTimePayChannelNum())); //TODO: is Integer big enough?			
		}
		if(sps.getRecurringPayChannelNum()!=null && !sps.getRecurringPayChannelNum().isEmpty() 
				//&& !sps.getOneTimePayChannelNumber().equals(Constants.UNKNOWN_ACCOUNT_NUMBER)
				){					
			payChannelNumSet.add(new Long(sps.getRecurringPayChannelNum()));		
		}
		if(payChannelNumSet.isEmpty()){
			payChannelNumSet.add(null);
		}
		
		List<Long> payChannelNumList = new ArrayList<Long>();
		payChannelNumList.addAll(payChannelNumSet);		
		list.setPayChannelNum(payChannelNumList);		
		return list;	
	}
}
