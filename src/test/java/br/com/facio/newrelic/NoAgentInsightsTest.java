package br.com.facio.newrelic;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author fabiano
 */
public class NoAgentInsightsTest {
    private NoAgentInsights insights = new NoAgentInsights();

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    public NoAgentInsightsTest() {
    }

    @Test
    public void testRecordCustomEvent_eventTypeOK() {
        Map<String, Object> attributes = new HashMap<String,Object>();
        attributes.put("key", "value");
        insights.recordCustomEvent("MyCustom2_Transaction", attributes);
    }
    
    @Test
    public void testRecordCustomEvent_eventTypeInvalidCharacter() {        
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("eventType argument Must match /^[a-zA-Z0-9:_ ]+$/, be non-null, and less than 256 chars");

        insights.recordCustomEvent("MyCustom2_Transaction$%$#@@@", null);
    }

    @Test
    public void testRecordCustomEvent_eventTypeNull() {        
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("eventType cannot be null or empty");

        insights.recordCustomEvent(null, null);
    }

    @Test
    public void testRecordCustomEvent_eventTypeEmpty() {        
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("eventType cannot be null or empty");

        insights.recordCustomEvent("", null);
    }

    @Test
    public void testRecordCustomEvent_eventTypeBlank() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("eventType cannot be null or empty");

        insights.recordCustomEvent("          ", null);
    }

    @Test
    public void testRecordCustomEvent_eventTypeGreaterThan256Chars() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("eventType cannot be null or empty");

        insights.recordCustomEvent("Nomedetransacaomaiorque256caracterespra"
                + "testarseasuitedetestescomonomedessemetodosuperaonumeromaximode256caracteres"
                + "masparecequeemuitodificilcriarumnomedessetamanhoporcontadissonosvamosfazerum"
                + "controlcedepoisumqueridocontrolcpoisaimaginacaopracriarumnomebastantegrandeestaacabando", null);
    }

    @Test
    public void testRecordCustomEvent_attributesIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("attributes cannot be null or empty");

        insights.recordCustomEvent("MyCustom2_Transaction", null);
    }

    @Test
    public void testRecordCustomEvent_attributesIsEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("attributes cannot be null or empty");

        insights.recordCustomEvent("MyCustom2_Transaction", new HashMap<String,Object>());
    }
    
    @Test
    public void testRecordCustomEvent_attributesValuesIsOkWithBooleanNumberString() {
        Map<String, Object> attributes = new HashMap<String,Object>();
        attributes.put("keyWithString", "value");
        attributes.put("keyWithNumber", 666);
        attributes.put("keyWithBoolean", false);
        insights.recordCustomEvent("MyCustom2_Transaction", attributes);
    }
    
    @Test
    public void testRecordCustomEvent_attributesValuesIsNOTBooleanNumberString() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Wrong attributes values. Each key should be a String and each value should be a "
                + "String, Number, or Boolean.");

        Map<String, Object> attributes = createAttributeNotAllowed();
        insights.recordCustomEvent("MyCustom2_Transaction", attributes);
    }

    private Map<String, Object> createAttributeNotAllowed() {
        Map<String, Object> attributes = new HashMap<String,Object>();
        attributes.put("keyWithString", "value");
        attributes.put("keyWithNumber", 666);
        attributes.put("keyWithBoolean", false);
        attributes.put("keyWithObject", this);
        return attributes;
    }
}
