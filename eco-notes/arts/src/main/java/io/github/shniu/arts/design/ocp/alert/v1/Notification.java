package io.github.shniu.arts.design.ocp.alert.v1;

public interface Notification {
    void notify(NotificationEmergencyLevel emergencyLevel, String message);
}
