package com.telus.csm.ewlnsc.adapter.fwa.domain;

import java.util.Date;

import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
import com.telus.tmi.xmlschema.xsd.resource.resource.workforcemanagementordertypes_v3.Component;
import com.telus.tmi.xmlschema.xsd.resource.resource.workforcemanagementordertypes_v3.InputHeader;
import com.telus.tmi.xmlschema.xsd.resource.resource.workforcemanagementordertypes_v3.WorkOrder;

public class FieldWorkAppointmentAdapterRequest extends AdapterRequestBase {

	private static final long serialVersionUID = 1L;
    
    protected InputHeader inputHeader;
    protected WorkOrder workOrder;
    protected Date startDate;
    protected Date endDate;
    protected Boolean gradeAppointmentInd;
    protected Boolean fullSearchInd;
	
	@Override
	public String getCacheKey(){
		StringBuilder sbCacheKey = new StringBuilder();
		sbCacheKey.append("searchAvailableAppointmentList");
		sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
		sbCacheKey.append(EnterpriseWLNSalesServicesConstants.SALES_TXN_ID);
		sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
		sbCacheKey.append(getSalesTransactionId());
		sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);

		sbCacheKey.append("systemSrcId");
		sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
		sbCacheKey.append(inputHeader.getSystemSourceCd());
		sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
		sbCacheKey.append("userId");
		sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);		
		sbCacheKey.append(inputHeader.getUserId());
		sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
		sbCacheKey.append("requestedDate");
		sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);		
		sbCacheKey.append(inputHeader.getRequestDate());
		sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
		sbCacheKey.append("startDate");
		sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
		sbCacheKey.append(startDate);
		sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
		sbCacheKey.append("endDate");
		sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);		
		sbCacheKey.append(endDate);
		sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
		
		// workOrder elements
		
		// workOrder component list

		if (workOrder.getComponentList() != null && !workOrder.getComponentList().getComponent().isEmpty()) {
			sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
			sbCacheKey.append("componentList");

			for (final Component elem : workOrder.getComponentList().getComponent()) {
				sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
				sbCacheKey.append(elem.getProductCategoryCd());
			}			
		}

		return sbCacheKey.toString();
	}

	public InputHeader getInputHeader() {
		return inputHeader;
	}

	public void setInputHeader(InputHeader inputHeader) {
		this.inputHeader = inputHeader;
	}

	public WorkOrder getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getGradeAppointmentInd() {
		return gradeAppointmentInd;
	}

	public void setGradeAppointmentInd(Boolean gradeAppointmentInd) {
		this.gradeAppointmentInd = gradeAppointmentInd;
	}

	public Boolean getFullSearchInd() {
		return fullSearchInd;
	}

	public void setFullSearchInd(Boolean fullSearchInd) {
		this.fullSearchInd = fullSearchInd;
	}

	
	
}
