package com.doudou.cn.testthreadmodel;

import android.test.AndroidTestCase;

/**
 * Created by jinliang on 15/12/8.
 */
public abstract  class BaseTestCase  extends AndroidTestCase{
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        testSetUp();
    }
    protected   abstract void testSetUp();
   protected  abstract  void testTearDown();
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        testTearDown();
    }
}
