package io.github.shniu.flashchat.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author niushaohan
 * @date 2020/12/25 18
 */
public class Jsons {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static String writeValueAsString(Object o) {
        try {
            return getObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
