package com.telus.csm.ewlnsc.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;


/**
 * 
 * @author x145592
 *
 */
public class EnterpriseSalesServiceUtil 
{
	private static Logger logger = Logger.getLogger(EnterpriseSalesServiceUtil.class);
	
	private static final String PROV_CD_QUEBEC = "QC";
	private static final String PROV_CD_NEWFOUNDLAND = "NL";
	private static final String PROV_CD_QUEBEC_OLD = "PQ";
	private static final String PROV_CD_NEWFOUNDLAND_OLD = "NF";
	
	private EnterpriseSalesServiceUtil(){
		
	}
	
	public static String getXmlString(Object obj, Class<?> type) {
		String str = "";
		try {
			JAXBContext jc = JAXBContext.newInstance(type);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			StringWriter w = new StringWriter();

			marshaller.marshal(new JAXBElement(new QName("uri", type.getSimpleName()), type, obj), w);
			// marshaller.marshal(obj, w)

			str = w.toString();
		} catch( JAXBException e ) {
			logger.error(" getXmlString", e);
		} catch (IllegalArgumentException e){
			logger.error(" getXmlString", e);
		} 
		
		return str;
	}
	
	public static String getErrorCodesStr(List<String> errorCodes) {
		if (errorCodes != null && !errorCodes.isEmpty()) {
			StringBuilder strBuilder = new StringBuilder();
			for (String str : errorCodes) {
				strBuilder.append(str);
				strBuilder.append("_");
			}
			return strBuilder.deleteCharAt(strBuilder.length()-1).toString();
		}
		return "";
	}
	
	public static String getStackTrace(final Exception exc) {
		final StringWriter swr = new StringWriter();
		exc.printStackTrace(new PrintWriter(swr));
		return exc.getMessage() + " " + exc.getCause() + " " + swr.toString();
		
	}

	public static String getStackTrace(final Throwable thw) {
		final StringWriter swr = new StringWriter();
		final PrintWriter pwr = new PrintWriter(swr);
		thw.printStackTrace(pwr);
		
		return swr.toString();
	}
	
	public static Date removeTime(Date date){
		DateFormat df = new SimpleDateFormat("yyyyMMdd"); 
		String formattedDate = df.format(date);
		Date dateNoTime = null;
		try{
			dateNoTime = df.parse(formattedDate);
		} catch(ParseException e){
			logger.error(e);
			dateNoTime = date;
		}
		return dateNoTime;
		
	}
	
	public static String getBrandId(String brandCode){
		if(brandCode.equalsIgnoreCase(Constants.TELUS)){
			return Constants.TELUS_BRAND_ID;
		}else{
			return "";
		}
	}
	
	public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
	public static boolean isWirelineBan(final String masterSourceCd) {
		boolean result = false;
		
		if (EnterpriseWLNSalesServicesConstants.ENABLER_BILLING_ACCOUNT_SYSTEM_CODE.equalsIgnoreCase(masterSourceCd)) {
			result = true;
		}
		
		return result;
	}

	
	/**
	 * Convert new Quebec/NewFoundland province codes to old one
	 * @param provinceCode
	 * @return
	 */
	public static String convertNewProvinceCodeToOld(String provinceCode) {
		if (provinceCode==null) {
			return null;
		}
		else if (PROV_CD_QUEBEC.equals(provinceCode)) {
			return PROV_CD_QUEBEC_OLD;
		}
		else if (PROV_CD_NEWFOUNDLAND.equals(provinceCode)) {
			return PROV_CD_NEWFOUNDLAND_OLD;
		}
		else {
			return provinceCode;
		}
	}
	
	public static boolean isWeekday(Date value) {
		Calendar cal = Calendar.getInstance();
	    cal.setTime(value);
	    
	    if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || 
	    	    cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
	    	return false;
	    }
	    
	    return true;

	}
	
	public static boolean containsAnyIgnoreCase(List<String> source, String[] target) {
		
		if (CollectionUtils.isEmpty(source)) {
			return false;
		}
		
		for (String t : target) {
			for (String s : source) {
				if (t.equalsIgnoreCase(s)) {
					return true; 
				}
			}
		}
		
		return false;
	}

	public static boolean isEligibleForInstallCredit(Date appointmentDate) {
		//1. Install date must be >= trunc(sysdate)  +1
		//2. Install date must be <= trunc(sysdate) + 5
		//3. Install date dayOfWeek must be in (Mon, Tues, Wed, Thurs, or Fri) 
		if (appointmentDate != null) {
			Date sysdatePlusOne = addDays(Calendar.getInstance().getTime(), 1);
			sysdatePlusOne = removeTime(sysdatePlusOne);
			
			Date sysdatePlusFive = addDays(Calendar.getInstance().getTime(), 5);
			sysdatePlusFive = removeTime(sysdatePlusFive);
			
			if(appointmentDate.compareTo(sysdatePlusOne) >= 0 && appointmentDate.compareTo(sysdatePlusFive) <=0 && isWeekday(appointmentDate)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static Date getEndOfDate (Date date ) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 99);
		
		return cal.getTime(); 
	}
	
	public static List<String> splitString( String value, String regex ) {
		
		List<String> result = new ArrayList<String>();

		if (StringUtils.isNotBlank( value ) ) {
			for( String element : value.split( regex ) ) {
				if ( StringUtils.isNotBlank( element ) ) {
					result.add( element.trim());
				}
			}
		}
		
		return result ;
	}
}
