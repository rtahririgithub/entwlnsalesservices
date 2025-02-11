package com.telus.csm.ewlnsc.helper;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.AddOnPackInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.EquipmentInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetProductQualificationAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.Product;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProductInstanceInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProductQualification;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.EquipmentAcquisitionTypeVO;
import com.telus.csm.ewlnsc.domain.ExistingFFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.ExternalOrderDetail;
import com.telus.csm.ewlnsc.domain.FFHDiscountTypeVO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO2;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.MultilingualTextVO;
import com.telus.csm.ewlnsc.domain.ProductComponentVO;
import com.telus.csm.ewlnsc.domain.ProductItemIdentifierVO;
import com.telus.csm.ewlnsc.domain.ProductOrderTypeVO;
import com.telus.csm.ewlnsc.domain.RelatedCartItemVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.CrossProductDependency;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Offer;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueIdentifier;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueItem;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductComponent;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductEligiblity;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;
import com.telus.csm.ewlnsc.transformer.EnterpriseWLNCommonTransformer;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class EnterpriseWLNOrderUtil {

    private static LoggerUtil logger = LoggerUtil.getLogger(EnterpriseWLNOrderUtil.class);

    public static void filterDiscount(Offer offer, ShoppingCartContextVO shoppingCartContextVO) {

        if (offer == null || offer.getOfferProduct() == null || offer.getOfferProduct().getWirelineOfferProductList() == null) {
            return;
        }

//        for (WirelineOfferProduct offerProduct : offer.getOfferProduct().getWirelineOfferProductList()) {
//            List<Discount> filteredDisountList = filterDiscountList(shoppingCartContextVO, offerProduct.getProductTypeCode(), offerProduct.getDiscountList());
//            offerProduct.setDiscountList(filteredDisountList);
//        }

    }

//    public static List<Discount> filterDiscountList(ShoppingCartContextVO shoppingCartContextVO,
//                                                    String productCode, List<Discount> disountListParam) {
//        String functionNam = "filterDiscountList";
//        List<Discount> result = new ArrayList<Discount>();
//
//        for (Discount discount : disountListParam) {
//            if (meetCrossProductDependency(shoppingCartContextVO, discount)) {
//                result.add(discount);
//            } else {
//                logger.debug(functionNam, "CrossProductDependency filter removed discount code: " + discount.getDiscountCode() + " for product: " + productCode);
//            }
//        }
//
//        return result;
//    }

//    public static boolean isDiscountAvailableForCrossProduct(ShoppingCartContextVO shoppingCartContextVO,
//                                                             String productCode, Discount disountParam) {
//        String functionNam = "filterDiscountList";
//
//
//        if (meetCrossProductDependency(shoppingCartContextVO, disountParam)) {
//            return true;
//        } else {
//            logger.debug(functionNam, "CrossProductDependency filter removed discount code: " + disountParam.getDiscountCode() + " for product: " + productCode);
//        }
//
//
//        return false;
//    }


//    public static boolean meetCrossProductDependency(ShoppingCartContextVO shoppingCartContextVO, Discount discount) {
//
//        for (CrossProductDependency crossProductDependency : discount.getCrossProductDependencyList()) {
//            // have to meet all CrossProductDependency
//            if (!meetCrossProductDependency(shoppingCartContextVO, crossProductDependency)) {
//                return false;
//            }
//        }
//        return true;
//    }

//    private static boolean meetCrossProductDependency(ShoppingCartContextVO shoppingCartContextVO, CrossProductDependency crossProductDependency) {
//
//        if (meetCrossProductDependencyInCart(shoppingCartContextVO, crossProductDependency)) {
//            return true;
//        }
////NWLN-8321 As per Ada's Email to comment out these logic of checking Assigned And Pending Product.
////      return meetCrossProductDependencyInAssignedAndPendingProduct(shoppingCartContextVO, crossProductDependency); 
//        return false;
//
//    }
//
    //NWLN-8321
    private static boolean isHSICZero(ShoppingCartContextVO contextVO){
    	boolean isHSICZero = false;
    	
		if(!CollectionUtils.isEmpty(contextVO.getOfferList())){
			List<GetSalesOfferDetailResponseVO2> offerList = contextVO.getOfferList();
			for(GetSalesOfferDetailResponseVO2 offer : offerList){
				if(offer.getOffer() != null){
					for(WirelineOfferProduct wirelineProduct : offer.getOffer().getOfferProduct().getWirelineOfferProductList()){
						if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(wirelineProduct.getProductTypeCode())){
							String externalCId = getExternalCidFromOffer(wirelineProduct.getProductComponentList());
							String hsPack = WLNOfferUtil.mapOmsCode(externalCId);
							String nonSellableProduct = ApplicationProperties.getConfigString("${common/getAvailableOfferSummaryListParams/nonSellableProduct}");
							if(hsPack.equalsIgnoreCase(nonSellableProduct)){
								return true;
							}else{
								return false;
							}
						}
					}
				}
			}
		}
	return isHSICZero;
  }
    
	private static String getExternalCidFromOffer(List<ProductComponent> productComponentList) {
		String externalProductCatalogueId=null;
		if(productComponentList!=null && !productComponentList.isEmpty()){
			for(ProductComponent productComponent : productComponentList){
				for (ProductCatalogueItem productCatalogueItem : productComponent.getProductCatalogueItemList()) {
					externalProductCatalogueId = productCatalogueItem.getProductCatalogueIdentifier()
							.getExternalProductCatalogId();
					break;
				}

			}
		}
		return externalProductCatalogueId;
	}
	

	//Commented out. Jul., 2019. The rule to be handled by the Promotion Qualification app		
