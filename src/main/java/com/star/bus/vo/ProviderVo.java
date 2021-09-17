package com.star.bus.vo;

import com.star.bus.pojo.Provider;

public class ProviderVo extends Provider {
    private Integer page; //当前页码
    private Integer limit; //每页显示条数

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
