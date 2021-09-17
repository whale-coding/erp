package com.star.bus.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("bus_goods")
@Data
public class Goods implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 商品编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名称
     */
    private String goodsname;

    /**
     * 商品产地
     */
    private String produceplace;

    /**
     * 商品规格
     */
    private String size;

    /**
     * 包装
     */
    private String goodspackage;

    /**
     * 生产批号
     */
    private String productcode;

    /**
     * 批准文号
     */
    private String promitcode;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 商品售价
     */
    private Double price;

    /**
     * 商品数量
     */
    private Integer number;

    /**
     * 预警数量
     */
    private Integer dangernum;

    /**
     * 商品图片
     */
    private String goodsimg;

    /**
     * 商品分类编号
     */
    private Integer typeid;

    /**
     * 供应商编号
     */
    private Integer providerid;
    /**
     * 是否可用
     */
    private Integer  available;


    @TableField(exist = false)
    private String goodsTypeName;   //商品类型名称

    @TableField(exist = false)
    private String providerName;   //供应商名称
}
