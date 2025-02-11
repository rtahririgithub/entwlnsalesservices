package com.telus.csm.ewlnsc.domain;

public class ServiceTypeFeasibilityVO {

	private double workTimeMin;
	private double workTimeMax;
	private double workTimeDefault;
	private String recommendedInstallType;
	private String defaultBucket;
	private String productSpecificationName; 
	
	public ServiceTypeFeasibilityVO(){
		
	}
	
	public ServiceTypeFeasibilityVO(double workTimeMin, double workTimeMax,
			double workTimeDefault, String recommendedInstallType,
			String defaultBucket, String productSpecificationName) {
		super();
		this.workTimeMin = workTimeMin;
		this.workTimeMax = workTimeMax;
		this.workTimeDefault = workTimeDefault;
		this.recommendedInstallType = recommendedInstallType;
		this.defaultBucket = defaultBucket;
		this.productSpecificationName = productSpecificationName;
	}
	
	public boolean isBookablService(){
		if (this.recommendedInstallType != null && this.recommendedInstallType.equalsIgnoreCase("SW")){
			return false;
		}
		
		return true;
	}
	
	
	public double getWorkTimeMin() {
		return workTimeMin;
	}
	public void setWorkTimeMin(double workTimeMin) {
		this.workTimeMin = workTimeMin;
	}
	public double getWorkTimeMax() {
		return workTimeMax;
	}
	public void setWorkTimeMax(double workTimeMax) {
		this.workTimeMax = workTimeMax;
	}
	public double getWorkTimeDefault() {
		return workTimeDefault;
	}
	public void setWorkTimeDefault(double workTimeDefault) {
		this.workTimeDefault = workTimeDefault;
	}
	public String getRecommendedInstallType() {
		return recommendedInstallType;
	}
	public void setRecommendedInstallType(String recommendedInstallType) {
		this.recommendedInstallType = recommendedInstallType;
	}
	public String getDefaultBucket() {
		return defaultBucket;
	}
	public void setDefaultBucket(String defaultBucket) {
		this.defaultBucket = defaultBucket;
	}
	public String getProductSpecificationName() {
		return productSpecificationName;
	}
	public void setProductSpecificationName(String productSpecificationName) {
		this.productSpecificationName = productSpecificationName;
	}
	
	
	
	
}
