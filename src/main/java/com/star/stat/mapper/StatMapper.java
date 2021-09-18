package com.star.stat.mapper;

import com.star.stat.domain.BaseEntity;

import java.util.List;


public interface StatMapper {

	/**
	 * 查询客户地区的数据
	 * @return
	 */
	List<BaseEntity> queryCustomerAreaStat();

	/**
	 * 查询进货员员年度业绩
	 * @param year
	 * @return
	 */
	List<BaseEntity> queryOperatePersonYearGradeStat(String year);

}
