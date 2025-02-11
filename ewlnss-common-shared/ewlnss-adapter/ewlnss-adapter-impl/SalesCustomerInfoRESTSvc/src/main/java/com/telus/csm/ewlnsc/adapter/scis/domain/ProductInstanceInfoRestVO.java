package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
public class ProductInstanceInfoRestVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String productInstanceId;
	protected String serviceId;
	protected String serviceInstanceId;
	protected String productName;
	protected Double depositAmt;
	protected String resourceId;
	protected String productCatalogId;
	protected Boolean productSuppressionInd;
	protected Boolean includedForDepositCalcInd;
	protected String commitmentPeriodStartDt;
	protected String termCd;
	protected String omsOfferCatalogId;
	protected SingleLineInfoRestVO singleLineComponent ;
	protected InternetInfoRestVO internetComponent ;
	protected TvInfoRestVO ttvComponent ;
	protected List<EquipmentInfoRestVO> equipmentList ;
	protected String refServiceId;

	public String getRefServiceId() {
		return refServiceId;
	}

	public void setRefServiceId(String refServiceId) {
		this.refServiceId = refServiceId;
	}

	public String getProductInstanceId() {
		return productInstanceId;
	}

	public void setProductInstanceId(String productInstanceId) {
		this.productInstanceId = productInstanceId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceInstanceId() {
		return serviceInstanceId;
	}

	public void setServiceInstanceId(String serviceInstanceId) {
		this.serviceInstanceId = serviceInstanceId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getDepositAmt() {
		return depositAmt;
	}

	public void setDepositAmt(Double depositAmt) {
		this.depositAmt = depositAmt;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getProductCatalogId() {
		return productCatalogId;
	}

	public void setProductCatalogId(String productCatalogId) {
		this.productCatalogId = productCatalogId;
	}

	public Boolean getProductSuppressionInd() {
		return productSuppressionInd;
	}

	public void setProductSuppressionInd(Boolean productSuppressionInd) {
		this.productSuppressionInd = productSuppressionInd;
	}

	public Boolean getIncludedForDepositCalcInd() {
		return includedForDepositCalcInd;
	}

	public void setIncludedForDepositCalcInd(Boolean includedForDepositCalcInd) {
		this.includedForDepositCalcInd = includedForDepositCalcInd;
	}

	public String getCommitmentPeriodStartDt() {
		return commitmentPeriodStartDt;
	}

	public void setCommitmentPeriodStartDt(String commitmentPeriodStartDt) {
		this.commitmentPeriodStartDt = commitmentPeriodStartDt;
	}

	public String getTermCd() {
		return termCd;
	}

	public void setTermCd(String termCd) {
		this.termCd = termCd;
	}

	public String getOmsOfferCatalogId() {
		return omsOfferCatalogId;
	}

	public void setOmsOfferCatalogId(String omsOfferCatalogId) {
		this.omsOfferCatalogId = omsOfferCatalogId;
	}

	public SingleLineInfoRestVO getSingleLineComponent() {
		return singleLineComponent;
	}

	public void setSingleLineComponent(SingleLineInfoRestVO singleLineComponent) {
		this.singleLineComponent = singleLineComponent;
	}

	public InternetInfoRestVO getInternetComponent() {
		return internetComponent;
	}

	public void setInternetComponent(InternetInfoRestVO internetComponent) {
		this.internetComponent = internetComponent;
	}

	public TvInfoRestVO getTtvComponent() {
		return ttvComponent;
	}

	public void setTtvComponent(TvInfoRestVO ttvComponent) {
		this.ttvComponent = ttvComponent;
	}

	public List<EquipmentInfoRestVO> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<EquipmentInfoRestVO> equipmentList) {
		this.equipmentList = equipmentList;
	}
}
