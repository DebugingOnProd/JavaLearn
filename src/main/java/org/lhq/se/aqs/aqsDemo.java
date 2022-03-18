package org.lhq.se.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
@Slf4j
public class aqsDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        new Thread(()->{
            lock.lock();
            try{
                log.info("A线程开始");
                try {
                    TimeUnit.MINUTES.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
            }
        },"A").start();



        new Thread(()->{
            lock.lock();
            try{
                log.info("B线程开始");
            } finally {
                lock.unlock();
            }
        },"B").start();




        new Thread(()->{
            lock.lock();
            try{
                log.info("C线程开始");

            } finally {
                lock.unlock();
            }
        },"C").start();

    }
}
