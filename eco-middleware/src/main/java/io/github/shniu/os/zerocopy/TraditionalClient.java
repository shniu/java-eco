package io.github.shniu.os.zerocopy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author niushaohan
 * @date 2021/1/28 14
 */
public class TraditionalClient {
    public static void main(String[] args) throws IOException {
        // 读取本地文件的数据，然后通过网络发送出去

        final String host = "127.0.0.1";
        final int port = 3333;

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(host, port));

        // 读取数据
        File file = new File("/Users/dfg/Downloads/jmap_1_dump_ui.hprof.tar.gz");
        FileInputStream inputStream = new FileInputStream(file);

        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

        byte[] bytes = new byte[4096];
        int read = 0, total = 0;

        long start = System.currentTimeMillis();
        while ((read = inputStream.read(bytes)) >= 0) {
            total += read;

            outputStream.write(bytes, 0, read);
        }

        outputStream.flush();

        long end = System.currentTimeMillis();
        System.out.println("bytes send: " + total + ", total time: " + (end -start) + "ms");

        outputStream.close();
        inputStream.close();
        socket.close();
    }
}
