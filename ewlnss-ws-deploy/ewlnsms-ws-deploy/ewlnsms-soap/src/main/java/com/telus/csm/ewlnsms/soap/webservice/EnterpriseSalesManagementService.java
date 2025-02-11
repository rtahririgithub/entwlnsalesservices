package com.telus.csm.ewlnsms.soap.webservice;

import javax.jws.WebService;
import javax.xml.ws.BindingType;

import com.telus.csm.ewlnsc.soap.webservice.SalesServiceSoapBase;
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
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetMultiSubscriberBenefitResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetMultiSubscriberBenefitType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.Ping;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.PingResponse;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6b21 
 * Generated source version: 2.1
 * 
 */
@WebService(portName = "EnterpriseSalesManagementServicePort", 
serviceName = "EnterpriseSalesManagementService_v5_0", 
targetNamespace = "http://telus.com/wsdl/MSO/ChannelSalesMgmt/EnterpriseSalesManagementService_5", 
wsdlLocation = "/wsdls/EnterpriseSalesManagementService_v5_0.wsdl", 
endpointInterface = "com.telus.csm.ewlnsms.ws.EnterpriseSalesManagementServicePortType")
@BindingType("http://schemas.xmlsoap.org/wsdl/soap/http")
public class EnterpriseSalesManagementService extends SalesServiceSoapBase
implements EnterpriseSalesManagementServicePortType {

    public EnterpriseSalesManagementService() {
    	// 
    }
    
	@Override
	protected Object getNewServiceImpl() {
		return new EnterpriseSalesManagementServiceImpl();
	}

	private EnterpriseSalesManagementServicePortType getProxy() {
		return (EnterpriseSalesManagementServicePortType) getServiceProxy();
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
        return getProxy().authenticateClient(parameters);
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
        return getProxy().authenticateAccount(parameters);
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
        return getProxy().getAccountSummary(parameters);
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
        return getProxy().getDataSharingSummary(parameters);
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
        return getProxy().getSubscriberDetail(parameters);
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
        return getProxy().getConsolidatedAccountProfile(parameters);
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
        return getProxy().calculatePrice(parameters);
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
        return getProxy().submitSalesOrderPayment(parameters);
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
        return getProxy().getUsageDetail(parameters);
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
        return getProxy().getSalesOrderHistory(parameters);
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
        return getProxy().getSalesOrderContract(parameters);
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
        return getProxy().getEstimatedProratedBill(parameters);
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
        return getProxy().payOutstandingAccountBalanceAndRestoreSuspension(parameters);
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
        return getProxy().createAccount(parameters);
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
        return getProxy().reservePhoneNumber(parameters);
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
        return getProxy().unreservePhoneNumber(parameters);
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
        return getProxy().searchClientNotes(parameters);
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
        return getProxy().createClientNote(parameters);
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
        return getProxy().initializeSales(parameters);
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
        return getProxy().getCorporateHierarchy(parameters);
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
        return getProxy().getCorporateAccountSummaryList(parameters);
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
        return getProxy().searchClient(parameters);
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
        return getProxy().updateSalesReference(parameters);
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
    	return getProxy().searchServiceAddress(parameters);
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
        return getProxy().createWirelineAccount(parameters);
    }

	@Override
	public PingResponse ping(Ping parameters) throws ServiceException {
        return getProxy().ping(parameters);
	}

	@Override
	public UpdateAccountResponseType updateAccount(UpdateAccountType parameters) throws ServiceException {
		return getProxy().updateAccount(parameters);
	}
	
	@Override
	 public GetMultiSubscriberBenefitResponseType getMultiSubscriberBenefit(GetMultiSubscriberBenefitType parameters) throws ServiceException {
		return getProxy().getMultiSubscriberBenefit(parameters);
	}

}
