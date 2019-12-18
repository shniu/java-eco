package io.github.shniu.arts.design.ocp.alert.v2;

public class ErrorAlertHandler extends AlertHandler {
    public ErrorAlertHandler(AlertRule alertRule, Notification notification) {
        super(alertRule, notification);
    }

    @Override
    void check(ApiStatInfo apiStatInfo) {
        if (apiStatInfo.getErrorCount() > alertRule.getMatchedRule(apiStatInfo.getApi()).getMaxErrorCount()) {
            notification.notify(NotificationEmergencyLevel.SEVERE, "");
        }
    }
}
