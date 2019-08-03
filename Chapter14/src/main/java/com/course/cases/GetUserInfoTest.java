package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserInfoCase;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetUserInfoTest {

    @Test(dependsOnGroups = "logintrue",description = "获取userId为1的用户信息")
    public void getUserInfo() throws IOException {
        SqlSession session= DatabaseUtil.getSqlSession();
        GetUserInfoCase getUserInfoCase=session.selectOne("getUserInfoCase",1);
        System.out.println(getUserInfoCase.toString());
        System.out.println(TestConfig.getUserInfoUrl);

        //发送请求，获取结果
        JSONArray resultJson=getResultJson(getUserInfoCase);

        //验证返回的结果
        User user=session.selectOne(getUserInfoCase.getExpected(),getUserInfoCase);

        List<User> list= new ArrayList<User>();
        list.add(user);
        JSONArray expectJson=new JSONArray(list);
        JSONArray jsonArray=new JSONArray(resultJson.getString(0));
        Assert.assertEquals(jsonArray.toString(),expectJson.toString());






    }

    private JSONArray getResultJson(GetUserInfoCase getUserInfoCase) throws IOException {

        HttpPost httpPost=new HttpPost(TestConfig.getUserInfoUrl);
        httpPost.setHeader("content-Type","application/json");
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        JSONObject param=new JSONObject();
        param.put("id",getUserInfoCase.getUserId());
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        httpPost.setEntity(entity);
        HttpResponse response=TestConfig.defaultHttpClient.execute(httpPost);
        String result= EntityUtils.toString(response.getEntity(),"utf-8");
//        JSONArray resultJson=new JSONArray(result); 这个真的不可以用吗？
        List resultList= Arrays.asList(result);
        JSONArray resultJson=new JSONArray(resultList);
        System.out.println(resultJson.getString(0));

        return resultJson;
    }

}
