package com.telus.csm.ewlnssproxy.rest.webservices;

import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.telus.channelsalesmgmt.common.aspect.AspectWeaver;
import com.telus.channelsalesmgmt.common.aspect.aspect.Aspect;
import com.telus.channelsalesmgmt.common.aspect.aspect.FlatProfilingAspect;
import com.telus.csm.ewlnsc.rest.exception.EssRestErrorException;
import com.telus.csm.ewlnsc.rest.exception.EssRestException;
import com.telus.csm.ewlnsc.rest.exception.EssRestSystemErrorException;
import com.telus.csm.ewlnsc.rest.webservice.SalesServiceRestBase;
import com.telus.csm.ewlnss.rest.time.DateTime;
import com.telus.csm.ewlnssproxy.ccb.domain.ConsumerCreditEligibility;
import com.telus.csm.ewlnssproxy.cpib.domain.Error;
import com.telus.csm.ewlnssproxy.cpib.domain.ErrorCharacteristic;
import com.telus.csm.ewlnssproxy.cpib.domain.Message;
import com.telus.csm.ewlnssproxy.cpqb.domain.ProductQualification;
import com.telus.csm.ewlnssproxy.rest.operation.BaseOperation;
import com.telus.csm.ewlnssproxy.rest.transformer.BaseTransformer;


@Path("/")
public class EWLNSSProxyService  extends SalesServiceRestBase{
	
	@Context

    ServletContext servletContext;	
	private static final String RESIDENTIAL = "R";

	/** The default aspects. */
	private static Aspect[] keyServiceProfileAspects = null;
	private static Logger logger = Logger.getLogger(EWLNSSProxyService.class);
	
	static {
		// profile aspects
		Aspect flatProfilingAspect = new FlatProfilingAspect();
		keyServiceProfileAspects = new Aspect[1];
		keyServiceProfileAspects[0] = flatProfilingAspect;
	}
	
    private IEWLNSSProxyService getEssProxyServiceImpl() {
		
		IEWLNSSProxyService result = new EWLNSSProxyServiceImpl();
		
		try {
			result = (IEWLNSSProxyService) AspectWeaver.weave(result, keyServiceProfileAspects);
		} catch (Exception e) {
			logger.error("getSalesInformationServiceImpl: " +
					"Could not weave SalesInformationServiceImpl",
					e);
		}
		return result;
	}	
	
	@GET
	@Path("/demo")
	@Produces(MediaType.TEXT_PLAIN)
	public String getServiceDemo() {

		return serviceDemo(servletContext);
		
	}
	
	@GET
	@Path("/service-address/{serviceAddressId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getServiceAddressDetails(
			@HeaderParam("transactionId") String transactionId,
			@PathParam("serviceAddressId") String serviceAddressId,
			@QueryParam("provinceCd") String provinceCd ) {

		Object result = null;
		int statusCd = Response.Status.OK.getStatusCode();
		
		try {
			result = getEssProxyServiceImpl().getServiceAddressDetails(transactionId, serviceAddressId, provinceCd);
			
		} catch (EssRestException e) {
			result = e.getResponse();
			statusCd = getStatusCode(e);
		}
		
		return formatRestResponseNotEmpty(result, statusCd);
		
	}
	
	@GET
	@Path("/service-address/forborne-status")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getForborneStatus(
			@HeaderParam("transactionId") String transactionId,
			@QueryParam("customerType") String customerType,
			@QueryParam("npaNxxList") String npaNxxList) {
		
		int statusCd = Response.Status.OK.getStatusCode();
		Object result = null;
		if (StringUtils.isEmpty(customerType)) {
			customerType = RESIDENTIAL;
		}
		try {
			result = getEssProxyServiceImpl().getForborneStatus(transactionId, customerType, Arrays.asList(npaNxxList.split(",")));
			if (result == null) {
				statusCd = Response.Status.NOT_FOUND.getStatusCode();				
				com.telus.csm.ewlnssproxy.cpdsb.domain.Error err = new com.telus.csm.ewlnssproxy.cpdsb.domain.Error();
				err.setCode(new Integer(Response.Status.NOT_FOUND.getStatusCode()).toString()); 
				err.setReason("forborne status service is not found !"); 
				err.setStatus("ERROR");
				err.setTraceId(transactionId); 
				result = err;
			}
		} catch (EssRestException e) {
			result = e.getResponse();
			statusCd = getStatusCode(e);
		}
		return formatRestResponse(result, statusCd);
	}
	
