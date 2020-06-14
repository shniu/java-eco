package io.github.shniu.ratelimiter.rule.datasource;

import io.github.shniu.ratelimiter.rule.RuleConfig;
import io.github.shniu.ratelimiter.rule.parser.JsonRuleConfigParser;
import io.github.shniu.ratelimiter.rule.parser.RuleConfigParser;
import io.github.shniu.ratelimiter.rule.parser.YamlRuleConfigParser;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author niushaohan
 * @date 2020/6/14 23
 */
@Slf4j
public class FileRuleConfigSource implements RuleConfigSource {

    public static final String API_LIMIT_CONFIG_NAME = "ratelimiter-rule";
    public static final String YAML_EXTENSION = "yaml";
    public static final String YML_EXTENSION = "yml";
    public static final String JSON_EXTENSION = "json";
    private static final String[] SUPPORT_EXTENSIONS = new String[]{YAML_EXTENSION, YML_EXTENSION, JSON_EXTENSION};
    private static final Map<String, RuleConfigParser> PARSER_MAP = new HashMap<>();

    static {
        PARSER_MAP.put(YAML_EXTENSION, new YamlRuleConfigParser());
        PARSER_MAP.put(YML_EXTENSION, new YamlRuleConfigParser());
        PARSER_MAP.put(JSON_EXTENSION, new JsonRuleConfigParser());
    }

    @Override
    public RuleConfig load() {
        for (String extension : SUPPORT_EXTENSIONS) {
            InputStream in = null;

            try {
                in = this.getClass().getResourceAsStream("/" + getFileNameByExt(extension));
                if (in != null) {
                    RuleConfigParser configParser = PARSER_MAP.get(extension);
                    return configParser.parse(in);
                }
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        log.error("close file error", e);
                    }
                }
            }
        }

        return null;
    }

    private String getFileNameByExt(String extension) {
        return API_LIMIT_CONFIG_NAME + "." + extension;
    }
}
