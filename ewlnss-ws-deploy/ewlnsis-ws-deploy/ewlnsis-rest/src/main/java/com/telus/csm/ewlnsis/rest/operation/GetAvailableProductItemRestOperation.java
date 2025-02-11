package com.telus.csm.ewlnsis.rest.operation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.domain.CharacteristicOptionVO;
import com.telus.csm.ewlnsc.domain.CharacteristicVO;
import com.telus.csm.ewlnsc.domain.EquipmentAcquisitionTypeVO;
import com.telus.csm.ewlnsc.domain.ExistingFFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.GetAvailableProductItemDelegateResponse;
import com.telus.csm.ewlnsc.domain.GroupCategoryVO;
import com.telus.csm.ewlnsc.domain.InstallmentOptionVO;
import com.telus.csm.ewlnsc.domain.MoneyVO;
import com.telus.csm.ewlnsc.domain.MultilingualTextVO;
import com.telus.csm.ewlnsc.domain.PriceDiscountVO;
import com.telus.csm.ewlnsc.domain.PriceVO;
import com.telus.csm.ewlnsc.domain.ProductItemIdentifierVO;
import com.telus.csm.ewlnsc.domain.product.AssociatedProductItemVO;
import com.telus.csm.ewlnsc.domain.product.AvailableAccessoryProductItemVO;
import com.telus.csm.ewlnsc.domain.product.AvailableFFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.product.AvailableHomePhoneProductItemVO;
import com.telus.csm.ewlnsc.domain.product.AvailableHomeSecurityProductItemVO;
import com.telus.csm.ewlnsc.domain.product.AvailableInternetProductItemVO;
import com.telus.csm.ewlnsc.domain.product.AvailableProductItemResponseVO;
import com.telus.csm.ewlnsc.domain.product.AvailableTelevisionProductItemVO;
import com.telus.csm.ewlnsc.domain.product.FFHEquipmentItemVO;
import com.telus.csm.ewlnsc.domain.product.MarketOfferClassificationVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemConstraintGroupVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemGroupCategoryVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemListVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemVO;
import com.telus.csm.ewlnsc.domain.product.QuantityAllowedVO;
import com.telus.csm.ewlnsc.rest.exception.EssRestErrorException;
import com.telus.csm.ewlnsc.rest.exception.EssRestException;
import com.telus.csm.ewlnsc.rest.exception.EssRestSystemErrorException;
import com.telus.csm.ewlnsc.rest.util.RESTResponseMessageUtil;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsis.core.operation.GetAvailableProductItemCoreOperation;
import com.telus.csm.ewlnsis.rest.domain.AssociatedProductItem;
import com.telus.csm.ewlnsis.rest.domain.AvailableAccessoryProductItem;
import com.telus.csm.ewlnsis.rest.domain.AvailableHomePhoneProductItem;
import com.telus.csm.ewlnsis.rest.domain.AvailableHomeSecurityProductItem;
import com.telus.csm.ewlnsis.rest.domain.AvailableInternetProductItem;
import com.telus.csm.ewlnsis.rest.domain.AvailableProductItemResponse;
import com.telus.csm.ewlnsis.rest.domain.AvailableTelevisionProductItem;
import com.telus.csm.ewlnsis.rest.domain.AvailableWirelineEquipmentType;
import com.telus.csm.ewlnsis.rest.domain.Characteristic;
import com.telus.csm.ewlnsis.rest.domain.CharacteristicOption;
import com.telus.csm.ewlnsis.rest.domain.EquipmentAcquisitionType;
import com.telus.csm.ewlnsis.rest.domain.ExistingWirelineEquipmentType;
import com.telus.csm.ewlnsis.rest.domain.GetAvailableProductItemErrorResponse;
import com.telus.csm.ewlnsis.rest.domain.GroupCategory;
import com.telus.csm.ewlnsis.rest.domain.InstallmentOption;
import com.telus.csm.ewlnsis.rest.domain.MarketOfferClassification;
import com.telus.csm.ewlnsis.rest.domain.Money;
import com.telus.csm.ewlnsis.rest.domain.MultilingualText;
import com.telus.csm.ewlnsis.rest.domain.Price;
import com.telus.csm.ewlnsis.rest.domain.PriceDiscount;
import com.telus.csm.ewlnsis.rest.domain.ProductItem;
import com.telus.csm.ewlnsis.rest.domain.ProductItemCategory;
import com.telus.csm.ewlnsis.rest.domain.ProductItemConstraintGroupList;
import com.telus.csm.ewlnsis.rest.domain.ProductItemConstraintList;
import com.telus.csm.ewlnsis.rest.domain.ProductItemGroupCategory;
import com.telus.csm.ewlnsis.rest.domain.ProductItemIdentifier;
import com.telus.csm.ewlnsis.rest.domain.QuantityAllowed;
import com.telus.csm.ewlnsis.rest.domain.ResponseMessage;
import com.telus.csm.ewlnsis.rest.domain.WirelineEquipmentItem;
import com.telus.csm.ewlnss.rest.time.DateTime;

import weblogic.wsee.util.StringUtil;

public class GetAvailableProductItemRestOperation {

	private static Logger logger = Logger.getLogger(GetAvailableProductItemRestOperation.class);

