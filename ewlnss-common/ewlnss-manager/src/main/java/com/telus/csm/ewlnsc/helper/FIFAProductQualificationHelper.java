package com.telus.csm.ewlnsc.helper;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.telus.csm.ewlnsc.aspect.MonitorPerformance;
import com.telus.csm.ewlnsc.delegate.FIFAProductOfferingQualificationDelegate;
import com.telus.csm.ewlnsc.domain.FIFAProductOfferingsVO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailRequestVO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.util.ExecutionTimer;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProgramPromotion;

@Component
@Scope(SCOPE_PROTOTYPE)
public class FIFAProductQualificationHelper {

	private static final LoggerUtil logger = LoggerUtil.getLogger(GetSalesOfferDetailHelper.class);

	List<Offer> offers = new ArrayList<Offer>();
	ProgramPromotion programPromotion = null;

	@MonitorPerformance
	public FIFAProductOfferingsVO execute(ShoppingCartVO requestVO) {
		String functionName = "execute";
		ExecutionTimer timer = new ExecutionTimer(this.getClass().getSimpleName(), functionName);

		logger.enter(functionName);
		logger.debug(functionName, "ShoppingCartVO:\n" + JsonUtil.getJsonFromObjectNonNUll(requestVO));
		
		FIFAProductOfferingsVO response = new FIFAProductOfferingsVO();
		response = (new FIFAProductOfferingQualificationDelegate()).getProductOfferingQualification(requestVO);
		
		return response;
	}
}
