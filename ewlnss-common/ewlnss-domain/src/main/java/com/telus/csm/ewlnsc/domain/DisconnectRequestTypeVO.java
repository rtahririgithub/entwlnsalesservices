package com.telus.csm.ewlnsc.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DisconnectRequestTypeVO {


private List<String> productServiceType = null;

private OriginalCarrierInfoTypeVO originalCarrierInfo = null;

private  Date requestedDisconnectDate = null;

public Date getRequestedDisconnectDate() {
	return requestedDisconnectDate;
}

public void setRequestedDisconnectDate(Date requestedDisconnectDate) {
	this.requestedDisconnectDate = requestedDisconnectDate;
}

public DisconnectRequestTypeVO productServiceType(List<String> productServiceType) {
  this.productServiceType = productServiceType;
  return this;
}

public DisconnectRequestTypeVO addProductServiceTypeItem(String productServiceTypeItem) {
  
  if (this.productServiceType == null) {
    this.productServiceType = new ArrayList<String>();
  }
  
  this.productServiceType.add(productServiceTypeItem);
  return this;
}


public List<String> getProductServiceType() {
  return productServiceType;
}
public void setProductServiceType(List<String> productServiceType) {
  this.productServiceType = productServiceType;
}

public DisconnectRequestTypeVO originalCarrierInfo(OriginalCarrierInfoTypeVO originalCarrierInfo) {
  this.originalCarrierInfo = originalCarrierInfo;
  return this;
}


/**
* Get originalCarrierInfo
* @return originalCarrierInfo
**/
public OriginalCarrierInfoTypeVO getOriginalCarrierInfo() {
  return originalCarrierInfo;
}
public void setOriginalCarrierInfo(OriginalCarrierInfoTypeVO originalCarrierInfo) {
  this.originalCarrierInfo = originalCarrierInfo;
}


@Override
public String toString() {
  StringBuilder sb = new StringBuilder();
  sb.append("class DisconnectRequestType {\n");
  
  sb.append("    productServiceType: ").append(toIndentedString(productServiceType)).append("\n");
  sb.append("    originalCarrierInfo: ").append(toIndentedString(originalCarrierInfo)).append("\n");
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