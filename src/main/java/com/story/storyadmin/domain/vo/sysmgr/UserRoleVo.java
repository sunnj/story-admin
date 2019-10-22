package com.story.storyadmin.domain.vo.sysmgr;

import lombok.Data;

import java.util.Set;

/**
 * <p>
 * </p>
 *
 * @author sunnj
 * @since 2018-12-28
 */
@Data
public class UserRoleVo {
    private Long userId;
    private Set<Long> roleIds;
}
