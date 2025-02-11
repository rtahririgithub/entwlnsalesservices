package com.telus.csm.ewlnsc.delegate;

import com.telus.csm.ewlnsc.adapter.IOrderQueryRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.IWLNBookingRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.IWLNOrderChargingRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.IWLNOrderShoppingCartRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.CreateInvoiceAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CreateInvoiceAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetDepositInfoRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetDepositInfoResponse;
import com.telus.csm.ewlnsc.adapter.domain.quote.QuoteRequest;
import com.telus.csm.ewlnsc.adapter.domain.quote.QuoteResponse;
import com.telus.csm.ewlnsc.adapter.oqs.domain.GetOrderSummaryByOrderIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.oqs.domain.GetOrderSummaryByOrderIdAdapterResponse;
import com.telus.csm.ewlnsc.adapter.wbk.domain.CancelBookingAdapterRequest;
import com.telus.csm.ewlnsc.adapter.wbk.domain.CancelBookingAdapterResponse;
import com.telus.csm.ewlnsc.adapter.wbk.domain.ConfirmBookingAdapterRequest;
import com.telus.csm.ewlnsc.adapter.wbk.domain.ConfirmBookingAdapterResponse;
import com.telus.csm.ewlnsc.adapter.wbk.domain.GetBookingRequirementRequest;
import com.telus.csm.ewlnsc.adapter.wbk.domain.GetBookingRequirementResponse;
import com.telus.csm.ewlnsc.adapter.woscs.domain.CreateOrderAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.CreateOrderAdapterResponse;
import com.telus.csm.ewlnsc.adapter.woscs.domain.SubmitOrderAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.SubmitOrderAdapterResponse;
import com.telus.csm.ewlnsc.adapter.woscs.domain.UpdateOrderAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.UpdateOrderAdapterResponse;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ValidateProductConfigAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ValidateProductConfigAdapterResponse;
import com.telus.csm.ewlnsc.helper.OperationHeaderUtil;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;

public class ExternalOrderManager {
	/**
	 * Manage interaction with downstream order systems, currently only interact with OP, maybe Netcracker in the future
	 */
	
	private ExternalOrderManager(){
		
	}

	public static CreateOrderAdapterResponse createOrder(OperationHeader operationHeader, CreateOrderAdapterRequest request){
		
		AdapterFeatureDriver adapterFeatureDriver = OperationHeaderUtil.getAdapterFeatureDriver(operationHeader);
		
		IWLNOrderShoppingCartRestSvcAdapter shoppingCartAdapter = AdapterFactory.getAdapter(IWLNOrderShoppingCartRestSvcAdapter.class, adapterFeatureDriver);
		return shoppingCartAdapter.createOrder(request);
	}
	
	public static SubmitOrderAdapterResponse submitOrder(OperationHeader operationHeader, SubmitOrderAdapterRequest request){
		
		AdapterFeatureDriver adapterFeatureDriver = OperationHeaderUtil.getAdapterFeatureDriver(operationHeader);
		
		IWLNOrderShoppingCartRestSvcAdapter shoppingCartAdapter = AdapterFactory.getAdapter(IWLNOrderShoppingCartRestSvcAdapter.class, adapterFeatureDriver);
		return shoppingCartAdapter.submitOrder(request);
	}
	
	public static UpdateOrderAdapterResponse updateOrder(OperationHeader operationHeader, UpdateOrderAdapterRequest request){
		
		AdapterFeatureDriver adapterFeatureDriver = OperationHeaderUtil.getAdapterFeatureDriver(operationHeader);
		
		IWLNOrderShoppingCartRestSvcAdapter shoppingCartAdapter = AdapterFactory.getAdapter(IWLNOrderShoppingCartRestSvcAdapter.class, adapterFeatureDriver);
		return shoppingCartAdapter.updateOrder(request);
	}

	public static ValidateProductConfigAdapterResponse validateProductConfig(OperationHeader operationHeader, ValidateProductConfigAdapterRequest request){
		
		AdapterFeatureDriver adapterFeatureDriver = OperationHeaderUtil.getAdapterFeatureDriver(operationHeader);
		
		IWLNOrderShoppingCartRestSvcAdapter shoppingCartAdapter = AdapterFactory.getAdapter(IWLNOrderShoppingCartRestSvcAdapter.class, adapterFeatureDriver);
		return shoppingCartAdapter.validateProductConfig(request);
	}
	
