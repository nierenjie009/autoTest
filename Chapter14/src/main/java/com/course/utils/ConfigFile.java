package com.course.utils;

import com.course.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigFile {

    //通过application这个文件名就可以找到application.properties这个配置文件
    //这个类主要是用来拼接接口的地址
    private static ResourceBundle bundle=ResourceBundle.getBundle("application", Locale.CHINA);

    public static String getUrl(InterfaceName name){
      String address=bundle.getString("test.url");
      String uri="";
      //testUri用来存放最后拼接好的地址
      String testUri;
      if(name==InterfaceName.LOGIN){
          uri=bundle.getString("login.url");
      }
      if(name==InterfaceName.GETUSERLIST){
          uri=bundle.getString("getUserList.url");
      }
      if(name==InterfaceName.GETUSERINFO){
          uri=bundle.getString("getUserInfo.url");
      }
      if(name==InterfaceName.UPDATEUSERINFO){
          uri=bundle.getString("updateUserInfo.url");
      }
      if(name==InterfaceName.ADDUSER){
          uri=bundle.getString("addUser.url");
      }
      testUri=address+uri;


      return testUri;

    }
}
