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
import com.tangosol.util.filter.InFilter;
import com.telus.csm.ewlnsc.grid.domain.ProductCharacteristicDO;

public class ProductCharacteristicRepository extends DefaultRepository<String, ProductCharacteristicDO> {
	
	private static Logger LOG = Logger.getLogger(ProductCharacteristicRepository.class);
	
	@Override
	public void createIndexes() {
		createIndex( "catalogueItemId", new ReflectionExtractor("getCatalogueItemId") );
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, List<ProductCharacteristicDO> > getCatalogueItemCharacteristic( Set<String> catalogueItemIds ) {
		
		createIndexes();

		long startTime = System.currentTimeMillis();
		try {
			Map<String, List<ProductCharacteristicDO> > result = null;
			
			ValueExtractor ve = new ReflectionExtractor("getCatalogueItemId");
			Filter filter = new InFilter(ve, catalogueItemIds );
			
			Set<Map.Entry<String, ProductCharacteristicDO> > resultSet = getCache().entrySet(filter );
			
			if ( resultSet.isEmpty()==false ) {
				result = new HashMap<String, List<ProductCharacteristicDO> > ();
				Iterator<Entry<String, ProductCharacteristicDO>> rsIt = resultSet.iterator();
				while( rsIt.hasNext()) {
					ProductCharacteristicDO characteristic = rsIt.next().getValue();
					addToResult( result, characteristic  );
				}
			} else {
				result = Collections.emptyMap();
			}
			
			return result;
			
		} finally {
			LOG.debug( "getCatalogueItemCharacteristic() timeElapsed(ms):" + (System.currentTimeMillis()- startTime )  );
		}		
		
	}

	private void addToResult(Map<String, List<ProductCharacteristicDO>> result,	ProductCharacteristicDO characteristic) {

		String catalogueItemId = characteristic.getCatalogueItemId();
		
		List<ProductCharacteristicDO> list = result.get(catalogueItemId);
		if ( list == null ) {
			list = new ArrayList<ProductCharacteristicDO>();
			result.put( catalogueItemId, list );
			
		} 
		list.add(characteristic);
	}

}