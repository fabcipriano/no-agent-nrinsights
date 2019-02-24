package br.com.facio.newrelic;

import java.util.Map;

/**
 *
 * @author fabiano
 */
public class NoAgentInsights {
    
    private static final String PATTERN = "^[a-zA-Z0-9:_ ]+$";
    
    private ConverterMapJSON map2json = new ConverterMapJSON();

    /**
     * 
     * @param eventType Must match /^[a-zA-Z0-9:_ ]+$/, be non-null, and less than 256 chars.
     * @param attributes A map of event data. Each key should be a String and each value should be a String, Number, or Boolean.
     */
    public void recordCustomEvent(String eventType, Map<String, Object> attributes) {
        validateArguments(eventType, attributes);
        
        String json = map2json.convert(attributes);
    }

    void validateArguments(String eventType, Map<String, Object> attributes) throws IllegalArgumentException, RuntimeException {
        validateEventTypeArgument(eventType);
        validateAttributes(attributes);
    }

    private void validateAttributes(Map<String, Object> attributes) throws IllegalArgumentException {
        validateAttributesIsNotEmpty(attributes);
        validateAttributesValuesIsStringOrNumberOrBoolean(attributes);
    }

    private void validateAttributesValuesIsStringOrNumberOrBoolean(Map<String, Object> attributes) {
        attributes.values().stream().forEach(value -> {
            if (!isBooleanOrStringOrNumber(value)) {
                throw new IllegalArgumentException("Wrong attributes values. Each key should be a String and each "
                        + "value should be a String, Number, or Boolean.");
            }
        });
    }

    private void validateAttributesIsNotEmpty(Map<String, Object> attributes) throws IllegalArgumentException {
        if ((attributes == null) || (attributes.size() == 0)){
            throw new IllegalArgumentException("attributes cannot be null or empty");
        }
    }

    private void validateEventTypeArgument(String eventType) throws RuntimeException, IllegalArgumentException {
        validateEventTypeIsNotBlank(eventType);
        validateEventTypeIsNotGreaterThan256Chars(eventType);
        validateEventTypeFormat(eventType);
    }

    private void validateEventTypeIsNotGreaterThan256Chars(String eventType) throws IllegalArgumentException {
        if (eventType.length() > 256) {
            throw new IllegalArgumentException("eventType cannot be null or empty");
        }
    }

    private void validateEventTypeIsNotBlank(String eventType) throws IllegalArgumentException {
        if ((eventType == null) || (eventType.trim().isEmpty())) {
            throw new IllegalArgumentException("eventType cannot be null or empty");
        }
    }

    private void validateEventTypeFormat(String eventType) throws RuntimeException {
        if (!eventType.matches(PATTERN)) {
            throw new IllegalArgumentException("eventType argument Must match /^[a-zA-Z0-9:_ ]+$/, be non-null, and less than "
                    + "256 chars");
        }
    }

    private boolean isBooleanOrStringOrNumber(Object value) {
        return (value instanceof String) || (value instanceof Boolean) || (value instanceof Number);
    }
}
