package it.prova.hellojaxrspersona.utility;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.std.StdDeserializer;

public class DataParseHandler extends  StdDeserializer<Date>{

	
	public DataParseHandler() {
		this(null);
	}
	
	public DataParseHandler(Class<?> vc) {
		super(vc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

		String date= jp.getText();
		try {
			SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
			return dateFormat.parse(date);
		}catch(Exception e ) {
			return null;
		}
	}

}
