package com.telus.csm.esdg.domain;

import java.io.Serializable;

import com.telus.csm.essc.cachestore.ICacheAware;

// TODO: Auto-generated Javadoc
/**
 * The Class EsdgDO.
 */
public abstract class EsdgDO implements ICacheAware, Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8841874417441830346L;

	/** The sales interaction id. */
	protected String salesInteractionId;
	
	/** The sales interaction start time in mills. */
	protected long salesInteractionStartTimeInMills = 0;
	
	/** The sales context id. */
	protected String salesContextId;
	
	/** The bundle id. - For BC*/
	protected String bundleId;	

	/** The external key. */
	protected String externalKey;
	
	/** The data generation time in mills. */
	protected long dataGenerationTimeInMills;
	
	/** The data generation time in hours. */
	protected long dataGenerationTimeInHours; //for coherence query optimization purpose
	
	/**
	 * Gets the sales interaction id.
	 *
	 * @return the sales interaction id
	 */
	public String getSalesInteractionId() {
		return salesInteractionId;
	}
	
	/**
	 * Sets the sales interaction id.
	 *
	 * @param salesInteractionId the new sales interaction id
	 */
	public void setSalesInteractionId(String salesInteractionId) {
		this.salesInteractionId = salesInteractionId;
	}

	/**
	 * Gets the sales interaction start time in mills.
	 *
	 * @return the sales interaction start time in mills
	 */
	public long getSalesInteractionStartTimeInMills() {
		return salesInteractionStartTimeInMills;
	}

	/**
	 * Sets the sales interaction start time in mills.
	 *
	 * @param salesInteractionStartTimeInMills the new sales interaction start time in mills
	 */
	public void setSalesInteractionStartTimeInMills(
			long salesInteractionStartTimeInMills) {
		this.salesInteractionStartTimeInMills = salesInteractionStartTimeInMills;
	}

	/**
	 * Gets the data generation time in mills.
	 *
	 * @return the data generation time in mills
	 */
	public long getDataGenerationTimeInMills() {
		return dataGenerationTimeInMills;
	}

	/**
	 * Sets the data generation time in mills.
	 *
	 * @param dataGenerationTimeInMills the new data generation time in mills
	 */
	public void setDataGenerationTimeInMills(long dataGenerationTimeInMills) {
		this.dataGenerationTimeInMills = dataGenerationTimeInMills;
		this.dataGenerationTimeInHours = dataGenerationTimeInMills/3600000;
	}

	/**
	 * Gets the data generation time in hours.
	 *
	 * @return the data generation time in hours
	 */
	public long getDataGenerationTimeInHours() {
		return dataGenerationTimeInHours;
	}

	/**
	 * Sets the data generation time in hours.
	 *
	 * @param dataGenerationTimeInHours the new data generation time in hours
	 */
	public void setDataGenerationTimeInHours(long dataGenerationTimeInHours) {
		this.dataGenerationTimeInHours = dataGenerationTimeInHours;
	}

	/**
	 * Gets the sales context id.
	 *
	 * @return the sales context id
	 */
	public String getSalesContextId() {
		return salesContextId;
	}
	
	/**
	 * Sets the sales context id.
	 *
	 * @param salesContextId the new sales context id
	 */
	public void setSalesContextId(String salesContextId) {
		this.salesContextId = salesContextId;
	}
	
	public String getBundleId() {
		return bundleId;
	}

	public void setBundleId(String bundleId) {
		this.bundleId = bundleId;
	}
	
	/**
	 * Gets the external key.
	 *
	 * @return the external key
	 */
	public String getExternalKey() {
		return externalKey;
	}
	
	/**
	 * Sets the external key.
	 *
	 * @param externalKey the new external key
	 */
	public void setExternalKey(String externalKey) {
		this.externalKey = externalKey;
	}
}
