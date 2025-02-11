package com.telus.csm.ewlnsc.helper;

import com.telus.csm.ewlnsc.domain.schemasclone.GetAvailableSweetenerOfferListResponseVO;
import com.telus.csm.ewlnsc.domain.schemasclone.GetAvailableSweetenerOfferListRequestVO;
import com.telus.csm.ewlnsc.transformer.GetAvailableSweetenerOfferListTransformer;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class GetAvailableSweetenerOfferListSchemaIndependentHelper extends GetAvailableSweetenerOfferListHelper {

	private static final LoggerUtil logger = LoggerUtil.getLogger(GetAvailableSweetenerOfferListSchemaIndependentHelper.class);

	public GetAvailableSweetenerOfferListSchemaIndependentHelper() {
		super();
	}

	public GetAvailableSweetenerOfferListResponseVO execute(GetAvailableSweetenerOfferListRequestVO requestVO) {
		String functionName = "execute";

		logger.enter(functionName);
		
		GetAvailableSweetenerOfferListResponseVO availableSweetenerOfferListResponseVO = GetAvailableSweetenerOfferListTransformer.transform(super.execute(GetAvailableSweetenerOfferListTransformer.transform(requestVO)));

		logger.exit(functionName);
		
		return availableSweetenerOfferListResponseVO;
	}
}