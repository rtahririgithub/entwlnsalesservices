package com.telus.csm.ewlnsc.grid.loader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemHierarchyDO;
import com.telus.csm.ewlnsc.grid.domain.Entity;
import com.telus.csm.ewlnsc.grid.domain.EquipmentCatalogueItemDO;
import com.telus.csm.ewlnsc.grid.domain.ProductCharacteristicDO;
import com.telus.csm.ewlnsc.grid.repository.DefaultRepository;
import com.telus.csm.ewlnsc.grid.repository.RepositoryFactory;
import com.telus.csm.ewlnsc.grid.repository.WirelineCacheControlRepository;

import au.com.bytecode.opencsv.CSVReader;

public abstract class WirelineCSVReader {
	
	private static final Logger LOGGER = Logger.getLogger(WirelineCSVReader.class);
	
	private static final String CODEPAGE = "UTF-8";
	private static final char DELIMETER = '|';

	private String filePath = "../";
	protected boolean hasHeader = false;
	protected int putSize;
	protected int waitTime;
	protected int retryCnt;
	
	protected WirelineCacheControlRepository controlRepo;
	
	//for loader
	public WirelineCSVReader(String filePath, boolean hasHeader, int putSize, int waitTime, int retryCnt) {
		if (filePath!=null) {
			if (!filePath.endsWith("/")) {
				filePath += "/";
			}
			this.filePath = filePath;
		}
		this.hasHeader = hasHeader;
		this.putSize = putSize;
		this.waitTime = waitTime;
		this.retryCnt = retryCnt;
		this.controlRepo = WirelineCacheControlRepository.getInstance();
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private <T> int readAndSavetoGrid( DataLoader reader, DefaultRepository repository) throws Exception {
		
		int retCode = Loader.RET_SUCCESS;	
		
		LOGGER.info("Reading file " + reader.getFileName() );
		try {
		
			
			//monitor
			int initialCacheSize = repository.getCache().size();
			
			Set<String> allItemIdSet = new HashSet<String>();			
			
		    while ( true ) {
		    	
		    	List<T> result = reader.loadData(putSize) ;
		    	
		    	if ( result.isEmpty()) {
		    		break;
		    	}
		    	
	        	retCode = process(repository, result, reader.getTotalRecordCount(), reader.getFileName(), retCode);		        	
			}
		    
		    repository.createIndexes();
			
   			retCode = postProcess( 0, repository, initialCacheSize, reader.getTotalRecordCount(), reader.getTotalInvalidCount(),
   					reader.getFileName(), retCode, allItemIdSet);				
			
	    } catch (FileNotFoundException e) {
			
	    	LOGGER.info(filePath + reader.getFileName() + " not found!");
			return Loader.RET_SYS_FAILURE;
		}
		
		finally {
			
			if (reader != null) {
				reader.close();    	
			}
		}
		
		return retCode;
	}
	
	@SuppressWarnings("rawtypes")
	public int readCatalogueItem(String fileName, final String cacheSuffix, int fileIndex) throws Exception {

		DataLoader reader = new CatalogueItemReader( filePath + fileName, true );
		DefaultRepository repo = RepositoryFactory.getInstance().getCatalogueItemRepository(cacheSuffix);
		return readAndSavetoGrid( reader, repo );
	}

	@SuppressWarnings("rawtypes")
	public int readCatalogueHierarchy(String fileName, final String cacheSuffix, int fileIndex) throws Exception {

		DataLoader reader = new CatalogueItemHierarchyReader(filePath + fileName, true );
		DefaultRepository repo = RepositoryFactory.getInstance().getHierarchyRepository(cacheSuffix);
		return readAndSavetoGrid( reader, repo );
	}
		
	@SuppressWarnings("rawtypes")
	public int readEquipment(String fileName, final String cacheSuffix, int fileIndex) throws Exception {

		DataLoader reader = new EquipmentCatalogueItemReader(filePath + fileName, true );
		DefaultRepository repo = RepositoryFactory.getInstance().getEquipmentRepository(cacheSuffix);
		return readAndSavetoGrid( reader, repo );
	}
	
	@SuppressWarnings("rawtypes")
	public int readProductCharacteristic(String fileName, final String cacheSuffix, int fileIndex) throws Exception {

		DataLoader reader = new ProductCharacteristicReader(filePath + fileName, true );
		DefaultRepository repo = RepositoryFactory.getInstance().getProductCharacteristicRepository(cacheSuffix);
		return readAndSavetoGrid( reader, repo );
	}
	

	@SuppressWarnings("rawtypes")
	protected abstract int process(DefaultRepository repo, List<?> result, int totalRecordCount, String fileName, int retCode) throws Exception;

	@SuppressWarnings("rawtypes")
	protected abstract int postProcess(int fileIndex, DefaultRepository repo, int objCnt, 
			int totalRecordCount, int totalInvalidCount, String fileName, int retCode, Set<String> allItemIdSet) throws Exception;

	protected void saveObjCntInCtrlRepo(int fileIndex, int objCnt) {
		switch (fileIndex) {
		case 0: controlRepo.setCatalogueItemCount(objCnt);
			break;
		case 1: controlRepo.seHierarchyCount(objCnt);
			break;
		case 2: controlRepo.setEquipmentCount(objCnt);
			break;
		case 3: controlRepo.setProductCharacteristicCount(objCnt);
			break;		
		}	
	}

	protected int getObjCntInCtrlRepo(int fileIndex) {
		int objCnt = 0;
		switch (fileIndex) {
		case 0: objCnt = controlRepo.getCatalogueItemCount();
			break;
		case 1: objCnt = controlRepo.getHierarchyCount();
			break;
		case 2: objCnt = controlRepo.getEquipmentCount();
			break;
		case 3: objCnt = controlRepo.getProductCharacteristicCount();
			break;		
		}	
		
		return objCnt;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected int saveRecordsToGrid(DefaultRepository repo, List<?> result, int totalRecordCount, String fileName, int retCode)  throws Exception {
		LOGGER.debug("Enter saveRecordsToGrid()");
		
    	LOGGER.info( repo.getCache().getCacheName() + " saving... " + result.size() + ", total: " + totalRecordCount );
    	
		for( int i = 0; i < (retryCnt + 1); i++ ) {
			try {
	        	repo.putAll(result);
				break;
			} catch( Throwable t ) {
				LOGGER.error(t);
				if( i >= retryCnt )
					return Loader.RET_SYS_FAILURE;
				Thread.sleep(waitTime);
			}
		}
		
    	return retCode;
	}
	

	
	static abstract class DataLoader<K, T extends Entity<K>> {
		
		private String fileName;
		private boolean hasHeader;
		private int totalRecordCount;
		private int totalInvalidCount;
		
		private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public int getTotalRecordCount() {
			return totalRecordCount;
		}

		public int getTotalInvalidCount() {
			return totalInvalidCount;
		}
		
		Date stringToDate(final String value) {
			if (value == null || value.isEmpty()) {
				return null;
			}
			Date result = null;
			try {
				result = dateFormat.parse(value);
			} catch (Exception e) {
				LOGGER.info(e);
			}

			return result;
		}
		
		Double stringToDouble (String value ) {
			try {
				if ( value!=null && value.length()>0 ) {
					return Double.parseDouble(value);
				}else {
					return null;
				}
			} catch( RuntimeException re ) {
				LOGGER.warn(re);
				return null;
			}
		}
		
		int toInteger(String value) {
			try {
				if ( value!=null && value.length()>0 ) {
					return Integer.parseInt(value);
				}else {
					return 0;
				}
			} catch( RuntimeException re ) {
				LOGGER.warn(re);
				return 0;
			}
		}
		
		abstract T mapToObject( String[] record );
		
		
		DataLoader( String fileName, boolean hasHeader) {
			this.fileName = fileName;
			this.hasHeader = hasHeader;
		}
		
		
		/*
		private CSVReader reader;
		private CSVReader getCSVReader() throws UnsupportedEncodingException, FileNotFoundException {
			
			if ( reader==null) {
				int skipLine = hasHeader? 1:0;
				reader = new CSVReader(new InputStreamReader( new FileInputStream(fileName), CODEPAGE), 
						DELIMETER, CSVReader.DEFAULT_QUOTE_CHARACTER, CSVReader.DEFAULT_ESCAPE_CHARACTER, skipLine );
			}
			return reader;
		}
		
		
		private Iterator<String[]> iterator;
		private String[] readNext() throws IOException, UnsupportedEncodingException, FileNotFoundException {
			
			//return getCSVReader().readNext();
			
			if (iterator==null) {
				List<String[]> allRecords = getCSVReader().readAll();
				iterator = allRecords.iterator();
				
				LOGGER.info(fileName + " readAll.size = " + allRecords.size() );
			}
			
			if ( iterator!=null && iterator.hasNext()) {
				return iterator.next();
			} else {
				return null;
			}
		}
		*/
		
		private DataReader reader;
		DataReader getReader() throws UnsupportedEncodingException, FileNotFoundException {
			if ( reader==null ) {
				reader = new StringImpl(fileName, hasHeader);
			}
			return reader;
		}
		
		void close() {
			if ( reader!=null ) {
				reader.close();
			}
		}
		
		List<T> loadData( int batchCount ) {
			
			List<T> result = new ArrayList<T>(batchCount);
			
			String[] values = null;
			
		    try {
		    	
		    	while ((values = getReader().readNext()) != null) {
					
				    totalRecordCount++;
				    
					T model = mapToObject( values );
					
					if (model==null ) {
						
						totalInvalidCount++;
						LOGGER.info(fileName + " invalid row " + totalRecordCount + ", # of col=" + values.length + ", value=" + values[0]);
						continue;
						
					} else {
				    
						result.add(model);
					}
					
				    if (result.size() == batchCount) {
				    	break;
				    }	
				}
			} catch (IOException e) {
				LOGGER.error(fileName + " encountered exception while loading.", e );
			}
		    
		    return result;
		}
		
		static interface DataReader {
			String[] readNext() throws IOException;
			void close();
		}
		
		static class CSVReaderImpl implements DataReader {

			private CSVReader reader;
			CSVReaderImpl(String fileName, boolean hasHeader ) throws UnsupportedEncodingException, FileNotFoundException {
				
					int skipLine = hasHeader? 1:0;
					reader = new CSVReader(new InputStreamReader( new FileInputStream(fileName), CODEPAGE), 
							DELIMETER, CSVReader.DEFAULT_QUOTE_CHARACTER, CSVReader.DEFAULT_ESCAPE_CHARACTER, skipLine );
			}
			public String[] readNext() throws IOException {
				return reader.readNext();
			}
			public void close() {
				if ( reader!=null )
					try {
						reader.close();
					} catch (IOException e) {
					}
			}
		}
		
		static class StringImpl implements DataReader {
			
			private BufferedReader reader;
			private boolean firstLine;
			
			StringImpl(String fileName, boolean hasHeader ) throws UnsupportedEncodingException, FileNotFoundException {
				reader = new BufferedReader( new InputStreamReader( new FileInputStream(fileName), CODEPAGE) );
				firstLine = hasHeader;
			}

			@Override
			public String[] readNext() throws IOException {
				
				String line = reader.readLine();
				if (line==null) return null;
				
				if (firstLine) {
					firstLine = false;
					return readNext();
				}
				
				return line.split( "\\|",-1 );
			}

			@Override
			public void close() {
				if ( reader!=null )
					try {
						reader.close();
					} catch (IOException e) {
				}
			}
		}
	}
	
	static class CatalogueItemReader extends DataLoader<String, CatalogueItemDO> {

		CatalogueItemReader(String fileName, boolean hasHeader) {
			super(fileName, hasHeader);
		}
		
		CatalogueItemReader() {
			super("CatalogueItem.csv",true );
		}

		@Override
		public CatalogueItemDO mapToObject(String[] values) {
			
			//file header:
			//#PROD_CATLG_ITM_ID|PROD_MASTER_SRC_ID|PROD_CD|PROD_CATLG_ITM_TYPE_CD|DATA_SRC_ID|PROD_INTRNL_NM|EFF_START_DT|EFF_END_DT|COMP_SERVICE_TYPE_CD|PROD_DESC|PROD_NM

			if (values.length != 11) {
				return null;
			}

			CatalogueItemDO model  = new CatalogueItemDO();
			
			int i=0;
	        model.setCatalogueItemId(values[i++]);
	        model.setMasterSrcId(values[i++]);
	        model.setProductCode(values[i++]);
	        model.setItemType(values[i++]);
	        model.setDataSrcId(values[i++]);
	        model.setInternalName(values[i++]);
	        model.setEffStartDt( stringToDate(values[i++]) ) ;
	        model.setEffEndDt( stringToDate(values[i++]));
	        model.setComponentServiceType(values[i++]);
	        model.setDescription(values[i++]);
	        model.setName(values[i++]);

			return model;
		}

	}
	
	static class CatalogueItemHierarchyReader extends DataLoader<String, CatalogueItemHierarchyDO> {

		CatalogueItemHierarchyReader() {
			super("CatalogueHierarchy.csv", true );
		}

		CatalogueItemHierarchyReader(String fileName, boolean hasHeader) {
			super(fileName, hasHeader);
		}
		
		@Override
		public CatalogueItemHierarchyDO mapToObject(String[] values) {

			//header:
			//#NODE_PATH|OFFER_ID|COMPONENT_PATH|PARENT_PROD_CATLG_ITM_ID|
			//PARENT_PROD_CATLG_ITM_TYPE_CD|PARENT_PROD_CD|PARENT_PROD_INTRNL_NM|PARENT_COMPONENT_SVC_TYPE_CD|
			//CHILD_PROD_CATLG_ITM_ID|CHILD_PROD_CATLG_ITM_TYPE_CD|CHILD_PROD_CD|CHILD_PROD_INTRNL_NM|CHILD_COMPONENT_SVC_TYPE_CD|
			//RELN_EFF_START_DT|RELN_EFF_END_DT|RELN_OPTIONAL_CD|RELN_MIN_ALLOWED_QTY|RELN_MAX_ALLOWED_QTY|PROD_RELN_RSN_CD
			if (values.length != 19) {
				return null;
			}

			CatalogueItemHierarchyDO model  = new CatalogueItemHierarchyDO();
			
			model = new CatalogueItemHierarchyDO();

	        int i=0;
	        model.setNodePath(values[i++]);
	        model.setOfferId(values[i++]);
	        model.setMainComponentPath(values[i++]);
			model.setParentProdCatItemId(values[i++]); 
	        model.setParentProdCatItemTypeId(values[i++]);
	        model.setParentProdCd(values[i++]);
	        model.setParentProdInternalNm(values[i++]);
	        model.setParentCompSvcTypeCd(values[i++]);
	        model.setChildProdCatItemId(values[i++]);
	        model.setChildProdCatItemTypeCd(values[i++]);
	        model.setChildProdCd(values[i++]);
	        model.setChildProdInternalNm(values[i++]);
	        model.setChildCompSvcTypeCd(values[i++]);
	        model.setRelnEffStartDt(values[i++]);
	        model.setRelnEffEndDt(values[i++]);
	        model.setRelnOptionalCd(values[i++]);
	        model.setRelnMaxAllowedQty( toInteger( values[i++]) );
	        model.setRelnMaxAllowedQty( toInteger(values[i++] ) );
	        model.setProdRelnRsnCd(values[i++]);
	        
	        return model;
		}


	}
	
	static class EquipmentCatalogueItemReader extends DataLoader<String, EquipmentCatalogueItemDO> {

		EquipmentCatalogueItemReader(String fileName, boolean hasHeader) {
			super(fileName, hasHeader);
		}
		
		EquipmentCatalogueItemReader() {
			super("Equipment.csv",true );
		}

		@Override
		public EquipmentCatalogueItemDO mapToObject(String[] values) {
			
			//file header:
			//#PROD_CATLG_ITM_ID|PROD_CD|EFF_START_DT|EFF_END_DT|PROD_CATEGORY_TYPE_CD|ACQUISITION_TYPE|MODEL_NAME|
			//PURCHASE_PROD_PRICE_TYPE_CD|PURCHASE_CHARGE_TYPE_CD|PURCHASE_PRICE|
			//RENTAL_PROD_PRICE_TYPE_CD|RENTAL_CHARGE_TYPE_CD|RENTAL_PRICE|
			//LATE_RENTAL_PROD_PRICE_TYPE_CD|LATE_RENTAL_CHARGE_TYPE_CD|LATE_RENTAL_PRICE|
			//PROD_DESC_LANG_CD|PROD_DESC
			if (values.length != 18) {
				return null;
			}

			EquipmentCatalogueItemDO model  = new EquipmentCatalogueItemDO();
			
			int i=0;
	        model.setCatalogueItemId(values[i++]);
	        model.setProductCode(values[i++]);
	        model.setEffStartDt( stringToDate(values[i++]) ) ;
	        model.setEffEndDt( stringToDate(values[i++]));
	        model.setItemType(values[i++]);
	        model.setAcqusitionType(values[i++]);
	        model.setModelName(values[i++]);
	        
	        model.setPurchasePriceTypeCode(values[i++]);
	        model.setPurchaseChargeTypeCode(values[i++]);
	        model.setPurchasePrice( stringToDouble(values[i++]) );
	        
	        model.setRentalPriceTypeCode(values[i++]);
	        model.setRentalChargeTypeCode(values[i++]);
	        model.setRentalPrice( stringToDouble(values[i++]) );
	        
	        model.setLateRentalPriceTypeCode(values[i++]);
	        model.setLateRentalChargeTypeCode(values[i++]);
	        model.setLateRentalPrice( stringToDouble(values[i++]) );
	        
	        i++;//PROD_DESC_LANG_CD
	        model.setDescription(values[i++]);

			return model;
		}
	}
	
	static class ProductCharacteristicReader extends DataLoader<String, ProductCharacteristicDO> {

		ProductCharacteristicReader(String fileName, boolean hasHeader) {
			super(fileName, hasHeader);
		}
		
		ProductCharacteristicReader() {
			super("ProductCharacteristic.csv",true );
		}

		@Override
		public ProductCharacteristicDO mapToObject(String[] values) {
			
			//file header:
			//#CHARACTERISTIC_ID|CATALOGUE_ITEM_ID|NAME|VALUE|STATIC_IND|ELEMENT_DOMAIN_ID|CODE
			if (values.length != 7) {
				return null;
			}

			ProductCharacteristicDO model  = new ProductCharacteristicDO();
			
			int i=0;
			model.setCharacteristicId (values[i++] );
	        model.setCatalogueItemId( values[i++] );
	        model.setName(values[i++] );
	        model.setValue(values[i++] );
	        model.setStaticInd( "1".equals(values[i++]) );
	        model.setElementDomainId( values[i++] );
	        model.setCode(values[i++] );
	        
			return model;
		}
	}	
	
	
}
