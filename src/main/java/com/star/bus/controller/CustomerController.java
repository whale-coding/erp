package com.star.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.star.bus.pojo.Customer;
import com.star.bus.service.CustomerService;
import com.star.bus.vo.CustomerVo;
import com.star.common.utils.DataGridViewResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Njq
 * @since 2021-09-15
 */
@RestController
@RequestMapping("/bus/customer")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    /**
     *
     * @param customerVo
     * @return
     */
    @RequestMapping("/customerList")
    public DataGridViewResult customerList(CustomerVo customerVo){
        //创建分页对象
        IPage<Customer> page=new Page<>(customerVo.getPage(),customerVo.getLimit());
        //创建条件构造器
        QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        //客户姓名(模糊查询)
        wrapper.like(StringUtils.isNotBlank(customerVo.getCustomername()),"customername",customerVo.getCustomername());
        //客户电话(模糊查询)
        wrapper.like(StringUtils.isNotBlank(customerVo.getTelephone()),"telephone",customerVo.getTelephone());
        //联系人(模糊查询)
        wrapper.like(StringUtils.isNotBlank(customerVo.getLinkman()),"linkman",customerVo.getLinkman());

        //调用分页查询的方法
        customerService.page(page,wrapper);
        //返回数据
        return new DataGridViewResult(page.getTotal(),page.getRecords());
    }

}

