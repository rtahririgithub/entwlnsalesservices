package com.telus.csm.ewlnsc.grid.repository;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.tangosol.util.Filter;
import com.tangosol.util.ValueExtractor;
import com.tangosol.util.extractor.MultiExtractor;
import com.tangosol.util.extractor.ReflectionExtractor;
import com.tangosol.util.filter.EqualsFilter;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemHierarchyDO;

public class CatalogueItemHierarchyRepository extends DefaultRepository<String, CatalogueItemHierarchyDO> {
	
	static Logger LOG = Logger.getLogger(CatalogueItemHierarchyRepository.class);
	

	@Override
	public void createIndexes() {
		
		createIndex( "offerMainComponentIndex", getMainComponetMultExtractor() );
			
		createIndex( "componetPathLeafNodeIndex", getLeafMultExtractor() );
		
		createIndex( "productComponetIndex", getProductComponetMultExtractor() );
	}
	
	private MultiExtractor getMainComponetMultExtractor() {
		
		ValueExtractor[] ve = { 
				new ReflectionExtractor("getOfferId" ), 
				new ReflectionExtractor("getChildProdCatItemId" ), 
				new ReflectionExtractor("getParentProdCatItemTypeId" )};

		MultiExtractor me = new MultiExtractor(ve);
		return me;
	}	
	
	private MultiExtractor getProductComponetMultExtractor() {
		
		ValueExtractor[] ve = { 
				new ReflectionExtractor("getOfferId" ), 
				new ReflectionExtractor("getChildProdCatItemId" ), 
				new ReflectionExtractor("getParentProdCatItemId" )};

		MultiExtractor me = new MultiExtractor(ve);
		return me;
	}	

	private MultiExtractor getLeafMultExtractor() {
		
		ValueExtractor[] ve = { 
				new ReflectionExtractor("getMainComponentPath" ), 
				new ReflectionExtractor("getParentProdCatItemId" ), 
				new ReflectionExtractor("getChildProdCatItemId" )};

		MultiExtractor me = new MultiExtractor(ve);
		return me;
	}
	

	@SuppressWarnings("unchecked")
	public Set<Map.Entry<String, CatalogueItemHierarchyDO>> queryMainComponentNode( String templateId, String childId) {
		
		long startTime = System.currentTimeMillis();
		
		try {
			
			MultiExtractor me = getMainComponetMultExtractor();
			createIndex( "offerMainComponentIndex", me );
								
			Filter filter = new EqualsFilter(me, Arrays.asList( templateId, childId, "SPC" ) );
					
			return getCache().entrySet(filter);	
			
		} finally {
			LOG.debug( getCacheName() + ": queryMainComponentNode() timeElapsed(ms):" + (System.currentTimeMillis()- startTime )  );
		}
	}

	@SuppressWarnings("unchecked")
	public Set<Map.Entry<String, CatalogueItemHierarchyDO>> queryProductComponentNode( String templateId, String productCatalog, String parentCatalogId) {
		
		long startTime = System.currentTimeMillis();
		
		try {
			
			MultiExtractor me = getProductComponetMultExtractor();
			createIndex( "productComponetIndex", me );
			
			Filter filter = new EqualsFilter(me, Arrays.asList(templateId,  productCatalog, parentCatalogId) );
					
			return getCache().entrySet(filter);	
			
		} finally {
			LOG.debug( getCacheName() + ": queryProductComponentNode() timeElapsed(ms):" + (System.currentTimeMillis()- startTime )  );
		}
	}
	
	@SuppressWarnings("unchecked")
	public Set<Map.Entry<String, CatalogueItemHierarchyDO>>  queryHierarchyLeafNode( String componentPath, String parentId, String childId) {
		
		long startTime = System.currentTimeMillis();
		
		try {
			
			MultiExtractor me = getLeafMultExtractor();
			createIndex( "componetPathLeafNodeIndex", me );

			Filter filter = new EqualsFilter(me, Arrays.asList( componentPath, parentId, childId ) );
			
			return getCache().entrySet(filter);	
			
		} finally {
			LOG.debug( getCacheName() + ": queryHierarchyLeafNode() timeElapsed(ms):" + (System.currentTimeMillis()- startTime )  );
		}
	}

}