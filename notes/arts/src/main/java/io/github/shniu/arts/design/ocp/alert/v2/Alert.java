package io.github.shniu.arts.design.ocp.alert.v2;

import java.util.ArrayList;
import java.util.List;

public class Alert {
    private List<AlertHandler> handlers = new ArrayList<>();

    public void addAlertHandler(AlertHandler handler) {
        handlers.add(handler);
    }

    public void check(ApiStatInfo apiStatInfo) {
        for (AlertHandler handler :
                handlers) {
            handler.check(apiStatInfo);
        }
    }
}
