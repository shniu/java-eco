package io.github.shniu.flashchat.common.net;

import java.io.Closeable;
import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * @author niushaohan
 * @date 2020/12/22 22
 */
public class Closer {

    public static void close(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            try {
                closeable.close();
            } catch (IOException e) {
                // ...
            }
        }
    }

    public static void close(SelectionKey key) {
        close(key.channel());
        key.cancel();
    }
}
