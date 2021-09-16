package com.star.bus.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.star.bus.pojo.Leavebill;
import com.star.bus.service.LeavebillService;
import com.star.bus.vo.LeavebillVo;
import com.star.common.utils.DataGridViewResult;
import com.star.common.utils.SystemConstant;
import com.star.sys.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Njq
 * @since 2021-09-16
 */
@RestController
@RequestMapping("/bus/leavebill")
public class LeavebillController {

    @Resource
    private LeavebillService leavebillService;

    /**
     * 请假单列表
     * @param leavebillVo
     * @return
     */
    @RequestMapping("/leavebillList")
    public DataGridViewResult leavebillList(LeavebillVo leavebillVo, HttpSession session){
        //获取当前登录用户
        User user=(User) session.getAttribute(SystemConstant.LOGINUSER);
        //请假人
        leavebillVo.setUserid(user.getId());
        //创建分页对象
        IPage<Leavebill> page=new Page<>(leavebillVo.getPage(),leavebillVo.getLimit());

        //调用分页查询的方法
        try {
            leavebillService.findLeaveBillByPage(page,leavebillVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataGridViewResult(page.getTotal(),page.getRecords());
    }

}

