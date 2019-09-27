package com.story.storyadmin.service.common.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.story.storyadmin.config.upload.support.FileArchiveStrategy;
import com.story.storyadmin.service.common.StorageService;
import com.story.storyadmin.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageServiceImpl implements StorageService {

	@Value("${file.multipart.baseDir}")
	private String baseDir;

	@Autowired
	protected FileArchiveStrategy fileArchiveStrategy;

	/**
	 * 
	 */
	@Override
	public Path store(String sourceUri, MultipartFile multipartFile) throws IllegalStateException, IOException {
		String originalFilename = multipartFile.getOriginalFilename();
		Path fileRelativePath = fileArchiveStrategy.createPath(sourceUri, originalFilename);

		Path fileAbsolutePath = this.getBaseDir().resolve(fileRelativePath);
		if (!Files.exists(fileAbsolutePath.getParent())) {
			Files.createDirectories(fileAbsolutePath.getParent());
			Files.createFile(fileAbsolutePath);
		}

		multipartFile.transferTo(fileAbsolutePath.toFile());

		return fileRelativePath;
	}

	/**
	* 
	*/
	@Override
	public Path load(String filename) {
		Path fileAbsolutePath = this.getBaseDir().resolve(filename);
		return fileAbsolutePath;
	}

	private Path getBaseDir() {
		if (StringUtils.isEmpty(this.baseDir)) {
			return Paths.get(System.getProperty("user.home")).resolve("e");
		}

		return Paths.get(this.baseDir);
	}
}