	//@Path("/customer/{customerId}{account:(/account/[^/]+?)?}")
	@GET
	@Path("/getAssignedAndPendingProducts/customer/{customerId}{account:(/account/[^/]+?)?}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAssignedAndPendingProducts(
			@HeaderParam("transactionId") String transactionId,
			@PathParam("customerId") String customerId,
			@PathParam("account") String accountId,
			@QueryParam("originatorApplicationId") String originatorApplicationId,
			@QueryParam("brandId") String brandId,
			@QueryParam("correlationId") String correlationId,
			@QueryParam("isRefreshInd") String isRefreshInd,
			@QueryParam("salesRepId") String salesRepId,
			@QueryParam("salesRepAssociatedChannelOutletId") String salesRepAssociatedChannelOutletId) {
		
		int statusCd = Response.Status.OK.getStatusCode();
		Object result = null;
		String realAccountId = null;
		if (accountId != null && accountId.split("/").length > 1) {
			realAccountId = accountId.split("/")[2];
		}
		try {
			result = getEssProxyServiceImpl().getAssignedAndPendingProducts(transactionId, customerId, realAccountId, originatorApplicationId, brandId,
						correlationId, salesRepId, salesRepAssociatedChannelOutletId);
			
			if (result == null) {
				statusCd = Response.Status.NOT_FOUND.getStatusCode();				
				com.telus.csm.ewlnssproxy.cpdsb.domain.Error err = new com.telus.csm.ewlnssproxy.cpdsb.domain.Error();
				err.setCode(new Integer(Response.Status.NOT_FOUND.getStatusCode()).toString()); 
				err.setReason("assigned and pending product service is not found !"); 
				err.setStatus("ERROR");
				err.setTraceId(transactionId); 
				result = err;
			}
			
		} catch (EssRestException e) {
			result = e.getResponse();
			statusCd = getStatusCode(e);
		}
		return formatRestResponse(result, statusCd);
	}
	
	@GET
	@Path("/getAssignedAndPendingProductsConsolidated/customer/{customerId}{account:(/account/[^/]+?)?}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAssignedAndPendingProductsConsolidated(
			@HeaderParam("transactionId") String transactionId,
			@PathParam("customerId") String customerId,
			@PathParam("account") String accountId,
			@QueryParam("originatorApplicationId") String originatorApplicationId,
			@QueryParam("brandId") String brandId,
			@QueryParam("correlationId") String correlationId,
			@QueryParam("isRefreshInd") String isRefreshInd,
			@QueryParam("salesRepId") String salesRepId,
			@QueryParam("salesRepAssociatedChannelOutletId") String salesRepAssociatedChannelOutletId) {
		
		int statusCd = Response.Status.OK.getStatusCode();
		String realAccountId = null;
		if (accountId != null && accountId.split("/").length > 1) {
			realAccountId = accountId.split("/")[2];
		}
		Object result = null;
		try {
			result = getEssProxyServiceImpl().getAssignedAndPendingProductsConsolidated(transactionId, customerId, realAccountId, originatorApplicationId, brandId,
						correlationId, isRefreshInd, salesRepId, salesRepAssociatedChannelOutletId);
			
			if (result == null) {
				statusCd = Response.Status.NOT_FOUND.getStatusCode();				
				com.telus.csm.ewlnssproxy.cpdsb.domain.Error err = new com.telus.csm.ewlnssproxy.cpdsb.domain.Error();
				err.setCode(new Integer(Response.Status.NOT_FOUND.getStatusCode()).toString()); 
				err.setReason("assigned and pending product service is not found !"); 
				err.setStatus("ERROR");
				err.setTraceId(transactionId); 
				result = err;
			}
			
		} catch (EssRestException e) {
			result = e.getResponse();
			statusCd = getStatusCode(e);
		}
		return formatRestResponse(result, statusCd);
	}
	
	
	@GET
	@Path("/productInventory/application/{applicationId}/customer/{customerId}/gift-eligibility")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAvailableBoltOnService(
			@HeaderParam("transactionId") String transactionId,
			@PathParam("applicationId") String applicationId,
			@PathParam("customerId") String customerId,
			@QueryParam("roleId") String roleId
			) {
		int statusCd = Response.Status.OK.getStatusCode();
		Object result = null;

		try {
			
			result = getEssProxyServiceImpl().getAvailableBoltOnService(transactionId, applicationId, customerId, roleId);
			
			if(result==null){
				statusCd = Response.Status.NOT_FOUND.getStatusCode();
				
				com.telus.csm.ewlnssproxy.cpib.domain.Error err = new com.telus.csm.ewlnssproxy.cpib.domain.Error();
				err.setCode(new Integer(Response.Status.NOT_FOUND.getStatusCode()).toString()); 
				err.setReason("required services not found !"); 
				err.setStatus("ERROR");
				err.setTraceId(transactionId); 
				result = err;
			}
		} catch (EssRestException e) {
			result = e.getResponse();
			statusCd = getStatusCode(e);
		}

		return formatRestResponse(result, statusCd);
	}
	
