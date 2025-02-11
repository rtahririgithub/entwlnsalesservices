package com.telus.csm.ewlnsms.rest.webservices;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
import com.telus.csm.ewlnsms.rest.domain.BookAppointmentErrorResponse;
import com.telus.csm.ewlnsms.rest.domain.BookAppointmentRequest;
import com.telus.csm.ewlnsms.rest.domain.BookAppointmentResponse;
import com.telus.csm.ewlnsms.rest.domain.GetPriceErrorResponse;
import com.telus.csm.ewlnsms.rest.domain.PriceResponse;


@Path("/")
public class EnterpriseSalesManagementRESTSvc extends SalesServiceRestBase {
	
	private static final String PATH_PARAM_SHOPPING_CART_ID = "shopping-cart-id";

	@Context
    ServletContext servletContext;

	@Autowired
	private EnterpriseSalesManagementRESTSvcImpl svcImpl;

	private static Logger logger = Logger.getLogger(EnterpriseSalesManagementRESTSvc.class);

	@GET
	@Path("/demo")
	@Produces(MediaType.TEXT_PLAIN)
	public String getServiceDemo() {

		return serviceDemo(servletContext);
		
	}


	@GET
	@Path("/shopping-cart/{" + PATH_PARAM_SHOPPING_CART_ID + "}/price")
	@Produces(MediaType.APPLICATION_JSON)
	@RestSignature(response=PriceResponse.class, errorResponse=GetPriceErrorResponse.class, systemErrorResponse=GetPriceErrorResponse.class)
	public Response price(@PathParam(PATH_PARAM_SHOPPING_CART_ID) String shoppingCartId, @QueryParam("sales-transaction-id") String transactionId) {

		/**********************************************************************************/
		/* call mock service if it is enable and salesTransactionId between 0001 and 0006 */
		/**********************************************************************************/
		String action = "shopping-cart_price";
		if (MockServiceHelper.inRange(shoppingCartId)){
			
			if (MockServiceHelper.enableMock()){
				
				String body = "/" + shoppingCartId+"/price?sales-transaction-id=" + transactionId;
				Map<String, String> mockResponse = MockServiceHelper.get(action, body);
				return formatRestResponse(mockResponse.get("response"), Integer.parseInt(mockResponse.get("status")));
			}
			
		}
		
		
		int statusCd = Response.Status.OK.getStatusCode();
		Object result;

		try {
			result = svcImpl.price(shoppingCartId, transactionId);
		} catch (EssRestException e) {
			result = e.getResponse();
			statusCd = getStatusCode(e);
		}

		return formatRestResponse(result, statusCd);
	}
	
	@POST
	@Path("/book-appointment")
	@Produces(MediaType.APPLICATION_JSON)
	@RestSignature(request=BookAppointmentRequest.class,response=BookAppointmentResponse.class,errorResponse=BookAppointmentErrorResponse.class,systemErrorResponse=BookAppointmentResponse.class)
	public Response bookAppointment(BookAppointmentRequest bookAppointmentRequest){
		
		int statusCd = Response.Status.OK.getStatusCode();
		
		Object result;
		
		try{
			result = svcImpl.bookAppointment(bookAppointmentRequest);
		}catch(EssRestException e){
			result = e.getResponse();
			statusCd = getStatusCode(e);
		}
		
		return formatRestResponse(result, statusCd);
		
	}
	

}
