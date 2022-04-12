package org.lhq.im;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.lhq.entity.Message;
import org.lhq.entity.User;


import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @program: JavaLearn
 * @description:
 * @author: wang.defa
 * @create: 2022-03-16 11:23
 */
public class Exchange {
	/**
	 *未读消息队列
	 */
	private volatile Map<Integer, LinkedBlockingQueue<Message>> unreadMessageMap = Maps.newConcurrentMap();
	private static volatile Exchange exchange;
	private Exchange(){}
	public static Exchange getExchange(){
		if (null==exchange){
			synchronized (Exchange.class){
				if (null==exchange){
					exchange = new Exchange();
				}
			}
		}
		return exchange;
	}
	public  void exchange(Message message) throws InterruptedException {
		Integer destId = message.getDestId();
		LinkedBlockingQueue<Message> messages = unreadMessageMap.get(destId);
		if (messages!=null){
			messages.put(message);
		}else {
			messages = new LinkedBlockingQueue<>();
			messages.put(message);
		}
		unreadMessageMap.put(1,messages);
	}

	public Message getUnReadMessgae(Integer userId) throws InterruptedException {
		return unreadMessageMap.get(userId).take();
	}
}
