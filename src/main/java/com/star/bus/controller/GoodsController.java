package com.star.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.star.bus.pojo.Customer;
import com.star.bus.pojo.Goods;
import com.star.bus.pojo.GoodsType;
import com.star.bus.pojo.Provider;
import com.star.bus.service.GoodsService;
import com.star.bus.service.GoodsTypeService;
import com.star.bus.service.ProviderService;
import com.star.bus.vo.GoodsVo;
import com.star.common.utils.DataGridViewResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
@RequestMapping("/bus/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @Resource
    private ProviderService providerService;

    @Resource
    private GoodsTypeService goodsTypeService;

    /**
     * 商品列表
     * @param goodsVo
     * @return
     */
    @RequestMapping("/goodsList")
    public DataGridViewResult customerList(GoodsVo goodsVo){
        //创建分页对象
        IPage<Goods> page=new Page<>(goodsVo.getPage(),goodsVo.getLimit());
        //创建条件构造器
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        //供应商
        wrapper.eq(goodsVo.getProviderid()!=null&&goodsVo.getProviderid()!=0,"providerid",goodsVo.getProviderid());
        //商品分类
        wrapper.eq(goodsVo.getTypeid()!=null&&goodsVo.getTypeid()!=0,"typeid",goodsVo.getTypeid());
        //商品名称
        wrapper.like(StringUtils.isNotBlank(goodsVo.getGoodsname()), "goodsname", goodsVo.getGoodsname());
        //生产批号
        wrapper.like(StringUtils.isNotBlank(goodsVo.getProductcode()), "productcode", goodsVo.getProductcode());
        //批准文号
        wrapper.like(StringUtils.isNotBlank(goodsVo.getPromitcode()), "promitcode", goodsVo.getPromitcode());
        //商品描述
        wrapper.like(StringUtils.isNotBlank(goodsVo.getDescription()), "description", goodsVo.getDescription());
        //商品规格
        wrapper.like(StringUtils.isNotBlank(goodsVo.getSize()), "size", goodsVo.getSize());

        //调用分页查询的方法
        goodsService.page(page,wrapper);

        List<Goods> records = page.getRecords();
        //遍历查询的结果
        for (Goods goods : records) {
            Provider provider = providerService.getById(goods.getProviderid()); //查询供应商名称
            if(null!=provider) {
                goods.setProviderName(provider.getProvidername());  //将供应商名称赋值给表格属性列
            }
            GoodsType goodsType = goodsTypeService.getById(goods.getTypeid());   //查询商品分类名称
            if(null!=goodsType&&goodsType.getId()!=1) {
                goods.setGoodsTypeName(goodsType.getTitle());  //将商品分类名称赋值给表格属性列
            }

        }
        //返回数据
        return new DataGridViewResult(page.getTotal(),page.getRecords());
    }



}

