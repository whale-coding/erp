package com.star.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.star.bus.pojo.Customer;
import com.star.bus.service.CustomerService;
import com.star.bus.vo.CustomerVo;
import com.star.common.utils.DataGridViewResult;
import com.star.common.utils.JSONResult;
import com.star.common.utils.SystemConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;

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
     * 客户列表
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

    /**
     * 添加客户
     * @param customer
     * @return
     */
    @RequestMapping("/addCustomer")
    public JSONResult addCustomer(Customer customer){
        if (customerService.save(customer)){
            return SystemConstant.ADD_SUCCESS;
        }
        return SystemConstant.ADD_ERROR;
    }

    /**
     * 修改客户信息
     * @param customer
     * @return
     */
    @RequestMapping("/updateCustomer")
    public JSONResult updateCustomer(Customer customer){
        if (customerService.updateById(customer)){
            return SystemConstant.UPDATE_SUCCESS;
        }
        return SystemConstant.UPDATE_ERROR;
    }

    /**
     * 删除客户信息
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public JSONResult deleteCustomer(Integer id){
        if (customerService.removeById(id)){
            return SystemConstant.DELETE_SUCCESS;
        }
        return SystemConstant.DELETE_ERROR;
    }

    /**
     * 批量删除客户
     * @param ids
     * @return
     */
    @RequestMapping("/batchDelete")
    public JSONResult batchDelete(String ids){
        //将字符串拆分成数组
        String[] idsStr=ids.split(",");
        //判断是否删除成功
        if (customerService.removeByIds(Arrays.asList(idsStr))){  //将字符数组转换为List集合作为参数
            //删除成功
            return SystemConstant.DELETE_SUCCESS;
        }else {
            //删除失败
            return SystemConstant.DELETE_ERROR;
        }
    }



}

