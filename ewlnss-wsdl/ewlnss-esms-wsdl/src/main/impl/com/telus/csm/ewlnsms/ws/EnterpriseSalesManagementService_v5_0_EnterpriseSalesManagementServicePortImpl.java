
package com.telus.csm.ewlnsms.ws;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
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


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6b21 
 * Generated source version: 2.1
 * 
 */
@WebService(portName = "EnterpriseSalesManagementServicePort", serviceName = "EnterpriseSalesManagementService_v5_0", targetNamespace = "http://telus.com/wsdl/MSO/ChannelSalesMgmt/EnterpriseSalesManagementService_5", wsdlLocation = "/wsdls/EnterpriseSalesManagementService_v5_0.wsdl", endpointInterface = "com.telus.csm.ewlnsms.ws.EnterpriseSalesManagementServicePortType")
@BindingType("http://schemas.xmlsoap.org/wsdl/soap/http")
public class EnterpriseSalesManagementService_v5_0_EnterpriseSalesManagementServicePortImpl
    implements EnterpriseSalesManagementServicePortType
{


    public EnterpriseSalesManagementService_v5_0_EnterpriseSalesManagementServicePortImpl() {
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.AuthenticateClientResponseType
     * @throws ServiceException
     */
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
     *     returns com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.AuthenticateAccountResponseType
     * @throws ServiceException
     */
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
     *     returns com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetAccountSummaryResponseType
     * @throws ServiceException
     */
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
     *     returns com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetDataSharingSummaryResponseType
     * @throws ServiceException
     */
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
     *     returns com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetSubscriberDetailResponseType
     * @throws ServiceException
     */
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
     *     returns com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetConsolidatedAccountProfileResponseType
     * @throws ServiceException
     */
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
     *     returns com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.CalculatePriceResponseType
     * @throws ServiceException
     */
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
     *     returns com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.SubmitSalesOrderPaymentResponseType
     * @throws ServiceException
     */
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
     *     returns com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetUsageDetailResponseType
     * @throws ServiceException
     */
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
     *     returns com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetSalesOrderHistoryResponseType
     * @throws ServiceException
     */
    public GetSalesOrderHistoryResponseType getSalesOrderHistory(GetSalesOrderHistoryType parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetSalesOrderContractResponseType
     * @throws ServiceException
     */
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
     *     returns com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetEstimatedProratedBillResponseType
     * @throws ServiceException
     */
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
     *     returns com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.PayOutstandingAccountBalanceResponseType
     * @throws ServiceException
     */
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
     *     returns com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.CreateAccountResponseType
     * @throws ServiceException
     */
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
     *     returns com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.ReservePhoneNumberResponseType
     * @throws ServiceException
     */
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
     *     returns com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.UnreservePhoneNumberResponseType
     * @throws ServiceException
     */
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
     *     returns com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.SearchClientNotesResponseType
     * @throws ServiceException
     */
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
     *     returns com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.CreateClientNoteResponseType
     * @throws ServiceException
     */
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
     *     returns com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.InitializeSalesResponseType
     * @throws ServiceException
     */
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
     *     returns com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetCorporateHierarchyResponseType
     * @throws ServiceException
     */
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
     *     returns com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetCorporateAccountSummaryListResponseType
     * @throws ServiceException
     */
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
     *     returns com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.SearchClientResponseType
     * @throws ServiceException
     */
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
     *     returns com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.UpdateSalesReferenceResponseType
     * @throws ServiceException
     */
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
     *     returns com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.SearchServiceAddressResponseType
     * @throws ServiceException
     */
    public SearchServiceAddressResponseType searchServiceAddress(SearchServiceAddressType parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.CreateWirelineAccountResponseType
     * @throws ServiceException
     */
    public CreateWirelineAccountResponseType createWirelineAccount(CreateWirelineAccountType parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.UpdateAccountResponseType
     * @throws ServiceException
     */
    public UpdateAccountResponseType updateAccount(UpdateAccountType parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetMultiSubscriberBenefitResponseType
     * @throws ServiceException
     */
    public GetMultiSubscriberBenefitResponseType getMultiSubscriberBenefit(GetMultiSubscriberBenefitType parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

    /**
     * 
     * @param parameters
     * @return
     *     returns com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.PingResponse
     * @throws ServiceException
     */
    public PingResponse ping(Ping parameters)
        throws ServiceException
    {
        //replace with your impl here
        return null;
    }

}
