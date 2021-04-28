package io.github.shniu.corejava.io.chatexample;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author niushaohan
 * @date 2020/12/20 21
 */
public class Closer {
    public static void close(Closeable... closeables) {
        try {
            for (Closeable closeable : closeables)
                closeable.close();
        } catch (IOException e) {
            //
        }
    }
}
