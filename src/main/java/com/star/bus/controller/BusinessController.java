package com.star.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bus")
public class BusinessController {
    /**
     * 去到客户管理页面
     * @return
     */
    @RequestMapping("/toCustomerManager")
    public String toCustomerManager(){
        return "business/customer/customerManager";
    }

    /**
     * 去到商品分类页面
     * @return
     */
    @RequestMapping("/toGoodsTypeManager")
    public String toGoodsManager(){
        return "business/goodstype/goodsTypeManager";
    }

    /**
     * 去到商品分类页面--->Left
     * @return
     */
    @RequestMapping("/toGoodsTypeLeft")
    public String toGoodsTypeLeft(){
        return "business/goodstype/left";
    }

    /**
     * 去到商品分类页面--->Right
     * @return
     */
    @RequestMapping("/toGoodsTypeRight")
    public String toGoodsTypeRight(){
        return "business/goodstype/right";
    }

    /**
     * 去到查看请假单页面
     * @return
     */
    @RequestMapping("/toLeaveBillManager")
    public String toLeaveBillManager(){
        return "business/leavebill/leavebillManager";
    }

    /**
     * 去到我的待审批页面
     * @return
     */
    @RequestMapping("/toMyCheckManager")
    public String toMyCheckManager(){
        return "business/leavebill/myCheckManager";
    }



}
