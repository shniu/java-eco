package io.github.shniu.os.zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author niushaohan
 * @date 2021/1/28 14
 */
public class TraditionalServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(3333));

        while (true) {
            Socket client = serverSocket.accept();
            System.out.println("New client connected: " + client.getInetAddress() + ":" + client.getPort());

            DataInputStream dis = new DataInputStream(client.getInputStream());

            byte[] bytes = new byte[4096];
            while (true) {
                int read = dis.read(bytes, 0, 4096);
                if (read <= 0) {
                    break;
                }

                // System.out.println("read: " + read + ", content: " + new String(bytes, 0, read));
            }

            System.out.println("total read: " + dis.toString());

            client.close();
        }
    }
}
