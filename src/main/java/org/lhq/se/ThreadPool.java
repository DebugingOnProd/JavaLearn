package org.lhq.se;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Hades
 */
@Slf4j
public class ThreadPool {
    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4,
            4,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(),
            ThreadFactoryBuilder.create().setNamePrefix("wudalang-").build());
    private static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
    public void exec(){
        threadPoolExecutor.execute(() -> {
            for (int i = 1; i <= 30; i++) {
                try {
                    log.info("西门2买到了{}",queue.take());
                } catch (InterruptedException e) {
                    log.error("西门2被中断{}",e.getMessage());
                }
            }
        });

        threadPoolExecutor.execute(()->{
            for (int i = 1; i <=30; i++) {
                log.info("武大郎1做了{}个烧饼",i);
                try {
                    queue.put("1第"+i+"个烧饼");
                } catch (InterruptedException e) {
                    log.error("武大郎1被中断",e.getMessage());
                }
            }
        });
        threadPoolExecutor.execute(()->{
            for (int i = 1; i <= 30; i++) {
                try {
                    log.info("西门1买到了{}",queue.take());
                } catch (InterruptedException e) {
                    log.error("西门1被中断{}",e.getMessage());
                }
            }
        });
        threadPoolExecutor.execute(()->{
            for (int i = 1; i <=30; i++) {
                log.info("武大郎2做了{}个烧饼",i);
                try {
                    queue.put("2第"+i+"个烧饼");
                } catch (InterruptedException e) {
                    log.error("武大郎2被中断",e.getMessage());
                }
            }
        });



        threadPoolExecutor.shutdown();
    }
}
