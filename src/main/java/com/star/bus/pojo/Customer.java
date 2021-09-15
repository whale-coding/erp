package com.star.bus.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Njq
 * @since 2021-09-15
 */
@TableName("bus_customer")
@Data
public class Customer implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 客户编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 客户姓名
     */
    private String customername;

    /**
     * 客户地址
     */
    private String address;

    /**
     * 客户公司电话
     */
    private String telephone;

    /**
     * 联系人
     */
    private String linkman;

    /**
     * 联系人手机
     */
    private String phone;

    /**
     * 开户银行
     */
    private String bank;

    /**
     * 客户银行账号
     */
    private String account;

    /**
     * 联系人邮箱
     */
    private String email;

    /**
     * 联系人传真
     */
    private String fax;

    /**
     * 客户邮编
     */
    private String zipcode;

}
