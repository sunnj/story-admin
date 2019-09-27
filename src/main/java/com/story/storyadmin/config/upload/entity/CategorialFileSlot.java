package com.story.storyadmin.config.upload.entity;


/**
 *
 */
public interface CategorialFileSlot extends FileSlot {
    /**
     * 用户自定义的文件类别.
     * 当上传文件时， 该方法获取传来的category， 并保存到对应的{@link Att}中。
     * @return
     */
    String getSlotFileCategoryOnUploading();
}
