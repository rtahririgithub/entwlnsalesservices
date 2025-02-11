package com.telus.csm.ewlnsc.domain;

import java.util.Date;
import java.util.List;

import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceAddress;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineProductSummary; 

public class GetAvailableInstallDateCoreRequest extends CoreRequestBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean combinationInd ;
	private List<WirelineProductSummary> orderProductList;
	private List<WirelineProductSummary> subscriberProductList;
	private ServiceAddress serviceAddress;
	private Date startDate;
	private Date endDate;
	private boolean outboundChannel;
	private boolean inboundChannel;
	
	
	 
	public boolean isOutboundChannel() {
		return outboundChannel;
	}
	public void setOutboundChannel(boolean outboundChannel) {
		this.outboundChannel = outboundChannel;
	}
	public boolean isInboundChannel() {
		return inboundChannel;
	}
	public void setInboundChannel(boolean inboundChannel) {
		this.inboundChannel = inboundChannel;
	}
	public Boolean getCombinationInd() {
		return combinationInd;
	}
	public void setCombinationInd(Boolean combinationInd) {
		this.combinationInd = combinationInd;
	}
	public List<WirelineProductSummary> getOrderProductList() {
		return orderProductList;
	}
	public void setOrderProductList(List<WirelineProductSummary> orderProductList) {
		this.orderProductList = orderProductList;
	}
	public List<WirelineProductSummary> getSubscriberProductList() {
		return subscriberProductList;
	}
	public void setSubscriberProductList(
			List<WirelineProductSummary> subscriberProductList) {
		this.subscriberProductList = subscriberProductList;
	}
	public ServiceAddress getServiceAddress() {
		return serviceAddress;
	}
	public void setServiceAddress(ServiceAddress serviceAddress) {
		this.serviceAddress = serviceAddress;
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
	
	
	
}
