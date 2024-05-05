package com.onbank.users.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class UtilMapper {
    public static final String RESPONSE = "{\n" +
            "    \"id\": 1,\n" +
            "    \"username\": \"the_dev\",\n" +
            "    \"password\": \"mypassword123\",\n" +
            "    \"email\": \"thedev@example.com\",\n" +
            "    \"firstName\": \"Elliot\",\n" +
            "    \"lastName\": \"Vhradim\",\n" +
            "    \"createdAt\": \"2024-05-04T19:00:46\",\n" +
            "    \"updatedAt\": \"2024-05-04T19:00:46\",\n" +
            "    \"enabled\": true\n" +
            "}";
    private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    public static String REQUEST = "{\n" +
            "    \"username\": \"the_dev_01\",\n" +
            "    \"password\": \"mypassword123\",\n" +
            "    \"email\": \"thedev@example.com\",\n" +
            "    \"firstName\": \"Elliot\",\n" +
            "    \"lastName\": \"Vhradim\",\n" +
            "    \"enabled\": true\n" +
            "}";

    public static <T> T fromJsonString(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error convirtiendo JSON a objeto", e);
        }
    }

}