package com.course.server.testNG;

import org.testng.annotations.Test;

import java.util.concurrent.TimeoutException;

public class timeoutTest {

    @Test(timeOut = 3000)//单位为毫秒
    public void test1() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("哈哈");
    }

    @Test(timeOut = 2000)//单位为毫秒
    public void test2() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("哈哈");
    }

}
