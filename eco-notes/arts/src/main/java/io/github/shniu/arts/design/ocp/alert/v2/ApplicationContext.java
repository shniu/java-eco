package io.github.shniu.arts.design.ocp.alert.v2;

/**
 * 负责 Alert 的创建、组装（alertRule 和 notification 的依赖注入）、初始化（添加 handlers）工作
 */
public class ApplicationContext {
    private AlertRule alertRule;
    private Notification notification;
    private Alert alert;

    // 单例模式实现
    private static final ApplicationContext instance = new ApplicationContext();
    private ApplicationContext() {
        instance.initializeBeans();
    }

    public static ApplicationContext getInstance() {
        return instance;
    }

    private void initializeBeans() {
        alertRule = new AlertRule();
        notification = (emergencyLevel, message) -> {
            // todo
        };
        alert = new Alert();
        alert.addAlertHandler(new TpsAlertHandler(alertRule, notification));
        alert.addAlertHandler(new ErrorAlertHandler(alertRule, notification));
        // 改动3：注册新的 alertHandler 对象
        alert.addAlertHandler(new TimeoutAlertHandler(alertRule, notification));
    }

    public Alert getAlert() {
        return alert;
    }
}
