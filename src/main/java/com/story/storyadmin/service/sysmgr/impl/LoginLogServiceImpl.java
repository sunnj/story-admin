package com.story.storyadmin.service.sysmgr.impl;

import com.story.storyadmin.domain.entity.sysmgr.LoginLog;
import com.story.storyadmin.mapper.sysmgr.LoginLogMapper;
import com.story.storyadmin.service.sysmgr.LoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录日志 服务实现类
 * </p>
 *
 * @author sunnj
 * @since 2019-07-26
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

}
