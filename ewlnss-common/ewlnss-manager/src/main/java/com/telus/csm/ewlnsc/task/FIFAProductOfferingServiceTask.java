package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.adapter.IFIFAProductOfferingRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetFIFAPoductOfferingAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetFIFAPoductOfferingAdapterResponse;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;

import commonj.work.Work;


public class FIFAProductOfferingServiceTask extends TaskBase implements Work {
	
	private GetFIFAPoductOfferingAdapterRequest  input;
	private GetFIFAPoductOfferingAdapterResponse result;
	private IFIFAProductOfferingRestSvcAdapter adapter;
	private String categoryType ;
	private String subCategoryType;
	private String productType;
	public FIFAProductOfferingServiceTask(IFIFAProductOfferingRestSvcAdapter adapter,GetFIFAPoductOfferingAdapterRequest input, String categoryType,String subCategoryType,String productType) {
		this.input=input;
		this.adapter = adapter;
		this.categoryType = categoryType ;
		this.subCategoryType=subCategoryType;
		this.productType=productType;
	}
	
	public GetFIFAPoductOfferingAdapterRequest getInput() {
		return input;
	}

	public GetFIFAPoductOfferingAdapterResponse getResult() {
		rethrowException();
		return result;
	}

	@Override
	protected void execute() {
		result = adapter.getProductOfferingQualification (input);
	}

	public String getCategoryType() {
//		if(!EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(productType)) {
			return categoryType;
//		}else {
//			return productType+"_"+categoryType+"_"+subCategoryType;
//		}
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

}
