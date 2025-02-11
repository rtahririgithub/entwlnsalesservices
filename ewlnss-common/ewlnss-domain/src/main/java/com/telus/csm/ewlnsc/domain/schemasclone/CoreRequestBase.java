package com.telus.csm.ewlnsc.domain.schemasclone;

import java.io.Serializable;
import java.util.List;

import com.telus.csm.ewlnsc.domain.schemasclone.OperationHeader.SystemIntegrationParameterList;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;

/**
 * Base class for all Core Request classes.
 * 
 * @author x145592
 *
 */
public abstract class CoreRequestBase implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private OperationHeader operationHeader;
	
	public OperationHeader getOperationHeader() {
		return operationHeader;
	}
	public void setOperationHeader(OperationHeader operationHeader) {
		this.operationHeader = operationHeader;
	}
	public boolean isJoinSalesOfferRequest() {
		if (getOperationHeader() == null) {
			return false;
		}
		else if  (getOperationHeader().getSystemIntegrationParameterList() == null) {
			return false;
		} else {

		List<SystemIntegrationParameterList> systemIntegrationList = getOperationHeader().getSystemIntegrationParameterList();
		if (systemIntegrationList != null) {
			for (SystemIntegrationParameterList systemIntegrationParameterList : systemIntegrationList) {
				if (EnterpriseWLNSalesServicesConstants.SYSTEM_INTEGRATION_PARAMETER_JOIN_SALES_OFFER.equalsIgnoreCase(systemIntegrationParameterList.getParameterName())) {
					if (EnterpriseWLNSalesServicesConstants.SYSTEM_INTEGRATION_PARAMETER_VALUE_TRUE.equalsIgnoreCase(systemIntegrationParameterList.getParameterValue())){
						return true;
					}
				} 
			}
		}
		}
		return false;
	}
}
