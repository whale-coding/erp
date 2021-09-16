package com.star.bus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.star.bus.pojo.Leavebill;
import com.star.bus.pojo.LeavebillCheck;
import com.baomidou.mybatisplus.extension.service.IService;
import com.star.bus.vo.LeavebillVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Njq
 * @since 2021-09-16
 */
public interface LeavebillCheckService extends IService<LeavebillCheck> {
    /**
     * 根据请假单id查询该请假单的送审信息
     * @param id
     * @return
     * @throws Exception
     */
    List<LeavebillCheck> findLeaveBillCheckListByLeaveBillId(Integer id) throws Exception;

}
