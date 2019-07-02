package com.story.storyadmin.domain.entity.sysmgr;

import com.baomidou.mybatisplus.annotation.TableName;
import com.story.storyadmin.domain.entity.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 附件表
 * </p>
 *
 * @author sunningjun
 * @since 2019-07-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("st_att")
public class Att extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private LocalDateTime createDate;

    private Boolean deleteFlag;

    private String description;

    private String lotId;

    private String path;

    private String type;

    private LocalDateTime updateDate;

    private Long createUser;

    private Long updateUser;

    private Long fileSize;

    private String originName;


}
