package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ProductTypeBase
 */

public abstract class ProductTypeBaseVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected ProductOrderTypeVO productOrderType = null;

	protected String selectedContractTermCd = null;

	protected List<ProductComponentVO> productComponents = null;

	protected List<FFHEquipmentTypeVO> equipments = null;

	protected List<FFHProductPlanAddOnTypeVO> addOns = null;

	protected List<FFHDiscountTypeVO> discounts = null;

	protected List<FFHSweetenerTypeVO> sweeteners = null;

	protected AppointmentDetailTypeVO appointmentDetail = null;

	protected Boolean winback;

	protected List<AdditionalProductItemTypeVO> additionalProductItemList;
	
	private List<CharacteristicVO> characteristics=null; //FIFA product level characteristics
	

	public Boolean getWinback() {
		return winback;
	}

	public void setWinback(Boolean winback) {
		this.winback = winback;
	}

	/**
	 * Get productOrderType
	 * 
	 * @return productOrderType
	 **/

	public ProductOrderTypeVO getProductOrderType() {
		return productOrderType;
	}

	public void setProductOrderType(ProductOrderTypeVO productOrderType) {
		this.productOrderType = productOrderType;
	}

	/**
	 * Get selectedContractTermCd
	 * 
	 * @return selectedContractTermCd
	 **/

	public String getSelectedContractTermCd() {
		return selectedContractTermCd;
	}

	public void setSelectedContractTermCd(String selectedContractTermCd) {
		this.selectedContractTermCd = selectedContractTermCd;
	}

	/**
	 * Get productComponents
	 * 
	 * @return productComponents
	 **/

	public List<ProductComponentVO> getProductComponents() {
		return productComponents;
	}

	public void setProductComponents(List<ProductComponentVO> productComponents) {
		this.productComponents = productComponents;
	}

	/**
	 * Get equipments
	 * 
	 * @return equipments
	 **/

	public List<FFHEquipmentTypeVO> getEquipments() {
		if(equipments == null) {
			return new ArrayList<FFHEquipmentTypeVO>();
		}
		return equipments;
	}

	public void setEquipments(List<FFHEquipmentTypeVO> equipments) {
		this.equipments = equipments;
	}

	/**
	 * Get addOns
	 * 
	 * @return addOns
	 **/

	public List<FFHProductPlanAddOnTypeVO> getAddOns() {
		if(addOns == null) {
			return new ArrayList<FFHProductPlanAddOnTypeVO>();
		}
		
		return addOns;
	}

	public void setAddOns(List<FFHProductPlanAddOnTypeVO> addOns) {
		this.addOns = addOns;
	}

	/**
	 * Get discounts
	 * 
	 * @return discounts
	 **/

	public List<FFHDiscountTypeVO> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<FFHDiscountTypeVO> discounts) {
		this.discounts = discounts;
	}

	/**
	 * Get sweeteners
	 * 
	 * @return sweeteners
	 **/
	public List<FFHSweetenerTypeVO> getSweeteners() {
		return sweeteners;
	}

	public void setSweeteners(List<FFHSweetenerTypeVO> sweeteners) {
		this.sweeteners = sweeteners;
	}

	/**
	 * Get appointmentDetail
	 * 
	 * @return appointmentDetail
	 **/

	public AppointmentDetailTypeVO getAppointmentDetail() {
		return appointmentDetail;
	}

	public void setAppointmentDetail(AppointmentDetailTypeVO appointmentDetail) {
		this.appointmentDetail = appointmentDetail;
	}

	public List<AdditionalProductItemTypeVO> getAdditionalProductItemList() {
		return additionalProductItemList;
	}

	public void setAdditionalProductItemList(List<AdditionalProductItemTypeVO> additionalProductItemList) {
		this.additionalProductItemList = additionalProductItemList;
	}

	public List<CharacteristicVO> getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(List<CharacteristicVO> characteristics) {
		this.characteristics = characteristics;
	}

}
