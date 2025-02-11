package com.telus.csm.ewlnssproxy.rest.webservices;

import java.util.List;

import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductResponseProxyVO;
import com.telus.csm.ewlnss.rest.time.DateTime;
import com.telus.csm.ewlnssproxy.ccb.domain.ConsumerCreditEligibility;
import com.telus.csm.ewlnssproxy.cpdsb.domain.ReferencePDSData;
import com.telus.csm.ewlnssproxy.cpqb.domain.ProductQualification;
import com.telus.csm.ewlnssproxy.csab.domain.ServiceAddress;

public interface IEWLNSSProxyService {
	public List<com.telus.csm.ewlnssproxy.cpib.domain.RequiredService> getAvailableBoltOnService(String transactionId, String applicationId, String customerId,
			String roleId);

	public ServiceAddress getServiceAddressDetails(String transactionId,
			String serviceAddressId, String provinceCd);

	public List<ProductQualification> getAvailableProducts(String transactionId, String applicationId,
			String customerId, String accountId, String addressId, String provinceCd, String city,
			String qualByServiceId, String correlationId, String salesRepId, String channelOutletId, String isRefreshInd) throws Exception;

	public ConsumerCreditEligibility getCustomerCreditEligibility(String transactionId, String customerId,
			String refreshcacheInd, String userId, String userTypeCode, String salesRepresentativeId,
			String channelOrganizationId, String outletId, String originatorApplicationId, String correlationId,
			DateTime timestamp);
	
	public String getForborneStatus(String transactionId, String customerType, List<String> npaNxxList);

	public ReferencePDSData getReferencePDSMap(String salesTransactionId, String applicationId, String entityName);
	public GetAssignedAndPendingProductResponseProxyVO getAssignedAndPendingProducts(String transactionId, String customerId, String billingAccountNumber, String originatorApplicationId, String brandId,
			String correlationId, String salesRepId, String salesRepAssociatedChannelOutletId );
	public GetAssignedAndPendingProductResponseProxyVO getAssignedAndPendingProductsConsolidated(String transactionId, String customerId, String billingAccountNumber, String originatorApplicationId, String brandId,
			String correlationId, String isRefreshInd, String salesRepId, String salesRepAssociatedChannelOutletId);
	
	public String getCustomerCreditProfile(String transactionId, String customerId, 
			String refreshcacheInd, String userId, String userTypeCode, String salesRepresentativeId,
			String channelOrganizationId, String outletId, String originatorApplicationId, String correlationId, 
			DateTime timestamp) throws Exception;
}
