package org.lhq.im.server;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
@Slf4j
public class ServerThread implements Runnable{
    private SocketChannel socketChannel;

    public ServerThread(SocketChannel socketChannel){
        this.socketChannel = socketChannel;
    }
    @Override
    public void run() {
        while (socketChannel!=null){
            try {
                SocketAddress remoteAddress = socketChannel.getRemoteAddress();
                log.info("远端地址为:{}",remoteAddress);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
