package com.telus.csm.ewlnsc.grid.loader;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.grid.repository.DefaultRepository;


@SuppressWarnings("rawtypes")
public class WirelineCSVReaderForLoader extends
		WirelineCSVReader {

	private static final Logger LOGGER = Logger.getLogger(WirelineCSVReaderForLoader.class);

	public WirelineCSVReaderForLoader(String filePath,
			boolean hasHeader, int putSize, int waitTime, int retryCnt) {
		super(filePath, hasHeader, putSize, waitTime, retryCnt);
	}

	@Override
	protected int process(DefaultRepository repo, List<?> result, int totalRecordCount, String fileName, int retCode) throws Exception {

		return this.saveRecordsToGrid(repo, result, totalRecordCount, fileName, retCode);
	}

	@Override
	protected int postProcess(int fileIndex, DefaultRepository repo, 
			int objCnt, int totalRecordCount, int totalInvalidCount, String fileName, int retCode, Set<String> allItemIdSet)
			throws Exception {
		
	    LOGGER.info("File = " + fileName + ", Total Record Count = " + totalRecordCount +
    			", Invalid Record Count = " + totalInvalidCount);		
	    
		this.saveObjCntInCtrlRepo(fileIndex, allItemIdSet.size());
		
	    LOGGER.info("NamedCache = " + repo.getCache().getCacheName() + ", Total Object Count = " + repo.getCache().size());
	    
		return retCode;
	}

}