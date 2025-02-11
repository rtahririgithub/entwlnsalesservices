package com.telus.csm.ewlnsc.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.*;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author Jose.Mena
 *
 */
public class ValidateFieldsUtil {
	
	private ValidateFieldsUtil(){
		
	}
	
	// implements rules
	// is all exist
	public static boolean isAllFieldsExist(Map<String, Object> fields) {
		boolean isValid = true;
				
		for (Map.Entry<String, Object> entry : fields.entrySet()){
			// check valid
			if (entry.getValue() == null || StringUtils.isEmpty(entry.getValue().toString())){
				isValid = false;
			}
		}

		return isValid;
	}
	
	/**
	 * Validates Non Null values. If passed value is null, it'll return true.
	 * @param regExp
	 * @param value
	 * @return
	 */
	public static boolean validateRegExp(String regExp, String value) {
		if (value != null){
			Pattern pattern = Pattern.compile(regExp);
			Matcher matcher = pattern.matcher(value);
			return matcher.matches();
		}else{
			return true;
		}
		
	}
	
	/**
	 * Convert old Quebec/NewFoundland province codes to new one
	 * @param provinceCode
	 * @return
	 */
	public static String convertOldProvinceCodeToNew(String provinceCode) {
		if (provinceCode == null) {
			return null;
		}
		else if (PROV_CD_QUEBEC_OLD.equals(provinceCode)) {
			return PROV_CD_QUEBEC;
		}
		else if (PROV_CD_NEWFOUNDLAND_OLD.equals(provinceCode)) {
			return PROV_CD_NEWFOUNDLAND;
		}
		else {
			return provinceCode;
		}
	}
}