//	private static boolean meetCrossProductDependencyInCart(ShoppingCartContextVO shoppingCartContextVO,
//                                                            CrossProductDependency crossProductDependency) {
//
//        String productCode = crossProductDependency.getProductCode();
//        int dependencyTerm = determineMinTerm(crossProductDependency);
//
//        for (CartItemVO cartItem : shoppingCartContextVO.getShoppingCartVO().getCartItemList()) {
//            if (cartItem.isSalesOrderItem()) {
//                ProductTypeVO cartProducts = cartItem.getProducts();
//                if (cartProducts == null) {
//                    continue;
//                }
//
//                String cartProductTermString = null;
//                if (EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(productCode)) {
//                    if (cartProducts.getInternetProduct() == null || isHSICZero(shoppingCartContextVO)) { //NWLN-8321
//                        continue;
//                    }
//                    cartProductTermString = cartProducts.getInternetProduct().getSelectedContractTermCd();
//                } else if (EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(productCode)) {
//                    if (cartProducts.getTelevisionProduct() == null) {
//                        continue;
//                    }
//                    cartProductTermString = cartProducts.getTelevisionProduct().getSelectedContractTermCd();
//                } else if (EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(productCode)) {
//                    if (cartProducts.getHomePhoneProduct() == null) {
//                        continue;
//                    }
//                    cartProductTermString = cartProducts.getHomePhoneProduct().getSelectedContractTermCd();
//                } else {
//                    continue;
//                }
//
//                int cartProductTerm = 0;
//                try {
//                    cartProductTerm = Integer.parseInt(cartProductTermString);
//                } catch (NumberFormatException e) {
//                }
//
//                if (cartProductTerm >= dependencyTerm) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }


//    private static boolean meetCrossProductDependencyInAssignedAndPendingProduct(
//            ShoppingCartContextVO shoppingCartContextVO, CrossProductDependency crossProductDependency) {
//
//        GetAssignedAndPendingProductResponseVO getAssignedAndPendingProductResponse = shoppingCartContextVO.getAssignedAndPendingProductResponseVO();
//
//        if (getAssignedAndPendingProductResponse != null && getAssignedAndPendingProductResponse.getPendingProductList() != null) {
//            if (meetCrossProductDependencyInAssignedAndPendingProductList(getAssignedAndPendingProductResponse.getPendingProductList(), crossProductDependency)) {
//                return true;
//            }
//        }
//
//        if (getAssignedAndPendingProductResponse != null && getAssignedAndPendingProductResponse.getSubscribedProductList() != null) {
//            if (meetCrossProductDependencyInAssignedAndPendingProductList(getAssignedAndPendingProductResponse.getSubscribedProductList(), crossProductDependency)) {
//                return true;
//            }
//        }
//
//
//        return false;
//    }

//    private static boolean meetCrossProductDependencyInAssignedAndPendingProductList(
//            List<SubscribedProductInfoRestVO> currentProductList, CrossProductDependency crossProductDependency) {
//
//        int dependencyTerm = determineMinTerm(crossProductDependency);
//
//        for (SubscribedProductInfoRestVO currentProduct : currentProductList) {
//            if (StringUtils.equalsIgnoreCase(crossProductDependency.getProductCode(), currentProduct.getProductTypeCd())) {
//                for (ProductInstanceInfoRestVO productInstance : currentProduct.getProductInstance()) {
//                    int currentTerm = 0;
//                    try {
//                        currentTerm = Integer.parseInt(productInstance.getTermCd());
//                    } catch (NumberFormatException e) {
//                    }
//                    if (currentTerm >= dependencyTerm) {
//                        return true;
//                    }
//                }
//            }
//        }
//
//        return false;
//    }


    private static int determineMinTerm(CrossProductDependency crossProductDependency) {
        //The minimum term from the crossProductDependency
        BigInteger dependencyTerm = null;

        for (BigInteger x : crossProductDependency.getContractTermList()) {
            if (dependencyTerm == null) {
                dependencyTerm = x;
            } else if (x != null && x.intValue() < dependencyTerm.intValue()) {
                dependencyTerm = x;
            }
        }

        if (dependencyTerm == null) {
            return 0;
        }
        return dependencyTerm.intValue();
    }

