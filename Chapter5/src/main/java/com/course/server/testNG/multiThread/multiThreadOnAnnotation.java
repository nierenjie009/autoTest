package com.course.server.testNG.multiThread;

import org.testng.annotations.Test;
//多线程测试，注解方法实现
public class multiThreadOnAnnotation {

     @Test(invocationCount = 5,threadPoolSize = 3)
     public void test(){
         System.out.println("哈哈");
         System.out.printf("ThreadId:%s%n",Thread.currentThread().getId());
     }
}
