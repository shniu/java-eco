package io.github.shniu.corejava.os;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author niushaohan
 * @date 2021/3/19 11
 */
public class FileChannelSample2 {

    public static void main(String[] args) throws IOException {
        FileChannel fileChannel = new RandomAccessFile("/tmp/os/file1.data", "rw").getChannel();
        FileChannel targetChannel = new RandomAccessFile("/tmp/os/file2.data", "rw").getChannel();

        fileChannel.transferTo(0, fileChannel.size(), targetChannel);

        System.out.println("Source File size " + fileChannel.size() + ", and Target File size " + targetChannel.size());

        FileChannel channel2 = FileChannel.open(Paths.get("/tmp/os/file1.data"), StandardOpenOption.READ);

        ByteBuffer buffer = ByteBuffer.allocate(4 * 1024);
        channel2.read(buffer);
        System.out.println(buffer);
    }
}
