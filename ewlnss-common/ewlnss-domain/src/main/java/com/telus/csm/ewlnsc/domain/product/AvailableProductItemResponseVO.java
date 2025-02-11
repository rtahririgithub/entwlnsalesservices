package com.telus.csm.ewlnsc.domain.product;

import java.util.ArrayList;
import java.util.List;

public class AvailableProductItemResponseVO {

	private List<AvailableInternetProductItemVO> internetProductItems = null;

	private List<AvailableTelevisionProductItemVO> televisionProductItems = null;

	private List<AvailableHomePhoneProductItemVO> homePhoneProductItems = null;

	private List<AvailableAccessoryProductItemVO> accessoryProductItems = null;

	private List<AvailableHomeSecurityProductItemVO> homeSecurityProductItems = null; //FIFA SHS
	
	public AvailableProductItemResponseVO internetProductItems(
			List<AvailableInternetProductItemVO> internetProductItems) {
		this.internetProductItems = internetProductItems;
		return this;
	}

	public AvailableProductItemResponseVO addInternetProductItemsItem(
			AvailableInternetProductItemVO internetProductItemsItem) {

		if (this.internetProductItems == null) {
			this.internetProductItems = new ArrayList<AvailableInternetProductItemVO>();
		}

		this.internetProductItems.add(internetProductItemsItem);
		return this;
	}

	/**
	 * Get internetProductItems
	 * 
	 * @return internetProductItems
	 **/
	public List<AvailableInternetProductItemVO> getInternetProductItems() {

		if (this.internetProductItems == null) {
			this.internetProductItems = new ArrayList<AvailableInternetProductItemVO>();
		}
		return internetProductItems;
	}

	public void setInternetProductItems(List<AvailableInternetProductItemVO> internetProductItems) {
		this.internetProductItems = internetProductItems;
	}

	public AvailableProductItemResponseVO televisionProductItems(
			List<AvailableTelevisionProductItemVO> televisionProductItems) {
		this.televisionProductItems = televisionProductItems;
		return this;
	}

	public AvailableProductItemResponseVO addTelevisionProductItemsItem(
			AvailableTelevisionProductItemVO televisionProductItemsItem) {

		if (this.televisionProductItems == null) {
			this.televisionProductItems = new ArrayList<AvailableTelevisionProductItemVO>();
		}

		this.televisionProductItems.add(televisionProductItemsItem);
		return this;
	}

	/**
	 * Get televisionProductItems
	 * 
	 * @return televisionProductItems
	 **/
	public List<AvailableTelevisionProductItemVO> getTelevisionProductItems() {
		if (this.televisionProductItems == null) {
			this.televisionProductItems = new ArrayList<AvailableTelevisionProductItemVO>();
		}
		return televisionProductItems;
	}

	public void setTelevisionProductItems(List<AvailableTelevisionProductItemVO> televisionProductItems) {
		this.televisionProductItems = televisionProductItems;
	}

	public AvailableProductItemResponseVO homePhoneProductItems(
			List<AvailableHomePhoneProductItemVO> homePhoneProductItems) {
		this.homePhoneProductItems = homePhoneProductItems;
		return this;
	}

	public AvailableProductItemResponseVO addHomePhoneProductItemsItem(
			AvailableHomePhoneProductItemVO homePhoneProductItemsItem) {

		if (this.homePhoneProductItems == null) {
			this.homePhoneProductItems = new ArrayList<AvailableHomePhoneProductItemVO>();
		}

		this.homePhoneProductItems.add(homePhoneProductItemsItem);
		return this;
	}

	/**
	 * Get homePhoneProductItems
	 * 
	 * @return homePhoneProductItems
	 **/
	public List<AvailableHomePhoneProductItemVO> getHomePhoneProductItems() {
		if (this.homePhoneProductItems == null) {
			this.homePhoneProductItems = new ArrayList<AvailableHomePhoneProductItemVO>();
		}
		return homePhoneProductItems;
	}

	public void setHomePhoneProductItems(List<AvailableHomePhoneProductItemVO> homePhoneProductItems) {
		this.homePhoneProductItems = homePhoneProductItems;
	}

	public AvailableProductItemResponseVO accessoryProductItems(
			List<AvailableAccessoryProductItemVO> accessoryProductItems) {
		this.accessoryProductItems = accessoryProductItems;
		return this;
	}

	public AvailableProductItemResponseVO addAccessoryProductItemsItem(
			AvailableAccessoryProductItemVO accessoryProductItemsItem) {

		if (this.accessoryProductItems == null) {
			this.accessoryProductItems = new ArrayList<AvailableAccessoryProductItemVO>();
		}

		this.accessoryProductItems.add(accessoryProductItemsItem);
		return this;
	}

	/**
	 * Get accessoryProductItems
	 * 
	 * @return accessoryProductItems
	 **/
	public List<AvailableAccessoryProductItemVO> getAccessoryProductItems() {
		if (this.accessoryProductItems == null) {
			this.accessoryProductItems = new ArrayList<AvailableAccessoryProductItemVO>();
		}
		return accessoryProductItems;
	}

	public void setAccessoryProductItems(List<AvailableAccessoryProductItemVO> accessoryProductItems) {
		this.accessoryProductItems = accessoryProductItems;
	}


	public List<AvailableHomeSecurityProductItemVO> getHomeSecurityProductItems() {
		if (this.homeSecurityProductItems == null) {
			this.homeSecurityProductItems = new ArrayList<AvailableHomeSecurityProductItemVO>();
		}
		return homeSecurityProductItems;
	}

	public void setHomeSecurityProductItems(List<AvailableHomeSecurityProductItemVO> homeSecurityProductItems) {
		this.homeSecurityProductItems = homeSecurityProductItems;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class AvailableProductItemResponse {\n");

		sb.append("    internetProductItems: ").append(toIndentedString(internetProductItems)).append("\n");
		sb.append("    televisionProductItems: ").append(toIndentedString(televisionProductItems)).append("\n");
		sb.append("    homePhoneProductItems: ").append(toIndentedString(homePhoneProductItems)).append("\n");
		sb.append("    accessoryProductItems: ").append(toIndentedString(accessoryProductItems)).append("\n");
		sb.append("    homeSecurityProductItems: ").append(toIndentedString(homeSecurityProductItems)).append("\n");
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
