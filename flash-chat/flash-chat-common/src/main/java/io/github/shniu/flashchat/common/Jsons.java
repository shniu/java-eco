package io.github.shniu.flashchat.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

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

        return "";
    }

    public static byte[] writeValueAsBytes(Object o) {
        try {
            return getObjectMapper().writeValueAsBytes(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new byte[]{};
    }

    public static <T> T readValue(byte[] bytes, Class<T> cls) {
        try {
            return getObjectMapper().readValue(bytes, cls);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Json read value error.");
        }
    }
}
