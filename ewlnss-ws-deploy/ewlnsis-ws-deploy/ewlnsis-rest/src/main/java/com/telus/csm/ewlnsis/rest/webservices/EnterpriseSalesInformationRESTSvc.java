package com.telus.csm.ewlnsis.rest.webservices;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.telus.csm.ewlnsc.helper.MockServiceHelper;
import com.telus.csm.ewlnsc.rest.exception.EssRestException;
import com.telus.csm.ewlnsc.rest.webservice.RestSignature;
import com.telus.csm.ewlnsc.rest.webservice.SalesServiceRestBase;
import com.telus.csm.ewlnsis.rest.domain.AvailableProductItemResponse;
import com.telus.csm.ewlnsis.rest.domain.GetAvailableAppointmentErrorResponse;
import com.telus.csm.ewlnsis.rest.domain.GetAvailableAppointmentRequest;
import com.telus.csm.ewlnsis.rest.domain.GetAvailableAppointmentResponse;
import com.telus.csm.ewlnsis.rest.domain.GetAvailableProductItemErrorResponse;

@Path("/")
public class EnterpriseSalesInformationRESTSvc extends SalesServiceRestBase {

	@Context
    ServletContext servletContext;

	@Autowired
	private EnterpriseSalesInformationRESTSvcImpl svcImpl;

	private static Logger logger = Logger.getLogger(EnterpriseSalesInformationRESTSvc.class);

	@GET
	@Path("/demo")
	@Produces(MediaType.TEXT_PLAIN)
	public String getServiceDemo() {

		return serviceDemo(servletContext);
		
	}

	@GET
	@Path("/available-product-items")
	@Produces(MediaType.APPLICATION_JSON)
	@RestSignature(response=AvailableProductItemResponse.class, errorResponse=GetAvailableProductItemErrorResponse.class, systemErrorResponse=GetAvailableProductItemErrorResponse.class)
	public Response availableProductItem(@QueryParam("sales-transaction-id") String transactionId, @QueryParam("shopping-cart-id") String shoppingCartId, @QueryParam("commerce-cartitem-id") String commerceCartitemId) {
		
		/**********************************************************************************/
		/* call mock service if it is enable and salesTransactionId between 0001 and 0006 */
		/**********************************************************************************/
		if (MockServiceHelper.inRange(transactionId)){ 
			
			if (MockServiceHelper.enableMock()){	
				String body = "?sales-transaction-id=" + transactionId + "&shopping-cart-id=" + shoppingCartId;
				Map<String, String> mockResponse = MockServiceHelper.get("available-product-item", body);
				return formatRestResponse(mockResponse.get("response"), Integer.parseInt(mockResponse.get("status")));
			}
			
		}

		int statusCd = Response.Status.OK.getStatusCode();
		Object result;

		try {
			result = svcImpl.availableProductItem(transactionId, shoppingCartId, commerceCartitemId);
		} catch (EssRestException e) {
			result = e.getResponse();
			statusCd = getStatusCode(e);
		}

		return formatRestResponse(result, statusCd);
	}

//	@GET
//	@Path("/available-product-items-v2")
//	@Produces(MediaType.APPLICATION_JSON)
//	@RestSignature(response=AvailableProductItemResponse.class, errorResponse=GetAvailableProductItemErrorResponse.class, systemErrorResponse=GetAvailableProductItemErrorResponse.class)
//	public Response availableProductItem(@QueryParam("sales-transaction-id") String transactionId, @QueryParam("shopping-cart-id") String shoppingCartId, @QueryParam("commerce-cartitem-id") String commerceCartitemId) {
//		
//		/**********************************************************************************/
//		/* call mock service if it is enable and salesTransactionId between 0001 and 0006 */
//		/**********************************************************************************/
//		if (MockServiceHelper.inRange(transactionId)){ 
//			
//			if (MockServiceHelper.enableMock()){	
//				String body = "?sales-transaction-id=" + transactionId + "&shopping-cart-id=" + shoppingCartId;
//				Map<String, String> mockResponse = MockServiceHelper.get("available-product-item", body);
//				return formatRestResponse(mockResponse.get("response"), Integer.parseInt(mockResponse.get("status")));
//			}
//			
//		}
//
//		int statusCd = Response.Status.OK.getStatusCode();
//		Object result;
//
//		try {
//			result = svcImpl.availableProductItem(transactionId, shoppingCartId, commerceCartitemId);
//		} catch (EssRestException e) {
//			result = e.getResponse();
//			statusCd = getStatusCode(e);
//		}
//
//		return formatRestResponse(result, statusCd);
//	}
	
	@POST
	@Path("/available-appointments")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RestSignature(request=GetAvailableAppointmentRequest.class, response=GetAvailableAppointmentResponse.class, errorResponse=GetAvailableAppointmentErrorResponse.class, systemErrorResponse=GetAvailableAppointmentErrorResponse.class)
	public Response getAvailableInstallDetail(GetAvailableAppointmentRequest request) {
		int statusCd = Response.Status.OK.getStatusCode();
		Object result = null;

		try {
			result = svcImpl.getAvailableInstallDetail(request);
		} catch (EssRestException e) {
			result = e.getResponse();
			statusCd = getStatusCode(e);
		}

		return formatRestResponse(result, statusCd);
	}
	
}
