package com.telus.csm.ewlnsc.rules.business;

import java.util.List;

import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

/**
 *  
 *  1) When calling GAOSL or GSOD, if no ports available then include an error message in the response.
 *  2) if condition 1 fulfill, then exclude any offer that has TTV and HSIC offers in response.
 * 
 * @author Alejandro.Hernandez
 *
 */
public class FilterProductsByNoPortsAvailableRule extends AbstractSpecification<Offer,TraceVO>{
	
	private SalesOfferCommonVO commonVO;
	private static final LoggerUtil logger = LoggerUtil.getLogger(FilterProductsByNoPortsAvailableRule.class);
	
	public FilterProductsByNoPortsAvailableRule(SalesOfferCommonVO commonVO){
		this.commonVO=commonVO;
	}
	
	@Override
	public boolean isSatisfiedBy(Offer o, List<TraceVO> traces) {
		boolean isSatisfied = true;
		
		logger.debug("isSatisfiedBy", WLNOfferUtil.getRuleContextInformation(o));
		
		//Check if FeasibilityResponse has NO ports error code returned
		
		if(WLNOfferUtil.checkProductFeasibilityContainsNoPortsAvailable(commonVO.getCheckFeasibilityResponseVO())){
			
			final List<WirelineOfferProduct> oisProductList = o.getOfferProduct().getWirelineOfferProductList();
			
			if(oisProductList!=null && !oisProductList.isEmpty()){
				for(WirelineOfferProduct wirelineOfferProduct : oisProductList){
					 if (!WLNOfferUtil.isNoChangeOfferProduct(wirelineOfferProduct)) {
						if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode()) || EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode())){
							logger.debug("FilterProductsByNoPortsAvailableRule", "Offer: " + o.getOfferId() + " was filtered out because FeasibilityResponse returned No Ports Available error. Offer Cannot have HSIC or TTV product.");
							isSatisfied=false;
							break;
						}
					 }
				}
			}
			
			if(!isSatisfied){
				this.commonVO.setNoPortsAvailableInd(true);
				TraceVO t = TraceVO.newInstance(this);
				t.setAction(TraceVO.DELETED);
				t.setElementType(TraceVO.OFFER);
				t.setOffer(o);
				t.setReason("Offer: " + o.getOfferId() + " was filtered out because FeasibilityResponse returned No Ports Available error. Offer Cannot have HSIC or TTV product.");
				traces.add(t);
			}
			
		}else{
			logger.debug("FilterProductsByNoPortsAvailableRule", "Rule skipped since Feasibility response didn't return No ports Available error code.");
			return true;
		}
		
		
		return isSatisfied;
	}

}
