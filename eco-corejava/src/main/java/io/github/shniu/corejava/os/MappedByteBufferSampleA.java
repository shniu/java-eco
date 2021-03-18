package io.github.shniu.corejava.os;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

/**
 * @author niushaohan
 * @date 2021/3/15 18
 */
public class MappedByteBufferSampleA {

    public static void main(String[] args) throws IOException {
        Path path = null;
        try {
            path = Paths.get(new URI("file:///tmp/os/000000"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.exit(1);
        }

        CharBuffer writeBuffer = CharBuffer.wrap("This will be written to the file");
        CharBuffer charBuffer = null;
        MappedByteBuffer mappedByteBuffer = null;
        long readSize = 0;

        try (FileChannel fileChannel = (FileChannel)
                Files.newByteChannel(path,
                        EnumSet.of(StandardOpenOption.READ,
                                StandardOpenOption.WRITE))) {
            readSize = fileChannel.size();
            int length = writeBuffer.length();
            mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileChannel.size() + writeBuffer.length() + 1);

            if (mappedByteBuffer != null) {
                charBuffer = StandardCharsets.UTF_8.decode(mappedByteBuffer);
            }
        }

        assert charBuffer != null;
        // assert charBuffer.toString().equals("This is a content of file.");
        System.out.println("Before:");
        System.out.println(charBuffer.toString());

        // Write message
        mappedByteBuffer.position((int) readSize);
        mappedByteBuffer.put("\n".getBytes());
        mappedByteBuffer.put(StandardCharsets.UTF_8.encode(writeBuffer));
        System.out.println("After:");
        System.out.println(charBuffer.toString());
    }
}
