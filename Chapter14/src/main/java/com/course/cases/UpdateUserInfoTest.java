package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.UpdateUserInfoCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateUserInfoTest {

    @Test(dependsOnGroups = "logintrue", description = "将userId为2的用户的用户名改为haha")
    public void updateUserInfo() throws IOException, InterruptedException {

        SqlSession session = DatabaseUtil.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase = session.selectOne("updateUserInfoCase", 1);
        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestConfig.updateUserInfoUrl);

        //发送请求，获取结果
        int i = getResult(updateUserInfoCase);
        Thread.sleep(3000);
        //验证结果
        User user = session.selectOne(updateUserInfoCase.getExpected(), updateUserInfoCase);
        Assert.assertNotNull(i);
        Assert.assertNotNull(user);
    }


    @Test(dependsOnGroups = "logintrue", description = "将userId为8的用户信息删除")
    public void deleteUserInfo() throws IOException, InterruptedException {

        SqlSession session = DatabaseUtil.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase = session.selectOne("updateUserInfoCase", 2);
        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestConfig.updateUserInfoUrl);

        //发送请求，获取结果
        int i = getResult(updateUserInfoCase);
        Thread.sleep(3000);
        //验证结果
        User user = session.selectOne(updateUserInfoCase.getExpected(), updateUserInfoCase);
        Assert.assertNotNull(i);
        Assert.assertNotNull(user);
    }

    private int getResult(UpdateUserInfoCase updateUserInfoCase) throws IOException {

        HttpPost httpPost=new HttpPost(TestConfig.updateUserInfoUrl);
        httpPost.setHeader("content-Type","application/json");
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        JSONObject param=new JSONObject();
        param.put("id",updateUserInfoCase.getUserId());
        param.put("userName",updateUserInfoCase.getUserName());
        param.put("sex",updateUserInfoCase.getSex());
        param.put("age",updateUserInfoCase.getAge());
        param.put("permission",updateUserInfoCase.getPermission());
        param.put("isDelete",updateUserInfoCase.getIsDelete());
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        httpPost.setEntity(entity);
        HttpResponse response=TestConfig.defaultHttpClient.execute(httpPost);
        String result= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        return Integer.parseInt(result);
    }
}