	public AvailableProductItemResponse execute(String transactionId, String shoppingCartId, String commerceCartitemId) {
		logger.info("FIFA flow - transactionId="+ transactionId +", shoppingCartId="+ shoppingCartId +", commerceCartitemId="+ commerceCartitemId);
		GetAvailableProductItemDelegateResponse coreResult = null ;
		try {
			GetAvailableProductItemCoreOperation coreOperation = new GetAvailableProductItemCoreOperation();
			if(commerceCartitemId != null && ! StringUtil.isEmpty(commerceCartitemId)) {
				coreResult = coreOperation.execute(transactionId, shoppingCartId, commerceCartitemId);
			}
			else {				
				coreResult = coreOperation.execute(transactionId, shoppingCartId);
			}

			if (coreResult.getErrorResponse() != null) {
				throw new EssRestErrorException(coreResult.getErrorResponse());
			}
			
			return this.convertResponse(coreResult.getResponse());
		} catch (EssRestException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Unknown Exception", e);
			GetAvailableProductItemErrorResponse errorResponse = new GetAvailableProductItemErrorResponse();
			ResponseMessage responseMessagesItem = JsonUtil
					.parseJsonToObject(RESTResponseMessageUtil.getResponseMessageJson(e), ResponseMessage.class);
			errorResponse.addResponseMessagesItem(responseMessagesItem);
			throw new EssRestSystemErrorException(errorResponse);
		}
	}
	/**
	 * this is to support FIFA z commerce shopping cart and NOT compass SC
	 * @param availProdItemVOList
	 * @return
	 */
//	public AvailableProductItemResponse execute(String transactionId, String shoppingCartId, String commerceCartitemId) {
//
//		try {
//			GetAvailableProductItemCoreOperation coreOperation = new GetAvailableProductItemCoreOperation();
//			GetAvailableProductItemDelegateResponse coreResult = coreOperation.execute(transactionId, shoppingCartId);
//
//			if (coreResult.getErrorResponse() != null) {
//				throw new EssRestErrorException(coreResult.getErrorResponse());
//			}
//			
//			return this.convertResponse(coreResult.getResponse());
//		} catch (EssRestException e) {
//			throw e;
//		} catch (Exception e) {
//			logger.error("Unknown Exception", e);
//			GetAvailableProductItemErrorResponse errorResponse = new GetAvailableProductItemErrorResponse();
//			ResponseMessage responseMessagesItem = JsonUtil
//					.parseJsonToObject(RESTResponseMessageUtil.getResponseMessageJson(e), ResponseMessage.class);
//			errorResponse.addResponseMessagesItem(responseMessagesItem);
//			throw new EssRestSystemErrorException(errorResponse);
//		}
//	}
	private AvailableProductItemResponse convertResponse(AvailableProductItemResponseVO respVO) {
		AvailableProductItemResponse response = new AvailableProductItemResponse();

		List<AvailableInternetProductItem> internetProductItems = this.convertInternetProduct(respVO.getInternetProductItems());		  
		List<AvailableTelevisionProductItem> televisionProductItems = this.convertTVProduct(respVO.getTelevisionProductItems());		  
		List<AvailableHomePhoneProductItem> homePhoneProductItems = this.convertHomePhoneProduct(respVO.getHomePhoneProductItems());
		List<AvailableAccessoryProductItem> accessoryProductItems = this.convertAccessoryProduct(respVO.getAccessoryProductItems());

		response.setInternetProductItems(internetProductItems);
		response.setTelevisionProductItems(televisionProductItems);
		response.setHomePhoneProductItems(homePhoneProductItems);
		response.setAccessoryProductItems(accessoryProductItems);

		//FIFA-SHS
		response.setSmartHomeSecurityProductItems( convertHomeSecurityProduct(respVO.getHomeSecurityProductItems() ) );
		
		return response;
	}
	
	private List<AvailableInternetProductItem> convertInternetProduct(List<AvailableInternetProductItemVO> availProdItemVOList) {
		List<AvailableInternetProductItem> prodItemList = new ArrayList<AvailableInternetProductItem>();
		if (CollectionUtils.isEmpty(availProdItemVOList))
			return prodItemList;
		
		for (AvailableInternetProductItemVO prodItemVO : availProdItemVOList) {
			AvailableInternetProductItem availProdItem = new AvailableInternetProductItem();			
			List<ProductItemGroupCategory> productItemGroupCategories = this.convertProdItemGrpCatgryLists(prodItemVO.getProductItemGroupCategories());		  
			List<AvailableWirelineEquipmentType> equipments = this.convertEquipmentType(prodItemVO.getEquipments());
			
			// Gary - 2018-09-26
			List<ProductItemConstraintGroupList> constraintGroupList = this.convertConstraintGrouplist(prodItemVO.getProductItemConstraintGroupList()); // Gary - 2018-09-24 QC67396 
			
			availProdItem.setProductItemGroupCategories(productItemGroupCategories);
			availProdItem.setAvailableEquipmentList(equipments);
			availProdItem.setProductItemConstraintGroupList(constraintGroupList);  // Gary - 2018-09-24 QC67396 
			prodItemList.add(availProdItem);
		}

		return prodItemList;
	}

