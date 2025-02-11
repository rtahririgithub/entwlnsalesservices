package com.telus.csm.ess.rest.operation;


import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.telus.csm.ess.core.operation.CreateShoppingCartCoreOperation;
import com.telus.csm.ess.rest.domain.CreateShoppingCart;
import com.telus.csm.ess.rest.domain.ShoppingCartErrorResponse;
import com.telus.csm.ess.rest.domain.ShoppingCartResponse;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.rest.exception.EssRestErrorException;
import com.telus.csm.ewlnsc.transformer.EnterpriseWLNCommonTransformer;
import com.telus.csm.ewlnsc.transformer.ShoppingCartTransformer;
import com.telus.csm.ewlnsc.util.JsonUtil;

public class CreateShoppingCartRestOperation {

	private static final Logger LOGGER = Logger.getLogger(CreateShoppingCartRestOperation.class);

	public ShoppingCartResponse execute(final CreateShoppingCart request) {
		ShoppingCartResponse result = new ShoppingCartResponse();
		
		//Transforming from swagger object to internal objects
		ShoppingCartVO shoppingCartVOin = ShoppingCartTransformer.transformRestToShoppingCart(null, request.getShoppingCart());
		shoppingCartVOin.setOperationHeader(ShoppingCartTransformer.transformOperationHeaderFromRest(request.getOperationHeader()));
		LOGGER.debug("ShoppingCartVO content is: " + JsonUtil.getJsonFromObjectNonNUll(shoppingCartVOin));
		
		final CreateShoppingCartCoreOperation op = new CreateShoppingCartCoreOperation();
		
		ShoppingCartVO shoppingCartVOout = op.execute(request, shoppingCartVOin);
		
		if(shoppingCartVOout!=null && !CollectionUtils.isEmpty(shoppingCartVOout.getValidationResultList()) && !shoppingCartVOout.isValidShoppingCart()){
			ShoppingCartErrorResponse err = EnterpriseWLNCommonTransformer.transformShoppingCartException(shoppingCartVOout);
			throw new EssRestErrorException(err);
		}else{
			if(shoppingCartVOout!=null){
				result.setShoppingCartId(shoppingCartVOout.getShoppingCartId());
				result.setShoppingCart(ShoppingCartTransformer.transformShoppingCartToRest(shoppingCartVOout));
				if(shoppingCartVOout.isValidShoppingCart() && !CollectionUtils.isEmpty(shoppingCartVOout.getValidationResultList())){
					result.setResponseMessages(EnterpriseWLNCommonTransformer.transformWarnMessages(shoppingCartVOout.getValidationResultList()));
				}
			}
		}
		
		
		return result;
	}

}
