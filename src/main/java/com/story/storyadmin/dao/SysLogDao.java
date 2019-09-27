package com.story.storyadmin.dao;

import com.story.storyadmin.domain.entity.sysmgr.SysLog;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * <p>
 * 附件表 Mapper 接口
 * </p>
 *
 * @author sunningjun
 * @since 2019-07-12
 */
public interface SysLogDao extends MongoRepository<SysLog, String> {

}
