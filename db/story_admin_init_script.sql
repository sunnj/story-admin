/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : story_admin

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 25/09/2019 15:27:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for st_att
-- ----------------------------
DROP TABLE IF EXISTS `st_att`;
CREATE TABLE `st_att`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `slot_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '批次',
  `file_cate` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类',
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件类型',
  `file_size` bigint(20) NULL DEFAULT NULL COMMENT '文件大小',
  `origin_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源文件名',
  `path` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存储路径',
  `description` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `yn_flag` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '附件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_att
-- ----------------------------
INSERT INTO `st_att` VALUES (1, NULL, 'fb135e8c-15c5-4248-91d1-ce6cfd762315', NULL, 'text/plain', 24, 'test.txt', 'sysmgr\\att\\upload\\2019\\09\\20190917103547386_.txt', NULL, '0', NULL, 'admin', '2019-09-17 02:35:45', '2019-09-17 03:10:06');
INSERT INTO `st_att` VALUES (2, NULL, '6e4b7792-b047-4bc5-a776-60ec1ea800bc', NULL, 'text/plain', 24, 'test.txt', 'sysmgr\\att\\upload\\2019\\09\\20190917104316003_.txt', NULL, '0', NULL, 'admin', '2019-09-17 02:43:13', '2019-09-17 03:10:11');
INSERT INTO `st_att` VALUES (3, NULL, '1123b4ff-dc8e-463a-a667-a98b5fac286b', NULL, 'text/plain', 24, 'test.txt', 'sysmgr\\att\\upload\\2019\\09\\20190917104429759_.txt', NULL, '1', 'admin', 'admin', '2019-09-17 02:44:27', '2019-09-17 02:44:27');
INSERT INTO `st_att` VALUES (4, NULL, 'a5805f49-50ae-482b-a08a-4f5e481d0bb5', NULL, 'text/plain', 24, 'test.txt', 'sysmgr\\att\\upload\\2019\\09\\20190917104607876_.txt', NULL, '1', 'admin', 'admin', '2019-09-17 02:46:07', '2019-09-17 02:46:07');
INSERT INTO `st_att` VALUES (5, NULL, 'ecd4d39d-1c12-40d6-af53-3e25e298a577', NULL, 'text/plain', 24, 'test.txt', 'sysmgr\\att\\upload\\2019\\09\\20190917105941981_.txt', NULL, '1', 'admin', 'admin', '2019-09-17 02:59:41', '2019-09-17 02:59:41');
INSERT INTO `st_att` VALUES (6, NULL, '4ca31b41-9e3f-4e45-a664-83224b42dc17', NULL, 'text/plain', 24, 'test.txt', 'sysmgr\\att\\upload\\2019\\09\\20190917110251321_.txt', NULL, '1', 'admin', 'admin', '2019-09-17 03:02:49', '2019-09-17 03:02:49');
INSERT INTO `st_att` VALUES (7, NULL, '9317023f-0b72-4709-a452-c6631d26988a', NULL, 'text/plain', 24, 'test.txt', 'sysmgr\\att\\upload\\2019\\09\\20190917111001050_.txt', NULL, '1', 'admin', 'admin', '2019-09-17 03:10:01', '2019-09-17 03:10:01');
INSERT INTO `st_att` VALUES (8, NULL, '35f2c37a-2b8e-4d2c-a9be-782656897c96', NULL, 'application/octet-stream', 96, 'test.rar', 'sysmgr\\att\\upload\\2019\\09\\20190917111905377_.rar', NULL, '1', 'admin', 'admin', '2019-09-17 03:19:05', '2019-09-17 03:19:05');
INSERT INTO `st_att` VALUES (9, NULL, '1829f0c9-6aa8-4634-a206-28b50d6d39d4', NULL, 'application/octet-stream', 96, 'test.rar', 'sysmgr\\att\\upload\\2019\\09\\20190917112253117_.rar', NULL, '1', 'admin', 'admin', '2019-09-17 03:22:53', '2019-09-17 03:22:53');
INSERT INTO `st_att` VALUES (10, NULL, '8df3f2af-3830-496a-afee-af76bca3d699', NULL, '.rar', 96, 'test.rar', 'sysmgr\\att\\upload\\2019\\09\\20190917112513577_.rar', NULL, '1', 'admin', 'admin', '2019-09-17 03:25:14', '2019-09-17 03:25:14');

-- ----------------------------
-- Table structure for st_authority
-- ----------------------------
DROP TABLE IF EXISTS `st_authority`;
CREATE TABLE `st_authority`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限名称',
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限编码',
  `full_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '编号路径',
  `authority_desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限描述',
  `show_order` int(11) NULL DEFAULT NULL COMMENT '排序',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '父Id',
  `yn_flag` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_authority
