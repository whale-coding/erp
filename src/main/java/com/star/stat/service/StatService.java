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

    /**
     * 查询进货员员年度业绩
     * @param year
     * @return
     */
    List<BaseEntity> queryOperatePersonYearGradeStatList(String year);


    /**
     * 加载公司年度进货量统计数据
     * @param year
     * @return
     */
    List<Integer> loadCompanyYearGradeStatList(String year);

}
