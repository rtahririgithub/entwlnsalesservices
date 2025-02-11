package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.telus.csm.ewlnsc.grid.domain.ProductCharacteristicDO;

public class ProductCatalogueItemVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String catalogueItemId;
	private String productCode;
	private String catalogueItemType;
	private String componentServiceType;
	private String internalName;
	private Date effStartDt;
	private Date effEndDt;
	private String description;
	private String name;
	private int minimumQuatity;
	private int maximumQuatity;
	
	private List<ProductCharacteristicDO> characteristics;
	
	private Map<String, ProductCatalogueItemVO> components = new HashMap<String, ProductCatalogueItemVO>();
	
	
	public ProductCatalogueItemVO() {
		
	}
	
	public String getCatalogueItemId() {
		return catalogueItemId;
	}

	public void setCatalogueItemId(String catalogueItemId) {
		this.catalogueItemId = catalogueItemId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getCatalogueItemType() {
		return catalogueItemType;
	}

	public void setCatalogueItemType(String catalogueItemType) {
		this.catalogueItemType = catalogueItemType;
	}

	public String getComponentServiceType() {
		return componentServiceType;
	}

	public void setComponentServiceType(String componentServiceType) {
		this.componentServiceType = componentServiceType;
	}

	public String getInternalName() {
		return internalName;
	}

	public void setInternalName(String internalName) {
		this.internalName = internalName;
	}

	public Date getEffStartDt() {
		return effStartDt;
	}

	public void setEffStartDt(Date effStartDt) {
		this.effStartDt = effStartDt;
	}

	public Date getEffEndDt() {
		return effEndDt;
	}

	public void setEffEndDt(Date effEndDt) {
		this.effEndDt = effEndDt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProductCatalogueItemVO> getComponents() {
		
		return new ArrayList<ProductCatalogueItemVO>(components.values() );
	}

	public int getMinimumQuatity() {
		return minimumQuatity;
	}

	public void setMinimumQuatity(int minimumQuatity) {
		this.minimumQuatity = minimumQuatity;
	}

	public int getMaximumQuatity() {
		return maximumQuatity;
	}

	public void setMaximumQuatity(int maximumQuatity) {
		this.maximumQuatity = maximumQuatity;
	}

	public List<ProductCharacteristicDO> getCharacteristics() {
		if (characteristics==null) {
			characteristics = new ArrayList<ProductCharacteristicDO>();
		}
		return characteristics;
	}

	public void setCharacteristics(List<ProductCharacteristicDO> characteristics) {
		this.characteristics = characteristics;
	}

	public ProductCatalogueItemVO newComponentById( String childCatalgueId) {
		
		ProductCatalogueItemVO childComponent = new ProductCatalogueItemVO();
		childComponent.setCatalogueItemId(childCatalgueId);
		
		getComponents().add( childComponent);
		return childComponent;
	}
	

	///facilities for HierarchyBuilder
	private String nodePath;
	
	public String getNodePath() {
		return nodePath;
	}

	public void setNodePath(String nodePath) {
		this.nodePath = nodePath;
	}

	public ProductCatalogueItemVO addChild(String childCatalogueId ) {
		
		ProductCatalogueItemVO childNode = components.get( childCatalogueId );
		
		if ( childNode==null) {
			childNode = new ProductCatalogueItemVO();
			childNode.nodePath = this.nodePath + "/" + childCatalogueId ;
			childNode.catalogueItemId = childCatalogueId;
			components.put( childCatalogueId, childNode );
		}
		
		return childNode;
	}
	

}
