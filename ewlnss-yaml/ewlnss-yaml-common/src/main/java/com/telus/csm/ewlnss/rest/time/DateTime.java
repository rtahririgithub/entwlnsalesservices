package com.telus.csm.ewlnss.rest.time;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(using = CustomDateTimeSerializer.class)
public class DateTime implements Serializable {
	private static final long serialVersionUID = 1L;
	static final String FORMAT_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ssX";
	private static final String FORMAT_DATE_TIME_MILLIS = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
	private Date date;

	public DateTime(String parm) {
		try {
			date = new SimpleDateFormat(FORMAT_DATE_TIME).parse(parm);
		} catch (ParseException e) {
			try {
				date = new SimpleDateFormat(FORMAT_DATE_TIME_MILLIS).parse(parm);
			} catch (ParseException e2) {			//TODO how to handle this case? 
				throw new RuntimeException("Cannot parse:" + parm, e2);
			}
		}
	}


	public DateTime(Date parm) {
		this.date = parm;
	}
	
	public DateTime() {
		this.date = new Date();
	}

	public Date toDate() {
		return date;
	}
	
	@Override
	public String toString() {
		return new SimpleDateFormat(FORMAT_DATE_TIME).format(date);
	}
	
	public String toStringZ() {
		SimpleDateFormat sf = new SimpleDateFormat(FORMAT_DATE_TIME);
		sf.setTimeZone(TimeZone.getTimeZone("UTC"));
		return sf.format(date);
	}

}
