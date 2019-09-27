package com.story.storyadmin.config.upload.support;

import com.story.storyadmin.config.upload.entity.FileSlot;
import com.story.storyadmin.domain.entity.sysmgr.Att;
import com.story.storyadmin.service.sysmgr.AttService;
import com.story.storyadmin.utils.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 默认的文件上传处理类
 */
public class DefaultFileResolver implements FileResolver {

	@Autowired
	private AttService attService;

	@Override
	public void resolveFile(MultipartRequestWrapper multipartRequestWrapper, FileSlot fileBatch) throws Exception {

		MultipartHttpServletRequest multipartRequest = multipartRequestWrapper.getRequest();

		String sourceUri = multipartRequest.getRequestURI();

		Map<String, MultipartFile> multipartMap = multipartRequest.getFileMap();

		String batchId = fileBatch.getSlotId();
		List<Att> batchFilesFromRequest = fileBatch.getSlotFiles();

		if (StringUtils.isNotEmpty(batchId)) {
			// 对于文件槽不为空的情况， 分两步:
			// 1. 先根据文件槽从数据库中取出已存在的文件。
			// 与传来的BatchFiles对比。
			// BatchFiles缺少的文件， 认为是此次要删除的；
			// BatchFiles多出的文件，不予处理。
			List<Att> batchFilesInDb = attService.findBySlotId(batchId);
			for (Att fileEntity : batchFilesInDb) {
				final Long fileId = fileEntity.getId();
				Object batchFileFromRequest = CollectionUtils.find(batchFilesFromRequest, new Predicate() {
					@Override
					public boolean evaluate(Object arg0) {
						Att f = (Att) arg0;
						if (fileId.equals(f.getId())) {
							return true;
						}

						return false;
					}
				});
				if (batchFileFromRequest == null) {
					// 删除
					attService.removeById(fileEntity.getId());
				}
			}
		} else {
			// 如果文件插槽为空
			// 只走第二步
			batchId = FileSlot.newSlotId();
			fileBatch.setSlotId(batchId);
		}

		// 2. 取Multipart文件， 保存文件体， 保存文件数据。
		for (Entry<String, MultipartFile> entry : multipartMap.entrySet()) {
			MultipartFile multipartFile = entry.getValue();
			try {
				attService.save(sourceUri, multipartFile, fileBatch);
			} catch (IllegalStateException | IOException e) {
				throw new Exception("sys.multipart.save.error");
			}
		}

	}
}
