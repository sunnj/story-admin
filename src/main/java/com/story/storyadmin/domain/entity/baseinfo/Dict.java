package com.story.storyadmin.domain.entity.baseinfo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.story.storyadmin.domain.entity.BaseEntity;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author sunningjun
 * @since 2019-07-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("st_dict")
public class Dict extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    private String code;

    /**
     * 中文名称
     */
    private String chnName;

    /**
     * 英文名称
     */
    private String engName;

    /**
     * 排序
     */
    private Integer showOrder;

    /**
     * 父编码
     */
    private String parentCode;

    /**
     * 有效标志
     */
    private String ynFlag;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改人
     */
    private String editor;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改时间
     */
    private Date modifiedTime;


}
