package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.AddUserCase;
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
import java.sql.SQLOutput;

public class AddUserTest {

     @Test(dependsOnGroups = "logintrue",description = "添加用户")
     public void addUser() throws IOException {
         SqlSession session= DatabaseUtil.getSqlSession();
         AddUserCase addUserCase=session.selectOne("addUserCase",1);
         System.out.println(addUserCase.toString());
         System.out.println(TestConfig.addUserUrl);


         //发请求，获取结果
         String result=getResult(addUserCase);

         //验证返回结果

//         User user=session.selectOne("addUser",addUserCase);
//
//         System.out.println(user.toString());
         Assert.assertEquals(result,addUserCase.getExpected());



     }
    //在getResult里面发请求
    private String getResult(AddUserCase addUserCase) throws IOException {
        //创建一个Post对象
        HttpPost httpPost=new HttpPost(TestConfig.addUserUrl);

        //设置请求头
        httpPost.setHeader("content-type","application/json");

        //设置cookies
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);


        //设置请求体
        JSONObject param=new JSONObject();
        param.put("userName",addUserCase.getUserName());
        param.put("password",addUserCase.getPassword());
        param.put("sex",addUserCase.getSex());
        param.put("age",addUserCase.getAge());
        param.put("permission",addUserCase.getPermission());
        param.put("isDelete",addUserCase.getIsDelete());


        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        httpPost.setEntity(entity);
        //发送Post请求对象，获得Response响应对象
        HttpResponse response=TestConfig.defaultHttpClient.execute(httpPost);
        //定义一个result用来存放返回结果
        String result= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        return result;
    }
}
