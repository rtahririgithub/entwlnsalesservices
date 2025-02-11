package com.telus.csm.ess.core.posttask;

import com.telus.csm.ewlnsc.task.TaskBase;

public class ProcessOrderPostTask extends TaskBase {
	private String shoppingCartId;
	
	public ProcessOrderPostTask(String shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	@Override
	protected void execute() {
		new ExecutePostTasksOperation().execute(shoppingCartId);
	}

}
