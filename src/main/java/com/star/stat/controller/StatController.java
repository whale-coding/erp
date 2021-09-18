package com.star.stat.controller;

import com.star.stat.domain.BaseEntity;
import com.star.stat.service.StatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 统计分析
 */
@Controller
@RequestMapping("/stat")
public class StatController {

    @Resource
    private StatService statService;

    /**
     * 跳转到客户地区统计页面
     */
    @RequestMapping("/toCustomerAreaStat")
    public String toCustomerAreaStat() {
        return "statistics/customerAreaStat";
    }

    /**
     * 加载客户地区数据
     */
    @RequestMapping("/loadCustomerAreaStatJosn")
    @ResponseBody
    public List<BaseEntity> loadCustomerAreaStatJosn(){
        return statService.loadCustomerAreaStatList();
    }




}
