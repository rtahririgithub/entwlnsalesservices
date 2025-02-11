package com.telus.csm.ewlnsc.delegate;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.IOfferInformationServiceAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetAccessoryOfferListAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListByOfferIdentifierListAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListByPromotionCodeForCustomerAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListForCustomerAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetSweetenerOfferListForCustomerAdapterRequest;
import com.telus.csm.ewlnsc.domain.GetOffersRequestVO;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.transformer.GetAccessoryOfferListTransformer;
import com.telus.csm.ewlnsc.transformer.GetAvailableOfferSummaryTransformer;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Discount;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Sweetener;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;
/**
 * 
 * @author Alejandro.Hernandez
 *
 */
public class OfferSummaryListDelegate {
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(OfferSummaryListDelegate.class);
	
	public GetOfferListAdapterResponse getOfferSummaryList(SalesOfferCommonVO offerCommonVO, boolean callForRecontractEligibleInd,String productCatalogId){
		String functionName="getOfferSummaryList";
		logger.enter(functionName);
		GetOfferListAdapterResponse offerSummaryResponse = null;
		String creditValueCd;
		String technologyTypeCd;
		/**
		 * Declaring the adapter
		 */
		IOfferInformationServiceAdapter offerAdapter = AdapterFactory.getAdapter(IOfferInformationServiceAdapter.class);
		/**
		 * Getting the creditValueCd and technologyTypeCd from shared offer utility class
		 */
		creditValueCd = WLNOfferUtil.getCreditValueCode(offerCommonVO);
		technologyTypeCd = WLNOfferUtil.getTechnologyTypeCd(offerCommonVO);
		
		if(offerCommonVO!=null && offerCommonVO.getOffersRequestVO()!=null){
			GetOffersRequestVO offersRequestVO = offerCommonVO.getOffersRequestVO();
			if(offersRequestVO.getSalesOfferCriteria()!=null){
				if(!StringUtils.isBlank(offersRequestVO.getSalesOfferCriteria().getPromotionCd())){
					logger.info(functionName, "Promotion code was passed in GetAvailableOfferSummaryList Request, OIS.getOfferListByPromoCode will be called");
					GetOfferListByPromotionCodeForCustomerAdapterRequest offerListForCustByPromoCd = GetAvailableOfferSummaryTransformer.transformOfferListForCustomerByPromoCd(offerCommonVO,callForRecontractEligibleInd,productCatalogId);
					offerListForCustByPromoCd.getCustomer().setTechnologyTypeCode(technologyTypeCd);
					offerListForCustByPromoCd.getCustomer().setCreditValueCode(creditValueCd);
					//offerListForCustByPromoCd.getCustomer().setProductInstanceList(WLNOfferUtil.transformProductInstance(offerCommonVO));
					offerSummaryResponse = offerAdapter.getOfferListByPromotionCodeForCustomer(offerListForCustByPromoCd);	
				}else{
					logger.info(functionName, "Promotion code was not passed in GetAvailableOfferSummaryList Request, OIS.getOfferListForCustomer will be called");
					GetOfferListForCustomerAdapterRequest offerListForCustomerAdapterReq = GetAvailableOfferSummaryTransformer.transformOfferListForCustomer(offerCommonVO,callForRecontractEligibleInd,productCatalogId);
					offerListForCustomerAdapterReq.getCustomer().setTechnologyTypeCode(technologyTypeCd);
					offerListForCustomerAdapterReq.getCustomer().setCreditValueCode(creditValueCd);
					//offerListForCustomerAdapterReq.getCustomer().setProductInstanceList(WLNOfferUtil.transformProductInstance(offerCommonVO));
					offerSummaryResponse = offerAdapter.getOfferListForCustomer(offerListForCustomerAdapterReq);
				}
			}
		}
		logger.exit(functionName);
		return offerSummaryResponse;
	}
	

