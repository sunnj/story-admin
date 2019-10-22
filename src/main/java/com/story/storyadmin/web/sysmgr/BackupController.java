package com.story.storyadmin.web.sysmgr;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.domain.entity.sysmgr.Backup;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.service.sysmgr.BackupService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;

/**
 * <p>
 * DB备份表 前端控制器
 * </p>
 *
 * @author sunnj
 * @since 2019-09-10
 */
@RestController
@RequestMapping("/sysmgr/backup")
public class BackupController {

    @Autowired
    BackupService backupService;

    /**
     * 分页查询
     * @param backup
     * @param pageNo
     * @param limit
     * @return
     */
    @RequiresPermissions("sysmgr.backup.query")
    @RequestMapping(value="/list",method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(Backup backup,
                       @RequestParam(defaultValue = "1")int pageNo,
                       @RequestParam(defaultValue = "10")int limit){
        Result result = new Result();
        Page<Backup> page = new Page(pageNo, limit);
        QueryWrapper<Backup> eWrapper = new QueryWrapper(backup);
        eWrapper.eq("yn_flag","1");
        IPage<Backup> list = backupService.page(page, eWrapper);
        result.setData(list);
        result.setResult(true);
        result.setCode(Constants.TOKEN_CHECK_SUCCESS);
        return result;
    }


    /**
     * 根据Id查询
     * @param backup
     * @return
     */
    @RequiresPermissions("sysmgr.backup.query")
    @RequestMapping(value="/find",method = {RequestMethod.POST})
    public Result findById(@RequestBody Backup backup){
        Backup backupBean= backupService.getById(backup.getId());
        Result result = new Result();
        result.setData(backupBean);
        result.setResult(true);
        result.setCode(Constants.TOKEN_CHECK_SUCCESS);
        return result;
    }

    /**
     * 删除
     * @param backup
     * @return
     */
    @RequiresPermissions("sysmgr.backup.delete")
    @RequestMapping(value="/delete",method = {RequestMethod.POST})
    public Result dropById(@RequestBody Backup backup){
        Result result ;
        if(backup.getId()!=null){
            Backup delBackup= new Backup();
            delBackup.setId(backup.getId());
            delBackup.setYnFlag("0");
            delBackup.setEditor(UserContext.getCurrentUser().getAccount());
            delBackup.setModifiedTime(Date.from(Instant.now()));
            result=new Result(backupService.updateById(delBackup),null,null,Constants.TOKEN_CHECK_SUCCESS);
        }else{
            result = new Result(false, "", null ,Constants.PARAMETERS_MISSING);
        }
        return result;
    }
}
