/**
 * 
 */
package com.telus.csm.ess.core.operation;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.telus.csm.ewlnsc.delegate.ShoppingCartDelegate;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;

/**
 * @author x145592
 *
 */
@Component
@Scope(SCOPE_PROTOTYPE)
public class GetShoppingCartCoreOperation {
	
	public ShoppingCartVO execute(final String shoppingCartId) {
		//ShoppingCartVO shoppingCartVO = SalesContextDelegate.getInstance().getShoppingCartByShoppingCartId(shoppingCartId);
		
		ShoppingCartVO shoppingCartVO = ShoppingCartDelegate.getInstance().getShoppingCart(shoppingCartId);

		return shoppingCartVO;
	}
	
}
