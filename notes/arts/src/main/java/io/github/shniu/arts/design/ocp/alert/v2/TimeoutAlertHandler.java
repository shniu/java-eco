package io.github.shniu.arts.design.ocp.alert.v2;

// 改动2: 增加 TimeoutAlertHandler 的实现
public class TimeoutAlertHandler extends AlertHandler {
    public TimeoutAlertHandler(AlertRule alertRule, Notification notification) {
        super(alertRule, notification);
    }

    @Override
    void check(ApiStatInfo apiStatInfo) {
        long timeoutTps = apiStatInfo.getTimeoutCount() / apiStatInfo.getDurationOfSeconds();
        if (timeoutTps > alertRule.getMatchedRule(apiStatInfo.getApi()).getMaxTimeoutTps()) {
            notification.notify(NotificationEmergencyLevel.URGENCY, "Overflow timeout");
        }
    }
}
