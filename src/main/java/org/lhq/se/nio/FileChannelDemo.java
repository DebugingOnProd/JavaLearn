package org.lhq.se.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
@Slf4j
public class FileChannelDemo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("db.properties","rw");
        FileChannel channel = randomAccessFile.getChannel();
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        int read = channel.read(allocate);
        while (read!=-1){
            log.info("读取{}",read);
            while (allocate.hasRemaining()){
                log.info("转换:{}",(char)allocate.get());
            }
            allocate.clear();
            read = channel.read(allocate);
        }
        randomAccessFile.close();
        log.info("操作结束");
    }
}
