package com.telus.csm.ewlnsc.adapter;

import com.telus.csm.ewlnsc.adapter.domain.GetOfferListByOfferIdentifierListAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListByPromotionCodeForCustomerAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetAccessoryOfferListAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListForCustomerAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetSweetenerOfferListForCustomerAdapterRequest;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

public interface IOfferInformationServiceAdapter extends IAdapterBase{

	public GetOfferListAdapterResponse getOfferListByOfferIdentifierList(final GetOfferListByOfferIdentifierListAdapterRequest request);
	
	public GetOfferListAdapterResponse getOfferListForCustomer(final GetOfferListForCustomerAdapterRequest request);
	
	public GetOfferListAdapterResponse getOfferListByPromotionCodeForCustomer(final GetOfferListByPromotionCodeForCustomerAdapterRequest request);

	public GetOfferListAdapterResponse getSweetenerListByOfferSummaryListForCustomer(final GetSweetenerOfferListForCustomerAdapterRequest request);	

    public GetOfferListAdapterResponse getAccessoryOfferList(final GetAccessoryOfferListAdapterRequest request);
}
