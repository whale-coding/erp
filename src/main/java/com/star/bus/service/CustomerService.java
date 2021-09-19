package com.star.bus.service;

import com.star.bus.pojo.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.star.bus.vo.CustomerVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Njq
 * @since 2021-09-15
 */
public interface CustomerService extends IService<Customer> {

    // List<Customer> queryAllCustomerForList(CustomerVo customerVo);
}
