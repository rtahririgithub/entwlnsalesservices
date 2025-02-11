package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.io.Serializable;

public class PromotionVO implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String promotionId;
    private String promotionName;

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String value) {
        this.promotionId = value;
    }

	public String getPromotionName() {
		return promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((promotionId == null) ? 0 : promotionId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PromotionVO other = (PromotionVO) obj;
		if (promotionId == null) {
			if (other.promotionId != null)
				return false;
		} else if (!promotionId.equals(other.promotionId))
			return false;
		return true;
	}

}

