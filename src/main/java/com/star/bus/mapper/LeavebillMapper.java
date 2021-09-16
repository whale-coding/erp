package com.star.bus.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.star.bus.pojo.Leavebill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.star.bus.vo.LeavebillVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Njq
 * @since 2021-09-16
 */
public interface LeavebillMapper extends BaseMapper<Leavebill> {
    /**
     * 分页查询请假单
     * @param page
     * @param leavebillVo
     * @return
     * @throws Exception
     */
    IPage<Leavebill> findLeaveBillByPage(@Param("page") IPage<Leavebill> page, @Param("leavebillVo") LeavebillVo leavebillVo) throws Exception;

}
