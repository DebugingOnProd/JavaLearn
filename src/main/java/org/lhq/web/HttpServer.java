package org.lhq.web;

import lombok.extern.slf4j.Slf4j;
import org.lhq.web.http.HttpRequest;
import org.lhq.web.http.HttpResponse;
import org.lhq.web.http.HttpServlet;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Slf4j
public class HttpServer {
	private int port;
	private Selector selector;
	private HttpServlet servlet;
	ExecutorService service;

	public static void run(int port,String[] args) {
		port = port == 0 ? 8080:port;
		try {
			HttpServer server = new HttpServer(port, new HttpServlet());
			server.start();
			log.info("服务器启动成功, 您现在可以访问 http://localhost:" + server.port);
		} catch (IOException e) {
			log.error("服务器启动失败...",e);
		}
		//System.in.read();
	}

	/**
	 * 初始化
	 * @param port
	 * @param servlet
	 * @throws IOException
	 */
	public HttpServer(int port, HttpServlet servlet) throws IOException {
		this.port = port;
		this.servlet = servlet;
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
				poll(selector);
			} catch (IOException e) {
				log.info("服务器异常退出...");
				e.printStackTrace();
			}
		}, "Selector-IO").start();
	}


	/**
	 * 轮询键集
	 * @param selector
	 * @throws IOException
	 */
	private void poll(Selector selector) throws IOException {
		while (selector!=null) {
			selector.select();
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				if (key.isAcceptable()) {
					handleAccept(key);
				} else if (key.isReadable()) {
					handleRead(key);
				} else if (key.isWritable()) {
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
		final HttpRequest request = decode(out.toByteArray());
		// 3. 业务处理
		service.submit(() -> {
			HttpResponse response = new HttpResponse();
			if ("get".equalsIgnoreCase(request.getMethod())) {
				servlet.doGet(request, response);
			} else if ("post".equalsIgnoreCase(request.getMethod())) {
				servlet.doPost(request, response);
			}
			// 获得响应
			key.interestOps(SelectionKey.OP_WRITE);
			key.attach(response);
			// 坑：异步唤醒
			key.selector().wakeup();
//          socketChannel.register(key.selector(), SelectionKey.OP_WRITE, response);
		});

	}


	/**
	 * 从缓冲区读取数据并写入 {@link ByteArrayOutputStream}
	 * @param socketChannel
	 * @param out
	 * @throws IOException
	 */
	private void read(SocketChannel socketChannel, ByteArrayOutputStream out) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while (socketChannel.read(buffer) > 0) {
			buffer.flip(); // 切换到读模式
			out.write(buffer.array());
			buffer.clear(); // 清理缓冲区
		}
	}


	/**
	 * 解码 Http 请求报文
	 * @param array
	 * @return
	 */
	private HttpRequest decode(byte[] array) {
		try {
			HttpRequest request = new HttpRequest();
			ByteArrayInputStream inStream = new ByteArrayInputStream(array);
			InputStreamReader reader = new InputStreamReader(inStream);
			BufferedReader in = new BufferedReader(reader);

			// 解析起始行
			String firstLine = in.readLine();
			log.info("第一行:{}",firstLine);
			String[] split = firstLine.split(" ");
			request.setMethod(split[0]);
			request.setUrl(split[1]);
			request.setVersion(split[2]);

			// 解析首部
			Map<String, String> headers = new HashMap<>();
			while (true) {
				String line = in.readLine();
				// 首部以一个空行结束
				if ("".equals(line.trim())) {
					break;
				}
				String[] keyValue = line.split(":");
				headers.put(keyValue[0], keyValue[1]);
			}
			request.setHeaders(headers);

			// 解析请求主体
			CharBuffer buffer = CharBuffer.allocate(1024);
			CharArrayWriter out = new CharArrayWriter();
			while (in.read(buffer) > 0) {
				buffer.flip();
				out.write(buffer.array());
				buffer.clear();
			}
			request.setBody(out.toString());
			return request;
		} catch (Exception e) {
			log.error("解码 Http 失败",e);
		}
		return null;
	}

	private void handleWrite(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();
		HttpResponse response = (HttpResponse) key.attachment();

		// 编码
		byte[] bytes = encode(response);
		channel.write(ByteBuffer.wrap(bytes));

		key.interestOps(SelectionKey.OP_READ);
		key.attach(null);
	}

	/**
	 * http 响应报文编码
	 * @param response
	 * @return
	 */
	private byte[] encode(HttpResponse response) {
		StringBuilder builder = new StringBuilder();
		if (response.getCode() == 0) {
			response.setCode(200); // 默认成功
		}
		// 响应起始行
		builder.append("HTTP/1.1 ")
				.append(response.getCode())
				.append(" ")
				.append(response.getCode())
				.append("\r\n");
		// 响应头
		if (response.getBody() != null && response.getBody().length() > 0) {
			builder.append("Content-Length:").append(response.getBody().length()).append("\r\n");
			builder.append("Content-Type:text/html\r\n");
		}
		if (response.getHeaders() != null) {
			String headStr = response.getHeaders().entrySet().stream().map(e -> e.getKey() + ":" + e.getValue())
					.collect(Collectors.joining("\r\n"));
			if (!headStr.isEmpty()) {
				builder.append(headStr).append("\r\n");
			}
		}
		// 首部以一个空行结束
		builder.append("\r\n");
		if (response.getBody() != null) {
			builder.append(response.getBody());
		}
		return builder.toString().getBytes();
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
