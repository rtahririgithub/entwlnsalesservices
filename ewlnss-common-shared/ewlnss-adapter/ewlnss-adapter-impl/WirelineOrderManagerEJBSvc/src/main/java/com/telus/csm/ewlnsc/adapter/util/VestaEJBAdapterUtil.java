package com.telus.csm.ewlnsc.adapter.util;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.scis.domain.AddressVO;
import com.telus.csm.ewlnsc.util.EnterpriseSalesServiceUtil;
import com.telus.ucss.ssm.objectmodel.ServiceAddressInfo;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProvinceCodeVO;

public class VestaEJBAdapterUtil {

    public static AddressVO translateVestaProductAddress(ServiceAddressInfo customerServiceAddress) {
		if( customerServiceAddress == null ) return null;
		AddressVO translatedAddress = new AddressVO();

		translatedAddress.setCity(customerServiceAddress.getMunicipalityName());
		translatedAddress.setCountry(customerServiceAddress.getCountryCode());
		translatedAddress.setPoBox(customerServiceAddress.getPostOfficeBoxNumber());
		translatedAddress.setPostalCode(customerServiceAddress.getPostalZipCode());
		if (customerServiceAddress.getProvinceStateCode()!=null) translatedAddress.setProvince(ProvinceCodeVO.fromValue(EnterpriseSalesServiceUtil.convertNewProvinceCodeToOld(customerServiceAddress.getProvinceStateCode())).value());
		//preSalesResponseAddress.setServiceAddressId(customerServiceAddress.getAddressId());
		translatedAddress.setStreetDirection(customerServiceAddress.getStreetDirectionCode());
		translatedAddress.setStreetName(customerServiceAddress.getStreetName());
		translatedAddress.setStreetNumber(customerServiceAddress.getStreetNumber());
		translatedAddress.setStreetNumberSuffix( customerServiceAddress.getStreetNumberSuffix());
		translatedAddress.setStreetDirection(customerServiceAddress.getStreetDirectionCode());
		translatedAddress.setStreetType(customerServiceAddress.getStreetTypeCode());
		translatedAddress.setUnit(customerServiceAddress.getUnitNumber());
		translatedAddress.setUnitType(customerServiceAddress.getUnitTypeCode());

		return translatedAddress;
	}
    
	/**
	 * Checks if is empty address.
	 *
	 * @param customerServiceAddress the customer service address
	 * @return true, if is empty address
	 */
	public static boolean isEmptyAddress(ServiceAddressInfo customerServiceAddress) {
		return customerServiceAddress == null ||
			StringUtils.isEmpty(customerServiceAddress.getMunicipalityName()) ||
			StringUtils.isEmpty(customerServiceAddress.getProvinceStateCode()) ||
			StringUtils.isEmpty(customerServiceAddress.getStreetName()) ||
			StringUtils.isEmpty(customerServiceAddress.getStreetNumber());
	}
	
}
