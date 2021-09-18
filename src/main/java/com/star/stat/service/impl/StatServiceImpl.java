package com.star.stat.service.impl;

import com.star.stat.domain.BaseEntity;
import com.star.stat.mapper.StatMapper;
import com.star.stat.service.StatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StatServiceImpl implements StatService {

    @Resource
    private StatMapper statMapper;

    @Override
    public List<BaseEntity> loadCustomerAreaStatList() {
        return statMapper.queryCustomerAreaStat();
    }

    @Override
    public List<BaseEntity> queryOperatePersonYearGradeStatList(String year) {
        return statMapper.queryOperatePersonYearGradeStat(year);
    }
}
