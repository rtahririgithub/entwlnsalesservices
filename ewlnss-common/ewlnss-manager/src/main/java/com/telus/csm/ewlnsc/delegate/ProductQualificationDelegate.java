/**
 * 
 */
package com.telus.csm.ewlnsc.delegate;

import com.telus.csm.ewlnsc.adapter.ISalesCustomerInfoRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetProductQualificationAdapterRequest;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetProductQualificationAdapterResponse;
import com.telus.csm.ewlnsc.domain.GetOffersRequestVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressVO;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;

/**
 * Business Delegate to get Product Qualification information
 * 
 * @author t837932
 *
 */
public class ProductQualificationDelegate {

	/** Declaring the Logger **/
	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(ProductQualificationDelegate.class);
	
	public ProductQualificationDelegate(){
		super();
	}
	
	public GetProductQualificationAdapterResponse getProductQualification(GetProductQualificationAdapterRequest request){
				
		String functionName="getProductQualification";
		
		LOGGER.enter(functionName);
		
		/** Step 1: Building GetProductQualificationAdapterRequest **/
		
		//GetProductQualificationAdapterRequest adapterRequest = transform(request);
		
		/** Step 2: Getting Adapter from Adapter Factory **/
		ISalesCustomerInfoRestSvcAdapter adapter = AdapterFactory.getAdapter(ISalesCustomerInfoRestSvcAdapter.class);
		
		/** Step 3: Calling Adapter implementation **/
		GetProductQualificationAdapterResponse adapterResponse = adapter.getProductQualification(request);
		
		/** Step 4: handling results from Service Response **/
//		if(adapterResponse.isMsgHasError()){
//			LOGGER.error(null, functionName, "An Error has occurred when calling ReferencePDSDataSvc, please check Response Details");
//		}else{
//			LOGGER.info(functionName, "Response successfully retrieved from RefPDSDataService...");
//			LOGGER.info(functionName, "Transforming Response to HashMap");
//			tableMap = RefPDSServiceDelegatorTransformer.transformRefPDSResponse(adapterResponse);
//		}
		
		/** Step 5: Returning Map **/
		LOGGER.exit(functionName);
		return adapterResponse;
	}

	private GetProductQualificationAdapterRequest transform(GetOffersRequestVO request) {
		
		OperationHeader operationHeader = request.getOperationHeader();
		
		GetProductQualificationAdapterRequest result = new GetProductQualificationAdapterRequest();

		result.setSalesTransactionId(operationHeader.getSalesTransactionId());
		result.setCorrelationId(operationHeader.getSalesId());

		if (!operationHeader.getUserProfile().getSalesRepAssociatedOutletList().isEmpty()) {
			result.setChannelOutletId(operationHeader.getUserProfile().getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedChannelOutletId());
		}

		if ( (operationHeader.isRefreshInd() != null) && (operationHeader.isRefreshInd())) {
			result.setRefreshCache(operationHeader.isRefreshInd());
		}

		result.setSalesRepId(operationHeader.getUserProfile().getSalesRepId());
		
		if (request.getSalesOfferCriteria() != null) {
			ServiceAddressVO serviceAddress = request.getSalesOfferCriteria().getServiceAddress();
			if (serviceAddress != null) {
				result.setAddressId(serviceAddress.getServiceAddressId());
				result.setCity(serviceAddress.getCityCode());
				result.setProvinceCd(serviceAddress.getProvinceCode());				
			}
		}

		result.setQualByServiceId(false);

		return result;
	}
	
	}
