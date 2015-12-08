package com.doudou.cn.testthreadmodel;

import android.test.AndroidTestCase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jinliang on 15/12/8.
 * 测试线程的原子性
 *
 * 线程原子性：
 *  就是多个线程可以同时操作一个变量，
 *  不会发生错误。
 *
 *
 *
 */
public class TestThreadAtomic  extends AndroidTestCase{

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }


    public void testThreadAtomic(){
        //线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Lock lock = new ReentrantLock(false);

        Runnable t1 = new MyRunnuable("t1", 2000, lock);
        Runnable t2 = new MyRunnuable("t2", 3600, lock);
        Runnable t3 = new MyRunnuable("t3", 2700, lock);
        Runnable t4 = new MyRunnuable("t4", 600, lock);
        Runnable t5 = new MyRunnuable("t5", 1300, lock);
        Runnable t6 = new MyRunnuable("t6", 800, lock);

        // 执行各个线程
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        pool.execute(t6);

        // 关闭线程池
        pool.shutdown();



    }
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }


}

//测试线程的原子
class  MyRunnuable implements   Runnable{
    private static AtomicLong atomicLong = new AtomicLong(10000);//原子量 每个线程都可以自由的操作。
    private String name;
    private Lock lock;
    private int  x ;


    MyRunnuable(String name, int x, Lock lock) {
        this.name = name;
        this.x = x;
        this.lock = lock;
    }

    public void run() {

        // 原子量虽然可以保证单个变量在某一个操作过程的安全，但无法保证你整个代码块，或者整个程序的安全性。
        // 因此，通常还应该使用锁等同步机制来控制整个程序的安全性。
//        atomicLong.addAndGet(l);
//        atomicLong.getAndAdd()

        lock.lock();
        System.out.println(name + "执行了" + x + "，当前余额：" + atomicLong.addAndGet(x));
        lock.unlock();
    }
}