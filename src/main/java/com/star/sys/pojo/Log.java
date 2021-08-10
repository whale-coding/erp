package com.star.sys.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Njq
 * @since 2021-08-09
 */
@TableName("sys_log")
@Data
public class Log implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 日志编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 日志内容
     */
    private String content;

    /**
     * 日志操作类型
     */
    private String type;

    /**
     * 执行人
     */
    private String loginname;

    /**
     * 执行人编号
     */
    private Integer userid;

    /**
     * 登录ip
     */
    private String loginip;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 添加日志时使用
     * @param content       日志内容
     * @param type          操作类型
     * @param loginname     操作人
     * @param userid        操作人id
     * @param loginip       登录ip
     * @param createtime    创建时间
     */
    public Log(String content, String type, String loginname, Integer userid, String loginip, Date createtime) {
        this.content = content;
        this.type = type;
        this.loginname = loginname;
        this.userid = userid;
        this.loginip = loginip;
        this.createtime = createtime;
    }


    public Log() {

    }

    public Log(Integer id, String content, String type, String loginname, Integer userid, String loginip, Date createtime) {
        this.id = id;
        this.content = content;
        this.type = type;
        this.loginname = loginname;
        this.userid = userid;
        this.loginip = loginip;
        this.createtime = createtime;
    }
}
