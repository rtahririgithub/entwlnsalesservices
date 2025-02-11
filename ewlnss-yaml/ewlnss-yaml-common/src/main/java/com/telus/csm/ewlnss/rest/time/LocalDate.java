package com.telus.csm.ewlnss.rest.time;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(using = CustomLocalDateSerializer.class)
public class LocalDate implements Serializable {
	private static final long serialVersionUID = 1L;
	static final String FORMAT_DATE = "yyyy-MM-dd";
	private Date date;

	public LocalDate(String parm) {
		try {
			date = (new SimpleDateFormat(FORMAT_DATE)).parse(parm);
		} catch (ParseException e) {
			//TODO how to handle this case? 
			throw new RuntimeException("Cannot parse:" + parm, e);
		}
	}

	public LocalDate() {
		date = new Date();
	}

	public LocalDate(Date parm) {
		date = parm;
	}
	
	public Date toDate() {
		return date;
	}

	@Override
	public String toString() {
		return format(date);
	}

	public static String format(Date parm) {
		if (parm == null) {
			return null;
		}
		return (new SimpleDateFormat(FORMAT_DATE)).format(parm);
	}
	
	public static boolean equals(Date date1, Date date2) {
		
		if (date1 == null && date2 == null) {
			return true;
		}
		
		if (date1 == null || date2 == null) {
			return false;
		}

		return format(date1).equals(format(date2));
	}

}

 