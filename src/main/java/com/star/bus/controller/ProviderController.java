package com.star.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.star.bus.pojo.Customer;
import com.star.bus.pojo.Provider;
import com.star.bus.service.ProviderService;
import com.star.bus.vo.CustomerVo;
import com.star.bus.vo.ProviderVo;
import com.star.common.utils.DataGridViewResult;
import com.star.common.utils.JSONResult;
import com.star.common.utils.SystemConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Njq
 * @since 2021-09-17
 */
@RestController
@RequestMapping("/bus/provider")
public class ProviderController {

    @Resource
    private ProviderService providerService;

    /**
     * 供应商列表
     * @param providerVo
     * @return
     */
    @RequestMapping("/providerList")
    public DataGridViewResult providerList(ProviderVo providerVo){
        //创建分页对象
        IPage<Provider> page=new Page<>(providerVo.getPage(),providerVo.getLimit());
        //创建条件构造器
        QueryWrapper<Provider> wrapper = new QueryWrapper<>();
        //供应商名称
        wrapper.like(StringUtils.isNotBlank(providerVo.getProvidername()),"providername",providerVo.getProvidername());
        //供应商电话
        wrapper.like(StringUtils.isNotBlank(providerVo.getTelephone()),"telephone",providerVo.getTelephone());
        //联系人
        wrapper.like(StringUtils.isNotBlank(providerVo.getLinkman()),"linkman",providerVo.getLinkman());

        //调用分页查询的方法
        providerService.page(page,wrapper);
        //返回数据
        return new DataGridViewResult(page.getTotal(),page.getRecords());
    }


    /**
     * 添加供应商
     * @param provider
     * @return
     */
    @RequestMapping("/addProvider")
    public JSONResult addProvider(Provider provider){
        if (providerService.save(provider)){
            return SystemConstant.ADD_SUCCESS;
        }
        return SystemConstant.ADD_ERROR;
    }


    /**
     * 修改供应商信息
     * @param provider
     * @return
     */
    @RequestMapping("/updateProvider")
    public JSONResult updateProvider(Provider provider){
        if (providerService.updateById(provider)){
            return SystemConstant.UPDATE_SUCCESS;
        }
        return SystemConstant.UPDATE_ERROR;
    }

    /**
     * 删除供应商
     * @param id
     * @return
     */
    @RequestMapping("/deleteProvider")
    public JSONResult deleteProvider(Integer id){
        if (providerService.removeById(id)){
            return SystemConstant.DELETE_SUCCESS;
        }
        return SystemConstant.DELETE_ERROR;
    }

    /**
     * 批量删除供应商
     * @param ids
     * @return
     */
    @RequestMapping("/batchDelete")
    public JSONResult batchDelete(String ids){
        //将字符串拆分成数组
        String[] idsStr=ids.split(",");
        //判断是否删除成功
        if (providerService.removeByIds(Arrays.asList(idsStr))){  //将字符数组转换为List集合作为参数
            //删除成功
            return SystemConstant.DELETE_SUCCESS;
        }else {
            //删除失败
            return SystemConstant.DELETE_ERROR;
        }
    }

}

