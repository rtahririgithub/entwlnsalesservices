package com.telus.csm.ewlnsc.helper;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;


import com.telus.csm.ewlnsc.domain.GetCatalogueHierarchyResponseVO;
import com.telus.csm.ewlnsc.domain.GetProductCatalogueHierarchyRequestVO;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemHierarchyDO;
import com.telus.csm.ewlnsc.grid.domain.EquipmentCatalogueItemDO;
import com.telus.csm.ewlnsc.grid.repository.CatalogueItemHierarchyRepository;
import com.telus.csm.ewlnsc.grid.repository.RepositoryFactory;

public class CommonWLNGridHelper {
	
	private static Logger LOG = Logger.getLogger(CommonWLNGridHelper.class);
	
	public static CommonWLNGridHelper getInstance() {
		return new CommonWLNGridHelper();
	}
	
	public CommonWLNGridHelper() {
		
	}
	
	public GetCatalogueHierarchyResponseVO getProductCatalogueHierarchy( GetProductCatalogueHierarchyRequestVO request ) {
		
		long startTime = System.currentTimeMillis();
		try {
			return new HierarchyBuilder().getProductCatalogueHierarchy(request);
		} finally {
			LOG.debug( "getProductCatalogueHierarchy timeElapsed(ms):" + (System.currentTimeMillis()- startTime )  );
		}
	}

	public CatalogueItemDO getCatalogueItemById( String catalogueItemId) {
		return RepositoryFactory.getInstance().getCatalogueItemRepository().get(catalogueItemId);
	}
	
	public CatalogueItemDO getCatalogueItemByExternalId( String externalId) {
		return RepositoryFactory.getInstance().getCatalogueItemRepository().getCatalogueItemByProductCode( externalId);
	}

	public Map<String, CatalogueItemDO> getCatalogueItemsByExternalId(Set<String> externalIds) {
		return RepositoryFactory.getInstance().getCatalogueItemRepository().getCatalogueItemsByProductCode( externalIds);
	}
	
	public EquipmentCatalogueItemDO getEquipmentByCatalogueId( String catalogueItemId) {
		
		return RepositoryFactory.getInstance().getEquipmentRepository().get(catalogueItemId);
	}
	
	public EquipmentCatalogueItemDO getEquipmentByProductCode( String productCode) {
		
		return RepositoryFactory.getInstance().getEquipmentRepository().getEquipmentByProductCode(productCode);
	}  
		
	public List<CatalogueItemHierarchyDO> getCategoryItemDetails(String templateId, String productCatalogId, String parentCatalogId) {
		CatalogueItemHierarchyRepository repo = RepositoryFactory.getInstance().getHierarchyRepository();
		Set<Map.Entry<String, CatalogueItemHierarchyDO>> results = repo.queryProductComponentNode(templateId, productCatalogId, parentCatalogId);
		List<CatalogueItemHierarchyDO> productCatalogItems = new ArrayList<CatalogueItemHierarchyDO>();
		if (results != null) {
			for (Map.Entry<String, CatalogueItemHierarchyDO> productItem : results) {
				if (productItem != null){
					productCatalogItems.add(productItem.getValue());
				}
			}
		}
		return productCatalogItems;
		
		
	}				
}
