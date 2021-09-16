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
@TableName("bus_leavebill_check")
@Data
public class LeavebillCheck implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 审批意见编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 审批回复内容
     */
    @TableField("replyContent")
    private String replyContent;

    /**
     * 审批人
     */
    @TableField("checkUserId")
    private Integer checkUserId;

    /**
     * 审批时间
     */
    @TableField("checkTime")
    private Date checkTime;

    /**
     * 请假单id
     */
    @TableField("leavebillId")
    private Integer leavebillId;


    @TableField(exist = false)
    private String checkPersonName;  //审批人



}