	private List<AvailableTelevisionProductItem> convertTVProduct(List<AvailableTelevisionProductItemVO> availProdItemVOList) {
		List<AvailableTelevisionProductItem> prodItemList = new ArrayList<AvailableTelevisionProductItem>();
		if (CollectionUtils.isEmpty(availProdItemVOList))
			return prodItemList;
		
		for (AvailableTelevisionProductItemVO prodItemVO : availProdItemVOList) {
			AvailableTelevisionProductItem availProdItem = new AvailableTelevisionProductItem();			
			List<ProductItemGroupCategory> productItemGroupCategories = this.convertProdItemGrpCatgryLists(prodItemVO.getProductItemGroupCategories());		  
			List<AvailableWirelineEquipmentType> equipments = this.convertEquipmentType(prodItemVO.getEquipments());
			
			// Gary - 2018-09-24 QC67396
			List<ProductItemConstraintGroupList> constraintGroupList = this.convertConstraintGrouplist(prodItemVO.getProductItemConstraintGroupList());  
			
			availProdItem.setProductItemGroupCategories(productItemGroupCategories);
			availProdItem.setEquipments(equipments);
			availProdItem.setProductItemConstraintGroupList(constraintGroupList); // Gary - 2018-09-24 QC67396 
			prodItemList.add(availProdItem);
		}

		return prodItemList;
	}
	
	private List<AvailableAccessoryProductItem> convertAccessoryProduct(List<AvailableAccessoryProductItemVO> availProdItemVOList) {
		List<AvailableAccessoryProductItem> prodItemList = new ArrayList<AvailableAccessoryProductItem>();
		if (CollectionUtils.isEmpty(availProdItemVOList))
			return prodItemList;
		
		for (AvailableAccessoryProductItemVO prodItemVO : availProdItemVOList) {
			AvailableAccessoryProductItem availProdItem = new AvailableAccessoryProductItem();			
			List<ProductItemGroupCategory> productItemGroupCategories = this.convertProdItemGrpCatgryLists(prodItemVO.getProductItemGroupCategories());		  
			List<AvailableWirelineEquipmentType> equipments = this.convertEquipmentType(prodItemVO.getEquipments());

			List<ProductItemConstraintGroupList> constraintGroupList = this.convertConstraintGrouplist(prodItemVO.getProductItemConstraintGroupList()); 
			
			availProdItem.setProductItemGroupCategories(productItemGroupCategories);
			availProdItem.setAvailableEquipmentList(equipments);
			availProdItem.setProductItemConstraintGroupList(constraintGroupList); 
			prodItemList.add(availProdItem);
		}

		return prodItemList;
	}

	/*****************************************************/
	/*  convertConstraintGrouplist to json output        */
	/*****************************************************/
	// Gary - 2018-09-24 QC67396 
	private List<ProductItemConstraintGroupList> convertConstraintGrouplist(List<ProductItemConstraintGroupVO> productItemConstraingGroupVOList){ //ProductItemConstraintGroupVO productItemConstraintGroupVO){
		List<ProductItemConstraintGroupList> list = new ArrayList<ProductItemConstraintGroupList>();
		
		ProductItemConstraintGroupList item = new ProductItemConstraintGroupList();
		item.setConstraintGroupTypeCd("PRICING"); //NWLN-7789
		item.setProductItemConstraintList(new ArrayList<ProductItemConstraintList>());
		list.add(item);
		
		for (ProductItemConstraintGroupVO productItemConstraintGroupVO : productItemConstraingGroupVOList){
			
			ProductItemConstraintList productItemConstraintList = new ProductItemConstraintList();
			QuantityAllowed quantityAllowed = new QuantityAllowed();
			quantityAllowed.setMaximumQty(productItemConstraintGroupVO.getFreePricingQuantityAllowed().getMaxItemQty()); 
			quantityAllowed.setMinimumQty(productItemConstraintGroupVO.getFreePricingQuantityAllowed().getMinItemQty());
			// NWLN-7782 - ESS REST - Schema update for getAvailableProductItem

			productItemConstraintList.setProductItemList(new ArrayList<ProductItemIdentifier>());
			productItemConstraintList.setEligibleForMinMaxPricingInd(true);
			productItemConstraintList.setFreePricingQuantityAllowed(quantityAllowed);
			
			if(StringUtils.isNotBlank(productItemConstraintGroupVO.getConstraintType())) {
				productItemConstraintList.setConstraintTypeCd(productItemConstraintGroupVO.getConstraintType()); //NWLN-7789
			}
			
			if (productItemConstraintGroupVO != null && productItemConstraintGroupVO.getProductItemConstraintListVO() != null && productItemConstraintGroupVO.getProductItemConstraintListVO().getProductItemList() != null){
				for (ProductItemListVO productItemListVO : productItemConstraintGroupVO.getProductItemConstraintListVO().getProductItemList()){
					ProductItemIdentifier productItemIdentifier = new ProductItemIdentifier();
					productItemIdentifier.setParentProductCatalogueId(productItemListVO.getParentProductCatalogueId());
					productItemIdentifier.setProductCatalogueId(productItemListVO.getProductCatalogueId());
				
					// NWLN-7782 - ESS REST - Schema update for getAvailableProductItem
					productItemConstraintList.getProductItemList().add(productItemIdentifier);
				} 
			}
			item.getProductItemConstraintList().add(productItemConstraintList);
		}
		
		return list;
	}

