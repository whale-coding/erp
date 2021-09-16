package com.star.bus.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.star.bus.pojo.Leavebill;
import com.star.bus.pojo.LeavebillCheck;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.star.bus.vo.LeavebillVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Njq
 * @since 2021-09-16
 */
public interface LeavebillCheckMapper extends BaseMapper<LeavebillCheck> {
    /**
     * 根据请假单id查询该请假单的送审信息
     * @param id
     * @return
     * @throws Exception
     */
    List<LeavebillCheck> findLeaveBillCheckListByLeaveBillId(Integer id) throws Exception;

}
