package com.telus.csm.ewlnssproxy.rest.webservices;

import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductResponseProxyVO;
import com.telus.csm.ewlnss.rest.time.DateTime;
import com.telus.csm.ewlnssproxy.ccb.domain.ConsumerCreditEligibility;
import com.telus.csm.ewlnssproxy.cpdsb.domain.ReferencePDSData;
import com.telus.csm.ewlnssproxy.cpqb.domain.ProductQualification;
import com.telus.csm.ewlnssproxy.csab.domain.ServiceAddress;
import com.telus.csm.ewlnssproxy.rest.operation.GetAssignedAndPendingProductsConsolidatedRestOperation;
import com.telus.csm.ewlnssproxy.rest.operation.GetAssignedAndPendingProductsRestOperation;
import com.telus.csm.ewlnssproxy.rest.operation.GetAvailableBoltOnServiceRessOperation;
import com.telus.csm.ewlnssproxy.rest.operation.GetAvailableProductsOperation;
import com.telus.csm.ewlnssproxy.rest.operation.GetCustomerCreditEligibilityOperation;
import com.telus.csm.ewlnssproxy.rest.operation.GetCustomerCreditProfileOperation;
import com.telus.csm.ewlnssproxy.rest.operation.GetForborneStatusRestOperation;
import com.telus.csm.ewlnssproxy.rest.operation.GetReferencePDSMapRessOperation;
import com.telus.csm.ewlnssproxy.rest.operation.GetServiceAddressDetailsOperation;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v7.AuditInfo;


public class EWLNSSProxyServiceImpl implements IEWLNSSProxyService {
	private static Logger logger = Logger.getLogger(EWLNSSProxyServiceImpl.class);
	
	public List<com.telus.csm.ewlnssproxy.cpib.domain.RequiredService> getAvailableBoltOnService(String transactionId, String applicationId, String customerId, String roleId) {
		List<com.telus.csm.ewlnssproxy.cpib.domain.RequiredService> response = null;		
		GetAvailableBoltOnServiceRessOperation operation = new GetAvailableBoltOnServiceRessOperation();
				
		response = operation.execute(transactionId, applicationId, customerId, roleId);
		
		return response;
	}

	public ServiceAddress getServiceAddressDetails(String transactionId, String serviceAddressId, String provinceCd) {
		logger.debug("rest in get Service address details");
		GetServiceAddressDetailsOperation operation = new GetServiceAddressDetailsOperation();
		
		return operation.execute(transactionId, serviceAddressId, provinceCd);
	}
	
	public List<ProductQualification> getAvailableProducts(String transactionId, String applicationId,
			String customerId, String accountId, String addressId, String provinceCd, String city,
			String qualByServiceId, String correlationId, String salesRepId, String channelOutletId, String isRefreshInd ) throws Exception {
		logger.debug("rest call in get Product Qualification");
		GetAvailableProductsOperation operation = new GetAvailableProductsOperation();
		return operation.execute(transactionId, applicationId, customerId, accountId, addressId, provinceCd, city,
				qualByServiceId, correlationId, salesRepId, channelOutletId, isRefreshInd); // QC79739 added isRefreshInd
	}

	@Override
	public String getForborneStatus(String transactionId, String customerType, List<String> npaNxxList) {
		logger.debug("rest call in get forborne status");
		GetForborneStatusRestOperation operation = new GetForborneStatusRestOperation();
		
		return operation.execute(transactionId, customerType, npaNxxList);
	}

