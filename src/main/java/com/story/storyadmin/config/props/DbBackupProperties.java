package com.story.storyadmin.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * 数据库备份配置
 * @author sunnj
 *
 */
@ConfigurationProperties(prefix = "dbbackup")
@Data
public class DbBackupProperties {
	private String servername;
	private String dbname;
	private String username;
	private String password;
	private String installPath;
	private String backupPath;
}
