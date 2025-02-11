package com.telus.csm.ewlnsis.core.domain;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.domain.CoreResponseBase;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineProductCatalogueItem;

/**
 * 
 * @author Jose.Mena
 *
 */
public class GetWirelineProductComponentListCoreResponse extends CoreResponseBase {

	private static final long serialVersionUID = 1L;

	private List<WirelineProductCatalogueItem> salesProductComponentList = new ArrayList<WirelineProductCatalogueItem>();

	public List<WirelineProductCatalogueItem> getSalesProductComponentList() {
		return salesProductComponentList;
	}

	public void setSalesProductComponentList(List<WirelineProductCatalogueItem> salesProductComponentList) {
		this.salesProductComponentList = salesProductComponentList;
	}

}
