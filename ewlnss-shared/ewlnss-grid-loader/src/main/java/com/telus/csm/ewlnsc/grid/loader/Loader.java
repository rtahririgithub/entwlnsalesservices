package com.telus.csm.ewlnsc.grid.loader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tangosol.util.ValueExtractor;
import com.telus.csm.ewlnsc.grid.repository.DefaultRepository;

public abstract class Loader {
	
	protected static Logger log = Logger.getLogger(Loader.class);

	protected final static int RET_SUCCESS = 0;
	protected final static int RET_SYS_FAILURE = -1;
	protected final static int RET_CACHE_EMPTY = -2;
	protected final static int RET_DATA_ERROR = -3;
	protected final static int RET_DATA_LOAD_FAILURE = -4;
	
	protected final static int MAX_PUT_SIZE = 500;
	protected final static int WAIT_TIME = 500;
	protected final static int RETRY_COUNT = 10;
	
	protected String filePath;
	protected boolean hasHeader;
	protected int putSize = MAX_PUT_SIZE;
	protected int waitTime = WAIT_TIME;
	protected int retryCnt = RETRY_COUNT;

	public int getPutSize() {
		return putSize;
	}
	
	public void setPutSize(int putSize) {
		this.putSize = putSize;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public int getRetryCnt() {
		return retryCnt;
	}

	public void setRetryCnt(int retryCnt) {
		this.retryCnt = retryCnt;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public boolean isHasHeader() {
		return hasHeader;
	}

	public void setHasHeader(boolean hasHeader) {
		this.hasHeader = hasHeader;
	}

	public Loader(String filePath, boolean hasHeader) {
		super();
		this.filePath = filePath;
		this.hasHeader = hasHeader;
	}

	public Loader(String filePath, boolean hasHeader, int putSize, int waitTime) {
		super();
		this.filePath = filePath;
		this.hasHeader = hasHeader;
		this.putSize = putSize;
		this.waitTime = waitTime;
	}

	public Loader(String filePath, boolean hasHeader, int putSize, int waitTime, int retryCnt) {
		super();
		this.filePath = filePath;
		this.hasHeader = hasHeader;
		this.putSize = putSize;
		this.waitTime = waitTime;
		this.retryCnt = retryCnt;
	}

	public long timing(long timer1, String fname) {
		long timer2 = new Date().getTime();
		log.info("Time cost for " + fname + ":" + ((timer2 - timer1) / 1000) + " seconds;\r\n");
		return timer2;		
	}
	
	/**
	 * Record the object count of a named cache after loading.
	 * 
	 * @param cacheObjCntMap
	 * @param repo
	 * @param itemList
	 * @param indexAry
	 * @param indexOrd
	 * @return
	 * @throws Exception
	 */
	public int loadDataTOCacheAfterCleanup(Map<String, Integer> cacheObjCntMap, @SuppressWarnings("rawtypes") DefaultRepository repo, List<?> itemList, 
			ValueExtractor[] indexAry, boolean[] indexOrd) throws Exception {
		int count = this.loadDataTOCacheAfterCleanup(repo, itemList, indexAry, indexOrd);
		cacheObjCntMap.put(repo.getCache().getCacheName(), repo.getCache().size());
		
		return count;
	}
	
	/**
	 * This method cleans up the old data from the grid, then load the data.
	 * 
	 * @param repo
	 * @param itemList
	 * @param indexAry
	 * @param indexOrd
	 * @return
	 * @throws Exception
	 */
	public int loadDataTOCacheAfterCleanup(@SuppressWarnings("rawtypes") DefaultRepository repo, List<?> itemList, 
			ValueExtractor[] indexAry, boolean[] indexOrd) throws Exception {
		
		if( itemList == null || itemList.isEmpty())
			return 0;
		
		for( int i = 0; i < (this.getRetryCnt() + 1); i++ ) {
			try {
				repo.getCache().clear();
				break;
			} catch( Exception e ) {
				log.error(e);
				if( i >= this.getRetryCnt() )
					return RET_SYS_FAILURE;
				Thread.sleep(this.getWaitTime());
			}
		}
		
		return this.loadDataTOCache(repo, itemList, indexAry, indexOrd);
	}

	/**
	 * This method is used to load any data into grid without cleanup.
	 * 
	 * @param repo
	 * @param itemList
	 * @param indexAry
	 * @param indexOrd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public int loadDataTOCache(@SuppressWarnings("rawtypes") DefaultRepository repo, List<?> itemList, 
			ValueExtractor[] indexAry, boolean[] indexOrd) throws Exception {
		log.debug("Enter Loader.loadDataTOCache()");
		
		if( itemList == null || itemList.isEmpty())
			return 0;
				
		int count = 0;
		List<Object> tempList = new ArrayList<Object>();
		for( Object obj : itemList ) {
			tempList.add(obj);
			count++;
			if( (count % this.getPutSize()) == 0 ) {
				for( int i = 0; i < (this.getRetryCnt() + 1); i++ ) {
					try {
						repo.putAll(tempList);
						break;
					} catch( Exception e ) {
						log.error(e);
						if( i >= this.getRetryCnt() )
							return RET_SYS_FAILURE;
						Thread.sleep(this.getWaitTime());
					}
				}
				tempList = new ArrayList<Object>();
				log.info("Item: # of records loaded = " + count);
			}
		}
		
		if( !tempList.isEmpty() ) {
			for( int i = 0; i < (this.getRetryCnt() + 1); i++ ) {
				try {
					repo.putAll(tempList);
					break;
				} catch( Exception e ) {
					log.error(e);
					if( i >= this.getRetryCnt() )
						return RET_SYS_FAILURE;
					Thread.sleep(this.getWaitTime());
				}
			}
			log.info("Item: # of records loaded = " + count);
		}
			
		if (indexAry != null && indexAry.length > 0) {
			int k = 0;
			for (ValueExtractor index : indexAry) {
				for( int i = 0; i < (this.getRetryCnt() + 1); i++ ) {
					try {
						repo.getCache().addIndex(index, indexOrd[k], null);
						break;
					} catch( Exception e ) {
						log.error(e);
						if( i >= this.getRetryCnt() )
							return RET_SYS_FAILURE;
						Thread.sleep(this.getWaitTime());
					}
				}				
				k++;
			}
		}
		
		return count;
	}
	
	abstract public int execute() throws Throwable;
}
