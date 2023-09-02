package com.gyb.join;

/**
 * Description:
 * 该例子是用来说明，通过join()方法，可以实现线程之间的同步。
 * 例如，如果一个线程A需要依赖另一个线程B的结果，那么线程A可以在需要使用结果的地方调用线程B的join()方法，
 * 等待线程B执行完毕后再继续执行。
 *
 * @author GB
 * @date 2022/8/12 9:18
 */
public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        Runnable x = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName() + "=========");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(x);
        thread.start();
        thread.join();

        System.out.println("890890");


    }

}
