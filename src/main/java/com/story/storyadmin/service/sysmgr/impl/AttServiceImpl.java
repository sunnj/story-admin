package com.story.storyadmin.service.sysmgr.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.config.upload.entity.CategorialFileSlot;
import com.story.storyadmin.config.upload.entity.FileSlot;
import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.constant.enumtype.YNFlagStatusEnum;
import com.story.storyadmin.domain.entity.sysmgr.Att;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.mapper.sysmgr.AttMapper;
import com.story.storyadmin.service.common.StorageService;
import com.story.storyadmin.service.sysmgr.AttService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.story.storyadmin.utils.DateUtils;
import com.story.storyadmin.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 附件表 服务实现类
 * </p>
 *
 * @author sunningjun
 * @since 2019-07-12
 */
@Service
public class AttServiceImpl extends ServiceImpl<AttMapper, Att> implements AttService {

    @Autowired
    StorageService storageService;

    @Override
    public boolean removeById(Serializable id){
        Att delAtt= new Att();
        delAtt.setId((Long)id);
        delAtt.setYnFlag("0");
        if(UserContext.getCurrentUser()!=null) {
            delAtt.setEditor(UserContext.getCurrentUser().getAccount());
        }
        delAtt.setModifiedTime(Date.from(Instant.now()));
        return this.updateById(delAtt);
    }

    @Override
    public List<Att> findBySlotId(String slotId) {
        return this.findBySlotId(slotId, null);
    }

    @Override
    public List<Att> findBySlotId(String slotId, String category) {
        QueryWrapper<Att> attWrapper= new QueryWrapper<>();
        if (StringUtils.isNotEmpty(category)) {
            attWrapper.eq("slotId",slotId);
        }
        if (StringUtils.isNotEmpty(category)) {
            attWrapper.eq("file_cate",category);
        }
        attWrapper.eq("yn_flag","1");
        List<Att> attList= this.list(attWrapper);
        return attList;
    }

    @Override
    public Att save(String sourceUri, MultipartFile multipartFile) throws IllegalStateException, IOException {
        return this.save(sourceUri, multipartFile, FileSlot.newSlotId());
    }

    @Override
    public Att save(String sourceUri, MultipartFile multipartFile, String batchId)
            throws IllegalStateException,IOException {

        final String localSlotId = StringUtils.defaultString(batchId, FileSlot.newSlotId());

        return this.save(sourceUri, multipartFile, new FileSlot() {

            @Override
            public String getSlotId() {
                return localSlotId;
            }

            @Override
            public void setSlotId(String batchId) {
                // TODO Auto-generated method stub

            }

            @Override
            public List<Att> getSlotFiles() {
                // TODO Auto-generated method stub
                return null;
            }
        });
    }

    @Override
    public Att save(String sourceUri, MultipartFile multipartFile, FileSlot fileSlot)
            throws IllegalStateException,IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        Att fileEntity = new Att();
        int extendNameIndex=originalFilename.indexOf(".");
        if(extendNameIndex>=0){
            fileEntity.setType(originalFilename.substring(extendNameIndex).toLowerCase());
        }
        fileEntity.setOriginName(originalFilename);
        fileEntity.setFileSize(multipartFile.getSize());
        fileEntity.setSlotId(fileSlot.getSlotId());
        fileEntity.setYnFlag(YNFlagStatusEnum.VALID.getCode());
        if (UserContext.getCurrentUser() != null) {
            fileEntity.setCreator(UserContext.getCurrentUser().getAccount());
            fileEntity.setEditor(fileEntity.getCreator());
        }
        fileEntity.setCreatedTime(DateUtils.currentDate());
        fileEntity.setModifiedTime(fileEntity.getCreatedTime());
        if (fileSlot instanceof CategorialFileSlot) {
            fileEntity.setFileCate(((CategorialFileSlot) fileSlot).getSlotFileCategoryOnUploading());
        }

        // 保存文件
        Path path = storageService.store(sourceUri, multipartFile);
        fileEntity.setPath(path.toString());

        // 保存数据
        this.save(fileEntity);
        return fileEntity;
    }
}
