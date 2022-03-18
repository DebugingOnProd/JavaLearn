package org.lhq.im;

import lombok.extern.slf4j.Slf4j;
import org.lhq.entity.ImUser;
import org.lhq.entity.enums.UserStatus;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

@Slf4j
public class ImClient {
	private Selector selector;

	public ImClient(String serverHost, int port) throws IOException {
		SocketChannel channel = SocketChannel.open();
		channel.configureBlocking(false);
		selector = Selector.open();
		channel.connect(new InetSocketAddress(serverHost, port));
		channel.register(selector, SelectionKey.OP_CONNECT);
		ImUser imUser = new ImUser();
		imUser.setUserId(1);
		imUser.setUserStatus(UserStatus.ONLINE);
		new Chat(channel, selector,imUser).start();
	}

	public void listen() throws IOException {
		log.info("客户端启动");
		while (selector != null) {
			//选择注册过的io操作的事件(第一次为SelectionKey.OP_CONNECT)
			selector.select();
			Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
			while (ite.hasNext()) {
				SelectionKey key = ite.next();
				//删除已选的key，防止重复处理
				ite.remove();
				//可链接
				if (key.isConnectable()) {
					SocketChannel channel = (SocketChannel) key.channel();
					if (channel.isConnectionPending()) {
						channel.finishConnect();
					}
					while (!channel.finishConnect()) {
						log.info("链接中");
					}
					channel.register(selector, SelectionKey.OP_READ);
					log.info("链接成功");
				} else if (key.isWritable()) {
					log.info("链接可写");
					SocketChannel channel = (SocketChannel) key.channel();
					channel.write((ByteBuffer) key.attachment());
					channel.register(selector, SelectionKey.OP_READ);
				} else if (key.isReadable()) {
					log.info("链接可读");
					SocketChannel channel = (SocketChannel) key.channel();
					ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 4);
					int len;
					if ((len = channel.read(byteBuffer)) > 0) {
						log.info("来着服务器的消息");
						log.info(new String(byteBuffer.array(), 0, len));
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new ImClient("localhost", 9999).listen();
	}
}
