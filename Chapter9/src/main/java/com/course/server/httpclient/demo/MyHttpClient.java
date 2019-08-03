package com.course.server.httpclient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyHttpClient {

    @Test
    public void test() throws IOException {
        String result;//result用来存放结果的
        HttpGet get=new HttpGet("http://www.baidu.com");
        HttpClient client=new DefaultHttpClient();
        //client对象是用来执行get对象的
        HttpResponse response=client.execute(get);
        result= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
    }

    @Test
    public void test1() throws IOException {
        String result;//result用来存放结果的
        HttpGet get=new HttpGet("localhost:8080//getwithcookies");
        HttpClient client=new DefaultHttpClient();//client对象是用来执行get对象的
        //设置cookies
        JSONObject param=new JSONObject();
        HttpResponse response=client.execute(get);
        result= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
    }
}
