package io.github.shniu.corejava.os;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author niushaohan
 * @date 2021/3/16 14
 */
public class MappedByteBufferSampleB {
    private static final int TEN_MB = 1024 * 1024 * 10;

    public static void main(String[] args) throws IOException {
        createFixedSizeFile();
        // test1();
    }

    public static void createFixedSizeFile() throws IOException {
        FileChannel fileChannel = new RandomAccessFile("/tmp/os/000002", "rw").getChannel();
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, TEN_MB * 2);// 20 M
        fileChannel.close();
    }

    public static void test1() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("/tmp/os/000001", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        MappedByteBuffer mappedByteBuffer;
        try {
            mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, TEN_MB);
        } finally {
            fileChannel.close();
            randomAccessFile.close();
        }

        if (mappedByteBuffer != null) {
            System.out.println("position: " + mappedByteBuffer.position());
            System.out.println("limit: " + mappedByteBuffer.limit());
            System.out.println("capacity: " + mappedByteBuffer.capacity());

            mappedByteBuffer.load();
            //mappedByteBuffer.put("abc".getBytes());

            byte[] bytes = new byte[3];
            mappedByteBuffer.get(bytes);
            System.out.println(new String(bytes));

            System.out.println("position: " + mappedByteBuffer.position());
            System.out.println("limit: " + mappedByteBuffer.limit());
            System.out.println("capacity: " + mappedByteBuffer.capacity());

            mappedByteBuffer.force();
        }
    }
}
