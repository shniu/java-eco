package io.github.shniu.corejava.os;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

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
    }
}
