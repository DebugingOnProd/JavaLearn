package org.lhq.utils;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamingThreadFactory implements ThreadFactory {
	private final AtomicInteger threadNum = new AtomicInteger();
	private final ThreadFactory factory;
	private final String threadName;

	public NamingThreadFactory(String threadName,ThreadFactory threadFactory){
		this.factory = threadFactory;
		this.threadName = threadName;
	}



	@Override
	public Thread newThread(@NotNull Runnable runnable) {
		Thread thread = factory.newThread(runnable);
		thread.setName(threadName + "[#"+threadNum.getAndIncrement() + "]");
		return thread;
	}
}
