package io.github.shniu.arts.design.lod.spider;

/**
 * 网络传输接口，职责是发送请求并收到响应，封装了网络IO
 */
public class NetworkTranspoter {

    // 发送请求，这个功能需要非常通用
    // 如果设计成依赖具体的类就会有些问题，如
    //    public byte[] send(HtmlRequest htmlRequest) {...}
    public byte[] send(String address, byte[] data) {
        // Todo
        return null;
    }

    // 设计成如下这样，我理解也是可以的
    public byte[] send(Request request) {
        // Todo
        return null;
    }
}

// 需要发送请求的类实现 Request 接口
interface Request {
    String getAddress();
    byte[] getData();
}
