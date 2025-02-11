/**
 * 
 */
package com.telus.csm.ewlnsc.adapter.domain.quote;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.telus.csm.ewlnss.adapter.domain.WlnOPRestSvcResponseBase;

/**
 * @author x145592
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuoteResponse extends WlnOPRestSvcResponseBase {

	private List<ProductCharacteristic> productCharacteristics = null;
    private String typeDesc;
    private String statusDesc;
    private Boolean isShoppingCartOrder;
    private String id;
    private PartyInteractionRole partyInteractionRole;
    private List<ProductOrderItem> productOrderItems = null;
    private String status;
    private String type;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
    public List<ProductCharacteristic> getProductCharacteristics() {
		return productCharacteristics;
	}
	public void setProductCharacteristics(List<ProductCharacteristic> productCharacteristics) {
		this.productCharacteristics = productCharacteristics;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public Boolean getIsShoppingCartOrder() {
		return isShoppingCartOrder;
	}
	public void setIsShoppingCartOrder(Boolean isShoppingCartOrder) {
		this.isShoppingCartOrder = isShoppingCartOrder;
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
	public List<ProductOrderItem> getProductOrderItems() {
		return productOrderItems;
	}
	public void setProductOrderItems(List<ProductOrderItem> productOrderItems) {
		this.productOrderItems = productOrderItems;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
