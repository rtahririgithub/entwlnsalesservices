package com.telus.csm.ewlnsc.adapter.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.telus.csm.ewlnsc.adapter.IWirelineSalesEJBAdapter;
import com.telus.csm.ewlnsc.adapter.scis.domain.AddressVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO.ProductInstance;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO.ProductInstance.EquipmentList;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO.ProductInstance.SingleLineComponent;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.SubscriptionNumberList;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.SubscriptionNumberList.SubscriptionNumber;
import com.telus.csm.ewlnsc.adapter.util.VestaEJBAdapterUtil;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.UcssXStream;
import com.telus.ucss.wirelinesales.ejb.OrderManager;
import com.telus.ucss.wirelinesales.exception.WLNSalesApplicationException;
import com.telus.ucss.ssm.objectmodel.CustomerAccount;
import com.telus.ucss.wirelinesales.domain.EquipmentOrderItem;
import com.telus.ucss.wirelinesales.domain.ItemGroup;
import com.telus.ucss.wirelinesales.domain.Order;
import com.telus.ucss.wirelinesales.domain.Product;
import com.telus.ucss.wirelinesales.domain.ProductType;
import com.telus.ucss.wirelinesales.domain.Term;
import com.telus.ucss.wirelinesales.domain.UpdateOMSOrderRequest;
import com.telus.ucss.wirelinesales.domain.OrderItem;
import com.telus.ucss.wirelinesales.domain.OrderStatus;
import com.telus.csm.ewlnsc.adapter.scis.domain.SalesProductLineVO;

@Component
public class WirelineSalesEJBAdapter  implements IWirelineSalesEJBAdapter, ApplicationContextAware {
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(WirelineSalesEJBAdapter.class);
	
    /** The Constant OMSProducttoSalesProductMap. */
    private HashMap<String, String> OMSProducttoSalesProductMap;
    
    /** The Constant OMSHighSpeedPackCIDtoSalesProductMap. */
    private HashMap<String, String> OMSHighSpeedPackCIDtoSalesProductMap;
    
    /** The Constant salesProducttoProductLineMap. */
    private HashMap<String, String> salesProducttoProductLineMap;
    
    @Autowired
	OrderManager vestaEjb;


	@Override
	public ArrayList<SubscribedProductSummaryVO> getPendingOrderByChannelTransactionId(String channelTransactionId,
			long sessionId) throws Exception {
		
		String functionName = "*" + sessionId + "* " + "getPendingOrdersByChannelTransactionId";
		LoggerUtil.getLogger(this).enter(functionName);
		
		long startTime = System.currentTimeMillis();
		
		try{
			logger.enter(functionName + " channelTransactionId: " + channelTransactionId);
			Order order = vestaEjb.getOrdersByChannelTransactionId(channelTransactionId);
			ArrayList<SubscribedProductSummaryVO> subscribedProductSummaryList = null;
			if( order != null ) {
				subscribedProductSummaryList = new ArrayList<SubscribedProductSummaryVO>();
				populateSubscribedProductSummaryList(subscribedProductSummaryList, order);	
			}
			
			try {
				LoggerUtil.getLogger(this).info(functionName, new StringBuffer("getPendingOrdersByChannelTransactionId.response\r\n").append(UcssXStream.toString(order)).toString());
			} catch(Exception ex) {}
			
			return subscribedProductSummaryList;
		}catch(Exception e){
			
		} finally{
			long elapsed = System.currentTimeMillis() - startTime;
			LoggerUtil.getLogger(this).info(functionName, "+++ process time(ms): " + elapsed);
			
			LoggerUtil.getLogger(this).exit(functionName);
		}
		
		return null;
	}

