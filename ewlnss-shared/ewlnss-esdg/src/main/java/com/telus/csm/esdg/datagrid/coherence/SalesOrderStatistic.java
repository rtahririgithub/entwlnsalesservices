package com.telus.csm.esdg.datagrid.coherence;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.telus.csm.esdg.domain.EsdgOrderDO_1;

// TODO: Auto-generated Javadoc
/**
 * The Class SalesOrderStatistic.
 */
public final class SalesOrderStatistic implements Serializable {

		/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5481122559434648278L;
		
		/** The total. */
		protected long total = 0;
		
	/** The oldest ts. */
	protected long oldestTs = 0;
		
	/** The newest ts. */
	protected long newestTs = 0;
		
	/** The stats counter map. */
	protected HashMap<String, HashMap<String, SalesOrderStatisticCounter>> statsCounterMap = new HashMap<String, HashMap<String, SalesOrderStatisticCounter>>(); // classifier_type --> classifier
		
	public SalesOrderStatistic() {}

		/**
		 * Gets the total.
		 *
		 * @return the total
		 */
		public long getTotal() {
			return total;
		}

		/**
	 * Gets the oldest ts.
		 *
	 * @return the oldest ts
		 */
	public long getOldestTs() {
		return oldestTs;
		}

		/**
	 * Gets the newest ts.
		 *
	 * @return the newest ts
		 */
	public long getNewestTs() {
		return newestTs;
		}

		/**
	 * Gets the stats counter map.
		 *
	 * @return the stats counter map
		 */
	public HashMap<String, HashMap<String, SalesOrderStatisticCounter>> getStatsCounterMap() {
		return statsCounterMap;
		}

		/**
	 * Adds the.
		 *
	 * @param theStats the the stats
	 */
	/* (non-Javadoc)
	 * @see com.telus.csm.esdg.datagrid.coherence.SalesOrderStatistic#add(com.telus.csm.esdg.datagrid.coherence.SalesOrderStatistic)
		 */
	public void add(SalesOrderStatistic stats) {
		this.total += stats.total;
		if( this.oldestTs > stats.oldestTs ) {
			this.oldestTs = stats.oldestTs;
		}
		if( this.newestTs < stats.newestTs ) {
			this.newestTs = stats.newestTs;
		}

		if( stats.statsCounterMap != null ) {
			for( Map.Entry<String, HashMap<String, SalesOrderStatisticCounter>> contributorEntry : stats.statsCounterMap.entrySet() ) {
				HashMap<String, SalesOrderStatisticCounter> thisStats = statsCounterMap.get(contributorEntry.getKey());
				if( thisStats == null ) {
					statsCounterMap.put(contributorEntry.getKey(), contributorEntry.getValue());
				} else {					
					for( Map.Entry<String, SalesOrderStatisticCounter> contributorCounterEntry : contributorEntry.getValue().entrySet() ) {
						SalesOrderStatisticCounter thisCounter = thisStats.get(contributorCounterEntry.getKey());
						if( thisCounter == null ) {
							thisStats.put(contributorCounterEntry.getKey(), contributorCounterEntry.getValue());
						} else {
							thisCounter.add(contributorCounterEntry.getValue());							
		}
		}
				}
			}
		}
		}

		/**
		 * Adds the.
		 *
		 * @param order the order
		 */
	/* (non-Javadoc)
	 * @see com.telus.csm.esdg.datagrid.coherence.SalesOrderStatistic#add(com.telus.csm.esdg.domain.EsdgOrderDO_1)
	 */
		public void add(EsdgOrderDO_1 order) {
			this.total += 1;
		if( order.getDataGenerationTimeInMills() > 0 && (oldestTs == 0 || oldestTs > order.getDataGenerationTimeInMills()) ) {
			oldestTs = order.getDataGenerationTimeInMills();
		}
		if( newestTs < order.getDataGenerationTimeInMills() ) {
			newestTs = order.getDataGenerationTimeInMills();
		}
		
		HashMap<String, String> classifierMap = order.getOrderClassifierMap();
		if( classifierMap != null ) {
			for( Map.Entry<String, String> entry : classifierMap.entrySet() ) {				
				HashMap<String, SalesOrderStatisticCounter> stats = statsCounterMap.get(entry.getKey());
				if( stats == null ) {
					stats = new HashMap<String, SalesOrderStatisticCounter>();
					statsCounterMap.put(entry.getKey(), stats);
				}
				SalesOrderStatisticCounter thisCounter = stats.get(entry.getValue());
				if( thisCounter == null ) {
					stats.put(entry.getValue(), new SalesOrderStatisticCounter(order));
				} else {					
					thisCounter.add(order);
				}
			}
		}
	}
}
