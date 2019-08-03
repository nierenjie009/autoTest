package com.course.server.testNG;

import org.testng.annotations.Test;

public class dependTest{
//    @Test注解中的dependsOnMethods属性表示该方法的执行依赖另一个方法
    @Test
    public void test1(){
        System.out.println("test1 run");
        throw new RuntimeException();
    }
    @Test(dependsOnMethods = "test1")
    public void test2(){
        System.out.println("test2 run");    }
}
    //test1方法执行失败,test2方法由于test1方法执行失败而没法执行，即被忽略
    //依赖测试常常用于前置条件，比如购买之前肯定得登陆