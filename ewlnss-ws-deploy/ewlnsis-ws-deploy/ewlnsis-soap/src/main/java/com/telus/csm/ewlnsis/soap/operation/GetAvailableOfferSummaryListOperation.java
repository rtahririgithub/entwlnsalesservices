package com.telus.csm.ewlnsis.soap.operation;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.telus.csm.ewlnsc.domain.GetOffersRequestVO;
import com.telus.csm.ewlnsc.domain.GetOffersResponseVO;
import com.telus.csm.ewlnsc.helper.GetAccessoryOfferListHelper;
import com.telus.csm.ewlnsc.helper.GetAvailableOfferSummaryHelper;
import com.telus.csm.ewlnsc.helper.GetAvailableSweetenerOfferListHelper;
import com.telus.csm.ewlnsc.transformer.GetAvailableOfferSummaryTransformer;
import com.telus.csm.ewlnsc.util.LoggerUtil;

import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableOfferSummaryListResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableOfferSummaryListType;

@Component
@Scope(SCOPE_PROTOTYPE)
public class GetAvailableOfferSummaryListOperation {

	private static final LoggerUtil logger = LoggerUtil.getLogger(GetAvailableOfferSummaryListOperation.class);
	
	@Autowired
	GetAccessoryOfferListHelper getAccessoryOfferListHelper;
	
	@Autowired
	GetAvailableSweetenerOfferListHelper getAvailableSweetenerOfferListHelper;
	
	@Autowired
	GetAvailableOfferSummaryHelper getAvailableOfferSummaryHelper;
	
	/**
	 * This execute method will call the GetAvailableOfferSummaryListCoreOperation class to retrieve the GetAvailableOfferSummaryListResponseType
	 * 
	 * @param GetAvailableOfferSummaryListType
	 * @return GetAvailableOfferSummaryListResponseType
	 */

	public GetAvailableOfferSummaryListResponseType execute(GetAvailableOfferSummaryListType parameters) {
		String functionName = "execute()";
		logger.enter(functionName);
		
		/*
		 * Transforming from schema object to GetOffersRequestVO
		 */
		final GetOffersRequestVO requestVO = GetAvailableOfferSummaryTransformer.transform(parameters);

		GetOffersResponseVO offersResponseVO = null;

		if (requestVO.getSalesOfferCriteria() != null) {
			/*
			 * Calling GetAvailableOfferSummaryHelper
			 */
			//GetAvailableOfferSummaryHelper getAvailableOfferSummaryHelper = new GetAvailableOfferSummaryHelper();
			
			offersResponseVO = getAvailableOfferSummaryHelper.execute(requestVO);
		}
		else if (requestVO.getSweetenerOfferFilterCriteria() != null) {
			/*
			 * Calling GetAvailableSweetenerOfferListHelper
			 */
			//GetAvailableSweetenerOfferListHelper helper = new GetAvailableSweetenerOfferListHelper();
			
			offersResponseVO = getAvailableSweetenerOfferListHelper.execute(requestVO);
		}
		else if (requestVO.getAccessoryOfferCriteria() != null) {
			/*
			 * Calling GetAccessoryOfferSummaryHelper
			 */
			//GetAccessoryOfferListHelper helper = new GetAccessoryOfferListHelper();
			
			offersResponseVO = getAccessoryOfferListHelper.execute(requestVO);
		}

		logger.info(functionName, "Transforming from GetOffersResponseVO to GetAvailableOfferSummaryListResponseType");
		
		GetAvailableOfferSummaryListResponseType result = GetAvailableOfferSummaryTransformer.transform(offersResponseVO);

		logger.exit(functionName);

		return result;
	}

}
