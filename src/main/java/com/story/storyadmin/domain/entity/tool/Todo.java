package com.story.storyadmin.domain.entity.tool;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.story.storyadmin.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 待办事项
 * </p>
 *
 * @author sunnj
 * @since 2019-08-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("st_todo")
public class Todo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    private String title;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date start;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date end;

    /**
     * 天数
     */
    private Integer daysCount;

    /**
     * 链接
     */
    private String url;

    /**
     * 内容
     */
    private String taskContent;

    /**
     * 优先级
     */
    private Integer level;

    /**
     * 执行人
     */
    private String executor;

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
