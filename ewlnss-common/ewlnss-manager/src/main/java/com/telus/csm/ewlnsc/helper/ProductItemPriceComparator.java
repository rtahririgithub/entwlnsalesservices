package com.telus.csm.ewlnsc.helper;

import java.util.Comparator;
import java.util.List;

import com.telus.csm.ewlnsc.domain.MultilingualTextVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemVO;
import com.telus.csm.ewlnsc.util.Constants;

public class ProductItemPriceComparator implements Comparator<ProductItemVO> {

	@Override
	public int compare(ProductItemVO obj1, ProductItemVO obj2) {
		if(obj1.isIncludedInd() != null && obj2.isIncludedInd() != null) {
			int compIncluded = obj1.isIncludedInd().compareTo(obj2.isIncludedInd());
			if(compIncluded != 0) {
				return -compIncluded;//negate
			}
		}
		if(obj1.isMandatoryInd() != null && obj2.isMandatoryInd() != null) {
			int compMandatory = obj1.isMandatoryInd().compareTo(obj2.isMandatoryInd());
			if(compMandatory != 0) {
				return -compMandatory;//negate
			}
		}
//		compare by itemRankNum if it is not null, 1 is the highest, if one of them is null, the one has itemRankNum ranked higher than the one doesn't
		int compareByRankNumber = compareByRankNumber(obj1, obj2);
		if (compareByRankNumber == 0) {
			int compareByPriceValue = compareByPrice(obj1, obj2);
			if (compareByPriceValue == 0) {
				return compareByAlphabetic(obj1, obj2);
			} else {
				return compareByPriceValue;
			}
		} else {
			return compareByRankNumber;
		}
	}
	
	private int compareByRankNumber(ProductItemVO obj1, ProductItemVO obj2) {
		if (obj1.getItemRankNum() != null && obj2.getItemRankNum() != null){
			return obj1.getItemRankNum().compareTo(obj2.getItemRankNum());
		} else if (obj1.getItemRankNum() == null && obj2.getItemRankNum() != null){
			return 1;
		} else if (obj1.getItemRankNum() != null && obj2.getItemRankNum() == null){
			return -1;
		}
		return 0;
	}
	
	private int compareByPrice(ProductItemVO obj1, ProductItemVO obj2) {
		if(obj1.getProductItemPriceCharge() != null && obj2.getProductItemPriceCharge() !=null) {
			return Float.compare(obj2.getProductItemPriceCharge().getBasePriceAmount().getValueAmt(), obj1.getProductItemPriceCharge().getBasePriceAmount().getValueAmt());
		} else if (obj1.getProductItemPriceCharge() == null && obj2.getProductItemPriceCharge() != null){
			return 1;
		} else if (obj1.getProductItemPriceCharge() != null && obj2.getProductItemPriceCharge() == null){
			return -1;
		}
		return 0;
	}
	
	private int compareByAlphabetic(ProductItemVO obj1, ProductItemVO obj2) {
		//if prices are the same, compare two strings by alphabetical order
		if (obj1.getProductCatalogueName() != null && obj2.getProductCatalogueName() != null){
			String desc1 = getLocaleTxt(obj1.getProductCatalogueName());
			String desc2 = getLocaleTxt(obj2.getProductCatalogueName());
			if (desc1 != null){
				return desc1.compareTo(desc2);
			} else {
				return -1;
			}
		} else if (obj1.getProductCatalogueName() == null && obj2.getProductCatalogueName() != null){
			return 1;
		} else if (obj1.getProductCatalogueName() != null && obj2.getProductCatalogueName() == null){
			return -1;
		}
		return 0;
	}
	
	private String getLocaleTxt(List<MultilingualTextVO> textVO) {
		for (MultilingualTextVO vo: textVO){
			if (Constants.ENGLISH.equals(vo.getLocaleTxt())){
				return vo.getValueTxt();
			}
		}
		return null;
	}
}
