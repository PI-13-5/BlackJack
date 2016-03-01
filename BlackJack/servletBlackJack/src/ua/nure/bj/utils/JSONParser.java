package ua.nure.bj.utils;

import java.io.IOException;
import java.util.HashMap;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JSONParser {
	static ObjectMapper mapper = new ObjectMapper();

	public static String parseToJson(HashMap<String, String> responseResult)
			throws JsonGenerationException, JsonMappingException, IOException {
		return mapper.writeValueAsString(responseResult);
	}

}
