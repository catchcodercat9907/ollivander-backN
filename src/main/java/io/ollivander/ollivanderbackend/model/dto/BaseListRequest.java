package io.ollivander.ollivanderbackend.model.dto;

import io.ollivander.ollivanderbackend.model.DbConst;

public class BaseListRequest {
    private String orderBy;

    private String orderDirection;

    private Integer pageIndex;

    protected Integer pageSize;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    public Integer getPageIndex() {
        // index must >= 0
        if (pageIndex == null || (pageIndex != null && pageIndex.intValue() < 0)) {
            return 0;
        } else
            return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        // size must > 0
        if (pageSize == null || (pageSize != null && pageSize.intValue() <= 0)) {
            return DbConst.DEFAULT_PAGE_SIZE;
        } else
            return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
