package org.lhq.im;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.lhq.entity.Message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Slf4j
public class ImServer {
	private int port;
	private Selector selector;
	private ExecutorService service;


	public static void main(String[] args) throws IOException {
		ImServer imServer = new ImServer(9999);
		imServer.start();
	}
	/**
	 * 初始化
	 * @param port
	 * @throws IOException
	 */
	public ImServer(int port) throws IOException {
		log.info("服务器初始化");
		this.port = port;
		this.service = Executors.newFixedThreadPool(5);
		ServerSocketChannel channel = ServerSocketChannel.open();
		channel.configureBlocking(false);
		channel.bind(new InetSocketAddress(port));
		selector = Selector.open();
		channel.register(selector, SelectionKey.OP_ACCEPT);
	}
	/**
	 * 启动
	 */
	public void start() {
		new Thread(() -> {
			try {
				log.info("TCP服务器启动");
				poll(selector);
			} catch (IOException e) {
				log.info("服务器异常退出...");
			}
		}, "Selector-IO").start();
	}
	/**
	 * 轮询键集
	 * @param selector
	 * @throws IOException
	 */
	private void poll(Selector selector) throws IOException {
		log.info("轮询selector");
		while (selector!=null) {
			selector.select();
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				if (key.isAcceptable()) {
					log.info("接受链接");
					handleAccept(key);
				} else if (key.isReadable()) {
					log.info("可读信息");
					handleRead(key);
				} else if (key.isWritable()) {
					log.info("可写信息");
					handleWrite(key);
				}
				iterator.remove();
			}
		}
	}
	private void handleRead(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		// 1. 读取数据
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		read(socketChannel, out);
		// 坑：浏览器空数据
		if (out.size() == 0) {
			log.info("关闭连接："+ socketChannel.getRemoteAddress());
			socketChannel.close();
			return;
		}
		// 2. 解码
		String str = StrUtil.str(out.toByteArray(), Charset.defaultCharset());
		Message message = JSONUtil.toBean(str, Message.class);
		// 3. 业务处理
		Exchange exchange = Exchange.getExchange();
		service.submit(() -> {
			// 获得响应
			try {
				exchange.exchange(message);
			} catch (InterruptedException e) {
				log.error("消息出错",e);
			}
			key.interestOps(SelectionKey.OP_WRITE);
			log.info("接受到信息{}",message);
			key.attach(message);
			// 坑：异步唤醒
			key.selector().wakeup();
			try {
				socketChannel.register(key.selector(), SelectionKey.OP_WRITE, message);
			} catch (ClosedChannelException e) {
				e.printStackTrace();
			}
		});

	}
	private void read(SocketChannel socketChannel, ByteArrayOutputStream out) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while (socketChannel.read(buffer) > 0) {
			buffer.flip(); // 切换到读模式
			out.write(buffer.array());
			buffer.clear(); // 清理缓冲区
		}
	}

	private void handleWrite(SelectionKey key) throws IOException {
		log.info("写入信息");
		SocketChannel channel = (SocketChannel) key.channel();
		Message message = (Message) key.attachment();

		// 编码
		String str = JSONUtil.toJsonStr(message);
		byte[] bytes = str.getBytes();
		channel.write(ByteBuffer.wrap(bytes));

		key.interestOps(SelectionKey.OP_READ);
		key.attach(null);
	}


	private void handleAccept(SelectionKey key) throws IOException {
		ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
		Selector selector = key.selector();
		SocketChannel socketChannel = serverSocketChannel.accept();
		log.info("请求的Channel:{}", socketChannel.getRemoteAddress());

		socketChannel.configureBlocking(false);
		socketChannel.register(selector, SelectionKey.OP_READ);
	}
}
