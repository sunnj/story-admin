package com.story.storyadmin.service.sysmgr.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.domain.entity.sysmgr.Authority;
import com.story.storyadmin.domain.entity.sysmgr.RoleAuthority;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.AuthorityNode;
import com.story.storyadmin.mapper.sysmgr.AuthorityMapper;
import com.story.storyadmin.service.sysmgr.AuthorityService;
import com.story.storyadmin.service.sysmgr.RoleAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author sunningjun
 * @since 2018-12-28
 */
@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority> implements AuthorityService {

    @Autowired
    RoleAuthorityService roleRoleAuthorityService;

    @Override
    public List<AuthorityNode> findAll() {
        QueryWrapper<Authority> wrapper= new QueryWrapper<>();
        wrapper.eq("yn_flag","1");
        wrapper.orderBy(true,true,"full_id","show_order");
        List<Authority> resList = baseMapper.selectList(wrapper);

        List<AuthorityNode> treeList = new ArrayList<>();

        AuthorityNode newNode;
        for (Authority node : resList) {
            if (node.getPid() == 0) {
                newNode = new AuthorityNode(node.getId(),node.getPid(),node.getName());
                newNode.setCode(node.getCode());
                newNode.setFullId(node.getFullId());
                newNode.setShowOrder(node.getShowOrder());
                treeList.add(findChildren(newNode, resList));
            }
        }
        return treeList;
    }

    /**
     * 递归构造树结构
     * @param parentNode
     * @param list
     * @return
     */
    private AuthorityNode findChildren(AuthorityNode parentNode, List<Authority> list) {
        AuthorityNode newNode;
        for (Authority node : list) {
            if (node.getPid() == parentNode.getId()) {
                if (parentNode.getChildren() == null) {
                    parentNode.setChildren(new ArrayList<>());
                }
                newNode = new AuthorityNode(node.getId(),node.getPid(),node.getName());
                newNode.setCode(node.getCode());
                newNode.setFullId(node.getFullId());
                newNode.setShowOrder(node.getShowOrder());
                parentNode.getChildren().add(findChildren(newNode, list));
            }
        }
        return parentNode;
    }

    /**
     * 获取用户权限
     * @param userId
     * @return
     */
    @Override
    public List<Object> findByUserId(Long userId) {
        List<RoleAuthority> roleAuthList= roleRoleAuthorityService.findByUserId(userId);

        Collection coll = roleAuthList.stream().map(e -> e.getAuthorityId()).collect(Collectors.toList());
        List<Object> roleList= baseMapper.selectObjs(new QueryWrapper<Authority>()
                .eq("yn_flag","1")
                .in("id",coll)
                .select("distinct code"));
        return roleList;
    }

    /**
     * 保存
     * @param auth
     * @return
     */
    @Override
    public Result persist(Authority auth) {
        Date currentDate= Date.from(Instant.now());

        //fullId
        if(auth.getPid()!=null && auth.getPid()>0){
            Authority parent= baseMapper.selectById(auth.getPid());
            auth.setFullId(parent.getFullId()+'-'+ parent.getId());
        }else{
            auth.setFullId("0");
        }

        if(auth.getId()!=null){
            auth.setModifiedTime(currentDate);
            baseMapper.updateById(auth);
        }else{
            auth.setYnFlag("1");
            auth.setEditor(UserContext.getCurrentUser().getAccount());
            auth.setCreator(UserContext.getCurrentUser().getAccount());
            auth.setCreatedTime(currentDate);
            auth.setModifiedTime(currentDate);
            baseMapper.insert(auth);
        }
        return new Result(true,null,null, Constants.TOKEN_CHECK_SUCCESS);
    }
}
