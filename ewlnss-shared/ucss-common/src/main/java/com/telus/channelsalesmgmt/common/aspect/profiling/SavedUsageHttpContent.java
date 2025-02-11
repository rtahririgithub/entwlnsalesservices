package com.telus.channelsalesmgmt.common.aspect.profiling;

// TODO: Auto-generated Javadoc
/**
 * The Class SavedUsageHttpContent.
 */
public class SavedUsageHttpContent {
	
	/** The saved current content. */
	private String savedCurrentContent = null;
	
	/** The last time saved current content. */
	private long lastTimeSavedCurrentContent = 0L; 
	
	/**
	 * Gets the saved current content.
	 *
	 * @return the saved current content
	 */
	public String getSavedCurrentContent() {
		return savedCurrentContent;
	}

	/**
	 * Gets the last time saved current content.
	 *
	 * @return the last time saved current content
	 */
	public long getLastTimeSavedCurrentContent() {
		return lastTimeSavedCurrentContent;
	}

	/**
	 * Update saved current content.
	 *
	 * @param newContent the new content
	 * @param now the now
	 * @return the string
	 */
	public synchronized String updateSavedCurrentContent(String newContent, long now) {
		if( now > lastTimeSavedCurrentContent ) {
			lastTimeSavedCurrentContent = now;
			savedCurrentContent = newContent;
			return newContent;
		} else {
			return savedCurrentContent;
		}
	}
}
