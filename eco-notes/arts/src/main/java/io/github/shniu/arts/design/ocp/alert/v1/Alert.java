package io.github.shniu.arts.design.ocp.alert.v1;

/**
 * 新功能：
 * 在此基础上，添加一个功能，当每秒钟接口超时请求个数，超过某个预先设置的最大阈值时，我们也要触发告警发送通知。
 */
public class Alert {
    private AlertRule rule;
    private Notification notification;

    public Alert(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    public void check(String api, long requestCount, long errorCount,
                      long timeoutCount,  // 改动1：添加新的参数 超时次数 timeoutCount
                      long durationOfSeconds) {
        long tps = requestCount / durationOfSeconds;
        if (tps > rule.getMatchedRule(api).getMaxTps()) {
            notification.notify(NotificationEmergencyLevel.URGENCY, "Overflow TPS");
        }

        if (errorCount > rule.getMatchedRule(api).getMaxErrorCount()) {
            notification.notify(NotificationEmergencyLevel.SEVERE, "Overflow Request Error Count");
        }

        // 改动2：添加当每秒钟接口超时请求个数，超过某个预先设置的最大阈值时，触发告警发送通知
        long timeoutTps = timeoutCount / durationOfSeconds;
        if (timeoutTps > rule.getMatchedRule(api).getMaxTimeoutTps()) { // 改动3：添加 getMaxTimeoutTps 函数
            notification.notify(NotificationEmergencyLevel.URGENCY, "Overflow timeout");
        }
    }
}
