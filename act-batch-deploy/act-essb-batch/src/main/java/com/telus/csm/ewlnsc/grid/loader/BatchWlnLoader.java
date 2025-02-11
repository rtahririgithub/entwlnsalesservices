/*
 * Copyright (c) 2004 TELUS Communications Inc., All Rights Reserved.
 *
 * This document contains proprietary information that shall be distributed or
 * routed only within TELUS, and its authorized clients, except with written
 * permission of TELUS.
 *
 * $Id$
 */

package com.telus.csm.ewlnsc.grid.loader;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.telus.csm.ewlnsc.grid.loader.WirelineLoader;
import com.telus.framework.batch.BatchContext;
import com.telus.framework.batch.Module;
import com.telus.framework.batch.exception.ModuleException;


public class BatchWlnLoader implements Module {
	private static final Log log = LogFactory.getLog(BatchWlnLoader.class);

	private boolean firstRun = true;

	private String filePath;
	private boolean hasHeader;
	private int putSize;
	private int waitTime;
	private int retryCnt;

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

	/**
	 * Batch container calls this method first when a step is executed.
	 * 
	 * @see #MODE_NORMAL
	 * @see #MODE_RESTART
	 * @param batchContext
	 * @throws ModuleException
	 */
	@Override
	public void launch(BatchContext batchContext) throws ModuleException {
		log.info("......launch(...) is called");
	}

	/**
	 * This method will be called by the batch container in the case of restart.
	 * 
	 * @see #MODE_RESTART
	 * @see #getStateForRestart()
	 * @see com.telus.framework.batch.io.PositionAwareWriter#reposition(long)
	 * @see com.telus.framework.batch.io.LineNumberAwareReader#reposition(int)
	 * @param state
	 * @throws ModuleException
	 */
	@Override
	public void restoreState(Properties state) throws ModuleException { 
		// State restore is not required since on each run, job will pickup unprocessed
		// document reference ids within specified duration.
		log.info("......restoreState() is called");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.telus.framework.batch.Module#hasNext()
	 */
	@Override
	public boolean hasNext() {
		log.info("......hasNEXT() is called.");
		return this.firstRun;
	}

	/**
	 * If checkpoint is enabled (commit frequency > 0) the batch container will
	 * commit when the commit frequency is reached. Not applicable if checkpoint
	 * is not enabled.<br>
	 * 
	 * This method will be called by the batch container before taking a
	 * checkpoint (before calling commit). Implementations of this method should
	 * return the current state of the module to be saved. In the case of a
	 * restart, this saved state will be passed to the module via the
	 * restoreState() method.<br>
	 * 
	 * <b>NOTE: Only String properties can be saved</b><br>
	 * 
	 * @see com.telus.framework.batch.Module#execute()
	 */
	@Override
	public void execute() throws ModuleException {
		log.info("......execute() is called");
		
		this.firstRun = false; // this is to make sure the job only runs once
		WirelineLoader loader = new WirelineLoader(filePath, Boolean.valueOf(hasHeader), 
				Integer.valueOf(putSize), Integer.valueOf(waitTime), Integer.valueOf(retryCnt));

		try {
			loader.execute();
		} catch (Throwable e1) {
			log.error("WlnOfferLoader execution error.", e1);
			throw new ModuleException(e1);
		}

	}

	/**
	 * This is the last method called by the batch container. Implementations
	 * should perform all cleanup (e.g. close files, database connections etc.).
	 * Module implementations should also return the appropriate return code.
	 * 
	 * This method will be called outside a transaction boundry. Implementations
	 * should not access any transactional resources( e.g RDBMS, JMS etc.)
	 * within this method.<br>
	 * 
	 * @see #RETURN_CODE_SUCCESS
	 * @see #RETURN_CODE_FAILURE
	 * @param success
	 * @return Return code of the module
	 * @throws ModuleException
	 */
	@Override
	public int onExit(boolean success) {
		if (success) {
			log.info("....onExit()  RETURN_CODE=" + RETURN_CODE_SUCCESS);
			return RETURN_CODE_SUCCESS;
		} else {
			log.error("....onExit()  RETURN_CODE=" + RETURN_CODE_FAILURE);
			return RETURN_CODE_FAILURE;
		}

	}

	// ----------------------------------------------------------------------------
	/**
	 * If checkpoint is enabled (commit frequency > 0) the batch container will
	 * commit when the commit frequency is reached. Not applicable if checkpoint
	 * is not enabled.<br>
	 * 
	 * This method will be called by the batch container before taking a
	 * checkpoint (before calling commit). Implementations of this method should
	 * return the current state of the module to be saved. In the case of a
	 * restart, this saved state will be passed to the module via the
	 * restoreState() method.<br>
	 * 
	 * <b>NOTE: Only String properties can be saved</b><br>
	 * 
	 * @see #restoreState(Properties)
	 * @see com.telus.framework.batch.io.PositionAwareWriter#getPosition()
	 * @see com.telus.framework.batch.io.LineNumberAwareReader#getLineNumber()
	 * @return Module state to be saved for restart
	 * @throws ModuleException
	 */
	@Override
	public Properties getStateForRestart() {
		log.info("......getStateForRestart is called");
		return null;
	}

	/**
	 * This method is called on successful completion of a module.
	 * 
	 * @return Summary of module
	 * @throws ModuleException
	 */

	@Override
	public Properties getSummary() {

		log.info("......getSummary is called");
		return null;
	}

}