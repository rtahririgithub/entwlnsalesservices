/*
 * Author: Jason Li (t840227)
 * Contact: jason.li2@telus.com
 */
package com.telus.channelsalesmgmt.common.aspect.profiling;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class UsagePercentile.
 */
public abstract class UsagePercentile {
	
	/** The resample map. */
	protected Map<String, Long> resampleMap = new HashMap<String, Long>();
	
	/**
	 * Gets the resample map.
	 *
	 * @return the resample map
	 */
	public Map<String, Long> getResampleMap() {
		return resampleMap;
	}

	/**
	 * Resample.
	 *
	 * @param sampleValue the sample value
	 * @throws Exception the exception
	 */
	public synchronized void resample(String sampleValue) throws Exception {
		String resampleKey = getResampleKey(sampleValue);
		if( resampleKey == null ) return;
		
		Long count = resampleMap.get(resampleKey);
		if( count == null ) {
			resampleMap.put(resampleKey, Long.valueOf(0));
		}
		resampleMap.put(resampleKey, count + 1);
	}

	/**
	 * Gets the percentile.
	 *
	 * @param percentileValue the percentile value
	 * @return the percentile
	 */
	public String getPercentile(int percentileValue) {
		String[] keySequence = getResampleKeySequence();
		Long[] resampleValues = getResampleValues(keySequence);
		
		if( keySequence == null || resampleValues == null ) return null;
		
		//get sampling size
		long sampleSize = 0;
		for( int i = 0; i < resampleValues.length; i++ ) {
			sampleSize += resampleValues[i].longValue();
		}
		
		//find percentile sample
		long percentile = sampleSize * percentileValue / 100;
		
		//find percentile value
		int resamplePosition = 0;
		long sampleTotal = 0;
		for( ; resamplePosition < resampleValues.length; resamplePosition++ ) {
			sampleTotal += resampleValues[resamplePosition].longValue();
			if( percentile <= sampleTotal ) {
				break;
			}
		}		

		return keySequence[resamplePosition];
	}
	
	/**
	 * Gets the resample values.
	 *
	 * @param keySequence the key sequence
	 * @return the resample values
	 */
	private synchronized Long[] getResampleValues(String[] keySequence) {
		if( keySequence == null || keySequence.length < 1 ) return null;
		
		Long[] resampleValues = new Long[keySequence.length];
		     
		for( int i = 0; i < keySequence.length; i++ ) {
			Long count = resampleMap.get(keySequence[i]);
			if( count == null ) {
				resampleValues[i] = Long.valueOf(0);
			} else {
				resampleValues[i] = count;
			}
		}
		
		return resampleValues;
	}

	/**
	 * Adds the usage.
	 *
	 * @param responseTimePercentileContrib the response time percentile contrib
	 */
	public void addUsage(UsagePercentile responseTimePercentileContrib) {
		for( Iterator<String> i = responseTimePercentileContrib.getResampleMap().keySet().iterator(); i.hasNext(); ) {
			String resampleKey = i.next();
			Long count = resampleMap.get(resampleKey);
			if( count == null ) {
				count = Long.valueOf(0);
			} 
			resampleMap.put(resampleKey, count + responseTimePercentileContrib.getResampleMap().get(resampleKey));
		}
	}
	
	/**
	 * Gets the resample key sequence.
	 *
	 * @return the resample key sequence
	 */
	protected abstract String[] getResampleKeySequence();

	/**
	 * Gets the resample key.
	 *
	 * @param sampleValue the sample value
	 * @return the resample key
	 * @throws Exception the exception
	 */
	protected abstract String getResampleKey(String sampleValue) throws Exception;

}
