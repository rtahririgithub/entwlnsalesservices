package com.telus.csm.ewlnsc.delegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.telus.csm.ewlnsc.domain.GroupCategoryVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemGroupCategoryVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemVO;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;
import com.telus.csm.ewlnsc.helper.CommonWLNGridHelperV2;
import com.telus.csm.ewlnsc.util.Constants;

public class ProductGroupCategorizationDelegateV2 extends SpringBeanAutowiringSupport {
	
	private static Logger logger = Logger.getLogger(ProductGroupCategorizationDelegateV2.class);

	private final String GROUP_TYPE_OTHER = "OTHER";
	
	/**
	 * groupProductItems
	 * @param List<ProductItem> productItems
	 * @return List<ProductItemGroupCategory>
	 */
	public List<ProductItemGroupCategoryVO> groupProductItems(List<ProductItemVO> productItems) {
		List<ProductItemGroupCategoryVO> productItemGroupCategories = new ArrayList<ProductItemGroupCategoryVO>();

		if (CollectionUtils.isEmpty(productItems)) {
			// return empty Grouping
			return productItemGroupCategories;
		}

		Map<String,List<ProductItemVO>> groupProductItemsMap = new HashMap<String,List<ProductItemVO>>();
		List<ProductItemVO> otherGroupProdItems = new ArrayList<ProductItemVO>();
		List<ProductItemVO> groupProdItems = new ArrayList<ProductItemVO>();

		for (ProductItemVO prodItem : productItems) {
			/*
			// AddOns or Feature will have a different Group based on its Parent Product Catalogue
			if (isAddOnOrFeature(prodItem)) {			
				if (prodItem.getProductItemIdentifier() == null) {
					otherGroupProdItems.add(prodItem);
					continue;
				}
				String parentProductCatalogueId = prodItem.getProductItemIdentifier().getParentProductCatalogueId();
				
				// Gary 2018-09-13
				
				String serviceTypeCd = prodItem.getServiceTypeCode();
				String mapKey = this.transformMapKey(serviceTypeCd, parentProductCatalogueId);
				
				groupProdItems = groupProductItemsMap.get(mapKey);
				if (groupProdItems == null) {
					groupProdItems = new ArrayList<ProductItemVO>();
					groupProductItemsMap.put(mapKey, groupProdItems);
				}
				groupProdItems.add(prodItem);
			} else {
				otherGroupProdItems.add(prodItem);
			}
			*/
			
			if (prodItem.getProductItemIdentifier() == null) {
				otherGroupProdItems.add(prodItem);
				continue;
			}
			String parentProductCatalogueId = prodItem.getProductItemIdentifier().getParentProductCatalogueId();
			
			// Gary 2018-09-13
			
			String serviceTypeCd = prodItem.getServiceTypeCode();
			String mapKey = this.transformMapKey(serviceTypeCd, parentProductCatalogueId);
			if (mapKey != null) {
				groupProdItems = groupProductItemsMap.get(mapKey);
				if (groupProdItems == null) {
					groupProdItems = new ArrayList<ProductItemVO>();
					groupProductItemsMap.put(mapKey, groupProdItems);
				}
			}
			groupProdItems.add(prodItem);
			
		}

		

		// AddOn Groups
		Iterator<String> it = groupProductItemsMap.keySet().iterator();
		while (it.hasNext()) {
			String parentProductCatalogueId = it.next();
			groupProdItems = groupProductItemsMap.get(parentProductCatalogueId);
			String groupType = retrieveGroupTypeName(parentProductCatalogueId); // Gary 2018-09-13
			
			if (groupType.equals(GROUP_TYPE_OTHER)){
				otherGroupProdItems.addAll(groupProductItemsMap.get(parentProductCatalogueId));
			}else {
				ProductItemGroupCategoryVO prodItemGrpCategory = new ProductItemGroupCategoryVO();
				productItemGroupCategories.add(prodItemGrpCategory);
				GroupCategoryVO groupCategory = new GroupCategoryVO();
				groupCategory.setGroupTypeCd(groupType);
				prodItemGrpCategory.setGroupCategory(groupCategory);
				prodItemGrpCategory.setProductItems(groupProdItems);
			}
			
		}
		
		if (CollectionUtils.isNotEmpty(otherGroupProdItems)) {
			ProductItemGroupCategoryVO prodItemGrpCategory = new ProductItemGroupCategoryVO();
			productItemGroupCategories.add(prodItemGrpCategory);
			GroupCategoryVO groupCategory = new GroupCategoryVO();
			groupCategory.setGroupTypeCd(GROUP_TYPE_OTHER);
			prodItemGrpCategory.setGroupCategory(groupCategory);
			prodItemGrpCategory.setProductItems(otherGroupProdItems);
		}

		return productItemGroupCategories;
	}
	
	
	/*************************************************/
	/* transform key based on service type           */
	/*************************************************/
	private String transformMapKey(String serviceTypeCd, String parentProductCatalogueId){
		
		String mapKey = GROUP_TYPE_OTHER;
		
		if (Constants.serviceTypeCdMapping.containsKey(serviceTypeCd)) 
			mapKey =  serviceTypeCd;
		else 
			mapKey = parentProductCatalogueId;
		
		//logger.debug("transformMapKey serviceTypeCd=(" + serviceTypeCd + "), parentProductCatalogueId=(" + parentProductCatalogueId + ") result -> " + mapKey);
		return mapKey;
	}

