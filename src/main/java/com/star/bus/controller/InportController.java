package com.star.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.star.bus.pojo.Goods;
import com.star.bus.pojo.GoodsType;
import com.star.bus.pojo.Inport;
import com.star.bus.pojo.Provider;
import com.star.bus.service.GoodsService;

import com.star.bus.service.InportService;
import com.star.bus.service.ProviderService;
import com.star.bus.vo.InportVo;
import com.star.common.utils.DataGridViewResult;
import com.star.common.utils.JSONResult;
import com.star.common.utils.SystemConstant;
import com.star.sys.pojo.User;
import org.apache.commons.lang3.StringUtils;
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
 * @since 2021-09-17
 */
@RestController
@RequestMapping("/bus/inport")
public class InportController {
    @Resource
    private InportService inportService;

    @Resource
    private GoodsService goodsService;

    @Resource
    private ProviderService providerService;


    /**
     * 进货列表
     * @param inportVo
     * @return
     */
    @RequestMapping("/inportList")
    public DataGridViewResult inportList(InportVo inportVo){
        //创建分页对象
        IPage<Inport> page = new Page<>(inportVo.getPage(), inportVo.getLimit());
        QueryWrapper<Inport> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(inportVo.getProviderid()!=null&&inportVo.getProviderid()!=0,"providerid",inportVo.getProviderid());
        queryWrapper.eq(inportVo.getGoodsid()!=null&&inportVo.getGoodsid()!=0,"goodsid",inportVo.getGoodsid());
        queryWrapper.ge(inportVo.getStartTime()!=null, "inporttime", inportVo.getStartTime());
        queryWrapper.le(inportVo.getEndTime()!=null, "inporttime", inportVo.getEndTime());
        queryWrapper.like(StringUtils.isNotBlank(inportVo.getOperateperson()), "operateperson", inportVo.getOperateperson());
        queryWrapper.like(StringUtils.isNotBlank(inportVo.getRemark()), "remark", inportVo.getRemark());
        queryWrapper.orderByDesc("inporttime");

        //调用分页查询的方法
        inportService.page(page,queryWrapper);

        List<Inport> records = page.getRecords();
        for (Inport inport : records) {
            Provider provider = this.providerService.getById(inport.getProviderid());
            if(null!=provider) {
                inport.setProvidername(provider.getProvidername());
            }
            Goods goods = goodsService.getById(inport.getGoodsid());
            if(null!=goods) {
                inport.setGoodsname(goods.getGoodsname());
                inport.setSize(goods.getSize());
            }
        }
        //返回数据
        return new DataGridViewResult(page.getTotal(),page.getRecords());
    }


    /**
     * 添加商品进货
     */
    @RequestMapping("/addInport")
    public JSONResult addInport(InportVo inportVo, HttpSession session) {
        try {
            inportVo.setInporttime(new Date());
            //获取当前登录用户
            User user=(User) session.getAttribute(SystemConstant.LOGINUSER);
            inportVo.setOperateperson(user.getName());
            inportService.save(inportVo);
            return SystemConstant.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return SystemConstant.ADD_ERROR;
        }
    }


    /**
     * 修改商品进货
     */
    @RequestMapping("/updateInport")
    public JSONResult updateInport(InportVo inportVo) {
        try {
            inportService.updateById(inportVo);
            return SystemConstant.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return SystemConstant.UPDATE_ERROR;
        }
    }






}