	private List<AvailableHomePhoneProductItem> convertHomePhoneProduct(List<AvailableHomePhoneProductItemVO> availProdItemVOList) {
		List<AvailableHomePhoneProductItem> prodItemList = new ArrayList<AvailableHomePhoneProductItem>();
		if (CollectionUtils.isEmpty(availProdItemVOList))
			return prodItemList;
		
		for (AvailableHomePhoneProductItemVO prodItemVO : availProdItemVOList) {
			AvailableHomePhoneProductItem availProdItem = new AvailableHomePhoneProductItem();			
			List<ProductItemGroupCategory> productItemGroupCategories = this.convertProdItemGrpCatgryLists(prodItemVO.getProductItemGroupCategories());		  
			
			// Gary - 2018-09-24 QC67396
			List<ProductItemConstraintGroupList> constraintGroupList = this.convertConstraintGrouplist(prodItemVO.getProductItemConstraintGroupList()); 
			
			availProdItem.setProductItemGroupCategories(productItemGroupCategories);
			availProdItem.setProductItemConstraintGroupList(constraintGroupList);  // Gary - 2018-09-24 QC67396 
			prodItemList.add(availProdItem);
		}

		return prodItemList;
	}

	private List<ProductItemGroupCategory> convertProdItemGrpCatgryLists(List<ProductItemGroupCategoryVO> prodItemGrpCategryVOList) {
		List<ProductItemGroupCategory> prodItemGrpCategryList = new ArrayList<ProductItemGroupCategory>();
		if (CollectionUtils.isEmpty(prodItemGrpCategryVOList))
			return prodItemGrpCategryList;
		
		for (ProductItemGroupCategoryVO item : prodItemGrpCategryVOList) {
			ProductItemGroupCategory pigc = new ProductItemGroupCategory();
			pigc.setGroupCategory(this.convertGroupCategory(item.getGroupCategory()));
			pigc.setProductItems(this.convertProdItems(item.getProductItems()));
			//pigc.setQuantityAllowed(this.convertQuantityAllowed(item.getQuantityAllowed()));
			
			prodItemGrpCategryList.add(pigc);
		}
		
		return prodItemGrpCategryList;
	}

	private QuantityAllowed convertQuantityAllowed(QuantityAllowedVO qaVO) {
		if (qaVO == null)
			return null;
		
		QuantityAllowed qa = new QuantityAllowed();
		
		qa.setMaximumQty(qaVO.getMaximumQty());
		qa.setMinimumQty(qaVO.getMinimumQty());
		
		return qa;
	}
	
	private GroupCategory convertGroupCategory(GroupCategoryVO groupCategoryVO) {
		GroupCategory gc = new GroupCategory();
		gc.setGroupTypeCd(groupCategoryVO.getGroupTypeCd());
		gc.setGroupSubTypeCd(groupCategoryVO.getGroupSubTypeCd());
		
		return gc;
	}
	
	private List<ProductItem> convertProdItems(List<ProductItemVO> productItemVOList) {
		List<ProductItem> piList = new ArrayList<ProductItem>();
		if (CollectionUtils.isEmpty(productItemVOList))
			return piList;
		
		for (ProductItemVO piVO : productItemVOList) {
			piList.add(this.covertProductItem(piVO));
		}
		
		return piList;
	}
	
  	private ProductItem covertProductItem(ProductItemVO piVO) {
  		ProductItem pi = new ProductItem();  		
	  	pi.setProductItemIdentifier(this.convertProductItemIdentifier(piVO.getProductItemIdentifier()));
		pi.setDiscountCode(piVO.getDiscountCode());
		pi.setIncludedInd(piVO.isIncludedInd());
		pi.setMandatoryInd(piVO.isMandatoryInd());
		pi.setPreSelectedInd(piVO.isPreSelectedInd());
		pi.setSmartRingInd(piVO.isSmartRingInd());
		pi.setProductCatalogueDescription(this.convertMultilingualTextList(piVO.getProductCatalogueDescription()));
		pi.setProductCatalogueName(this.convertMultilingualTextList(piVO.getProductCatalogueName()));
		pi.setProductItemPriceCharge(this.convertPrice(piVO.getProductItemPriceCharge()));
		pi.setProductItemPriceDiscount(this.convertPriceDiscount(piVO.getProductItemPriceDiscount()));
		/*
		if (piVO.isEligibleForMinMaxPricingInd() != null) {
			pi.setEligibleForMinMaxPricingInd(piVO.isEligibleForMinMaxPricingInd());
		}
		*/
		pi.setMarketOfferClassification(this.convertMarketOfferClassification(piVO.getMarketOfferClassification()));
		pi.setAssociatedProductItems(this.convertAssociatedProductItems(piVO.getAssociatedProductItems()));
		pi.setExistingProductItemInd(piVO.isExistingInd());
		pi.setCarryOverInd(piVO.isCarryOverInd());
		pi.setProductItemCategoryList(comvert(piVO.getCategoryList()));
		
		//FIFA-SHS
		pi.setProductItemSpecifications( convertCharacteriscs( piVO.getCharacteristics() ) );
		
		return pi;
  	}

