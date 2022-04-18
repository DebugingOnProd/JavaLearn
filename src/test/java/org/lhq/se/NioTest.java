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
import java.nio.channels.Pipe;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * NIO编程步骤
 * 1.创建Selector选择器
 * 2.创建ServerSocketChannel,并绑定监听端口
 * 3.设置通道的非阻塞模式
 * 4.吧Channel注册到Selector上
 * 5.循环调用Selector的select方法,监听通道的就绪情况
 * 6.获取就绪的Channel集合(selectKeys)
 * 7.遍历Channel集合判断就绪类型，实现具体业务
 * 8.根据业务，决定是否再次注册监听事件。重复执行第三部操作
 */


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
	@DisplayName("选择器/多路复用器")
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
	@DisplayName("通道实例2")
	void channel2() throws IOException {
		//----------------   服务端    ----------------------

		//服务端通过服务端Socket创建Channel
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

		//给服务端Channel绑定8088端口
		serverSocketChannel.bind(new InetSocketAddress(8088));

		//服务端监听客户端连接，建立SocketChannel连接
		SocketChannel socketChannel = serverSocketChannel.accept();

		//----------------   客户端    ----------------------------
		//客户端通道连接远程主机端口和IP
		SocketChannel socketChannel1 = SocketChannel.open(new InetSocketAddress("127.0.0.1",8088));

	}

	@Test
	@DisplayName("测试管线")
	void pipe() throws IOException {
		Pipe pipe = Pipe.open();
		// 发送数据
		Pipe.SinkChannel sink = pipe.sink();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.put("NIO".getBytes());
		buffer.flip();
		//向管线写入数据
		sink.write(buffer);

		//接受数据
		Pipe.SourceChannel source = pipe.source();
		ByteBuffer sourceBuffer = ByteBuffer.allocate(1024);
		int read = source.read(sourceBuffer);
		log.info("输出管道消息:{}",new String(sourceBuffer.array(),0,read));
		source.close();
		sink.close();
	}

	//Scatter/Gather
	@Test
	@DisplayName("分散于聚集")
	void scatterAndGathaer(){
		ByteBuffer header = ByteBuffer.allocate(1024);
		ByteBuffer body = ByteBuffer.allocate(1024);
		ByteBuffer[] byteBuffers = {header,body};
	}
	@Test
	@DisplayName("socket监听器")
	void socketChannel() throws IOException, InterruptedException {
		ByteBuffer buffer = ByteBuffer.wrap("你好,也可以不好，无所谓".getBytes());
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().bind(new InetSocketAddress(8888));
		serverSocketChannel.configureBlocking(false);
		while (true) {
			log.info("等待连接");
			SocketChannel socketChannel = serverSocketChannel.accept();
			if (socketChannel==null) {
				log.info("socketChannel为空");
				TimeUnit.SECONDS.sleep(1);
			}else {
				log.info("链接来自:{}",socketChannel.socket().getRemoteSocketAddress());
				buffer.rewind();
				socketChannel.write(buffer);
				socketChannel.close();
			}
		}
	}
}
