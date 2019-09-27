package com.story.storyadmin.config.mongo;

import java.io.Serializable;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * MongoDB 分页辅助类
 * 
 * @author dante
 *
 */
public class StoryMongoPageable implements Serializable, Pageable {
    private static final long serialVersionUID = 1;
    // 当前页
    private Integer pagenumber = 1;
    // 当前页面条数
    private Integer pagesize = 10;
    // 排序条件
    private Sort sort;

    // 当前页面
    @Override
    public int getPageNumber() {
        return getPagenumber();
    }

    // 每一页显示的条数
    @Override
    public int getPageSize() {
        return getPagesize();
    }

    // 第二页所需要增加的数量
    @Override
    public long getOffset() {
        return (getPagenumber() - 1) * getPagesize();
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    public Integer getPagenumber() {
        return pagenumber;
    }

    public void setPagenumber(Integer pagenumber) {
        this.pagenumber = pagenumber;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }
}