	private List<ProductItemCategory> comvert(String[] categoryList) {
  		if(categoryList == null) return null;
  		List<ProductItemCategory> result = new ArrayList<ProductItemCategory>(); 
  		for( String id : categoryList) {
  			ProductItemCategory element = new ProductItemCategory();
  			element.setId(id);
  			result.add(element);
  		}
		return result;
	}
	private MarketOfferClassification convertMarketOfferClassification(MarketOfferClassificationVO mocVO) {
  		if (mocVO != null) {
  		 MarketOfferClassification moc = new MarketOfferClassification();
  		 moc.setAddOnInd(mocVO.isAddOnInd());
  		 moc.setCallingFeatureInd(mocVO.isCallingFeatureInd());
  		 moc.setDiscountInd(mocVO.isDiscountInd());
  		 moc.setProductComponentInd(mocVO.isProductComponentInd());
  		 moc.setSweetenerInd(mocVO.isSweetenerInd());
  		 return moc;
  		}
  		return null;
  	}
	
	private ProductItemIdentifier convertProductItemIdentifier(ProductItemIdentifierVO piiVO) {
		ProductItemIdentifier pii = new ProductItemIdentifier();
		pii.setParentProductCatalogueId(piiVO.getParentProductCatalogueId());
		pii.setProductCatalogueId(piiVO.getProductCatalogueId());
		
		return pii;
	}
	  
	private MultilingualText convertMultiText(MultilingualTextVO mtVO) {
		MultilingualText mt = new MultilingualText();
		mt.setLocaleTxt(mtVO.getLocaleTxt());
		mt.setValueTxt(mtVO.getValueTxt());
		
		return mt;
	}
	  
	private List<MultilingualText> convertMultilingualTextList(List<MultilingualTextVO> mtVOList) {
		List<MultilingualText> mtList = new ArrayList<MultilingualText>();
		if (CollectionUtils.isEmpty(mtVOList))
			return mtList;
		
		for (MultilingualTextVO mtVO : mtVOList)
			mtList.add(this.convertMultiText(mtVO));
		
		return mtList;
	}
	      
  	private Price convertPrice(PriceVO priceVO) {
  		if (priceVO == null)
  			return null;
  		
  		Price price = new Price();
  		price.setPricingTypeCd(priceVO.getPricingTypeCd());
  		price.setRecurrenceCount(priceVO.getRecurrenceCount());
  		price.setBasePriceAmount(this.convertMoney(priceVO.getBasePriceAmount()));
  		price.setInstallmentOptions(convertInstallmentOptions(priceVO.getInstallmentOptions()));
  		
  		return price;
  	}
  	
  	private List<InstallmentOption> convertInstallmentOptions(List<InstallmentOptionVO> list){
  		if (list == null)
  			return null;
  		
  		List<InstallmentOption> optionList = new ArrayList<InstallmentOption>();
  		for(InstallmentOptionVO optionVO: list) {
  			InstallmentOption option = new InstallmentOption();
  			option.setDownPaymentAmount(convertMoney(optionVO.getDownPaymentAmount()));
  			option.setInstallmentAmount(convertMoney(optionVO.getInstallmentAmount()));
  			option.setInstallmentOptionCd(optionVO.getInstallmentOptionCd());
  			option.setNumberOfInstallments(optionVO.getNumberOfInstallments());
  			
  			optionList.add(option);
  		}
  		
  		return optionList;
  	}
  
  	private Money convertMoney(MoneyVO mVO) {
  		if (mVO == null)
  			return null;
  		
  		Money m = new Money();
  		m.setValueAmt(mVO.getValueAmt());
  		
  		return m;
  	}
  	
  	private PriceDiscount convertPriceDiscount(PriceDiscountVO pdVO) {
  		if (pdVO == null)
  			return null;
  		
  		PriceDiscount pd = new PriceDiscount();
  		pd.setPromotionId(pdVO.getPromotionId());
  		pd.setPerspectiveDate(pdVO.getPerspectiveDate() != null? new DateTime(pdVO.getPerspectiveDate()): null);
  		pd.setBasePriceAmount(this.convertMoney(pdVO.getBasePriceAmount()));
  		pd.setPriceAlterationTypeCd(pdVO.getPriceAlterationTypeCd());
  		pd.setPricingTypeCd(pdVO.getPricingTypeCd());
  		pd.setRecurrenceCount(pdVO.getRecurrenceCount());
  		
  		return pd;
  	}
  
	private List<AvailableWirelineEquipmentType> convertEquipmentType(List<AvailableFFHEquipmentTypeVO> equipTypeVOList) {
		List<AvailableWirelineEquipmentType> equipTypeList = new ArrayList<AvailableWirelineEquipmentType>();
		if (CollectionUtils.isEmpty(equipTypeVOList))
			return equipTypeList;
		
		for (AvailableFFHEquipmentTypeVO equipTypeVO : equipTypeVOList) {
			AvailableWirelineEquipmentType equipType = new AvailableWirelineEquipmentType();
			equipTypeList.add(equipType);
			equipType.setQuantityAllowed(this.convertQuantityAllowed(equipTypeVO.getQuantityAllowed()));
			equipType.setBuyEquipmentList(this.convertEquipmentList(equipTypeVO.getBuyEquipmentList()));
			equipType.setRentalEquipmentList(this.convertEquipmentList(equipTypeVO.getRentalEquipmentList())); // Gary 2018-09-17
			equipType.setClientOwnedEquipmentList(this.convertEquipmentList(equipTypeVO.getClientOwnedEquipmentList())); // Gary 2018-09-17
			if(equipTypeVO.hasExistingEquipment()) {
				equipType.setExistingEquipmentList(this.convertExisingEquipmentList(equipTypeVO.getExistingEquipmentList()));
			}else {
				equipType.setExistingEquipmentList(this.convertExisingEquipmentList(equipTypeVO.getBuyEquipmentList(), equipTypeVO.getRentalEquipmentList(), equipTypeVO.getClientOwnedEquipmentList()));
			}
		}

		return equipTypeList;
	}
	
