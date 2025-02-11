package com.telus.csm.ewlnsc.adapter.domain.quote;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductOrderItem {

	private String action;
	private String actionDesc;
	private String stateDesc;
	private String refNum;
	private Boolean isShoppingCartOrder;
	private Boolean isAmendingOrder;
	private String omsProcessStatusType;
	private Boolean isOnSiteAmend;
	private String id;
	private PartyInteractionRole partyInteractionRole;
	private Product product;
	private String state;
	@JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getActionDesc() {
		return actionDesc;
	}

	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}

	public String getStateDesc() {
		return stateDesc;
	}

	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}

	public String getRefNum() {
		return refNum;
	}

	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}

	public String getOmsProcessStatusType() {
		return omsProcessStatusType;
	}

	public void setOmsProcessStatusType(String omsProcessStatusType) {
		this.omsProcessStatusType = omsProcessStatusType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PartyInteractionRole getPartyInteractionRole() {
		return partyInteractionRole;
	}

	public void setPartyInteractionRole(PartyInteractionRole partyInteractionRole) {
		this.partyInteractionRole = partyInteractionRole;
	}

	public Boolean getIsAmendingOrder() {
		return isAmendingOrder;
	}

	public Boolean getIsOnSiteAmend() {
		return isOnSiteAmend;
	}

	public void setIsAmendingOrder(Boolean isAmendingOrder) {
		this.isAmendingOrder = isAmendingOrder;
	}

	public void setIsOnSiteAmend(Boolean isOnSiteAmend) {
		this.isOnSiteAmend = isOnSiteAmend;
	}

	public Boolean getIsShoppingCartOrder() {
		return isShoppingCartOrder;
	}

	public void setIsShoppingCartOrder(Boolean isShoppingCartOrder) {
		this.isShoppingCartOrder = isShoppingCartOrder;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	@JsonAnySetter
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

}