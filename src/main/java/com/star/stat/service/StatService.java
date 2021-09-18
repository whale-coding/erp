package com.star.stat.service;

import com.star.stat.domain.BaseEntity;

import java.util.List;

/**
 * 统计分析数据服务接口
 * @author NJQ
 *
 */
public interface StatService {
    /**
     * 查询客户地区的数据
     * @return
     */
    List<BaseEntity> loadCustomerAreaStatList();
}
