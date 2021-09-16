package com.star.bus.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.star.bus.pojo.Leavebill;

import com.star.bus.pojo.LeavebillCheck;
import com.star.bus.service.LeavebillCheckService;
import com.star.bus.service.LeavebillService;
import com.star.bus.vo.LeavebillVo;
import com.star.common.utils.DataGridViewResult;
import com.star.common.utils.JSONResult;
import com.star.common.utils.SystemConstant;
import com.star.sys.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

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
    @Resource
    private LeavebillCheckService leavebillCheckService;

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


    /**
     * 审批请假单
     * @param leavebillCheck
     * @return
     */
    @RequestMapping("/checkLeaveBill")
    public JSONResult checkLeaveBill(LeavebillCheck leavebillCheck,HttpSession session){

        //获取当前登录用户
        User user=(User) session.getAttribute(SystemConstant.LOGINUSER);
        //设置审批人
        leavebillCheck.setCheckUserId(user.getId());
        //设置审批时间
        leavebillCheck.setCheckTime(new Date());

        //保存审批
        if (leavebillCheckService.save(leavebillCheck)){
            return SystemConstant.ADD_SUCCESS;
        }
        return SystemConstant.ADD_ERROR;
    }


    /**
     * 查看个人请假单
     * @param id
     * @return
     */
    @RequestMapping("/showchecklist")
    public DataGridViewResult showchecklist(Integer id){

        //调用"根据请假单id查询该请假的送审信息"的方法
        try {
            List<LeavebillCheck> leavebillCheckList = leavebillCheckService.findLeaveBillCheckListByLeaveBillId(id);
            return new DataGridViewResult(leavebillCheckList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

