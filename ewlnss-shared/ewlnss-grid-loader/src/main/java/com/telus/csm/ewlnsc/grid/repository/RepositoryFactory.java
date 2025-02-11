package com.telus.csm.ewlnsc.grid.repository;

import java.util.HashMap;
import java.util.Map;

import com.telus.csm.ewlnsc.grid.domain.Entity;

@SuppressWarnings("rawtypes")
public class RepositoryFactory {
	
	private static RepositoryFactory singleton;
	public static String CACHE_NAME_CATALOGUE_ITEM = "CatalogueItemCache";
	public static String CACHE_NAME_CATALOGUE_HIERARCHY = "CatalogueHierarchyCache";
	public static String CACHE_NAME_EQUIPMENT = "EquipmentCatalogueItemCache";
	public static String CACHE_NAME_PRODUCT_CHARACTERISTIC = "ProductCharacteristicCache";
	
	private Map<String, DefaultRepository<?, Entity<?>>> sideA = new HashMap<String, DefaultRepository<?, Entity<?>>> ();
	private Map<String, DefaultRepository<?, Entity<?>>> sideB = new HashMap<String, DefaultRepository<?, Entity<?>>> ();
	
	
	public static RepositoryFactory getInstance() {
		if (singleton==null) {
			singleton = new RepositoryFactory();
		}
		return singleton;
	}
	
	private RepositoryFactory() {}
	
	private String getCacheSuffix() {
		return WirelineCacheControlRepository.getInstance().getCacheSuffix();
	}

	@SuppressWarnings("unchecked")
	private synchronized <T extends DefaultRepository> T getRepository(Class<? extends DefaultRepository> repoClass, String cacheName, String cacheSuffix ) {
		
		//add following check to avoid creating NamedCache with name without cacheSuffix 
		if (cacheSuffix==null || cacheSuffix.trim().length()==0) {
			
			throw new RuntimeException ("Unable to determine cache side, cacheSuffix: " + cacheSuffix);
		}
		
		Map<String, DefaultRepository<?, Entity<?>>> repositories = "A".equals(cacheSuffix)? sideA : sideB;
		
		DefaultRepository repository = repositories.get( repoClass.getName() );
		if ( repository==null) {
			try {
				repository = repoClass.newInstance();
				repository.initCache(cacheName + cacheSuffix);
				repositories.put(repoClass.getName(), repository);
			} catch (InstantiationException e) {
				throw new RuntimeException(e );
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e );
			}
		}
		return (T) repository;
	}
	

	//================== CatalogueItemRepository ===================
	public CatalogueItemRepository getCatalogueItemRepository(String cacheSuffix ) {
		
		return getRepository( CatalogueItemRepository.class, CACHE_NAME_CATALOGUE_ITEM, cacheSuffix);
	}
	public CatalogueItemRepository getCatalogueItemRepository() {
		
		return getCatalogueItemRepository( getCacheSuffix() );
	}
	

	//================== EquipmentCatalogueItemRepository ===================
	public EquipmentCatalogueItemRepository getEquipmentRepository(String cacheSuffix ) {
		
		return getRepository( EquipmentCatalogueItemRepository.class, CACHE_NAME_EQUIPMENT, cacheSuffix);
	}
	public EquipmentCatalogueItemRepository getEquipmentRepository() {
		
		return getEquipmentRepository( getCacheSuffix() );
	}


	//================== EquipmentCatalogueItemRepository ===================
	public CatalogueItemHierarchyRepository getHierarchyRepository(String cacheSuffix ) {
		
		return getRepository( CatalogueItemHierarchyRepository.class, CACHE_NAME_CATALOGUE_HIERARCHY, cacheSuffix);
	}
	public CatalogueItemHierarchyRepository getHierarchyRepository() {
		
		return getHierarchyRepository( getCacheSuffix() );
	}

	//================== EquipmentCatalogueItemRepository ===================
	public ProductCharacteristicRepository getProductCharacteristicRepository(String cacheSuffix ) {
		
		return getRepository( ProductCharacteristicRepository.class, CACHE_NAME_PRODUCT_CHARACTERISTIC, cacheSuffix);
	}
	public ProductCharacteristicRepository getProductCharacteristicRepository() {
		
		return getProductCharacteristicRepository( getCacheSuffix() );
	}
	
}
