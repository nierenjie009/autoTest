package com.course.config;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;

public class TestConfig {
    //用来存放接口的url
    public static String loginUrl;
    public static String getUserListUrl;
    public static String getUserInfoUrl;
    public static String addUserUrl;
    public static String updateUserInfoUrl;
    //用来存放httpClient对象
    public static DefaultHttpClient defaultHttpClient;
    //用来存放cookies
    public static CookieStore store;
}
