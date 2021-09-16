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
@RequestMapping("/bus/leavebillcheck")
public class LeavebillCheckController {
    @Resource
    private LeavebillService leavebillService;


    /**
     * 我的待审批列表
     * @param leavebillVo
     * @return
     */
    @RequestMapping("/unCheckLeavebillList")
    public DataGridViewResult unCheckLeavebillList(LeavebillVo leavebillVo, HttpSession session){
        //获取当前登录用户
        User user=(User) session.getAttribute(SystemConstant.LOGINUSER);
        //审批人
        leavebillVo.setCheckPerson(user.getId());
        //审批
        leavebillVo.setState(1);

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

