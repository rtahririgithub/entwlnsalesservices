package com.telus.csm.ewlnsc.util;

import java.io.IOException;
import java.io.InputStream;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

public abstract class JsonUtil {
	
	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(JsonUtil.class);
	
	
	
	public static String getJsonFromObject(Object clazz){
		String functionName = "getJsonFromObject";
//		LOGGER.enter(functionName);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "";
		try {
			jsonString= mapper.writeValueAsString(clazz);
//			LOGGER.info(functionName, "Formated object into Json: " + jsonString);
		} catch (JsonGenerationException e) {
			LOGGER.error(null, functionName, "Error has occured when trying to parse Object into JSON: " + e.getMessage(),e);
		} catch (JsonMappingException e) {
			LOGGER.error(null, functionName, "Error has occured when trying to parse Object into JSON: " + e.getMessage(),e);
		} catch (IOException e) {
			LOGGER.error(null, functionName, "Error has occured when trying to parse Object into JSON: " + e.getMessage(),e);
		}
		return jsonString;
	}
	
	public static String getJsonFromObjectNonNUll(Object clazz){
		String functionName = "getJsonFromObject";
//		LOGGER.enter(functionName);
		ObjectMapper mapper = new ObjectMapper();
		mapper.getSerializationConfig().setSerializationInclusion(Inclusion.NON_NULL);
		String jsonString = "";
		try {
			jsonString= mapper.writeValueAsString(clazz);
//			LOGGER.info(functionName, "Formated object into Json: " + jsonString);
		} catch (JsonGenerationException e) {
			LOGGER.error(null, functionName, "Error has occured when trying to parse Object into JSON: " + e.getMessage(),e);
		} catch (JsonMappingException e) {
			LOGGER.error(null, functionName, "Error has occured when trying to parse Object into JSON: " + e.getMessage(),e);
		} catch (IOException e) {
			LOGGER.error(null, functionName, "Error has occured when trying to parse Object into JSON: " + e.getMessage(),e);
		}
		return jsonString;
	}
	
	public static String getJsonFromObjectNonEmpty(Object clazz){
		String functionName = "getJsonFromObject";
//		LOGGER.enter(functionName);
		ObjectMapper mapper = new ObjectMapper();
		mapper.getSerializationConfig().setSerializationInclusion(Inclusion.NON_EMPTY);
		String jsonString = "";
		try {
			jsonString= mapper.writeValueAsString(clazz);
//			LOGGER.info(functionName, "Formated object into Json: " + jsonString);
		} catch (JsonGenerationException e) {
			LOGGER.error(null, functionName, "Error has occured when trying to parse Object into JSON: " + e.getMessage(),e);
		} catch (JsonMappingException e) {
			LOGGER.error(null, functionName, "Error has occured when trying to parse Object into JSON: " + e.getMessage(),e);
		} catch (IOException e) {
			LOGGER.error(null, functionName, "Error has occured when trying to parse Object into JSON: " + e.getMessage(),e);
		}
		return jsonString;
	}

	public static <T> T parseJsonToObject(String jsonString, Class<T> clazz){
		String functionName = "parseJsonToObject";
//		LOGGER.enter(functionName);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		T response = null;
		try {
			response = mapper.readValue(jsonString, clazz);
		} catch (JsonParseException e) {
			LOGGER.error("", functionName, e.getMessage(),e);
		} catch (JsonMappingException e) {
			LOGGER.error("", functionName, e.getMessage(),e);
		} catch (IOException e) {
			LOGGER.error("", functionName, e.getMessage(),e);
		}
//		LOGGER.exit(functionName);
		return response;
	}

	public static <T> T parseJsonToObject( InputStream inputStream, Class<T> clazz){
		String functionName = "parseJsonToObject";
//		LOGGER.enter(functionName);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		T response = null;
		try {
			response = mapper.readValue(inputStream, clazz);
		} catch (JsonParseException e) {
			LOGGER.error("", functionName, e.getMessage(),e);
		} catch (JsonMappingException e) {
			LOGGER.error("", functionName, e.getMessage(),e);
		} catch (IOException e) {
			LOGGER.error("", functionName, e.getMessage(),e);
		}
//		LOGGER.exit(functionName);
		return response;
	}
}
