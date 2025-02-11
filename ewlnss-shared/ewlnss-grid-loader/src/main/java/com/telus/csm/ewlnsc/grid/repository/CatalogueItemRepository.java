package com.telus.csm.ewlnsc.grid.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.tangosol.util.Filter;
import com.tangosol.util.ValueExtractor;
import com.tangosol.util.extractor.ReflectionExtractor;
import com.tangosol.util.filter.EqualsFilter;
import com.tangosol.util.filter.InFilter;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;
import com.telus.csm.ewlnsc.grid.domain.ProductCharacteristicDO;

public class CatalogueItemRepository extends DefaultRepository<String, CatalogueItemDO> {
	
	private static Logger LOG = Logger.getLogger(CatalogueItemRepository.class);
	
	
	private static ValueExtractor VE_PRODUCT_CODE =  new ReflectionExtractor("getProductCode");

	@Override
	public void createIndexes() {
		createIndex( "productCode", VE_PRODUCT_CODE );

	}
	
	@SuppressWarnings("unchecked")
	public CatalogueItemDO getCatalogueItemByProductCode(String productCode ) {
		

		long startTime = System.currentTimeMillis();
		
		try {
			createIndexes();

			CatalogueItemDO result = null;
			
			Filter filter = new EqualsFilter( VE_PRODUCT_CODE, productCode );
			
			Set<Map.Entry<String, CatalogueItemDO> > resultSet = getCache().entrySet(filter );
			
			if ( resultSet.isEmpty()==false ) {
				result = resultSet.iterator().next().getValue();
			}
			return result;
			
		} finally {
			LOG.debug( getCacheName() + ": getCataloguteItemByProductCode() timeElapsed(ms):" + (System.currentTimeMillis()- startTime )  );
		}		
		
	}
	@SuppressWarnings("unchecked")
	public Map<String, CatalogueItemDO>  getCatalogueItemsByProductCode(Set<String> catalogueItemIds  ) {
		

		long startTime = System.currentTimeMillis();
		
		try {
			createIndexes(); 
			
		  	Map<String, CatalogueItemDO> result = null;
			
			Filter filter = new InFilter( VE_PRODUCT_CODE, catalogueItemIds );
			
			Set<Map.Entry<String, CatalogueItemDO> > resultSet = getCache().entrySet(filter );
			
			if ( !resultSet.isEmpty() ) {
				result = new HashMap<String, CatalogueItemDO> ();
				Iterator<Entry<String, CatalogueItemDO>> rsIt = resultSet.iterator();
				while (rsIt.hasNext()) {
					CatalogueItemDO catalogueItemDO = rsIt.next().getValue();
					addToResult( result, catalogueItemDO );
				}
			} else { 
				result = Collections.emptyMap();
			}		
			return result;
			
		} finally {
			LOG.debug( getCacheName() + ": getCatalogueItemsByProductCodes() timeElapsed(ms):" + (System.currentTimeMillis()- startTime )  );
		}		
		
	}	
	/**
	 * 
	 * @param result
	 * @param catalogueItemDO
	 */
	private void addToResult(Map<String, CatalogueItemDO> result,	CatalogueItemDO catalogueItemDO) {
		String catalogueItemId = catalogueItemDO.getProductCode();  
		result.put( catalogueItemId, catalogueItemDO );
	}
}