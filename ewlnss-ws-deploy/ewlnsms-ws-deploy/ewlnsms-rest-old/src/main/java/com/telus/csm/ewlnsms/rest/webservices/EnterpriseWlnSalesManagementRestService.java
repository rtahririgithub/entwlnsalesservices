package com.telus.csm.ewlnsms.rest.webservices;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;

import com.telus.csm.ewlnsc.rest.domain.EntWLNSalesRestResponseBase;
import com.telus.csm.ewlnsc.rest.webservice.SalesServiceRestBase;
import com.telus.csm.ewlnsms.rest.webservices.vo.AccountReqRestDO;
import com.telus.csm.ewlnsms.rest.webservices.vo.AccountRespRestDO;
import com.telus.csm.ewlnsms.rest.webservices.vo.CreditInfomationRespRestVO;
import com.telus.csm.ewlnsms.rest.webservices.vo.CreditProfileVO;
import com.telus.csm.ewlnsms.rest.webservices.vo.CustomerEligibilityRespRestDO;
import com.telus.csm.ewlnsms.rest.webservices.vo.Equipment;



@Path("/")
public class EnterpriseWlnSalesManagementRestService extends SalesServiceRestBase {
	
	static Logger logger = Logger.getLogger(EnterpriseWlnSalesManagementRestService.class);

	@Context
    ServletContext servletContext;
	
//	@Autowired
//	EnterpriseWlnSalesManagementService enterpriseWlnSalesManagementService;
	
	@GET
	@Path("/demo")
	@Produces(MediaType.TEXT_PLAIN)
	public String getServiceDemo() {

//		return getServiceDemo(this.getClass());
		return "";
	}

	@POST
	@Path("/customer/account")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response account(
			AccountReqRestDO accountReqRest,
			@QueryParam("lang") String lang,	
			@QueryParam("appid") String appId,
			@QueryParam("channeloutletid") String channelOutletId,
			@QueryParam("salestransid") String salesTransactionId,
			@QueryParam("salesrepid") String salesRepId,
			@QueryParam("extsalesrepid") String extSalesRepId,
			@QueryParam("custid") String custId,	
			@Context UriInfo uriInfo
			) {
		
		logger.error("test - error log");
		
		
		String type = AccountRespRestDO.STUB_TYPE_MATCHING_CREADIT_PROFILE_BAN_ACTIVE; 
		if (!StringUtils.isEmpty(custId))
			type = AccountRespRestDO.STUB_TYPE_ACCOUNT_CREATED;
		else {
			String ln = accountReqRest.getAccountinfo().getLastName().toUpperCase();
			if (ln.endsWith("A"))
				type = AccountRespRestDO.STUB_TYPE_MATCHING_CREADIT_PROFILE_BAN_ACTIVE;
			else if (ln.endsWith("B"))
				type = AccountRespRestDO.STUB_TYPE_MATCHING_CREADIT_PROFILE_BAN_NON_POS_BAL;
			else if (ln.endsWith("C"))
				type = AccountRespRestDO.STUB_TYPE_MATCHING_CREADIT_PROFILE_BAN_POS_BAL;
			else if (ln.endsWith("D"))
				type = AccountRespRestDO.STUB_TYPE_CUSTOMER_ACCOUNT_CREATED;
		}
	
		EntWLNSalesRestResponseBase responseRestDO = AccountRespRestDO.getStub(type, custId);
		return Response.status(responseRestDO.getStatus().getStatusCd()).entity(responseRestDO).build();
	}

