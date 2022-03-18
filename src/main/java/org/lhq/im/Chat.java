package org.lhq.im;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.lhq.entity.ImUser;
import org.lhq.entity.Message;

import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
@Slf4j
public class Chat extends Thread   {
	private Selector selector;
	private SocketChannel socket;
	private ImUser imUser;
	public Chat(SocketChannel socket, Selector selector, ImUser user){
		super();
		this.selector = selector;
		this.socket = socket;
		this.imUser = user;
	}
	@Override
	public void run() {
		try {
			//等待连接建立
			Thread.sleep(500);
		} catch (InterruptedException e) {
			log.info("线程被中断");
		}
		Scanner scanner = new Scanner(System.in);
		log.info("请输入您要发送给服务端的消息");
		log.info("=========================================================");
		while (scanner.hasNextLine()) {
			String s = scanner.nextLine();
			try {
				Message message = new Message();
				String[] strings = s.split(",");
				message.setDestId(Integer.parseInt(strings[0]));
				message.setMessage(strings[1]);
				message.setFromId(imUser.getUserId());
				String jsonStr = JSONUtil.toJsonStr(message);
				//用户已输入，注册写事件，将输入的消息发送给客户端
				socket.register(selector, SelectionKey.OP_WRITE, ByteBuffer.wrap(jsonStr.getBytes()));
				//唤醒之前因为监听OP_READ而阻塞的select()
				selector.wakeup();
			} catch (ClosedChannelException e) {
				log.error("消息发送错误",e);
			}
		}


	}
}
