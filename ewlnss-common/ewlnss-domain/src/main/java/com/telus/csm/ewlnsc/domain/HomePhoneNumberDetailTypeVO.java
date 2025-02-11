package com.telus.csm.ewlnsc.domain;

public class HomePhoneNumberDetailTypeVO {

	  //@SerializedName("telephoneNumber")
	  private TelephoneNumberTypeVO telephoneNumber = null;
	  
	  //@SerializedName("directoryListing")
	  private DirectoryListingTypeVO directoryListing = null;
	  
	  //@SerializedName("portRequestType")
	  private PortRequestTypeVO portRequestType = null;
	  
	  public HomePhoneNumberDetailTypeVO telephoneNumber(TelephoneNumberTypeVO telephoneNumber) {
	    this.telephoneNumber = telephoneNumber;
	    return this;
	  }

	  
	  /**
	  * Get telephoneNumber
	  * @return telephoneNumber
	  **/
	  //@ApiModelProperty(value = "")
	  public TelephoneNumberTypeVO getTelephoneNumber() {
	    return telephoneNumber;
	  }
	  public void setTelephoneNumber(TelephoneNumberTypeVO telephoneNumber) {
	    this.telephoneNumber = telephoneNumber;
	  }
	  
	  public HomePhoneNumberDetailTypeVO directoryListing(DirectoryListingTypeVO directoryListing) {
	    this.directoryListing = directoryListing;
	    return this;
	  }

	  
	  /**
	  * Get directoryListing
	  * @return directoryListing
	  **/
	  //@ApiModelProperty(value = "")
	  public DirectoryListingTypeVO getDirectoryListing() {
	    return directoryListing;
	  }
	  public void setDirectoryListing(DirectoryListingTypeVO directoryListing) {
	    this.directoryListing = directoryListing;
	  }
	  
	  public HomePhoneNumberDetailTypeVO portRequestType(PortRequestTypeVO portRequestType) {
	    this.portRequestType = portRequestType;
	    return this;
	  }

	  
	  /**
	  * Get portRequestType
	  * @return portRequestType
	  **/
	  //@ApiModelProperty(value = "")
	  public PortRequestTypeVO getPortRequestType() {
	    return portRequestType;
	  }
	  public void setPortRequestType(PortRequestTypeVO portRequestType) {
	    this.portRequestType = portRequestType;
	  }
	  
	  /*
	  @Override
	  public boolean equals(java.lang.Object o) {
	    if (this == o) {
	      return true;
	    }
	    if (o == null || getClass() != o.getClass()) {
	      return false;
	    }
	    HomePhoneNumberDetailType homePhoneNumberDetailType = (HomePhoneNumberDetailType) o;
	    return Objects.equals(this.telephoneNumber, homePhoneNumberDetailType.telephoneNumber) &&
	        Objects.equals(this.directoryListing, homePhoneNumberDetailType.directoryListing) &&
	        Objects.equals(this.portRequestType, homePhoneNumberDetailType.portRequestType);
	  }

	  @Override
	  public int hashCode() {
	    return java.util.Objects.hash(telephoneNumber, directoryListing, portRequestType);
	  }
	  
	  */
	  @Override
	  public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("class HomePhoneNumberDetailType {\n");
	    
	    sb.append("    telephoneNumber: ").append(toIndentedString(telephoneNumber)).append("\n");
	    sb.append("    directoryListing: ").append(toIndentedString(directoryListing)).append("\n");
	    sb.append("    portRequestType: ").append(toIndentedString(portRequestType)).append("\n");
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


