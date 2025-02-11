package com.telus.csm.ewlnsc.transformer.opshoppingcart;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.telus.csm.ewlnsc.adapter.woscs.domain.CreateOrderAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.CreateOrderAdapterRequestBody;
import com.telus.csm.ewlnsc.adapter.woscs.domain.Product;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductCharacteristic;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductComponent;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductOrderItem;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.DisconnectRequestTypeVO;
import com.telus.csm.ewlnsc.domain.RelatedCartItemVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;

public class OPDisconnectOrderTransformer extends OPCommonProvideTransformer {

	public static List<CreateOpOrderRequestWrapper> transform(OperationHeader operationHeader, ShoppingCartContextVO shoppingCartContextVO, ArrayList<CartItemVO> disconnectRequests) {
		if(disconnectRequests != null) {
			List<GroupedDisconnectRequests> groupedRequestList = groupByOrder(shoppingCartContextVO, disconnectRequests);
			if(groupedRequestList != null) {
				ArrayList<CreateOpOrderRequestWrapper> disconnectCreateOpOrders = new ArrayList<CreateOpOrderRequestWrapper>();
				for(GroupedDisconnectRequests groupedRequests: groupedRequestList) {
					CreateOpOrderRequestWrapper disconnectCreateOpOrd = transformToCreateOpOrderRequestWrapper(operationHeader, shoppingCartContextVO, groupedRequests);
					if(disconnectCreateOpOrd != null) {
						disconnectCreateOpOrders.add(disconnectCreateOpOrd);
					}
				}
				return disconnectCreateOpOrders;
			}
		}
		return null;
	}

