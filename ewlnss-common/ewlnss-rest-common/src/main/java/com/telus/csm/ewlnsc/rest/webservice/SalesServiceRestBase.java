package com.telus.csm.ewlnsc.rest.webservice;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.telus.csm.ewlnsc.rest.exception.EssRestErrorException;
import com.telus.csm.ewlnsc.rest.exception.EssRestException;
import com.telus.csm.ewlnsc.util.App;
import com.telus.csm.ewlnsc.util.JsonUtil;

public abstract class SalesServiceRestBase extends SpringBeanAutowiringSupport {

	protected Logger logger = Logger.getLogger(getClass());

	protected Response formatRestResponse(Object result, int statusCd) {
		
		if (result instanceof String) {
			return Response.status(statusCd).entity(result).build();
		}
		
		return Response.status(statusCd).entity(JsonUtil.getJsonFromObjectNonNUll(result)).header("Watermark", App.getWatermark()).build();
	}
	
	protected Response formatRestResponseNotEmpty(Object result, int statusCd) {
		
		if (result instanceof String) {
			return Response.status(statusCd).entity(result).build();
		}
		
		return Response.status(statusCd).entity(JsonUtil.getJsonFromObjectNonEmpty(result)).header("Watermark", App.getWatermark()).build();
	}

	protected int getStatusCode(EssRestException e) {
		
		int result = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
		
		if (e instanceof EssRestErrorException) {
			result = Response.Status.BAD_REQUEST.getStatusCode();
		}

		return result;
	}

	protected String serviceDemo(ServletContext servletContext) {
		
		 Class<?> class1 = this.getClass();

		StringBuffer output = new StringBuffer();

		int idx = 0;

		Method[] methods = class1.getMethods();
		for (Method m : methods) {

			if (m.isAnnotationPresent(Path.class)) {
				output.append("\n" + ++idx + ") ");
				if (m.isAnnotationPresent(GET.class)) {
					output.append(GET.class.getSimpleName() + " ");
				}
				if (m.isAnnotationPresent(PUT.class)) {
					output.append(PUT.class.getSimpleName() + " ");
				}
				if (m.isAnnotationPresent(POST.class)) {
					output.append(POST.class.getSimpleName() + " ");
				}
				if (m.isAnnotationPresent(DELETE.class)) {
					output.append(DELETE.class.getSimpleName() + " ");
				}
				if (m.isAnnotationPresent(HEAD.class)) {
					output.append(HEAD.class.getSimpleName() + " ");
				}
				Path ta = m.getAnnotation(Path.class);
				output.append(servletContext.getContextPath() + ta.value());

				Class<?>[] parms = m.getParameterTypes();
				Annotation[][] parmAnnotations = m.getParameterAnnotations();

				int parmIdx = -1;
				String queryLeadingChar = "?";
				for (Annotation[] annotations : parmAnnotations) {
					parmIdx++;
					for (Annotation annotation : annotations) {
						if (annotation instanceof QueryParam) {
							output.append(queryLeadingChar + ((QueryParam) annotation).value() + "={"
									+ parms[parmIdx].getSimpleName() + "}");
							queryLeadingChar = "&";
						}
					}
				}

				if (m.isAnnotationPresent(RestSignature.class)) {
					RestSignature restResponseClass = m.getAnnotation(RestSignature.class);
					Class<?> requestClass = restResponseClass.request();
					if (requestClass != Object.class) {
						output.append(
								"\nRequest: " + requestClass.getName() + "\n" + jsonOfPopulatedObject(requestClass));
					}
					Class<?> responseClass = restResponseClass.response();
					if (responseClass != Object.class) {
						output.append(
								"\nResponse: " + responseClass.getName() + "\n" + jsonOfPopulatedObject(responseClass));
					}
					Class<?> errorResponseClass = restResponseClass.errorResponse();
					if (errorResponseClass != Object.class) {
						output.append(
								"\nError Response: " + errorResponseClass.getName() + "\n" + jsonOfPopulatedObject(errorResponseClass));
					}
					Class<?> systemErrorResponseClass = restResponseClass.systemErrorResponse();
					if (errorResponseClass != Object.class) {
						output.append(
								"\nSystem Error Response: " + systemErrorResponseClass.getName() + "\n" + jsonOfPopulatedObject(systemErrorResponseClass));
					}
				}

				output.append("\n");
			}

		}

		output.append("\n");

		return output.toString();
	}

	private String jsonOfPopulatedObject(Class<?> parm) {

		Object obj = populateObject(parm, new ArrayList<Class<?>>());

		return JsonUtil.getJsonFromObjectNonNUll(obj);
	}

	private Object populateObject(Class<?> parm, ArrayList<Class<?>> classStackParm) {
		// prevent infinite recursion
		if (classStackParm.contains(parm) && (classStackParm.indexOf(parm) != classStackParm.lastIndexOf(parm))) {
			return null;
		}
		
		ArrayList<Class<?>> classStack = new ArrayList<Class<?>>();
		classStack.addAll(classStackParm);
		classStack.add(parm);
		
		Object result = initBasicTypes(parm);

		if (result != null) {
			return result;
		}

		try {
			result = parm.newInstance();
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}

		if (result == null) {
			return result;
		}

		Class<? extends Object> objClass = result.getClass();

		Method[] methods = objClass.getMethods();

		for (Method m : methods) {
			if (m.getName().startsWith("set")) {
				Class<?>[] parms = m.getParameterTypes();
				Class<?> p = parms[0];
				Type[] genericParms = m.getGenericParameterTypes();
				Type gp = genericParms[0];

				Object args;
				if (p.isAssignableFrom(List.class)) {
					ArrayList<Object> listArgs = new ArrayList<Object>();
					args = listArgs;
					if (gp instanceof ParameterizedType) {
						ParameterizedType pt = (ParameterizedType) gp;
						Type[] at = pt.getActualTypeArguments();
						Object x = populateObject((Class<?>) at[0], classStack);
						listArgs.add(x);
					}

				} else {
					args = populateObject(p, classStack);
				}

				if (args != null) {
					try {
						m.invoke(result, args);
					} catch (IllegalArgumentException e) {
					} catch (IllegalAccessException e) {
					} catch (InvocationTargetException e) {
					}
				}

			}
		}

		return result;
	}

	private Object initBasicTypes(Class<?> parm) {

		if (parm == String.class) {
			return "StringValue";
		}

		if (parm == Boolean.class) {
			return Boolean.TRUE;
		}
		if (parm == Character.class) {
			return Character.MAX_VALUE;
		}
		if (parm == Byte.class) {
			return Byte.MAX_VALUE;
		}
		if (parm == Short.class) {
			return Short.MAX_VALUE;
		}
		if (parm == Integer.class) {
			return Integer.MAX_VALUE;
		}
		if (parm == Long.class) {
			return Long.MAX_VALUE;
		}
		if (parm == Float.class) {
			return Float.MAX_VALUE;
		}
		if (parm == Double.class) {
			return Double.MAX_VALUE;
		}
		if (parm == BigDecimal.class) {
			return BigDecimal.valueOf(Long.MAX_VALUE);
		}
		if (parm == Date.class) {
			return new Date();
		}
		
		return null;
	}


}
