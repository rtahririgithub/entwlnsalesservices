
package com.telus.csm.ewlnsc.transformer;

import static com.telus.csm.ewlnsc.util.CharacteristicUtils.getFloatByName;
import static com.telus.csm.ewlnsc.util.CharacteristicUtils.getValueByName;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.adapter.fwa.domain.FieldWorkAppointmentAdapterRequest;
import com.telus.csm.ewlnsc.domain.OperationHeaderVO;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.fifa.sc.domain.CartItem;
import com.telus.csm.fifa.sc.domain.Place;
import com.telus.csm.fifa.sc.domain.ShoppingCart;
import com.telus.csm.fifa.sc.domain.TCharacteristic;
import com.telus.tmi.xmlschema.xsd.resource.resource.workforcemanagementordertypes_v3.Component;
import com.telus.tmi.xmlschema.xsd.resource.resource.workforcemanagementordertypes_v3.ComponentList;
import com.telus.tmi.xmlschema.xsd.resource.resource.workforcemanagementordertypes_v3.InputHeader;
import com.telus.tmi.xmlschema.xsd.resource.resource.workforcemanagementordertypes_v3.Location;
import com.telus.tmi.xmlschema.xsd.resource.resource.workforcemanagementordertypes_v3.LocationAddress;
import com.telus.tmi.xmlschema.xsd.resource.resource.workforcemanagementordertypes_v3.TypeCode;
import com.telus.tmi.xmlschema.xsd.resource.resource.workforcemanagementordertypes_v3.TypeCodeList;
import com.telus.tmi.xmlschema.xsd.resource.resource.workforcemanagementordertypes_v3.TypedLocationAddress;
import com.telus.tmi.xmlschema.xsd.resource.resource.workforcemanagementordertypes_v3.WorkOrder;


/**
 * This class transform FifaShoppingCart to WFM request
 *
 */
public class FieldWorkAppointmentSvcTransformer implements EnterpriseWLNSalesServicesConstants {

	static final Logger LOGGER = Logger.getLogger(FieldWorkAppointmentSvcTransformer.class);
	
	public static  FieldWorkAppointmentAdapterRequest transform( OperationHeaderVO operationHeader, Date startDate, Date endDate, ShoppingCart fifaShoppingCart, String projectCode, String techLanguage ) {
		
		FieldWorkAppointmentAdapterRequest appointmentRequest = new FieldWorkAppointmentAdapterRequest();

		appointmentRequest.setSalesTransactionId(operationHeader.getSalesTransactionId());
		
		appointmentRequest.setStartDate( startDate );
		appointmentRequest.setEndDate( endDate );
		appointmentRequest.setGradeAppointmentInd(false);
		appointmentRequest.setFullSearchInd(false);

		InputHeader inputHeader = new InputHeader();
		inputHeader.setRequestDate( new Date() );
		inputHeader.setSystemSourceCd( operationHeader.getSourceSystemId() );
		inputHeader.setUserId( operationHeader.getUserId() );
		appointmentRequest.setInputHeader(inputHeader);
		
		WorkOrder workOrder = getWorkOrder( fifaShoppingCart );
		workOrder.setProjectCd( projectCode );

		if (!"DEFAULT".equalsIgnoreCase(techLanguage)) {
			TypeCode requiredLanguage = new TypeCode();
			requiredLanguage.setTypeCd("REQUIRED_LANGUAGE");
			requiredLanguage.setDescriptionTxt(techLanguage);
			List<TypeCode> list = new ArrayList<TypeCode>();
			list.add( requiredLanguage );
			TypeCodeList attributeList = new TypeCodeList();
			attributeList.setTypeCode( list );
			workOrder.setWorkOrderAttributeList(attributeList);
		}
		
		appointmentRequest.setWorkOrder( workOrder );
		
		return appointmentRequest;
	}
	
	public static WorkOrder getWorkOrder(ShoppingCart shoppingCart) {
		
		WorkOrder workOrder = new WorkOrder();
		
		workOrder.setClassificationCd("ORDER");//mandatory
		workOrder.setServiceClassCd("R");//mandatory
		
		workOrder.setProductCategoryCd("MASS_MARKET");
		workOrder.setJobTypeCd("MASS_MARKET");
		
		workOrder.setLocation( getServiceAddressFromPlaces ( shoppingCart.getPlace() ) );
		
		CartItem workOfferTopCartItem = getWorOfferCartItem ( shoppingCart.getCartItem() );
		if ( workOfferTopCartItem!=null ) {

			//mandatory
			workOrder.setOriginatingSystemId(  getValueByName( workOfferTopCartItem.getProduct().getCharacteristic(), FIFA_WORK_OFFER_PROD_CHARAC_ORIGINATING_SYSTEM_ID ) ); 
			
			List<CartItem> workOfferChildItems = workOfferTopCartItem.getCartItem();
			if ( CollectionUtils.isNotEmpty( workOfferChildItems ) ) {
				
				workOrder.setComponentList( getCommponentList( workOfferChildItems ) );
				
			} else {
				LOGGER.warn("fifa shoppingCart topLevel cartItem[product=orkOffer] does not have child cartItems.");

			}
		} else {
			LOGGER.warn("fifa shoppingCart does not have topLevel cartItem[product=WorkOffer].");
		}
		
		return workOrder;
	}

