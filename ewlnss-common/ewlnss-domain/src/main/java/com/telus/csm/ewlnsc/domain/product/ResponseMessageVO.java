package com.telus.csm.ewlnsc.domain.product;

import java.util.ArrayList;
import java.util.List;

public class ResponseMessageVO {

  //@SerializedName("errorCode")
  private String errorCode = null;
  
  //@SerializedName("messageTypeCd")
  private String messageTypeCd = null;
  
  //@SerializedName("messages")
  private List<MessageTypeVO> messages = null;
  
  //@SerializedName("contextDataAttributes")
  private List<ContextAttributeVO> contextDataAttributes = null;
  
  public ResponseMessageVO errorCode(String errorCode) {
    this.errorCode = errorCode;
    return this;
  }

  
  /**
  * Get errorCode
  * @return errorCode
  **/
  //@ApiModelProperty(value = "")
  public String getErrorCode() {
    return errorCode;
  }
  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }
  
  public ResponseMessageVO messageTypeCd(String messageTypeCd) {
    this.messageTypeCd = messageTypeCd;
    return this;
  }

  
  /**
  * Get messageTypeCd
  * @return messageTypeCd
  **/
  //@ApiModelProperty(value = "")
  public String getMessageTypeCd() {
    return messageTypeCd;
  }
  public void setMessageTypeCd(String messageTypeCd) {
    this.messageTypeCd = messageTypeCd;
  }
  
  public ResponseMessageVO messages(List<MessageTypeVO> messages) {
    this.messages = messages;
    return this;
  }

  public ResponseMessageVO addMessagesItem(MessageTypeVO messagesItem) {
    
    if (this.messages == null) {
      this.messages = new ArrayList<MessageTypeVO>();
    }
    
    this.messages.add(messagesItem);
    return this;
  }
  
  /**
  * Get messages
  * @return messages
  **/
  //@ApiModelProperty(value = "")
  public List<MessageTypeVO> getMessages() {
    return messages;
  }
  public void setMessages(List<MessageTypeVO> messages) {
    this.messages = messages;
  }
  
  public ResponseMessageVO contextDataAttributes(List<ContextAttributeVO> contextDataAttributes) {
    this.contextDataAttributes = contextDataAttributes;
    return this;
  }

  public ResponseMessageVO addContextDataAttributesItem(ContextAttributeVO contextDataAttributesItem) {
    
    if (this.contextDataAttributes == null) {
      this.contextDataAttributes = new ArrayList<ContextAttributeVO>();
    }
    
    this.contextDataAttributes.add(contextDataAttributesItem);
    return this;
  }
  
  /**
  * Get contextDataAttributes
  * @return contextDataAttributes
  **/
  //@ApiModelProperty(value = "")
  public List<ContextAttributeVO> getContextDataAttributes() {
    return contextDataAttributes;
  }
  public void setContextDataAttributes(List<ContextAttributeVO> contextDataAttributes) {
    this.contextDataAttributes = contextDataAttributes;
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
    ResponseMessage responseMessage = (ResponseMessage) o;
    return Objects.equals(this.errorCode, responseMessage.errorCode) &&
        Objects.equals(this.messageTypeCd, responseMessage.messageTypeCd) &&
        Objects.equals(this.messages, responseMessage.messages) &&
        Objects.equals(this.contextDataAttributes, responseMessage.contextDataAttributes);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(errorCode, messageTypeCd, messages, contextDataAttributes);
  }
  
  */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseMessage {\n");
    
    sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("\n");
    sb.append("    messageTypeCd: ").append(toIndentedString(messageTypeCd)).append("\n");
    sb.append("    messages: ").append(toIndentedString(messages)).append("\n");
    sb.append("    contextDataAttributes: ").append(toIndentedString(contextDataAttributes)).append("\n");
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



