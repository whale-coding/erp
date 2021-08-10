package com.star.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.star.sys.pojo.Log;
import com.star.sys.service.LogService;
import com.star.sys.utils.DataGridViewResult;
import com.star.sys.utils.JSONResult;
import com.star.sys.utils.SystemConstant;
import com.star.sys.vo.LogVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;

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

    /**
     * 查询日志列表
     * @param logVo
     * @return
     */
    @RequestMapping("/loglist")
    public DataGridViewResult  loglist(LogVo logVo){
        //创建分页信息对象，参数1：当前页码  参数2：每页显示条数
        IPage<Log> page=new Page<>(logVo.getPage(),logVo.getLimit());

        //创建条件构造器
        QueryWrapper<Log> wrapper = new QueryWrapper<>();
        /**
         * 参数1：条件  参数2：数据库列名  参数3：值
         */
        //操作类型
        wrapper.eq(StringUtils.isNotBlank(logVo.getType()),"type",logVo.getType());
        //登录名模糊查询
        wrapper.like(StringUtils.isNotBlank(logVo.getLoginname()),"loginname",logVo.getLoginname());
        //开始时间
        wrapper.ge(logVo.getStartTime()!=null,"createtime",logVo.getStartTime());
        //结束时间
        wrapper.le(logVo.getEndTime()!=null,"createtime",logVo.getEndTime());
        //设置排序
        wrapper.orderByDesc("createtime");//登录时间降序

        //调用查询日志列表的方法
        IPage<Log> logIPage = logService.page(page, wrapper);
        //返回数据(返回总记录数和数据)
        return new DataGridViewResult(logIPage.getTotal(),logIPage.getRecords());
    }

    @RequestMapping("/batchDelete")
    public JSONResult batchDelete(String ids){
        //将字符串拆分成数组
        String[] idsStr=ids.split(",");
        //判断是否删除成功
        if (logService.removeByIds(Arrays.asList(idsStr))){  //将字符数组转换为List集合作为参数
            //删除成功
            return SystemConstant.DELETE_SUCCESS;
        }else {
            //删除失败
            return SystemConstant.DELETE_ERROR;
        }
    }


}

