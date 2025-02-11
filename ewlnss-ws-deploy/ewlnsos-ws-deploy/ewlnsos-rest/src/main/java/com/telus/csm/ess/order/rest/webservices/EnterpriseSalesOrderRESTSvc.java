package com.telus.csm.ess.order.rest.webservices;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.telus.csm.ess.rest.domain.CancelShoppingCartErrorResponse;
import com.telus.csm.ess.rest.domain.SubmitSalesErrorResponse;
import com.telus.csm.ess.rest.domain.SubmitSalesRequest;
import com.telus.csm.ess.rest.domain.SubmitSalesResponse;
import com.telus.csm.ess.rest.domain.SubmitSalesToPOSRequest;
import com.telus.csm.ess.rest.domain.UpdateSaleErrorResponse;
import com.telus.csm.ess.rest.domain.UpdateSaleRequest;
import com.telus.csm.ess.rest.domain.UpdateSaleResponse;
import com.telus.csm.ewlnsc.aspect.ProfilingVariance;
import com.telus.csm.ewlnsc.rest.exception.EssRestException;
import com.telus.csm.ewlnsc.rest.webservice.RestSignature;
import com.telus.csm.ewlnsc.rest.webservice.SalesServiceRestBase;

@Path("/")
public class EnterpriseSalesOrderRESTSvc extends SalesServiceRestBase {

	private static final String PATH_PARAM_SHOPPING_CART_ID = "shopping-cart-id";
	private static final String PATH_SHOPPING_CART = "shopping-cart";
	private static final String PATH_SALE = "sale";
	private static final String PATH_PARAM_SALE_ID = "sale-id";
	private static final String PATH_SUBMIT_SALES_TO_POS = "pos-order";

	@Context
    ServletContext servletContext;
	
	@Autowired
	private EnterpriseSalesOrderRESTSvcImpl svcImpl;

	@GET
	@Path("/demo")
	@Produces(MediaType.TEXT_PLAIN)
	public String getServiceDemo() {

		return serviceDemo(servletContext);
	}


	@POST
	@Path("/" + PATH_SALE )
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RestSignature(request=SubmitSalesRequest.class, response=SubmitSalesResponse.class, errorResponse=SubmitSalesErrorResponse.class, systemErrorResponse=SubmitSalesErrorResponse.class)
	public Response submitSales(SubmitSalesRequest submitSalesRequest) {
		int statusCd = Response.Status.OK.getStatusCode();
		Object result;

		try {
			result = svcImpl.submitSales(submitSalesRequest);
		} catch (EssRestException e) {
			result = e.getResponse();
			statusCd = getStatusCode(e);
		}

		return formatRestResponse(result, statusCd);


	}
	
	@PUT
	@Path("/" + PATH_SALE + "/{" + PATH_PARAM_SALE_ID + "}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RestSignature(request=UpdateSaleRequest.class, response=UpdateSaleResponse.class, errorResponse=UpdateSaleErrorResponse.class, systemErrorResponse=UpdateSaleErrorResponse.class)
	public Response updateSale(@PathParam(PATH_PARAM_SALE_ID) String salesId, UpdateSaleRequest request) {
		int statusCd = Response.Status.OK.getStatusCode();
		Object result;

		try {
			result = svcImpl.updateSale(getProfilingVariance(request), salesId, request);
		} catch (EssRestException e) {
			result = e.getResponse();
			statusCd = getStatusCode(e);
		}

		return formatRestResponse(result, statusCd);

	}

	@POST
	@Path("/" + PATH_SUBMIT_SALES_TO_POS )
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RestSignature(request=SubmitSalesToPOSRequest.class, response=SubmitSalesResponse.class, errorResponse=SubmitSalesErrorResponse.class, systemErrorResponse=SubmitSalesErrorResponse.class)
	public Response submitSalesToPOS(SubmitSalesToPOSRequest submitSalesToPOSRequest) {
		int statusCd = Response.Status.OK.getStatusCode();
		Object result;

		try {
			result = svcImpl.submitSalesToPOS(submitSalesToPOSRequest);
		} catch (EssRestException e) {
			result = e.getResponse();
			statusCd = getStatusCode(e);
		}

		return formatRestResponse(result, statusCd);


	}

	@DELETE
	@Path("/" + PATH_SHOPPING_CART + "/{" + PATH_PARAM_SHOPPING_CART_ID + "}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RestSignature(errorResponse=CancelShoppingCartErrorResponse.class, systemErrorResponse=CancelShoppingCartErrorResponse.class)
	public Response cancelSales(@PathParam(PATH_PARAM_SHOPPING_CART_ID) String shoppingCartId,@QueryParam("sales-transaction-id") String transactionId) {

		try {
			svcImpl.cancelSales(shoppingCartId);
		} catch (EssRestException e) {
			Object errorResponse = e.getResponse();
			return formatRestResponse(errorResponse, getStatusCode(e));
		}

		return null;
	}

	private ProfilingVariance getProfilingVariance(UpdateSaleRequest request) {
		if (request != null && request.getFulfillmentAction() != null && request.getFulfillmentAction().getCompleteSaleInd() != null) {
			return new ProfilingVariance("completeSaleInd=" + request.getFulfillmentAction().getCompleteSaleInd());
		}
		return null;
	}

}
