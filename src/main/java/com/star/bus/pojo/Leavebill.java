package com.star.bus.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Njq
 * @since 2021-09-16
 */
@TableName("bus_leavebill")
@Data
public class Leavebill implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 请假编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 请假标题
     */
    private String title;

    /**
     * 请假内容
     */
    private String content;

    /**
     * 请假天数
     */
    private Double days;

    /**
     * 请假开始时间
     */
    private Date leavetime;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 请假人id
     */
    private Integer userid;

    /**
     * 状态：0-未提交，1-待审批，2-审核通过，3-审核不通过，4-已放弃
     */
    private Integer state;

    /**
     * 审批人
     */
    @TableField("checkPerson")
    private Integer checkPerson;

    /**
     * 请假单提交时间
     */
    private Date committime;


}
