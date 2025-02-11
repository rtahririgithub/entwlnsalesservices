package com.telus.csm.ewlnsc.helper;

import java.util.Comparator;

import com.telus.csm.ewlnsc.domain.product.FFHEquipmentItemVO;

public class EquipmentItemPriceComparator implements Comparator<FFHEquipmentItemVO> {

	@Override
	public int compare(FFHEquipmentItemVO obj1, FFHEquipmentItemVO obj2) {
		if(obj1.getEquipmentPrice() != null && obj2.getEquipmentPrice() !=null) {
			return Float.compare(obj2.getEquipmentPrice().getBasePriceAmount().getValueAmt(), obj1.getEquipmentPrice().getBasePriceAmount().getValueAmt());  
		}
		return 0;
	}

}
