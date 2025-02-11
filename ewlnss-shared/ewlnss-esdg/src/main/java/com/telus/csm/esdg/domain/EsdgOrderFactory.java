package com.telus.csm.esdg.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating EsdgOrder objects.
 */
public class EsdgOrderFactory {
	
	/**
	 * Gets the json order.
	 *
	 * @param esdgOrder the esdg order
	 * @return the json order
	 */
	public static String getJsonOrder(Serializable esdgOrder) {
		if( esdgOrder == null ) return null;
		if( esdgOrder instanceof EsdgOrderDO_1 ) {
			return ((EsdgOrderDO_1)esdgOrder).getJsonOrder();
		}
		return null;
	}
	
	
	/**
	 * Gets the order contracts.
	 *
	 * @param esdgOrder the esdg order
	 * @return the json order
	 */
	public static ArrayList<EsdgOrderContractDO> getOrderContracts(Serializable esdgOrder) {
		if( esdgOrder == null ) return null;
		if( esdgOrder instanceof EsdgOrderContractDO_1 ) {
			return ((EsdgOrderContractDO_1)esdgOrder).getOrderContractList();
		}
		return null;
	}
	
	/**
	 * Gets the order last update time in mills.
	 *
	 * @param esdgOrder the esdg order
	 * @return the order last update time in mills
	 */
	public static long getOrderLastUpdateTimeInMills(Serializable esdgOrder) {
		if( esdgOrder == null ) return -1;
		if( esdgOrder instanceof EsdgOrderDO_1 ) {
			return ((EsdgOrderDO_1)esdgOrder).getDataGenerationTimeInMills();
		}
		return -1;
	}

	/**
	 * Gets the status.
	 *
	 * @param esdgOrder the esdg order
	 * @return the status
	 */
	public static String getStatus(Serializable esdgOrder) {
		if( esdgOrder == null ) return null;
		if( esdgOrder instanceof EsdgOrderDO_1 ) {
			return ((EsdgOrderDO_1)esdgOrder).getOrderStatus();
		}
		return null;
	}
	
	/**
	 * Gets the type code.
	 *
	 * @param esdgOrder the esdg order
	 * @return the status
	 */
	public static Boolean isCommerceCartItem(Serializable esdgOrder) {
		if( esdgOrder == null ) return null;
		if( esdgOrder instanceof EsdgOrderDO_1 ) {
			String typeCode = ((EsdgOrderDO_1)esdgOrder).getTypeCode();
			return EsdgOrderDO_1.COMMERCE_CART_ITEM.equalsIgnoreCase(typeCode);
		}
		return null;
	}
	
	/**
	 * Gets the fulfillment status.
	 *
	 * @param esdgOrder the esdg order
	 * @return the fulfillment status
	 */
	public static String getFulfillmentStatus(Serializable esdgOrder) {
		if( esdgOrder == null ) return null;
		if( esdgOrder instanceof EsdgOrderDO_1 ) {
			return ((EsdgOrderDO_1)esdgOrder).getFulfillmentStatus();
		}
		return null;
	}
	
	/**
	 * Gets the order status change list.
	 *
	 * @param esdgOrder the esdg order
	 * @return the order status change list
	 */
	public static ArrayList<EsdgOrderStatusHistoryDO> getOrderStatusChangeList(Serializable esdgOrder) {
		if( esdgOrder == null ) return null;
		if( esdgOrder instanceof EsdgOrderDO_1 ) {
			return ((EsdgOrderDO_1)esdgOrder).getOrderStatusChangeList();
		}
		return null;
	}
	
	/**
	 * Gets the order fulfillment status change list.
	 *
	 * @param esdgOrder the esdg order
	 * @return the order fulfillment status change list
	 */
	public static ArrayList<EsdgOrderFulfillmentStatusHistoryDO> getOrderFulfillmentStatusChangeList(Serializable esdgOrder) {
		if( esdgOrder == null ) return null;
		if( esdgOrder instanceof EsdgOrderDO_1 ) {
			return ((EsdgOrderDO_1)esdgOrder).getOrderFulfillmentStatusChangeList();
		}
		return null;
	}
	
