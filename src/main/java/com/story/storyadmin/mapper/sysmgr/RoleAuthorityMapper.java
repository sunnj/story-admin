package com.story.storyadmin.mapper.sysmgr;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.story.storyadmin.domain.entity.sysmgr.RoleAuthority;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author
 * @since 2018-12-28
 */
public interface RoleAuthorityMapper extends BaseMapper<RoleAuthority> {

    /**
     * 根据角色Id删除
     * @param role
     * @return
     */
    int deleteAuthByRoleId(RoleAuthority role);

    /**
     * 批量插入
     * @param roleAuthList
     */
    void batchInsert(List<RoleAuthority> roleAuthList);

    /**
     * 根据角色Id获取权限
     * @param roleId
     * @return
     */
    List<Long> selectAuthByRoleId(@Param(value = "roleId") Long roleId);

}
