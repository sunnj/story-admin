package com.story.storyadmin.web.sysmgr;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.domain.entity.sysmgr.Att;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.AttVo;
import com.story.storyadmin.service.sysmgr.AttService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 附件表 前端控制器
 * </p>
 *
 * @author sunningjun
 * @since 2019-07-02
 */
@RestController
@RequestMapping("/sysmgr/att")
public class AttController {

    @Autowired
    AttService attService;

    /**
     * 分页查询
     * @param att
     * @param pageNo
     * @param limit
     * @return
     */
    @RequiresPermissions("sysmgr.att.query")
    @RequestMapping(value="/list",method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(Att att,
                       @RequestParam(defaultValue = "1")int pageNo,
                       @RequestParam(defaultValue = "10")int limit){
        Result result = new Result();
        Page<Att> page = new Page(pageNo, limit);
        QueryWrapper<Att> eWrapper = new QueryWrapper(att);
        eWrapper.eq("yn_flag","1");
        IPage<Att> list = attService.page(page, eWrapper);
        result.setData(list);
        result.setResult(true);
        result.setCode(Constants.TOKEN_CHECK_SUCCESS);
        return result;
    }

    /**
     * 删除
     * @param att
     * @return
     */
    @RequiresPermissions("sysmgr.att.delete")
    @RequestMapping(value="/delete",method = {RequestMethod.POST})
    public Result dropById(@RequestBody Att att){
        Result result ;
        if(att.getId()!=null){
            result=new Result(attService.removeById(att.getId()),null,null,Constants.TOKEN_CHECK_SUCCESS);
        }else{
            result = new Result(false, "", null ,Constants.PARAMETERS_MISSING);
        }
        return result;
    }

    /**
     * 上传文件
     * @param attVo
     * @return
     */
    @RequiresPermissions("sysmgr.att.upload")
    @PostMapping(value="/upload")
    public Result upload(AttVo attVo){
        Result result = new Result(true,null,null,Constants.TOKEN_CHECK_SUCCESS);
        return result;
    }
}
