package com.star.bus.service.impl;

import com.star.bus.pojo.Customer;
import com.star.bus.mapper.CustomerMapper;
import com.star.bus.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Njq
 * @since 2021-09-15
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}