-- ----------------------------
INSERT INTO `st_authority` VALUES (1, '公共', 'public', '0', NULL, 1, 0, '1', NULL, NULL, NULL, '2019-07-26 16:44:51');
INSERT INTO `st_authority` VALUES (2, '系统管理', 'sysmgr', '0', NULL, 1, 0, '1', NULL, NULL, NULL, '2019-07-26 11:56:42');
INSERT INTO `st_authority` VALUES (3, '用户管理', 'sysmgr.user', '0-2', NULL, 1, 2, '1', NULL, NULL, NULL, '2019-07-26 18:44:24');
INSERT INTO `st_authority` VALUES (4, '角色管理', 'sysmgr.rule', '0-2', NULL, 2, 2, '1', NULL, NULL, NULL, '2019-07-26 11:53:51');
INSERT INTO `st_authority` VALUES (5, '菜单管理', 'sysmgr.resource', '0-2', NULL, 3, 2, '1', NULL, NULL, NULL, '2019-07-26 18:44:50');
INSERT INTO `st_authority` VALUES (6, '权限管理', 'sysmgr.authority', '0-2', NULL, 4, 2, '1', NULL, NULL, NULL, '2019-07-26 18:44:53');
INSERT INTO `st_authority` VALUES (7, '查询用户', 'sysmgr.user.query', '0-2-3', NULL, 1, 3, '1', NULL, NULL, NULL, '2019-07-26 18:44:57');
INSERT INTO `st_authority` VALUES (8, '编辑用户', 'sysmgr.user.save', '0-2-3', NULL, 2, 3, '1', NULL, NULL, NULL, '2019-07-26 18:45:00');
INSERT INTO `st_authority` VALUES (9, '删除用户', 'sysmgr.user.delete', '0-2-3', NULL, 3, 3, '1', NULL, NULL, NULL, '2019-07-26 18:45:04');
INSERT INTO `st_authority` VALUES (10, '查询角色', 'sysmgr.role.query', '0-2-4', NULL, 1, 4, '1', NULL, NULL, NULL, '2019-07-26 18:45:06');
INSERT INTO `st_authority` VALUES (11, '编辑角色', 'sysmgr.role.save', '0-2-4', NULL, 2, 4, '1', NULL, NULL, NULL, '2019-07-26 18:45:09');
INSERT INTO `st_authority` VALUES (12, '删除角色', 'sysmgr.role.delete', '0-2-4', NULL, 3, 4, '1', NULL, NULL, NULL, '2019-07-26 18:45:13');
INSERT INTO `st_authority` VALUES (13, '查询菜单', 'sysmgr.resource.query', '0-2-5', NULL, 1, 5, '1', NULL, NULL, NULL, '2019-07-26 11:50:34');
INSERT INTO `st_authority` VALUES (14, '编辑菜单', 'sysmgr.resource.save', '0-2-5', NULL, 2, 5, '1', NULL, NULL, NULL, '2019-07-26 18:45:23');
INSERT INTO `st_authority` VALUES (15, '删除菜单', 'sysmgr.resource.delete', '0-2-5', NULL, 3, 5, '1', NULL, NULL, NULL, '2019-07-26 18:45:26');
INSERT INTO `st_authority` VALUES (16, '查询权限', 'sysmgr.authority.query', '0-2-6', NULL, 1, 6, '1', NULL, NULL, NULL, '2019-07-26 18:45:28');
INSERT INTO `st_authority` VALUES (17, '编辑权限', 'sysmgr.authority.save', '0-2-6', NULL, 2, 6, '1', NULL, NULL, NULL, '2019-07-26 18:45:31');
INSERT INTO `st_authority` VALUES (18, '删除权限', 'sysmgr.authority.delete', '0-2-6', NULL, 3, 6, '1', NULL, NULL, NULL, '2019-07-26 18:45:35');
INSERT INTO `st_authority` VALUES (30, '登录日志', 'sysmgr.loginlog', '0-2', NULL, 5, 2, '1', 'admin', 'admin', '2019-07-26 03:11:50', '2019-07-26 18:45:38');
INSERT INTO `st_authority` VALUES (31, '查询日志', 'sysmgr.loginlog.query', '0-2-30', NULL, 1, 30, '1', 'admin', 'admin', '2019-07-26 03:12:14', '2019-07-26 11:37:46');
INSERT INTO `st_authority` VALUES (32, '删除日志', 'sysmgr.loginlog.delete', '0-2-30', NULL, 2, 30, '1', 'admin', 'admin', '2019-07-26 03:12:44', '2019-07-26 11:37:54');
INSERT INTO `st_authority` VALUES (33, '基础信息', 'baseinfo', '0', NULL, 3, 0, '1', 'admin', 'admin', '2019-07-26 03:13:11', '2019-07-26 03:13:11');
INSERT INTO `st_authority` VALUES (34, '字典管理', 'baseinfo.dict', '0-33', NULL, 1, 33, '1', 'admin', 'admin', '2019-07-26 03:13:31', '2019-07-26 03:13:31');
INSERT INTO `st_authority` VALUES (35, '查询字典', 'baseinfo.dict.query', '0-33-34', NULL, 1, 34, '1', 'admin', 'admin', '2019-07-26 03:14:01', '2019-07-26 11:37:20');
INSERT INTO `st_authority` VALUES (36, '编辑字典', 'baseinfo.dict.save', '0-33-34', NULL, 2, 34, '1', 'admin', 'admin', '2019-07-26 03:14:43', '2019-07-26 11:37:30');
INSERT INTO `st_authority` VALUES (37, '删除字典', 'baseinfo.dict.delete', '0-33-34', NULL, 3, 34, '1', 'admin', 'admin', '2019-07-26 03:15:03', '2019-07-26 11:37:38');
INSERT INTO `st_authority` VALUES (38, '附件管理', 'sysmgr.att', '0-2', NULL, 6, 2, '1', 'admin', 'admin', '2019-09-24 13:03:19', '2019-09-24 21:05:50');
INSERT INTO `st_authority` VALUES (39, '查询附件', 'sysmgr.att.query', '0-2-38', NULL, 1, 38, '1', 'admin', 'admin', '2019-09-24 13:05:23', '2019-09-24 21:06:07');
INSERT INTO `st_authority` VALUES (40, '删除附件', 'sysmgr.att.delete', '0-2-38', NULL, 2, 38, '1', 'admin', 'admin', '2019-09-24 13:06:55', '2019-09-24 21:08:27');
INSERT INTO `st_authority` VALUES (41, '系统日志', 'sysmgr.syslog', '0-2', NULL, 7, 2, '1', 'admin', 'admin', '2019-09-24 13:09:29', '2019-09-24 21:09:48');
INSERT INTO `st_authority` VALUES (42, '查询系统日志', 'sysmgr.syslog.query', '0-2-41', NULL, 1, 41, '1', 'admin', 'admin', '2019-09-24 13:13:39', '2019-09-24 13:13:39');
INSERT INTO `st_authority` VALUES (43, '系统备份', 'sysmgr.backup', '0-2', NULL, 8, 2, '1', 'admin', 'admin', '2019-09-25 06:10:01', '2019-09-25 06:10:01');
INSERT INTO `st_authority` VALUES (44, '查询备份', 'sysmgr.backup.query', '0-2-43', NULL, 1, 43, '1', 'admin', 'admin', '2019-09-25 06:10:15', '2019-09-25 06:10:15');
INSERT INTO `st_authority` VALUES (45, '删除备份', 'sysmgr.backup.delete', '0-2-43', NULL, 2, 43, '1', 'admin', 'admin', '2019-09-25 06:10:35', '2019-09-25 06:12:43');
INSERT INTO `st_authority` VALUES (46, '定时任务', 'sysmgr.schedulejob', '0-2', NULL, 9, 2, '1', 'admin', 'admin', '2019-09-25 06:11:03', '2019-09-25 06:11:17');
INSERT INTO `st_authority` VALUES (47, '查询任务', 'sysmgr.schedulejob.query', '0-2-46', NULL, 1, 46, '1', 'admin', 'admin', '2019-09-25 06:11:35', '2019-09-25 06:12:34');
INSERT INTO `st_authority` VALUES (48, '编辑任务', 'sysmgr.schedulejob.save', '0-2-46', NULL, 2, 46, '1', 'admin', 'admin', '2019-09-25 06:12:04', '2019-09-25 06:12:04');
INSERT INTO `st_authority` VALUES (49, '删除任务', 'sysmgr.schedulejob.delete', '0-2-46', NULL, 3, 46, '1', 'admin', 'admin', '2019-09-25 06:12:25', '2019-09-25 06:12:25');
INSERT INTO `st_authority` VALUES (50, '上传附件', 'sysmgr.att.upload', '0-2-38', NULL, 3, 38, '1', 'admin', 'admin', '2019-09-25 06:15:38', '2019-09-25 06:15:57');
INSERT INTO `st_authority` VALUES (51, '个人空间', 'tool', '0', NULL, 10, 0, '1', 'admin', 'admin', '2019-09-25 07:11:47', '2019-09-25 07:11:47');
INSERT INTO `st_authority` VALUES (52, '待办事项', 'tool.todo', '0-51', NULL, 1, 51, '1', 'admin', 'admin', '2019-09-25 07:13:25', '2019-09-25 07:13:25');
INSERT INTO `st_authority` VALUES (53, '查询待办事项', 'tool.todo.query', '0-51-52', NULL, 1, 52, '1', 'admin', 'admin', '2019-09-25 07:13:41', '2019-09-25 07:13:41');
INSERT INTO `st_authority` VALUES (54, '编辑待办事项', 'tool.todo.save', '0-51-52', NULL, 2, 52, '1', 'admin', 'admin', '2019-09-25 07:14:22', '2019-09-25 07:14:22');
INSERT INTO `st_authority` VALUES (55, '删除待办事项', 'tool.todo.delete', '0-51-52', NULL, 3, 52, '1', 'admin', 'admin', '2019-09-25 07:14:45', '2019-09-25 07:14:45');

