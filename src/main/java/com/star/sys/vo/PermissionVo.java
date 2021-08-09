package com.star.sys.vo;

import com.star.sys.pojo.Permission;

/**
 * 自定义的权限扩展类，
 * 只做方法条件参数，不做方法的返回值类型
 */
public class PermissionVo extends Permission {
    private Integer page;//当前页码
    private Integer limit;//每页显示数量

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
