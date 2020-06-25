package com.story.storyadmin.mapper.sysmgr;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.domain.entity.sysmgr.UserRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author sunnj
 * @since 2018-12-28
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询根据账号查询用户，忽略租户过滤
     * @return
     */
    @SqlParser(filter=true)
    @Select("SELECT id,account,name,password,status,erp_flag FROM st_user ${ew.customSqlSegment}")
    List<User> findUserByAccount(@Param(Constants.WRAPPER) QueryWrapper<User> wrapper);
}
