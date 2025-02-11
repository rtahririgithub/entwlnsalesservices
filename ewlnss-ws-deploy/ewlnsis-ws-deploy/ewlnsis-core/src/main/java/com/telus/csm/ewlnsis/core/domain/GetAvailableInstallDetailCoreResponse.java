package com.telus.csm.ewlnsis.core.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.telus.csm.ess.common.domain.CoreResponseBaseVO;
import com.telus.csm.ewlnsc.domain.AvailableProductAppointmentVO;
import com.telus.csm.ewlnsc.domain.ProductServiceInstallRequirementVO;
import com.telus.tmi.xmlschema.xsd.resource.resource.workforcemanagementordertypes_v3.AvailableAppointment;
import com.telus.tmi.xmlschema.xsd.resource.resource.workforcemanagementordertypes_v3.WorkOrder;

public class GetAvailableInstallDetailCoreResponse extends CoreResponseBaseVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<AvailableProductAppointmentVO> availableProductAppointments;
	private List<ProductServiceInstallRequirementVO> productServiceInstallRequirement;
	
	/**
	 * Additional fields to accommodate ESS response
	 */
	private Map<String,Boolean> infeasibleProductsMap;
	private List<String> serviceTypeSWList;
	private List<List<String>> orderedProductCombination;
	
	// ATLAS: start for new service FieldWorkAppointment service
    private WorkOrder workOrder;
    private List<AvailableAppointment> availableAppointmentList;
    
	public WorkOrder getWorkOrder() {
		return workOrder;
	}
	
	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}
	
	public List<AvailableAppointment> getAvailableAppointmentList() {
		return availableAppointmentList;
	}
	
	public void setAvailableAppointmentList(List<AvailableAppointment> availableAppointmentList) {
		this.availableAppointmentList = availableAppointmentList;
	}
	// ATLAS: end for new service FieldWorkAppointment service
		
	
	public Map<String, Boolean> getInfeasibleProductsMap() {
		return infeasibleProductsMap;
	}

	public void setInfeasibleProductsMap(Map<String, Boolean> infeasibleProductsMap) {
		this.infeasibleProductsMap = infeasibleProductsMap;
	}

	public List<String> getServiceTypeSWList() {
		return serviceTypeSWList;
	}

	public void setServiceTypeSWList(List<String> serviceTypeSWList) {
		this.serviceTypeSWList = serviceTypeSWList;
	}

	private Boolean installationRequiredIndicator;


	public List<AvailableProductAppointmentVO> getAvailableProductAppointments() {
		if(availableProductAppointments == null){
			this.availableProductAppointments = new ArrayList<AvailableProductAppointmentVO>();
		}
		return availableProductAppointments;
	}

	public void setAvailableProductAppointments(List<AvailableProductAppointmentVO> availableProductAppointments) {
		this.availableProductAppointments = availableProductAppointments;
	}

	public List<ProductServiceInstallRequirementVO> getProductServiceInstallRequirement() {
		return productServiceInstallRequirement;
	}

	public void setProductServiceInstallRequirement(
			List<ProductServiceInstallRequirementVO> productServiceInstallRequirement) {
		this.productServiceInstallRequirement = productServiceInstallRequirement;
	}

	public Boolean getInstallationRequiredIndicator() {
		return installationRequiredIndicator;
	}

	public void setInstallationRequiredIndicator(Boolean installationRequiredIndicator) {
		this.installationRequiredIndicator = installationRequiredIndicator;
	}
	
	public void addAvailableProductAppointmentVO(AvailableProductAppointmentVO productAppointmentVO){
		this.getAvailableProductAppointments().add(productAppointmentVO);
	}

	public List<List<String>> getOrderedProductCombination() {
		return orderedProductCombination;
	}

	public void setOrderedProductCombination(List<List<String>> orderedProductCombination) {
		this.orderedProductCombination = orderedProductCombination;
	}
}
