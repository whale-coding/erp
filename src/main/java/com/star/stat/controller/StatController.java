package com.star.stat.controller;

import com.star.stat.domain.BaseEntity;
import com.star.stat.service.StatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 跳转到进货员年度统计页面
     */
    @RequestMapping("/toOperatepersonpersonYearGradeStat")
    public String toOperatepersonpersonYearGradeStat() {
        return "statistics/operatepersonYearGradeStat";
    }

    /**
     * 加载业务员年度统计数据
     */
    @RequestMapping("/loadOperatePersonYearGradeStat")
    @ResponseBody
    public Map<String,Object> loadOperatePersonYearGradeStat(String year){

        List<BaseEntity> entities=statService.queryOperatePersonYearGradeStatList(year);

        Map<String,Object> map=new HashMap<>();
        List<String> names=new ArrayList<>();
        List<Integer> values=new ArrayList<>();
        for (BaseEntity baseEntity : entities) {
            names.add(baseEntity.getName());
            values.add(baseEntity.getValue());
        }
        map.put("name", names);
        map.put("value", values);
        return map;
    }


    /**
     * 跳转到公司年度统计页面
     */
    @RequestMapping("/toCompanyYearGradeStat")
    public String toCompanyYearGradeStat() {
        return "statistics/companyYearGradeStat";
    }

    /**
     * 加载公司年度统计数据
     */
    @RequestMapping("/loadCompanyYearGradeStat")
    @ResponseBody
    public List<Integer> loadCompanyYearGradeStat(String year){
        List<Integer> entities=statService.loadCompanyYearGradeStatList(year);
        for (int i = 0; i < entities.size(); i++) {
            if(null==entities.get(i)) {
                entities.set(i, 0);
            }
        }
        return entities;
    }


}
