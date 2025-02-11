package com.telus.csm.ewlnsc.domain;

/**
 * 
 * @author Jose.Mena
 *
 */
public class CreateProfileForNewCustomerRequestCommonVO extends CoreRequestBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String email;
	private String notificationLanguage;
	private String customerId;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNotificationLanguage() {
		return notificationLanguage;
	}

	public void setNotificationLanguage(String notificationLanguage) {
		this.notificationLanguage = notificationLanguage;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