	@Override
	public ConsumerCreditEligibility getCustomerCreditEligibility(String transactionId, String customerId,
			String refreshcacheInd, String userId, String userTypeCode, String salesRepresentativeId,
			String channelOrganizationId, String outletId, String originatorApplicationId, String correlationId,
			DateTime timestamp){
		logger.debug("rest in getCreditEligibility");
		
		GetCustomerCreditEligibilityOperation operation = new GetCustomerCreditEligibilityOperation();
		
		AuditInfo auditInfo = new AuditInfo();
		auditInfo.setChannelOrganizationId(StringUtils.defaultString(channelOrganizationId, null));
		auditInfo.setCorrelationId(StringUtils.defaultString(correlationId, null));
		auditInfo.setOriginatorApplicationId(StringUtils.defaultString(originatorApplicationId, null));
		auditInfo.setOutletId(StringUtils.defaultString(outletId, null));
		auditInfo.setSalesRepresentativeId(StringUtils.defaultString(salesRepresentativeId, null));
		if (null != timestamp)
			auditInfo.setTimestamp(timestamp.toDate());
		auditInfo.setUserId(StringUtils.defaultString(userId, null));
		auditInfo.setUserTypeCode(StringUtils.defaultString(userTypeCode, null));
		
		return operation.execute(transactionId, customerId, BooleanUtils.toBoolean(refreshcacheInd), auditInfo );
	}

	@Override
	public ReferencePDSData getReferencePDSMap(String salesTransactionId, String applicationId, String entityName) {
		/** Step 1: Building GetReferencePDSDataserviceAdapterRequest **/
				
		GetReferencePDSMapRessOperation operation = new GetReferencePDSMapRessOperation();
		ReferencePDSData response = operation.execute(salesTransactionId, applicationId, entityName);

		return response;	
	}

	@Override
	public GetAssignedAndPendingProductResponseProxyVO getAssignedAndPendingProducts(String transactionId, String customerId,
			String billingAccountNumber, String originatorApplicationId, String brandId, String correlationId,
			String salesRepId, String salesRepAssociatedChannelOutletId) {
		logger.debug("rest call in getAssignedAndPendingProducts");
		GetAssignedAndPendingProductsRestOperation operation = new GetAssignedAndPendingProductsRestOperation();
		
		return operation.execute(transactionId, customerId, billingAccountNumber, originatorApplicationId, brandId, correlationId, 
				salesRepId, salesRepAssociatedChannelOutletId);
	}
	
	@Override
	public GetAssignedAndPendingProductResponseProxyVO getAssignedAndPendingProductsConsolidated(String transactionId, String customerId,String billingAccountNumber,
			String originatorApplicationId, String brandId, String correlationId, String isRefreshInd, String salesRepId, String salesRepAssociatedChannelOutletId) {
		logger.debug("rest call in getAssignedAndPendingProductsConsolidated");
		GetAssignedAndPendingProductsConsolidatedRestOperation operation = new GetAssignedAndPendingProductsConsolidatedRestOperation();
		
		return operation.execute(transactionId, customerId, billingAccountNumber, originatorApplicationId, brandId, correlationId, isRefreshInd, salesRepId, salesRepAssociatedChannelOutletId);
	}

	public String getCustomerCreditProfile(String transactionId, String customerId,
			String refreshcacheInd, String userId, String userTypeCode, String salesRepresentativeId,
			String channelOrganizationId, String outletId, String originatorApplicationId, String correlationId,
			DateTime timestamp) throws Exception {
		logger.debug("rest call in get customer credit profile");
		GetCustomerCreditProfileOperation operation = new GetCustomerCreditProfileOperation();

		com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v6.AuditInfo auditInfo = new com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v6.AuditInfo();
		auditInfo.setCorrelationId(correlationId);
		auditInfo.setOriginatorApplicationId(originatorApplicationId);
		auditInfo.setOutletId(outletId);
		auditInfo.setSalesRepresentativeId(salesRepresentativeId);
		auditInfo.setUserId(userId);
		auditInfo.setUserTypeCode(userTypeCode);
		if(timestamp != null) {
			auditInfo.setTimestamp(timestamp.toDate());
		} 
		
		return operation.execute(transactionId, customerId, BooleanUtils.toBoolean(refreshcacheInd), auditInfo);
	}
	
}