	/**
	 * Gets the order classifier map.
	 *
	 * @param esdgOrder the esdg order
	 * @return the order classifier map
	 */
	public static HashMap<String, String> getOrderClassifierMap(Serializable esdgOrder) {
		if( esdgOrder == null ) return null;
		if( esdgOrder instanceof EsdgOrderDO_1 ) {
			return ((EsdgOrderDO_1)esdgOrder).getOrderClassifierMap();
		}
		return null;
	}
	
	/**
	 * Adds the order fulfillment status change list.
	 *
	 * @param esdgOrder the esdg order
	 * @param orderFulfillmentTypeCd the order fulfillment type cd
	 * @param fulfillmentTransactionJsonData the fulfillment transaction json data
	 * @param newOrderFulfillmentUpdateTimeInMills the new order fulfillment update time in mills
	 * @param isSuccessful the is successful
	 */
	public static void addOrderFulfillmentStatusChangeList(Serializable esdgOrder, String orderFulfillmentTypeCd, String fulfillmentTransactionJsonData, long newOrderFulfillmentUpdateTimeInMills, boolean isSuccessful) {
		if( esdgOrder == null ) return;
		if( esdgOrder instanceof EsdgOrderDO_1 ) {
			((EsdgOrderDO_1)esdgOrder).addOrderFulfillmentStatus(orderFulfillmentTypeCd, fulfillmentTransactionJsonData, newOrderFulfillmentUpdateTimeInMills, isSuccessful);
		}
	}
	
	/**
	 * Sets the order fulfillment status.
	 *
	 * @param esdgOrder the esdg order
	 * @param fulfillmentStatus the fulfillment status
	 */
	public static void setOrderFulfillmentStatus(Serializable esdgOrder, String fulfillmentStatus) {
		if( esdgOrder == null ) return;
		if( esdgOrder instanceof EsdgOrderDO_1 ) {
			((EsdgOrderDO_1)esdgOrder).setFulfillmentStatus(fulfillmentStatus);
		}
	}
	
	/**
	 * Sets the order classifier.
	 *
	 * @param esdgOrder the esdg order
	 * @param orderClassifierMap the order classifier map
	 */
	public static void setOrderClassifier(Serializable esdgOrder, HashMap<String, String> orderClassifierMap) {
		if( esdgOrder == null ) return;
		if( esdgOrder instanceof EsdgOrderDO_1 ) {
			((EsdgOrderDO_1)esdgOrder).setOrderClassifierMap(orderClassifierMap);
		}
	}
	
	/**
	 * Sets the write to database.
	 *
	 * @param esdgOrder the esdg order
	 * @param isWriteToDatabase the is write to database
	 */
	public static void setWriteToDatabase(Serializable esdgOrder, boolean isWriteToDatabase) {
		if( esdgOrder == null ) return;
		if( esdgOrder instanceof EsdgOrderDO_1 ) {
			((EsdgOrderDO_1)esdgOrder).setWriteToDatabase(isWriteToDatabase);
		}		
	}
	
	/**
	 * To string.
	 *
	 * @param esdgOrder the esdg order
	 * @return the string
	 */
	public static String toString(Serializable esdgOrder) {
		if( esdgOrder == null ) return null;
		if( esdgOrder instanceof EsdgOrderDO_1 ) {
			return ((EsdgOrderDO_1)esdgOrder).toString();
		}
		return null;
	}

	/**
	 * Clone.
	 *
	 * @param cachedOrder the cached order
	 * @return the esdg order d o_1
	 */
	public static EsdgOrderDO_1 clone(Serializable cachedOrder) {
		EsdgOrderDO_1 esdgOrder = new EsdgOrderDO_1();
		esdgOrder.setOrderStatus(EsdgOrderFactory.getStatus(cachedOrder));
		esdgOrder.setOrderStatusChangeList(EsdgOrderFactory.getOrderStatusChangeList(cachedOrder));	
		esdgOrder.setFulfillmentStatus(EsdgOrderFactory.getFulfillmentStatus(cachedOrder));
		esdgOrder.setOrderFulfillmentStatusChangeList(EsdgOrderFactory.getOrderFulfillmentStatusChangeList(cachedOrder));
		esdgOrder.setOrderClassifierMap(EsdgOrderFactory.getOrderClassifierMap(cachedOrder));
		esdgOrder.setJsonOrder(EsdgOrderFactory.getJsonOrder(cachedOrder));
		return esdgOrder;
	}

}
