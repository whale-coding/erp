package com.star.bus.controller;


import com.star.bus.service.OutportService;
import com.star.common.utils.JSONResult;
import com.star.common.utils.SystemConstant;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Njq
 * @since 2021-09-17
 */
@RestController
@RequestMapping("/bus/outport")
public class OutportController {

    @Resource
    private OutportService outportService;








    /**
     * 添加退货信息
     */
    @RequestMapping("/addOutport")
    public JSONResult addOutport(Integer id, Integer number, String remark) {
        try {
            this.outportService.addOutPort(id,number,remark);
            return SystemConstant.OPERATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return SystemConstant.OPERATE_ERROR;
        }
    }

}

