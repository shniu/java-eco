package io.github.shniu.os.zerocopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author niushaohan
 * @date 2021/1/28 15
 */
public class ZeroCopyClient {

    public static void main(String[] args) {
        ZeroCopyClient zeroCopyClient = new ZeroCopyClient();
        zeroCopyClient.sendFile();
    }

    public void sendFile() {

        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 3334));
            socketChannel.configureBlocking(true);

            File file = new File("/Users/dfg/Downloads/jmap_1_dump_ui.hprof.tar.gz");
            FileChannel fileChannel = new FileInputStream(file).getChannel();

            long start = System.currentTimeMillis();
            long transferBytes = fileChannel.transferTo(0, file.length(), socketChannel);
            long end = System.currentTimeMillis();

            System.out.println("total bytes transfer: " + transferBytes + ", total time: " + (end - start) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
