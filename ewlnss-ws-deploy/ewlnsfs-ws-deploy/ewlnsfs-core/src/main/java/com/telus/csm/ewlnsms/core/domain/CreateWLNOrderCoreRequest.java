/**
 * 
 */
package com.telus.csm.ewlnsms.core.domain;

import com.telus.csm.ewlnsc.domain.CoreRequestBase;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.CreateWirelineSalesOrderType;

/**
 * @author x145592
 *
 */
public class CreateWLNOrderCoreRequest extends CoreRequestBase {

	private static final long serialVersionUID = 1L;
	
	private CreateWirelineSalesOrderType createWirelineSalesOrder;
	
	private boolean outboundChannel;
	private boolean inboundChannel;
	
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

	public CreateWirelineSalesOrderType getCreateWirelineSalesOrder() {
		return createWirelineSalesOrder;
	}

	public void setCreateWirelineSalesOrder(CreateWirelineSalesOrderType createWirelineSalesOrder) {
		this.createWirelineSalesOrder = createWirelineSalesOrder;
	}
	
	
	
}
