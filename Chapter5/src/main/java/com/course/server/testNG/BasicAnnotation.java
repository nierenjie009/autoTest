package com.course.server.testNG;

import org.testng.annotations.*;

public class BasicAnnotation {
    //TestNG常用的几个注解
    //最基本的注解,用来把方法标记为测试的一部分
    //@BeforeMethod标记的方法会在@Test标记的方法前执行,
    //@AfterMethod标记的方法会在@Test标记的方法后执行,
    //@BeforeClass标记的方法会在类运行前运行
    //@AfterClass标记的方法会在类运行后运行
    //@BeforeSuite标记的方法会在套件运行前运行
    //@AfterSuite标记的方法会在套件运行后运行
    //@BeforeTest标记的方法会在测试运行前运行
    //@AfterTest标记的方法会在测试运行后运行
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("beforeMethod这是测试方法前执行的");
    }

    @Test
    public void testCase1(){
        System.out.println("这是测试用例1");

    }

    @Test
    public void testCase2(){
        System.out.println("这是测试用例2");

    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("afterMethod这是测试方法后执行的");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("beforeClass这是在类之前运行的");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("afterClass这是在类之后运行的");
    }

    @BeforeSuite
    public  void beforeSuite(){
        System.out.println("这是在测试套件之前运行的");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("这是在测试套件之后运行的");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("在测试方法之前运行的方法");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("在测试方法之后运行的方法");
    }
}



