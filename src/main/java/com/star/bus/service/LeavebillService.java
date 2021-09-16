package com.star.bus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.star.bus.pojo.Leavebill;
import com.baomidou.mybatisplus.extension.service.IService;
import com.star.bus.vo.LeavebillVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Njq
 * @since 2021-09-16
 */
public interface LeavebillService extends IService<Leavebill> {

    /**
     * 分页查询请假单
     * @param page
     * @param leavebillVo
     * @return
     * @throws Exception
     */
    IPage<Leavebill> findLeaveBillByPage( IPage<Leavebill> page, LeavebillVo leavebillVo) throws Exception;


}
