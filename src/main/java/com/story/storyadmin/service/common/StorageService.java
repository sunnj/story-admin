/*
 *  Copyright HNA©2017. All rights reserved.
 */
package com.story.storyadmin.service.common;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件存储
 */
public interface StorageService {

    Path store(String sourceUri, MultipartFile multipartFile)
            throws IllegalStateException, IOException;


    Path load(String filename);

}
