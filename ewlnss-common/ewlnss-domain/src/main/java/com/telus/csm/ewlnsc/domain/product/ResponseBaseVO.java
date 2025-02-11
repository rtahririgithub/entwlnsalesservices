package com.telus.csm.ewlnsc.domain.product;

import java.util.ArrayList;
import java.util.List;

public class ResponseBaseVO {

  //@SerializedName("responseMessages")
  private List<ResponseMessageVO> responseMessages = null;
  
  public ResponseBaseVO responseMessages(List<ResponseMessageVO> responseMessages) {
    this.responseMessages = responseMessages;
    return this;
  }

  public ResponseBaseVO addResponseMessagesItem(ResponseMessageVO responseMessagesItem) {
    
    if (this.responseMessages == null) {
      this.responseMessages = new ArrayList<ResponseMessageVO>();
    }
    
    this.responseMessages.add(responseMessagesItem);
    return this;
  }
  
  /**
  * Get responseMessages
  * @return responseMessages
  **/
  //@ApiModelProperty(value = "")
  public List<ResponseMessageVO> getResponseMessages() {
    return responseMessages;
  }
  public void setResponseMessages(List<ResponseMessageVO> responseMessages) {
    this.responseMessages = responseMessages;
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
    ResponseBase responseBase = (ResponseBase) o;
    return Objects.equals(this.responseMessages, responseBase.responseMessages);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(responseMessages);
  }
  
  */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseBase {\n");
    
    sb.append("    responseMessages: ").append(toIndentedString(responseMessages)).append("\n");
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



