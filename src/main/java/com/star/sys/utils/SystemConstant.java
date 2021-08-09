package com.star.sys.utils;

/**
 * 系统常量接口
 */
public interface SystemConstant {
    /**
     * 当前登录用户的key
     */
    String LOGINUSER = "loginUser";


    /**
     * 登录成功
     */
    JSONResult LOGIN_SUCCESS = new JSONResult(true,"登录成功");
    /**
     * 登录失败，用户名或密码错误
     */
    JSONResult LOGIN_ERROR_PASS = new JSONResult(false,"登录失败,用户名或密码错误!");
}
