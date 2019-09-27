package com.story.storyadmin.domain.vo.sysmgr;

import com.story.storyadmin.config.upload.entity.FileSlot;
import com.story.storyadmin.domain.entity.sysmgr.Att;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 附件表
 * </p>
 *
 * @author sunningjun
 * @since 2019-07-12
 */
@Data
public class AttVo implements FileSlot {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String slotId;
    private String fileCate;
    private String type;
    private Long fileSize;
    private String originName;
    private String path;
    private String description;

    @Override
    public List<Att> getSlotFiles() {
        return null;
    }
}
