package com.telus.csm.ess.core.posttask;

import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;

public interface IPostTaskStrategy {
	
	void execute(ShoppingCartContextVO shoppingCartContextVO);

}
