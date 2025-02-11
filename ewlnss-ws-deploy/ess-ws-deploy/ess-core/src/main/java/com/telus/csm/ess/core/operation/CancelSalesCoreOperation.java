package com.telus.csm.ess.core.operation;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.telus.csm.ess.common.domain.MessageVO;
import com.telus.csm.ess.core.domain.CancelSaleCoreResponse;
import com.telus.csm.ewlnsc.delegate.CancelVestaOrderDelegate;
import com.telus.csm.ewlnsc.delegate.ShoppingCartDelegate;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.transformer.EnterpriseWLNCommonTransformer;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.ShoppingCartStatus;

@Component
@Scope(SCOPE_PROTOTYPE)
public class CancelSalesCoreOperation {
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(CancelSalesCoreOperation.class);
	
	@Autowired
	CancelVestaOrderDelegate cancelVestaOrderDelegate;
	
	
	public CancelSaleCoreResponse execute( String shoppingCartId ) {
		
		CancelSaleCoreResponse result = new CancelSaleCoreResponse();
		result.setHasError(false);
		result.setMessageList(new ArrayList<MessageVO>());
		
		ShoppingCartVO shoppingCartVO  = ShoppingCartDelegate.getInstance().getShoppingCart(shoppingCartId);

		if ( shoppingCartVO!=null ) {
			
			if (!ShoppingCartStatus.CANCELLED.equals(shoppingCartVO.getStatus())
				&& !ShoppingCartStatus.COMPLETED.equals(shoppingCartVO.getStatus())) {

				cancelVestaOrderDelegate.execute(shoppingCartVO,EnterpriseWLNSalesServicesConstants.EXTERNAL_SYSTEM_OMS);
				
				shoppingCartVO.setStatus( ShoppingCartStatus.CANCELLED );
				shoppingCartVO.setCartItemsStatus( ShoppingCartStatus.CartItemStatus.CANCELLED.getCode());
				ShoppingCartDelegate.getInstance().updateShoppingCart(shoppingCartVO);
			} else {
				logger.info("execute", "Sale in status cannot be cancelled. Status=" + shoppingCartVO.getStatus());
				result.setHasError(true);
				result.setMessageList(EnterpriseWLNCommonTransformer.transformToMessageListVO(EnterpriseWLNSalesServicesErrorCodes.ESS_FFH_SALE_STATUS_CANNOT_BE_CANCELLED, "Shopping cart in " + shoppingCartVO.getStatus() + " status cannot be cancelled."));
			}
		} else {
			logger.info("execute", "unable to find shoppingCart with id: " + shoppingCartId);
			result.setHasError(true);
			result.setMessageList(EnterpriseWLNCommonTransformer.transformToMessageListVO(EnterpriseWLNSalesServicesErrorCodes.ESS_FFH_SHOPPING_CART_NOT_FOUND, shoppingCartId + " shopping cart does not exist."));
		}
		
		return result;
		
	}
	
}