	@Override
	public Map<String, ArrayList<SubscribedProductSummaryVO>> getPendingOrdersByCustomerId(long customerId,
			long sessionId) throws Exception {
		String methodName = "*" + sessionId + "* " + "getPendingOrdersByCustomerId";
		LoggerUtil.getLogger(this).enter(methodName);
		
		long startTime = System.currentTimeMillis();
		
		try {
			LoggerUtil.getLogger(this).info(methodName, "getPendingOrdersByCustomerId.request. customerId = " + customerId);
					
			Order[] orderList = vestaEjb.getPendingOrdersByCustomerId(customerId);
			
			try {
				LoggerUtil.getLogger(this).info(methodName, new StringBuffer("getPendingOrdersByCustomerId.response\r\n").append(UcssXStream.toString(orderList)).toString());
			} catch(Exception ex) {}
			
			if( orderList == null || orderList.length < 1 ) return null;
			
			Map<String, ArrayList<SubscribedProductSummaryVO>> subscribedProductSummaryListMap = new HashMap<String, ArrayList<SubscribedProductSummaryVO>>();
			
			for( Order order : orderList) {				
				String channelTransactionId = order.getChannelTransactionId();
			    ArrayList<SubscribedProductSummaryVO> subscribedProductSummaryList = new ArrayList<SubscribedProductSummaryVO>();
				subscribedProductSummaryListMap.put(channelTransactionId, subscribedProductSummaryList);				
				populateSubscribedProductSummaryList(subscribedProductSummaryList, order);
				
				
				
			}
			return subscribedProductSummaryListMap;
		} finally {
			long elapsed = System.currentTimeMillis() - startTime;
			LoggerUtil.getLogger(this).info(methodName, "+++ process time(ms): " + elapsed);
			
			LoggerUtil.getLogger(this).exit(methodName);
		}
		
	}
	
	@Override
	public boolean updateOMSOrderStatus(UpdateOMSOrderRequest updateOMSRequest) throws WLNSalesApplicationException {
		try {
			LoggerUtil.getLogger(this).info("updateOMSOrderStatus", new StringBuffer("updateOMSOrderStatus.request\r\n").append(UcssXStream.toString(updateOMSRequest)).toString());
		} catch(Exception ex) {}
		return vestaEjb.updateOMSOrderStatus(updateOMSRequest);
	}	

	@Override
	public Order getOrder(Long orderId) throws WLNSalesApplicationException {
		return vestaEjb.getOrder(orderId);
	}

	@Override
	public boolean updateOrderDepositInvoiceNumber(String orderId,  Double amount, String invoiceNumber) throws WLNSalesApplicationException {
		return vestaEjb.updateOrderForDeposit(orderId, amount, invoiceNumber);
	}
	@Override
	public boolean addNewVestaToSfdcStg(long orderId) throws WLNSalesApplicationException {
		return vestaEjb.addNewVestaToSfdcStg(orderId);
	}	
	private void populateSubscribedProductSummaryList(ArrayList<SubscribedProductSummaryVO> subscribedProductSummaryList, Order order) throws Exception {
		if( order == null ) return;
		
		Long orderId = order.getOrderId();
		CustomerAccount account = order.getCustomer();
		String servicePhoneNum = order.getNewServicePhoneNumber();
		//RTCA 1.5
		Long payChannelNumber = order.getCustomer().getDefaultPayChannelNumber();
		String accountNumber = order.getCustomer().getBillingAccountNumber();
		//skip product for empty service address
		if( account == null || VestaEJBAdapterUtil.isEmptyAddress(account.getCustomerServiceAddress()) ) {
			LoggerUtil.getLogger(this).warn("Ignore vesta pending order without service address. orderId = " + orderId, null);
			return;
		}
		AddressVO serviceAddress = VestaEJBAdapterUtil.translateVestaProductAddress(account.getCustomerServiceAddress());
		
		List<Product> prodList = order.getProducts();
		//RTCA
		boolean hasInvoice=false;
		if (order.getWlnOrder() !=null && order.getWlnOrder().getInvoiceNumber() !=null 
				&& order.getWlnOrder().getInvoiceNumber().trim().length() > 0) {
			hasInvoice = true;
		}
		if( prodList != null && prodList.size() > 0 ) {
			String productName = prodList.get(0).getProductName();
			for( Iterator<Product> j = prodList.iterator(); j.hasNext(); ) {
				SubscribedProductSummaryVO sps = populateSubscribedProduct(orderId, serviceAddress, servicePhoneNum, j.next(), payChannelNumber, accountNumber, productName, hasInvoice);
				if (sps != null) {
					subscribedProductSummaryList.add(sps);
				}
			}
		}
	}
	
	/**
	 * Populate subscribed product.
	 *
	 * @param orderId the order id
	 * @param serviceAddress the service address
	 * @param prod the prod
	 * @param hasDepositInvoice 
	 * @return the subscribed product summary
	 * @throws ServiceException the service exception
	 * 
	 * RTCA 1.5 add two more parameters: Long payChannelNumber, String accountNumber
	 */
	
