package com.star.bus.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.star.bus.pojo.Customer;
import com.star.bus.pojo.Leavebill;
import com.star.bus.service.LeavebillCheckService;
import com.star.bus.service.LeavebillService;
import com.star.bus.vo.LeavebillVo;
import com.star.common.utils.DataGridViewResult;
import com.star.common.utils.JSONResult;
import com.star.common.utils.SystemConstant;
import com.star.sys.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;

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

    @Resource
    private LeavebillCheckService leavebillCheckService;

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


    /**
     * 添加请假单
     * @param leavebill
     * @return
     */
    @RequestMapping("/addLeaveBill")
    public JSONResult addLeaveBill(Leavebill leavebill,HttpSession session){
        //获取当前登录用户
        User user=(User) session.getAttribute(SystemConstant.LOGINUSER);
        //设置请假人
        leavebill.setUserid(user.getId());
        //设置审批人
        leavebill.setCheckPerson(user.getMgr());
        //设置请假时间
        leavebill.setCreatetime(new Date());
        //设置请假状态
        if (leavebill.getState()==0){
            leavebill.setState(0);   //新创建
        }else if (leavebill.getState()==1){
            leavebill.setState(1);   //已提交，待审批
            leavebill.setCommittime(new Date());  //请假单提交时间
        }

        //保存请假单
        if (leavebillService.save(leavebill)){
            return SystemConstant.ADD_SUCCESS;
        }
        return SystemConstant.ADD_ERROR;
    }

    /**
     * 修改请假单
     * @param leavebill
     * @return
     */
    @RequestMapping("/updateLeaveBill")
    public JSONResult updateLeaveBill(Leavebill leavebill){
        //设置请假状态
        if (leavebill.getState()==0){
            leavebill.setState(0);   //新创建
        }else if (leavebill.getState()==1){
            leavebill.setState(1);   //已提交，待审批
            leavebill.setCommittime(new Date());  //请假单提交时间
        }

        //修改请假单
        if (leavebillService.updateById(leavebill)){
            return SystemConstant.ADD_SUCCESS;
        }
        return SystemConstant.ADD_ERROR;
    }

    /**
     * 批量删除请假单
     * @param ids
     * @return
     */
    @RequestMapping("/batchDelete")
    public JSONResult batchDelete(String ids){
        //将字符串拆分成数组
        String[] idsStr=ids.split(",");
        //判断是否删除成功
        if (leavebillService.removeByIds(Arrays.asList(idsStr))){  //将字符数组转换为List集合作为参数
            //删除成功
            return SystemConstant.DELETE_SUCCESS;
        }else {
            //删除失败
            return SystemConstant.DELETE_ERROR;
        }
    }


}