	/*************************************************/
	/* transform key based on service type           */
	/*************************************************/
	private String retrieveGroupTypeName(String productCatalogueId){
		
		String groupTypeName = GROUP_TYPE_OTHER;
		
		if (Constants.serviceTypeCdMapping.containsKey(productCatalogueId)){
			groupTypeName = Constants.serviceTypeCdMapping.get(productCatalogueId);
		} else {
			groupTypeName = retrieveGroupType(productCatalogueId);
		}

		logger.debug("retrieveGroupTypeName(" + productCatalogueId +  ") result -> " + groupTypeName);
		
		return groupTypeName;
	}
	/**
	 * retrieveGroupTye
	 * @param productCatalogueId
	 * @return String
	 */
	private String retrieveGroupType(String productCatalogueId) {
		
		 
		CatalogueItemDO catalogueItemDO = this.getCatalogItemById(productCatalogueId);
		String result = "";
		
		/*
		if (catalogueItemDO.getName() != null && catalogueItemDO.getName().length() > 0) {
			//Gary 2018-09-20
			if (Constants.serviceTypeCdComponentNameList.contains(catalogueItemDO.getComponentServiceType())){
				result = catalogueItemDO.getName(); 
			} else {
				result = "OTHER";
			}
			
		} else {
			result = productCatalogueId;
		}
		*/
		if ( StringUtils.isNotBlank( catalogueItemDO.getName()) )  {
			result = catalogueItemDO.getName(); 
		} else {
			result = "OTHER";
		}
		
		logger.debug("retrieveGroupType(" + productCatalogueId +  ") result -> " + result);
		return result;
	}

	/**
	 * getCatalogItemById
	 * @param String productCatalogueIdentifier
	 * @return CatalogueItemDO
	 */
	private CatalogueItemDO getCatalogItemById(String productCatalogueIdentifier){

		CommonWLNGridHelperV2 gridHelper = CommonWLNGridHelperV2.getInstance();
		CatalogueItemDO result = gridHelper.getCatalogueItemById(productCatalogueIdentifier);

		//TODO temp code
		if (result == null) {
			result = new CatalogueItemDO();
			result.setCatalogueItemId(productCatalogueIdentifier);
		}

		return result;
	}
	
	
	private boolean isAddOnOrFeature(ProductItemVO prodItem) {
		if (prodItem.getMarketOfferClassification() == null) {
			return false;
		}
		if (prodItem.getMarketOfferClassification().isAddOnInd() != null &&
			prodItem.getMarketOfferClassification().isAddOnInd()) {
			return true;
		}
		if (prodItem.getMarketOfferClassification().isCallingFeatureInd() != null &&
			prodItem.getMarketOfferClassification().isCallingFeatureInd()) {
			return true;
		}
		return false;
	}

}
