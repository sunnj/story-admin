package com.story.storyadmin.service.sysmgr;

import com.baomidou.mybatisplus.extension.service.IService;
import com.story.storyadmin.domain.entity.sysmgr.Backup;
import com.story.storyadmin.domain.vo.Result;

/**
 * <p>
 * DB备份表 服务类
 * </p>
 *
 * @author sunningjun
 * @since 2019-09-10
 */
public interface BackupService extends IService<Backup> {

    /**
     * 备份
     */
    Result<Backup> backup();

    /**
     * 保存
     * @param backup
     * @return
     */
    Result persist(Backup backup);
}
