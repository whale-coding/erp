package com.star.bus.service;

import com.star.bus.pojo.Outport;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Njq
 * @since 2021-09-17
 */
public interface OutportService extends IService<Outport> {
    /**
     * 退货
     * @param id  进货单ID
     * @param number  退货数量
     * @param remark  备注
     */
    void addOutPort(Integer id, Integer number, String remark);
}
