package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.List;

import com.telus.csm.ess.common.domain.DescriptionVO;

public class FFHEquipmentTypeVO implements Serializable{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  private ProductComponentVO productCatalogueIdentifier = null;

	  private String materialItemCode = null;

	  private String deliveryMethodType = null;

	  private EquipmentAcquisitionTypeVO acquisitionType = null;
	  
	  private String action;
	  
	  private DescriptionVO description;
		
		private PriceVO price;
	  
	  //NWLN-10222
	  private PaymentOptionVO paymentOption = null;
	  
	  //FIFA child cart item level equipment characteristics
	  private List<CharacteristicVO> characteristics = null; 


	  /**
	   * Get productCatalogueIdentifier
	   * @return productCatalogueIdentifier
	  **/

	  public ProductComponentVO getProductCatalogueIdentifier() {
	    return productCatalogueIdentifier;
	  }

	  public void setProductCatalogueIdentifier(ProductComponentVO productCatalogueIdentifier) {
	    this.productCatalogueIdentifier = productCatalogueIdentifier;
	  }

	  /**
	   * Get materialItemCode
	   * @return materialItemCode
	  **/


	  public String getMaterialItemCode() {
	    return materialItemCode;
	  }

	  public void setMaterialItemCode(String materialItemCode) {
	    this.materialItemCode = materialItemCode;
	  }

	  /**
	   * One of INSTALLER/INSTORE/SHIPPED
	   * @return deliveryMethodType
	  **/

	  public String getDeliveryMethodType() {
	    return deliveryMethodType;
	  }

	  public void setDeliveryMethodType(String deliveryMethodType) {
	    this.deliveryMethodType = deliveryMethodType;
	  }
	  
	  /**
	   * Get acquisitionType
	   * @return acquisitionType
	  **/
	  public EquipmentAcquisitionTypeVO getAcquisitionType() {
	    return acquisitionType;
	  }

	  public void setAcquisitionType(EquipmentAcquisitionTypeVO acquisitionType) {
	    this.acquisitionType = acquisitionType;
	  }
	  
	  /**
	   * Get paymentOptionType
	   * @return paymentOptionType
	  **/
	  public PaymentOptionVO getPaymentOption() {
	    return paymentOption;
	  }

	  public void setPaymentOption(PaymentOptionVO paymentOption) {
	    this.paymentOption = paymentOption;
	  }

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public DescriptionVO getDescription() {
		return description;
	}

	public void setDescription(DescriptionVO description) {
		this.description = description;
	}

	public PriceVO getPrice() {
		return price;
	}

	public void setPrice(PriceVO price) {
		this.price = price;
	}

	public List<CharacteristicVO> getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(List<CharacteristicVO> characteristics) {
		this.characteristics = characteristics;
	}

}
