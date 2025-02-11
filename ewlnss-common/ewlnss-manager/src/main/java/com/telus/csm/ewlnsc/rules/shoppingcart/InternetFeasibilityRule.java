package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.telus.csm.ewlnsc.adapter.scis.domain.Product;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProductQualification;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.ProductTypeVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;
import com.telus.csm.ewlnsc.helper.CommonWLNGridHelper;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.rules.business.ProductFeasibilityInfoWrapper;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;


public class InternetFeasibilityRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	private static final LoggerUtil logger = LoggerUtil.getLogger(InternetFeasibilityRule.class);
	private CartItemVO cartItem;
	private ShoppingCartContextVO ctxVO;
	
	public InternetFeasibilityRule(CartItemVO cartItem) {
		this.cartItem = cartItem;
	}

	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO ctxVO,List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(ctxVO, traces);
		
		String functionName = "InternetFeasibilityRule.execute()";
		
		boolean isSatisfied=true;
		String cartItemRelationId= "";
		String productCatalogueId ="";
		this.ctxVO = ctxVO;
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(InternetFeasibilityRule.class.getName());
		
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
					if (ctxVO.getFeasibilityResponseVO()!= null ){
						CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
						CatalogueItemDO catalogueItemDO = gridHelper.getCatalogueItemById(productCatalogueId);
						if(catalogueItemDO!=null && !StringUtils.isEmpty(catalogueItemDO.getProductCode())){
							String hiSpeedPackCode = WLNOfferUtil.mapOmsCode(catalogueItemDO.getProductCode());
							
							ProductFeasibilityInfoWrapper productFeasibilityInfoWrapper = new ProductFeasibilityInfoWrapper(ctxVO.getFeasibilityResponseVO());
							if (productFeasibilityInfoWrapper.getFeasibilityResult() == false
									&& WLNOfferUtil.feasibilityHasTierNotFeasible(ctxVO.getFeasibilityResponseVO()) && productFeasibilityInfoWrapper.getBestAvailableConfigurationInd() == false) {
								isSatisfied = false;
							} else {
								int feasibilityServicePlanRanking = calculateLowestRankingForPP(productFeasibilityInfoWrapper.getServicePlan());
								int offerPlanRanking = calculateLowestRankingForPP(hiSpeedPackCode);

								if (offerPlanRanking < feasibilityServicePlanRanking) {
									trace.setErrors(ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.INTERNET_BEST_FEASIBILITY));
									isSatisfied = false;
								}
							}
						}else{
							ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.INTERNET_BEST_FEASIBILITY);
							shoppingCartValidationError.setMessage(this.getClass().getSimpleName() +  ": The Internet productCode cannot be found in the grid. productCatalogueId associated=" + productCatalogueId);
							trace.setErrors(shoppingCartValidationError);
							isSatisfied = false;
						}

						}else{
							trace.setErrors(ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.FEASIBILITY_RESP_NULL));
							isSatisfied = false;
						}
					}else{
					logger.debug(functionName, "Rule skipped. Internet ProductCatalogueId is not provided or Action is Remove. cartItem is: market Offer=" + cartItem.isSalesOrderItem() + " and cartItemRelationId=" + cartItem.getCartItemRelationId());
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

		private int calculateLowestRankingForPP(String hiSpeedPackCode) {
			List<ProductQualification> productQualificationList = ctxVO.getProductQualificationAdapterResponseVO().getProductQualificationList();
		
			int tomOfferPPRanking = 100000;
		
			if(!CollectionUtils.isEmpty(productQualificationList)){
				for (ProductQualification productQualification : productQualificationList) {
					for (Product product : productQualification.getProductList()) {
						if (EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(product.getProductTypeCd())
								&& hiSpeedPackCode.equalsIgnoreCase(product.getProductTierCd())) {
							if (product.getProductRanking() < tomOfferPPRanking) {
								tomOfferPPRanking = product.getProductRanking();
							}
						}
					}
				}
			}
		
			return tomOfferPPRanking;
		}

}
