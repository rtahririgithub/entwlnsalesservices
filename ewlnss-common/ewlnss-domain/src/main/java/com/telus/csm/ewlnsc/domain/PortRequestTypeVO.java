package com.telus.csm.ewlnsc.domain;

public class PortRequestTypeVO {

	// @SerializedName("portinInd")
	private Boolean portinInd = null;

	// @SerializedName("temporaryPhoneNumberRequiredInd")
	private Boolean temporaryPhoneNumberRequiredInd = null;

	public PortRequestTypeVO portinInd(Boolean portinInd) {
		this.portinInd = portinInd;
		return this;
	}

	/**
	 * Get portinInd
	 * 
	 * @return portinInd
	 **/
	// @ApiModelProperty(value = "")
	public Boolean isPortinInd() {
		return portinInd;
	}

	public void setPortinInd(Boolean portinInd) {
		this.portinInd = portinInd;
	}

	public PortRequestTypeVO temporaryPhoneNumberRequiredInd(Boolean temporaryPhoneNumberRequiredInd) {
		this.temporaryPhoneNumberRequiredInd = temporaryPhoneNumberRequiredInd;
		return this;
	}

	/**
	 * Get temporaryPhoneNumberRequiredInd
	 * 
	 * @return temporaryPhoneNumberRequiredInd
	 **/
	// @ApiModelProperty(value = "")
	public Boolean isTemporaryPhoneNumberRequiredInd() {
		return temporaryPhoneNumberRequiredInd;
	}

	public void setTemporaryPhoneNumberRequiredInd(Boolean temporaryPhoneNumberRequiredInd) {
		this.temporaryPhoneNumberRequiredInd = temporaryPhoneNumberRequiredInd;
	}

	/*
	 * @Override public boolean equals(java.lang.Object o) { if (this == o) { return
	 * true; } if (o == null || getClass() != o.getClass()) { return false; }
	 * PortRequestType portRequestType = (PortRequestType) o; return
	 * Objects.equals(this.portinInd, portRequestType.portinInd) &&
	 * Objects.equals(this.temporaryPhoneNumberRequiredInd,
	 * portRequestType.temporaryPhoneNumberRequiredInd); }
	 * 
	 * @Override public int hashCode() { return java.util.Objects.hash(portinInd,
	 * temporaryPhoneNumberRequiredInd); }
	 * 
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PortRequestType {\n");

		sb.append("    portinInd: ").append(toIndentedString(portinInd)).append("\n");
		sb.append("    temporaryPhoneNumberRequiredInd: ").append(toIndentedString(temporaryPhoneNumberRequiredInd))
				.append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}

}
