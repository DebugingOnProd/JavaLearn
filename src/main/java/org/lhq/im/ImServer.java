package org.lhq.im;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

@Slf4j
public class ImServer {
	/**
	 * 服务端启动
	 */
	public void start() throws IOException {

		//创建Selector
		Selector selector = Selector.open();

		//创建ServerSocketChannel
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

		//绑定端口
		serverSocketChannel.bind(new InetSocketAddress(8088));

		//将Channel设置非阻塞
		serverSocketChannel.configureBlocking(false);

		//将Channel注册到Selector上 监听连接事件
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		log.info("服务端通道已连接,服务端启动成功!");

		do {
			//轮询Selector  获得触发连接事件的channel数量
			int readyChannel = selector.select();

			//如果个数为0 则下一次循环  这是由于nio存在空轮询的bug
			if (readyChannel == 0) continue;

			//获取可用的Channel集合    selectedKeys方法是获取所有触发事件的客户端Channel
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectionKeys.iterator();

			while (iterator.hasNext()) {
				//获取SelectionKey实例
				SelectionKey selectionKey = iterator.next();

				//移除处理过的key
				iterator.remove();

				//判断触发的事件 进行相应的处理
				if (selectionKey.isAcceptable()) {
					//接入事件
					acceptHandler(serverSocketChannel, selector);
				}

				if (selectionKey.isReadable()) {
					//可读事件
					readHandler(selectionKey, selector);
				}
			}
		} while (true);


	}


	//可读事件处理器
	private void readHandler(SelectionKey selectionKey, Selector selector) throws IOException {
		log.info("处理可读事件");
		//获取客户端Channel
		SocketChannel channel = (SocketChannel) selectionKey.channel();

		//读取客户端发来的消息
		//创建Buffer
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		StringBuilder request = new StringBuilder();
		//循环读取客户端内容
		while (channel.read(buffer) > 0) {
			//当前是读模式  现在切换写模式  将读取到的数据写入request中
			buffer.flip();

			//读取buffer中的内容
			request.append(StandardCharsets.UTF_8.decode(buffer));
		}

		//将Channel再次注册打Selector上监听可读事件
		channel.register(selector, SelectionKey.OP_READ);

		//将客户端发来的消息广播给其他用户
		if (request.length() > 0) {
			broadCast(selector, channel, request);
		}
	}



	private void parsonChat(){

	}

	//将某个客户端发来的消息广播给其他用户
	private void broadCast(Selector selector, SocketChannel sourceChannel, StringBuilder request) {
		log.info("有一条广播消息");
		//获取所有已接入的客户端       keys方法是获取所有的客户端Channel  无论是否触发事件
		Set<SelectionKey> keys = selector.keys();

		//遍历所有客户端 向他们发送消息
		for (SelectionKey selectionKey : keys) {
			//获得客户端Channel
			Channel targetChannel = selectionKey.channel();
			//判断是否是客户端通道和发来消息的客户端通道   剔除服务端通道和发来消息的客户端
			if (targetChannel instanceof SocketChannel && targetChannel != sourceChannel) {
				try {
					//将消息发送到客户端
					((SocketChannel) targetChannel).write(StandardCharsets.UTF_8.encode(CharBuffer.wrap(request)));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}


	//接入事件处理器
	private void acceptHandler(ServerSocketChannel serverSocketChannel, Selector selector) throws IOException {
		log.info("有客户端接入");
		//获取客户端Channel
		SocketChannel socketChannel = serverSocketChannel.accept();

		SocketAddress remoteAddress = socketChannel.getRemoteAddress();
		log.info("客户端地址:{}",remoteAddress);

		//将客户端Channel设置为非阻塞模式
		socketChannel.configureBlocking(false);

		//将客户端注册到selector上  监听可读事件
		socketChannel.register(selector, SelectionKey.OP_READ);

		//回复客户端消息
		socketChannel.write(StandardCharsets.UTF_8.encode("欢迎您进入多人聊天室..."));

	}


	public static void main(String[] args) throws IOException {

		//启动服务端
		new ImServer().start();

	}


}