//    public static void filterSweetenerDiscount(SweetenerOfferSummary sweetenerOfferSummary,
//                                               ShoppingCartContextVO shoppingCartContextVO) {
//
//        if (sweetenerOfferSummary.getOfferProductSummary() != null) {
//            for (WirelineOfferProductSummary wirelineOfferProductSummary : sweetenerOfferSummary.getOfferProductSummary().getWirelineOfferProductSummaryList()) {
//                List<Discount> filteredDisountList = filterDiscountList(shoppingCartContextVO, wirelineOfferProductSummary.getProductTypeCode(), wirelineOfferProductSummary.getDiscountList());
//                wirelineOfferProductSummary.setDiscountList(filteredDisountList);
//            }
//        }
//    }


    public static String getFormatedStringList(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if (!CollectionUtils.isEmpty(list)) {
            for (String nonCompliantEquipment : list) {
                sb.append(" " + nonCompliantEquipment);
            }
        }
        return sb.toString();
    }

    public static boolean productItemCatalogueIdMatches(ProductItemIdentifierVO productItemIdentifier, ProductComponentVO productCatalogueIdentifier) {
        String productCatalogueId = null;
        String parentProductCatalogueId = null;
        String availableItemProductCatalogueId = null;
        String availableItemParentProductCatalogueId = null;
        if (productCatalogueIdentifier != null) {
            if (!StringUtils.isBlank(productCatalogueIdentifier.getParentProductCatalogueId())) {
                parentProductCatalogueId = productCatalogueIdentifier.getParentProductCatalogueId();
            }
            if (!StringUtils.isBlank(productCatalogueIdentifier.getProductCatalogueId())) {
                productCatalogueId = productCatalogueIdentifier.getProductCatalogueId();
            }
        }

        if (productItemIdentifier != null) {
            if (!StringUtils.isBlank(productItemIdentifier.getProductCatalogueId())) {
                availableItemProductCatalogueId = productItemIdentifier.getProductCatalogueId();
            }
            if (!StringUtils.isBlank(productItemIdentifier.getParentProductCatalogueId())) {
                availableItemParentProductCatalogueId = productItemIdentifier.getParentProductCatalogueId();
            }
        }

        if (!StringUtils.isBlank(productCatalogueId) && !StringUtils.isBlank(parentProductCatalogueId) && !StringUtils.isBlank(availableItemProductCatalogueId)
                && !StringUtils.isBlank(availableItemParentProductCatalogueId) && productCatalogueId.equals(availableItemProductCatalogueId) && parentProductCatalogueId.equals(availableItemParentProductCatalogueId)) {
            return true;
        }
        return false;
    }

    public static List<String> getProductsFromCartItem(CartItemVO cartItem) {
        List<String> cartItemProductList = new ArrayList<String>();
        if (cartItem != null && cartItem.isSalesOrderItem()) {
            if (cartItem.getProducts().getInternetProduct() != null) {
                cartItemProductList.add(EnterpriseWLNSalesServicesConstants.HSIC);
            }

            if (cartItem.getProducts().getHomePhoneProduct() != null) {
                cartItemProductList.add(EnterpriseWLNSalesServicesConstants.SING);
            }

            if (cartItem.getProducts().getTelevisionProduct() != null) {
                cartItemProductList.add(EnterpriseWLNSalesServicesConstants.TTV);
            }
            
            if (cartItem.getProducts().getAccessoryProduct() != null) {
                cartItemProductList.add(EnterpriseWLNSalesServicesConstants.CPE);
            }
        }
        return cartItemProductList;
    }

    public static boolean productComponentHasRemove(List<ProductComponentVO> productComponentList) {
        if (!CollectionUtils.isEmpty(productComponentList)) {
            for (ProductComponentVO productComponent : productComponentList) {
                if (EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(productComponent.getAction())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isRemoveDiscounts(List<FFHDiscountTypeVO> discounts) {
        for (FFHDiscountTypeVO cartDiscount : discounts) {
            if (EnterpriseWLNOrderUtil.productComponentHasRemove(cartDiscount.getProductCatalogueIdentifiers())) {
                return true;
            }
        }
        return false;
    }

    public static SubscribedProductInfoRestVO getExistingProductByType(List<SubscribedProductInfoRestVO> existingProducts, String productTypeCd) {

        for (SubscribedProductInfoRestVO subscribedProduct : existingProducts) {
            if (productTypeCd.equalsIgnoreCase(subscribedProduct.getProductTypeCd())) {
                return subscribedProduct;
            }
        }

        return null;
    }

    public static boolean containsAnyIgnoreCase(List<String> source, String[] target) {

        if (CollectionUtils.isEmpty(source)) {
            return false;
        }

        for (String t : target) {
            for (String s : source) {
                if (t.equalsIgnoreCase(s)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isUpgradeProductOrderType(ProductOrderTypeVO productOrderType) {
        if (productOrderType != null && !StringUtils.isBlank(productOrderType.getOrderTypeCd()) && EnterpriseWLNSalesServicesConstants.UPGRADE.equalsIgnoreCase(productOrderType.getOrderTypeCd())) {
            return true;
        }
        return false;
    }

    public static boolean isFeasibilityResponseValid(CheckProductFeasibilityAdapterResponse feasibilityResponse) {
        String functionName = "isFeasibilityResponseValid";
        /**
         * This method will determine if the response of feasibilitySvc is valid.
         *
         * 	- no serviceException and workType returned for products => Feasibility response valid.
         */

        if (feasibilityResponse != null && feasibilityResponse.getResponseMessageList() != null && CollectionUtils.isNotEmpty(feasibilityResponse.getResponseMessageList().getResponseMessage()) && feasibilityResponse.getProductFeasibilityInfoList() == null) {
            logger.error("checkProductFeasibilityResponse returned error messages only, setting FeasibilityResponseValid to false.");
            return false;

        }

        if (feasibilityResponse != null && (CollectionUtils.isNotEmpty(feasibilityResponse.getInstallTypeRWFW()) || CollectionUtils.isNotEmpty(feasibilityResponse.getInstallTypeSW()))) {
            return true;
        }

        if (feasibilityResponse != null && CollectionUtils.isEmpty(feasibilityResponse.getInstallTypeRWFW()) && CollectionUtils.isNotEmpty(feasibilityResponse.getInstallTypeSW())) {
            logger.warn(functionName, "CheckProductFeasibilityResponse returned only SW products, no installation is required, setting FeasibilityResponseValid to true.");
        }
        return false;
    }


    public static List<String> getOrderedProductsForFeasibility(ShoppingCartContextVO ctxVO) {
        List<String> products = new ArrayList<String>();
        List<Product> orderedProductsFromVOList = EnterpriseWLNCommonTransformer.getOrderedProductFromVO(ctxVO.getShoppingCartVO(), ctxVO);

        if (CollectionUtils.isNotEmpty(orderedProductsFromVOList)) {
            for (Product product : orderedProductsFromVOList) {
                products.add(product.getProductTypeCd());
            }
        }

        List<String> existingProductsToUpgradeList = EnterpriseWLNCommonTransformer.getExistingProductsToUpgrade(ctxVO);

        if (!CollectionUtils.isEmpty(existingProductsToUpgradeList)) {
            for (String existingProduct : existingProductsToUpgradeList) {
                if (!products.contains(existingProduct)) {
                    products.add(existingProduct);
                }
            }
        }


        return products;
    }

    public static boolean isUpgradingExistingProduct(SubscribedProductInfoRestVO subscribedProduct,
                                                     ShoppingCartVO shoppingCartVO) {
        boolean productOrderedIsAssigned = false;
        if (shoppingCartVO != null && shoppingCartVO.getCartItemList() != null && !shoppingCartVO.getCartItemList().isEmpty()) {
            for (CartItemVO cartItemVO : shoppingCartVO.getCartItemList()) {
                if (cartItemVO.isSalesOrderItem() && cartItemVO.getProducts() != null) {
                    if (cartItemVO.getProducts().getInternetProduct() != null && EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(subscribedProduct.getProductTypeCd()) && EnterpriseWLNOrderUtil.isUpgradeProductOrderType(cartItemVO.getProducts().getInternetProduct().getProductOrderType())) {
                        productOrderedIsAssigned = true;
                        break;
                    }
                    if (cartItemVO.getProducts().getTelevisionProduct() != null && EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(subscribedProduct.getProductTypeCd()) && EnterpriseWLNOrderUtil.isUpgradeProductOrderType(cartItemVO.getProducts().getTelevisionProduct().getProductOrderType())) {
                        productOrderedIsAssigned = true;
                        break;

                    }
                    if (cartItemVO.getProducts().getHomePhoneProduct() != null && EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(subscribedProduct.getProductTypeCd()) && EnterpriseWLNOrderUtil.isUpgradeProductOrderType(cartItemVO.getProducts().getHomePhoneProduct().getProductOrderType())) {
                        productOrderedIsAssigned = true;
                        break;
                    }
                }
            }
        }
        return productOrderedIsAssigned;
    }

    public static boolean getCotIndicator(GetProductQualificationAdapterResponse productQualificationAdapterResponseVO) {
        boolean cotIndicator = false;
        if (productQualificationAdapterResponseVO != null && productQualificationAdapterResponseVO.getProductQualificationList() != null && !productQualificationAdapterResponseVO.getProductQualificationList().isEmpty()) {
            for (ProductQualification productQualification : productQualificationAdapterResponseVO.getProductQualificationList()) {
                if (productQualification.getChangeOfTechnologyInd().equalsIgnoreCase("YES")) {
                    cotIndicator = true;
                    break;
                }
            }
        }
        return cotIndicator;
    }

    public static String getOpOrderList(List<ExternalOrderDetail> externalOrderDetailList) {
        StringBuilder sb = new StringBuilder();
        if (CollectionUtils.isNotEmpty(externalOrderDetailList)) {
            for (ExternalOrderDetail externalOrderDetail : externalOrderDetailList) {
                sb.append(externalOrderDetail.toString());
            }
        }
        return sb.toString();
    }


    public static boolean opIdExistInExternalOrderDetailList(int opOrderId, ShoppingCartVO shoppingCart) {
        if (shoppingCart != null && CollectionUtils.isNotEmpty(shoppingCart.getExternalOrderDetailList())) {
            for (ExternalOrderDetail externalOrderDetail : shoppingCart.getExternalOrderDetailList()) {
                if (String.valueOf(opOrderId).equals(externalOrderDetail.getExternalOrderId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static ExternalOrderDetail getAssociatedExternalOrderDetailByOpId(Integer opOrderId, ShoppingCartContextVO shoppingCartContextVO) {

        if (shoppingCartContextVO != null && shoppingCartContextVO.getShoppingCartVO() != null && CollectionUtils.isNotEmpty(shoppingCartContextVO.getShoppingCartVO().getExternalOrderDetailList())) {
            for (ExternalOrderDetail externalOrderDetail : shoppingCartContextVO.getShoppingCartVO().getExternalOrderDetailList()) {
                if (String.valueOf(opOrderId).equals(externalOrderDetail.getExternalOrderId())) {
                    return externalOrderDetail;
                }
            }
        }

        return null;
    }

    public static String getExternalOrderIdBySystem(ShoppingCartVO shoppingCartVO, String cancellationSystem) {
        if (shoppingCartVO != null && !CollectionUtils.isEmpty(shoppingCartVO.getExternalOrderDetailList())) {
            for (ExternalOrderDetail externalOrderDetail : shoppingCartVO.getExternalOrderDetailList()) {
                if (cancellationSystem.equalsIgnoreCase(externalOrderDetail.getExternalOrderSystem())) {
                    return externalOrderDetail.getExternalOrderId();
                }
            }
        }
        return null;
    }

    public static List<RelatedCartItemVO> getRelatedCartItemListFromVO(ShoppingCartVO shoppingCartVO) {
        List<RelatedCartItemVO> relatedCartItemList = new ArrayList<RelatedCartItemVO>();
        if (shoppingCartVO != null && CollectionUtils.isNotEmpty(shoppingCartVO.getCartItemList())) {
            for (CartItemVO cartItem : shoppingCartVO.getCartItemList()) {
                RelatedCartItemVO relatedCartItemVO = new RelatedCartItemVO();

                if (cartItem.isSalesOrderItem()) {
                    relatedCartItemVO.setCartItemType(RelatedCartItemVO.CARTITEM_TYPE.SALE.toString());
                }

                if (cartItem.isDisconnectOrderItem()) {
                    relatedCartItemVO.setCartItemType(RelatedCartItemVO.CARTITEM_TYPE.DISCONNECT.toString());
                }

                if (cartItem.isGwpOrderItem()) {
                    relatedCartItemVO.setCartItemType(RelatedCartItemVO.CARTITEM_TYPE.GWP.toString());
                }

                if (cartItem.isPerkOrderItem()) {
                    relatedCartItemVO.setCartItemType(RelatedCartItemVO.CARTITEM_TYPE.PERK.toString());
                }

                if (StringUtils.isNotBlank(cartItem.getCartItemId())) {
                    relatedCartItemVO.setCartItemId(cartItem.getCartItemId());
                }
                relatedCartItemList.add(relatedCartItemVO);
            }
        }
        return relatedCartItemList;
    }

    public static boolean isCustomerRecontractingProduct(SubscribedProductInfoRestVO subscribedProduct, ShoppingCartVO shoppingCartVO) {
        boolean productOrderedIsAssigned = false;
        if (shoppingCartVO != null && shoppingCartVO.getCartItemList() != null && !shoppingCartVO.getCartItemList().isEmpty()) {
            for (CartItemVO cartItemVO : shoppingCartVO.getCartItemList()) {
                if (cartItemVO.isSalesOrderItem() && cartItemVO.getProducts() != null) {
                    if (cartItemVO.getProducts().getInternetProduct() != null && EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(subscribedProduct.getProductTypeCd()) && isRecontractingProduct(cartItemVO.getProducts().getInternetProduct().getProductOrderType())) {
                        productOrderedIsAssigned = true;
                        break;
                    }
                    if (cartItemVO.getProducts().getTelevisionProduct() != null && EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(subscribedProduct.getProductTypeCd()) && isRecontractingProduct(cartItemVO.getProducts().getTelevisionProduct().getProductOrderType())) {
                        productOrderedIsAssigned = true;
                        break;

                    }
                    if (cartItemVO.getProducts().getHomePhoneProduct() != null && EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(subscribedProduct.getProductTypeCd()) && isRecontractingProduct(cartItemVO.getProducts().getHomePhoneProduct().getProductOrderType())) {
                        productOrderedIsAssigned = true;
                        break;
                    }
                }
            }
        }
        return productOrderedIsAssigned;

    }

    public static boolean isRecontractingProduct(ProductOrderTypeVO productOrderType) {
        if (productOrderType != null && StringUtils.isNotBlank(productOrderType.getOrderTypeCd()) && (EnterpriseWLNSalesServicesConstants.ACTION_NO_CHANGE.equalsIgnoreCase(productOrderType.getOrderTypeCd()) || EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_NOCHANGE.equalsIgnoreCase(productOrderType.getOrderTypeCd()))) {
            return true;
        }
        return false;
    }

    // NWLN-7614 - split offer project. NWLN-10229 Apply new validation rule for enforcementGroupCd, MANDATORY/ONE_OF
    // added to validate the product eligibility of current offer.
    // this check should work for both split and joined offers
    public static boolean isOfferProductEligibilityValid(Offer offer, List<SubscribedProductInfoRestVO> subscribedProducts, List<Offer> allOffers, CartItemVO cartItem, InternetProductTypeVO internetProductType, List<String> offerProductEligibilityErrorMsg) {
    	String functionName = "isOfferProductEligibilityValid";
    	logger.info(functionName, "Start");
        if (offer.getProductEligiblityList() != null && offer.getProductEligiblityList().size() > 0) {
        	logger.info(functionName, "Has product eligibility list");
        	Map<String, Boolean> oneOfList = new HashMap<String, Boolean>();
        	Map<String, String> allProductTypeMap = new HashMap<String, String>();
        	
        	
			// NWLN-10229 Add all offer product from shopping cart into map
        	logger.info(functionName, "Adding all offer product in cart into map");
			for (Offer cartItemOffer : allOffers) {
				String offerProductTypeCd = cartItemOffer.getOfferProduct().getWirelineOfferProductList().get(0)
						.getProductTypeCode();
				if (!allProductTypeMap.containsKey(offerProductTypeCd))
					allProductTypeMap.put(offerProductTypeCd, offerProductTypeCd);
			}

			// NWLN-10229 Add all existing product into map
			logger.info(functionName, "Adding all existing product in cart into map");
			for (SubscribedProductInfoRestVO subscribedProduct : subscribedProducts) {
				String offerProductTypeCd = subscribedProduct.getProductTypeCd();
				if (!allProductTypeMap.containsKey(offerProductTypeCd))
					allProductTypeMap.put(offerProductTypeCd, offerProductTypeCd);
			}
        	
            for (ProductEligiblity productEligiblity : offer.getProductEligiblityList()) {
            	String enforcementGroupCd = productEligiblity.getProductEnforcementGroupCd();
            	String productTypeCd = productEligiblity.getProductTypeCd();
            	
            	boolean mandatory, oneof;
            	
            	// NWLN-10229 set default flag
            	if(StringUtils.isBlank(enforcementGroupCd)) {
            		mandatory = true;
            		oneof = false;
            	}else {
            		mandatory = EnterpriseWLNSalesServicesConstants.PRODUCT_ENFORCEMENT_GROUP_CD_MANDATORY.equalsIgnoreCase(enforcementGroupCd);
                	oneof = EnterpriseWLNSalesServicesConstants.PRODUCT_ENFORCEMENT_GROUP_CD_ONEOF.equalsIgnoreCase(enforcementGroupCd);
            	}
            	
            	
            	logger.info(functionName, "ProductType:" + productTypeCd + " Mandatory:" + mandatory + " OneOf:"+oneof);
            	
				// NWLN-10229 Validate product type
				if (!allProductTypeMap.containsKey(productTypeCd)) {
					String msg = "Required Product Type Code: " + productTypeCd + " is not found in cart items or subscribed products.";
                	logger.info(functionName, msg); 
                	offerProductEligibilityErrorMsg.add(msg);
					if (mandatory) {
						logger.info(functionName, "Mandatory rule failed, Return " + productTypeCd + "  as False"); 
						return false;
					}
					
					if (oneof) {
						logger.info(functionName, "Add " + productTypeCd + "  as False into OneOf list"); 
						oneOfList.put(productTypeCd, Boolean.FALSE);
						continue;
					}
				}
            	
                ProductCatalogueIdentifier requiredProductTemplateId = productEligiblity.getProductTemplateIdentifier();
                boolean foundMatchingProductCatalogueIdentifier = false;

                //NWLN-10173 fix for updateShopping cart error if this product is Accessory
                if (requiredProductTemplateId != null) {
                	// check to see if requiredProductCatalogueIdentifier part of the Products returned by the offer.
                    for ( Offer cartItemOffer: allOffers) {
                    	if(cartItemOffer != null && cartItemOffer.getOfferProduct() != null){
                            for (WirelineOfferProduct wirelineOfferProduct : cartItemOffer.getOfferProduct().getWirelineOfferProductList()) {
                                ProductCatalogueIdentifier offerProductCatalogueIdentifier = wirelineOfferProduct.getProductTemplateIdentifier();
                                if (    productEligiblity.getProductTypeCd().equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode())
                                        && requiredProductTemplateId.getProductCatalogueId().equalsIgnoreCase(offerProductCatalogueIdentifier.getProductCatalogueId())
                                        && requiredProductTemplateId.getExternalProductCatalogId().equalsIgnoreCase(offerProductCatalogueIdentifier.getExternalProductCatalogId())) {
                                	logger.debug(functionName, "Amongst all offers from the cart, required template id was found in offer Id: " + cartItemOffer.getOfferId());
                                    foundMatchingProductCatalogueIdentifier = true;
                                    break;
                                }
                            }
                            if (foundMatchingProductCatalogueIdentifier) {
                                break; // found... no need to search further
                            }	
                    	}
                    }

                    // check to see if requiredProductCatalogueIdentifier part of the existing products of the customer .
                    if (!foundMatchingProductCatalogueIdentifier) {
                        if (subscribedProducts != null && !subscribedProducts.isEmpty()) {
                            for (SubscribedProductInfoRestVO subscribedProductInfoRestVO : subscribedProducts) {
                                for (ProductInstanceInfoRestVO productInstanceInfoRestVO : subscribedProductInfoRestVO.getProductInstance()) {
                                    if (    productEligiblity.getProductTypeCd().equalsIgnoreCase(subscribedProductInfoRestVO.getProductTypeCd())
                                            && requiredProductTemplateId.getExternalProductCatalogId().equalsIgnoreCase(productInstanceInfoRestVO.getOmsOfferCatalogId())) {
                                    	logger.debug(functionName, "Amongst all subscribed products, required template id was found in subscribedProducts: " + productInstanceInfoRestVO.getProductName());
                                        foundMatchingProductCatalogueIdentifier = true;
                                        break;
                                    }
                                }
                                if (foundMatchingProductCatalogueIdentifier) {
                                    break; // found... no need to search further
                                }
                            }
                        }
                    }
                } else {
                	foundMatchingProductCatalogueIdentifier = true;
                }
                
                // if no matches , no need to check for other products... offer is not a valid split offer
                if (!foundMatchingProductCatalogueIdentifier) {
                	String msg = "Required Product Template Id: " + requiredProductTemplateId.getProductCatalogueId() + " is not found in cart items or subscribed products.";
                	logger.info(functionName, msg); 
                	offerProductEligibilityErrorMsg.add(msg);
					if (mandatory) {
						logger.info(functionName, "Mandatory rule failed, Return " + productTypeCd + "  as False");
						return false;
					}
					if (oneof) {
						logger.info(functionName, "Add " + productTypeCd + "  as False into OneOf list");
						oneOfList.put(productTypeCd, Boolean.FALSE);
						continue;
					}
                } else {
        			// NWLN-7750 - Fail the ESS Offer Validation for the Cart if the required HS component from the offer's productEligibiltyList is not the matching HS from the cart item or from the existing product list
            		if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(productEligiblity.getProductTypeCd()) &&
            				!productEligiblity.getProductComponentIdentifierList().isEmpty()){
                		List<String> productEligiblityProductCatalogueId = new ArrayList<String>();
            			for(ProductCatalogueIdentifier productCatalogueIdentifier: productEligiblity.getProductComponentIdentifierList()){
                			productEligiblityProductCatalogueId.add(productCatalogueIdentifier.getProductCatalogueId());
                		}
            			
                		if(internetProductType != null && internetProductType.getProductComponents() != null){
                    		List<String> productCatalogueId = new ArrayList<String>();
                    		
                    		for(ProductComponentVO productComponentVO: internetProductType.getProductComponents()){
                    			productCatalogueId.add(productComponentVO.getProductCatalogueId());
                    		}
                    		
                    		if(!productEligiblityProductCatalogueId.isEmpty() && 
                    				!productCatalogueId.isEmpty() &&
                    				!productEligiblityProductCatalogueId.containsAll(productCatalogueId)){
                            	String msg = "Required Product Catalogue Id: " + productEligiblityProductCatalogueId + " is not found in cart items.";
                            	logger.info(functionName, msg); 
                            	offerProductEligibilityErrorMsg.add(msg);
                            	if (mandatory){
            						logger.info(functionName, "Mandatory rule failed, Return " + productTypeCd + "  as False"); 
            						return false;
            					}
            					if (oneof) {
            						logger.info(functionName, "Add " + productTypeCd + "  as False into OneOf list");
            						oneOfList.put(productTypeCd, Boolean.FALSE);
            						continue;
            					}
                    		} else {
                    			logger.info(functionName, "Required Product Catalogue Id: " + productEligiblityProductCatalogueId + " is found in cart items.");
                    		}
                    	} else {
                    		if (subscribedProducts != null && !subscribedProducts.isEmpty()) {
                                for (SubscribedProductInfoRestVO subscribedProductInfoRestVO : subscribedProducts) {
                                    if (productEligiblity.getProductTypeCd().equalsIgnoreCase(subscribedProductInfoRestVO.getProductTypeCd()) && 
                                    		!productEligiblity.getProductComponentIdentifierList().isEmpty()) {
                                		boolean cidMatch = false;
                                		
                                    	for(ProductCatalogueIdentifier productCatalogueIdentifier: productEligiblity.getProductComponentIdentifierList()){
                                    		if(catalogueIdMatch(subscribedProductInfoRestVO.getProductTierCd(), productCatalogueIdentifier.getProductCatalogueId())){
                                    			cidMatch = true;
                                    			logger.debug(functionName, "Required Product Catalogue Id: " + productCatalogueIdentifier.getProductCatalogueId() + " is found in subscribed products.");
                                    			break;
                                    		}
                                    	}
                                    	
                                    	if(!cidMatch){
                                        	String msg = "Required Product Catalogue Id: " + productEligiblityProductCatalogueId + " is not found in subscribed products.";
                                        	logger.info(functionName, msg); 
                                        	offerProductEligibilityErrorMsg.add(msg);
                                        	if (mandatory){
                        						logger.info(functionName, "Mandatory rule failed, Return " + productTypeCd + "  as False"); 
                        						return false;
                        					}
                        					if (oneof) {
                        						logger.info(functionName, "Add " + productTypeCd + "  as False into OneOf list");
                        						oneOfList.put(productTypeCd, Boolean.FALSE);
                        						continue;
                        					}
                                    	}
                                    }
                                }
                            }
                    	}
            		}
                }
                
                logger.info(functionName, "Pass all Mandatory/OneOf check for " + productTypeCd); 
                // Pass all check set one of as true
                if (oneof) {
                	logger.info(functionName, "Flag " + productTypeCd + " OneOf check as True "); 
					oneOfList.put(productTypeCd, Boolean.TRUE);
				}
            }
            
            logger.info(functionName, "Start verify OneOf list"); 
			// check One of result
			if (!oneOfList.isEmpty()) {
				for (Boolean result : oneOfList.values()) {
					if (result) {
						  logger.info(functionName, "There's a true reulst in OneOf list, clear error msg list, and return true"); 
						offerProductEligibilityErrorMsg.clear();
						return true;
					}	
				}
				logger.info(functionName, "No true reulst in OneOf list, return false"); 
				return false;
			}else {
				 logger.info(functionName, "OneOf list is empty, every rule passed"); 
			}
        }
        // if here either
        //    1. no required Product list
        //    2. all required products matched
        return true;
    }
    
    private static boolean catalogueIdMatch(String productTierCd, String productCatalogueId) {
    	String existingProductCatalogueId = WLNOfferUtil.getInternalCidFromProductTierCd(productTierCd);
    	
    	if(!StringUtils.isBlank(existingProductCatalogueId) && productCatalogueId.equalsIgnoreCase(existingProductCatalogueId)){
    		return true;
    	}
    	
    	return false;
    }

    // NWLN-10262 build existing equipment list from shopping cart context
    public static Map<String, Map<String, List<ExistingFFHEquipmentTypeVO>>> buildExistingEquipmentList(ShoppingCartContextVO shoppingCartCtxVO) {
    	Map<String, Map<String, List<ExistingFFHEquipmentTypeVO>>> existingEquipmentList = new HashMap<String, Map<String, List<ExistingFFHEquipmentTypeVO>>>();
		if (shoppingCartCtxVO != null && shoppingCartCtxVO.getAssignedAndPendingProductResponseVO() != null) {
			List<SubscribedProductInfoRestVO> subscribedProductList = EnterpriseWLNCommonTransformer
					.getAssignedProductsFromVO(shoppingCartCtxVO);
			
			if (subscribedProductList != null && !subscribedProductList.isEmpty()) {
				for (SubscribedProductInfoRestVO productInfo : subscribedProductList) {
					for (ProductInstanceInfoRestVO product : productInfo.getProductInstance()) {
						buildExistingEquipment(product, existingEquipmentList);
					}
				}
			}
		}
		return existingEquipmentList;
	}

    // NWLN-10262 build existing equipment from product component
	private static void buildExistingEquipment(ProductInstanceInfoRestVO product, Map<String, Map<String, List<ExistingFFHEquipmentTypeVO>>> existingEquipmentList) {
		// HSIC
		if (product.getInternetComponent() != null) {
			Map<String, List<ExistingFFHEquipmentTypeVO>> equipmentMap = buildEquipmentFromAddOnPack(product.getInternetComponent().getAddOnPacks());
			if (!equipmentMap.isEmpty()) {
				if (existingEquipmentList.containsKey(EnterpriseWLNSalesServicesConstants.HSIC)) {
					Map<String, List<ExistingFFHEquipmentTypeVO>> map = existingEquipmentList.get(EnterpriseWLNSalesServicesConstants.HSIC);
					existingEquipmentList.put(EnterpriseWLNSalesServicesConstants.HSIC, combineMap(map, equipmentMap));
				} else {
					existingEquipmentList.put(EnterpriseWLNSalesServicesConstants.HSIC, equipmentMap);
				}
			}

		}
	}
	
	// NWLN-10262 build existing equipment from addOn pack, Key=ComponentId Value= list of ExistingFFHEquipmentTypeVO
	private static Map<String, List<ExistingFFHEquipmentTypeVO>> buildEquipmentFromAddOnPack(List<AddOnPackInfoRestVO> packList) {
		Map<String, List<ExistingFFHEquipmentTypeVO>> map = new HashMap<String, List<ExistingFFHEquipmentTypeVO>>();

		if (packList != null) {
			for (AddOnPackInfoRestVO pack : packList) {
				if (pack.getEquipmentList() != null && !pack.getEquipmentList().isEmpty()) {
					String componentId = pack.getComponentId();
					List<ExistingFFHEquipmentTypeVO> list = transferAddOnPackToEquipment(pack);
					if (map.containsKey(componentId)) {
						map.get(componentId).addAll(list);
					} else {
						map.put(componentId, list);
					}
				}
			}
		}

		return map;
	}
	
	// NWLN-10262 transfer product's addon equipment to existing equipment list
	private static List<ExistingFFHEquipmentTypeVO> transferAddOnPackToEquipment(AddOnPackInfoRestVO pack){
		List<ExistingFFHEquipmentTypeVO> list = new ArrayList<ExistingFFHEquipmentTypeVO>();
		for(EquipmentInfoRestVO equipmentInfo: pack.getEquipmentList()) {
			ProductItemIdentifierVO productItemId = new ProductItemIdentifierVO();
			productItemId.setExternalId(pack.getComponentId());

			MultilingualTextVO description = new MultilingualTextVO();
			description.setValueTxt(pack.getComponentName());
			
			ExistingFFHEquipmentTypeVO existingEquipment = new ExistingFFHEquipmentTypeVO();
			existingEquipment.setProductItemIdentifier(productItemId);
			existingEquipment.setEquipmentType(pack.getComponentServiceTypeCd());
			existingEquipment.addEquipmentDescriptionItem(description);
			
			if(EnterpriseWLNSalesServicesConstants.ACQUISITION_TYPE_CD_BUY.equalsIgnoreCase(equipmentInfo.getEquipmentAcquisitionTypeCd())) {
				EquipmentAcquisitionTypeVO acqType = new EquipmentAcquisitionTypeVO();
				acqType.setBuyIndicator(true);
				existingEquipment.setAcquisitionType(acqType);
			}
			
			if(EnterpriseWLNSalesServicesConstants.ACQUISITION_TYPE_CD_RENTAL.equalsIgnoreCase(equipmentInfo.getEquipmentAcquisitionTypeCd())) {
				EquipmentAcquisitionTypeVO acqType = new EquipmentAcquisitionTypeVO();
				acqType.setRentalEquipmentIndicator(true);
				existingEquipment.setAcquisitionType(acqType);
			}
			
			list.add(existingEquipment);
		}
		
		return list;
	}

	// NWLN-10262 combine two map
	private static Map<String, List<ExistingFFHEquipmentTypeVO>> combineMap(Map<String, List<ExistingFFHEquipmentTypeVO>> map1, Map<String, List<ExistingFFHEquipmentTypeVO>> map2) {
		if (map1 == null || map1.isEmpty())
			return map2;
		if (map2 == null || map2.isEmpty())
			return map1;

		for (Entry<String, List<ExistingFFHEquipmentTypeVO>> entry : map2.entrySet()) {
			if (map1.containsKey(entry.getKey())) {
				map1.get(entry.getKey()).addAll(entry.getValue());
			} else {
				map1.put(entry.getKey(), entry.getValue());
			}
		}

		return map1;
	}
}
