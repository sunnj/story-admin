package com.story.storyadmin.service.baseinfo.impl;

import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.enumtype.YNFlagStatusEnum;
import com.story.storyadmin.domain.entity.baseinfo.Dict;
import com.story.storyadmin.mapper.baseinfo.DictMapper;
import com.story.storyadmin.service.baseinfo.DictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author sunningjun
 * @since 2019-07-12
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    @Transactional
    public Boolean batchSave(List<Dict> dictList) {
        for(Dict dict: dictList){
            if(dict.getId()!=null){
                this.updateById(dict);
            }else{
                dict.setYnFlag(YNFlagStatusEnum.VALID.getCode());
                dict.setCreatedTime(Date.from(Instant.now()));
                dict.setEditor(UserContext.getCurrentUser().getAccount());
                dict.setCreator(UserContext.getCurrentUser().getAccount());
                this.save(dict);
            }
        }
        return true;
    }
}
