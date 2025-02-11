package com.telus.csm.ewlnss.adapter.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.IllegalFormatException;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.log4j.Logger;

public class TimestampAdapter extends XmlAdapter<String, Timestamp> {
	private static Logger logger = Logger.getLogger(TimestampAdapter.class);

	@Override
	public String marshal(Timestamp value) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		if(value == null){
			return null;
		}
		int nanos = value.getNanos() / 1000;
		String nanoStr = Integer.toString(nanos);
		String nanoAppend;

		if( nanoStr.length() < 6 ) {
			nanoAppend = "000000" + nanoStr;
			nanoStr = nanoAppend;
		}

		return sdf.format(value) + "." + nanoStr.substring(nanoStr.length() - 6, nanoStr.length());
	}

	@Override
	public Timestamp unmarshal(String value) throws Exception {
		return convertStringToTimestamp(value);
	}

	private Timestamp convertStringToTimestamp(String tsString) {
		if(tsString == null){
			return null;
		}
		Calendar calendar = DatatypeConverter.parseDateTime(tsString);
		Timestamp timest = new java.sql.Timestamp(calendar.getTimeInMillis());

		/**
		 * check if has fractional second component as in
		 * 2014-02-26T10:46:32.5444545-05:00
		 * 
		 * **/
		if( (tsString.lastIndexOf('.') > 0) && (getNanoSeconds(tsString) > 0) ) {
			timest.setNanos(getNanoSeconds(tsString));
		}

		return timest;

	}

	/**
	 * Gets nano seconds from time string.
	 * Example of time string:
	 * "2014-02-26T10:46:32.544-05:00" has time zone at the end
	 * "2014-02-26T10:46:32-05:00" has time zone at the end and no fractional second
	 * "2014-02-26T10:46:32.544456" no time zone at the end. this is SRDPS time format
	 * 
	 * @param tsString
	 * @return
	 */
	private int getNanoSeconds(String tsString) {
		String methodName = "getNanoSeconds:";
		int nanoSecs = 0;
		try {
			int fromIndex = tsString.lastIndexOf('.') + 1;
			int toIndex = tsString.lastIndexOf('-') > 0 ? tsString.lastIndexOf('-') : 0;

			if( tsString.lastIndexOf('Z') > 0 ) {
				toIndex = tsString.lastIndexOf('Z');
			}

			String nanos;
			if( (toIndex > 0) && (toIndex > fromIndex) ) {
				nanos = tsString.substring(fromIndex, toIndex);
			} else {
				nanos = tsString.substring(fromIndex);
			}

			nanos = String.format("%-9s", nanos).replace(' ', '0');
			if( nanos.length() > 9 ) {
				nanos = nanos.substring(0, 9);
			}
			nanoSecs = Integer.parseInt(nanos);

		} catch( IndexOutOfBoundsException e ) {
			logger.error(methodName + e.getMessage(), e);
		} catch( IllegalFormatException e ) {
			logger.error(methodName + e.getMessage(), e);
		} catch( NumberFormatException e ) {
			logger.error(methodName + e.getMessage(), e);
		} catch( NullPointerException e ) {
			logger.error(methodName + e.getMessage(), e);
		}

		return nanoSecs;
	}

	/**
	 * Gets nano seconds since Jan 1 1970 00:00:00 GMT.
	 * 
	 * @param ts
	 * @return
	 */
	public long getNanoSeconds(Timestamp ts) {
		return ((ts.getTime() / 1000) * 1000000000) + ts.getNanos();

	}

	public static void main(String[] args) {
		/*// String ts = "2014-12-08T23:59:10.110140";
		// String ts2 = "2014-02-26T10:46:32.544123456-05:00";
		// String ts3 = "2014-02-26T10:46:32-05:00";
		// String ts4 = "2014-02-26T10:46:32.123Z";

		// String ts = "2014-03-23T16:54:47.512619";
		String ts = "2014-03-23T16:54:47.512620000";

		TimestampAdapter tsa = new TimestampAdapter();
		try {
			Timestamp tso1 = tsa.unmarshal(ts);
			// Timestamp tso2 = tsa.unmarshal(ts2);
			// Timestamp tso3 = tsa.unmarshal(ts3);
			// Timestamp tso4 = tsa.unmarshal(ts4);

			// System.out.println("TS1: " + tso1);
			System.out.println("TS1:" + tsa.marshal(tso1));

			System.out.println("TS1 milliseconds since 1970:" + tso1.getTime());
			System.out.println("TS1 nano seconds:" + tso1.getNanos());

			// long nanoSeconds = (tso1.getTime()/1000) * 1000000000 + tso1.getNanos();

			System.out.println("TS1 nano seconds since 1970:" + tsa.getNanoSeconds(tso1));

			// System.out.println("TS2:" + tsa.marshal(tso2));
			// System.out.println("TS4:" + tsa.marshal(tso4));

		} catch( Exception e ) {
			
			e.printStackTrace();
		}*/
	}
}
