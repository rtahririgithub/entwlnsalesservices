package com.telus.csm.ewlnsc.helper;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.telus.csm.ewlnsc.domain.ApplicationProfileVO;
import com.telus.csm.ewlnsc.domain.SalesRepAssociatedOutletVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingProfileVO;
import com.telus.csm.ewlnsc.domain.UserProfileVO;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v4.AuditInfo;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.ChannelPartnerUserProfileType;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.SalesRepAssociatedOutletType;

/**
 * 
 * @author Jose.Mena
 *
 */
public class OperationHeaderUtil {

	private OperationHeaderUtil(){
		
	}
	
	public static AuditInfo getAuditInfoFromHeader(final OperationHeader param) {
		final AuditInfo result = new AuditInfo();

		final ChannelPartnerUserProfileType user = param.getUserProfile();
		result.setSalesRepresentativeId(String.valueOf(user.getSalesRepInternalId()));
		result.setCorrelationId(param.getSalesTransactionId());
		result.setChannelOrganizationId(String.valueOf(user.getChnlOrgInternalId()));
		if (!user.getSalesRepAssociatedOutletList().isEmpty()) {
			result.setOutletId(String.valueOf(user.getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedOutletInternalId()));
		}
		result.setCorrelationId(param.getSalesTransactionId());
		result.setOriginatorApplicationId(String.valueOf(param.getOriginatorApplicationId()));
		result.setUserId(String.valueOf(user.getSalesRepInternalId()));
		return result;
	}
	
	public static AdapterFeatureDriver getAdapterFeatureDriver(final OperationHeader param) {
		
		AdapterFeatureDriver result = new AdapterFeatureDriver();
		
		if (param.getUserProfile() != null) {
			result.setChannelOrgType(param.getUserProfile().getChnlOrgTypeCode());
		}
		result.setSalesPersonRoleCode(param.getSalesPersonRoleCode());
		
		return result;
	}
	
	public static OperationHeader buildOperationHeader(String transactionId, ShoppingCartVO shoppingCartVO) {
		
		ShoppingProfileVO shoppingProfile = shoppingCartVO.getShoppingProfile();
//		AgentProfileVO agentProfile = shoppingProfile.getAgentProfile();

		OperationHeader result = new OperationHeader();
		
		result.setSalesTransactionId(transactionId);
		result.setSalesTransactionTimestamp(new Date());
		result.setSalesId(shoppingCartVO.getShoppingCartId());
		//hard code to TELUS
		result.setBrandCode(EnterpriseWLNSalesServicesConstants.BRAND_CODE_TELUS);
		
		UserProfileVO userProfile = shoppingProfile.getUserProfile(); 
		if (userProfile != null) {			
			ChannelPartnerUserProfileType channelPartnerUserProfile = new ChannelPartnerUserProfileType();
			channelPartnerUserProfile.setChnlOrgTypeCode(userProfile.getChannelOrganizationTypeCd());
			try {
				channelPartnerUserProfile.setChnlOrgInternalId(Long.parseLong(userProfile.getChannelOrganizationInternalId()));
			} catch (NumberFormatException e) {
			}
			channelPartnerUserProfile.setChnlOrgNumber(userProfile.getChannelOrganizationNumber());
			channelPartnerUserProfile.setOutletAssociatedProvinces(userProfile.getOutletAssociatedProvinces());
			channelPartnerUserProfile.setLoginId(userProfile.getLoginId());
			channelPartnerUserProfile.setSalesRepId(userProfile.getSalesRepId());
			try {
				channelPartnerUserProfile.setSalesRepInternalId(Long.parseLong(userProfile.getSalesRepInternalId()));
			} catch (NumberFormatException e) {
			}
			
			List<SalesRepAssociatedOutletVO> salesRepAssociatedOutlets = userProfile.getSalesRepAssociatedOutlet();
			if (salesRepAssociatedOutlets != null && !salesRepAssociatedOutlets.isEmpty()) {
				for (SalesRepAssociatedOutletVO salesRepAssociatedOutlet : salesRepAssociatedOutlets) {
					SalesRepAssociatedOutletType newSalesRepAssociatedOutlet = new SalesRepAssociatedOutletType();
					newSalesRepAssociatedOutlet.setSalesRepAssociatedChannelOutletId(salesRepAssociatedOutlet.getChannelOutletId());
					try {
						newSalesRepAssociatedOutlet.setSalesRepAssociatedOutletInternalId(Long.parseLong(salesRepAssociatedOutlet.getOutletInternalId()));
					} catch (NumberFormatException e1) {
					}
					channelPartnerUserProfile.getSalesRepAssociatedOutletList().add(newSalesRepAssociatedOutlet);
				}
			}
			
			result.setUserProfile(channelPartnerUserProfile);
		}
		
		ApplicationProfileVO applicationProfile = shoppingProfile.getApplicationProfile();
		if (applicationProfile != null && !StringUtils.isBlank(applicationProfile.getOriginatorApplicationId())) {
			try {
				result.setOriginatorApplicationId(Long.valueOf(applicationProfile.getOriginatorApplicationId()));
			} catch (NumberFormatException e) {
			}
		}
		return result;

	}

}
