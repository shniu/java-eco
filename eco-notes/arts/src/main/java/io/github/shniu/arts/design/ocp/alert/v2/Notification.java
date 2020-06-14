package io.github.shniu.arts.design.ocp.alert.v2;

public interface Notification {
    void notify(NotificationEmergencyLevel emergencyLevel, String message);
}
