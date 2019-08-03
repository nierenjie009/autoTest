package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//使用SpringBoot开发get方法接口
@RestController
@Api(value="/",description = "这是我的全部get方法")
public class MyGetMethod {
    //返回cookies信息的get接口开发
    @RequestMapping(value = "/getCookies", method = RequestMethod.GET)
    @ApiOperation(value="通过这个方法可以获取到Cookies信息",httpMethod="Get")
    public String getCookies(HttpServletResponse response) {
        //HttpServletRequest 装请求信息的类
        //HttpServletResponse装响应信息的类
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        return "恭喜你获得cookies信息成功";
    }

    /**
     * 这是一个需要携带cookies信息才能访问的get请求
     */
    //需要携带cookies信息才能访问的的get接口开发
    @RequestMapping(value = "/getwithcookies", method = RequestMethod.GET)
    @ApiOperation(value="要求客户端携带cookies信息访问",httpMethod="Get")
    public String getWithCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {   //判断返回的对象内容是否为空,这个方法是非常保险的，要学会用
            return "需要携带cookies信息才能访问";
        }
        for (Cookie cookie : cookies) {
            if ("login".equals(cookie.getName()) && "true".equals(cookie.getValue())) {
                return "恭喜你访问成功";
            }
        }
        return "需要携带正确的cookies信息才能访问";

    }

    /**
     * 开发一个需要携带参数才能访问的get请求
     * 第一种实现方式 url:key=value&&key=value
     * 模拟获取商品列表
     */
    //需要携带参数才能访问的的get接口开发
    @RequestMapping(value = "/getListWithParams", method = RequestMethod.GET)
    @ApiOperation(value="需要携带参数才能访问的get请求方法一",httpMethod = "Get")
    public Map<String, Integer> getList1(@RequestParam Integer start,
                                         @RequestParam Integer end) {

        Map<String, Integer> myList = new HashMap<String, Integer>();
        myList.put("鞋子", 400);
        myList.put("腰带", 200);
        myList.put("衬衫", 300);

        return myList;

    }

    /**
     * 第二种需要携带参数才能访问的get请求
     * url:ip/port/path/10/20
     */
    @RequestMapping(value = "/getListWithParamss/{start}/{end}", method = RequestMethod.GET)
    @ApiOperation(value="需要携带参数才能访问的get请求方法二",httpMethod = "Get")
    public Map<String, Integer> getList2(@PathVariable Integer start,
                                         @PathVariable Integer end) {
        Map<String, Integer> myList = new HashMap<String, Integer>();
        myList.put("鞋子", 400);
        myList.put("腰带", 200);
        myList.put("衬衫", 300);

        return myList;
    }

}

