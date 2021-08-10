package com.star.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.star.sys.pojo.Log;
import com.star.sys.service.LogService;
import com.star.sys.utils.DataGridViewResult;
import com.star.sys.vo.LogVo;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Njq
 * @since 2021-08-09
 */
@RestController
@RequestMapping("/sys/log")
public class LogController {

    @Resource
    private LogService logService;

    @RequestMapping("/loglist")
    public DataGridViewResult  loglist(LogVo logVo){
        //创建分页信息对象，参数1：当前页码  参数2：每页显示条数
        IPage<Log> page=new Page<>(logVo.getPage(),logVo.getLimit());

        //创建条件构造器
        QueryWrapper<Log> wrapper = new QueryWrapper<>();

        //调用查询日志列表的方法
        IPage<Log> logIPage = logService.page(page, wrapper);
        //返回数据(返回总记录数和数据)
        return new DataGridViewResult(logIPage.getTotal(),logIPage.getRecords());
    }

}

