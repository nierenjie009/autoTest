package com.course.server.testNG;

import org.testng.annotations.Test;

public class exceptionTest {
//     什么时候会用到异常测试?
//     故意传入错误的值，看是否会提示异常信息


    @Test(expectedExceptions = RuntimeException.class)
    //expectedExceptions=RunTimeException.class表示预期会出现运行时异常
    public void exceptionTestFailed(){
        System.out.println("这是一个失败的异常测试");

    }
    @Test(expectedExceptions =RuntimeException.class)
    public void exceptionTestSuccess(){

        System.out.println("这是一个成功的异常测试");
        throw new RuntimeException();
    }
}
