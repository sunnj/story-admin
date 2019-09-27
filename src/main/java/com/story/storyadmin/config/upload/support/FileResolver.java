package com.story.storyadmin.config.upload.support;

import com.story.storyadmin.config.upload.entity.FileSlot;

public interface FileResolver {
    void resolveFile(MultipartRequestWrapper multipartRequestAttributes, FileSlot entity) throws Exception;
}
