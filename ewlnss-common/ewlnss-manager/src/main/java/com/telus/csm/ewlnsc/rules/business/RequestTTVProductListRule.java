package com.telus.csm.ewlnsc.rules.business;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.TTV;

import java.math.BigInteger;
import java.util.List;

import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressVO;
import com.telus.csm.ewlnsc.domain.SubscribedServiceIdentifierVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

//2018 June Exception Release
public class RequestTTVProductListRule extends AbstractSpecification<Offer,TraceVO> {

	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(RequestTTVProductListRule.class);
	
	
	private SalesOfferCommonVO salesOfferCommonVO;
	
	public RequestTTVProductListRule(SalesOfferCommonVO salesOfferCommonVO) {
		this.salesOfferCommonVO = salesOfferCommonVO;
	}

	@Override
	public boolean isSatisfiedBy(final Offer o, final List<TraceVO> traces) {
		LOGGER.info("RequestTTVProductListRule", "enter");
		
		boolean result = true;
		
		if(isTTVExists(salesOfferCommonVO)){
			final List<WirelineOfferProduct> tomProdList = o.getOfferProduct().getWirelineOfferProductList(); 
			for (final WirelineOfferProduct tomProdElem : tomProdList) {
				if (!WLNOfferUtil.isNoChangeOfferProduct(tomProdElem)){
					String tomProdType = tomProdElem.getProductTypeCode(); 
					if (TTV.equals(tomProdType)) {
						if (!WLNOfferUtil.isOfferAndSubscribedProductCidMatchingInd(o, salesOfferCommonVO) && !tomProdElem.getContractTermList().contains(BigInteger.valueOf(0))) {
							result = false;
							log(o, traces, "Offer filtered out due to existing TTV ProductCatalogIds not matching TOM ExternalProductCatalogId for TTV.");
							return result;
						} else {
							log(o, traces, "Offer no filtered out since matching product cid or offer contract term is 0");
						}
					}
				}
			}
		}
			
		return result;
	}
  	/**
	 * 
	 * @param o
	 * @param traces
	 * @param msg
	 */
	private void log(final Offer o, final List<TraceVO> traces, final String msg) {
		LOGGER.error("Offer with id: " + o.getOfferId() + " filterred out.");
		TraceVO t = TraceVO.newInstance(this);
		t.setAction(TraceVO.DELETED);
		t.setElementType(TraceVO.OFFER);
		t.setOffer(o);
		t.setReason(msg);
		traces.add(t);
	}

	private boolean isTTVExists(SalesOfferCommonVO commonVO){
		boolean existTtvInd = false;
		if(commonVO!=null){
			if(commonVO.getAssignedAndPendingProductsResponseVO()!=null){
				ServiceAddressVO requestServiceAddress = commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress();
				List<SubscribedServiceIdentifierVO> requestSubscribedServiceIdList = commonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier();
				List<SubscribedProductInfoRestVO> assignedProductList = commonVO.getAssignedAndPendingProductsResponseVO().getAssignedProductListByServiceAddressAndServiceId(requestServiceAddress, requestSubscribedServiceIdList);
				List<SubscribedProductInfoRestVO> pendingProductList = commonVO.getAssignedAndPendingProductsResponseVO().getPendingProductListByServiceAddress(commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress(),commonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier(),commonVO.getServiceAddressResponseVO());
				if(assignedProductList!=null && !assignedProductList.isEmpty()){
					for(SubscribedProductInfoRestVO assignedProduct : assignedProductList){
						if(assignedProduct!=null){
							String assignedProductType = assignedProduct.getProductTypeCd();
							if(TTV.equalsIgnoreCase(assignedProductType)){
								existTtvInd = true;
							}
						}
					}
				}else if(pendingProductList!=null && !pendingProductList.isEmpty()){
					for(SubscribedProductInfoRestVO pendingProduct : pendingProductList){
						if(pendingProduct!=null){
							String pendingProductType = pendingProduct.getProductTypeCd();
							if(TTV.equalsIgnoreCase(pendingProductType)){
								existTtvInd = true;
							}
						}
					}
				}
			}
		}
		return existTtvInd;
	}
	
}
