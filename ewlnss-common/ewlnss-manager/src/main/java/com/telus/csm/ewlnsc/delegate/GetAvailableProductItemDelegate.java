package com.telus.csm.ewlnsc.delegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.adapter.oqs.domain.Product;
import com.telus.csm.ewlnsc.delegate.response.ProductItemDelegateResponse;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.ExistingFFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.GetAvailableProductItemDelegateResponse;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO2;
import com.telus.csm.ewlnsc.domain.ProductTypeVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.product.AssociatedProductItemVO;
import com.telus.csm.ewlnsc.domain.product.AvailableAccessoryProductItemVO;
import com.telus.csm.ewlnsc.domain.product.AvailableFFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.product.AvailableHomePhoneProductItemVO;
import com.telus.csm.ewlnsc.domain.product.AvailableInternetProductItemVO;
import com.telus.csm.ewlnsc.domain.product.AvailableProductItemResponseVO;
import com.telus.csm.ewlnsc.domain.product.AvailableTelevisionProductItemVO;
import com.telus.csm.ewlnsc.domain.product.FFHEquipmentItemVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemConstraintGroupVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemGroupCategoryVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemVO;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Discount;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Offer;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineEquipment;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineEquipmentItem;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.SweetenerOfferSummary;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;
import com.telus.csm.ewlnsc.helper.EnterpriseWLNOrderUtil;
import com.telus.csm.ewlnsc.helper.WirelineProductHelper;
import com.telus.csm.ewlnsc.transformer.EnterpriseWLNCommonTransformer;
import com.telus.csm.ewlnsc.transformer.GetAvailableProductItemCoreTransformer;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;

public class GetAvailableProductItemDelegate {

	private static Logger logger = Logger.getLogger(GetAvailableProductItemDelegate.class);

	ProductItemDelegate productItemDelegate = new ProductItemDelegate();
	ProductGroupCategorizationDelegate groupDelegate = new ProductGroupCategorizationDelegate();
	ProductItemConstraintGroupDelegate constraintDelegate = new ProductItemConstraintGroupDelegate();

	private class ProductLevelObjects {

		String productTypeCode = null;
		Offer offer = null;
		List<com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.SweetenerOfferSummary> sweeteners = null;

		private String getProductTypeCode() {
			return productTypeCode;
		}
		private void setProductTypeCode(String productTypeCode) {
			this.productTypeCode = productTypeCode;
		}
		private Offer getOffer() {
			return offer;
		}
		private void setOffer(Offer offer) {
			this.offer = offer;
		}
		private List<com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.SweetenerOfferSummary> getSweeteners() {
			return sweeteners;
		}
		private void setSweeteners(List<com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.SweetenerOfferSummary> sweeteners) {
			this.sweeteners = sweeteners;
		}
	}

