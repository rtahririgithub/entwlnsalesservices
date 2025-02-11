package com.telus.csm.ewlnsc.grid.repository;

import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.tangosol.util.Filter;
import com.tangosol.util.ValueExtractor;
import com.tangosol.util.extractor.ReflectionExtractor;
import com.tangosol.util.filter.EqualsFilter;
import com.telus.csm.ewlnsc.grid.domain.EquipmentCatalogueItemDO;

public class EquipmentCatalogueItemRepository extends DefaultRepository<String, EquipmentCatalogueItemDO> {

	private static Logger LOG = Logger.getLogger(EquipmentCatalogueItemRepository.class);
	
	
	private static ValueExtractor VE_PRODUCT_CODE =  new ReflectionExtractor("getProductCode");

	@Override
	public void createIndexes() {
		
		createIndex( "productCode", VE_PRODUCT_CODE );
	}
	
	@SuppressWarnings("unchecked")
	public EquipmentCatalogueItemDO getEquipmentByProductCode(String productCode ) {
		

		long startTime = System.currentTimeMillis();
		
		try {
			createIndexes();

			EquipmentCatalogueItemDO result = null;
			
			Filter filter = new EqualsFilter( VE_PRODUCT_CODE, productCode );
			
			Set<Map.Entry<String, EquipmentCatalogueItemDO> > resultSet = getCache().entrySet(filter );
			
			if ( resultSet.isEmpty()==false ) {
				result = resultSet.iterator().next().getValue();
			}
			return result;
			
		} finally {
			LOG.debug( getCacheName() + ": getEquipmentByProductCode() timeElapsed(ms):" + (System.currentTimeMillis()- startTime )  );
		}		
		
	}
}