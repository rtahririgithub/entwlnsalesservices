package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;

public class BookingServiceVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date startDate;
	private Date endDate;
	private boolean combinationInd;
	private CheckProductFeasibilityAdapterRequest feasibilityRequest;
	private CheckProductFeasibilityAdapterResponse feasibilityAdapterResponse;
	private Map<String,String> nguiInstallLagTime;
	private ServiceAddressResponseVO serviceAddressResponseVO;
	private boolean outboundChannel;
	private boolean inboundChannel;
	private boolean isDisconnectCartItem;
	private List<SubscribedProductInfoRestVO> existingProductList;
	private boolean isChangeOfTechnology;
	
	public List<SubscribedProductInfoRestVO> getExistingProductList() {
		return existingProductList;
	}
	public void setExistingProductList(List<SubscribedProductInfoRestVO> existingProductList) {
		this.existingProductList = existingProductList;
	}
	public boolean isChangeOfTechnology() {
		return isChangeOfTechnology;
	}
	public void setChangeOfTechnology(boolean isChangeOfTechnology) {
		this.isChangeOfTechnology = isChangeOfTechnology;
	}
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
	public Map<String, String> getNguiInstallLagTime() {
		return nguiInstallLagTime;
	}
	public void setNguiInstallLagTime(Map<String, String> nguiInstallLagTime) {
		this.nguiInstallLagTime = nguiInstallLagTime;
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
	public boolean isCombinationInd() {
		return combinationInd;
	}
	public void setCombinationInd(boolean combinationInd) {
		this.combinationInd = combinationInd;
	}
	public CheckProductFeasibilityAdapterRequest getFeasibilityRequest() {
		return feasibilityRequest;
	}
	public void setFeasibilityRequest(CheckProductFeasibilityAdapterRequest feasibilityRequest) {
		this.feasibilityRequest = feasibilityRequest;
	}
	public CheckProductFeasibilityAdapterResponse getFeasibilityAdapterResponse() {
		return feasibilityAdapterResponse;
	}
	public void setFeasibilityAdapterResponse(CheckProductFeasibilityAdapterResponse feasibilityAdapterResponse) {
		this.feasibilityAdapterResponse = feasibilityAdapterResponse;
	}
	public ServiceAddressResponseVO getServiceAddressResponseVO() {
		return serviceAddressResponseVO;
	}
	public void setServiceAddressResponseVO(ServiceAddressResponseVO serviceAddressResponseVO) {
		this.serviceAddressResponseVO = serviceAddressResponseVO;
	}
	public boolean isDisconnectCartItem() {
		return isDisconnectCartItem;
	}
	public void setDisconnectCartItem(boolean isDisconnectCartItem) {
		this.isDisconnectCartItem = isDisconnectCartItem;
	}
}