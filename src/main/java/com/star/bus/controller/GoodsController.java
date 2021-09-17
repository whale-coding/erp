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
import com.star.common.fileUtil.FileUtils;
import com.star.common.utils.DataGridViewResult;
import com.star.common.utils.JSONResult;
import com.star.common.utils.SystemConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
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


    /**
     * 添加商品
     */
    @RequestMapping("/addGoods")
    public JSONResult addGoods(GoodsVo goodsVo) {
        try {
            //如果图片是上传的，需要改名字
            if(goodsVo.getGoodsimg()!=null&&goodsVo.getGoodsimg().endsWith("_temp")) {
                String newName= FileUtils.renameFile(goodsVo.getGoodsimg());  //调用工具类，给图片改名字

                goodsVo.setGoodsimg(newName);
            }
            goodsService.save(goodsVo);
            return SystemConstant.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return SystemConstant.ADD_ERROR;
        }
    }



    /**
     * 修改商品
     */
    @RequestMapping("/updateGoods")
    public JSONResult updateGoods(GoodsVo goodsVo) {
        try {
            //说明是不默认图片
            if(!(goodsVo.getGoodsimg()!=null&&goodsVo.getGoodsimg().equals(SystemConstant.IMAGES_DEFAULTGOODSIMG_PNG))) {
                //判断是否需要改名字
                if(goodsVo.getGoodsimg().endsWith("_temp")) {
                    String newName=FileUtils.renameFile(goodsVo.getGoodsimg());
                    goodsVo.setGoodsimg(newName);
                    //删除原先的图片
                    String oldPath=goodsService.getById(goodsVo.getId()).getGoodsimg();
                    FileUtils.removeFileByPath(oldPath);
                }
            }
            //更新商品
            goodsService.updateById(goodsVo);

            return SystemConstant.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return SystemConstant.UPDATE_ERROR;
        }
    }


    /**
     * 删除商品
     */
    @RequestMapping("/deleteGoods")
    public JSONResult deleteGoods(Integer id,String goodsimg) {
        try {
            //删除原文件
            FileUtils.removeFileByPath(goodsimg);
            goodsService.removeById(id);
            return SystemConstant.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return SystemConstant.DELETE_ERROR;
        }
    }

    /**
     * 加载所有可用的商品
     */
    @RequestMapping("/loadAllGoodsForSelect")
    public DataGridViewResult loadAllGoodsForSelect() {
        QueryWrapper<Goods> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("available", 1);
        List<Goods> list = goodsService.list(queryWrapper);
        for (Goods goods : list) {
            Provider provider = providerService.getById(goods.getProviderid());
            if(null!=provider) {
                goods.setProviderName(provider.getProvidername());
            }
        }
        return new DataGridViewResult(list);
    }


}

