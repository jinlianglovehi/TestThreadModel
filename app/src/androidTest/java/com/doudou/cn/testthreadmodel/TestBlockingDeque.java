package com.doudou.cn.testthreadmodel;

import android.util.Log;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by jinliang on 15/12/8.
 */
public class TestBlockingDeque extends BaseTestCase{
private static final String TAG = TestBlockingDeque.class.getSimpleName();
    @Override
    protected void testSetUp() {

    }

    public void testDemo() throws InterruptedException {

        // 双端队列、栈、队列
        BlockingDeque<Integer> bDeque = new LinkedBlockingDeque<Integer>(20);

        for (int i = 0; i < 20; i++) {
            // 将指定元素添加到此阻塞栈中，如果没有可用空间，将一直等待（如果有必要）。
            try {
                // 放在队列的头部
                bDeque.putFirst(i);

                // 放在队列的底部
//                bDeque.putLast(i);

            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i(TAG, "testDemo " + "向阻塞栈中添加了元素:" + i);
        }

//        Log.i(TAG, "testDemo  peek:" + bDeque.peek());// 头部的查询元素

//        Log.i(TAG, "testDemo  task:"+ bDeque.take());// 头部拿出一个元素
//        Log.i(TAG, "testDemo  getFirst:" + bDeque.getFirst()); //只是获取头部的元素
//        Log.i(TAG, "testDemo getLast:" + bDeque.getLast());// 获取队列foot 元素

        for(int i=0; i<20 ;i++){
            Log.i(TAG, "testDemo peek:" + bDeque.poll());// 只是显示第一个元素 ，但是队列中不会减少。

            Log.i(TAG, "testDemo size:" + bDeque.size());
        }
        Log.i(TAG, "testDemo finishSize:" + bDeque.size());
        Log.i(TAG, "testDemo "+"程序到此运行结束，即将退出----");


    }
    @Override
    protected void testTearDown() {

    }
}
