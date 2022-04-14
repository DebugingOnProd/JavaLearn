package org.lhq.utils;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AsyncThreadPoolUtil {
	private static final Logger log = LoggerFactory.getLogger(AsyncThreadPoolUtil.class);
	private volatile static ThreadPoolExecutor executor = null;
	private volatile static  ListeningExecutorService listeningExecutor;

	private AsyncThreadPoolUtil() { }
	public static ListeningExecutorService getGuavaExecutor() {
		if (listeningExecutor == null) {
			listeningExecutor = MoreExecutors.listeningDecorator(AsyncThreadPoolUtil.getInstance());
		}
		return listeningExecutor;
	}

	public static ExecutorService getInstance() {
		if (executor == null){
			synchronized (AsyncThreadPoolUtil.class) {
				if (executor==null){
					executor = new ThreadPoolExecutor (
							16,
							100,
							500,
							TimeUnit.SECONDS,
							new LinkedBlockingQueue<>(),
							new ThreadPoolExecutor.CallerRunsPolicy()
							   /*
								当线程池的任务缓冲队列已满 并且 线程池中的线程数目达到maximumPoolSize，如果还有任务来就会采取拒绝策略
								通常采用以下四中策略：
								ThreadPoolExecutor.AbortPolicy         丢弃任务并抛出RejectedExecutionException异常
								ThreadPoolExecutor.DiscardPolicy       丢弃任务，但是不抛异常
								ThreadPoolExecutor.DiscardOldestPolicy 丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
								ThreadPoolExecutor.CallerRunsPolicy    尝试添加当前任务，自动重复调用execute()方法，直到成功
         					*/
					);
					log.info("\n 自定义线程池初始化完毕： \n 核心线程数为：{} \n 线程池最大数量为：{} \n 溢出线程空闲销毁时间：{}秒",
							executor.getCorePoolSize(),
							executor.getMaximumPoolSize(),
							executor.getKeepAliveTime(TimeUnit.SECONDS)
					);
				}
			}
		}
		return executor;
	}

	public static void shutdown(){
		if(executor!=null) executor.shutdown();

	}

}
