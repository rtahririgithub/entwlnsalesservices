package com.telus.csm.ess.core.posttask.node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ess.rest.domain.OperationHeader;
import com.telus.csm.ewlnsc.adapter.IEnterpriseSalesServiceFulfillmentSvcAdapter;
import com.telus.csm.ewlnsc.adapter.esfs.domain.UpdateSalesOrderAdapterRequest;
import com.telus.csm.ewlnsc.adapter.esfs.domain.UpdateSalesOrderAdapterResponse;
import com.telus.csm.ewlnsc.delegate.ShoppingCartDelegate;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.OperationHeaderVO;
import com.telus.csm.ewlnsc.domain.OrderCommentVO;
import com.telus.csm.ewlnsc.domain.SalesItemPerkVO;
import com.telus.csm.ewlnsc.domain.SalesRepAssociatedOutletVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingProfileVO;
import com.telus.csm.ewlnsc.domain.UserProfileVO;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.UpdateOrderItem;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.UpdateOrderItemTypeParameter;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.UpdateWirelessSalesOrderType;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.ChannelPartnerUserProfileType;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.SalesRepAssociatedOutletType;

public class SubmitPerkWLNExecNode extends AbstractNode<UpdateSalesOrderAdapterResponse> {
	
	private final LoggerUtil logger = LoggerUtil.getLogger(getClass());
	private ShoppingCartContextVO shoppingCartContextVO;

	public SubmitPerkWLNExecNode(ShoppingCartContextVO shoppingCartContextVO) {
		super("SubmitOrderWLNExecNode");
		this.shoppingCartContextVO = shoppingCartContextVO;
	} 

