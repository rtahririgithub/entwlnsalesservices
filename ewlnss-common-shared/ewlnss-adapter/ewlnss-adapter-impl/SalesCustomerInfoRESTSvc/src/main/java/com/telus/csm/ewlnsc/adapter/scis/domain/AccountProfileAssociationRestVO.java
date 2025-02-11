package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.io.Serializable;

public class AccountProfileAssociationRestVO extends AccountProfileRestVO {

	private static final long serialVersionUID = 1L;
	
	public static class AccountAssociationRestVO implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
        private String accountAssociationId;
        
        private String accountAssociationTypeCd;
        
        private String originalAccountMasterSourceTypeCd;
        
        private String originalBillingAccountNum;

		private String startDate;

		private String endDate;
        
		public String getAccountAssociationId() {
			return accountAssociationId;
		}
		
		public void setAccountAssociationId(String accountAssociationId) {
			this.accountAssociationId = accountAssociationId;
		}
		
		public String getAccountAssociationTypeCd() {
			return accountAssociationTypeCd;
		}
		
		public void setAccountAssociationTypeCd(String accountAssociationTypeCd) {
			this.accountAssociationTypeCd = accountAssociationTypeCd;
		}
		
		public String getOriginalAccountMasterSourceTypeCd() {
			return originalAccountMasterSourceTypeCd;
		}
		
		public void setOriginalAccountMasterSourceTypeCd(String originalAccountMasterSourceTypeCd) {
			this.originalAccountMasterSourceTypeCd = originalAccountMasterSourceTypeCd;
		}
		
		public String getOriginalBillingAccountNum() {
			return originalBillingAccountNum;
		}
		
		public void setOriginalBillingAccountNum(String originalBillingAccountNum) {
			this.originalBillingAccountNum = originalBillingAccountNum;
		}

		public String getStartDate() {
			return startDate;
		}

		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}

	}
	
	private AccountAssociationRestVO accountAssociation;

	public AccountAssociationRestVO getAccountAssociation() {
		return accountAssociation;
	}

	public void setAccountAssociation(AccountAssociationRestVO accountAssociation) {
		this.accountAssociation = accountAssociation;
	}

}
