package com.course.server.testNG.parameter;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class parameterTest {
  //xml参数化测试，即由xml文件来提供参数所需的值
    @Test
    @Parameters({"name","age"})
    public void parameterTest(String name, int age){

        System.out.println("name="+name+";age="+age);
    }
}
