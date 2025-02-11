/**
 * 
 */
package com.telus.csm.ewlnsc.domain.price;

import java.io.Serializable;

/**
 * @author x145592
 *
 */
public class DurationVO implements Serializable {
	  
	private static final long serialVersionUID = 1L;

	private Integer startMonthCnt = null;
	private Integer endMonthCnt = null;

	public Integer getStartMonthCnt() {
		return startMonthCnt;
	}

	public void setStartMonthCnt(Integer startMonthCnt) {
		this.startMonthCnt = startMonthCnt;
	}

	public Integer getEndMonthCnt() {
		return endMonthCnt;
	}

	public void setEndMonthCnt(Integer endMonthCnt) {
		this.endMonthCnt = endMonthCnt;
	}

	  
}
