package io.github.shniu.arts.design.ocp.alert.v2;

/**
 * 运行 Alert 系统
 */
public class Main {
    public static void main(String[] args) {
        ApiStatInfo apiStatInfo = new ApiStatInfo();
        // ... set apiStatInfo
        // 改动4：添加 timeoutCount 的值
        apiStatInfo.setTimeoutCount(20);
        ApplicationContext.getInstance().getAlert().check(apiStatInfo);
    }
}
