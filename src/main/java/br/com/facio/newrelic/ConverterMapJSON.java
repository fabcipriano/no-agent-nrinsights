package br.com.facio.newrelic;

import com.google.gson.Gson;
import java.util.Map;

/**
 *
 * @author fabiano
 */
public class ConverterMapJSON {
    Gson gson = new Gson();

    public String convert(Map<String, Object> attributes) {
        String json = gson.toJson(attributes);
        return json;
    }
}
