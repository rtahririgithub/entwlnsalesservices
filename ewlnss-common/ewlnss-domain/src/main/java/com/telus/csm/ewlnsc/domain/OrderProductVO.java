package com.telus.csm.ewlnsc.domain;

import java.math.BigDecimal;
import java.util.List;

public class OrderProductVO {
	private Integer purchasedEquipmentCnt;
	private Integer rentedEquipmentCnt;
	private Double rentalEquipmentChargeAmt;
	boolean productOrderCanceledInd;
	boolean forborneInd;
	String assignedProductID;
	String assignedProductIDSourceSystemCd;
	String offerNameCd;
	private String productName;
	BigDecimal totalRecurringChargeAmt;
	BigDecimal estimatedUsageChargeAmt;
	String serviceTypeCd;
	List<Long> payChannelNumberList;
	BigDecimal previouslyAssessedDepositAmt;
	boolean forbornInd;
	BigDecimal lossRentalEquipmentChargeAmt;
	

	public Integer getPurchasedEquipmentCnt() {
		return purchasedEquipmentCnt;
	}
	public void setPurchasedEquipmentCnt(Integer purchasedEquipmentCnt) {
		this.purchasedEquipmentCnt = purchasedEquipmentCnt;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getTotalRecurringChargeAmt() {
		return totalRecurringChargeAmt;
	}
	public void setTotalRecurringChargeAmt(BigDecimal totalRecurringChargeAmt) {
		this.totalRecurringChargeAmt = totalRecurringChargeAmt;
	}
	public BigDecimal getEstimatedUsageChargeAmt() {
		return estimatedUsageChargeAmt;
	}
	public void setEstimatedUsageChargeAmt(BigDecimal bigDecimal) {
		this.estimatedUsageChargeAmt = bigDecimal;
	}
	public String getServiceTypeCd() {
		return serviceTypeCd;
	}
	public void setServiceTypeCd(String serviceTypeCd) {
		this.serviceTypeCd = serviceTypeCd;
	}
	public List<Long> getPayChannelNumberList() {
		return payChannelNumberList;
	}
	public void setPayChannelNumberList(List<Long> payChannelNumberList) {
		this.payChannelNumberList = payChannelNumberList;
	}
	public BigDecimal getPreviouslyAssessedDepositAmt() {
		return previouslyAssessedDepositAmt;
	}
	public void setPreviouslyAssessedDepositAmt(BigDecimal previouslyAssessedDepositAmt) {
		this.previouslyAssessedDepositAmt = previouslyAssessedDepositAmt;
	}
	public boolean isProductOrderCanceledInd() {
		return productOrderCanceledInd;
	}
	public void setProductOrderCanceledInd(boolean productOrderCanceledInd) {
		this.productOrderCanceledInd = productOrderCanceledInd;
	}
	public boolean isForborneInd() {
		return forborneInd;
	}
	public void setForborneInd(boolean forborneInd) {
		this.forborneInd = forborneInd;
	}
	public Double getRentalEquipmentChargeAmt() {
		return rentalEquipmentChargeAmt;
	}
	public void setRentalEquipmentChargeAmt(Double rentalEquipmentChargeAmt) {
		this.rentalEquipmentChargeAmt = rentalEquipmentChargeAmt;
	}
	public String getAssignedProductID() {
		return assignedProductID;
	}
	public void setAssignedProductID(String assignedProductID) {
		this.assignedProductID = assignedProductID;
	}
	public String getAssignedProductIDSourceSystemCd() {
		return assignedProductIDSourceSystemCd;
	}
	public void setAssignedProductIDSourceSystemCd(String assignedProductIDSourceSystemCd) {
		this.assignedProductIDSourceSystemCd = assignedProductIDSourceSystemCd;
	}
	public String getOfferNameCd() {
		return offerNameCd;
	}
	public void setOfferNameCd(String offerNameCd) {
		this.offerNameCd = offerNameCd;
	}
	public Integer getRentedEquipmentCnt() {
		return rentedEquipmentCnt;
	}
	public void setRentedEquipmentCnt(Integer rentedEquipmentCnt) {
		this.rentedEquipmentCnt = rentedEquipmentCnt;
	}
	public void setLossRentalEquipmentChargeAmt(Double lossRentalEquipmentChargeAmt) {
		this.rentedEquipmentCnt = rentedEquipmentCnt;
	}
	public BigDecimal getLossRentalEquipmentChargeAmt() {
		return lossRentalEquipmentChargeAmt;
	}
	public void setLossRentalEquipmentChargeAmt(BigDecimal lossRentalEquipmentChargeAmt) {
		this.lossRentalEquipmentChargeAmt = lossRentalEquipmentChargeAmt;
	}
	
}
