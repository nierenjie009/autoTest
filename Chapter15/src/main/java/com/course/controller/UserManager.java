package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.mybatis.spring.SqlSessionTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
@Slf4j
@RestController
@Api(value="/v1",description ="用户管理系统")
@RequestMapping(value = "/v1")
public class UserManager {

    @Autowired
    private SqlSessionTemplate template;

    @ApiOperation(value="登录接口",httpMethod="POST")
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public boolean login(HttpServletResponse response, @RequestBody User user){
        int i=template.selectOne("login",user);
        Cookie cookie=new Cookie("login","true");
        response.addCookie(cookie);
        log.info("查询到的结果是:"+i);
        if(i==1){
            log.info("登录的用户是:"+user.getUserName());
            return true;
        }
            return false;
    }
    @ApiOperation(value="新增用户接口",httpMethod = "POST")
    @RequestMapping(value="/addUser",method=RequestMethod.POST)
    public boolean addUser(HttpServletRequest request, @RequestBody User user){
         Boolean x=verifyCookies(request);
         if(x==true){
             int result=template.insert("addUser",user);
             if(result>0){
                 log.info("添加用户的数量是:"+result);
                 return true;
             }
                 return false;
         }
          return false;

    }

    @ApiOperation(value="获取用户列表接口",httpMethod="POST")
    @RequestMapping(value="/getUserInfo",method = RequestMethod.POST)
    public List<User> getUserList(HttpServletRequest request,@RequestBody User user){
      Boolean x=verifyCookies(request);
      if(x==true){
          List<User> list=template.selectList("getUserInfo",user);
          if(list.size()==0){
              System.out.println("hehe");
              return null;
          }else{
              log.info("获取到的用户数量是:"+list.size());
              return list;
          }
      }else{
          System.out.println("heihei");
          return null;
      }

    }
    @ApiOperation(value="更新用户/删除用户接口",httpMethod = "POST")
    @RequestMapping(value="/updateUserInfo",method = RequestMethod.POST)
    public int updateUser(HttpServletRequest request,@RequestBody User user){
        boolean x=verifyCookies(request);
        int i=0;
        if(x==true){
            i=template.update("updateUserInfo",user);
            if(i!=0){
                log.info("更新的数据条数为:"+i+"条");
                return i;
            }
                return i;
        }
                return i;

    }

    private Boolean verifyCookies(HttpServletRequest request) {
        Cookie[] cookies=request.getCookies();
        if(Objects.isNull(cookies)){
            log.info("cookie为空");
            return false;
        }
        for(Cookie c:cookies){
            if(c.getName().equals("login")&&c.getValue().equals("true")){
                log.info("cookie验证通过");
                return true;
            }
        }
            return  false;
    }
}
