package com.course.server.testNG.multiThread;

import org.testng.annotations.Test;

public class multiThreadOnXml {
    @Test
    public void test1(){
        System.out.printf("Thread Id:%s%f",Thread.currentThread().getId());
    }
    @Test
    public void test2(){
        System.out.printf("Thread Id:%s%f",Thread.currentThread().getId());
    }
    @Test
    public void test3(){
        System.out.printf("Thread Id:%s%f",Thread.currentThread().getId());
        
    }

}
