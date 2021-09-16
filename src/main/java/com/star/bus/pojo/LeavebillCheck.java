package com.star.bus.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Njq
 * @since 2021-09-16
 */
@TableName("bus_leavebill_check")
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
    private LocalDateTime checkTime;

    /**
     * 请假单id
     */
    @TableField("leavebillId")
    private Integer leavebillId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Integer getCheckUserId() {
        return checkUserId;
    }

    public void setCheckUserId(Integer checkUserId) {
        this.checkUserId = checkUserId;
    }

    public LocalDateTime getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(LocalDateTime checkTime) {
        this.checkTime = checkTime;
    }

    public Integer getLeavebillId() {
        return leavebillId;
    }

    public void setLeavebillId(Integer leavebillId) {
        this.leavebillId = leavebillId;
    }

    @Override
    public String toString() {
        return "LeavebillCheck{" +
        "id=" + id +
        ", replyContent=" + replyContent +
        ", checkUserId=" + checkUserId +
        ", checkTime=" + checkTime +
        ", leavebillId=" + leavebillId +
        "}";
    }
}
