package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserListCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetUserListTest {

    @Test(dependsOnGroups = "logintrue",description = "获取性别为男的用户列表")
    public void getUserList() throws IOException {
        SqlSession session= DatabaseUtil.getSqlSession();
        GetUserListCase getUserListCase=session.selectOne("getUserListCase",1);
        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getUserListUrl);

        //发送请求获取结果
        JSONArray resultJson=getResultJson(getUserListCase);

        //验证结果
        List<User> list=session.selectList(getUserListCase.getExpected(),getUserListCase);
        JSONArray expectJson=new JSONArray(list);
        Assert.assertEquals(resultJson.length(),expectJson.length());

        for(int i=0;i<resultJson.length();i++){
            JSONObject actual= (JSONObject) resultJson.get(i);
            JSONObject expect= (JSONObject) expectJson.get(i);
            Assert.assertEquals(actual.toString(),expect.toString());


        }


    }

    private JSONArray getResultJson(GetUserListCase getUserListCase) throws IOException {
        //创建Post对象
        HttpPost httpPost=new HttpPost(TestConfig.getUserListUrl);
        //设置请求头
        httpPost.setHeader("content-Type","application/json");
        //设置cookie
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        //设置请求体
        JSONObject param=new JSONObject();
        param.put("userName",getUserListCase.getUserName());
        param.put("age",getUserListCase.getAge());
        param.put("sex",getUserListCase.getSex());
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        httpPost.setEntity(entity);
        //发送请求对象，获取响应对象
        HttpResponse response=TestConfig.defaultHttpClient.execute(httpPost);
        //定义result用来存放返回的结果
        String result= EntityUtils.toString(response.getEntity(),"utf-8");
        JSONArray resultJson=new JSONArray(result);


        return resultJson;
    }
}
