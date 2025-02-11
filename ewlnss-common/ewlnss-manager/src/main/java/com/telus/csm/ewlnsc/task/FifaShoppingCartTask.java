package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.adapter.domain.GetFifaShoppingCartAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetFifaShoppingCartAdapterResponse;
import com.telus.csm.ewlnsc.adapter.IFifaShoppingCartMgmtRestSvcAdapter;

public class FifaShoppingCartTask extends TaskBase {

	private GetFifaShoppingCartAdapterRequest input;
	private GetFifaShoppingCartAdapterResponse result;
	private IFifaShoppingCartMgmtRestSvcAdapter adapter;

	public GetFifaShoppingCartAdapterResponse getResult() {
		rethrowException();
		return result;
	}

	public FifaShoppingCartTask(IFifaShoppingCartMgmtRestSvcAdapter adapter, GetFifaShoppingCartAdapterRequest input) {
		this.input=input;
		this.adapter=adapter;
	}

	@Override
	protected void execute() {
		result = adapter.getFifaShoppingCart(input);
	}

}