	@Override
	public void run() {
		String functionName = "SubmitPerkWLNExecNode.run()";
		if (shoppingCartContextVO != null && shoppingCartContextVO.getShoppingCartVO() != null) {
			ShoppingCartVO shoppingCartVO = shoppingCartContextVO.getShoppingCartVO();
			ShoppingProfileVO shoppingProfileVO = shoppingCartVO.getShoppingProfile();
			List<CartItemVO> cartItemList = shoppingCartContextVO.getShoppingCartVO().getCartItemList();
			
			if (cartItemList != null && shoppingProfileVO != null) {
				logger.info(functionName, "Start processing perk for shoppingCart id: "+ shoppingCartVO.getShoppingCartId());
				IEnterpriseSalesServiceFulfillmentSvcAdapter enterpriseSalesServiceFulfillmentSvcAdapter = AdapterFactory.getAdapter(IEnterpriseSalesServiceFulfillmentSvcAdapter.class);
				UserProfileVO userProfile = shoppingProfileVO.getUserProfile();
				for (CartItemVO cartItem : cartItemList) {
					logger.info(functionName, "Start processing perk for cartItemId id: "+ cartItem.getCartItemId());
					if (cartItem.isSalesOrderItem()) {
						SalesItemPerkVO salesItem = ShoppingCartDelegate.getInstance().getPerkByCartItemId(cartItem.getCartItemId());
						if (salesItem != null) {
							UpdateSalesOrderAdapterRequest updateSalesOrderRequest = new UpdateSalesOrderAdapterRequest();
							updateSalesOrderRequest.setOperationHeader(buildOperationHeader(cartItem, userProfile));
							updateSalesOrderRequest.setCreateUpdateWirelessSalesOrderType(createUpdateWirelessSalesOrderType());
							logger.info(functionName, "Begin: Call updateSalesOrder to submit  perk for cartItemId id: "+ cartItem.getCartItemId());
							UpdateSalesOrderAdapterResponse updateSalesOrderResponse = enterpriseSalesServiceFulfillmentSvcAdapter.updateSalesOrder(updateSalesOrderRequest );
							logger.info(functionName, "End: Call updateSalesOrder to submit  perk for cartItemId id: "+ cartItem.getCartItemId());
						} else {
							logger.info(functionName, "salesItem returned esdb is empty");
						}
					}
				}
			}
		}
		
	}
	/**
	 * 
	 * @param cartItemVO
	 * @return
	 */
	private com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader buildOperationHeader(
			CartItemVO cartItemVO, UserProfileVO userProfileVO) {
		com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader operationHeader = new com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader();
		if (cartItemVO != null) {
			operationHeader.setSalesId(cartItemVO.getCartItemId());
			operationHeader.setSalesTransactionId(cartItemVO.getCartItemId());
			operationHeader.setSalesTransactionTimestamp(new Date());
			operationHeader.setUserProfile(buildProfile(userProfileVO));
			operationHeader.setOriginatorApplicationId(EnterpriseWLNSalesServicesConstants.APPLICATION_ID_WLN_SALES);
			operationHeader.setBrandCode(Constants.TELUS_INSTALL);
			return operationHeader;
		}

		return operationHeader;
	}
	/**
	 * 
	 * @param operationHeaderVO
	 * @param userProfileVO
	 * @return
	 */
	private ChannelPartnerUserProfileType buildProfile(UserProfileVO userProfileVO) {
		ChannelPartnerUserProfileType userProfile = new ChannelPartnerUserProfileType();
		if (userProfileVO != null) {
			userProfile.setChnlOrgTypeCode(userProfileVO.getChannelOrganizationTypeCd());
			userProfile.setChnlOrgInternalId(!StringUtils.isEmpty(userProfileVO.getChannelOrganizationInternalId())?Long.parseLong(userProfileVO.getChannelOrganizationInternalId()):0);
			userProfile.setChnlOrgNumber(userProfileVO.getChannelOrganizationNumber());
			userProfile.setSalesRepId(userProfileVO.getSalesRepId());
			userProfile.setOutletAssociatedProvinces(userProfileVO.getOutletAssociatedProvinces());
			userProfile.setSalesRepAssociatedOutletList(createSalesRepAssociatedOutletList(userProfileVO));
		}
		return userProfile;
	}
	/**
	 * 
	 * @param userProfileVO
	 * @return
	 */
	private List<SalesRepAssociatedOutletType> createSalesRepAssociatedOutletList(UserProfileVO userProfileVO) {
		List<SalesRepAssociatedOutletType> associatedOutletLis = new ArrayList<SalesRepAssociatedOutletType>();
		if (userProfileVO != null) {
			List<SalesRepAssociatedOutletVO> associatedProvinces = userProfileVO.getSalesRepAssociatedOutlet();
			if (associatedProvinces != null) {
				for (SalesRepAssociatedOutletVO outlet : associatedProvinces) {
					SalesRepAssociatedOutletType salesRepAssociatedOutletType = new SalesRepAssociatedOutletType();
					salesRepAssociatedOutletType.setSalesRepAssociatedChannelOutletId(outlet.getChannelOutletId());
					salesRepAssociatedOutletType.setSalesRepAssociatedOutletInternalId(!StringUtils.isEmpty(outlet.getOutletInternalId())?Long.parseLong(outlet.getOutletInternalId()):0);
				}
			}
		}
		return associatedOutletLis;
	}

	private UpdateWirelessSalesOrderType createUpdateWirelessSalesOrderType() {
		UpdateWirelessSalesOrderType salesOrderType = new UpdateWirelessSalesOrderType();
		UpdateOrderItem updateOrderItem = new UpdateOrderItem();
		updateOrderItem.setUpdateOrderItemType(EnterpriseWLNSalesServicesConstants.SALES_ITEM_FULFILLMENT);

		UpdateOrderItemTypeParameter updateOrderItemParam = new UpdateOrderItemTypeParameter();
		updateOrderItemParam.setName(EnterpriseWLNSalesServicesConstants.LINE_OF_BUSINESS);
		updateOrderItemParam.setValue(EnterpriseWLNSalesServicesConstants.APPLICATION_ID);

		updateOrderItem.setUpdateOrderItemTypeParamList(Collections.singletonList(updateOrderItemParam));
		salesOrderType.setUpdateOrderItemList(Collections.singletonList(updateOrderItem));

		return salesOrderType;
	}	
}
