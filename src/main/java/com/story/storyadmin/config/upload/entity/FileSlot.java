package com.story.storyadmin.config.upload.entity;

import java.util.List;
import java.util.UUID;

import com.story.storyadmin.domain.entity.sysmgr.Att;

/**
 * 文件批次。
 * 凡是实现该接口的实体类， 代表这个类有附件或者其它附属文件； 这些文件的增删改均通过切面{@code MultipartAspect}统一处理,
 * 数据保存为{@code FileEntity} 。
 */
public interface FileSlot {

    static String newSlotId() {
        return UUID.randomUUID().toString();
    }

    /**
     * 文件槽的ID, 一个ID关联一个或者多个FileEntity
     * @return
     */
    String getSlotId();

    /**
     * 设置文件槽的ID, 一个ID关联一个或者多个FileEntity
     * @param batchId
     */
    void setSlotId(String batchId);

    /**
     * 与文件槽关联的文件实体FileEntity 列表。
     * @return
     */
    List<Att> getSlotFiles();
}
