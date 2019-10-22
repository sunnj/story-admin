package com.story.storyadmin.service.sysmgr;


import com.baomidou.mybatisplus.extension.service.IService;
import com.story.storyadmin.domain.entity.sysmgr.RoleAuthority;

import java.util.List;

/**
 * <p>
 * 角色权限关系表 服务类
 * </p>
 *
 * @author sunnj
 * @since 2018-12-28
 */
public interface RoleAuthorityService extends IService<RoleAuthority> {


    /**
     * 批量新增
     */
     void batchInsert(List<RoleAuthority> list);


    /**
     * 根据角色删除
     * @param role
     */
     void deleteAuthByRoleId(RoleAuthority role);

    /**
     * 根据角色查询
     * @param roleId
     * @return
     */
    List<Long> selectAuthByRoleId(Long roleId);

    /**
     * 根据用户Id查询
     * @return
     */
    List<RoleAuthority> findByUserId(Long userId);


}
