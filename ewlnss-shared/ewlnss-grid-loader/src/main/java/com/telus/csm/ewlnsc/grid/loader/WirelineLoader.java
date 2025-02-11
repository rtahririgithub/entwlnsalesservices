package com.telus.csm.ewlnsc.grid.loader;

import java.util.Date;

import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.grid.repository.RepositoryFactory;
import com.telus.csm.ewlnsc.grid.repository.WirelineCacheControlRepository;

public class WirelineLoader extends Loader {
	
	protected static final Logger LOGGER = Logger.getLogger(WirelineLoader.class);
		
	public static void main(String[] args) throws Throwable {
		String filePath = "../";
		boolean hasHeader = false;
		int putSize = MAX_PUT_SIZE;
		int waitTime = WAIT_TIME;
		int retryCnt = RETRY_COUNT;

		if( args != null ) {
			if( args.length > 0 ) {
				filePath = args[0];
			}
			if( args.length > 1 ) {
				hasHeader = Boolean.valueOf(args[1]);
			}
			if( args.length > 2 ) {
				putSize = Integer.parseInt(args[2]);
			}
			if( args.length > 3 ) {
				waitTime = Integer.parseInt(args[3]);
			}
			if( args.length > 4 ) {
				retryCnt = Integer.parseInt(args[4]);
			}
			
		}
		
		final WirelineLoader loader = new WirelineLoader(filePath, hasHeader, putSize, waitTime, retryCnt);
		System.exit(loader.execute());
	}
	
	public WirelineLoader(String filePath, boolean hasHeader, int putSize, int waitTime) {
		super(filePath, hasHeader, putSize, waitTime);
	}

	public WirelineLoader(String filePath, boolean hasHeader) {
		super(filePath, hasHeader);
	}

	public WirelineLoader(String filePath, boolean hasHeader, int putSize, int waitTime, int retryCnt) {
		super(filePath, hasHeader, putSize, waitTime, retryCnt);
	}

	public int execute() throws Throwable {
		LOGGER.info("STARTED - Loading Wireline. " + WlnGridLoaderVersion.getVersion() );
		LOGGER.info("filePath: " + filePath + ". putSize: " + putSize + ". waitTime: " + waitTime);
		long startTime = new Date().getTime();

		final WirelineCSVReader csvReader = new WirelineCSVReaderForLoader(filePath, hasHeader, putSize, waitTime, retryCnt);

		WirelineCacheControlRepository controlRepo = WirelineCacheControlRepository.getInstance();
		String cacheSuffix = controlRepo.getCacheSuffix();
		
		String oldCacheSuffix = cacheSuffix;
		LOGGER.info("current cacheSuffix =  " + cacheSuffix);
		if( cacheSuffix == null ) {
			cacheSuffix = "A";
		} 
		else {
			cacheSuffix = cacheSuffix.equals("A") ? "B" : "A";
		}

		LOGGER.info("cacheSuffix for loading = " + cacheSuffix);

		int processedFiles = 0;

		// load CatalogueItem
		int catalogueItemResult = csvReader.readCatalogueItem("CatalogueItem.csv", cacheSuffix, 0);
		if (catalogueItemResult == Loader.RET_SUCCESS) {
			processedFiles++;
		} else {
			LOGGER.info("CatalogueItem.csv loading failed.");
		}

		// load CatalogueHierarchy
		int hierarchyResult = csvReader.readCatalogueHierarchy("CatalogueHierarchy.csv", cacheSuffix, 1);
		if (hierarchyResult == Loader.RET_SUCCESS) {
			processedFiles++;
		} else {
			LOGGER.info("CatalogueHierarchy.csv loading failed.");
		}

		// load CatalogueHierarchy
		int equipmentResult = csvReader.readEquipment("Equipment.csv", cacheSuffix, 2);
		if (equipmentResult == Loader.RET_SUCCESS) {
			processedFiles++;
		} else {
			LOGGER.info("Equipment.csv loading failed.");
		}
		// load CatalogueHierarchy
		int characteristicResult = csvReader.readProductCharacteristic("ProductCharacteristic.csv", cacheSuffix, 3);
		if (equipmentResult == Loader.RET_SUCCESS) {
			processedFiles++;
		} else {
			LOGGER.info("ProductCharacteristic.csv loading failed.");
		}

		int finalStatus = Loader.RET_SUCCESS;
		
		if (processedFiles == 4) {
			
			if (catalogueItemResult == Loader.RET_SYS_FAILURE || 
					hierarchyResult == Loader.RET_SYS_FAILURE ||
					equipmentResult == Loader.RET_SYS_FAILURE || 
					characteristicResult == Loader.RET_SYS_FAILURE
					) {
				
				finalStatus = Loader.RET_SYS_FAILURE;
				
			} else {
				
				finalStatus =  Loader.RET_SUCCESS;
				// set last updated
				controlRepo.setLastUpdated(new Date());
				LOGGER.info("Last updated date set to " + controlRepo.getLastUpdated());
				
				// set Cache suffix after successful loading
				controlRepo.setCacheSuffix(cacheSuffix);
				LOGGER.info("Cache Suffix set to " + cacheSuffix);
				
				
				//clear the other side of cache
				if ( oldCacheSuffix !=null ) {
					
					// wait for 120 seconds before trashing to insure all transactions are completed on old side
					LOGGER.info("Waiting for 120 seconds before clearing side " + oldCacheSuffix + " to insure all transactions are completed");
					Thread.sleep(120 * 1000);
					
					RepositoryFactory repoFactory = RepositoryFactory.getInstance();
					repoFactory.getHierarchyRepository(oldCacheSuffix).clearCache();
					repoFactory.getCatalogueItemRepository(oldCacheSuffix).clearCache();
					repoFactory.getEquipmentRepository(oldCacheSuffix).clearCache();
					repoFactory.getProductCharacteristicRepository(oldCacheSuffix).clearCache();
				}
			}
		} 

		LOGGER.info("TIME ELAPSED for Loading Wireline = " + ((new Date().getTime() - startTime) / 1000) + " seconds");
		LOGGER.info("FINISHED - Loading Wireline");

		return finalStatus;
	}

}