	@GET
	@Path("/application/{applicationId}/product-qualification")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAvailableProducts(@HeaderParam("transactionId") String transactionId,
			@PathParam("applicationId") String applicationId, @QueryParam("customerId") String customerId,
			@QueryParam("accountId") String accountId, @QueryParam("addressId") String addressId,
			@QueryParam("provinceCd") String provinceCd, @QueryParam("city") String city,
			@QueryParam("qualByServiceId") String qualByServiceId, @QueryParam("correlationId") String correlationId,
			@QueryParam("salesRepId") String salesRepId, @QueryParam("channelOutletId") String channelOutletId, 
			@QueryParam("isRefreshInd") String isRefreshInd) { // QC79739 added refreshIndicator 

		logger.info("Received call getAvailableProducts");

		try {
			// Call operation to process request
			List<ProductQualification> rsp = getEssProxyServiceImpl().getAvailableProducts(transactionId, applicationId,
					customerId, accountId, addressId, provinceCd, city, qualByServiceId, correlationId, salesRepId,
					channelOutletId, isRefreshInd);

			return formatRestResponse(rsp, Response.Status.OK.getStatusCode());

		} catch (EssRestErrorException e) {
			logger.error("EWLNSSProxyService.getAvailableProducts rest error", e);
			Error error = (Error) e.getResponse();
			return formatRestResponse(error, Integer.valueOf(error.getCode()));
		} catch (EssRestSystemErrorException e) {
			logger.error("EWLNSSProxyService.getAvailableProducts rest system error", e);
			Error error = (Error) e.getResponse();
			return formatRestResponse(error, Integer.valueOf(error.getCode()));
		} catch (Exception e) {
			logger.error("EWLNSSProxyService.getAvailableProducts system error", e);
			return handleProxyError(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage(), null, transactionId,
					BaseTransformer.getResponseMessageFromException(e), null);
		}
	}
	
