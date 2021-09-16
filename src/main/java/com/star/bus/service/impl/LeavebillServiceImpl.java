package com.star.bus.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.star.bus.pojo.Leavebill;
import com.star.bus.mapper.LeavebillMapper;
import com.star.bus.pojo.LeavebillCheck;
import com.star.bus.service.LeavebillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.star.bus.vo.LeavebillVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Njq
 * @since 2021-09-16
 */
@Service
public class LeavebillServiceImpl extends ServiceImpl<LeavebillMapper, Leavebill> implements LeavebillService {

    @Resource
    private LeavebillMapper leavebillMapper;

    @Override
    public IPage<Leavebill> findLeaveBillByPage(IPage<Leavebill> page, LeavebillVo leavebillVo) throws Exception {
        return leavebillMapper.findLeaveBillByPage(page,leavebillVo);
    }


}
