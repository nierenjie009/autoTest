package com.course.server.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {

    private String url;
    private ResourceBundle bundle;
    private CookieStore store;


    @BeforeTest
    public void beforeTest() {
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        //从配置文件中拼接要测试的url
        String url = bundle.getString("getCookies.url");
        String testurl = this.url + url;

        //测试逻辑代码书写
        String result;
        HttpGet get = new HttpGet(testurl);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);

        //获取cookies信息
        store = client.getCookieStore();
        List<Cookie> cookieList = store.getCookies();
        for (Cookie cookie : cookieList) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie name=" + name + ";cookie value=" + value);
        }

    }

    @Test(dependsOnMethods = "testGetCookies")
    public void testPostMethod() throws IOException {
        String url = bundle.getString("getwithcookiess.url");
        //拼接最终的测试地址
        String testurl = this.url + url;

        //声明一个post对象
        HttpPost post = new HttpPost(testurl);

        //声明一个client对象
        DefaultHttpClient client = new DefaultHttpClient();

        //设置请求头信息
        post.setHeader("content-Type", "application/json");

        //添加参数
        JSONObject param = new JSONObject();
        param.put("name", "zhangsan");
        param.put("age", "22");
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);

        //设置cookies信息
        client.setCookieStore(store);

        //发送post请求
        HttpResponse response = client.execute(post);

        //使用result来存储结果
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);

        //处理结果，判断结果是否符合预期
        //将返回的响应结果字符串转化为json对象
        JSONObject resultJson = new JSONObject(result);

        //获取结果值
        String success = (String) resultJson.get("zhangsan");
        String status = (String) resultJson.get("status");
        //具体判断返回结果的值
        Assert.assertEquals("success", success);
        Assert.assertEquals("1", status);



    }

}

