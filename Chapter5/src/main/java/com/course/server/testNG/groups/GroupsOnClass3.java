package com.course.server.testNG.groups;

import org.testng.annotations.Test;

@Test(groups = "tea")
public class GroupsOnClass3 {

    public void tea1(){
        System.out.println("GroupsOnClass3中的老师的方法1运行");
    }

    public void tea2(){
        System.out.println("GroupsOnClass3中的老师的方法2运行");
    }
}