	@GET
	@Path("/customer/{customerId}/creditProfile")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getCustomerCreditProfile(@HeaderParam("transactionId") String transactionId, 
			@PathParam("customerId") String customerId,
			@QueryParam("refreshcacheInd") String refreshcacheInd,
			@QueryParam("userId") String userId,
			@QueryParam("userTypeCode") String userTypeCode,
			@QueryParam("salesRepresentativeId") String salesRepresentativeId,
			@QueryParam("channelOrganizationId") String channelOrganizationId,
			@QueryParam("outletId") String outletId,
			@QueryParam("originatorApplicationId") String originatorApplicationId,
			@QueryParam("correlationId") String correlationId,
			@QueryParam("timestamp") DateTime timestamp){
		
		logger.info("Received call getCreditProfile");
	
		try {
		// Call operation to process request
					String rsp = getEssProxyServiceImpl().getCustomerCreditProfile(transactionId, customerId, 
					refreshcacheInd, userId, userTypeCode, salesRepresentativeId, channelOrganizationId, outletId,
					originatorApplicationId, correlationId, timestamp);

			return formatRestResponse(rsp, Response.Status.OK.getStatusCode());

		} catch (EssRestErrorException e) {
			logger.error("EWLNSSProxyService.getCustomerCreditProfile rest error", e);
			Error error = (Error) e.getResponse();
			return formatRestResponse(error, Integer.valueOf(error.getCode()));
		} catch (EssRestSystemErrorException e) {
			logger.error("EWLNSSProxyService.getCustomerCreditProfile rest system error", e);
			Error error = (Error) e.getResponse();
			return formatRestResponse(error, Integer.valueOf(error.getCode()));
		} catch (Exception e) {
			logger.error("EWLNSSProxyService.getCustomerCreditProfile system error", e);
			return handleProxyError(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage(), null, transactionId,
					BaseTransformer.getResponseMessageFromException(e), null);
		}
	}
	
	@GET
	@Path("/customer/{customerId}/credit-eligibility")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomerCreditEligibility(
			@HeaderParam("transactionId") String transactionId,
			@PathParam("customerId") String customerId,
			@QueryParam("refreshcacheInd") String refreshcacheInd,
			@QueryParam("userId") String userId,
			@QueryParam("userTypeCode") String userTypeCode,
			@QueryParam("salesRepresentativeId") String salesRepresentativeId,
			@QueryParam("channelOrganizationId") String channelOrganizationId,
			@QueryParam("outletId") String outletId,
			@QueryParam("originatorApplicationId") String originatorApplicationId,
			@QueryParam("correlationId") String correlationId,
			@QueryParam("timestamp") DateTime timestamp ) {

		logger.info("Rest Get getCustomerCreditEligibility");
		
		try {
			ConsumerCreditEligibility rsp = getEssProxyServiceImpl().getCustomerCreditEligibility(transactionId,
					customerId, refreshcacheInd, userId, userTypeCode, salesRepresentativeId, channelOrganizationId,
					outletId, originatorApplicationId, correlationId, timestamp);
			
			return formatRestResponse(rsp, Response.Status.OK.getStatusCode());
		} catch (EssRestException e) {
			return formatRestResponse(e.getResponse(), getStatusCode(e));
		} finally {
			logger.info("END getCustomerCreditEligibility");
		}
		
	}
	
	
	@GET
	@Path("/referencepds-value/application/{applicationId}/entity")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getReferencePDSMap(
			@HeaderParam("transactionId") String transactionId,
			@PathParam("applicationId") String applicationId,
			@QueryParam("entityName") String entityName) throws Exception {
		int statusCd = Response.Status.OK.getStatusCode();
		Object result = null;

		try {
			result = getEssProxyServiceImpl().getReferencePDSMap(transactionId,applicationId,entityName);
			
			if(result==null){
				statusCd = Response.Status.NOT_FOUND.getStatusCode();				
				
				com.telus.csm.ewlnssproxy.cpdsb.domain.Error err = new com.telus.csm.ewlnssproxy.cpdsb.domain.Error();
				err.setCode(new Integer(Response.Status.NOT_FOUND.getStatusCode()).toString()); 
				err.setReason("reference pds data not found !"); 
				err.setStatus("ERROR");
				err.setTraceId(transactionId); 
				result = err;
			}
		} catch (EssRestException e) {
			result = e.getResponse();
			statusCd = getStatusCode(e);
		}
		
		return formatRestResponse(result, statusCd);
	}
	
	private Response handleProxyError(Response.Status rspStatus, String reason, String status, String traceId,
			List<Message> messageList, List<ErrorCharacteristic> characteristicList) {

		BaseOperation operation = new BaseOperation();
		
		if ("404 Not Found".equalsIgnoreCase(reason))
			rspStatus = Response.Status.NOT_FOUND;
		
		Error error = operation.handleProxyError(rspStatus, reason, status, traceId, messageList, characteristicList);

		return formatRestResponse(error, rspStatus.getStatusCode());
	}
}
