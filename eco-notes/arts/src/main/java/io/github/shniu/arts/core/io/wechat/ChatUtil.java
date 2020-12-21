package io.github.shniu.arts.core.io.wechat;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author niushaohan
 * @date 2020/12/18 17
 */
public class ChatUtil {

    public static final String welcomeBuf = "Welcome to WeChat!\n";

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
