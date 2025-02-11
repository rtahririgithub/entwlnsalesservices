
package com.telus.csm.ewlnsc.domain.product;

public class MessageTypeVO {

  //@SerializedName("messageTxt")
  private String messageTxt = null;
  
  //@SerializedName("localeCd")
  private String localeCd = null;
  
  public MessageTypeVO messageTxt(String messageTxt) {
    this.messageTxt = messageTxt;
    return this;
  }

  
  /**
  * Get messageTxt
  * @return messageTxt
  **/
  //@ApiModelProperty(value = "")
  public String getMessageTxt() {
    return messageTxt;
  }
  public void setMessageTxt(String messageTxt) {
    this.messageTxt = messageTxt;
  }
  
  public MessageTypeVO localeCd(String localeCd) {
    this.localeCd = localeCd;
    return this;
  }

  
  /**
  * Get localeCd
  * @return localeCd
  **/
  //@ApiModelProperty(value = "")
  public String getLocaleCd() {
    return localeCd;
  }
  public void setLocaleCd(String localeCd) {
    this.localeCd = localeCd;
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
    MessageType messageType = (MessageType) o;
    return Objects.equals(this.messageTxt, messageType.messageTxt) &&
        Objects.equals(this.localeCd, messageType.localeCd);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(messageTxt, localeCd);
  }
  
  */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MessageType {\n");
    
    sb.append("    messageTxt: ").append(toIndentedString(messageTxt)).append("\n");
    sb.append("    localeCd: ").append(toIndentedString(localeCd)).append("\n");
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



