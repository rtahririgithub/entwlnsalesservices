package com.telus.csm.esdg.domain;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class EsdgContextRawDataDO_1.
 */
public class EsdgContextRawDataDO_1 extends EsdgDO {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1273415854649808611L;
	
	/** The raw data. */
	protected Serializable rawData;
	
	/**
	 * Gets the raw data.
	 *
	 * @return the raw data
	 */
	public Serializable getRawData() {
		return rawData;
	}

	/**
	 * Sets the raw data.
	 *
	 * @param rawData the new raw data
	 */
	public void setRawData(Serializable rawData) {
		this.rawData = rawData;
	}

	/* (non-Javadoc)
	 * @see com.telus.csm.essc.cachestore.ICacheAware#isWriteToDatabase()
	 */
	@Override
	public boolean isWriteToDatabase() {
		return true;
	}

}
