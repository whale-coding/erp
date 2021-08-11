package com.star.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 该控制器的作用是跳转页面路径
 */
@Controller
@RequestMapping("/sys")
public class SystemController {
    /**
     * 去到后台首页
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(){
        return "system/home/index";
    }

    /**
     * 跳转到桌面工作台
     * @return
     */
    @RequestMapping("/desktop")
    public String desktop() {
        return "system/home/desktopManager";
    }

    /**
     * 跳转到日志管理页面
     * @return
     */
    @RequestMapping("/toLogManager")
    public String toLogManager() {
        return "system/log/logManager";
    }

    /**
     * 跳转到公告管理页面
     * @return
     */
    @RequestMapping("/toNoticeManager")
    public String toNoticeManager() {
        return "system/notice/noticeManager";
    }

    /**
     * 去到部门管理页面-left左边树节点
     * @return
     */
    @RequestMapping("/toDeptLeft")
    public String toDeptLeft(){
        return "system/dept/left";
    }

    /**
     * 去到部门管理页面-right右边
     * @return
     */
    @RequestMapping("/toDeptRight")
    public String toDeptRight(){
        return "system/dept/right";
    }

    /**
     * 去到部门管理页面
     * @return
     */
    @RequestMapping("/toDeptManager")
    public String toDeptManager(){
        return "system/dept/deptManager";
    }

}
