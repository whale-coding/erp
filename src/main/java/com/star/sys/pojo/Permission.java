package com.star.sys.pojo;

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
 * @since 2021-08-09
 */
@TableName("sys_permission")
@Data
public class Permission implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 菜单权限编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 所属菜单
     */
    private Integer pid;

    /**
     * 权限类型[menu/permission]
     */
    private String type;

    /**
     * 菜单权限名称
     */
    private String title;

    /**
     * 权限编码[只有type= permission才有  user:view]
     */
    private String percode;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单跳转请求路径
     */
    private String href;

    /**
     * 菜单是否展开(0展开，1不展开)
     */
    private Integer open;

    /**
     * 排序码
     */
    private Integer ordernum;

    /**
     * 状态【0不可用1可用】
     */
    private Integer available;



}
