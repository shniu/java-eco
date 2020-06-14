package io.github.shniu.arts.design.ocp.alert.v2;

public abstract class AlertHandler {
    protected AlertRule alertRule;
    protected Notification notification;

    public AlertHandler(AlertRule alertRule, Notification notification) {
        this.alertRule = alertRule;
        this.notification = notification;
    }

    abstract void check(ApiStatInfo apiStatInfo);
}
