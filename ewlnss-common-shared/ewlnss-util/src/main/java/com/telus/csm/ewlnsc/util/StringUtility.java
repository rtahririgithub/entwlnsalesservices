/**
 * 
 */
package com.telus.csm.ewlnsc.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author x145592
 *
 */
public class StringUtility {

    private StringUtility(){
    	
    }
    
    /**
     * The origal postal code string. It might be 6 or 7. We will convert it from 7 to 6 if (the forth digital is space). 
     * If the string is not 6 or 7 digital, we just return the original one. Don't too detail validation. 
     * 
     * @param postalCode
     * @return
     */
    public static String covertSimplePostalCodeTo6Digitals(String postalCode) {
    	
    	if (postalCode == null || postalCode.trim().length() == 0) return postalCode;
    	
    	if (postalCode.trim().length() == 7 && postalCode.trim().substring(3,4).equalsIgnoreCase(Constants.SPACE)){
    		String newOne = postalCode.trim();
    		return (newOne.substring(0, 3) + newOne.substring(4)).toUpperCase();
    	}    		
    	
    	return postalCode.toUpperCase();
    	
    }
    
	 
	/**
	 * Utility function for encrypting a given value using Telus crypto framework.
	 * 
	 * @param value
	 * @param encryptInd
	 * @return
	 */
	public static String encrypt(String value, boolean encryptInd) {
		if (value != null && encryptInd == true) {
			return null; // EncryptionUtil.encrypt(value);
		} else {
			return value;
		}
	}
	
	/**
	 * 
	 * @param wsdlUrl
	 * @return the last part of the url representing the web service name.
	 */
	public static String getWebServiceNameFromWsdlUrl(String wsdlUrl){
		String lastSlashString = "";
		if (!StringUtils.isEmpty(wsdlUrl)){
			int lastIndex = wsdlUrl.lastIndexOf('/');
			lastSlashString = wsdlUrl.substring(lastIndex);
			if (lastSlashString.length() == 1){
				String trimmedForwardSlash = wsdlUrl.substring(0, lastIndex);
				int prevLastIndex = trimmedForwardSlash.lastIndexOf('/');
				lastSlashString = trimmedForwardSlash.substring(++prevLastIndex);
			}else{
				lastSlashString = lastSlashString.substring(1, lastSlashString.length());
			}
		}
		
		return lastSlashString;
	}

	public static String getNeededTagContentFromSoapException(String soapErrorString, String tagName) {
		String neededTagContent = null;

		if (!StringUtils.isEmpty(soapErrorString)) {

			String tagElementStart = "<" + tagName + ">";
			String tagElementEnd = "</" + tagName + ">";

	        if ( (soapErrorString.indexOf(tagElementStart) > 0) && (soapErrorString.indexOf(tagElementEnd) > 0) ) {
	        	int contentStart = soapErrorString.indexOf(tagElementStart) + tagElementStart.length();
	        	int contentEnd = soapErrorString.indexOf(tagElementEnd);

	        	neededTagContent = soapErrorString.substring(contentStart, contentEnd);
	        }
		}

		return neededTagContent;
	}
}
