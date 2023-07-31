package com.gyb.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 *
 * @author GB
 * @date 2022/10/3 11:38
 */
@Slf4j
public class Test3 {
    private static final Map<Integer, Integer> map = new ConcurrentHashMap<>();
    private static AtomicInteger atomicInteger = new AtomicInteger();

    public void setA(int a) {
        int b = 0;
        b = a;
        map.put(a, b);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100000; i++) {
            Test4 test4 = new Test4(i);
            Thread thread = new Thread(test4);
            thread.start();
        }
        TimeUnit.SECONDS.sleep(10);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (!entry.getValue().equals(entry.getKey())) {
               atomicInteger.incrementAndGet();
            }
        }
        log.info("最终={}",atomicInteger.get());
        System.out.println(atomicInteger.get());


    }

}

@Data
@AllArgsConstructor
class Test4 implements Runnable {
    private int a;

    @Override
    public void run() {
        Test3 test3 = new Test3();
        System.out.println("当前是==== " + a );
        test3.setA(a);
    }
}
