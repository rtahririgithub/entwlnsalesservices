package com.telus.csm.ewlnsc.task;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.IConsumerCustomerMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.ccm.domain.GetFullCustomerInfoAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.GetFullCustomerInfoAdapterResponse;
import com.telus.csm.ewlnsc.domain.BillingAccountIdentificationVO;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumercustomermgmtsvcrequestresponse_v2.FullCustomer;
import com.telus.tmi.xmlschema.xsd.customer.customerbill.customer_billing_sub_domain_v2.BillingAccount;

public class GetFullCustomerInfoTask extends TaskBase {
	
	private static final LoggerUtil log = LoggerUtil.getLogger(GetFullCustomerInfoTask.class);

	private GetFullCustomerInfoAdapterRequest input;
	private GetFullCustomerInfoAdapterResponse result;
	private IConsumerCustomerMgmtSvcAdapter adapter;
	private Map<BillingAccountIdentificationVO, BillingAccount> billingAccountMap = Collections.synchronizedMap(new HashMap<BillingAccountIdentificationVO, BillingAccount>());
	
	public GetFullCustomerInfoTask(GetFullCustomerInfoAdapterRequest input, IConsumerCustomerMgmtSvcAdapter adapter) {
		this.adapter = adapter;
		this.input = input;
	}
	
	@Override
	protected void execute() {
		result = adapter.getFullCustomerInfo(input);
		mapBillingAccounts(result.getFullCustomer());
	}

	public GetFullCustomerInfoAdapterRequest getInput() {
		return input;
	}

	public void setInput(GetFullCustomerInfoAdapterRequest input) {
		this.input = input;
	}

	public GetFullCustomerInfoAdapterResponse getResult() {
		rethrowException();
		return result;
	}

	public void setResult(GetFullCustomerInfoAdapterResponse result) {
		this.result = result;
	}

	public IConsumerCustomerMgmtSvcAdapter getAdapter() {
		return adapter;
	}

	public void setAdapter(IConsumerCustomerMgmtSvcAdapter adapter) {
		this.adapter = adapter;
	}

	public Map<BillingAccountIdentificationVO, BillingAccount> getBillingAccountMap() {
		return billingAccountMap;
	}

	public void setBillingAccountMap(Map<BillingAccountIdentificationVO, BillingAccount> billingAccountMap) {
		this.billingAccountMap = billingAccountMap;
	}
	
	private void mapBillingAccounts(FullCustomer customer) {
		String functionName="mapBillingAccounts";
		// for each billing account add link to the parent customer object in the billingAccountToCustomerMap
		if (customer == null) {
			log.warn(functionName,"null customer response");
			
		} else {
			Long customerId = customer.getCustomerId();
			
			for (BillingAccount account : customer.getBillingAccountList()) {
				if (account == null) {
					log.warn(functionName,"null billing account for customer [" + customerId + "]");
				} else {
					String accountNumber = account.getBillingAccountNumber();
					String sourceId = Long.toString(account.getBillingMasterSourceId());
					
					if (StringUtils.isEmpty(accountNumber)) {
						log.warn(functionName,"ignoring billing account with empty account number for customer [" + customerId + "]");
						continue;
					}
					
					if (StringUtils.isEmpty(sourceId)) {
						log.warn(functionName,"ignoring billing account with empty master source ID for customer [" + customerId + "]");
						continue;
					}
					
					BillingAccountIdentificationVO billingAccountKey = new BillingAccountIdentificationVO(sourceId, accountNumber);
					
					if (billingAccountMap.containsKey(billingAccountKey)) {
						log.debug(functionName,"already had mapped billing account " + billingAccountKey + " for customer [" + customerId + "]");
					} else {
						log.debug(functionName,"mapping new billing account " + billingAccountKey + " for customer [" + customerId + "]");
						billingAccountMap.put(billingAccountKey, account);
					}
				}
			}
		}
	}

}
