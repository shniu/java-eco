package io.github.shniu.dubbo.rpc.framework;

/**
 * @author niushaohan
 * @date 2021/5/13 13
 */
public class URL {
    private String serviceName;
    private String ip;
    private int port;

    public URL(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String toString() {
        return ip + ":" + port;
    }
}
