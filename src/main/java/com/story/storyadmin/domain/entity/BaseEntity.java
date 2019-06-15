package com.story.storyadmin.domain.entity;



import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;


public class BaseEntity<T extends Model<?>> extends Model<T> {

    protected Long id;

    public BaseEntity() {
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

}
