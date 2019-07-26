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

 Date: 26/07/2019 19:46:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for st_att
-- ----------------------------
DROP TABLE IF EXISTS `st_att`;
CREATE TABLE `st_att`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime(0) NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `description` varchar(255)  NULL DEFAULT NULL,
  `lot_id` varchar(255)  NOT NULL,
  `path` varchar(255)  NULL DEFAULT NULL,
  `type` varchar(255)  NULL DEFAULT NULL,
  `file_size` bigint(20) NULL DEFAULT NULL,
  `origin_name` varchar(255)  NULL DEFAULT NULL,
  `yn_flag` char(2)  NULL DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32)  NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '附件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_att
-- ----------------------------
INSERT INTO `st_att` VALUES (1, '2019-07-12 18:40:40', NULL, NULL, '111', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, '2019-07-12 18:40:40');

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
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_authority
-- ----------------------------
INSERT INTO `st_authority` VALUES (1, '公共', 'public', '0', NULL, 1, 0, '1', NULL, NULL, NULL, '2019-07-26 16:44:51');
INSERT INTO `st_authority` VALUES (2, '系统管理', 'sysmgr', '0', NULL, 1, 0, '1', NULL, NULL, NULL, '2019-07-26 18:44:20');
INSERT INTO `st_authority` VALUES (3, '用户管理', 'sysmgr.user', '0-2', NULL, 1, 2, '1', NULL, NULL, NULL, '2019-07-26 18:44:24');
INSERT INTO `st_authority` VALUES (4, '角色管理', 'sysmgr.rule', '0-2', NULL, 2, 2, '1', NULL, NULL, NULL, '2019-07-26 18:44:28');
INSERT INTO `st_authority` VALUES (5, '菜单管理', 'sysmgr.resource', '0-2', NULL, 3, 2, '1', NULL, NULL, NULL, '2019-07-26 18:44:50');
INSERT INTO `st_authority` VALUES (6, '权限管理', 'sysmgr.authority', '0-2', NULL, 4, 2, '1', NULL, NULL, NULL, '2019-07-26 18:44:53');
INSERT INTO `st_authority` VALUES (7, '查询用户', 'sysmgr.user.query', '0-2-3', NULL, 1, 3, '1', NULL, NULL, NULL, '2019-07-26 18:44:57');
INSERT INTO `st_authority` VALUES (8, '编辑用户', 'sysmgr.user.save', '0-2-3', NULL, 2, 3, '1', NULL, NULL, NULL, '2019-07-26 18:45:00');
INSERT INTO `st_authority` VALUES (9, '删除用户', 'sysmgr.user.delete', '0-2-3', NULL, 3, 3, '1', NULL, NULL, NULL, '2019-07-26 18:45:04');
INSERT INTO `st_authority` VALUES (10, '查询角色', 'sysmgr.role.query', '0-2-4', NULL, 1, 4, '1', NULL, NULL, NULL, '2019-07-26 18:45:06');
INSERT INTO `st_authority` VALUES (11, '编辑角色', 'sysmgr.role.save', '0-2-4', NULL, 2, 4, '1', NULL, NULL, NULL, '2019-07-26 18:45:09');
INSERT INTO `st_authority` VALUES (12, '删除角色', 'sysmgr.role.delete', '0-2-4', NULL, 3, 4, '1', NULL, NULL, NULL, '2019-07-26 18:45:13');
INSERT INTO `st_authority` VALUES (13, '查询菜单', 'sysmgr.resource.query', '0-2-5', NULL, 1, 5, '1', NULL, NULL, NULL, '2019-07-26 18:45:20');
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '登录日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_login_log
-- ----------------------------
INSERT INTO `st_login_log` VALUES (1, 'admin', '2019-07-26 05:50:21', '登录成功', '1', 'admin', 'admin', '2019-07-26 05:50:21', '2019-07-26 13:50:55');
INSERT INTO `st_login_log` VALUES (2, 'admin', '2019-07-26 08:31:30', '登录成功', '1', 'admin', 'admin', '2019-07-26 08:31:30', '2019-07-26 16:31:30');
INSERT INTO `st_login_log` VALUES (3, 'admin', '2019-07-26 08:58:40', '登录成功', '1', 'admin', 'admin', '2019-07-26 08:58:40', '2019-07-26 16:58:39');
INSERT INTO `st_login_log` VALUES (4, 'admin', '2019-07-26 09:04:12', '登录成功', '1', 'admin', 'admin', '2019-07-26 09:04:12', '2019-07-26 17:04:12');
INSERT INTO `st_login_log` VALUES (5, 'admin', '2019-07-26 11:35:11', '登录成功', '1', 'admin', 'admin', '2019-07-26 11:35:11', '2019-07-26 19:35:11');

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
) ENGINE = InnoDB AUTO_INCREMENT = 1149649350326554627 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_resource
-- ----------------------------
INSERT INTO `st_resource` VALUES (1, '系统管理', '0', 'nested', 1, '/sysmgr', '/layout/Layout', 1, 0, NULL, '1', NULL, 'admin', NULL, '2019-06-17 14:52:13');
INSERT INTO `st_resource` VALUES (2, '用户管理', '0-1', 'user', 1, 'user', '/sysmgr/user/index', 7, 1, NULL, '1', NULL, 'admin', NULL, '2019-07-26 10:01:27');
INSERT INTO `st_resource` VALUES (3, '角色管理', '0-1', 'peoples', 3, 'role', '/sysmgr/role/index', 10, 1, NULL, '1', NULL, 'admin', NULL, '2019-07-26 10:03:29');
INSERT INTO `st_resource` VALUES (4, '菜单管理', '0-1', 'list', 4, 'menu', '/sysmgr/menu/index', 13, 1, NULL, '1', NULL, 'admin', NULL, '2019-07-26 10:03:11');
INSERT INTO `st_resource` VALUES (5, '权限管理', '0-1', 'password', 5, 'authority', '/sysmgr/authority/index', 16, 1, NULL, '1', NULL, 'admin', NULL, '2019-07-26 11:36:33');
INSERT INTO `st_resource` VALUES (6, '基础信息', '0', 'nested', 1, '/baseinfo', '/layout/Layout', 1, 0, NULL, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2019-07-12 09:52:04');
INSERT INTO `st_resource` VALUES (7, '字典管理', '0-6', 'component', 1, 'dict', '/baseinfo/dict', 1, 6, NULL, '1', 'admin', 'admin', '2019-07-12 09:54:30', '2019-07-16 09:17:55');
INSERT INTO `st_resource` VALUES (8, '附件管理', '0-1', 'zip', 7, 'att', '/sysmgr/att/index', 1, 1, NULL, '1', 'admin', 'admin', '2019-07-12 10:25:37', '2019-07-16 09:17:46');
INSERT INTO `st_resource` VALUES (9, '登陆日志', '0-1', 'people', 6, 'loginlog', '/sysmgr/loginlog/index', 31, 1, NULL, '1', 'admin', 'admin', '2019-07-12 10:35:56', '2019-07-26 11:36:45');
INSERT INTO `st_resource` VALUES (10, '系统备份', '0-1', 'clipboard', 9, 'backup', NULL, 1, 1, NULL, '1', 'admin', 'admin', '2019-07-12 10:49:11', '2019-07-16 09:18:40');
INSERT INTO `st_resource` VALUES (11, '系统日志', '0-1', 'documentation', 8, 'syslog', NULL, 1, 1, NULL, '1', 'admin', 'admin', '2019-07-12 11:58:59', '2019-07-16 09:15:24');

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
INSERT INTO `st_role` VALUES (1, '管理员', NULL, '1', NULL, NULL, '2018-12-29 11:23:15', '2019-01-04 11:00:37');
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
INSERT INTO `st_role_authority` VALUES (219, 1, 0, '1', 'admin', 'admin', '2019-07-26 03:15:48', '2019-07-26 03:15:48');
INSERT INTO `st_role_authority` VALUES (220, 1, 1, '1', 'admin', 'admin', '2019-07-26 03:15:48', '2019-07-26 03:15:48');
INSERT INTO `st_role_authority` VALUES (221, 1, 2, '1', 'admin', 'admin', '2019-07-26 03:15:48', '2019-07-26 03:15:48');
INSERT INTO `st_role_authority` VALUES (222, 1, 3, '1', 'admin', 'admin', '2019-07-26 03:15:48', '2019-07-26 03:15:48');
INSERT INTO `st_role_authority` VALUES (223, 1, 4, '1', 'admin', 'admin', '2019-07-26 03:15:48', '2019-07-26 03:15:48');
INSERT INTO `st_role_authority` VALUES (224, 1, 5, '1', 'admin', 'admin', '2019-07-26 03:15:48', '2019-07-26 03:15:48');
INSERT INTO `st_role_authority` VALUES (225, 1, 6, '1', 'admin', 'admin', '2019-07-26 03:15:48', '2019-07-26 03:15:48');
INSERT INTO `st_role_authority` VALUES (226, 1, 7, '1', 'admin', 'admin', '2019-07-26 03:15:48', '2019-07-26 03:15:48');
INSERT INTO `st_role_authority` VALUES (227, 1, 8, '1', 'admin', 'admin', '2019-07-26 03:15:48', '2019-07-26 03:15:48');
INSERT INTO `st_role_authority` VALUES (228, 1, 9, '1', 'admin', 'admin', '2019-07-26 03:15:48', '2019-07-26 03:15:48');
INSERT INTO `st_role_authority` VALUES (229, 1, 10, '1', 'admin', 'admin', '2019-07-26 03:15:48', '2019-07-26 03:15:48');
INSERT INTO `st_role_authority` VALUES (230, 1, 11, '1', 'admin', 'admin', '2019-07-26 03:15:48', '2019-07-26 03:15:48');
INSERT INTO `st_role_authority` VALUES (231, 1, 12, '1', 'admin', 'admin', '2019-07-26 03:15:48', '2019-07-26 03:15:48');
INSERT INTO `st_role_authority` VALUES (232, 1, 13, '1', 'admin', 'admin', '2019-07-26 03:15:48', '2019-07-26 03:15:48');
INSERT INTO `st_role_authority` VALUES (233, 1, 14, '1', 'admin', 'admin', '2019-07-26 03:15:48', '2019-07-26 03:15:48');
INSERT INTO `st_role_authority` VALUES (234, 1, 15, '1', 'admin', 'admin', '2019-07-26 03:15:48', '2019-07-26 03:15:48');
INSERT INTO `st_role_authority` VALUES (235, 1, 16, '1', 'admin', 'admin', '2019-07-26 03:15:48', '2019-07-26 03:15:48');
INSERT INTO `st_role_authority` VALUES (236, 1, 17, '1', 'admin', 'admin', '2019-07-26 03:15:48', '2019-07-26 03:15:48');
INSERT INTO `st_role_authority` VALUES (237, 1, 18, '1', 'admin', 'admin', '2019-07-26 03:15:48', '2019-07-26 03:15:48');
INSERT INTO `st_role_authority` VALUES (238, 1, 30, '1', 'admin', 'admin', '2019-07-26 03:15:48', '2019-07-26 03:15:48');
INSERT INTO `st_role_authority` VALUES (239, 1, 31, '1', 'admin', 'admin', '2019-07-26 03:15:48', '2019-07-26 03:15:48');
INSERT INTO `st_role_authority` VALUES (240, 1, 32, '1', 'admin', 'admin', '2019-07-26 03:15:48', '2019-07-26 03:15:48');
INSERT INTO `st_role_authority` VALUES (241, 1, 33, '1', 'admin', 'admin', '2019-07-26 03:15:48', '2019-07-26 03:15:48');
INSERT INTO `st_role_authority` VALUES (242, 1, 34, '1', 'admin', 'admin', '2019-07-26 03:15:48', '2019-07-26 03:15:48');
INSERT INTO `st_role_authority` VALUES (243, 1, 35, '1', 'admin', 'admin', '2019-07-26 03:15:48', '2019-07-26 03:15:48');
INSERT INTO `st_role_authority` VALUES (244, 1, 36, '1', 'admin', 'admin', '2019-07-26 03:15:48', '2019-07-26 03:15:48');
INSERT INTO `st_role_authority` VALUES (245, 1, 37, '1', 'admin', 'admin', '2019-07-26 03:15:48', '2019-07-26 03:15:48');
INSERT INTO `st_role_authority` VALUES (246, 3, 0, '1', 'admin', 'admin', '2019-07-26 05:54:06', '2019-07-26 05:54:06');
INSERT INTO `st_role_authority` VALUES (247, 3, 1, '1', 'admin', 'admin', '2019-07-26 05:54:06', '2019-07-26 05:54:06');

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