	private static CreateOpOrderRequestWrapper transformToCreateOpOrderRequestWrapper(OperationHeader operationHeader, ShoppingCartContextVO shoppingCartContextVO, GroupedDisconnectRequests groupedRequests) {
		CreateOpOrderRequestWrapper disconnectCreateOpOrd = new CreateOpOrderRequestWrapper();
		disconnectCreateOpOrd.setRelatedCartItemList(groupedRequests.getRelatedCartItemList());
		
		ShoppingCartVO shoppingCart = shoppingCartContextVO.getShoppingCartVO();
		CreateOrderAdapterRequest request = new CreateOrderAdapterRequest();
		request.setReturnorderdetails(Boolean.FALSE.toString()); //true - see the actual order created in OP for debug purpose
		disconnectCreateOpOrd.setCreateOrderAdapterRequest(request);
		
		CreateOrderAdapterRequestBody requestBody = request.getBody();
		
		if(shoppingCart.getCustomer() != null) {
			request.setCustomerid(shoppingCart.getCustomer().getCustomerId());
		}
		requestBody.setType(EnterpriseWLNSalesServicesConstants.TYPE_DE); //will be defaulted to "DE" ; may have "RE" in future 
		requestBody.setPartyInteractionRole(populatePartyInteractionRoleBase(operationHeader, shoppingCartContextVO));
		
		ProductOrderItem productOrderItem = new ProductOrderItem();
		productOrderItem.setPartyInteractionRole(populatePartyInteractionRoleBase(operationHeader, shoppingCartContextVO));
		requestBody.addProductOrderItem(productOrderItem);
		
		Product product = new Product();
		productOrderItem.setProduct(product);
		product.setName(EnterpriseWLNSalesServicesConstants.OP_CLEC);
		product.setServiceType(EnterpriseWLNSalesServicesConstants.OP_CLEC);
		product.setCatalogId(EnterpriseWLNSalesServicesConstants.OP_CLEC_CAT_ID);
		
		ProductComponent generalProductInfoComp = new ProductComponent();
		generalProductInfoComp.setName(EnterpriseWLNSalesServicesConstants.COMP_GENERAL_PRODUCT_INFO);
		product.addProductComponent(generalProductInfoComp);
		ProductCharacteristic offerNameChar = createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OFFER_NAME, EnterpriseWLNSalesServicesConstants.OP_CLEC);
		generalProductInfoComp.addProductCharacteristic(offerNameChar);
		ProductCharacteristic offerCatIdChar = createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OFFER_CATALOG_ID, EnterpriseWLNSalesServicesConstants.OP_CLEC_OFFER_CAT_ID);
		generalProductInfoComp.addProductCharacteristic(offerCatIdChar);

		ProductComponent orderActionAttrComp = new ProductComponent();
		orderActionAttrComp.setName(EnterpriseWLNSalesServicesConstants.COMP_ORDER_ACTION_ATTRIBUTES);
		product.addProductComponent(orderActionAttrComp);
		Date serviceRequiredDate = groupedRequests.getDisconnectRequestList().get(0).getRequestedDisconnectDate();//take the first one. They should be the same.
		if(serviceRequiredDate != null) {
			//Should be: "value": "2018-10-04T09:40:37Z"
			ProductCharacteristic serviceRequiredDateChar = new ProductCharacteristic();
			serviceRequiredDateChar.setName(EnterpriseWLNSalesServicesConstants.SERVICE_REQUIRED_DATE);
			String serviceRequiredDateVal = formatToOpServiceRequiredDate(serviceRequiredDate);
			serviceRequiredDateChar.setValue(serviceRequiredDateVal);
			orderActionAttrComp.addProductCharacteristic(serviceRequiredDateChar);
		}

		//lsr
		Long primaryPhoneNumber = findPrimaryPhoneNumber(shoppingCartContextVO);
		ProductComponent lsrDataProdCom = createLsrDataProductComponent(groupedRequests.getDisconnectRequestList(), primaryPhoneNumber, shoppingCartContextVO);
		if(lsrDataProdCom != null) {
			product.addProductComponent(lsrDataProdCom);
		}
		return disconnectCreateOpOrd;
	}

	private static List<GroupedDisconnectRequests> groupByOrder(ShoppingCartContextVO shoppingCartContextVO, ArrayList<CartItemVO> disconnectCartItems) {
		//same date and the same provider go together to one OP order
		HashMap<String, GroupedDisconnectRequests> groups = new HashMap<String, GroupedDisconnectRequests>();
		if(disconnectCartItems != null) {
			for(CartItemVO itm: disconnectCartItems) {
				for(DisconnectRequestTypeVO req: itm.getDisconnectRequestList()) {
					String discServType = findIncludeDeleteServiceToProvide(req, shoppingCartContextVO.getShoppingCartVO());
					if(req.getProductServiceType().contains(discServType)) {
						//it is included to the provide OP order
						continue;
					}
					String provider = req.getOriginalCarrierInfo() != null? req.getOriginalCarrierInfo().getProviderId(): "";
					Date disconectDate = req.getRequestedDisconnectDate();
					String key = provider + "_" + (disconectDate != null? disconectDate.toString(): "");
					GroupedDisconnectRequests grp = groups.get(key);
					if(grp == null){
						grp = new GroupedDisconnectRequests();
						groups.put(key, grp);
					}
					List<RelatedCartItemVO> relatedCartItemVOs = grp.getRelatedCartItemList();
					boolean hasRelatedItm = existsInRelatedCartItemList(itm.getCartItemId(), relatedCartItemVOs);
					if(!hasRelatedItm) {
						RelatedCartItemVO relatedItm = new RelatedCartItemVO();
						relatedItm.setCartItemId(itm.getCartItemId());
						relatedItm.setCartItemType(RelatedCartItemVO.CARTITEM_TYPE.DISCONNECT.toString());
						grp.getRelatedCartItemList().add(relatedItm);
					}
					grp.getDisconnectRequestList().add(req);
				}
			}
		}
		return new ArrayList<GroupedDisconnectRequests>(groups.values());
	}
	
	public static class GroupedDisconnectRequests {
		private List<RelatedCartItemVO> relatedCartItemList = new ArrayList<RelatedCartItemVO>();
		List<DisconnectRequestTypeVO> disconnectRequestList = new ArrayList<DisconnectRequestTypeVO>();
		public List<RelatedCartItemVO> getRelatedCartItemList() {
			return relatedCartItemList;
		}
		public void setRelatedCartItemList(List<RelatedCartItemVO> relatedCartItemList) {
			this.relatedCartItemList = relatedCartItemList;
		}
		public List<DisconnectRequestTypeVO> getDisconnectRequestList() {
			return disconnectRequestList;
		}
		public void setDisconnectRequestList(List<DisconnectRequestTypeVO> disconnectRequestList) {
			this.disconnectRequestList = disconnectRequestList;
		}		
	}

}
