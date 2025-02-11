package com.telus.csm.esdg.datagrid.coherence;

import java.io.Serializable;

import com.telus.csm.esdg.EsdgConstants;
import com.telus.csm.esdg.domain.EsdgOrderDO_1;

// TODO: Auto-generated Javadoc
/**
 * The Class StatsCounter.
 */
public class SalesOrderStatisticCounter implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6546976100072389535L;

	/** The total. */
	protected long total = 0;
	
	/** The kb succ. */
	protected long kbSucc = 0;
	
	/** The succ. */
	protected long succ = 0;
	
	/** The fail. */
	protected long fail = 0;
	
	/** The error. */
	protected long error = 0;
	
	/** The cancel. */
	protected long cancel = 0;

	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * Gets the kb succ.
	 *
	 * @return the kb succ
	 */
	public long getKbSucc() {
		return kbSucc;
	}

	/**
	 * Gets the succ.
	 *
	 * @return the succ
	 */
	public long getSucc() {
		return succ;
	}

	/**
	 * Gets the fail.
	 *
	 * @return the fail
	 */
	public long getFail() {
		return fail;
	}

	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public long getError() {
		return error;
	}

	/**
	 * Gets the cancel.
	 *
	 * @return the cancel
	 */
	public long getCancel() {
		return cancel;
	}
	
	/**
	 * Instantiates a new stats counter.
	 *
	 * @param order the order
	 */
	public SalesOrderStatisticCounter(EsdgOrderDO_1 order) {
		add(order);
	}

	/**
	 * Adds the.
	 *
	 * @param value the value
	 */
	public void add(SalesOrderStatisticCounter value) {
		this.total += value.total;
		this.kbSucc += value.kbSucc;
		this.succ += value.succ;
		this.fail += value.fail;
		this.error += value.error;
		this.cancel += value.cancel;
	}

	/**
	 * Adds the.
	 *
	 * @param order the order
	 */
	public void add(EsdgOrderDO_1 order) {
		this.total += 1;
		if( EsdgConstants.ESDG_FULFILLMENT_STATUS_SUCESSFUL.equals(order.getFulfillmentStatus()) ) this.succ += 1;
		if( EsdgConstants.ESDG_FULFILLMENT_STATUS_KB_SUCESSFUL.equals(order.getFulfillmentStatus()) ) this.kbSucc += 1;
		if( EsdgConstants.ESDG_FULFILLMENT_STATUS_FAILED.equals(order.getFulfillmentStatus()) ) this.fail += 1;
		if( EsdgConstants.ESDG_FULFILLMENT_STATUS_ERROR.equals(order.getFulfillmentStatus()) ) this.error += 1;
		if( EsdgConstants.ESDG_FULFILLMENT_STATUS_CANCEL.equals(order.getFulfillmentStatus()) ) this.cancel += 1;		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new StringBuffer()
			.append("total_context = ").append(total).append(" : ")
			.append("kb_succ = ").append(kbSucc).append(" : ")
			.append("succ = ").append(succ).append(" : ")
			.append("fail = ").append(fail).append(" : ")
			.append("error = ").append(error).append(" : ")
			.append("cancel = ").append(cancel).append(" : ")
			.toString();
	}
}