	public static ConfirmBookingAdapterResponse confirmBooking(OperationHeader operationHeader, ConfirmBookingAdapterRequest confirmBookingAdapterRequest) {
		AdapterFeatureDriver adapterFeatureDriver = OperationHeaderUtil.getAdapterFeatureDriver(operationHeader);
		IWLNBookingRestSvcAdapter bookingAdapter = AdapterFactory.getAdapter(IWLNBookingRestSvcAdapter.class, adapterFeatureDriver);
		ConfirmBookingAdapterResponse confirmBookingResponse = bookingAdapter.confirmBooking(confirmBookingAdapterRequest);
		return confirmBookingResponse;
	}
	
	public static CancelBookingAdapterResponse cancelBooking(OperationHeader operationHeader, CancelBookingAdapterRequest cancelBookingAdapterRequest) {
		AdapterFeatureDriver adapterFeatureDriver = OperationHeaderUtil.getAdapterFeatureDriver(operationHeader);
		IWLNBookingRestSvcAdapter bookingAdapter = AdapterFactory.getAdapter(IWLNBookingRestSvcAdapter.class, adapterFeatureDriver);
		CancelBookingAdapterResponse cancelBookingResponse = bookingAdapter.cancelBooking(cancelBookingAdapterRequest);
		return cancelBookingResponse;
	}
	
	public static GetDepositInfoResponse getDepositInfo(OperationHeader operationHeader, GetDepositInfoRequest getDepositInfoRequest) {
		AdapterFeatureDriver adapterFeatureDriver = OperationHeaderUtil.getAdapterFeatureDriver(operationHeader);
		IWLNOrderChargingRestSvcAdapter chargingAdapter = AdapterFactory.getAdapter(IWLNOrderChargingRestSvcAdapter.class, adapterFeatureDriver);
		GetDepositInfoResponse getDepositInfoResponse = chargingAdapter.getDepositInfo(getDepositInfoRequest);
		return getDepositInfoResponse;
	}
	
	public static GetOrderSummaryByOrderIdAdapterResponse getOrderSummaryByOrderId(OperationHeader operationHeader, GetOrderSummaryByOrderIdAdapterRequest getOrderSummaryByOrderIdAdapterRequest) {
		AdapterFeatureDriver adapterFeatureDriver = OperationHeaderUtil.getAdapterFeatureDriver(operationHeader);
		IOrderQueryRestSvcAdapter orderQueryAdapter = AdapterFactory.getAdapter(IOrderQueryRestSvcAdapter.class, adapterFeatureDriver);
		GetOrderSummaryByOrderIdAdapterResponse getOrderSummaryByOrderIdAdapterResponse = orderQueryAdapter.getOrderSummaryByOrderId(getOrderSummaryByOrderIdAdapterRequest);
		return getOrderSummaryByOrderIdAdapterResponse;
	}
	
	public static CreateInvoiceAdapterResponse createInvoice(OperationHeader operationHeader, CreateInvoiceAdapterRequest createInvoiceAdapterRequest) {
		AdapterFeatureDriver adapterFeatureDriver = OperationHeaderUtil.getAdapterFeatureDriver(operationHeader);
		IWLNOrderChargingRestSvcAdapter chargingAdapter = AdapterFactory.getAdapter(IWLNOrderChargingRestSvcAdapter.class, adapterFeatureDriver);
		CreateInvoiceAdapterResponse createInvoiceAdapterResponse = chargingAdapter.createInvoice(createInvoiceAdapterRequest);
		return createInvoiceAdapterResponse;
	}
	
	public static GetBookingRequirementResponse getBookingRequirement(OperationHeader operationHeader, GetBookingRequirementRequest getBookingRequirementRequest) {
		AdapterFeatureDriver adapterFeatureDriver = OperationHeaderUtil.getAdapterFeatureDriver(operationHeader);
		IWLNBookingRestSvcAdapter bookingAdapter = AdapterFactory.getAdapter(IWLNBookingRestSvcAdapter.class, adapterFeatureDriver);
		GetBookingRequirementResponse getBookingRequirementResponse = bookingAdapter.getBookingRequirement(getBookingRequirementRequest);
		return getBookingRequirementResponse;
	}
	
	public static QuoteResponse getQuote(OperationHeader operationHeader, QuoteRequest quoteRequest) {
		AdapterFeatureDriver adapterFeatureDriver = OperationHeaderUtil.getAdapterFeatureDriver(operationHeader);
		IWLNOrderChargingRestSvcAdapter chargingAdapter = AdapterFactory.getAdapter(IWLNOrderChargingRestSvcAdapter.class, adapterFeatureDriver);
		QuoteResponse quoteResponse = chargingAdapter.getQuote(quoteRequest);
		return quoteResponse;
	}

}
