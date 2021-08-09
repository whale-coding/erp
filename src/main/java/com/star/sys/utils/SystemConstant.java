package com.star.sys.utils;

/**
 * 系统常量接口
 */
public interface SystemConstant {


//===========================================跟登录有关的常量=======================================================
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


// =========================================跟菜单树节点有关的常量=============================================

    /**
     * 类型为菜单：用于首页左侧导航栏
     */
    String TYPE_MENU = "menu";

    /**
     * 类型为权限
     */
    String TYPE_PERMISSION ="permission" ;

    /**
     * 菜单是否展开，1展开
     */
    Integer OPEN_TRUE = 1;

    /**
     * 菜单是否展开，0不展开
     */
    Integer OPEN_FALSE = 0;

    /**
     * 角色为超级管理员
     */
    Integer SUPERUSER = 0;


//==========================================================================================================



}