-- ----------------------------
-- Table structure for st_backup
-- ----------------------------
DROP TABLE IF EXISTS `st_backup`;
CREATE TABLE `st_backup`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `backup_date` datetime(0) NULL DEFAULT NULL,
  `backup_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `backup_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `db_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_size` bigint(20) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `runtime` bigint(20) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `yn_flag` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'DB备份表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_backup
-- ----------------------------
INSERT INTO `st_backup` VALUES (34, '2019-09-10 10:28:01', '2019-09-10 数据备份', '/201909/1568111280776.sql', 'story_admin', 38289, NULL, 0, 10, '1', NULL, NULL, '2019-09-10 10:28:01', '2019-09-10 10:28:01');
INSERT INTO `st_backup` VALUES (35, '2019-09-10 10:29:00', '2019-09-10 数据备份', '/201909/1568111340472.sql', 'story_admin', 38491, NULL, 0, 10, '1', NULL, NULL, '2019-09-10 10:29:00', '2019-09-10 10:29:00');
INSERT INTO `st_backup` VALUES (36, '2019-09-10 10:30:00', '2019-09-10 数据备份', '/201909/1568111400482.sql', 'story_admin', 38660, NULL, 0, 10, '0', NULL, 'admin', '2019-09-10 10:30:00', '2019-09-10 11:38:42');

-- ----------------------------
-- Table structure for st_dict
-- ----------------------------
DROP TABLE IF EXISTS `st_dict`;
CREATE TABLE `st_dict`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '编码',
  `chn_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '中文名称',
  `eng_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '英文名称',
  `show_order` int(11) NULL DEFAULT NULL COMMENT '排序',
  `parent_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '父编码',
  `yn_flag` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_dict
-- ----------------------------
INSERT INTO `st_dict` VALUES (1, 'true_or_false', '是否', 'YesOrNo', 1, '', '1', 'admin', 'admin', '2019-07-25 09:54:56', '2019-07-25 18:05:07');
INSERT INTO `st_dict` VALUES (2, '1', '是', 'Yes', 1, 'true_or_false', '1', 'admin', 'admin', '2019-07-25 09:54:56', '2019-07-25 20:10:18');
INSERT INTO `st_dict` VALUES (3, '0', '否', 'No', 2, 'true_or_false', '1', 'admin', 'admin', '2019-07-25 09:54:56', '2019-07-25 20:10:20');
INSERT INTO `st_dict` VALUES (4, 'effective_or_ineffective', '有效无效', NULL, 2, '', '1', 'admin', 'admin', '2019-07-25 09:54:56', '2019-07-25 17:57:15');
INSERT INTO `st_dict` VALUES (5, '1', '有效', 'Effective', 1, 'effective_or_ineffective', '1', 'admin', 'admin', '2019-07-25 09:54:56', '2019-07-25 20:06:09');
INSERT INTO `st_dict` VALUES (6, '0', '无效', 'Ineffective', 2, 'effective_or_ineffective', '1', 'admin', 'admin', '2019-07-25 09:54:56', '2019-07-25 20:06:11');
INSERT INTO `st_dict` VALUES (7, 'enable_or_disable', '启用或禁用', NULL, 3, '', '1', 'admin', 'admin', '2019-07-25 11:44:38', '2019-07-25 17:57:15');
INSERT INTO `st_dict` VALUES (8, '1', '启用', 'Enable', 1, 'enable_or_disable', '1', 'admin', 'admin', '2019-07-25 09:54:56', '2019-07-26 10:31:54');
INSERT INTO `st_dict` VALUES (9, '2', '禁用', 'Disabled', 2, 'enable_or_disable', '1', 'admin', 'admin', '2019-07-25 09:54:56', '2019-07-26 10:31:57');

-- ----------------------------
-- Table structure for st_login_log
-- ----------------------------
DROP TABLE IF EXISTS `st_login_log`;
CREATE TABLE `st_login_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  `content` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '内容',
  `yn_flag` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 83 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '登录日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_login_log
