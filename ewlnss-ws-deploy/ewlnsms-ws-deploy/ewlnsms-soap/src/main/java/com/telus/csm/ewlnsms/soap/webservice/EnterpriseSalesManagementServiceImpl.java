package com.telus.csm.ewlnsms.soap.webservice;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.soap.webservice.EnterpriseWLNExceptionUtil;
import com.telus.csm.ewlnsc.util.App;
import com.telus.csm.ewlnsc.util.EnterpriseSalesServiceUtil;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsms.soap.operation.CreateWLNAccountSoapOperation;
import com.telus.csm.ewlnsms.soap.operation.GetSalesOrderHistoryOperation;
import com.telus.csm.ewlnsms.soap.operation.SearchServiceAddressOperation;
import com.telus.csm.ewlnsms.soap.operation.UpdateAccountSoapOperation;
import com.telus.csm.ewlnsms.ws.EnterpriseSalesManagementServicePortType;
import com.telus.csm.ewlnsms.ws.ServiceException;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.AuthenticateAccountResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.AuthenticateAccountType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.AuthenticateClientResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.AuthenticateClientType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.CalculatePriceResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.CalculatePriceType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.CreateAccountResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.CreateAccountType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.CreateClientNoteResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.CreateClientNoteType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.CreateWirelineAccountResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.CreateWirelineAccountType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetAccountSummaryResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetAccountSummaryType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetConsolidatedAccountProfileResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetConsolidatedAccountProfileType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetCorporateAccountSummaryListResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetCorporateAccountSummaryListType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetCorporateHierarchyResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetCorporateHierarchyType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetDataSharingSummaryResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetDataSharingSummaryType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetEstimatedProratedBillResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetEstimatedProratedBillType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetMultiSubscriberBenefitResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetMultiSubscriberBenefitType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetSalesOrderContractResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetSalesOrderContractType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetSalesOrderHistoryResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetSalesOrderHistoryType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetSubscriberDetailResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetSubscriberDetailType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetUsageDetailResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetUsageDetailType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.InitializeSalesResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.InitializeSalesType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.PayOutstandingAccountBalanceResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.PayOutstandingAccountBalanceType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.ReservePhoneNumberResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.ReservePhoneNumberType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.SearchClientNotesResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.SearchClientNotesType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.SearchClientResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.SearchClientType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.SearchServiceAddressResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.SearchServiceAddressType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.SubmitSalesOrderPaymentResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.SubmitSalesOrderPaymentType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.UnreservePhoneNumberResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.UnreservePhoneNumberType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.UpdateAccountResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.UpdateAccountType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.UpdateSalesReferenceResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.UpdateSalesReferenceType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.Ping;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.PingResponse;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;

public class EnterpriseSalesManagementServiceImpl implements EnterpriseSalesManagementServicePortType {

	private static final Logger LOGGER = Logger.getLogger(EnterpriseSalesManagementServiceImpl.class);
	private static final Date startDate = new Date();
	private static final String UNKNOWN_ESS_ERROR = "Unknown ESS Error";
	
