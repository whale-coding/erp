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
}