	private List<ExistingWirelineEquipmentType> convertExisingEquipmentList(List<FFHEquipmentItemVO> buyEquipmentList, List<FFHEquipmentItemVO> rentalEquipmentList, List<FFHEquipmentItemVO> clientOwnedEquipmentList) {
		List<ExistingWirelineEquipmentType> equipItemList = new ArrayList<ExistingWirelineEquipmentType>();
		if (CollectionUtils.isEmpty(buyEquipmentList) && CollectionUtils.isEmpty(rentalEquipmentList) && CollectionUtils.isEmpty(clientOwnedEquipmentList))
			return equipItemList;
		
		if(!CollectionUtils.isEmpty(buyEquipmentList)) {
			for (FFHEquipmentItemVO equipItemVO : buyEquipmentList) {
				if(equipItemVO.isExisting()) {
					ExistingWirelineEquipmentType equipItem = new ExistingWirelineEquipmentType();
					equipItemList.add(equipItem);
					equipItem.setProductItemIdentifier(this.convertProductItemIdentifier(equipItemVO.getProductItemIdentifier()));
					equipItem.setEquipmentTypeCd(equipItemVO.getEquipmentType());
					equipItem.setEquipmentSubTypeCd(equipItemVO.getEquipmentSubType());
					equipItem.setModelName(equipItemVO.getModelName());
					equipItem.setMaterialItemCode(equipItemVO.getMaterialItemCode());
					equipItem.setIncludedInd(Boolean.valueOf(equipItemVO.getIncludedInd()));
					equipItem.setEquipmentDescription(this.convertMultilingualTextList(equipItemVO.getEquipmentDescription()));
					equipItem.setEquipmentPrice(this.convertPrice(equipItemVO.getEquipmentPrice()));
					equipItem.setEquipmentPriceDiscount(this.convertPriceDiscount(equipItemVO.getEquipmentPriceDiscount()));
					equipItem.setDeliveryMethods(equipItemVO.getDeliveryMethods());
					equipItem.setAssociatedProductItems(this.convertAssociatedProductItems(equipItemVO.getAssociatedProductItems()));
					equipItem.setCarryOverInd(equipItemVO.isCarryOver());
					EquipmentAcquisitionType acquisitionType = new EquipmentAcquisitionType();
					acquisitionType.setBuyIndicator(true);
					acquisitionType.setRentalEquipmentIndicator(false);
					acquisitionType.setCustomerOwnedIndicator(false);
				}
				
			}
		}
		
		if(!CollectionUtils.isEmpty(rentalEquipmentList)) {
			for (FFHEquipmentItemVO equipItemVO : rentalEquipmentList) {
				if(equipItemVO.isExisting()) {
					ExistingWirelineEquipmentType equipItem = new ExistingWirelineEquipmentType();
					equipItemList.add(equipItem);
					equipItem.setProductItemIdentifier(this.convertProductItemIdentifier(equipItemVO.getProductItemIdentifier()));
					equipItem.setEquipmentTypeCd(equipItemVO.getEquipmentType());
					equipItem.setEquipmentSubTypeCd(equipItemVO.getEquipmentSubType());
					equipItem.setModelName(equipItemVO.getModelName());
					equipItem.setMaterialItemCode(equipItemVO.getMaterialItemCode());
					equipItem.setIncludedInd(Boolean.valueOf(equipItemVO.getIncludedInd()));
					equipItem.setEquipmentDescription(this.convertMultilingualTextList(equipItemVO.getEquipmentDescription()));
					equipItem.setEquipmentPrice(this.convertPrice(equipItemVO.getEquipmentPrice()));
					equipItem.setEquipmentPriceDiscount(this.convertPriceDiscount(equipItemVO.getEquipmentPriceDiscount()));
					equipItem.setDeliveryMethods(equipItemVO.getDeliveryMethods());
					equipItem.setAssociatedProductItems(this.convertAssociatedProductItems(equipItemVO.getAssociatedProductItems()));
					equipItem.setCarryOverInd(equipItemVO.isCarryOver());
					EquipmentAcquisitionType acquisitionType = new EquipmentAcquisitionType();
					acquisitionType.setBuyIndicator(false);
					acquisitionType.setRentalEquipmentIndicator(true);
					acquisitionType.setCustomerOwnedIndicator(false);
				}
				
			}
		}
		
		if(!CollectionUtils.isEmpty(clientOwnedEquipmentList)) {
			for (FFHEquipmentItemVO equipItemVO : clientOwnedEquipmentList) {
				if(equipItemVO.isExisting()) {
					ExistingWirelineEquipmentType equipItem = new ExistingWirelineEquipmentType();
					equipItemList.add(equipItem);
					equipItem.setProductItemIdentifier(this.convertProductItemIdentifier(equipItemVO.getProductItemIdentifier()));
					equipItem.setEquipmentTypeCd(equipItemVO.getEquipmentType());
					equipItem.setEquipmentSubTypeCd(equipItemVO.getEquipmentSubType());
					equipItem.setModelName(equipItemVO.getModelName());
					equipItem.setMaterialItemCode(equipItemVO.getMaterialItemCode());
					equipItem.setIncludedInd(Boolean.valueOf(equipItemVO.getIncludedInd()));
					equipItem.setEquipmentDescription(this.convertMultilingualTextList(equipItemVO.getEquipmentDescription()));
					equipItem.setEquipmentPrice(this.convertPrice(equipItemVO.getEquipmentPrice()));
					equipItem.setEquipmentPriceDiscount(this.convertPriceDiscount(equipItemVO.getEquipmentPriceDiscount()));
					equipItem.setDeliveryMethods(equipItemVO.getDeliveryMethods());
					equipItem.setAssociatedProductItems(this.convertAssociatedProductItems(equipItemVO.getAssociatedProductItems()));
					EquipmentAcquisitionType acquisitionType = new EquipmentAcquisitionType();
					acquisitionType.setBuyIndicator(false);
					acquisitionType.setRentalEquipmentIndicator(false);
					acquisitionType.setCustomerOwnedIndicator(true);
					equipItem.setAcquisitionType(acquisitionType);
				}
				
			}
		}
		
		
		return equipItemList;
	}
	