-- ----------------------------
INSERT INTO `st_login_log` VALUES (1, 'admin', '2019-07-26 05:50:21', '登录成功', '1', 'admin', 'admin', '2019-07-26 05:50:21', '2019-07-26 13:50:55');
INSERT INTO `st_login_log` VALUES (2, 'admin', '2019-07-26 08:31:30', '登录成功', '1', 'admin', 'admin', '2019-07-26 08:31:30', '2019-07-26 16:31:30');
INSERT INTO `st_login_log` VALUES (3, 'admin', '2019-07-26 08:58:40', '登录成功', '1', 'admin', 'admin', '2019-07-26 08:58:40', '2019-07-26 16:58:39');
INSERT INTO `st_login_log` VALUES (4, 'admin', '2019-07-26 09:04:12', '登录成功', '1', 'admin', 'admin', '2019-07-26 09:04:12', '2019-07-26 17:04:12');
INSERT INTO `st_login_log` VALUES (5, 'admin', '2019-07-26 11:35:11', '登录成功', '1', 'admin', 'admin', '2019-07-26 11:35:11', '2019-07-26 19:35:11');
INSERT INTO `st_login_log` VALUES (6, 'admin', '2019-07-29 06:06:49', '登录成功', '1', 'admin', 'admin', '2019-07-29 06:06:49', '2019-07-29 14:06:48');
INSERT INTO `st_login_log` VALUES (7, 'admin', '2019-07-29 06:20:18', '登录成功', '1', 'admin', 'admin', '2019-07-29 06:20:18', '2019-07-29 14:20:18');
INSERT INTO `st_login_log` VALUES (8, 'admin', '2019-07-29 09:12:12', '登录成功', '1', 'admin', 'admin', '2019-07-29 09:12:12', '2019-07-29 17:12:11');
INSERT INTO `st_login_log` VALUES (9, 'admin', '2019-07-30 11:36:51', '登录成功', '1', 'admin', 'admin', '2019-07-30 11:36:51', '2019-07-30 19:36:50');
INSERT INTO `st_login_log` VALUES (10, 'admin', '2019-07-30 11:53:40', '登录成功', '1', 'admin', 'admin', '2019-07-30 11:53:40', '2019-07-30 19:53:39');
INSERT INTO `st_login_log` VALUES (11, 'admin', '2019-08-01 06:03:23', '登录成功', '1', 'admin', 'admin', '2019-08-01 06:03:23', '2019-08-01 14:03:22');
INSERT INTO `st_login_log` VALUES (12, 'admin', '2019-08-01 06:34:26', '登录成功', '1', 'admin', 'admin', '2019-08-01 06:34:26', '2019-08-01 14:34:25');
INSERT INTO `st_login_log` VALUES (13, 'admin', '2019-08-03 12:43:20', '登录成功', '1', 'admin', 'admin', '2019-08-03 12:43:20', '2019-08-03 20:43:20');
INSERT INTO `st_login_log` VALUES (14, 'admin', '2019-08-05 02:41:57', '登录成功', '1', 'admin', 'admin', '2019-08-05 02:41:57', '2019-08-05 10:41:57');
INSERT INTO `st_login_log` VALUES (15, 'admin', '2019-08-05 11:45:57', '登录成功', '1', 'admin', 'admin', '2019-08-05 11:45:57', '2019-08-05 19:45:56');
INSERT INTO `st_login_log` VALUES (16, 'admin', '2019-08-07 02:31:36', '登录成功', '1', 'admin', 'admin', '2019-08-07 02:31:36', '2019-08-07 10:31:35');
INSERT INTO `st_login_log` VALUES (17, 'admin', '2019-08-12 06:03:37', '登录成功', '1', 'admin', 'admin', '2019-08-12 06:03:37', '2019-08-12 14:03:36');
INSERT INTO `st_login_log` VALUES (18, 'admin', '2019-08-12 06:24:23', '登录成功', '1', 'admin', 'admin', '2019-08-12 06:24:23', '2019-08-12 14:24:22');
INSERT INTO `st_login_log` VALUES (19, 'admin', '2019-08-12 06:45:17', '登录成功', '1', 'admin', 'admin', '2019-08-12 06:45:17', '2019-08-12 14:45:16');
INSERT INTO `st_login_log` VALUES (20, 'admin', '2019-08-12 06:45:58', '登录成功', '1', 'admin', 'admin', '2019-08-12 06:45:58', '2019-08-12 14:45:58');
INSERT INTO `st_login_log` VALUES (21, 'admin', '2019-08-12 06:46:36', '登录成功', '1', 'admin', 'admin', '2019-08-12 06:46:36', '2019-08-12 14:46:36');
INSERT INTO `st_login_log` VALUES (22, 'admin', '2019-08-12 07:38:59', '登录成功', '1', 'admin', 'admin', '2019-08-12 07:38:59', '2019-08-12 15:38:58');
INSERT INTO `st_login_log` VALUES (23, 'admin', '2019-08-12 08:22:30', '登录成功', '1', 'admin', 'admin', '2019-08-12 08:22:30', '2019-08-12 16:22:29');
INSERT INTO `st_login_log` VALUES (24, 'admin', '2019-08-12 09:40:29', '登录成功', '1', 'admin', 'admin', '2019-08-12 09:40:29', '2019-08-12 17:40:29');
INSERT INTO `st_login_log` VALUES (25, 'admin', '2019-08-12 10:47:12', '登录成功', '1', 'admin', 'admin', '2019-08-12 10:47:12', '2019-08-12 18:47:12');
INSERT INTO `st_login_log` VALUES (26, 'admin', '2019-08-13 01:37:26', '登录成功', '1', 'admin', 'admin', '2019-08-13 01:37:26', '2019-08-13 09:37:25');
INSERT INTO `st_login_log` VALUES (27, 'admin', '2019-08-13 05:48:12', '登录成功', '1', 'admin', 'admin', '2019-08-13 05:48:12', '2019-08-13 13:48:11');
INSERT INTO `st_login_log` VALUES (28, 'admin', '2019-08-13 07:03:36', '登录成功', '1', 'admin', 'admin', '2019-08-13 07:03:36', '2019-08-13 15:03:35');
INSERT INTO `st_login_log` VALUES (29, 'admin', '2019-08-13 07:52:05', '登录成功', '1', 'admin', 'admin', '2019-08-13 07:52:05', '2019-08-13 15:52:05');
INSERT INTO `st_login_log` VALUES (30, 'admin', '2019-08-14 06:04:20', '登录成功', '1', 'admin', 'admin', '2019-08-14 06:04:20', '2019-08-14 14:04:20');
INSERT INTO `st_login_log` VALUES (31, 'admin', '2019-08-14 09:57:30', '登录成功', '1', 'admin', 'admin', '2019-08-14 09:57:30', '2019-08-14 17:57:30');
INSERT INTO `st_login_log` VALUES (32, 'admin', '2019-08-15 02:05:58', '登录成功', '1', 'admin', 'admin', '2019-08-15 02:05:58', '2019-08-15 10:05:57');
INSERT INTO `st_login_log` VALUES (33, 'admin', '2019-08-15 05:54:23', '登录成功', '1', 'admin', 'admin', '2019-08-15 05:54:23', '2019-08-15 13:54:23');
INSERT INTO `st_login_log` VALUES (34, 'admin', '2019-08-16 04:52:55', '登录成功', '1', 'admin', 'admin', '2019-08-16 04:52:55', '2019-08-16 12:52:55');
INSERT INTO `st_login_log` VALUES (35, 'admin', '2019-08-16 05:03:52', '登录成功', '1', 'admin', 'admin', '2019-08-16 05:03:52', '2019-08-16 13:03:51');
INSERT INTO `st_login_log` VALUES (36, 'admin', '2019-08-16 05:06:07', '登录成功', '1', 'admin', 'admin', '2019-08-16 05:06:07', '2019-08-16 13:06:06');
INSERT INTO `st_login_log` VALUES (37, 'admin', '2019-08-16 06:07:13', '登录成功', '1', 'admin', 'admin', '2019-08-16 06:07:13', '2019-08-16 14:07:12');
INSERT INTO `st_login_log` VALUES (38, 'admin', '2019-08-16 06:07:46', '登录成功', '1', 'admin', 'admin', '2019-08-16 06:07:46', '2019-08-16 14:07:45');
INSERT INTO `st_login_log` VALUES (39, 'admin', '2019-08-16 06:08:04', '登录成功', '1', 'admin', 'admin', '2019-08-16 06:08:04', '2019-08-16 14:08:03');
INSERT INTO `st_login_log` VALUES (40, 'admin', '2019-08-18 12:37:26', '登录成功', '1', 'admin', 'admin', '2019-08-18 12:37:26', '2019-08-18 20:37:26');
INSERT INTO `st_login_log` VALUES (41, 'admin', '2019-08-19 01:35:08', '登录成功', '1', 'admin', 'admin', '2019-08-19 01:35:08', '2019-08-19 09:35:08');
INSERT INTO `st_login_log` VALUES (42, 'admin', '2019-08-19 03:02:58', '登录成功', '1', 'admin', 'admin', '2019-08-19 03:02:58', '2019-08-19 11:02:58');
INSERT INTO `st_login_log` VALUES (43, 'admin', '2019-08-19 03:03:04', '登录成功', '1', 'admin', 'admin', '2019-08-19 03:03:04', '2019-08-19 11:03:04');
INSERT INTO `st_login_log` VALUES (44, 'admin', '2019-08-19 03:03:07', '登录成功', '1', 'admin', 'admin', '2019-08-19 03:03:07', '2019-08-19 11:03:07');
INSERT INTO `st_login_log` VALUES (45, 'admin', '2019-08-19 03:04:53', '登录成功', '1', 'admin', 'admin', '2019-08-19 03:04:53', '2019-08-19 11:04:52');
INSERT INTO `st_login_log` VALUES (46, 'admin', '2019-08-19 03:18:46', '登录成功', '1', 'admin', 'admin', '2019-08-19 03:18:46', '2019-08-19 11:18:45');
INSERT INTO `st_login_log` VALUES (47, 'admin', '2019-08-19 10:22:09', '登录成功', '1', 'admin', 'admin', '2019-08-19 10:22:09', '2019-08-19 18:22:08');
INSERT INTO `st_login_log` VALUES (48, 'admin', '2019-08-23 08:42:16', '登录成功', '1', 'admin', 'admin', '2019-08-23 08:42:16', '2019-08-23 16:42:15');
INSERT INTO `st_login_log` VALUES (49, 'admin', '2019-08-26 11:59:42', '登录成功', '1', 'admin', 'admin', '2019-08-26 11:59:42', '2019-08-26 19:59:41');
INSERT INTO `st_login_log` VALUES (50, 'admin', '2019-08-28 03:27:06', '登录成功', '1', 'admin', 'admin', '2019-08-28 03:27:06', '2019-08-28 11:27:06');
INSERT INTO `st_login_log` VALUES (51, 'admin', '2019-08-30 06:05:28', '登录成功', '1', 'admin', 'admin', '2019-08-30 06:05:28', '2019-08-30 14:05:28');
INSERT INTO `st_login_log` VALUES (52, 'admin', '2019-09-06 02:17:43', '登录成功', '1', 'admin', 'admin', '2019-09-06 02:17:43', '2019-09-06 10:17:43');
INSERT INTO `st_login_log` VALUES (53, 'admin', '2019-09-06 12:01:25', '登录成功', '1', 'admin', 'admin', '2019-09-06 12:01:25', '2019-09-06 20:01:24');
INSERT INTO `st_login_log` VALUES (54, 'admin', '2019-09-06 12:02:53', '登录成功', '1', 'admin', 'admin', '2019-09-06 12:02:53', '2019-09-06 20:02:53');
INSERT INTO `st_login_log` VALUES (55, 'admin', '2019-09-07 12:49:07', '登录成功', '1', 'admin', 'admin', '2019-09-07 12:49:07', '2019-09-07 20:49:06');
INSERT INTO `st_login_log` VALUES (56, 'admin', '2019-09-07 12:54:26', '登录成功', '1', 'admin', 'admin', '2019-09-07 12:54:26', '2019-09-07 20:54:26');
INSERT INTO `st_login_log` VALUES (57, 'admin', '2019-09-07 12:55:38', '登录成功', '1', 'admin', 'admin', '2019-09-07 12:55:38', '2019-09-07 20:55:38');
INSERT INTO `st_login_log` VALUES (58, 'admin', '2019-09-07 12:56:42', '登录成功', '1', 'admin', 'admin', '2019-09-07 12:56:42', '2019-09-07 20:56:41');
INSERT INTO `st_login_log` VALUES (59, 'admin', '2019-09-07 12:59:28', '登录成功', '1', 'admin', 'admin', '2019-09-07 12:59:28', '2019-09-07 20:59:27');
INSERT INTO `st_login_log` VALUES (60, 'admin', '2019-09-07 13:05:07', '登录成功', '1', 'admin', 'admin', '2019-09-07 13:05:07', '2019-09-07 21:05:07');
INSERT INTO `st_login_log` VALUES (61, 'admin', '2019-09-07 13:06:19', '登录成功', '1', 'admin', 'admin', '2019-09-07 13:06:19', '2019-09-07 21:06:19');
INSERT INTO `st_login_log` VALUES (62, 'admin', '2019-09-07 13:08:12', '登录成功', '1', 'admin', 'admin', '2019-09-07 13:08:12', '2019-09-07 21:08:11');
INSERT INTO `st_login_log` VALUES (63, 'admin', '2019-09-07 13:10:11', '登录成功', '1', 'admin', 'admin', '2019-09-07 13:10:11', '2019-09-07 21:10:10');
INSERT INTO `st_login_log` VALUES (64, 'admin', '2019-09-07 13:12:38', '登录成功', '1', 'admin', 'admin', '2019-09-07 13:12:38', '2019-09-07 21:12:38');
INSERT INTO `st_login_log` VALUES (65, 'admin', '2019-09-07 13:26:07', '登录成功', '1', 'admin', 'admin', '2019-09-07 13:26:07', '2019-09-07 21:26:07');
INSERT INTO `st_login_log` VALUES (66, 'admin', '2019-09-09 02:32:46', '登录成功', '1', 'admin', 'admin', '2019-09-09 02:32:46', '2019-09-09 10:32:46');
INSERT INTO `st_login_log` VALUES (67, 'admin', '2019-09-09 06:42:40', '登录成功', '1', 'admin', 'admin', '2019-09-09 06:42:40', '2019-09-09 14:42:39');
INSERT INTO `st_login_log` VALUES (68, 'admin', '2019-09-10 03:27:06', '登录成功', '1', 'admin', 'admin', '2019-09-10 03:27:06', '2019-09-10 11:27:05');
INSERT INTO `st_login_log` VALUES (69, 'admin', '2019-09-10 12:09:47', '登录成功', '1', 'admin', 'admin', '2019-09-10 12:09:47', '2019-09-10 20:09:47');
INSERT INTO `st_login_log` VALUES (70, 'admin', '2019-09-11 02:10:38', '登录成功', '1', 'admin', 'admin', '2019-09-11 02:10:38', '2019-09-11 10:10:38');
INSERT INTO `st_login_log` VALUES (71, 'admin', '2019-09-12 01:39:31', '登录成功', '1', 'admin', 'admin', '2019-09-12 01:39:31', '2019-09-12 09:39:31');
INSERT INTO `st_login_log` VALUES (72, 'admin', '2019-09-12 02:02:23', '登录成功', '1', 'admin', 'admin', '2019-09-12 02:02:23', '2019-09-12 10:02:22');
INSERT INTO `st_login_log` VALUES (73, 'admin', '2019-09-12 07:58:35', '登录成功', '1', 'admin', 'admin', '2019-09-12 07:58:35', '2019-09-12 15:58:34');
INSERT INTO `st_login_log` VALUES (74, 'admin', '2019-09-12 11:25:01', '登录成功', '1', 'admin', 'admin', '2019-09-12 11:25:01', '2019-09-12 19:25:00');
INSERT INTO `st_login_log` VALUES (75, 'admin', '2019-09-16 06:15:41', '登录成功', '1', 'admin', 'admin', '2019-09-16 06:15:41', '2019-09-16 14:15:41');
INSERT INTO `st_login_log` VALUES (76, 'admin', '2019-09-16 07:03:26', '登录成功', '1', 'admin', 'admin', '2019-09-16 07:03:26', '2019-09-16 15:03:26');
INSERT INTO `st_login_log` VALUES (77, 'admin', '2019-09-17 02:09:06', '登录成功', '1', 'admin', 'admin', '2019-09-17 02:09:06', '2019-09-17 10:09:06');
INSERT INTO `st_login_log` VALUES (78, 'admin', '2019-09-17 10:09:14', '登录成功', '1', 'admin', 'admin', '2019-09-17 10:09:14', '2019-09-17 18:09:13');
INSERT INTO `st_login_log` VALUES (79, 'admin', '2019-09-18 06:26:24', '登录成功', '1', 'admin', 'admin', '2019-09-18 06:26:24', '2019-09-18 14:26:24');
INSERT INTO `st_login_log` VALUES (80, 'admin', '2019-09-19 05:57:54', '登录成功', '1', 'admin', 'admin', '2019-09-19 05:57:54', '2019-09-19 13:57:54');
INSERT INTO `st_login_log` VALUES (81, 'admin', '2019-09-20 02:49:42', '登录成功', '1', 'admin', 'admin', '2019-09-20 02:49:42', '2019-09-20 10:49:41');
INSERT INTO `st_login_log` VALUES (82, 'admin', '2019-09-24 02:06:37', '登录成功', '1', 'admin', 'admin', '2019-09-24 02:06:37', '2019-09-24 10:06:37');
INSERT INTO `st_login_log` VALUES (83, 'admin', '2019-09-25 06:08:16', '登录成功', '1', 'admin', 'admin', '2019-09-25 06:08:16', '2019-09-25 14:08:15');
INSERT INTO `st_login_log` VALUES (84, 'admin', '2019-09-25 06:22:56', '登录成功', '1', 'admin', 'admin', '2019-09-25 06:22:56', '2019-09-25 14:22:55');
INSERT INTO `st_login_log` VALUES (85, 'admin', '2019-09-25 07:18:07', '登录成功', '1', 'admin', 'admin', '2019-09-25 07:18:07', '2019-09-25 15:18:07');
INSERT INTO `st_login_log` VALUES (86, 'admin', '2019-09-25 07:18:25', '登录成功', '1', 'admin', 'admin', '2019-09-25 07:18:25', '2019-09-25 15:18:25');

