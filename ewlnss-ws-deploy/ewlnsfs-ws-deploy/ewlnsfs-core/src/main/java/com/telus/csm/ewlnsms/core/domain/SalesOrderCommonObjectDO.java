package com.telus.csm.ewlnsms.core.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.telus.csm.ewlnsc.adapter.ccm.domain.GetFullCustomerInfoAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListAdapterResponse;
import com.telus.csm.ewlnsc.adapter.oqs.domain.GetProductsByCustomerIdAdapterResponse;
import com.telus.csm.ewlnsc.domain.MessageDO;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.EnterpriseSalesOrderType;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;

/**
 * 
 * @author Jose.Mena
 *
 */
public class SalesOrderCommonObjectDO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private OperationHeader operationHeader;
	private EnterpriseSalesOrderType salesOrder;
	private boolean termsAndConditionsConsentIndicator;
	private boolean finalizeFailedSalesOrderIndicator;
	private GetProductsByCustomerIdAdapterResponse productsDO;
	private GetOfferListAdapterResponse offerDO;
	private GetFullCustomerInfoAdapterResponse customerInfoDO;
	private CheckProductFeasibilityAdapterResponse productFeasibilityDO;
	private Map<String,ProductAttributes> productAttributesMap;
	


	private List<MessageDO> messageDOList;

	public OperationHeader getOperationHeader() {
		return operationHeader;
	}

	public void setOperationHeader(OperationHeader operationHeader) {
		this.operationHeader = operationHeader;
	}

	public EnterpriseSalesOrderType getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(EnterpriseSalesOrderType salesOrder) {
		this.salesOrder = salesOrder;
	}

	public boolean isTermsAndConditionsConsentIndicator() {
		return termsAndConditionsConsentIndicator;
	}

	public void setTermsAndConditionsConsentIndicator(boolean termsAndConditionsConsentIndicator) {
		this.termsAndConditionsConsentIndicator = termsAndConditionsConsentIndicator;
	}

	public boolean isFinalizeFailedSalesOrderIndicator() {
		return finalizeFailedSalesOrderIndicator;
	}

	public void setFinalizeFailedSalesOrderIndicator(boolean finalizeFailedSalesOrderIndicator) {
		this.finalizeFailedSalesOrderIndicator = finalizeFailedSalesOrderIndicator;
	}

	public GetProductsByCustomerIdAdapterResponse getProductsDO() {
		return productsDO;
	}

	public void setProductsDO(GetProductsByCustomerIdAdapterResponse productsDO) {
		this.productsDO = productsDO;
	}

	public GetOfferListAdapterResponse getOfferDO() {
		return offerDO;
	}

	public void setOfferDO(GetOfferListAdapterResponse offerDO) {
		this.offerDO = offerDO;
	}

	public GetFullCustomerInfoAdapterResponse getCustomerInfoDO() {
		return customerInfoDO;
	}

	public void setCustomerInfoDO(GetFullCustomerInfoAdapterResponse customerInfoDO) {
		this.customerInfoDO = customerInfoDO;
	}

	public CheckProductFeasibilityAdapterResponse getProductFeasibilityDO() {
		return productFeasibilityDO;
	}

	public void setProductFeasibilityDO(CheckProductFeasibilityAdapterResponse productFeasibilityDO) {
		this.productFeasibilityDO = productFeasibilityDO;
	}

	public List<MessageDO> getMessageDOList() {
		return messageDOList;
	}

	public void setMessageDOList(List<MessageDO> messageDOList) {
		this.messageDOList = messageDOList;
	}
	
	public Map<String, ProductAttributes> getProductAttributesMap() {
		return productAttributesMap;
	}

	public void setProductAttributesMap(Map<String, ProductAttributes> productAttributesMap) {
		this.productAttributesMap = productAttributesMap;
	}
	
	
}
