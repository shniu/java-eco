package io.github.shniu.corejava.os;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author niushaohan
 * @date 2021/3/19 09
 */
public class FileChannelSample1 {

    public static void main(String[] args) throws IOException {
        FileChannel fileChannel = new RandomAccessFile("/tmp/os/file1.data", "rw").getChannel();

        // read from file
        ByteBuffer buffer = ByteBuffer.allocateDirect(4 * 1024);
        int read = fileChannel.read(buffer);
        System.out.println("Read " + read + " bytes, content is " + buffer);

        int originPosition = buffer.position();

        buffer.flip();
        byte c = buffer.get();
        System.out.println((char) c);

        // write to file
        // buffer.flip();
        // buffer.rewind();
        // buffer.reset();

        ByteBuffer slice = buffer.slice();
        System.out.println(buffer);
        System.out.println(slice);

        buffer.clear();
        System.out.println(buffer);

        buffer.position(originPosition);
        System.out.println(buffer);

        byte b = slice.get();
        System.out.println((char) b);
    }
}
