package org.lhq.se;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;


@DisplayName("Nio测试")
@Slf4j
public class NioTest {

	@DisplayName("缓冲区")
	@Test
	void buffer() {
		IntBuffer intBuffer = IntBuffer.allocate(10);
		for (int i = 0; i < intBuffer.capacity(); i++) {
			int randomInt = new Random().nextInt(20);
			intBuffer.put(randomInt);
		}
		intBuffer.flip();
		while (intBuffer.hasRemaining()) {
			log.info("缓存元素输出:{}", intBuffer.get());
		}
	}

	/**
	 * @throws IOException
	 */
	@DisplayName("通道测试")
	@Test
	void channel() throws IOException {

		/**
		 *
		 * @description 传统io读取数据
		 */
		FileInputStream fileInputStream = new FileInputStream("db.properties");
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		while (bufferedReader.ready()) {
			log.info("读取到的数据:{}", bufferedReader.readLine());
		}
		log.info("-----------------------------------");

		/**
		 * nio读取数据
		 */
		FileInputStream nioFileInputStream = new FileInputStream("db.properties");
		ByteBuffer byteBuffer = ByteBuffer.allocate(512);
		FileChannel channel = nioFileInputStream.getChannel();
		channel.read(byteBuffer);
		byteBuffer.flip();
		while (byteBuffer.hasRemaining()) {
			log.info("读取到的数据:{}", (char) byteBuffer.get());
		}

	}

	@Test
	void selector() throws IOException {
		Selector selector = Selector.open();
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		log.info("选择器打开并等待连接:{}",selector.isOpen());
		InetSocketAddress address = new InetSocketAddress("localhost", 8888);

		serverSocketChannel.bind(address);
		serverSocketChannel.configureBlocking(false);
		int ops = serverSocketChannel.validOps();
		serverSocketChannel.register(selector, ops, null);
		while (true) {
			log.info("等待选择器操作");
			int select = selector.select();
			log.info("The Number of selectKeys are:{}",select);
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectionKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey next = iterator.next();
				if (next.isAcceptable()) {
					SocketChannel accept = serverSocketChannel.accept();
					accept.configureBlocking(false);
					accept.register(selector, SelectionKey.OP_READ);
					log.info("一个新的连接被接受来自客户端:{}",accept);
				}else if(next.isReadable()){
					SocketChannel channel =(SocketChannel) next.channel();
					ByteBuffer buffer = ByteBuffer.allocate(256);
					channel.read(buffer);
					String output = new String(buffer.array()).trim();
					log.info("接收到消息:{}",output);
					if ("close".equals(output)) {
						channel.close();
						log.info("关闭对话");
					}

				}
				iterator.remove();
			}
		}
	}

	@Test
	@DisplayName("这是啥")
	void scatter() {

	}
}
