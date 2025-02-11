package com.telus.csm.ewlnsms.core.operation;
 
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.telus.csm.ewlnsc.delegate.SalesContextDelegate;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.price.CartItemPriceVO;
import com.telus.csm.ewlnsc.domain.price.DepositVO;
import com.telus.csm.ewlnsc.domain.price.PriceResponseVO;
import com.telus.csm.ewlnsc.helper.ShoppingCartContextHelper;
import com.telus.csm.ewlnsc.helper.ShoppingCartHelper;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsms.rest.domain.CartItemPrice;
import com.telus.csm.ewlnsms.rest.domain.CombinedPrice;
import com.telus.csm.ewlnsms.rest.domain.Deposit;
import com.telus.csm.ewlnsms.rest.domain.PriceResponse;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
public class GetPriceCoreOperation {
	
	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(GetPriceCoreOperation.class);
	
	private String shoppingCartId;
	
	@Autowired
	ShoppingCartHelper helper;
	
	public PriceResponse execute(final String shoppingCartId, final String transactionId) {
		
		PriceResponse result = null;
		
		this.shoppingCartId = shoppingCartId;
		
		final ShoppingCartContextHelper contextHelper = new ShoppingCartContextHelper();
		final ShoppingCartContextVO shCartContextVO = contextHelper.execute(shoppingCartId);
		if (!shCartContextVO.getOfferList().isEmpty()) {
			result = new PriceResponse();
			helper.populatePrice(shCartContextVO, transactionId);
			
			final PriceResponseVO prVO = shCartContextVO.getPriceResponseVO();
			
			final String jsonCI = JsonUtil.getJsonFromObjectNonNUll(prVO.getCartItemPrice());
			final List<CartItemPrice> ci = JsonUtil.parseJsonToObject(jsonCI, ArrayList.class);
			result.setCartItemPrice(ci);
			
			final String jsonDep = JsonUtil.getJsonFromObjectNonNUll(prVO.getDeposit());
			final List<Deposit> dep = JsonUtil.parseJsonToObject(jsonDep, ArrayList.class);
			result.setDeposit(dep);
	
			final String jsonTot = JsonUtil.getJsonFromObjectNonNUll(prVO.getTotalPrice());
			final CombinedPrice tot = JsonUtil.parseJsonToObject(jsonTot, CombinedPrice.class);
			result.setTotalPrice(tot);
		} else {
			LOGGER.error("Unable to get the offer.");
		}
		
		return result;
	}
	
}
