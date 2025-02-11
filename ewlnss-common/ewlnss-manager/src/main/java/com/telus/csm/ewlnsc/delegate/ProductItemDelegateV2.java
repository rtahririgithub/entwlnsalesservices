package com.telus.csm.ewlnsc.delegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;
import com.telus.csm.ewlnsc.grid.domain.EquipmentCatalogueItemDO;
import com.telus.csm.ewlnsc.helper.CommonWLNGridHelperV2;

public class ProductItemDelegateV2 extends ProductItemDelegate {
	
	private static Logger logger = Logger.getLogger(ProductItemDelegateV2.class);

	@Override
	protected Map<String, EquipmentCatalogueItemDO> retrieveEquipments(List<String> materialItemCodes) {
		
		Map<String, EquipmentCatalogueItemDO> result = new HashMap<String, EquipmentCatalogueItemDO>();
		if (CollectionUtils.isEmpty(materialItemCodes))
			return result;
		
		CommonWLNGridHelperV2 gridHelper = CommonWLNGridHelperV2.getInstance();
		for (String miCode : materialItemCodes) {
			result.put(miCode, gridHelper.getEquipmentByProductCode(miCode));			
		}
		
		return result;
	}

	@Override
	protected Map<String, CatalogueItemDO> retrieveCatalogueItems(List<String> catalogueIdList) {

		logger.debug("total catalogueId size: " + catalogueIdList.size() );
		
		ArrayList<String> missingCatalogueIds = new ArrayList<String>();
		
		CommonWLNGridHelperV2 gridHelper = CommonWLNGridHelperV2.getInstance();

		Map<String,CatalogueItemDO> catalogueItemMap = new HashMap<String,CatalogueItemDO>();
		for (String catalogueId : catalogueIdList) {	
			CatalogueItemDO catalogueItem = gridHelper.getCatalogueItemById(StringUtils.substringBefore(catalogueId, "_"));
			if (catalogueItem == null) {
				missingCatalogueIds.add( catalogueId );
			} else {
				//logger.debug("OfferQualification does contain element: " + catalogueId + ",name=" + catalogueItem.getName() + ",desc=" + catalogueItem.getDescription());
			}
			catalogueItemMap.put(catalogueId, catalogueItem);
		}
		
		if ( missingCatalogueIds.isEmpty()==false ) {
			logger.error("could not retrieve element from OQ for following catalogueIds: " + missingCatalogueIds );
		}
		
		return catalogueItemMap;
	}
	
	
	
	

}
