package com.telus.csm.ewlnsc.grid.domain;

import java.io.Serializable;

public class CatalogueItemHierarchyDO implements Serializable, Entity<String> {
	
	private static final long serialVersionUID = 1L;

	private String offerId;
	private String nodePath;
	private String mainComponentPath;
	
	
	private String parentProdCatItemId; // PARENT_PROD_CATLG_ITM_ID
	private String parentProdCatItemTypeId; // PARENT_PROD_CATLG_ITM_TYPE_CD
	private String parentProdCd; // PARENT_PROD_CD
	private String parentProdInternalNm; // PARENT_PROD_INTRNL_NM
	private String parentCompSvcTypeCd; // PARENT_COMPONENT_SVC_TYPE_CD
	private String childProdCatItemId; // CHILD_PROD_CATLG_ITM_ID
	private String childProdCatItemTypeCd; // CHILD_PROD_CATLG_ITM_TYPE_CD
	private String childProdCd; // CHILD_PROD_CD
	private String childProdInternalNm; // CHILD_PROD_INTRNL_NM
	private String childCompSvcTypeCd; // CHILD_COMPONENT_SVC_TYPE_CD
	private String relnEffStartDt; // RELN_EFF_START_DT
	private String relnEffEndDt; // RELN_EFF_END_DT
	private String relnOptionalCd; // RELN_OPTIONAL_CD
	private int relnMinAllowedQty; // RELN_MIN_ALLOWED_QTY
	private int relnMaxAllowedQty; // RELN_MAX_ALLOWED_QTY
	private String prodRelnRsnCd; // PROD_RELN_RSN_CD


	@Override
	public String getId() {
		return nodePath;
	}

	public String getOfferId() {
		return offerId;
	}


	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}


	public String getNodePath() {
		return nodePath;
	}


	public void setNodePath(String nodePath) {
		this.nodePath = nodePath;
	}


	public String getMainComponentPath() {
		return mainComponentPath;
	}


	public void setMainComponentPath(String mainComponentPath) {
		this.mainComponentPath = mainComponentPath;
	}


	public String getParentProdCatItemId() {
		return parentProdCatItemId;
	}


	public void setParentProdCatItemId(String parentProdCatItemId) {
		this.parentProdCatItemId = parentProdCatItemId;
	}


	public String getParentProdCatItemTypeId() {
		return parentProdCatItemTypeId;
	}


	public void setParentProdCatItemTypeId(String parentProdCatItemTypeId) {
		this.parentProdCatItemTypeId = parentProdCatItemTypeId;
	}


	public String getParentProdCd() {
		return parentProdCd;
	}


	public void setParentProdCd(String parentProdCd) {
		this.parentProdCd = parentProdCd;
	}


	public String getParentProdInternalNm() {
		return parentProdInternalNm;
	}


	public void setParentProdInternalNm(String parentProdInternalNm) {
		this.parentProdInternalNm = parentProdInternalNm;
	}


	public String getParentCompSvcTypeCd() {
		return parentCompSvcTypeCd;
	}


	public void setParentCompSvcTypeCd(String parentCompSvcTypeCd) {
		this.parentCompSvcTypeCd = parentCompSvcTypeCd;
	}


	public String getChildProdCatItemId() {
		return childProdCatItemId;
	}


	public void setChildProdCatItemId(String childProdCatItemId) {
		this.childProdCatItemId = childProdCatItemId;
	}


	public String getChildProdCatItemTypeCd() {
		return childProdCatItemTypeCd;
	}


	public void setChildProdCatItemTypeCd(String childProdCatItemTypeCd) {
		this.childProdCatItemTypeCd = childProdCatItemTypeCd;
	}


	public String getChildProdCd() {
		return childProdCd;
	}


	public void setChildProdCd(String childProdCd) {
		this.childProdCd = childProdCd;
	}


	public String getChildProdInternalNm() {
		return childProdInternalNm;
	}


	public void setChildProdInternalNm(String childProdInternalNm) {
		this.childProdInternalNm = childProdInternalNm;
	}


	public String getChildCompSvcTypeCd() {
		return childCompSvcTypeCd;
	}


	public void setChildCompSvcTypeCd(String childCompSvcTypeCd) {
		this.childCompSvcTypeCd = childCompSvcTypeCd;
	}


	public String getRelnEffStartDt() {
		return relnEffStartDt;
	}


	public void setRelnEffStartDt(String relnEffStartDt) {
		this.relnEffStartDt = relnEffStartDt;
	}


	public String getRelnEffEndDt() {
		return relnEffEndDt;
	}


	public void setRelnEffEndDt(String relnEffEndDt) {
		this.relnEffEndDt = relnEffEndDt;
	}


	public String getRelnOptionalCd() {
		return relnOptionalCd;
	}


	public void setRelnOptionalCd(String relnOptionalCd) {
		this.relnOptionalCd = relnOptionalCd;
	}


	public int getRelnMinAllowedQty() {
		return relnMinAllowedQty;
	}


	public void setRelnMinAllowedQty(int relnMinAllowedQty) {
		this.relnMinAllowedQty = relnMinAllowedQty;
	}


	public int getRelnMaxAllowedQty() {
		return relnMaxAllowedQty;
	}


	public void setRelnMaxAllowedQty(int relnMaxAllowedQty) {
		this.relnMaxAllowedQty = relnMaxAllowedQty;
	}


	public String getProdRelnRsnCd() {
		return prodRelnRsnCd;
	}


	public void setProdRelnRsnCd(String prodRelnRsnCd) {
		this.prodRelnRsnCd = prodRelnRsnCd;
	}

	
}
