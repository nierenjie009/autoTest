package com.course.server.testNG.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsOnMethod {
     //@BeforeGroups("server")标记的方法会在server组运行之前运行
     //@AfterGroups("server")标记的方法会在server组运行之后运行

    @Test(groups = "server")
    public void test1(){
        System.out.println("这是服务端组的测试方法1");
    }
    @Test(groups = "server")
    public void test2(){
        System.out.println("这是服务端组的测试方法2");
    }
    @Test(groups = "client")
    public void test3(){
        System.out.println("这是客户端组的测试方法1");
    }
    @Test(groups = "client")
    public void test4(){
        System.out.println("这是客户端组的测试方法2");
    }

    @BeforeGroups("server")
    public void beforeGroupsOnMethod(){
        System.out.println("这是在服务端组运行之前运行的方法");
    }
    @AfterGroups("server")
    public void afterGroupsOnMethod(){
        System.out.println("这是在服务端组运行之后运行的方法");
    }
    @BeforeGroups("client")
    public void beforeGroupsOnMethod2(){
        System.out.println("这是在客户端组运行之前运行的方法");
    }
    @AfterGroups("client")
    public void afterGroupsOnMethod2(){
        System.out.println("这是在客户端组运行之后运行的方法");
    }


}
