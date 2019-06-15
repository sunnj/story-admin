package com.story.storyadmin.web.sysmgr;

import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.domain.entity.sysmgr.Resource;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.ResourceNode;
import com.story.storyadmin.service.sysmgr.ResourceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/sysmgr/resource")
public class ResourceController {
    @Autowired
    ResourceService ResourceService;

    /**
     * 查询所有菜单
     * @return
     */
    @RequiresPermissions("sysmgr.resource.query")
    @RequestMapping(value="/list")
    public Result list(){
        List<ResourceNode> trees = ResourceService.findAll();

        Result result = new Result();
        result.setData(trees);
        result.setResult(true);
        result.setCode(Constants.TOKEN_CHECK_SUCCESS);
        return result;
    }

    /**
     * 保存
     * @param resource
     * @return
     */
    @RequiresPermissions("sysmgr.resource.save")
    @RequestMapping(value="/save")
    public Result save(@RequestBody Resource resource){
        return ResourceService.persist(resource);
    }

    /**
     * 删除
     * @param resource
     * @return
     */
    @RequiresPermissions("sysmgr.resource.delete")
    @RequestMapping(value="/delete")
    public Result dropById(@RequestBody Resource resource){
        Result result ;
        if(resource.getId()!=null){
            Resource delRes= new Resource();
            delRes.setId(resource.getId());
            delRes.setYnFlag("0");
            delRes.setEditor(UserContext.getCurrentUser().getAccount());
            delRes.setModifiedTime(Date.from(Instant.now()));
            result=new Result(ResourceService.updateById(delRes),null,null,Constants.TOKEN_CHECK_SUCCESS);
        }else{
            result = new Result(false, "", null ,Constants.PARAMETERS_MISSING);
        }
        return result;
    }
}