	private static Location getServiceAddressFromPlaces( List<Place> placeList ) {
		
		List<TypedLocationAddress> locationList = new ArrayList<TypedLocationAddress> ();
		
		if ( isNotEmpty( placeList) ) {
			for( Place place: placeList ) {
				addServiceAddress(locationList, place);
			}
		}
		
		Location location = null;
		if ( locationList.isEmpty()==false ) {
			location = new Location();
			location.setLocationList(locationList);
		}
		
		return location;
	}

	static void addServiceAddress(List<TypedLocationAddress> locationList, Place place) {
		
		if ( "SERVICEADDRESS".equalsIgnoreCase(((place.getRole().replace(" ","" )).replace("_","")))) {
			TypedLocationAddress locationAddress = new TypedLocationAddress();
			locationAddress.setLocationAddress(new LocationAddress());
			locationAddress.getLocationAddress().setLocationId(Long.valueOf(place.getId()));
			locationAddress.setDispatchLocationInd(true);
			locationAddress.setTypeCode("SERVICE" );
			locationList.add(  locationAddress );
		}
	}

	/*
	private static Location getServiceLocationFromCartItems( List<CartItem> cartItemList ) {

		if ( isNotEmpty( cartItemList) ) {
			
			List<Place> allPlaces = new ArrayList<Place> () ;
			
			for( CartItem cartItem:  cartItemList ) {
				
				if ( cartItem.getProduct()!=null && 
					isNotEmpty(cartItem.getProduct().getPlace())) {
					allPlaces.addAll( cartItem.getProduct().getPlace() );
				}
			}
			
			return getServiceAddressFromPlaces( allPlaces );
		}
		
		return null;
	}
	*/

	private static CartItem getWorOfferCartItem(List<CartItem> topLevelCartItems) {
		CartItem cartItem = null;
		if ( isNotEmpty(topLevelCartItems ) ) {
			for ( CartItem topLevelCartItem: topLevelCartItems ) {
				if( "WorkOffer".equalsIgnoreCase( topLevelCartItem.getProduct().getName().replaceAll(" ", "") ) ) {
					cartItem = topLevelCartItem;
				}
			}
		}
		return cartItem;
	}
	
	private static ComponentList getCommponentList(List<CartItem> cartItemList) {
		
		ComponentList componentList = null;
		
		if ( isNotEmpty(cartItemList ) ) {
			componentList = new ComponentList();
			List<Component> components = new ArrayList<Component>();
			componentList.setComponent(components);
			
			for ( CartItem cartItem: cartItemList ) {
				components.add( mapToCommponent( cartItem ) ) ;
			}
		}
		
		return componentList;
	}


	private static Component mapToCommponent(CartItem cartItem) {
		
		List<TCharacteristic> characteristic = cartItem.getProduct().getCharacteristic();

		Component component = new Component();
		component.setProductCategoryCd( getValueByName( characteristic, FIFA_WORK_OFFER_PROD_CHARAC_PRODCUT_CATEGORY_CD ) );
		component.setJobTypeCd( getValueByName( characteristic, FIFA_WORK_OFFER_PROD_CHARAC_JOB_TYPE_CD ) );
		component.setWorkOrderActionCd( getValueByName( characteristic, FIFA_WORK_OFFER_PROD_CHARAC_WORDORDER_ACTION_CD ) );
		component.setProductTechnologyCd( getValueByName( characteristic, FIFA_WORK_OFFER_PROD_CHARAC_PRODDCT_TECHNOLOGY_CD ) );
		component.setInstallationTypeCd( getValueByName( characteristic, FIFA_WORK_OFFER_PROD_CHARAC_INSTALLATION_TYPE_CD ) );
		component.setEstimatedDurationNum( getFloatByName( characteristic, FIFA_WORK_OFFER_PROD_CHARAC_ESTIMATED_DURATION ) );
		component.setEstimatedDurationUnitCd( getValueByName( characteristic, FIFA_WORK_OFFER_PROD_CHARAC_ESTIMATED_DURATION_UNIT_CD ) );
		
		return component;
	}
	
}
