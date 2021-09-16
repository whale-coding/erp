package com.star.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.star.bus.pojo.GoodsType;
import com.star.bus.service.GoodsTypeService;
import com.star.bus.vo.goodsTypeVo;
import com.star.common.utils.DataGridViewResult;
import com.star.common.utils.JSONResult;
import com.star.common.utils.SystemConstant;
import com.star.common.utils.TreeNode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Njq
 * @since 2021-09-15
 */
@RestController
@RequestMapping("/bus/goodstype")
public class GoodsTypeController {

    @Resource
    private GoodsTypeService goodsTypeService;

    /***
     * 加载left.html左侧菜单树
     * @return
     */
    @RequestMapping("/loadGoodsTypeTreeLeft")
    public DataGridViewResult loadGoodsTypeTreeLeft(){
        //查询所有商品分类
        List<GoodsType> goodsTypeList = goodsTypeService.list();
        //创建节点集合
        List<TreeNode> treeNodes = new ArrayList<>();
        //循环遍历部门集合
        for (GoodsType goodsType : goodsTypeList) {
            //是否展开 1为展开状态
            Boolean spread = goodsType.getOpen()==1?true:false;

            //封装树节点
            TreeNode treeNode=new TreeNode();
            treeNode.setId(goodsType.getId());  //部门编号
            treeNode.setPid(goodsType.getPid()); //父级部门编号
            treeNode.setTitle(goodsType.getTitle()); //部门名称
            treeNode.setSpread(spread); //展开状态
            //将树节点对象添加到节点集合
            treeNodes.add(treeNode);

        }
        //将树节点返回
        return new DataGridViewResult(treeNodes);
    }

    /**
     * 商品分类列表
     * @param goodsTypeVo
     * @return
     */
    @RequestMapping("/goodsTypeList")
    public DataGridViewResult goodsTypeList(goodsTypeVo goodsTypeVo){
        //创建分页对象
        IPage<GoodsType> page = new Page<>(goodsTypeVo.getPage(),goodsTypeVo.getLimit());
        //创建条件构造器对象
        QueryWrapper<GoodsType> wrapper = new QueryWrapper<>();
        //分类名称查询
        wrapper.like(StringUtils.isNotBlank(goodsTypeVo.getTitle()),"title",goodsTypeVo.getTitle());

        //分类编号
        wrapper.eq(goodsTypeVo.getId()!=null,"id",goodsTypeVo.getId())
                .or().eq(goodsTypeVo.getId()!=null,"pid", goodsTypeVo.getId());
        //排序
        wrapper.orderByAsc("id");
        //调用查询的方法
        goodsTypeService.page(page,wrapper);
        //返回数据
        return new DataGridViewResult(page.getTotal(),page.getRecords());
    }

    /**
     * 添加商品分类
     * @param goodsType
     * @return
     */
    @RequestMapping("/addGoodsType")
    public JSONResult addGoodsType(GoodsType goodsType){
        if(goodsTypeService.save(goodsType)){
            return SystemConstant.ADD_SUCCESS;
        }
        return SystemConstant.ADD_ERROR;
    }





}

