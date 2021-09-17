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
 * @since 2021-09-17
 */
@TableName("bus_provider")
@Data
public class Provider implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 供应商编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 供应商名称
     */
    private String providername;

    /**
     * 供应商邮编
     */
    private String zip;

    /**
     * 供应商地址
     */
    private String address;

    /**
     * 供应商公司联系电话
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
     * 银行账号
     */
    private String account;

    /**
     * 供应商邮箱
     */
    private String email;

    /**
     * 供应商传真
     */
    private String fax;

    /**
     * 是否可以 0：不可用  1：可用
     */
    private Integer available;


}
