package com.telus.csm.ewlnsc.domain.schemasclone;


public class GetAvailableSweetenerOfferListRequestVO extends CoreRequestBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SweetnerOfferFilterCriteria sweetenerOfferFilterCriteria;

	public SweetnerOfferFilterCriteria getSweetenerOfferFilterCriteria() {
		return sweetenerOfferFilterCriteria;
	}

	public void setSweetenerOfferFilterCriteria(SweetnerOfferFilterCriteria sweetenerOfferFilterCriteria) {
		this.sweetenerOfferFilterCriteria = sweetenerOfferFilterCriteria;
	}
}