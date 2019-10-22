package com.story.storyadmin.domain.entity.sysmgr;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.story.storyadmin.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 角色权限关系表
 * </p>
 *
 * @author sunnj
 * @since 2018-12-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("st_role_authority")
public class RoleAuthority extends BaseEntity<RoleAuthority> {

    @TableField("role_id")
    private Long roleId;

    @TableField("authority_id")
    private Long authorityId;

    /**
     * 有效标志
     */
    @TableField("yn_flag")
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
    @TableField("created_time")
    private Date createdTime;

    /**
     * 修改时间
     */
    @TableField("modified_time")
    private Date modifiedTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public RoleAuthority() {
    }

    public RoleAuthority(Long roleId, Long authorityId) {
        this.roleId = roleId;
        this.authorityId = authorityId;
    }
}