	/*****************************************/
	/* execute                               */
	/*****************************************/
	public GetAvailableProductItemDelegateResponse execute(String transactionId, ShoppingCartContextVO shoppingCartContextVO ){
		List<Product> assignedProducts = shoppingCartContextVO.getAssignedProducts();
		Map<String, CatalogueItemDO> productCataloguesFromGrid = new HashMap<String, CatalogueItemDO>();
		if (assignedProducts != null && !assignedProducts.isEmpty()) {
			productCataloguesFromGrid = WirelineProductHelper.getProductIdsFromGrid(assignedProducts);
		}
		boolean isLiketoLikeRecontract = false;
		Boolean isInstallerRequired = isInstallerRequired(shoppingCartContextVO);
		
		// Load existing equipment from addon pack equipment list, use for CPE only
		Map<String, Map<String, List<ExistingFFHEquipmentTypeVO>>> existingEquipmentList = EnterpriseWLNOrderUtil.buildExistingEquipmentList(shoppingCartContextVO);
		
		//filter discounts in offers - cross product dependency
//		if (shoppingCartContextVO.getOfferList() != null) {
//			for (GetSalesOfferDetailResponseVO getSalesOfferDetailResponseVO : shoppingCartContextVO.getOfferList()) {
//				EnterpriseWLNOrderUtil.filterDiscount(getSalesOfferDetailResponseVO.getOffer(), shoppingCartContextVO);
//			}
//		}
//		//filter discounts in sweeteners - cross product dependency
//		if (shoppingCartContextVO.getAvailableSweetenerOfferListResponseVO() != null && shoppingCartContextVO.getAvailableSweetenerOfferListResponseVO().getSweetenerOfferSummaryList() != null) {
//			for (com.telus.csm.ewlnsc.domain.schemasclone.SweetenerOfferSummary sweetenerOfferSummary : shoppingCartContextVO.getAvailableSweetenerOfferListResponseVO().getSweetenerOfferSummaryList()) {
//				EnterpriseWLNOrderUtil.filterSweetenerDiscount(sweetenerOfferSummary, shoppingCartContextVO);
//			}
//		}

		//collects the objects required to format the response by product type code
		List<ProductLevelObjects> productLevelObjectsMap = buildProductLevelObjects(shoppingCartContextVO);

		Offer offer = null;
		List<SweetenerOfferSummary> sweeteners = null;
		GetAvailableProductItemDelegateResponse delegResponse = new GetAvailableProductItemDelegateResponse();
		AvailableProductItemResponseVO apiResp = new AvailableProductItemResponseVO();
		delegResponse.setResponse(apiResp);
		delegResponse.setProductItems(new ArrayList<ProductItemVO>());

		for(ProductLevelObjects productObjects: productLevelObjectsMap) {
			offer = productObjects.getOffer();
			sweeteners = productObjects.getSweeteners();
			
			//HSIC
			if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(productObjects.getProductTypeCode())) {
				isLiketoLikeRecontract = WirelineProductHelper.isLikeToLikeReContracting(EnterpriseWLNSalesServicesConstants.HSIC, shoppingCartContextVO, productCataloguesFromGrid);
				GetAvailableProductItemDelegateResponse hsicResponse = this.createResponse(EnterpriseWLNSalesServicesConstants.HSIC, offer, sweeteners, isLiketoLikeRecontract, shoppingCartContextVO);
				if (hsicResponse.getErrorResponse() != null)
					return hsicResponse;
				else {
					List<AvailableInternetProductItemVO> availProdItems = hsicResponse.getResponse().getInternetProductItems();
					AvailableInternetProductItemVO availableProductItem = availProdItems.get(0);
					List<AvailableFFHEquipmentTypeVO> equipments = availableProductItem.getEquipments();
					if (equipments == null) equipments = new ArrayList<AvailableFFHEquipmentTypeVO>();
					List<FFHEquipmentItemVO> equipmentItems = productItemDelegate.extractEquipmentItems(equipments);
					Product assignedInternetProduct = null;
					if (assignedProducts != null) {
						assignedInternetProduct = WirelineProductHelper.getAssignedProducts(assignedProducts, EnterpriseWLNSalesServicesConstants.HSIC);
						if (assignedInternetProduct != null) 
						   {
							addExistingEquipment(equipments, assignedInternetProduct, offer.getOfferId(), isLiketoLikeRecontract, productCataloguesFromGrid, EnterpriseWLNSalesServicesConstants.HSIC);
						    if (!equipments.isEmpty()) {
							 availableProductItem.setEquipments(equipments);
						    } 
					    }    
					}
					filterAvailableEquipmentDeliveryMethod(equipments, isInstallerRequired);
					// Don't pass the salesOfferDetailVO and sweetenerVO as the ProductItems and FFHEquipmentItems already have all the info
					ProductItemDelegateResponse productItemResponse = productItemDelegate.populateProductItems(EnterpriseWLNSalesServicesConstants.HSIC, hsicResponse.getProductItems(), equipmentItems, null, null);				
					List<ProductItemVO> productItemsForHSIC = productItemResponse.getProductItems();
					filterDiscountProductItems(productItemsForHSIC,shoppingCartContextVO,EnterpriseWLNSalesServicesConstants.HSIC);
					if (productItemsForHSIC != null && assignedInternetProduct != null) { 
						addCarryOverProductItems(productItemsForHSIC, assignedInternetProduct, WirelineProductHelper.getTemplateId(offer, EnterpriseWLNSalesServicesConstants.HSIC ), isLiketoLikeRecontract, productCataloguesFromGrid);
					}
					List<ProductItemConstraintGroupVO> productItemConstraintGroups = constraintDelegate.groupProductItemConstraints(EnterpriseWLNSalesServicesConstants.HSIC, productItemsForHSIC, offer);
					productItemDelegate.infuseEquipmentItems(productItemResponse.getEquipmentItems(), equipments);
					List<ProductItemGroupCategoryVO> productItemGroupCategories = groupDelegate.groupProductItems(productItemsForHSIC);
					availableProductItem.setProductItemGroupCategories(productItemGroupCategories);
					availableProductItem.setProductItemConstraintGroupList(productItemConstraintGroups);
					//availableProductItem.setExistingEquipment(availableProductItem.getEquipments().get(0).getExistingEquipmentList());
					apiResp.getInternetProductItems().addAll(availProdItems);
					delegResponse.getProductItems().addAll(productItemsForHSIC);
				}
			}
			
			//TTV
			if(EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(productObjects.getProductTypeCode())) {
				isLiketoLikeRecontract = WirelineProductHelper.isLikeToLikeReContracting(EnterpriseWLNSalesServicesConstants.TTV, shoppingCartContextVO, productCataloguesFromGrid);
				GetAvailableProductItemDelegateResponse ttvResponse = this.createResponse(EnterpriseWLNSalesServicesConstants.TTV, offer, sweeteners, isLiketoLikeRecontract, shoppingCartContextVO);
				if (ttvResponse.getErrorResponse() != null)
					return ttvResponse;
				else {
					List<AvailableTelevisionProductItemVO> availProdItems = ttvResponse.getResponse().getTelevisionProductItems();
					AvailableTelevisionProductItemVO availableProductItem = availProdItems.get(0);
					List<AvailableFFHEquipmentTypeVO> equipments = availableProductItem.getEquipments();
					List<FFHEquipmentItemVO> equipmentItems = productItemDelegate.extractEquipmentItems(equipments);
					
					Product assignedTTVProduct = null;
					if (assignedProducts != null) {
						assignedTTVProduct = WirelineProductHelper.getAssignedProducts(assignedProducts, EnterpriseWLNSalesServicesConstants.TTV);
						if (assignedTTVProduct != null) {
						  addExistingEquipment(equipments, assignedTTVProduct, offer.getOfferId(), isLiketoLikeRecontract, productCataloguesFromGrid, EnterpriseWLNSalesServicesConstants.TTV);
						  if (!equipments.isEmpty()) {
							availableProductItem.setEquipments(equipments);
						  }
					    }

					}
					filterAvailableEquipmentDeliveryMethod(equipments, isInstallerRequired);
					// Don't pass the salesOfferDetailVO and sweetenerVO as the ProductItems and FFHEquipmentItems already have all the info
					ProductItemDelegateResponse productItemResponse = productItemDelegate.populateProductItems(EnterpriseWLNSalesServicesConstants.TTV, ttvResponse.getProductItems(), equipmentItems, null, null);
					
					List<ProductItemVO> productItems = productItemResponse.getProductItems();
					ProductItemDelegateResponse productItemResponseForTTV = productItemDelegate.populateProductItems(EnterpriseWLNSalesServicesConstants.HSIC, productItemResponse.getProductItems(), equipmentItems, null, null);				
					List<ProductItemVO> productItemsForTTV = productItemResponseForTTV.getProductItems();
					filterDiscountProductItems(productItemsForTTV,shoppingCartContextVO,EnterpriseWLNSalesServicesConstants.TTV);
					if (productItems != null && assignedTTVProduct != null) {
						addCarryOverProductItems(productItemsForTTV, assignedTTVProduct, WirelineProductHelper.getTemplateId(offer, EnterpriseWLNSalesServicesConstants.TTV ), isLiketoLikeRecontract, productCataloguesFromGrid);
					}
					List<ProductItemConstraintGroupVO> productItemConstraintGroups = constraintDelegate.groupProductItemConstraints(EnterpriseWLNSalesServicesConstants.TTV, productItems, offer);
					productItemDelegate.infuseEquipmentItems(productItemResponse.getEquipmentItems(), equipments);
					List<ProductItemGroupCategoryVO> productItemGroupCategories = groupDelegate.groupProductItems(productItemsForTTV);
					availableProductItem.setProductItemGroupCategories(productItemGroupCategories);
					availableProductItem.setProductItemConstraintGroupList(productItemConstraintGroups);
					//availableProductItem.setExistingEquipment(availableProductItem.getEquipments().get(0).getExistingEquipmentList());
					apiResp.getTelevisionProductItems().addAll(availProdItems);
					delegResponse.getProductItems().addAll(productItemsForTTV);
				}
			}
			
			//SING
			if(EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(productObjects.getProductTypeCode())) {
				//Like to Like re-contracting is not applicable to SING..
				GetAvailableProductItemDelegateResponse singResponse = this.createResponse(EnterpriseWLNSalesServicesConstants.SING, offer, sweeteners, false, shoppingCartContextVO);
				if (singResponse.getErrorResponse() != null)
					return singResponse;
				else {
					List<AvailableHomePhoneProductItemVO> availProdItems = singResponse.getResponse().getHomePhoneProductItems();
					AvailableHomePhoneProductItemVO availableProductItem = availProdItems.get(0);
					// Don't pass the salesOfferDetailVO and sweetenerVO as the ProductItems already have the Price.  SL doesn't have FFHEquipmentItems
					ProductItemDelegateResponse productItemResponse = productItemDelegate.populateProductItems(EnterpriseWLNSalesServicesConstants.SING, singResponse.getProductItems(), null, null, null);
					List<ProductItemVO> productItemsForSING = productItemResponse.getProductItems();
					filterDiscountProductItems(productItemsForSING,shoppingCartContextVO,EnterpriseWLNSalesServicesConstants.SING);
					Product assignedHomePhoneProduct = null;
					if (assignedProducts != null) {
						assignedHomePhoneProduct = WirelineProductHelper.getAssignedProducts(assignedProducts, EnterpriseWLNSalesServicesConstants.SING);
					}
					if (productItemsForSING != null && assignedHomePhoneProduct != null) {
						addCarryOverProductItems(productItemsForSING, assignedHomePhoneProduct, WirelineProductHelper.getTemplateId(offer, EnterpriseWLNSalesServicesConstants.SING ), isLiketoLikeRecontract, productCataloguesFromGrid);
					}
					
					List<ProductItemConstraintGroupVO> productItemConstraintGroups = constraintDelegate.groupProductItemConstraints(EnterpriseWLNSalesServicesConstants.SING, productItemsForSING, offer);
					List<ProductItemGroupCategoryVO> productItemGroupCategories = groupDelegate.groupProductItems(productItemsForSING);
					availableProductItem.setProductItemGroupCategories(productItemGroupCategories);
					availableProductItem.setProductItemConstraintGroupList(productItemConstraintGroups);
					apiResp.getHomePhoneProductItems().addAll(availProdItems);
					delegResponse.getProductItems().addAll(productItemsForSING);
				}
			}
			
			//CPE NWLN-10255
			if(EnterpriseWLNSalesServicesConstants.CPE.equalsIgnoreCase(productObjects.getProductTypeCode())) {
				logger.info("Shopping Cart has CPE offer, start build available product item list");
				//isLiketoLikeRecontract = WirelineProductHelper.isLikeToLikeReContracting(EnterpriseWLNSalesServicesConstants.CPE, shoppingCartContextVO, productCataloguesFromGrid);
				GetAvailableProductItemDelegateResponse cpeResponse = this.createResponse(EnterpriseWLNSalesServicesConstants.CPE, offer, sweeteners, false, shoppingCartContextVO);
				if (cpeResponse.getErrorResponse() != null)
					return cpeResponse;
				else {
					List<AvailableAccessoryProductItemVO> availProdItems = cpeResponse.getResponse().getAccessoryProductItems();
					AvailableAccessoryProductItemVO availableProductItem = availProdItems.get(0);
					List<AvailableFFHEquipmentTypeVO> equipments = availableProductItem.getEquipments();
					if (equipments == null) equipments = new ArrayList<AvailableFFHEquipmentTypeVO>();
					List<FFHEquipmentItemVO> equipmentItems = productItemDelegate.extractEquipmentItems(equipments);
					logger.info("CPE available equipment items size = " + equipmentItems.size());
					if (existingEquipmentList != null && !existingEquipmentList.isEmpty()) {
						logger.info("Has existing equipment, start adding exisitng equipment");
						addExistingEquipment(equipments, offer, existingEquipmentList);
					}
					filterAvailableEquipmentDeliveryMethod(equipments, isInstallerRequired);
					// Don't pass the salesOfferDetailVO and sweetenerVO as the ProductItems and FFHEquipmentItems already have all the info
					ProductItemDelegateResponse productItemResponse = productItemDelegate.populateProductItems(EnterpriseWLNSalesServicesConstants.CPE, cpeResponse.getProductItems(), equipmentItems, null, null);				
					List<ProductItemVO> productItemsForcpe = productItemResponse.getProductItems();
//					filterDiscountProductItems(productItemsForcpe,shoppingCartContextVO,EnterpriseWLNSalesServicesConstants.CPE);
//					if (productItemsForcpe != null && assignedInternetProduct != null) { 
//						addCarryOverProductItems(productItemsForcpe, assignedInternetProduct, WirelineProductHelper.getTemplateId(offer, EnterpriseWLNSalesServicesConstants.CPE ), isLiketoLikeRecontract, productCataloguesFromGrid);
//					}
					List<ProductItemConstraintGroupVO> productItemConstraintGroups = constraintDelegate.groupProductItemConstraints(EnterpriseWLNSalesServicesConstants.CPE, productItemsForcpe, offer);
					productItemDelegate.infuseEquipmentItems(productItemResponse.getEquipmentItems(), equipments);
					List<ProductItemGroupCategoryVO> productItemGroupCategories = groupDelegate.groupProductItems(productItemsForcpe);
					availableProductItem.setProductItemGroupCategories(productItemGroupCategories);
					availableProductItem.setProductItemConstraintGroupList(productItemConstraintGroups);
					apiResp.getAccessoryProductItems().addAll(availProdItems);
					delegResponse.getProductItems().addAll(productItemsForcpe);
				}
			}
		}
			