	private SubscribedProductSummaryVO populateSubscribedProduct(Long orderId, AddressVO serviceAddress, String servicePhoneNum, Product prod, Long payChannelNumber, String accountNumber, String productName, boolean hasDepositInvoice) throws Exception {
		String methodName = "populateSubscribedProduct";
		
		ProductType prodType = prod.getProductType();
		String offerCatalogId = prod.getExternalOfferId();
		//validate product type
		if( prodType == null ) {
			StringBuffer sb = new StringBuffer("Invalid pending VESTA order product data for missing product type definition. orderId = ").append(orderId);
			try {
				sb.append("\r\n").append(UcssXStream.toString(prod)).append("\r\n");
			} catch (Exception ex) {}
			String errorMsg = sb.toString();
			//throw ExceptionHandler.createServiceException(this.getClass(), methodName, errorMsg, ERROR_CD_INVALID_DATA);
		}
		
		String omsProdType = prodType.toString();
		String salesProdType = OMSProducttoSalesProductMap.get(omsProdType);
			
		//validate product type
		if( salesProdType == null ) {
			StringBuffer sb = new StringBuffer("Invalid pending VESTA order product data for undefined product type definition. orderId = ").append(orderId).append(", prodType = ").append(omsProdType);
			try {
				sb.append("\r\n").append(UcssXStream.toString(prod)).append("\r\n");
			} catch (Exception ex) {}
			String errorMsg = sb.toString();
			//throw ExceptionHandler.createServiceException(this.getClass(), methodName, errorMsg, ERROR_CD_INVALID_DATA);
		}
		
		if(EnterpriseWLNSalesServicesConstants.HSIC.equals(omsProdType) ) {
			String hsCid = null;
			for( OrderItem orderItem : prod.getOrderItemList() ) {
        		if( orderItem.getItemGroupSet() != null && orderItem.containsItemGroup(ItemGroup.HSPACK) ) {
        			hsCid = orderItem.getExternalId().getComponentId();
        			break;
                }
        	}
			if( hsCid == null ) {
				return null;
			}
			salesProdType = OMSHighSpeedPackCIDtoSalesProductMap.get(hsCid);
			if( salesProdType == null ) {
				StringBuffer sb = new StringBuffer("Invalid pending VESTA order product data for undefined HSIC product type definition. orderId = ").append(orderId).append(", component CID = ").append(hsCid);
				try {
					sb.append("\r\n").append(UcssXStream.toString(prod)).append("\r\n");
				} catch (Exception eex) {}
				String errorMsg = sb.toString();
				//throw ExceptionHandler.createServiceException(this.getClass(), methodName, errorMsg, ERROR_CD_INVALID_DATA);
			}
		}
						
		SubscribedProductSummaryVO subscribedProduct = new SubscribedProductSummaryVO();
		subscribedProduct.setProductLine(SalesProductLineVO.fromValue(salesProducttoProductLineMap.get(salesProdType)));
		subscribedProduct.setProductType(salesProdType);	
		if( EnterpriseWLNSalesServicesConstants.SALES_PACK_HS_ZERO_NAME.equals(salesProdType) || 
			(EnterpriseWLNSalesServicesConstants.SALES_PRODUCT_TYPE_HOME.equals(salesProdType) && prod.isProvisioningOnly()) ) {
			ProductInstance productInstance = new ProductInstance();
			subscribedProduct.setProductInstance(productInstance);
			productInstance.setProductSuppressionInd(Boolean.TRUE);
			productInstance.setOfferCatalogId(offerCatalogId);
			
		}
		subscribedProduct.setProductInPendingOrderStatusInVestaInd(true); 
//		subscribedProduct.setServiceAddress(convertV4Address(serviceAddress));
		subscribedProduct.setServiceAddress(serviceAddress);
		
		//RTCA 1.5
		subscribedProduct.setOneTimePayChannelNumber(accountNumber);
		if(payChannelNumber!=null){
			subscribedProduct.setRecurringPayChannelNumber(payChannelNumber.toString());
		}
		
		
		Term term = prod.getTerm();
		if( term != null ) {
			String termCd = term.getTermCode();
			if( !StringUtils.isEmpty(termCd) && !"MTM".equalsIgnoreCase(termCd) ) {
				try {
					long termValue = Long.parseLong(termCd);
					if( termValue > 0 ) {
						ProductInstance productInstance = subscribedProduct.getProductInstance();
						if( productInstance == null ) {
							productInstance = new ProductInstance();
							subscribedProduct.setProductInstance(productInstance);
						}
						productInstance.setTermCd(termCd);	
						productInstance.setOfferCatalogId(offerCatalogId);
					}	
				} catch (Exception ex) {
					String errorMsg = "Invalid vesta product wrong term code. external product id = " + prod.getExternalProductId() + ", termCd = " + termCd;
					//throw ExceptionHandler.createServiceException(this.getClass(), methodName, errorMsg, ERROR_ID_INVALID_DATA);
				}
			}
		}
		
		if( !StringUtils.isEmpty(servicePhoneNum) &&
			( EnterpriseWLNSalesServicesConstants.SING.equals(omsProdType) || EnterpriseWLNSalesServicesConstants.HSIC.equals(omsProdType) || EnterpriseWLNSalesServicesConstants.TTV.equals(omsProdType) ) ) {
			SingleLineComponent singleLineComponent = new SingleLineComponent();
			SubscriptionNumberList subscriptionNumberList = new SubscriptionNumberList();
			SubscriptionNumber subscriptionNumber = new SubscriptionNumber();
			subscriptionNumber.setPhoneNumber(servicePhoneNum);
			subscriptionNumber.setCode(EnterpriseWLNSalesServicesConstants.SUBSCRIPTION_NUMBER_TYPE_CODE_PRIMARY);
			subscriptionNumberList.getSubscriptionNumber().clear();
			subscriptionNumberList.getSubscriptionNumber().add(subscriptionNumber);
			singleLineComponent.setSubscriptionNumberList(subscriptionNumberList);
			ProductInstance productInstance = subscribedProduct.getProductInstance();
			if( productInstance == null ) {
				productInstance = new ProductInstance();
				subscribedProduct.setProductInstance(productInstance);
			}
			productInstance.setSingleLineComponent(singleLineComponent);
			productInstance.setOfferCatalogId(offerCatalogId);
		}
		
		//RTCA 1.5 add equipment list to subscribedProduct.
		if (prod.getOrderItemList() != null) {
			ProductInstance productInstance = subscribedProduct.getProductInstance();
			if( productInstance == null ) {
				productInstance = new ProductInstance();
				productInstance.setOfferCatalogId(offerCatalogId);
				subscribedProduct.setProductInstance(productInstance);
			}
			List<EquipmentList.Equipment> equipList= new ArrayList<EquipmentList.Equipment>();
			if(productInstance.getEquipmentList()!=null){
				if(productInstance.getEquipmentList().getEquipment() !=null && !productInstance.getEquipmentList().getEquipment().isEmpty()){
					equipList = productInstance.getEquipmentList().getEquipment();
				}
			}else{
				EquipmentList equipmentList = new EquipmentList();
				equipmentList.getEquipment().addAll(equipList);
				productInstance.setEquipmentList(equipmentList);
				productInstance.setOfferCatalogId(offerCatalogId);
			}
			for (OrderItem orderItem : prod.getOrderItemList()) {
                   if (orderItem instanceof EquipmentOrderItem) {
                	    EquipmentList.Equipment equip = new EquipmentList.Equipment();
                	    equip.setEquipmentAcquisitionType(((EquipmentOrderItem) orderItem).getAcquisitionType());
                	    equipList.add(equip);
                   }
            }
        }
		ProductInstance productInstance = subscribedProduct.getProductInstance();
		if( productInstance == null ) {
			productInstance = new ProductInstance();
			productInstance.setOfferCatalogId(offerCatalogId);
		}
		productInstance.setProductName(productName);
		subscribedProduct.setServiceType(prod.getProductType().toString());
		if(hasDepositInvoice &&  prod.getDepositAmount()!=null){
			//conside deposit only when there is invoice.
			productInstance.setDepositAmmount(prod.getDepositAmount().longValue());
		}
		return subscribedProduct;
	}
	/**
	 * 
	 * @param vestaOrderId
	 * @param newComment
	 * @param commentType
	 * @throws WLNSalesApplicationException
	 */
	public void insertComment(String vestaOrderId, String newComment, String commentType) throws WLNSalesApplicationException {
		vestaEjb.insertOrderComment(vestaOrderId, newComment, commentType);
	}
	/**
	 * 
	 * @param vestaOrderId
	 * @param orderStatus
	 * @param oldStatus
	 * @throws WLNSalesApplicationException
	 */
	public boolean updateOrderStatus(String vestaOrderId, OrderStatus orderStatus, String oldStatus) throws WLNSalesApplicationException {
		return vestaEjb.updateOrderStatus(vestaOrderId, orderStatus, oldStatus);
	}
	@Override
	public String ping() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEndpointAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		//this.beanFactory = applicationContext;
		
	}

	@Override
	public Order getOrderByTransactionId(String channelTransactionId) throws WLNSalesApplicationException {
		return vestaEjb.getOrdersByChannelTransactionId(channelTransactionId);
	}

	

}
