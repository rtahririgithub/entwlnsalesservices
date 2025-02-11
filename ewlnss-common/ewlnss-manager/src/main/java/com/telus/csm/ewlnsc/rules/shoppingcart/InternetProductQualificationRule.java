package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.ProductTypeVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;
import com.telus.csm.ewlnsc.helper.CommonWLNGridHelper;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;


public class InternetProductQualificationRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	private static final LoggerUtil logger = LoggerUtil.getLogger(InternetProductQualificationRule.class);
	private CartItemVO cartItem;
	
	
	public InternetProductQualificationRule(CartItemVO cartItem) {
		this.cartItem = cartItem;
	}

	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO ctxVO,List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(ctxVO, traces);
		
		String functionName = "InternetProductQualificationRule.execute()";
		
		boolean isSatisfied=true;
		String cartItemRelationId= "";
		String productCatalogueId ="";
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(InternetProductQualificationRule.class.getName());
		
		String shppoingCartId = ctxVO.getShoppingCartVO().getShoppingCartId();		
		
		if (cartItem.isSalesOrderItem() && !StringUtils.isEmpty(cartItem.getCartItemRelationId())) {
			
			cartItemRelationId = cartItem.getCartItemRelationId();
			
			ProductTypeVO cartItemProducts = cartItem.getProducts();
			
			
			if(cartItemProducts!=null && cartItemProducts.getInternetProduct()!=null){
				
				InternetProductTypeVO internetProduct = cartItemProducts.getInternetProduct();
				if (!CollectionUtils.isEmpty(internetProduct.getProductComponents())
						&& internetProduct.getProductComponents().get(0)!=null
						&& !StringUtils.isEmpty(internetProduct.getProductComponents().get(0).getProductCatalogueId()) && !EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(internetProduct.getProductComponents().get(0).getAction())){
					productCatalogueId = internetProduct.getProductComponents().get(0).getProductCatalogueId();
					if (ctxVO.getProductQualificationAdapterResponseVO()!= null && !CollectionUtils.isEmpty(ctxVO.getProductQualificationAdapterResponseVO().getProductQualificationList())){
						
						if (!StringUtils.isEmpty(productCatalogueId)) { // GSOD might have an empty CID
							CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
							CatalogueItemDO catalogueItemDO = gridHelper.getCatalogueItemById(productCatalogueId);
							if (catalogueItemDO != null && !StringUtils.isEmpty(catalogueItemDO.getProductCode())) {
								String hsicTierCode = WLNOfferUtil.mapOmsCode(catalogueItemDO.getProductCode());
								if (!WLNOfferUtil.isRequestedHsicTierCodeFound(hsicTierCode, ctxVO.getProductQualificationAdapterResponseVO())) {
									trace.setErrors(ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.INTERNET_PROD_NOT_QUALIFY));
									isSatisfied = false;
								}
							} else {
								trace.setErrors(ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.INTERNET_PROD_CODE_NOT_FOUND));
								isSatisfied = false;
							}
							
						}
					}else{
						trace.setErrors(ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.INTERNET_PROD_QUALIFICATION_RESP_NULL));
						isSatisfied = false;
	
					}
				}else{
					logger.debug(functionName, "Rule skipped. Internet ProductCatalogueId is not provided or action is remove, cartItem is: market Offer=" + cartItem.isSalesOrderItem() + " and cartItemRelationId=" + cartItem.getCartItemRelationId());
				}
			}else{
				logger.debug(functionName, "Rule skipped. No internet product. cartItem is: market Offer=" + cartItem.isSalesOrderItem() + " and cartItemRelationId=" + cartItem.getCartItemRelationId());
			}

			if (!isSatisfied){
				trace.setValidationPassedInd(false);
				trace.setShoppingCartId(shppoingCartId);	
				trace.setCartItemRelationId(cartItemRelationId);
				traces.add(trace);
			}
		}else{
			logger.debug(functionName, "Rule skipped. cartItem is: market Offer=" + cartItem.isSalesOrderItem() + " and cartItemRelationId=" + cartItem.getCartItemRelationId());
		}
		return isSatisfied;
	}

}
