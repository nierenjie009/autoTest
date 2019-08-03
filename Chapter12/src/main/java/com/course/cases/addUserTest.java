package com.course.cases;

import com.course.config.testConfig;
import com.course.utils.dataBaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class addUserCase {

    @Test(dependsOnGroups = "loginTrue",description = "添加用户接口测试")
    public void addUser() throws IOException {
        SqlSession sqlSession= dataBaseUtil.getSqlSession();
        addUserCase addUserCase=sqlSession.selectOne("addUserCase",1);
        System.out.println(addUserCase.toString());
        System.out.println(testConfig.addUserUrl);


    }
}
