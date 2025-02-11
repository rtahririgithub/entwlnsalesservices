package com.telus.csm.ewlnsc.domain;

import java.util.List;

import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Description;

public class PromotionGroupVO {
	private String id;
	private String code;
	private List<Description> description;
	private String enforcementType ;//Valid values are MANDATORY_UNLIMITED, MANDATORY_CHOICE, OPTIONAL
	private boolean stackable;
	private int minQty;
	private int maxQty;
	private String availability; //Possible values are IN_MARKET, AGENT_ASSIGNED
	private int ranking;
	private String promotionGroupType;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<Description> getDescription() {
		return description;
	}
	public void setDescription(List<Description> description) {
		this.description = description;
	}
	public String getEnforcementType() {
		return enforcementType;
	}
	public void setEnforcementType(String enforcementType) {
		this.enforcementType = enforcementType;
	}
	public boolean isStackable() {
		return stackable;
	}
	public void setStackable(boolean stackable) {
		this.stackable = stackable;
	}
	public int getMinQty() {
		return minQty;
	}
	public void setMinQty(int minQty) {
		this.minQty = minQty;
	}
	public int getMaxQty() {
		return maxQty;
	}
	public void setMaxQty(int maxQty) {
		this.maxQty = maxQty;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public String getPromotionGroupType() {
		return promotionGroupType;
	}
	public void setPromotionGroupType(String promotionGroupType) {
		this.promotionGroupType = promotionGroupType;
	}
	
	
	
}