    public EnterpriseSalesManagementServiceImpl() {
    	// 
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.csm.ewlnsms.core.domain.AuthenticateClientResponseType
     * @throws ServiceException
     */
    @Override
    public AuthenticateClientResponseType authenticateClient(AuthenticateClientType parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.csm.ewlnsms.core.domain.AuthenticateAccountResponseType
     * @throws ServiceException
     */
    @Override
    public AuthenticateAccountResponseType authenticateAccount(AuthenticateAccountType parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.csm.ewlnsms.core.domain.GetAccountSummaryResponseType
     * @throws ServiceException
     */
    @Override
    public GetAccountSummaryResponseType getAccountSummary(GetAccountSummaryType parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.csm.ewlnsms.core.domain.GetDataSharingSummaryResponseType
     * @throws ServiceException
     */
    @Override
    public GetDataSharingSummaryResponseType getDataSharingSummary(GetDataSharingSummaryType parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.csm.ewlnsms.core.domain.GetSubscriberDetailResponseType
     * @throws ServiceException
     */
    @Override
    public GetSubscriberDetailResponseType getSubscriberDetail(GetSubscriberDetailType parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.csm.ewlnsms.core.domain.GetConsolidatedAccountProfileResponseType
     * @throws ServiceException
     */
    @Override
    public GetConsolidatedAccountProfileResponseType getConsolidatedAccountProfile(GetConsolidatedAccountProfileType parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.csm.ewlnsms.core.domain.CalculatePriceResponseType
     * @throws ServiceException
     */
    @Override
    public CalculatePriceResponseType calculatePrice(CalculatePriceType parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.csm.ewlnsms.core.domain.SubmitSalesOrderPaymentResponseType
     * @throws ServiceException
     */
    @Override
    public SubmitSalesOrderPaymentResponseType submitSalesOrderPayment(SubmitSalesOrderPaymentType parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.csm.ewlnsms.core.domain.GetUsageDetailResponseType
     * @throws ServiceException
     */
    @Override
    public GetUsageDetailResponseType getUsageDetail(GetUsageDetailType parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.csm.ewlnsms.core.domain.GetSalesOrderHistoryResponseType
     * @throws ServiceException
     */
    @Override
    public GetSalesOrderHistoryResponseType getSalesOrderHistory(GetSalesOrderHistoryType parameters)
        throws ServiceException
    {
    	try {
    		GetSalesOrderHistoryOperation operation = new GetSalesOrderHistoryOperation();
			return operation.execute(parameters);
		} catch( Exception e ) {
			LOGGER.error(UNKNOWN_ESS_ERROR, e);
			com.telus.tmi.xmlschema.xsd.enterprise.basetypes.exceptions_v3.FaultExceptionDetailsType fedt = new com.telus.tmi.xmlschema.xsd.enterprise.basetypes.exceptions_v3.FaultExceptionDetailsType();
			fedt.setErrorMessage(EnterpriseSalesServiceUtil.getStackTrace(e));
			throw new ServiceException(UNKNOWN_ESS_ERROR, fedt);
		}		
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.csm.ewlnsms.core.domain.GetSalesOrderContractResponseType
     * @throws ServiceException
     */
    @Override
    public GetSalesOrderContractResponseType getSalesOrderContract(GetSalesOrderContractType parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.csm.ewlnsms.core.domain.GetEstimatedProratedBillResponseType
     * @throws ServiceException
     */
    @Override
    public GetEstimatedProratedBillResponseType getEstimatedProratedBill(GetEstimatedProratedBillType parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.csm.ewlnsms.core.domain.PayOutstandingAccountBalanceResponseType
     * @throws ServiceException
     */
    @Override
    public PayOutstandingAccountBalanceResponseType payOutstandingAccountBalanceAndRestoreSuspension(PayOutstandingAccountBalanceType parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.csm.ewlnsms.core.domain.CreateAccountResponseType
     * @throws ServiceException
     */
    @Override
    public CreateAccountResponseType createAccount(CreateAccountType parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.csm.ewlnsms.core.domain.ReservePhoneNumberResponseType
     * @throws ServiceException
     */
    @Override
    public ReservePhoneNumberResponseType reservePhoneNumber(ReservePhoneNumberType parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.csm.ewlnsms.core.domain.UnreservePhoneNumberResponseType
     * @throws ServiceException
     */
    @Override
    public UnreservePhoneNumberResponseType unreservePhoneNumber(UnreservePhoneNumberType parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.csm.ewlnsms.core.domain.SearchClientNotesResponseType
     * @throws ServiceException
     */
    @Override
    public SearchClientNotesResponseType searchClientNotes(SearchClientNotesType parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.csm.ewlnsms.core.domain.CreateClientNoteResponseType
     * @throws ServiceException
     */
    @Override
    public CreateClientNoteResponseType createClientNote(CreateClientNoteType parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.csm.ewlnsms.core.domain.InitializeSalesResponseType
     * @throws ServiceException
     */
    @Override
    public InitializeSalesResponseType initializeSales(InitializeSalesType parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.csm.ewlnsms.core.domain.GetCorporateHierarchyResponseType
     * @throws ServiceException
     */
    @Override
    public GetCorporateHierarchyResponseType getCorporateHierarchy(GetCorporateHierarchyType parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.csm.ewlnsms.core.domain.GetCorporateAccountSummaryListResponseType
     * @throws ServiceException
     */
    @Override
    public GetCorporateAccountSummaryListResponseType getCorporateAccountSummaryList(GetCorporateAccountSummaryListType parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.csm.ewlnsms.core.domain.SearchClientResponseType
     * @throws ServiceException
     */
    @Override
    public SearchClientResponseType searchClient(SearchClientType parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.csm.ewlnsms.core.domain.UpdateSalesReferenceResponseType
     * @throws ServiceException
     */
    @Override
    public UpdateSalesReferenceResponseType updateSalesReference(UpdateSalesReferenceType parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.csm.ewlnsms.core.domain.SearchServiceAddressResponseType
     * @throws ServiceException
     */
    @Override
    public SearchServiceAddressResponseType searchServiceAddress(SearchServiceAddressType parameters)
        throws ServiceException
    {
    	try {
    		SearchServiceAddressOperation operation = new SearchServiceAddressOperation();
			return operation.execute(parameters);
		} catch( Exception e ) {
			LOGGER.error(UNKNOWN_ESS_ERROR, e);
			com.telus.tmi.xmlschema.xsd.enterprise.basetypes.exceptions_v3.FaultExceptionDetailsType fedt = new com.telus.tmi.xmlschema.xsd.enterprise.basetypes.exceptions_v3.FaultExceptionDetailsType();
			fedt.setErrorMessage(EnterpriseSalesServiceUtil.getStackTrace(e));
			throw new ServiceException(UNKNOWN_ESS_ERROR, fedt);
		}		
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.csm.ewlnsms.core.domain.CreateWirelineAccountResponseType
     * @throws ServiceException
     */
    @Override
    public CreateWirelineAccountResponseType createWirelineAccount(CreateWirelineAccountType parameters)
        throws ServiceException
    {
    	try {
			CreateWLNAccountSoapOperation operation = new CreateWLNAccountSoapOperation();
			return operation.execute(parameters);
		} catch( Exception e ) {
			LOGGER.error(UNKNOWN_ESS_ERROR, e);
			String transId = "";
			if(!StringUtils.isBlank(parameters.getOperationHeader().getSalesTransactionId())){
				transId = parameters.getOperationHeader().getSalesTransactionId();
			}
			CreateWirelineAccountResponseType result = new CreateWirelineAccountResponseType();
			List<SalesResponseMessage.MessageList> messageList = new ArrayList<SalesResponseMessage.MessageList>();
			MessageList exceptionDetails = EnterpriseWLNExceptionUtil.getExceptionDetails(transId, e,EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_EXCEPTION_ERROR);
			messageList.add(exceptionDetails);
			result.setMessageList(messageList);
			return result;
		}		
    }

	@Override
	public PingResponse ping(Ping parameters) throws ServiceException {

		PingResponse response = new PingResponse();
		response.setVersion(App.getPingInfo(startDate)) ;
		return response;

	}

	@Override
	public UpdateAccountResponseType updateAccount(UpdateAccountType parameters) throws ServiceException {
		//NWLN-9103 Update Account
		try {
			UpdateAccountSoapOperation operation = new UpdateAccountSoapOperation();
			return operation.execute(parameters);
		} catch( Exception e ) {
			LOGGER.error(UNKNOWN_ESS_ERROR, e);
			String transId = "";
			if(!StringUtils.isBlank(parameters.getOperationHeader().getSalesTransactionId())){
				transId = parameters.getOperationHeader().getSalesTransactionId();
			}
			UpdateAccountResponseType result = new UpdateAccountResponseType();
			List<SalesResponseMessage.MessageList> messageList = new ArrayList<SalesResponseMessage.MessageList>();
			MessageList exceptionDetails = EnterpriseWLNExceptionUtil.getExceptionDetails(transId, e,EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_EXCEPTION_ERROR);
			messageList.add(exceptionDetails);
			result.setMessageList(messageList);
			return result;
		}		
	}
	
    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.csm.ewlnsms.core.domain.GetMultiSubscriberBenefitResponseType
     * @throws ServiceException
     */
	@Override
	public GetMultiSubscriberBenefitResponseType getMultiSubscriberBenefit(GetMultiSubscriberBenefitType parameters) throws ServiceException {
		//replace with your impl here
		return null;
	}


}
