package org.lhq.se;


import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class BlockQ {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
         //LinkedList<String> xiaobai = new LinkedList<>();
        LinkedList<String> road = new LinkedList<>();
         Thread xiaoBai = new Thread(() -> {
             for (int i = 1; i <=30; i++) {
                 log.info("小白做了{}个烧饼",i);
                 try {
                     queue.put("第"+i+"个烧饼");
                 } catch (InterruptedException e) {
                     log.error("小白被中断",e);
                 }
             }
        });




        Thread xiaoBai2 = new Thread(() -> {
            for (int i = 1; i <=30; i++) {
                log.info("小白做了{}个烧饼",i);
                try {
                    queue.put("第"+i+"个烧饼");
                } catch (InterruptedException e) {
                    log.error("小白被中断",e);
                }
            }
        });

        var roadA = new Thread(()->{
            for (int i = 1; i <= 30; i++) {
                try {
                    log.info("路人买到了{}",queue.take());
                } catch (InterruptedException e) {
                    log.error("路人被中断{}",e.getMessage());
                }
            }
        });
        var roadB = new Thread(()->{
            for (int i = 1; i <= 30; i++) {
                try {
                    log.info("路人买到了{}",queue.take());
                } catch (InterruptedException e) {
                    log.error("路人被中断{}",e.getMessage());
                }
            }
        });

        xiaoBai.start();
        xiaoBai2.start();
        roadA.start();
        roadB.start();
        try {
            xiaoBai.join();
            xiaoBai2.join();
            roadA.join();
            roadB.join();
        }catch (InterruptedException e){
            log.error("join产生中断",e);
        }

    }
}
