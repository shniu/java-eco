package io.github.shniu.arts.design.isp.config;

import java.util.Map;

public interface Viewer {
    String outputInPlainText();

    Map<String, String> output();
}
