package com.telus.csm.esdg.datagrid.coherence;

import com.tangosol.util.aggregator.AbstractAggregator;
import com.tangosol.util.extractor.IdentityExtractor;
import com.telus.csm.esdg.domain.EsdgOrderDO_1;

// TODO: Auto-generated Javadoc
/**
 * The Class SalesOrderStatisticAggregator.
 */
public final class SalesOrderStatisticAggregator extends AbstractAggregator {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8200380661766001081L;
	
	/** The sales order statistic. */
	protected transient SalesOrderStatistic salesOrderStatistic;
		
	/**
	 * Instantiates a new sales order statistic aggregator.
	 */
	public SalesOrderStatisticAggregator() {
		super(IdentityExtractor.INSTANCE);
	}
	
	/* (non-Javadoc)
	 * @see com.tangosol.util.aggregator.AbstractAggregator#finalizeResult(boolean)
	 */
	@Override
	protected Object finalizeResult(boolean isFinal) {
		return salesOrderStatistic;
	}

	/* (non-Javadoc)
	 * @see com.tangosol.util.aggregator.AbstractAggregator#init(boolean)
	 */
	@Override
	protected void init(boolean isFinal) {
		salesOrderStatistic = new SalesOrderStatistic();
	}

	/* (non-Javadoc)
	 * @see com.tangosol.util.aggregator.AbstractAggregator#process(java.lang.Object, boolean)
	 */
	@Override
	protected void process(Object oValue, boolean isFinal) {
		if( oValue == null ) return;
		// process partial aggregated result
		if( isFinal ) {
			salesOrderStatistic.add((SalesOrderStatistic)oValue);
		// process new record
		} else {
			EsdgOrderDO_1 order = (EsdgOrderDO_1)oValue;
			salesOrderStatistic.add(order);
		}
	}
}
