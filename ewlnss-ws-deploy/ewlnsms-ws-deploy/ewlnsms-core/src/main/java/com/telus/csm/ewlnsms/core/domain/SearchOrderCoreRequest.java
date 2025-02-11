/**
 * 
 */
package com.telus.csm.ewlnsms.core.domain;

import java.util.Date;
import java.util.List;

import com.telus.csm.ewlnsc.domain.CoreRequestBase;

/**
 * @author x145592
 *
 */
public class SearchOrderCoreRequest extends CoreRequestBase {

	private static final long serialVersionUID = 1L;
	
	private String salesRepId;
	private List<String> outletIdList;
	private String orderStatus;
	private Date startDate;
	private Date endDate;
	
	public String getSalesRepId() {
		return salesRepId;
	}
	public void setSalesRepId(String salesRepId) {
		this.salesRepId = salesRepId;
	}
	public List<String> getOutletIdList() {
		return outletIdList;
	}
	public void setOutletIdList(List<String> outletIdList) {
		this.outletIdList = outletIdList;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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
	
	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("SearchPOCoreRequest [")
			.append("startDate=").append(startDate).append(", endDate=").append(endDate)
			.append(", salesRepId=").append(salesRepId)
			.append(", outletIdList=").append(outletIdList)
			.append(", orderStatus=").append(orderStatus)
			.append("]");
		
		return builder.toString();
	}
	
}
