package com.story.storyadmin.web.baseinfo;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.constant.enumtype.YNFlagStatusEnum;
import com.story.storyadmin.domain.entity.baseinfo.Dict;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.service.baseinfo.DictService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author sunningjun
 * @since 2019-07-12
 */
@RestController
@RequestMapping("/baseinfo/dict")
public class DictController {

    @Autowired
    DictService dictService;

    /**
     * 分页查询
     * @param dict
     * @param pageNo
     * @param limit
     * @return
     */
    @RequiresPermissions("baseinfo.dict.query")
    @RequestMapping(value="/list",method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(Dict dict,
                       @RequestParam(defaultValue = "1")int pageNo,
                       @RequestParam(defaultValue = "10")int limit){
        Result result = new Result();
        Page<Dict> page = new Page(pageNo, limit);
        QueryWrapper<Dict> eWrapper = new QueryWrapper(dict);
        eWrapper.eq("yn_flag","1");
        IPage<Dict> list = dictService.page(page, eWrapper);
        result.setData(list);
        result.setResult(true);
        result.setCode(Constants.TOKEN_CHECK_SUCCESS);
        return result;
    }

    /**
     * 根据Id查询
     * @param dict
     * @return
     */
    @RequiresPermissions("baseinfo.dict.query")
    @RequestMapping(value="/find",method = {RequestMethod.POST})
    public Result findById(@RequestBody Dict dict){
        Dict rolebean= dictService.getById(dict.getId());
        Result result = new Result();
        result.setData(rolebean);
        result.setResult(true);
        result.setCode(Constants.TOKEN_CHECK_SUCCESS);
        return result;
    }

    /**
     * 保存
     * @param dict
     * @return
     */
    @RequiresPermissions("baseinfo.dict.save")
    @RequestMapping(value="/save",method = {RequestMethod.POST})
    public Result save(@RequestBody Dict dict){
        if(dict.getId()!=null){
            dictService.updateById(dict);
        }else{
            dict.setParentCode("");
            dict.setYnFlag(YNFlagStatusEnum.VALID.getCode());
            dict.setCreatedTime(Date.from(Instant.now()));
            dict.setEditor(UserContext.getCurrentUser().getAccount());
            dict.setCreator(UserContext.getCurrentUser().getAccount());
            dictService.save(dict);
        }
        Result result = new Result();
        result.setData(dict);
        result.setResult(true);
        return result;
    }

    /**
     * 删除
     * @param dict
     * @return
     */
    @RequiresPermissions("baseinfo.dict.delete")
    @RequestMapping(value="/delete",method = {RequestMethod.POST})
    public Result dropById(@RequestBody Dict dict){
        Result result ;
        if(dict.getId()!=null){
            Dict delDict= new Dict();
            delDict.setId(dict.getId());
            delDict.setYnFlag("0");
            delDict.setEditor(UserContext.getCurrentUser().getAccount());
            delDict.setModifiedTime(Date.from(Instant.now()));
            result=new Result(dictService.updateById(delDict),null,null,Constants.TOKEN_CHECK_SUCCESS);
        }else{
            result = new Result(false, "", null ,Constants.PARAMETERS_MISSING);
        }
        return result;
    }

    /**
     * 批量保存
     * @param dictList
     * @return
     */
    @RequiresPermissions("baseinfo.dict.save")
    @RequestMapping(value="/batch_save",method = {RequestMethod.POST})
    public Result batchSave(@RequestBody List<Dict> dictList){
        dictService.batchSave(dictList);
        Result result = new Result();
        result.setCode(Constants.TOKEN_CHECK_SUCCESS);
        result.setResult(true);
        return result;
    }
}
