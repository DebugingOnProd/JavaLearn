package org.lhq.im;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Slf4j
public class ImClient {
	public void start(String name) throws IOException {

		//创建SocketChannel通道
		SocketChannel socketChannel = SocketChannel.open();

		//绑定IP和端口 连接服务器
		socketChannel.connect(new InetSocketAddress("127.0.0.1", 8088));

		//接受服务器响应
		Selector selector = Selector.open();
		socketChannel.configureBlocking(false);
		socketChannel.register(selector, SelectionKey.OP_READ);

		//如果服务器没有响应  会一直阻塞在这里 如果服务器响应 则进入下面

		//新开线程 专门负责处理服务端响应数据
		new Thread(new NioClientHandler(selector),"input—thread").start();

		//向服务端发送数据
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {

			String line = scanner.nextLine();
			if (line != null && line.length() > 0) {
				socketChannel.write(StandardCharsets.UTF_8.encode(name + " : " + line));
			}

		}


	}


	public static void main(String[] args) throws IOException {
		new ImClient().start("老王一号");
	}


}
