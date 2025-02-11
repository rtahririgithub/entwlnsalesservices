package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.helper.EBillAdoptionAndActivationHelper;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;

import commonj.work.Work;

/**
 * 
 * @author Jose.Mena
 *
 */
public class EBillAdoptionAndActivationTask extends TaskBase {
	
	private OperationHeader header;
	private String billingAccountNumber;
	private Long customerId;
	private String eBillDeclinedReasonCd;
	private String eBillNotificationTypeCd;
	
	public EBillAdoptionAndActivationTask(OperationHeader header, String billingAccountNumber, Long customerId,
			String eBillDeclinedReasonCd, String eBillNotificationTypeCd) {
		super();
		this.header = header;
		this.billingAccountNumber = billingAccountNumber;
		this.customerId = customerId;
		this.eBillDeclinedReasonCd = eBillDeclinedReasonCd;
		this.eBillNotificationTypeCd = eBillNotificationTypeCd;
	}

	@Override
	protected void execute() {
		EBillAdoptionAndActivationHelper ebillHelper = new EBillAdoptionAndActivationHelper();
		ebillHelper.eBillAdoptionAndActivation(header, billingAccountNumber, customerId, eBillDeclinedReasonCd, eBillNotificationTypeCd);
		
	}

}
