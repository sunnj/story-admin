package com.story.storyadmin.service.sysmgr;

import com.baomidou.mybatisplus.extension.service.IService;
import com.story.storyadmin.domain.entity.sysmgr.Authority;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.AuthorityNode;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author sunningjun
 * @since 2018-12-28
 */
public interface AuthorityService extends IService<Authority> {

    /**
     * 查询列表
     * @return
     */
    List<AuthorityNode> findAll();

    /**
     * 根据用户查询
     * @param userId
     * @return
     */
    List<Object> findByUserId(Long userId);

    /**
     * 保存
     * @param resource
     * @return
     */
    Result persist(Authority resource);
}
