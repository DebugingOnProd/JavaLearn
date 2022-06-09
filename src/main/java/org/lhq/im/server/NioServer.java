package org.lhq.im.server;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

@Slf4j
public class NioServer {
    // 缓冲区的大小
    private static final int BUFFER_SIZE = 1024;

    // 缓冲区
    private ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
    private Selector selector;

    public void initServer(int port) throws IOException {
        // 获得一个ServerSocketChannel通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 设置通道为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 将该通道对应的ServerSocket绑定到port端口
        serverSocketChannel.bind(new InetSocketAddress(port));
        // 获得一个通道管理器
        this.selector = Selector.open();
        // 将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后，
        // 当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void listen() throws IOException {
        log.info("服务端启动成功");
        while (true){
            selector.select();
            Iterator<SelectionKey> keyIterator = this.selector.selectedKeys().iterator();
            while (keyIterator.hasNext()){
                SelectionKey selectionKey = keyIterator.next();
                hendleKey(selectionKey);
                keyIterator.remove();
            }
        }
    }

    public void hendleKey(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isAcceptable()) {
            ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();// 获取通道 转化为要处理的类型
            SocketChannel socketChannel = server.accept();
            // SocketChannel通道的可读事件注册到Selector中
            registerChannel(selector, socketChannel, SelectionKey.OP_READ);
            ServerThread serverThread = new ServerThread(socketChannel);
            // 连接成功 向Client打个招呼
            if (socketChannel.isConnected()) {
                buffer.clear();
                buffer.put("I am Server...".getBytes());
                buffer.flip();
                socketChannel.write(buffer);

            }
        }
        // 通道的可读事件就绪
        if (selectionKey.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            buffer.clear(); // 清空缓冲区
            // 读取数据
            int len = 0;
            while ((len = socketChannel.read(buffer)) > 0) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    log.info("Server读取的数据:" + new String(buffer.array(), 0, len));
                }
            }
            if (len < 0) {
                // 非法的SelectionKey 关闭Channel
                socketChannel.close();
            }
            // SocketChannel通道的可写事件注册到Selector中
            registerChannel(selector, socketChannel, SelectionKey.OP_WRITE);
        }
        // 通道的可写事件就绪
        if (selectionKey.isWritable()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            buffer.clear(); // 清空缓冲区
            // 准备发送的数据
            String messageFromServer = "Hello,Client... " + socketChannel.getLocalAddress();
            buffer.put(messageFromServer.getBytes());
            buffer.flip();
            socketChannel.write(buffer);
            log.info("Server发送的数据:" + messageFromServer);
            // SocketChannel通道的可写事件注册到Selector中
            registerChannel(selector, socketChannel, SelectionKey.OP_READ);
        }
    }

    // 注册通道到指定Selector上
    private void registerChannel(Selector selector, SelectableChannel channel, int ops) throws IOException {
        if (channel == null) {
            return;
        }
        channel.configureBlocking(false);
        // 注册通道
        channel.register(selector, ops);

    }


    public static void main(String[] args) throws IOException {
        NioServer nioServer = new NioServer();
        nioServer.initServer(9999);
        nioServer.listen();
    }

}
