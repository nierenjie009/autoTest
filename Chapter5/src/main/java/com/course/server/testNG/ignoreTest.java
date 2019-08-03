package com.course.server.testNG;

import org.testng.annotations.Test;

public class ignoreTest {

    @Test
    public void test1(){
        System.out.println("哈哈");
    }
    @Test(enabled = false) //enabled赋值为false，就是忽略测试
    public void test2(){
        System.out.println("嘿嘿");
    }

    @Test(enabled = true)
    public void test3(){
        System.out.println("嘻嘻");
    }
}
