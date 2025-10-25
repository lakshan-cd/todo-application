package com.lakshancd.todo_application_backend.common.common_utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

/**
 * Utility class providing common methods for various functionalities.
 */
@Component
public class CommonUtils {
    @Autowired
    private ModelMapper modelMapper;

    public CommonUtils(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Default constructor for CommonUtils.
     */
    public CommonUtils() {
    }

    /**
     * Converts an object from one type to another using ModelMapper.
     *
     * @param source          The source object to be converted.
     * @param destinationType The class of the destination type.
     * @param <T>             The type of the destination.
     * @return The converted object of the destination type.
     */
    public <T> T convert(final Object source, final Class<T> destinationType) {

        if (source == null) {
            throw new IllegalArgumentException("Source object cannot be null");
        }
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true); // Ignore null values
        return modelMapper.map(source, destinationType);
    }

    /**
     * Converts a string from underscore-separated to capitalized words.
     *
     * @param type The input string with or without underscores.
     * @return The formatted string with the first letter of each word capitalized.
     * Example: ACCOUNT_CREATED --> Account Created
     */
    public String convertTypeToString(final String type) {
        if (type.contains("_")) {
            String[] array = type.split("_");
            for (int i = 0; i < array.length; i++) {
                array[i] = array[i].substring(0, 1).toUpperCase() + array[i].substring(1).toLowerCase();
            }
            return String.join(" ", array);
        } else {
            return type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase();
        }
    }

    /**
     * Converts an object to its JSON representation.
     *
     * @param entity The object to be converted to JSON.
     * @param <T>    The type of the object.
     * @return The JSON representation of the object.
     */
    public <T> String convertToJson(T entity) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

            String jsonString = objectMapper.writeValueAsString(entity);

            JsonNode rootNode = objectMapper.readTree(jsonString);

            processJsonNode(rootNode, objectMapper);

            return objectMapper.writeValueAsString(rootNode);

        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }

    /**
     * Converts a byte array to a string using the specified character encoding.
     *
     * @param contentAsByteArray The byte array to be converted to a string.
     * @param characterEncoding  The character encoding to be used.
     * @return The string representation of the byte array.
     */
    public String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
        try {
            return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Maps an inner array within a source array to an object of the specified target type.
     *
     * @param sourceArray      The source array containing an inner array.
     * @param targetType       The class of the target type.
     * @param innerArrayIndex  The index of the inner array within the source array.
     * @param <T>              The type of the destination.
     * @return The mapped object of the target type.
     */
    public <T> T mapToObject(Object[] sourceArray, Class<T> targetType, int innerArrayIndex) {
        if (sourceArray == null || sourceArray.length == 0 || innerArrayIndex < 0 || innerArrayIndex >= sourceArray.length) {
            return null;
        }

        Object[] innerArray = (Object[]) sourceArray[innerArrayIndex];
        if (innerArray == null || innerArray.length == 0) {
            return null;
        }

        T dto = instantiateTargetType(targetType);

        mapFields(innerArray, dto);

        return dto;
    }


    /**
     * Instantiates an object of the specified target type.
     *
     * @param targetType The class of the target type.
     * @param <T>        The type of the destination.
     * @return The instantiated object of the target type.
     */
    private <T> T instantiateTargetType(Class<T> targetType) {
        try {
            return targetType.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error instantiating target type", e);
        }
    }

    /**
     * Maps fields from an inner array to the corresponding fields of the target object.
     *
     * @param sourceArray The inner array containing values to be mapped.
     * @param dto         The target object.
     * @param <T>         The type of the destination.
     */
    private <T> void mapFields(Object[] sourceArray, T dto) {
        for (int i = 0; i < sourceArray.length; i++) {
            Object value = sourceArray[i];
            setFieldValue(dto, i, value);
        }
    }

    /**
     * Sets the value of a field in the target object.
     *
     * @param dto   The target object.
     * @param index The index of the field.
     * @param value The value to be set.
     * @param <T>   The type of the destination.
     */
    private <T> void setFieldValue(T dto, int index, Object value) {
        try {
            java.lang.reflect.Field field = dto.getClass().getDeclaredFields()[index];
            field.setAccessible(true);
            field.set(dto, value);
        } catch (Exception e) {
            throw new RuntimeException("Error setting field value", e);
        }
    }

    /**
     *
     * @param rootNode The current JsonNode being processed.
     * @param objectMapper ObjectMapper instance for parsing JSON-like text to a JsonNode
     * @throws Exception If an error occurs during processing and modification of the JsonNode
     */
    private void processJsonNode(JsonNode rootNode, ObjectMapper objectMapper) throws Exception {
        if (rootNode.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                JsonNode valueNode = entry.getValue();
                if ("maskedRequestBody".equals(entry.getKey()) || "maskedResponseBody".equals(entry.getKey())) {
                    if (valueNode.asText().startsWith("{") || valueNode.asText().startsWith("[")) {
                        try {
                            JsonNode parsedNode = objectMapper.readTree(valueNode.asText());
                            ((ObjectNode) rootNode).set(entry.getKey(), parsedNode);
                        } catch (Exception ex) {
                            ((ObjectNode) rootNode).put(entry.getKey(), valueNode.asText());
                        }
                    }
                    else {
                        ((ObjectNode) rootNode).put(entry.getKey(), valueNode.asText());
                    }
                } else {
                    processJsonNode(valueNode, objectMapper);
                }
            }
        } else if (rootNode.isArray()) {
            for (JsonNode arrayElement : rootNode) {
                processJsonNode(arrayElement, objectMapper);
            }
        }
    }
}
