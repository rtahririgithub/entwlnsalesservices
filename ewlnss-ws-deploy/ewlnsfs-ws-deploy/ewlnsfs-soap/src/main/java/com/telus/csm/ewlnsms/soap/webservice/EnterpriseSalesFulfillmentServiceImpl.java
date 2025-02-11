package com.telus.csm.ewlnsms.soap.webservice;



import java.util.Date;

import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.util.App;
import com.telus.csm.ewlnsc.util.EnterpriseSalesServiceUtil;
import com.telus.csm.ewlnsms.soap.operation.CreateWLNOrderSoapOperation;
import com.telus.csm.ewlnsms.ws.EnterpriseSalesFulfillmentServicePortType;
import com.telus.csm.ewlnsms.ws.ServiceException;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.CancelSalesOrder;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.CancelSalesOrderResponse;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.CompleteSalesBatchOrder;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.CompleteSalesOrder;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.CompleteSalesOrderResponse;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.CompleteWirelessSalesBatchOrderResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.CreateSalesBatchOrder;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.CreateSalesOrder;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.CreateSalesOrderItem;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.CreateSalesOrderItemResponse;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.CreateSalesOrderResponse;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.CreateWirelessSalesBatchOrderResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.CreateWirelineSalesOrder;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.CreateWirelineSalesOrderResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.CreateWirelineSalesOrderType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.ReserveSalesOrder;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.ReserveSalesOrderResponse;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.ReturnSalesOrder;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.ReturnSalesOrderResponse;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.SubmitSalesToPos;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.SubmitSalesToPosResponse;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.UnreserveSalesOrder;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.UnreserveSalesOrderResponse;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.UpdatePendingSalesOrder;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.UpdatePendingSalesOrderResponse;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.UpdateSalesOrder;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.UpdateSalesOrderResponse;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.GetSalesOrderByShoppingCartResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.GetSalesOrderByShoppingCartType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.Ping;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.PingResponse;

public class EnterpriseSalesFulfillmentServiceImpl implements EnterpriseSalesFulfillmentServicePortType {

	private static final Logger LOGGER = Logger.getLogger(EnterpriseSalesFulfillmentServiceImpl.class);
	private static final Date startDate = new Date();
	private static final String UNKNOWN_ESS_ERROR = "Unknown ESS Error";
	
    public EnterpriseSalesFulfillmentServiceImpl() {
    	// 
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.csm.ewlnsms.core.domain.CreateWirelineAccountResponseType
     * @throws ServiceException
     */
//    @Override
//    public CreateWirelineSalesOrderResponseType createWirelineOrder(CreateWirelineSalesOrderType parameters) throws ServiceException {
//    	try {
//			CreateWLNOrderSoapOperation operation = new CreateWLNOrderSoapOperation();
//			return operation.execute(parameters);
//		} catch( Exception e ) {
//			LOGGER.error(UNKNOWN_ESS_ERROR, e);
//
//			CreateWirelineSalesOrderResponseType result = new CreateWirelineSalesOrderResponseType();
//
//			return result;
//		}		
//    }

	@Override
	public PingResponse ping(Ping parameters) throws ServiceException {
		PingResponse response = new PingResponse();
		response.setVersion(App.getPingInfo(startDate)) ;
		return response;
	}
	
	
	public CompleteSalesOrderResponse completeSalesOrder(CompleteSalesOrder parameters) throws ServiceException{
		return null;
	}
	
	public ReserveSalesOrderResponse reserveSalesOrder(ReserveSalesOrder parameters) throws ServiceException {
		return null;
	}
	
	public UnreserveSalesOrderResponse unreserveSalesOrder(UnreserveSalesOrder parameters) throws ServiceException{
		return null;
	}
	
	public SubmitSalesToPosResponse submitSalesToPos(SubmitSalesToPos parameters) throws ServiceException{
		return null;
	}
	
	public CancelSalesOrderResponse cancelSalesOrder(CancelSalesOrder parameters) throws ServiceException{
		return null;
	}

	public UpdatePendingSalesOrderResponse updatePendingSalesOrder(UpdatePendingSalesOrder parameters) throws ServiceException{
		return null;
	}

	public UpdateSalesOrderResponse updateSalesOrder(UpdateSalesOrder parameters) throws ServiceException{
		return null;
	}

	public ReturnSalesOrderResponse returnSalesOrder(ReturnSalesOrder parameters) throws ServiceException{
		return null;
	}

	public CreateWirelessSalesBatchOrderResponseType createSalesBatchOrder(CreateSalesBatchOrder parameters) throws ServiceException {
		return null;
	}

	@Override
	public CompleteWirelessSalesBatchOrderResponseType completeSalesBatchOrder(	CompleteSalesBatchOrder parameters) throws ServiceException {
		return null;
	}

 	public CreateSalesOrderItemResponse createSalesOrderItem(CreateSalesOrderItem parameters) throws ServiceException {
		return null;
	}

	@Override
	public CreateSalesOrderResponse createSalesOrder(CreateSalesOrder parameters) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateWirelineSalesOrderResponseType createWirelineSalesOrder(CreateWirelineSalesOrder parameters)
			throws ServiceException {
    	try {
			CreateWLNOrderSoapOperation operation = new CreateWLNOrderSoapOperation();
			return operation.execute(parameters);
		} catch( Exception e ) {
			LOGGER.error(UNKNOWN_ESS_ERROR, e);
			com.telus.tmi.xmlschema.xsd.enterprise.basetypes.exceptions_v3.FaultExceptionDetailsType fedt = new com.telus.tmi.xmlschema.xsd.enterprise.basetypes.exceptions_v3.FaultExceptionDetailsType();
			fedt.setErrorMessage(e.getMessage());
			throw new ServiceException(UNKNOWN_ESS_ERROR, fedt, e);
		}	
	}
	
 	public GetSalesOrderByShoppingCartResponseType getSalesOrderByShoppingCart(GetSalesOrderByShoppingCartType parameters) throws ServiceException {
		return null;
	}

		
}