	public GetOfferListAdapterResponse getSweetenerOfferList(SalesOfferCommonVO offerCommonVO){
		String functionName="getSweetenerOfferList";

		logger.enter(functionName);

		GetOfferListAdapterResponse sweetenerOfferResponse = null;

		/**
		 * Declaring the adapter
		 */
		IOfferInformationServiceAdapter offerAdapter = AdapterFactory.getAdapter(IOfferInformationServiceAdapter.class);

		if ( (offerCommonVO != null) && (offerCommonVO.getOffersRequestVO() != null) && (offerCommonVO.getOffersRequestVO().getSweetenerOfferFilterCriteria() != null) ) {
			GetSweetenerOfferListForCustomerAdapterRequest sweetenerOfferListForCustomerAdapterRequest = GetAvailableOfferSummaryTransformer.transformOfferListForSweetener(offerCommonVO);

			sweetenerOfferResponse = offerAdapter.getSweetenerListByOfferSummaryListForCustomer(sweetenerOfferListForCustomerAdapterRequest);
			
			//NWLN-4541 begin
			List<Sweetener> sweetenerList = sweetenerOfferResponse.getSweetenerList();
			
			if (sweetenerList != null && !sweetenerList.isEmpty()) {
				for (Sweetener sweetener : sweetenerList) {
					boolean manualSweetenerInd = WLNOfferUtil.isManualSweetener(sweetener);

					if(manualSweetenerInd){
						filterDiscountProductCatalogueItemList(sweetener);
					}
				}
			}
			//NWLN-4541 end
		}

		logger.exit(functionName);

		return sweetenerOfferResponse;
	}
	
	//NWLN-4541
	private void filterDiscountProductCatalogueItemList(Sweetener sweetener){
		List<WirelineOfferProduct> wirelineOfferProductList = sweetener.getOfferProduct().getWirelineOfferProductList();
		if(wirelineOfferProductList!=null && 
				!wirelineOfferProductList.isEmpty()){
			for(WirelineOfferProduct wirelineOfferProduct : wirelineOfferProductList){
				if(wirelineOfferProduct.getDiscountList()!=null && 
						!wirelineOfferProduct.getDiscountList().isEmpty()){
					for(Discount discount : wirelineOfferProduct.getDiscountList()){
						if(discount.getDiscountProductCatalogueItemList() != null){
						   discount.setDiscountProductCatalogueItemList(null);
						}
					}					
				}
			}
		}
	}

	public GetOfferListAdapterResponse getAccessoryOfferList(SalesOfferCommonVO offerCommonVO){
		String functionName="getAccessoryOfferList";

		logger.enter(functionName);

		GetOfferListAdapterResponse offerListResponse = null;

		/**
		 * Declaring the adapter
		 */
		IOfferInformationServiceAdapter offerAdapter = AdapterFactory.getAdapter(IOfferInformationServiceAdapter.class);

		logger.info(functionName, "OIS.getAccessoryOfferList will be called");

		GetAccessoryOfferListAdapterRequest accessoryOfferListAdapterReq = GetAccessoryOfferListTransformer.transformAccessoryOfferList(offerCommonVO);
		offerListResponse = offerAdapter.getAccessoryOfferList(accessoryOfferListAdapterReq);

		logger.exit(functionName);

		return offerListResponse;
	}

	public GetOfferListAdapterResponse getOfferListByOfferIdentifierList(SalesOfferCommonVO offerCommonVO){
		String functionName="getOfferListByOfferIdentifierList";

		logger.enter(functionName);

		GetOfferListAdapterResponse offerListResponse = null;

		/**
		 * Declaring the adapter
		 */
		IOfferInformationServiceAdapter offerAdapter = AdapterFactory.getAdapter(IOfferInformationServiceAdapter.class);

		logger.info(functionName, "OIS.getOfferListByOfferIdentifierList will be called");

		GetOfferListByOfferIdentifierListAdapterRequest offerListByOfferIdentifierListAdapterReq = GetAvailableOfferSummaryTransformer.transformOfferListForAccessoryOfferListByOfferIdentifierList(offerCommonVO);
		offerListResponse = offerAdapter.getOfferListByOfferIdentifierList(offerListByOfferIdentifierListAdapterReq);

		logger.exit(functionName);

		return offerListResponse;
	}
}
