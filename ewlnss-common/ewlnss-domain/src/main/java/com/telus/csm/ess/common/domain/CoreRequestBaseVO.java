package com.telus.csm.ess.common.domain;

import com.telus.csm.ewlnsc.domain.OperationHeaderVO;

public abstract class CoreRequestBaseVO {
	private OperationHeaderVO operationHeader;

	public OperationHeaderVO getOperationHeader() {
		return operationHeader;
	}

	public void setOperationHeader(OperationHeaderVO operationHeader) {
		this.operationHeader = operationHeader;
	}
	
}
