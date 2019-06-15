package com.story.storyadmin.service.sysmgr.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.domain.entity.sysmgr.Resource;
import com.story.storyadmin.domain.entity.sysmgr.RoleAuthority;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.ResourceNode;
import com.story.storyadmin.mapper.sysmgr.ResourceMapper;
import com.story.storyadmin.service.sysmgr.ResourceService;
import com.story.storyadmin.service.sysmgr.RoleAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author sunningjun
 * @since 2018-12-28
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Autowired
    RoleAuthorityService roleRoleAuthorityService;

    @Override
    public List<ResourceNode> findAll() {
        QueryWrapper<Resource> wrapper= new QueryWrapper<>();
        wrapper.eq("yn_flag","1");
        wrapper.orderBy(true,true,"full_id","show_order");
        List<Resource> resList = baseMapper.selectList(wrapper);

        List<ResourceNode> treeList = new ArrayList<>();

        ResourceNode newNode;
        for (Resource node : resList) {
            if (node.getPid() == 0) {
                newNode = new ResourceNode(node.getId(),node.getPid(),node.getName());
                newNode.setIconClass(node.getIconClass());
                newNode.setUrl(node.getUrl());
                newNode.setComponent(node.getComponent());
                newNode.setAuthorityId(node.getAuthorityId());
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
    private ResourceNode findChildren(ResourceNode parentNode, List<Resource> list) {
        ResourceNode newNode;
        for (Resource node : list) {
            if (node.getPid() == parentNode.getId()) {
                if (parentNode.getChildren() == null) {
                    parentNode.setChildren(new ArrayList<>());
                }
                newNode = new ResourceNode(node.getId(),node.getPid(),node.getName());
                newNode.setIconClass(node.getIconClass());
                newNode.setUrl(node.getUrl());
                newNode.setComponent(node.getComponent());
                newNode.setAuthorityId(node.getAuthorityId());
                newNode.setShowOrder(node.getShowOrder());
                parentNode.getChildren().add(findChildren(newNode, list));
            }
        }
        return parentNode;
    }

    /**
     * 查询用户菜单
     * @param userId
     * @return
     */
    @Override
    public List<ResourceNode> findByUserId(Long userId) {

        List<RoleAuthority> roleAuthList= roleRoleAuthorityService.findByUserId(userId);

        Set<Long> authSet= roleAuthList.stream().map(e -> e.getAuthorityId()).collect(Collectors.toSet());

        QueryWrapper<Resource> wrapper= new QueryWrapper<>();
        wrapper.eq("yn_flag","1");
        wrapper.orderBy(true,true,"full_id","show_order");
        List<Resource> resList = baseMapper.selectList(wrapper);

        List<ResourceNode> treeList = new ArrayList<>();

        ResourceNode newNode;
        for (Resource node : resList) {
            if (node.getPid() == 0) {
                newNode = new ResourceNode(node.getId(),node.getPid(),node.getName());
                newNode.setIconClass(node.getIconClass());
                newNode.setUrl(node.getUrl());
                newNode.setComponent(node.getComponent());
                newNode.setAuthorityId(node.getAuthorityId());
                newNode.setShowOrder(node.getShowOrder());
                treeList.add(findChildrenWithAuth(newNode, resList,authSet));
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
    private ResourceNode findChildrenWithAuth(ResourceNode parentNode, List<Resource> list, Set<Long> authSet) {
        ResourceNode newNode;
        for (Resource node : list) {
            if (node.getPid() == parentNode.getId()) {
                if (parentNode.getChildren() == null) {
                    parentNode.setChildren(new ArrayList<>());
                }
                newNode = new ResourceNode(node.getId(),node.getPid(),node.getName());
                newNode.setIconClass(node.getIconClass());
                newNode.setUrl(node.getUrl());
                newNode.setComponent(node.getComponent());
                newNode.setAuthorityId(node.getAuthorityId());
                newNode.setShowOrder(node.getShowOrder());


                ResourceNode tempNode= findChildrenWithAuth(newNode, list,authSet);
                if((tempNode.getChildren()!=null && tempNode.getChildren().size()>0) || authSet.contains(tempNode.getAuthorityId())){
                    parentNode.getChildren().add(tempNode);
                }
            }
        }
        return parentNode;
    }

    /**
     * 保存
     * @param resource
     * @return
     */
    @Override
    public Result persist(Resource resource) {
        Date currentDate= Date.from(Instant.now());
        if(resource.getId()!=null){
            resource.setEditor(UserContext.getCurrentUser().getAccount());
            resource.setModifiedTime(currentDate);
            baseMapper.updateById(resource);
        }else{
            if(resource.getPid()!=null && resource.getPid()>0){
                //fullId
                Resource parent= baseMapper.selectById(resource.getPid());
                resource.setFullId(parent.getFullId()+'-'+ parent.getId());
            }else{
                resource.setFullId("0");
            }
            resource.setYnFlag("1");
            resource.setEditor(UserContext.getCurrentUser().getAccount());
            resource.setCreator(UserContext.getCurrentUser().getAccount());
            resource.setCreatedTime(currentDate);
            resource.setModifiedTime(currentDate);

            baseMapper.insert(resource);
        }
        return new Result(true,null,null, Constants.TOKEN_CHECK_SUCCESS);
    }
}