	private List<ExistingWirelineEquipmentType> convertExisingEquipmentList(List<FFHEquipmentItemVO> existingEquipmentList) {
		List<ExistingWirelineEquipmentType> equipItemList = new ArrayList<ExistingWirelineEquipmentType>();
		if (CollectionUtils.isEmpty(existingEquipmentList))
			return equipItemList;
		

		for (FFHEquipmentItemVO equipItemVO : existingEquipmentList) {
			ExistingWirelineEquipmentType equipItem = new ExistingWirelineEquipmentType();
			equipItemList.add(equipItem);
			if(equipItemVO instanceof ExistingFFHEquipmentTypeVO)
				equipItem.setAcquisitionType(convertAcquisitionType(((ExistingFFHEquipmentTypeVO) equipItemVO).getAcquisitionType()));
			equipItem.setProductItemIdentifier(
					this.convertProductItemIdentifier(equipItemVO.getProductItemIdentifier()));
			equipItem.setEquipmentTypeCd(equipItemVO.getEquipmentType());
			equipItem.setEquipmentSubTypeCd(equipItemVO.getEquipmentSubType());
			equipItem.setModelName(equipItemVO.getModelName());
			equipItem.setMaterialItemCode(equipItemVO.getMaterialItemCode());
			equipItem.setIncludedInd(Boolean.valueOf(equipItemVO.getIncludedInd()));
			equipItem.setEquipmentDescription(this.convertMultilingualTextList(equipItemVO.getEquipmentDescription()));
			equipItem.setEquipmentPrice(this.convertPrice(equipItemVO.getEquipmentPrice()));
			equipItem.setEquipmentPriceDiscount(this.convertPriceDiscount(equipItemVO.getEquipmentPriceDiscount()));
			equipItem.setDeliveryMethods(equipItemVO.getDeliveryMethods());
			equipItem.setAssociatedProductItems(
					this.convertAssociatedProductItems(equipItemVO.getAssociatedProductItems()));
			equipItem.setCarryOverInd(equipItemVO.isCarryOver());
		}
		
		return equipItemList;
	}

	private List<WirelineEquipmentItem> convertEquipmentList(List<FFHEquipmentItemVO> equipItemVOList) {
		List<WirelineEquipmentItem> equipItemList = new ArrayList<WirelineEquipmentItem>();
		if (CollectionUtils.isEmpty(equipItemVOList))
			return equipItemList;
		
		for (FFHEquipmentItemVO equipItemVO : equipItemVOList) {
			WirelineEquipmentItem equipItem = new WirelineEquipmentItem();
			equipItemList.add(equipItem);
			equipItem.setProductItemIdentifier(this.convertProductItemIdentifier(equipItemVO.getProductItemIdentifier()));
			equipItem.setEquipmentTypeCd(equipItemVO.getEquipmentType());
			equipItem.setEquipmentSubTypeCd(equipItemVO.getEquipmentSubType());
			equipItem.setModelName(equipItemVO.getModelName());
			equipItem.setMaterialItemCode(equipItemVO.getMaterialItemCode());
			equipItem.setIncludedInd(Boolean.valueOf(equipItemVO.getIncludedInd()));
			equipItem.setEquipmentDescription(this.convertMultilingualTextList(equipItemVO.getEquipmentDescription()));
			equipItem.setEquipmentPrice(this.convertPrice(equipItemVO.getEquipmentPrice()));
			equipItem.setEquipmentPriceDiscount(this.convertPriceDiscount(equipItemVO.getEquipmentPriceDiscount()));
			equipItem.setDeliveryMethods(equipItemVO.getDeliveryMethods());
			equipItem.setAssociatedProductItems(this.convertAssociatedProductItems(equipItemVO.getAssociatedProductItems()));
			
			//FIFA SHS
			equipItem.setEquipmentCharacteristics( convertCharacteriscs(equipItemVO.getCharacteristics()) );
		}
		
		return equipItemList;
	}
	
