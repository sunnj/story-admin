package com.story.storyadmin.web.sysmgr;

import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.domain.entity.sysmgr.Authority;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.AuthorityNode;
import com.story.storyadmin.service.sysmgr.AuthorityService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/sysmgr/authority")
public class AuthorityController {
    @Autowired
    AuthorityService authorityService;

    /**
     * 查询所有权限
     * @return
     */
    @RequiresPermissions("sysmgr.authority.query")
    @RequestMapping(value="/list")
    public Result list(){
        List<AuthorityNode> trees = authorityService.findAll();

        Result result = new Result();
        result.setData(trees);
        result.setResult(true);
        result.setCode(Constants.TOKEN_CHECK_SUCCESS);
        return result;
    }

    /**
     * 保存
     * @param authority
     * @return
     */
    @RequiresPermissions("sysmgr.authority.save")
    @RequestMapping(value="/save")
    public Result save(@RequestBody Authority authority){
        return authorityService.persist(authority);
    }

    /**
     * 删除
     * @param authority
     * @return
     */
    @RequiresPermissions("sysmgr.authority.delete")
    @RequestMapping(value="/delete")
    public Result dropById(@RequestBody Authority authority){
        Result result ;
        if(authority.getId()!=null){
            Authority delAuth= new Authority();
            delAuth.setId(authority.getId());
            delAuth.setYnFlag("0");
            delAuth.setEditor(UserContext.getCurrentUser().getAccount());
            delAuth.setModifiedTime(Date.from(Instant.now()));
            result=new Result(authorityService.updateById(delAuth),null,null,Constants.TOKEN_CHECK_SUCCESS);
        }else{
            result = new Result(false, "", null ,Constants.PARAMETERS_MISSING);
        }
        return result;
    }
}