		return delegResponse;
	}
	

	private void filterDiscountProductItems(List<ProductItemVO> productItems,ShoppingCartContextVO contextVO,String productType) {
		List<ProductItemVO> removedProductItemList = new ArrayList<ProductItemVO>();
		if(!CollectionUtils.isEmpty(productItems)){
			for(ProductItemVO productItemVO : productItems){
				if(productItemVO.getMarketOfferClassification()!=null && productItemVO.getMarketOfferClassification()!=null && 
						Boolean.TRUE.equals(productItemVO.getMarketOfferClassification().isDiscountInd())){
					//use the logic to filter out the cross product discounts. 
					if(!productItemDiscountIsValid(productItemVO,contextVO,productType)){
						removedProductItemList.add(productItemVO);						
					}
				}
				//NWLN-8321 to filter out the cross product discounts under AssociatedProductItemsList of productItems. 
				if(CollectionUtils.isNotEmpty(productItemVO.getAssociatedProductItems())) {
					List<AssociatedProductItemVO> removedAssociatedProductItemList = new ArrayList<AssociatedProductItemVO>();					
					for(AssociatedProductItemVO associatedProductItemVO : productItemVO.getAssociatedProductItems()) {
					   if(associatedProductItemVO.getMarketOfferClassification()!=null && 
						  Boolean.TRUE.equals(associatedProductItemVO.getMarketOfferClassification().isDiscountInd())){
							ProductItemVO associatedProdItem = createProdItemFromAssociated(associatedProductItemVO);
							if(!productItemDiscountIsValid(associatedProdItem,contextVO,productType)){
								removedAssociatedProductItemList.add(associatedProductItemVO);						
							}	
						}
					}
					if(!CollectionUtils.isEmpty(removedAssociatedProductItemList)){
						productItemVO.getAssociatedProductItems().removeAll(removedAssociatedProductItemList);
					}					
				}
			}
		}
		if(!CollectionUtils.isEmpty(removedProductItemList)){
			productItems.removeAll(removedProductItemList);
		}
	}
	//NWLN-8321
	private ProductItemVO createProdItemFromAssociated(AssociatedProductItemVO associatedProdItem) {
		ProductItemVO prodItem = new ProductItemVO();

		prodItem.setProductItemIdentifier(associatedProdItem.getProductItemIdentifier());
		prodItem.setDiscountCode(associatedProdItem.getDiscountCode());

		return prodItem;
	}
	

	private boolean productItemDiscountIsValid(ProductItemVO productItemVO, ShoppingCartContextVO contextVO, String productType) {
		if(!CollectionUtils.isEmpty(contextVO.getOfferList())){
			List<GetSalesOfferDetailResponseVO2> offerList = contextVO.getOfferList();
			//get the filtered discounts for the specified product.
			for(GetSalesOfferDetailResponseVO2 offer : offerList){
				if(offer.getOffer() != null){
					for(WirelineOfferProduct wirelineProduct : offer.getOffer().getOfferProduct().getWirelineOfferProductList()){
						if(wirelineProduct.getProductTypeCode().equalsIgnoreCase(productType)){
							List<Discount> tomDiscountList = wirelineProduct.getDiscountList();
							if(productItemIsValid(productItemVO,tomDiscountList,productType,contextVO)){
								return true;
							}
						}
					}
				}
			}
			
		}
		return false;
	}


	private boolean productItemIsValid(ProductItemVO productItemVO, List<Discount> tomDiscountList, String productType, ShoppingCartContextVO contextVO) {
		
		//Iterating over the list of discounts returned 
		for(Discount tomDiscount : tomDiscountList){
			if(EnterpriseWLNCommonTransformer.discountCatalogueIdMatches(tomDiscount, productItemVO) /*&& EnterpriseWLNOrderUtil.isDiscountAvailableForCrossProduct(contextVO, productType, tomDiscount)*/){
				return true;
			}
		}
		return false;
	}

	private List<ProductLevelObjects> buildProductLevelObjects(ShoppingCartContextVO shoppingCartContextVO) {
		List<ProductLevelObjects> result = new ArrayList<ProductLevelObjects>();

		ShoppingCartVO cart = shoppingCartContextVO.getShoppingCartVO();
		if(!CollectionUtils.isEmpty(cart.getCartItemList())){
			for (CartItemVO cartItem : cart.getCartItemList()) {
				if (!cartItem.isSalesOrderItem()) {
					continue;
				}
				String cartItemRelationId = cartItem.getCartItemRelationId();
				if (StringUtils.isBlank(cartItemRelationId)) {
					logger.error("missing cartItemRelationId. value=" + cartItemRelationId);
				}
				ProductTypeVO cartItemProducts = cartItem.getProducts();
				String cartItemOfferId = cartItem.getProductMarketOffering().getOfferHeader().getOfferId();
				Offer offer = null;
				GetSalesOfferDetailResponseVO2 getSalesOfferDetailResponseVO = shoppingCartContextVO.getOfferByCartItemOfferId(cartItemOfferId);
				if (getSalesOfferDetailResponseVO != null)  {
					offer = getSalesOfferDetailResponseVO.getOffer();
				}
	
				if (cartItemProducts.getInternetProduct() != null) {
					String productType = EnterpriseWLNSalesServicesConstants.HSIC;
					populateMap(shoppingCartContextVO, result, cartItemRelationId, offer,
							productType);
				}
				if (cartItemProducts.getTelevisionProduct() != null) {
					String productType = EnterpriseWLNSalesServicesConstants.TTV;
					populateMap(shoppingCartContextVO, result, cartItemRelationId, offer,
							productType);
				}
				if (cartItemProducts.getHomePhoneProduct() != null) {
					String productType = EnterpriseWLNSalesServicesConstants.SING;
					populateMap(shoppingCartContextVO, result, cartItemRelationId, offer,
							productType);
				}
				if (cartItemProducts.getAccessoryProduct() != null) {
					String productType = EnterpriseWLNSalesServicesConstants.CPE;
					populateMap(shoppingCartContextVO, result, cartItemRelationId, offer,
							productType);
				}
			}
		}
		return result;
	}

	private void populateMap(ShoppingCartContextVO shoppingCartContextVO, List<ProductLevelObjects> result,
			String cartItemRelationId, Offer offer,
			String productType) {

		ProductLevelObjects productObject = new ProductLevelObjects();
		productObject.setProductTypeCode(productType);	
		productObject.setOffer(offer);

		List<SweetenerOfferSummary> sweeteners = shoppingCartContextVO.getSweetnersByProduct(productType, cartItemRelationId);
		productObject.setSweeteners(sweeteners);
		
		result.add(productObject);
	}

	/***********************************/
	/* createResponse                  */
	/**
	 * @param isLiketoLikeRecontract *********************************/
	private GetAvailableProductItemDelegateResponse createResponse(String prodType
			 										   , Offer offer
			 										   , List<SweetenerOfferSummary> sweeteners, boolean isLiketoLikeRecontract, ShoppingCartContextVO shoppingCartContextVO){

		GetAvailableProductItemCoreTransformer transformer = new GetAvailableProductItemCoreTransformer();
		return transformer.transform(prodType, offer, sweeteners, isLiketoLikeRecontract, shoppingCartContextVO);
	}
	/**
	 * 
	 * @param productItems
	 * @param assignedInternetProduct
	 * @param productCataloguesFromGrid 
	 * @param offerId
	 */
	private void addCarryOverProductItems(List<ProductItemVO> productItems, Product assignedInternetProduct, String templateId, boolean isLikeToLikeContract, Map<String, CatalogueItemDO> productCataloguesFromGrid) {
		WirelineProductHelper.getProductComponentItems(assignedInternetProduct, assignedInternetProduct, productItems, templateId, isLikeToLikeContract, productCataloguesFromGrid);
	}
	/**
	 * 
	 * @param equipments
	 * @param assignedInternetProduct
	 * @param offerId
	 * @param productCataloguesFromGrid 
	 */
	private void addExistingEquipment(List<AvailableFFHEquipmentTypeVO> equipments, Product assignedInternetProduct, long offerId, boolean likeToLike, Map<String, CatalogueItemDO> productCataloguesFromGrid, String prodType) {
		if (equipments.isEmpty()) {
			AvailableFFHEquipmentTypeVO equipment = new AvailableFFHEquipmentTypeVO();
			WirelineProductHelper.getAssignedInternetEquipment(assignedInternetProduct, equipment, likeToLike, productCataloguesFromGrid, prodType);
			if (equipment.getExistingEquipmentList() != null) {
				equipments.add(equipment);
			}
		}
		for (AvailableFFHEquipmentTypeVO availableEquipment : equipments) {
			WirelineProductHelper.getAssignedInternetEquipment(assignedInternetProduct, availableEquipment, likeToLike, productCataloguesFromGrid, prodType);
		}
	}
	
	// NWLN-10262 Add existing equipment from existing equipment map (equipments in AddonPack), use for CPE only 
	private void addExistingEquipment(List<AvailableFFHEquipmentTypeVO> equipments, Offer offer,
			Map<String, Map<String, List<ExistingFFHEquipmentTypeVO>>> existingEquipmentMap) {
		
		for (AvailableFFHEquipmentTypeVO availableEquipment : equipments) {
			
			// get product catalogue id from available equipment list
			String productItemId = getAvailableEquipmentItem(availableEquipment).getProductItemIdentifier().getProductCatalogueId();

			for (WirelineOfferProduct product : offer.getOfferProduct().getWirelineOfferProductList()) {
				// get template product type from offer
				String templateProductType = product.getProductTemplateProductTypeCode();
				
				// existing equipment map has template product type?
				if (existingEquipmentMap.containsKey(templateProductType)) {
					// Yes, load up existing equipment map
					Map<String, List<ExistingFFHEquipmentTypeVO>> equipmentMap = existingEquipmentMap
							.get(templateProductType);

					// find the wireline equipment with match product catalogue id
					for (WirelineEquipment offerEquipment : product.getWirelineEquipmentList()) {
						String productCatalogueId = offerEquipment.getProductCatalogueItem()
								.getProductCatalogueIdentifier().getProductCatalogueId();
						String offerExternalId = offerEquipment.getProductCatalogueItem()
								.getProductCatalogueIdentifier().getExternalProductCatalogId();

						logger.info("Matching existing equipment to offer and available equipment Offer_catalogueId = " + productCatalogueId + "Offer_externalId = " + offerExternalId + " available_item_id = " + productItemId);
						
						// match product catelogue id between available and offer, existing map contains offer's external id
						if (productCatalogueId.equalsIgnoreCase(productItemId)
								&& equipmentMap.containsKey(offerExternalId)) {
							logger.info("find match existing equipment, start enrich it");
							// enrich equipment details
							enrichEquipment(availableEquipment, offerEquipment, equipmentMap.get(offerExternalId));
						}
					}
				}
			}
		}
	}
	
	// NWLN-10262 get equipment item from available equipment, should only contains one item
	private FFHEquipmentItemVO getAvailableEquipmentItem(AvailableFFHEquipmentTypeVO availableEquipment) {
		if(availableEquipment.getBuyEquipmentList() != null && !availableEquipment.getBuyEquipmentList().isEmpty())
			return availableEquipment.getBuyEquipmentList().get(0);
		
		if(availableEquipment.getRentalEquipmentList() != null && !availableEquipment.getRentalEquipmentList().isEmpty())
			return availableEquipment.getRentalEquipmentList().get(0);
		
		if(availableEquipment.getClientOwnedEquipmentList() != null && !availableEquipment.getClientOwnedEquipmentList().isEmpty())
			return availableEquipment.getClientOwnedEquipmentList().get(0);
		
		return null;
	}
	
	// NWLN-10262 get wireline quipment item from offer, should only contains one item
	private WirelineEquipmentItem getWirelineEquipmentItem(WirelineEquipment offerEquipment) {
		if(offerEquipment.getPurchaseEquipmentList() != null && !offerEquipment.getPurchaseEquipmentList().isEmpty())
			return offerEquipment.getPurchaseEquipmentList().get(0);
		
		if(offerEquipment.getRentalEquipmentList() != null && !offerEquipment.getRentalEquipmentList().isEmpty())
			return offerEquipment.getRentalEquipmentList().get(0);
		
		if(offerEquipment.getByodEquipmentList() != null && !offerEquipment.getByodEquipmentList().isEmpty())
			return offerEquipment.getByodEquipmentList().get(0);
		
		return null;
	}
	
	// NWLN-10262 enrich existing equipments from Offer and attach to available equipment item
	private void enrichEquipment(AvailableFFHEquipmentTypeVO availableEquipment, WirelineEquipment offerEquipment, List<ExistingFFHEquipmentTypeVO> existingEquipmentList) {
		FFHEquipmentItemVO availableEquipmentItem = getAvailableEquipmentItem(availableEquipment);
		WirelineEquipmentItem offerEquipmentItem = getWirelineEquipmentItem(offerEquipment);
		
		if(availableEquipmentItem == null || offerEquipmentItem == null) return;
		
		for(ExistingFFHEquipmentTypeVO existingEquipment: existingEquipmentList) {
			existingEquipment.setMaterialItemCode(offerEquipmentItem.getMaterialItemCode());
			existingEquipment.setProductItemIdentifier(availableEquipmentItem.getProductItemIdentifier());
			existingEquipment.setEquipmentDescription(availableEquipmentItem.getEquipmentDescription());
			existingEquipment.setEquipmentType(availableEquipmentItem.getEquipmentType());
			existingEquipment.setEquipmentSubType(availableEquipmentItem.getEquipmentSubType());
			existingEquipment.setCarryOver(true);
			existingEquipment.setExisting(true);
			availableEquipment.addExistingEquipment(existingEquipment);
		}
	}
	
	private void filterAvailableEquipmentDeliveryMethod(List<AvailableFFHEquipmentTypeVO> equipments, Boolean isInstallerRequired) {
		if(isInstallerRequired == null) return;
		
		for(AvailableFFHEquipmentTypeVO equipment: equipments) {
			if(equipment.getBuyEquipmentList() != null)
				filterEquipmentItemDeliveryMethod(equipment.getBuyEquipmentList(), isInstallerRequired);
			if(equipment.getRentalEquipmentList() != null)
				filterEquipmentItemDeliveryMethod(equipment.getRentalEquipmentList(), isInstallerRequired);
		}
	}
	
	private void filterEquipmentItemDeliveryMethod(List<FFHEquipmentItemVO> equipments, Boolean isInstallerRequired) {
		for(FFHEquipmentItemVO equipment: equipments) {
			List<String> deliveryMethods = equipment.getDeliveryMethods();
			
			List<String> installerMethod = new ArrayList<String>();
			installerMethod.add(EnterpriseWLNSalesServicesConstants.DELIVERY_METHOD_INSTALLER);
			
			if(deliveryMethods == null || deliveryMethods.isEmpty()) continue;
			
			// Has installer inside list with other methods
			if(deliveryMethods.contains(EnterpriseWLNSalesServicesConstants.DELIVERY_METHOD_INSTALLER) && deliveryMethods.size() > 1) {
				if(isInstallerRequired) {
					// Remove all other delivery method, only leave INSTALLER
					equipment.setDeliveryMethods(installerMethod);
				}else {
					// Remove INSTALLER and leave other delivery method
					deliveryMethods.remove(EnterpriseWLNSalesServicesConstants.DELIVERY_METHOD_INSTALLER);
				}
			}
			
		}
	}
	
	private Boolean isInstallerRequired(ShoppingCartContextVO shoppingCartContextVO) {
		Boolean result = null;
		logger.info("Check if installer required");
		if(shoppingCartContextVO.getFeasibilityResponseVO() != null) {
			if(!shoppingCartContextVO.getFeasibilityResponseVO().isMsgHasError()) {
				List<String> installTypeFW = shoppingCartContextVO.getFeasibilityResponseVO().getInstallTypeFW();
				// Has any FW?
				if(installTypeFW != null && !installTypeFW.isEmpty()) {
					logger.info("Has FW list, set isInstallerRequired = true");
					result = Boolean.TRUE;
				}else {
					logger.info("No FW list, set isInstallerRequired = false");
					result = Boolean.FALSE;
				}
			}else {
				logger.info("Fesibility call failed, isMsgHasError = true, set isInstallerRequired = null");
			}
		}else {
			logger.info("No fesibility response (didn't call fesibility), set isInstallerRequired = false");
			result = Boolean.FALSE;
		}
		
		return result;
	}
}
