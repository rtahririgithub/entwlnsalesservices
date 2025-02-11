package com.telus.csm.ewlnsms.rest.webservices.vo;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.XMLGregorianCalendar;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
public class PersonalInfoVO {
	protected String employmentStatusCd;
	protected String residencyCd;
	protected String creditCheckConsentCd;
	protected XMLGregorianCalendar birthDate;
	protected String underLegalCareCd;
	protected String provinceOfCurrentResidenceCd;
	
	public String getEmploymentStatusCd() {
		return employmentStatusCd;
	}
	public void setEmploymentStatusCd(String employmentStatusCd) {
		this.employmentStatusCd = employmentStatusCd;
	}
	public String getResidencyCd() {
		return residencyCd;
	}
	public void setResidencyCd(String residencyCd) {
		this.residencyCd = residencyCd;
	}
	public String getCreditCheckConsentCd() {
		return creditCheckConsentCd;
	}
	public void setCreditCheckConsentCd(String creditCheckConsentCd) {
		this.creditCheckConsentCd = creditCheckConsentCd;
	}
	public XMLGregorianCalendar getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(XMLGregorianCalendar birthDate) {
		this.birthDate = birthDate;
	}
	public String getUnderLegalCareCd() {
		return underLegalCareCd;
	}
	public void setUnderLegalCareCd(String underLegalCareCd) {
		this.underLegalCareCd = underLegalCareCd;
	}
	public String getProvinceOfCurrentResidenceCd() {
		return provinceOfCurrentResidenceCd;
	}
	public void setProvinceOfCurrentResidenceCd(String provinceOfCurrentResidenceCd) {
		this.provinceOfCurrentResidenceCd = provinceOfCurrentResidenceCd;
	}
}
