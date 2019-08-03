package com.course.server.testNG.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class SuiteConfig {
    //这个类里面被@BeforeClass @AfterClass  @BeforeMethod @AfterMethod标记的方法是不起作用的，所以没必要给方法添加这几个注解
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("在测试套件之前运行的方法");
    }

    @AfterSuite
    public void AfterSuite(){
        System.out.println("在测试套件之后运行的方法");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("beforeTest");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("afterTest");
    }

}
