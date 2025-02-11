package com.telus.csm.ess.common.domain;

import java.util.List;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.AccessoryOffer;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage;

public class SalesValidationHelperResult {

	
	private boolean salesItemValidationInd;
	
	private List<AccessoryOffer> accessoryOfferSummaryList;
	
	private List<ValidationMessage> validationMessageList;
	
	List<SalesResponseMessage.MessageList> helperMessageList;

	public List<SalesResponseMessage.MessageList> getHelperMessageList() {
		return helperMessageList;
	}

	public void setHelperMessageList(List<SalesResponseMessage.MessageList> helperMessageList) {
		this.helperMessageList = helperMessageList;
	}

	public boolean isSalesItemValidationInd() {
		return salesItemValidationInd;
	}

	public void setSalesItemValidationInd(boolean salesItemValidationInd) {
		this.salesItemValidationInd = salesItemValidationInd;
	}

	public List<AccessoryOffer> getAccessoryOfferSummaryList() {
		return accessoryOfferSummaryList;
	}

	public void setAccessoryOfferSummaryList(List<AccessoryOffer> accessoryOfferSummaryList) {
		this.accessoryOfferSummaryList = accessoryOfferSummaryList;
	}

	public List<ValidationMessage> getValidationMessageList() {
		return validationMessageList;
	}

	public void setValidationMessageList(List<ValidationMessage> validationMessageList) {
		this.validationMessageList = validationMessageList;
	}

	
}
