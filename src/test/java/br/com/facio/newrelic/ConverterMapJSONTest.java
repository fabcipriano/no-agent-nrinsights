/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.facio.newrelic;

import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author fabiano
 */
public class ConverterMapJSONTest {
    private static final Logger LOG = LogManager.getLogger();
    private ConverterMapJSON instance = new ConverterMapJSON();
    
    public ConverterMapJSONTest() {
    }

    /**
     * Test of convert method, of class ConverterMapJSON.
     */
    @Test
    public void testConvert_AllAttributesIsValid() {
        Map<String, Object> attributes = createAttributes();

        String result = instance.convert(attributes);
        
        LOG.debug("attributes={}", attributes);
        LOG.debug("After JSON conversion result={}", result);
        String expectedJSON = "{\"keyWithString\":\"value\","
                + "\"keyWithAnotherString\":\"Postmodern Jukebox\",\"keyWithNumber\":666,"
                + "\"keyWithFloat\":10.99,\"keyWithBoolean\":false}";
        
        assertEquals(expectedJSON, result);
    }

    private Map<String, Object> createAttributes() {
        Map<String, Object> attributes = new LinkedHashMap<String,Object>();
        attributes.put("keyWithString", "value");
        attributes.put("keyWithAnotherString", "Postmodern Jukebox");
        attributes.put("keyWithNumber", 666);
        attributes.put("keyWithFloat", 10.99);
        attributes.put("keyWithBoolean", false);
        return attributes;
    }
    
}
