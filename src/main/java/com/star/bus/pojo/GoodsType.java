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
@TableName("bus_goods_type")
@Data
public class GoodsType implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 分类编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 分类名称
     */
    private String title;

    /**
     * 父类编号
     */
    private Integer pid;

    /**
     * 分类图标
     */
    private String icon;

    /**
     * 是否展开（0展开，1不展开）
     */
    private Integer open;

    /**
     * 备注
     */
    private String remark;

}
