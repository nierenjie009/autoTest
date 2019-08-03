package com.course.server.testNG.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class dataProviderTest {
    //由@DataProvider标记的方法提供参数所需的值
    @Test(dataProvider = "data")
    public void dataProviderTest(String name,int age){
        System.out.println("name="+name+";age="+age);
    }

    @DataProvider(name="data")
    public Object[][] dataProvider(){
        Object[][] o=new Object[][]{

                {"zhangsan",10},
                {"lisi",20},
                {"wangwu",30}
        };
        return o;
    }
    @Test(dataProvider = "method")
    public void test1(String name,int age){
        System.out.println("name是"+name+";age是"+age);
    }
    @Test(dataProvider = "method")
    public void test2(String name,int age){
        System.out.println("name是"+name+";age是"+age);
    }
    @DataProvider(name="method")
    public Object[][] dataProvider1(Method method){
       Object[][] result=null;
       if(method.getName().equals("test1")){
           result=new Object[][]{
                   {"zhangsan",10},
                   {"lisi",20}
           };
       }else if(method.getName().equals("test2")){
          result=new Object[][]{
                  {"wangwu",30},
                  {"zhaoliu",40}
          };
       }
         return result;
    }
}
