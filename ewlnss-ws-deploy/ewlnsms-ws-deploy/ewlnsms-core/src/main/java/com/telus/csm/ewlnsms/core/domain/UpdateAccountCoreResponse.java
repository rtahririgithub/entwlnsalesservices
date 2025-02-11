/**
 * 
 */
package com.telus.csm.ewlnsms.core.domain;

import com.telus.csm.ewlnsc.domain.CoreResponseBase;


public class UpdateAccountCoreResponse extends CoreResponseBase {

	private static final long serialVersionUID = 1L;
	
	private boolean sucessfulUpdateInd;

	public boolean isSucessfulUpdateInd() {
		return sucessfulUpdateInd;
	}

	public void setSucessfulUpdateInd(boolean sucessfulUpdateInd) {
		this.sucessfulUpdateInd = sucessfulUpdateInd;
	}

}
