package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.LoginCase;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {

    @BeforeTest(groups = "logintrue",description = "登录前的测试准备工作,比如获取要测试的接口地址,获取HttpClient对象")
    public void beforeTest(){
        TestConfig.loginUrl= ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.addUserUrl=ConfigFile.getUrl(InterfaceName.ADDUSER);
        TestConfig.getUserInfoUrl=ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl=ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.updateUserInfoUrl=ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);

        TestConfig.defaultHttpClient=new DefaultHttpClient();


    }

    @Test(groups = "logintrue",description = "登录成功接口测试")
    public void loginTrue() throws IOException {

        SqlSession session= DatabaseUtil.getSqlSession();
        LoginCase loginCase=session.selectOne("loginCase",1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);

        //第一步，发送请求,获取结果
        String result=getResult(loginCase);
        //第二步，验证返回结果
        Assert.assertEquals(result,loginCase.getExpected());
    }

    private String getResult(LoginCase loginCase) throws IOException {
        //创建Post对象
        HttpPost httpPost=new HttpPost(TestConfig.loginUrl);
        //设置请求头
        httpPost.setHeader("content-Type","application/json");
        //设置请求体
        JSONObject param=new JSONObject();
        param.put("userName",loginCase.getUserName());
        param.put("password",loginCase.getPassword());
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        httpPost.setEntity(entity);
        //发送请求对象,获取响应对象
        HttpResponse response=TestConfig.defaultHttpClient.execute(httpPost);
        //存放cookie,这一步非常关键，必不可少
        TestConfig.store=TestConfig.defaultHttpClient.getCookieStore();
        //定义一个result用来存放返回结果
        String result= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);


        return result;


    }

    @Test(groups="loginfalse",description = "登录失败接口测试")
    public void loginFalse() throws IOException {
        SqlSession session=DatabaseUtil.getSqlSession();
        LoginCase loginCase=session.selectOne("loginCase",2);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);

        //第一步，发送请求,获取结果
        String result=getResult(loginCase);
        //第二步，验证返回结果
        Assert.assertEquals(result,loginCase.getExpected());

    }
}