-- ----------------------------
-- Table structure for st_resource
-- ----------------------------
DROP TABLE IF EXISTS `st_resource`;
CREATE TABLE `st_resource`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '菜单名称',
  `full_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单编号路径',
  `icon_class` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图标样式类',
  `show_order` int(11) NULL DEFAULT NULL COMMENT '排序',
  `url` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '链接',
  `component` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '页面路径',
  `authority_id` bigint(20) NULL DEFAULT NULL COMMENT '权限ID',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '父id',
  `resource_desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单描述',
  `yn_flag` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_resource
-- ----------------------------
INSERT INTO `st_resource` VALUES (1, '系统管理', '0', 'nested', 30, '/sysmgr', '/layout/Layout', 1, 0, NULL, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2019-09-25 07:25:47');
INSERT INTO `st_resource` VALUES (2, '用户管理', '0-1', 'user', 1, 'user', '/sysmgr/user/index', 7, 1, NULL, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2019-08-19 11:04:38');
INSERT INTO `st_resource` VALUES (3, '角色管理', '0-1', 'peoples', 3, 'role', '/sysmgr/role/index', 10, 1, NULL, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2019-08-19 11:04:40');
INSERT INTO `st_resource` VALUES (4, '菜单管理', '0-1', 'list', 4, 'menu', '/sysmgr/menu/index', 13, 1, NULL, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2019-08-19 11:04:42');
INSERT INTO `st_resource` VALUES (5, '权限管理', '0-1', 'password', 5, 'authority', '/sysmgr/authority/index', 16, 1, NULL, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2019-08-19 11:04:43');
INSERT INTO `st_resource` VALUES (6, '基础信息', '0', 'nested', 20, '/baseinfo', '/layout/Layout', 1, 0, NULL, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2019-09-25 07:25:42');
INSERT INTO `st_resource` VALUES (7, '字典管理', '0-6', 'component', 1, 'dict', '/baseinfo/dict', 1, 6, NULL, '1', 'admin', 'admin', '2019-07-12 09:54:30', '2019-07-16 09:17:55');
INSERT INTO `st_resource` VALUES (8, '附件管理', '0-1', 'zip', 7, 'att', '/sysmgr/att/index', 39, 1, NULL, '1', 'admin', 'admin', '2019-07-12 10:25:37', '2019-09-25 06:18:31');
INSERT INTO `st_resource` VALUES (9, '登陆日志', '0-1', 'people', 6, 'loginlog', '/sysmgr/loginlog/index', 31, 1, NULL, '1', 'admin', 'admin', '2019-07-12 10:35:56', '2019-07-26 11:36:45');
INSERT INTO `st_resource` VALUES (10, '系统备份', '0-1', 'clipboard', 9, 'backup', '/sysmgr/backup/index', 44, 1, NULL, '1', 'admin', 'admin', '2019-07-12 10:49:11', '2019-09-25 06:18:49');
INSERT INTO `st_resource` VALUES (11, '系统日志', '0-1', 'documentation', 8, 'syslog', '/sysmgr/syslog/index', 42, 1, NULL, '1', 'admin', 'admin', '2019-07-12 11:58:59', '2019-09-25 06:18:41');
INSERT INTO `st_resource` VALUES (12, '定时任务', '0-1', 'guide', 20, 'schedulejob', '/sysmgr/schedulejob/index', 47, 1, NULL, '1', 'admin', 'admin', '2019-08-19 03:02:50', '2019-09-25 06:18:55');
INSERT INTO `st_resource` VALUES (13, '个人空间', '0', 'nested', 10, '/tools', '/layout/Layout', 1, 0, '', '1', 'admin', 'admin', '2019-09-25 06:28:53', '2019-09-25 14:30:48');
INSERT INTO `st_resource` VALUES (14, '待办事项', '0-13', 'table', NULL, 'todolist', '/tools/todolist', 53, 13, NULL, '1', 'admin', 'admin', '2019-09-25 07:09:20', '2019-09-25 07:17:58');

-- ----------------------------
-- Table structure for st_role
-- ----------------------------
DROP TABLE IF EXISTS `st_role`;
CREATE TABLE `st_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色名称',
  `role_desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色描述',
  `yn_flag` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_role
-- ----------------------------
INSERT INTO `st_role` VALUES (1, '管理员', NULL, '1', NULL, 'admin', '2018-12-29 11:23:15', '2019-07-26 11:56:33');
INSERT INTO `st_role` VALUES (3, '供应商', NULL, '1', NULL, NULL, NULL, '2019-01-04 11:00:37');
INSERT INTO `st_role` VALUES (4, '游客', NULL, '1', NULL, NULL, NULL, '2019-01-04 11:00:37');

-- ----------------------------
-- Table structure for st_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `st_role_authority`;
CREATE TABLE `st_role_authority`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `authority_id` bigint(20) NOT NULL COMMENT '权限ID',
  `yn_flag` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 248 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色权限关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_role_authority
-- ----------------------------
INSERT INTO `st_role_authority` VALUES (219, 1, 0, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (220, 1, 1, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (221, 1, 2, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (222, 1, 3, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (223, 1, 4, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (224, 1, 5, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (225, 1, 6, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (226, 1, 7, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (227, 1, 8, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (228, 1, 9, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (229, 1, 10, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (230, 1, 11, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (231, 1, 12, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (232, 1, 13, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (233, 1, 14, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (234, 1, 15, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (235, 1, 16, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (236, 1, 17, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (237, 1, 18, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (238, 1, 30, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (239, 1, 31, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (240, 1, 32, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (241, 1, 33, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (242, 1, 34, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (243, 1, 35, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (244, 1, 36, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (245, 1, 37, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (246, 3, 0, '1', 'admin', 'admin', '2019-07-26 05:54:06', '2019-07-26 05:54:06');
INSERT INTO `st_role_authority` VALUES (247, 3, 1, '1', 'admin', 'admin', '2019-07-26 05:54:06', '2019-07-26 05:54:06');
INSERT INTO `st_role_authority` VALUES (248, 1, 0, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (249, 1, 1, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (250, 1, 2, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (251, 1, 3, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (252, 1, 4, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (253, 1, 5, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (254, 1, 6, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (255, 1, 7, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (256, 1, 8, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (257, 1, 9, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (258, 1, 10, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (259, 1, 11, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (260, 1, 12, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (261, 1, 13, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (262, 1, 14, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (263, 1, 15, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (264, 1, 16, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (265, 1, 17, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (266, 1, 18, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (267, 1, 30, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (268, 1, 31, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (269, 1, 32, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (270, 1, 33, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (271, 1, 34, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (272, 1, 35, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (273, 1, 36, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (274, 1, 37, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (275, 1, 38, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (276, 1, 39, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (277, 1, 40, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (278, 1, 41, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (279, 1, 42, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (280, 1, 43, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (281, 1, 44, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (282, 1, 45, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (283, 1, 46, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (284, 1, 47, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (285, 1, 48, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (286, 1, 49, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (287, 1, 50, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (288, 1, 0, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (289, 1, 1, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (290, 1, 2, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (291, 1, 3, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (292, 1, 4, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (293, 1, 5, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (294, 1, 6, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (295, 1, 7, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (296, 1, 8, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (297, 1, 9, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (298, 1, 10, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (299, 1, 11, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (300, 1, 12, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (301, 1, 13, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (302, 1, 14, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (303, 1, 15, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (304, 1, 16, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (305, 1, 17, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (306, 1, 18, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (307, 1, 30, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (308, 1, 31, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (309, 1, 32, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (310, 1, 33, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (311, 1, 34, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (312, 1, 35, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (313, 1, 36, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (314, 1, 37, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (315, 1, 38, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (316, 1, 39, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (317, 1, 40, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (318, 1, 41, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (319, 1, 42, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (320, 1, 43, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (321, 1, 44, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (322, 1, 45, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (323, 1, 46, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (324, 1, 47, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (325, 1, 48, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (326, 1, 49, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (327, 1, 50, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (328, 1, 51, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (329, 1, 52, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (330, 1, 53, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (331, 1, 54, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (332, 1, 55, '1', 'admin', 'admin', '2019-09-25 07:18:14', '2019-09-25 07:18:14');

-- ----------------------------
-- Table structure for st_schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `st_schedule_job`;
CREATE TABLE `st_schedule_job`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'JobID',
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'Job名称',
  `cron` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'cron表达式',
  `start_job` bit(1) NULL DEFAULT NULL COMMENT '启动状态',
  `job_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '方法',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `fire_time` datetime(0) NULL DEFAULT NULL COMMENT '触发时间',
  `last_fire_time` datetime(0) NULL DEFAULT NULL COMMENT '上次触发时间',
  `next_fire_time` datetime(0) NULL DEFAULT NULL COMMENT '下次触发时间',
  `job_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'Job描述',
  `fail_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '失败原因',
  `yn_flag` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '定时任务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_schedule_job
-- ----------------------------
INSERT INTO `st_schedule_job` VALUES (1, 'StoryDemoJob', '框架演示任务', '0 0/5 * * * ?', b'1', 'com.story.storyadmin.scheduler.StoryDemoJob', '2019-08-19 06:14:06', '2019-09-25 07:25:00', '2019-09-25 07:20:00', '2019-09-25 07:30:00', '演示，仅此而已', NULL, '1', 'admin', 'admin', '2019-08-19 06:14:20', '2019-09-25 07:25:00');
INSERT INTO `st_schedule_job` VALUES (2, 'DbBackupJob', '数据库定时备份', '0 0 2 ? * TUE', b'0', 'com.story.storyadmin.scheduler.DbBackupJob', '2019-08-30 07:42:21', '2019-09-10 10:30:00', '2019-09-10 10:29:00', '2019-09-10 10:31:00', NULL, 'org.quartz.core.JobRunShell.run(JobRunShell.java:218)\norg.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\n', '1', 'admin', 'admin', '2019-08-30 07:42:29', '2019-09-10 19:37:28');

-- ----------------------------
-- Table structure for st_todo
-- ----------------------------
DROP TABLE IF EXISTS `st_todo`;
CREATE TABLE `st_todo`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `title` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '编码',
  `start` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `days_count` int(11) NULL DEFAULT NULL COMMENT '天数',
  `url` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '链接',
  `task_content` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '内容',
  `level` int(11) NULL DEFAULT NULL COMMENT '优先级',
  `executor` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '执行人',
  `yn_flag` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '待办事项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_todo
-- ----------------------------
INSERT INTO `st_todo` VALUES (1, '测试标题', '2019-08-15 14:28:12', '2019-08-16 14:28:18', NULL, NULL, '测试内容', 1, NULL, '0', NULL, 'admin', NULL, '2019-08-16 11:39:42');
INSERT INTO `st_todo` VALUES (2, '出差1', '2019-08-14 17:31:14', '2019-08-14 17:31:19', NULL, NULL, '出差内容', 2, NULL, '0', NULL, 'admin', NULL, '2019-08-16 11:39:46');
INSERT INTO `st_todo` VALUES (3, '测试3', '2019-07-30 00:00:00', '2019-08-01 00:00:00', NULL, NULL, '测试3', 1, NULL, '0', NULL, 'admin', NULL, '2019-08-16 11:39:34');
INSERT INTO `st_todo` VALUES (4, '生日', '2019-08-29 00:00:00', '2019-08-31 00:00:00', NULL, NULL, '生日', 2, NULL, '0', NULL, 'admin', NULL, '2019-08-16 11:39:50');
INSERT INTO `st_todo` VALUES (5, '回家', '2019-08-07 00:00:00', '2019-08-08 00:00:00', NULL, NULL, '回家', 1, NULL, '0', NULL, 'admin', NULL, '2019-08-16 11:39:38');
INSERT INTO `st_todo` VALUES (6, '约会3', '2019-08-12 00:00:00', '2019-08-12 23:59:00', NULL, 'www.baidu.com', '大望路', 2, NULL, '0', NULL, 'admin', NULL, '2019-08-16 11:38:59');
INSERT INTO `st_todo` VALUES (7, '聚会', '2019-08-25 00:00:00', '2019-08-25 23:59:00', NULL, NULL, '主日聚会', 3, NULL, '1', NULL, 'admin', NULL, '2019-08-18 12:38:23');
INSERT INTO `st_todo` VALUES (8, '练车', '2019-09-07 00:00:00', '2019-09-08 00:00:00', NULL, NULL, '练车', 5, NULL, '1', NULL, 'admin', NULL, '2019-09-06 02:20:10');
INSERT INTO `st_todo` VALUES (9, '测试', '2019-09-07 00:00:00', '2019-09-07 23:59:00', NULL, NULL, NULL, NULL, NULL, '1', NULL, 'admin', NULL, '2019-09-06 02:21:04');
INSERT INTO `st_todo` VALUES (10, '测试2', '2019-09-07 00:00:00', '2019-09-07 23:59:00', NULL, NULL, NULL, NULL, NULL, '1', NULL, 'admin', NULL, '2019-09-06 02:21:16');
INSERT INTO `st_todo` VALUES (11, '测试3', '2019-09-07 00:00:00', '2019-09-07 23:59:00', NULL, NULL, NULL, NULL, NULL, '1', NULL, 'admin', NULL, '2019-09-06 02:21:23');
INSERT INTO `st_todo` VALUES (12, '出差', '2019-09-17 00:00:00', '2019-09-20 00:00:00', NULL, NULL, NULL, 1, NULL, '1', NULL, 'admin', NULL, '2019-09-09 06:59:11');
INSERT INTO `st_todo` VALUES (13, '测试4', '2019-09-07 00:00:00', '2019-09-07 23:59:00', NULL, NULL, NULL, 3, NULL, '1', NULL, 'admin', NULL, '2019-09-10 06:38:34');

-- ----------------------------
-- Table structure for st_user
-- ----------------------------
DROP TABLE IF EXISTS `st_user`;
CREATE TABLE `st_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `account` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '账号',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱',
  `last_pwd_modified_time` datetime(0) NULL DEFAULT NULL COMMENT '上次修改密码时间',
  `status` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '状态',
  `yn_flag` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `erp_flag` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'ERP账号标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_user
-- ----------------------------
INSERT INTO `st_user` VALUES (1, 'admin', '管理员', 'bcd0b29f69059fe4c69802525c4ab550', 'admin@admin.admin', '2019-01-17 15:05:04', '1', '1', NULL, 'admin', '2018-12-29 10:40:07', '2019-06-17 13:45:48', '0');
INSERT INTO `st_user` VALUES (2, 'njsun', 'sunnj01', '0ace0a5fc5802a43305c8ae547a81afe', 'aa@aa.aa2', '2019-01-17 15:25:42', '0', '1', NULL, NULL, NULL, '2019-01-17 15:25:42', '0');
INSERT INTO `st_user` VALUES (4, 'test', '测试', '0ace0a5fc5802a43305c8ae547a81afe', 'test@aa.aa', '2019-01-17 15:23:28', '1', '1', NULL, NULL, NULL, '2019-01-17 15:23:28', '0');

-- ----------------------------
-- Table structure for st_user_role
-- ----------------------------
DROP TABLE IF EXISTS `st_user_role`;
CREATE TABLE `st_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `yn_flag` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_user_role
-- ----------------------------
INSERT INTO `st_user_role` VALUES (3, 1, 1, '1', 'admin', 'admin', '2019-01-04 10:38:31', '2019-07-26 19:39:45');

SET FOREIGN_KEY_CHECKS = 1;
