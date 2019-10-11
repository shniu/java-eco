package io.github.shniu.arts;

public class Ipv4Util {

    public static int ipToInt(String ip) {

        // ip 转成 字符串数组
        String[] ips = ip.split("\\.");

        // 每个部分转成字节
        byte[] ret = new byte[4];
        for (int i = 0; i < ips.length; i++) {
            ret[i] = (byte) (Integer.parseInt(ips[i]) & 0xFF);
        }

        return 0;
    }
}
