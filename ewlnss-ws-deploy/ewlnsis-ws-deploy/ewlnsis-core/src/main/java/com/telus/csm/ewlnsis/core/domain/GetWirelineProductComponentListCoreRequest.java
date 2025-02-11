package com.telus.csm.ewlnsis.core.domain;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.domain.CoreRequestBase;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetWirelineProductComponentListType;

/**
 * 
 * @author Jose.Mena
 *
 */
public class GetWirelineProductComponentListCoreRequest extends CoreRequestBase {

	private static final long serialVersionUID = 1L;

	private List<GetWirelineProductComponentListType.SalesProductComponentList> salesProductComponentList = new ArrayList<GetWirelineProductComponentListType.SalesProductComponentList>();

	public List<GetWirelineProductComponentListType.SalesProductComponentList> getSalesProductComponentList() {
		return salesProductComponentList;
	}

	public void setSalesProductComponentList(
			List<GetWirelineProductComponentListType.SalesProductComponentList> salesProductComponentList) {
		this.salesProductComponentList = salesProductComponentList;
	}

}
