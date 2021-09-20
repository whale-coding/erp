package com.star.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.star.bus.pojo.Goods;
import com.star.bus.pojo.Inport;
import com.star.bus.pojo.Outport;
import com.star.bus.pojo.Provider;
import com.star.bus.service.GoodsService;
import com.star.bus.service.OutportService;
import com.star.bus.service.ProviderService;
import com.star.bus.vo.InportVo;
import com.star.bus.vo.OutportVo;
import com.star.common.utils.DataGridViewResult;
import com.star.common.utils.JSONResult;
import com.star.common.utils.SystemConstant;
import com.star.sys.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/bus/outport")
public class OutportController {

    @Resource
    private OutportService outportService;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private GoodsService goodsService;

    /**
     * 商品退货查询
     */
    @RequestMapping("/outportList")
    public DataGridViewResult loadAllOutport(OutportVo outportVo) {
        IPage<Outport> page = new Page<>(outportVo.getPage(), outportVo.getLimit());
        QueryWrapper<Outport> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(outportVo.getProviderid()!=null&&outportVo.getProviderid()!=0,"providerid",outportVo.getProviderid());
        queryWrapper.eq(outportVo.getGoodsid()!=null&&outportVo.getGoodsid()!=0,"goodsid",outportVo.getGoodsid());
        queryWrapper.ge(outportVo.getStartTime()!=null, "outputtime", outportVo.getStartTime());
        queryWrapper.le(outportVo.getEndTime()!=null, "outputtime", outportVo.getEndTime());
        queryWrapper.like(StringUtils.isNotBlank(outportVo.getOperateperson()), "operateperson", outportVo.getOperateperson());
        queryWrapper.like(StringUtils.isNotBlank(outportVo.getRemark()), "remark", outportVo.getRemark());
        queryWrapper.orderByDesc("outputtime");
        this.outportService.page(page, queryWrapper);
        List<Outport> records = page.getRecords();
        for (Outport outport : records) {
            Provider provider = this.providerService.getById(outport.getProviderid());
            if(null!=provider) {
                outport.setProvidername(provider.getProvidername());
            }
            Goods goods = this.goodsService.getById(outport.getGoodsid());
            if(null!=goods) {
                outport.setGoodsname(goods.getGoodsname());
                outport.setSize(goods.getSize());
            }
        }
        return new DataGridViewResult(page.getTotal(),page.getRecords());
    }

    /**
     * 添加退货信息
     */
    @RequestMapping("/addOutport")
    public JSONResult addOutport(Integer id, Integer number, String remark) {
        try {
            this.outportService.addOutPort(id,number,remark);
            return SystemConstant.OPERATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return SystemConstant.OPERATE_ERROR;
        }
    }



    /**
     * 销售列表
     * @param outportVo
     * @return
     */
    @RequestMapping("/saleList")
    public DataGridViewResult saleList(OutportVo outportVo){
        //创建分页对象
        IPage<Outport> page = new Page<>(outportVo.getPage(),outportVo.getLimit());
        QueryWrapper<Outport> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(outportVo.getProviderid()!=null&&outportVo.getProviderid()!=0,"providerid",outportVo.getProviderid());
        queryWrapper.eq(outportVo.getGoodsid()!=null&&outportVo.getGoodsid()!=0,"goodsid",outportVo.getGoodsid());
        queryWrapper.ge(outportVo.getStartTime()!=null, "outputtime", outportVo.getStartTime());
        queryWrapper.le(outportVo.getEndTime()!=null, "outputtime", outportVo.getEndTime());
        queryWrapper.like(StringUtils.isNotBlank(outportVo.getOperateperson()), "operateperson", outportVo.getOperateperson());
        queryWrapper.like(StringUtils.isNotBlank(outportVo.getRemark()), "remark", outportVo.getRemark());
        queryWrapper.orderByDesc("outputtime");

        //调用分页查询的方法
        outportService.page(page,queryWrapper);

        List<Outport> records = page.getRecords();
        for (Outport outport : records) {
            Provider provider = this.providerService.getById(outport.getProviderid());
            if(null!=provider) {
                outport.setProvidername(provider.getProvidername());
            }
            Goods goods = goodsService.getById(outport.getGoodsid());
            if(null!=goods) {
                outport.setGoodsname(goods.getGoodsname());
                outport.setSize(goods.getSize());
            }
        }
        //返回数据
        return new DataGridViewResult(page.getTotal(),page.getRecords());
    }


    /**
     * 添加商品售货
     */
    @RequestMapping("/addSale")
    public JSONResult addSale(OutportVo outportVo, HttpSession session) {
        try {
            outportVo.setOutputtime(new Date());
            //获取当前登录用户
            User user=(User) session.getAttribute(SystemConstant.LOGINUSER);
            outportVo.setOperateperson(user.getName());
            outportService.save(outportVo);
            return SystemConstant.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return SystemConstant.ADD_ERROR;
        }
    }


    /**
     * 修改商品售货
     */
    @RequestMapping("/updateSale")
    public JSONResult updateSale(OutportVo outportVo) {
        try {
            outportService.updateById(outportVo);
            return SystemConstant.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return SystemConstant.UPDATE_ERROR;
        }
    }


    /**
     * 删除商品售货
     */
    @RequestMapping("/deleteSale")
    public JSONResult deleteSale(Integer id) {
        try {
            outportService.removeById(id);
            return SystemConstant.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return SystemConstant.DELETE_ERROR;
        }
    }

}

