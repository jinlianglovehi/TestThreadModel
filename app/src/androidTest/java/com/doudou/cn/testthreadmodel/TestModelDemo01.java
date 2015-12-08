package com.doudou.cn.testthreadmodel;

import android.test.AndroidTestCase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by jinliang on 15/12/8.
 */
public class TestModelDemo01 extends AndroidTestCase{
private static final String TAG = TestModelDemo01.class.getSimpleName();

    /**
     * 线程以及队列
     */
    private static ThreadPoolExecutor threadPoolExecutor;
    private final static int TASK_NUM = 6;
    private static  CountDownLatch  countDownLatch;
    public void  add(){
        Log.i("TestModelDemo01", "test01 ");
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Log.i(TAG, "------setUp---- ");
        //设置队列的形式
        countDownLatch = new CountDownLatch(TASK_NUM);
        BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<Runnable>(5);
        threadPoolExecutor = new ThreadPoolExecutor(10, 30, 30L,
                TimeUnit.SECONDS, taskQueue);
    }

    public void testDelete(){
        Log.i(TAG, "---testDelete ---");
        executorTasks(TASK_NUM);

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "testDelete threadPoolExecutor:" + threadPoolExecutor);
        System.out.println(threadPoolExecutor);

        try {
            Thread.sleep(32000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "testDelete threadPoolExecutor:" + threadPoolExecutor);

        threadPoolExecutor.shutdown();
        Log.i(TAG, "testDelete threadPoolExecutor shutdonw");

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        Log.i(TAG, "---tearDown--- ");
    }

    private static Collection<Runnable> getNewTaskThread(int num) {
        Collection<Runnable> taskThreads = new ArrayList<Runnable>(num);
        for (int i = 0; i < num; i++) {
            taskThreads.add(new TaskThread(threadPoolExecutor, countDownLatch,
                    "Thread" + (i + 1)));
        }

        return taskThreads;
    }

    /**
     * 返回指定的任务数量
     *
     * @param num
     * @return
     */
    private static void executorTasks(int num) {
        Collection<Runnable> tasks = getNewTaskThread(num);
        Iterator<Runnable> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            threadPoolExecutor.execute(iterator.next());
        }
    }
    /**
     * 创建自定义的task 任务
     */
    static class TaskThread implements Runnable {
        private final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.sss";
        private ThreadPoolExecutor threadPoolExecutor;
        private CountDownLatch countDownLatch;
        private String name;

        public TaskThread(ThreadPoolExecutor threadPoolExecutor, CountDownLatch countDownLatch,
                          String name) {
            this.threadPoolExecutor = threadPoolExecutor;
            this.countDownLatch = countDownLatch;
            this.name = name + " create-time:"
                    + new SimpleDateFormat(DATE_FORMAT).format(new Date());
        }

        @Override
        public void run() {
            try {
                Log.i(TAG, "run " + name + "---" + threadPoolExecutor);
                Thread.sleep(10000l);
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
