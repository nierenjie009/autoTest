package com.course.server.httpclient.cookies;


import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {

   private String url;
   private ResourceBundle bundle;
   private CookieStore store;


   @BeforeTest
   public void beforeTest(){
       bundle=ResourceBundle.getBundle("application", Locale.CHINA);
       url=bundle.getString("test.url");
   }

   @Test
   public void testGetCookies() throws IOException {
       //从配置文件中拼接要测试的url
       String url=bundle.getString("getCookies.url");
       String testurl=this.url+url;

      //测试逻辑代码书写
       String result;
       HttpGet get=new HttpGet(testurl);
       DefaultHttpClient client=new DefaultHttpClient();
       HttpResponse response=client.execute(get);
       result= EntityUtils.toString(response.getEntity(),"utf-8");
       System.out.println(result);

       //获取cookies信息
       store=client.getCookieStore();
       List<Cookie> cookieList=store.getCookies();
       for (Cookie cookie: cookieList) {
           String name=cookie.getName();
           String value=cookie.getValue();
           System.out.println("cookie name="+name+";cookie value="+value);
       }

   }
   @Test(dependsOnMethods = "testGetCookies")
   public void testGetWithCookies() throws IOException {
       String url=bundle.getString("getwithcookies.url");
       String testurl=this.url+url;


       HttpGet get=new HttpGet(testurl);
       DefaultHttpClient client=new DefaultHttpClient();
       //设置cookies信息
       client.setCookieStore(store);
       HttpResponse response=client.execute(get);
       String result=EntityUtils.toString(response.getEntity(),"utf-8");
       int statuscode=response.getStatusLine().getStatusCode();
       if(statuscode==200){
           System.out.println("statuscode = "+statuscode);
           System.out.println(result);
       }

   }
}
