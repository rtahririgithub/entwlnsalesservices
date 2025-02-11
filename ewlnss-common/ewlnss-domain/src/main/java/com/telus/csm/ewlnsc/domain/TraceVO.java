package com.telus.csm.ewlnsc.domain;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Discount;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineEquipmentItem;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

public class TraceVO {
	
	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(TraceVO.class);
	
	//Constants for element type
	public static final String OFFER = "OFFER";
	public static final String PRODUCT = "PRODUCT";
	public static final String EQUIPMENT = "EQUIPMENT";
	public static final String PAYMENT_OPTION = "PAYMENT OPTION";
	public static final String DISCOUNT = "DISCOUNT";
	public static final String FEATURE = "FEATURE";
	public static final String SERVICE = "SERVICE";
	public static final String CALLING_FEATURE = "CALLING FEATURE";
	
	//Constants for action
	public static final String DELETED = "Deleted";
	public static final String CHANGED = "Changed";
	public static final String ADDED = "Added";

	private String ruleName = "";
	private String elementType = "";
	
	//element key
	private String offerId = null;
	private String term = null;
	private String productCode = null;
	private String equipmentId = null;
	private String discountId = null;
	private List<String> offerList = null;
	private List<String> discountList = null;

	private String action = "";
	private String reason = null;
	private boolean validationPassedInd;
	
	public boolean isValidationPassedInd() {
		return validationPassedInd;
	}
	public void setValidationPassedInd(boolean validationPassedInd) {
		this.validationPassedInd = validationPassedInd;
	}
	public static TraceVO newInstance(Object ruleObj) {
		return newInstance(ruleObj.getClass().getSimpleName());
	}
	public static TraceVO newInstance(String ruleName) {
		TraceVO result = new TraceVO();
		result.setRuleName(ruleName);
		
		return result;
	}
	
	public String toLogMessage() {
		StringBuilder result = new StringBuilder();
		result.append(ruleName);	
		result.append(" " + action);
		result.append(" " + elementType + ";");
		if (offerId != null) {
			result.append(" OfferId=" + offerId);
		}
		if (term != null) {
			result.append(" Term=" + term);
		}
		if (productCode != null) {
			result.append(" ProductCode=" + productCode);
		}
		if (equipmentId != null) {
			result.append(" EquipmentId=" + equipmentId);
		}
		if (discountId != null) {
			result.append(" DiscountId=" + discountId);
		}
		if (reason != null) {
			result.append("; Reason=" + reason);
		}
		
		return result.toString();
	}
	
	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getElementType() {
		return elementType;
	}

	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}


	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDiscountId() {
		return discountId;
	}

	public void setDiscountId(String discountId) {
		this.discountId = discountId;
	}

	public void setOffer(Offer offer) {
		setOfferId(Long.toString(offer.getOfferId()));
	}

	public void setProduct(WirelineOfferProduct product) {
		setProductCode(product.getProductTypeCode());
	}

	public void setEquipmentItem(WirelineEquipmentItem equipment) {
		setEquipmentId(equipment.getMaterialItemCode());
	}
	
	public void setDiscount(Discount discount) { 
		try {
			if(discount!=null && CollectionUtils.isNotEmpty(discount.getProductCatalogueItemList()) && discount.getProductCatalogueItemList().get(0).getProductCatalogueIdentifier()!=null
					&& StringUtils.isNotBlank(discount.getProductCatalogueItemList().get(0).getProductCatalogueIdentifier().getProductCatalogueId())){
				setDiscountId(discount.getProductCatalogueItemList().get(0).getProductCatalogueIdentifier().getProductCatalogueId());
			}			
		} catch (RuntimeException e) {
			LOGGER.error("Exception: " + LOGGER.getStackTrace(e));
			setDiscountId("Undefined");
		}
	}
	public List<String> getOfferList() {
		return offerList;
	}
	public void setOfferList(List<String> offerList) {
		this.offerList = offerList;
	}
	public List<String> getDiscountList() {
		return discountList;
	}
	public void setDiscountList(List<String> discountList) {
		this.discountList = discountList;
	}
	
	

}
