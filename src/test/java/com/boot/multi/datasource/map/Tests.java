package com.boot.multi.datasource.map;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author Xingyu Sun
 * @date 2018/10/27 15:15
 */
public class Tests {

    @Test
    public void test() {
        Map<String, String> map = new HashMap<>();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            threadPool.submit(() -> {
                map.put("a", "b");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                map.put("a", "c");
                System.out.println(Thread.currentThread().getId());
                System.out.println("test--------" + map.get("a"));
            });
        }
        threadPool.shutdown();
        while (true) {
            if (threadPool.isTerminated()) {
                System.out.println("main--------" + map.get("a"));
                break;
            }
        }
    }

    @Test
    public void thread() {
        ThreadFactory test = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 200, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024), test, new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 10; i++) {
            threadPool.submit(() -> {
                        System.out.println(Thread.currentThread().getId());
                        System.out.println(Thread.currentThread().getName());
                    }

            );
        }
        threadPool.shutdown();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