	private List<AssociatedProductItem> convertAssociatedProductItems(List<AssociatedProductItemVO> associatedProductItems) {
		List<AssociatedProductItem> associatedProductItemList = new ArrayList<AssociatedProductItem>();
		for (AssociatedProductItemVO associatedProductItemVO : associatedProductItems == null ? Collections.<AssociatedProductItemVO>emptyList() : associatedProductItems) {
			AssociatedProductItem associatedProductItem = new AssociatedProductItem();
			associatedProductItem.setDiscountCode(associatedProductItemVO.getDiscountCode());
			associatedProductItem.setProductItemIdentifier(this.convertProductItemIdentifier(associatedProductItemVO.getProductItemIdentifier()));
			associatedProductItem.setProductCatalogueName(this.convertMultilingualTextList(associatedProductItemVO.getProductCatalogueName()));
			associatedProductItem.setProductCatalogueDescription(this.convertMultilingualTextList(associatedProductItemVO.getProductCatalogueDescription()));
			associatedProductItem.setPreSelectedInd(associatedProductItemVO.isPreSelectedInd());
			associatedProductItem.setIncludedInd(associatedProductItemVO.isIncludedInd());
			associatedProductItem.setMarketOfferClassification(this.convertMarketOfferClassification(associatedProductItemVO.getMarketOfferClassification()));
			associatedProductItem.setProductItemPriceCharge(this.convertPrice(associatedProductItemVO.getProductItemPriceCharge()));
			associatedProductItem.setProductItemPriceDiscount(this.convertPriceDiscount(associatedProductItemVO.getProductItemPriceDiscount()));
			associatedProductItemList.add(associatedProductItem);			
		}
		return associatedProductItemList;		
	}
	
	private EquipmentAcquisitionType convertAcquisitionType(EquipmentAcquisitionTypeVO acquisitionTypeVO) {
		EquipmentAcquisitionType acquisitionType = new EquipmentAcquisitionType();
		if(acquisitionTypeVO != null) {
			if(Boolean.TRUE.equals(acquisitionTypeVO.isBuyIndicator()))
				acquisitionType.setBuyIndicator(true);
			if(Boolean.TRUE.equals(acquisitionTypeVO.isRentalEquipmentIndicator()))
				acquisitionType.setRentalEquipmentIndicator(true);
			if(Boolean.TRUE.equals(acquisitionTypeVO.isCustomerOwnedIndicator()))
				acquisitionType.setCustomerOwnedIndicator(true);
		}
		
		return acquisitionType;
	}
	
  	//FIFA-SHS
	private List<AvailableHomeSecurityProductItem> convertHomeSecurityProduct(List<AvailableHomeSecurityProductItemVO> availProdItemVOList) {
		
		List<AvailableHomeSecurityProductItem> prodItemList = new ArrayList<AvailableHomeSecurityProductItem>();
		if (CollectionUtils.isEmpty(availProdItemVOList))
			return prodItemList;
		
		for (AvailableHomeSecurityProductItemVO prodItemVO : availProdItemVOList) {
			AvailableHomeSecurityProductItem availProdItem = new AvailableHomeSecurityProductItem();			
			List<ProductItemGroupCategory> productItemGroupCategories = this.convertProdItemGrpCatgryLists(prodItemVO.getProductItemGroupCategories());		  
			List<AvailableWirelineEquipmentType> equipments = this.convertEquipmentType(prodItemVO.getEquipments());
			List<ProductItemConstraintGroupList> constraintGroupList = this.convertConstraintGrouplist(prodItemVO.getProductItemConstraintGroupList()); // Gary - 2018-09-24 QC67396 
			
			availProdItem.setProductItemGroupCategories(productItemGroupCategories);
			availProdItem.setAvailableEquipmentList(equipments);
			availProdItem.setProductItemConstraintGroupList(constraintGroupList); 
			prodItemList.add(availProdItem);
		}

		return prodItemList;
	}
	
  	//FIFA-SHS
  	private List<Characteristic> convertCharacteriscs(List<CharacteristicVO> characteristics) {
  		
  		List<Characteristic> result = null;
		if (CollectionUtils.isNotEmpty(characteristics) ) {
			
			result = new ArrayList<Characteristic> ();
			for ( CharacteristicVO source : characteristics ) {
				Characteristic target = null;
				if (source!=null) {
					
					target = new Characteristic();
					target.setDisplayName( source.getDisplayName());
					target.setName( source.getName() );
					if (  source.getValue()!=null ) {
						target.setValue( source.getValue().toString() );
					}
					
					if ( CollectionUtils.isNotEmpty(source.getOptions()) ) {
						List<CharacteristicOption> options = new ArrayList<CharacteristicOption>();
						for (  CharacteristicOptionVO srcOpt: source.getOptions() ) {
							CharacteristicOption targetOpt = new CharacteristicOption();
							
							targetOpt.setName(srcOpt.getName() );
							targetOpt.setValue(srcOpt.getValue());
							targetOpt.isDefault( srcOpt.isDefault() );
							options.add(targetOpt);
						}
						target.setOptions( options);
					}
				}
				result.add( target );
				
			}
		}
		return result;
	}
}