package org.lhq.jvm;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author Hades
 */
@Slf4j
@NoArgsConstructor
public class WdfClassLoader  extends ClassLoader{

    public WdfClassLoader(ClassLoader classLoader){
        super(classLoader);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            File file = new File("D:/sss");
            byte[] classBytes = this.getClassBytes(file);
            return this.defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return super.findClass(name);


    }

    private byte[] getClassBytes(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel channel = fileInputStream.getChannel();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        WritableByteChannel writableByteChannel = Channels.newChannel(byteArrayOutputStream);
        ByteBuffer allocate = ByteBuffer.allocate(1034);
        while (true){
            int read = channel.read(allocate);
            if (read == 0|| read == -1){
                break;
            }
            allocate.flip();
            writableByteChannel.write(allocate);
            allocate.clear();
        }
        fileInputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
}
