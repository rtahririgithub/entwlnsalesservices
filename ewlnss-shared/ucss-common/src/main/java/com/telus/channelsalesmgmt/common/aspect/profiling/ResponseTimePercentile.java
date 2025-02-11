/*
 * Author: Jason Li (t840227)
 * Contact: jason.li2@telus.com
 */
package com.telus.channelsalesmgmt.common.aspect.profiling;

// TODO: Auto-generated Javadoc
/**
 * The Class ResponseTimePercentile.
 */
public class ResponseTimePercentile extends UsagePercentile {
	
	/** The resample key sequence. */
	private static String[] resampleKeySequence; 
	static {
		resampleKeySequence = new String[18];
		resampleKeySequence[0] = "<1";
		for( int i = 1; i <= 10; i++ ) {
			resampleKeySequence[i] = Integer.toString(i); 
		}
		resampleKeySequence[11] = "<15";
		resampleKeySequence[12] = "<30";
		resampleKeySequence[13] = "<45";
		resampleKeySequence[14] = "<60";
		resampleKeySequence[15] = "<90";
		resampleKeySequence[16] = "<120";
		resampleKeySequence[17] = ">=120";
	}
		
	/* (non-Javadoc)
	 * @see com.telus.channelsalesmgmt.common.aspect.profiling.UsagePercentile#getResampleKeySequence()
	 */
	protected String[] getResampleKeySequence() {
		return resampleKeySequence;
	}

	/* (non-Javadoc)
	 * @see com.telus.channelsalesmgmt.common.aspect.profiling.UsagePercentile#getResampleKey(java.lang.String)
	 */
	protected String getResampleKey(String sampleValueInMillis) throws Exception {
		int sampleValueInt = Integer.parseInt(sampleValueInMillis) / 1000;
		
		if( sampleValueInt < 1 ) {
			return resampleKeySequence[0];
		} else if( sampleValueInt <= 10 ) {
			return Integer.toString(sampleValueInt);
		} else if( sampleValueInt < 15 ) {	
			return resampleKeySequence[11];
		} else if( sampleValueInt < 30 ) {	
			return resampleKeySequence[12];
		} else if( sampleValueInt < 45 ) {	
			return resampleKeySequence[13];
		} else if( sampleValueInt < 60 ) {	
			return resampleKeySequence[14];
		} else if( sampleValueInt < 90 ) {	
			return resampleKeySequence[15];
		} else if( sampleValueInt < 120 ) {	
			return resampleKeySequence[16];
		} else {
			return resampleKeySequence[17];
		}
	}

}
