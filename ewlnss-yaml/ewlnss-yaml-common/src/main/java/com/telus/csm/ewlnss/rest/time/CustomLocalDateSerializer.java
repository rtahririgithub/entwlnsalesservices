package com.telus.csm.ewlnss.rest.time;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public final class CustomLocalDateSerializer extends JsonSerializer<LocalDate> {
	

	@Override
	public void serialize(LocalDate dt, JsonGenerator jgen, SerializerProvider provider)
			throws IOException {
		
		jgen.writeString(new SimpleDateFormat(LocalDate.FORMAT_DATE).format(dt.toDate()));
		
	}
}