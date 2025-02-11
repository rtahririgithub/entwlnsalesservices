package com.telus.csm.ewlnss.rest.time;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public final class CustomDateTimeSerializer extends JsonSerializer<DateTime> {
	
	@Override
	public void serialize(DateTime dt, JsonGenerator jgen, SerializerProvider provider)
			throws IOException {
		
		jgen.writeString(new SimpleDateFormat(DateTime.FORMAT_DATE_TIME).format(dt.toDate()));
		
	}
}