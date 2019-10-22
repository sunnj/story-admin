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
public class RoleAuth {
    private Long roleId;
    private Set<Long> authorityIds;
}
