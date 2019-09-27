package com.story.storyadmin.config.upload.support;

import com.story.storyadmin.utils.DateUtils;
import com.story.storyadmin.utils.StringUtils;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 生成文件路径默认策略。
 */
public class DefaultFileArchiveStrategy implements FileArchiveStrategy {

    /**
     * 
     */
    @Override
    public Path createPath(String sourceUri, String originalFilename) {
        if (StringUtils.isEmpty(sourceUri)) {
            sourceUri = "";
        }
        if (sourceUri.startsWith("/")) {
            sourceUri = sourceUri.replaceFirst("/", "");
        }

        if (StringUtils.isNotEmpty(sourceUri) && !sourceUri.endsWith("/")) {
            sourceUri += "/";
        }

        // YYYY/mm/YYYYmmDD_originalFilename
        String path = sourceUri
                + DateUtils.formatDate(DateUtils.currentDate(), DateUtils.TimeFormat.LONG_DATE_PATTERN_WITH_MILSEC_NONE2);
        path += "_." + originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

        return Paths.get(path);
    }

}
