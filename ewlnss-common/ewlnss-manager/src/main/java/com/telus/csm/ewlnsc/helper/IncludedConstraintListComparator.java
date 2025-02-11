package com.telus.csm.ewlnsc.helper;

import java.util.Comparator;

import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.IncludedServiceConstraint;;

public class IncludedConstraintListComparator implements Comparator<IncludedServiceConstraint> {

	@Override
	public int compare(IncludedServiceConstraint obj1, IncludedServiceConstraint obj2) {
		//sort the list size from low to high
		if (obj1.getServiceTypeCodeList() != null && obj2.getServiceTypeCodeList() != null){
			return Integer.compare(obj1.getServiceTypeCodeList().size(), obj2.getServiceTypeCodeList().size());
		}
		return 0;
	}

}