	@POST
	@Path("/customer/{custId}")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response customerEligibility(
			CreditProfileVO creditProfile,
			@PathParam("custId") String customerId,   
			@QueryParam("appid") String appId,
			@QueryParam("channeloutletid") String channelOutletId,
			@QueryParam("salestransactionid") String salesTransactionId,
			@QueryParam("salesrepid") String salesRepId,
			@QueryParam("extsalesrepid") String extSalesRepId,
			@QueryParam("lang") String language,	
			@QueryParam("refreshInd") Boolean refreshInd,	
			@QueryParam("newCustomerInd") Boolean newCustomerInd,
			@Context UriInfo uriInfo
			) {
		
    
//	GetCustomerEligibilityRequestDO getCustomerELigibilityRequestDO = buildRequest(creditProfile, customerId, appId, channelOutletId,salesTransactionId, salesRepId, extSalesRepId, language, refreshInd, newCustomer);	
//	GetCustomerEligibilityResponseDO getCustomerEligibilityResponseDO = enterpriseWlnSalesManagementService.getCustomerEligibility(getCustomerELigibilityRequestDO);
	
	//BaseResponseRestDO responseRestDO = transform(null);
		EntWLNSalesRestResponseBase responseRestDO = CustomerEligibilityRespRestDO.getStub(creditProfile, customerId, appId, channelOutletId, salesTransactionId, salesRepId, extSalesRepId, language, refreshInd, newCustomerInd, uriInfo);
		return Response.status(responseRestDO.getStatus().getStatusCd()).entity(responseRestDO).build();
	}
	
	
	/**
	 * Stub operation
	 * @return
	 */
	private CreditInfomationRespRestVO getCreditInformationResponseMockResult(){
		CreditInfomationRespRestVO creditInfomationRespRestVO = new CreditInfomationRespRestVO();
		creditInfomationRespRestVO.setEligibilityWarningMessageCd("");
		List<Equipment> equipmentQualificationList = new ArrayList<Equipment>();
		Equipment equip1 = new Equipment();
		equip1.setMaxEquipmentNum(1);
		equip1.setProductTypeCd("TTV");
		equipmentQualificationList.add(equip1);
		Equipment equip2 = new Equipment();
		equip2.setMaxEquipmentNum(2);
		equip2.setProductTypeCd("HSIC");
		equipmentQualificationList.add(equip2);
		creditInfomationRespRestVO.setEquipmentQualificationList(equipmentQualificationList);
		creditInfomationRespRestVO.setFraudInd(true);
		creditInfomationRespRestVO.setInTreatementInd(true);
		return creditInfomationRespRestVO;
	}
	
//	@Override
//	protected ServletContext getServletContext() {
//		return servletContext;
//	}
	
	
	
/*

import com.telus.csm.ewlnsc.rest.webservices.EntWlnSalesRestServiceBase;
import com.telus.csm.ewlnsms.rest.domain.CreateAccountRestRequest;
import com.telus.csm.ewlnsms.rest.domain.CreateAccountRestResponse;
import com.telus.csm.ewlnsms.rest.domain.SearchServiceAddressRestResponse;




//
 * @author x145592
 *
//

@Path("/")
public class EnterpriseWlnSalesManagementRestService extends EntWlnSalesRestServiceBase{

	@Context
    ServletContext servletContext;

	private @Autowired EnterpriseWlnSalesManagementRestServiceImpl entWlnSalesManagementRestServiceImpl;


	@POST
	@Path("/customer/wireline-account")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createWLNAccount(@Context UriInfo uriInfo,CreateAccountRestRequest request) {

		CreateAccountRestResponse response = new CreateAccountRestResponse();
		response = entWlnSalesManagementRestServiceImpl.createWLNAccount(uriInfo,request);
		return Response.status(response.getResponseStatus().getStatusCd()).entity(response).build();
				
	}

	@POST
	@Path("/service-address")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response searchServiceAddress(@Context UriInfo uriInfo) {

		SearchServiceAddressRestResponse response = new SearchServiceAddressRestResponse();
		response = entWlnSalesManagementRestServiceImpl.searchServiceAddress(uriInfo);
		return Response.status(response.getResponseStatus().getStatusCd()).entity(response).build();
				
	}

	
	
	
 */
	
	
	
	
	
}
