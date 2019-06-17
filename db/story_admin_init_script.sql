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

 Date: 17/06/2019 22:55:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_authority
-- ----------------------------
INSERT INTO `st_authority` VALUES (1, '公共', 'public', NULL, NULL, 1, 0, '1', NULL, NULL, NULL, '2019-06-17 14:52:45');
INSERT INTO `st_authority` VALUES (2, '系统管理', 'sysmgr', NULL, NULL, 1, 0, '1', NULL, NULL, NULL, '2019-06-17 14:52:50');
INSERT INTO `st_authority` VALUES (3, '用户管理', 'sysmgr.user', NULL, NULL, 1, 2, '1', NULL, NULL, NULL, '2019-06-17 14:52:54');
INSERT INTO `st_authority` VALUES (4, '角色管理', 'sysmgr.rule', NULL, NULL, 2, 2, '1', NULL, NULL, NULL, '2019-06-17 14:53:01');
INSERT INTO `st_authority` VALUES (5, '菜单管理', 'sysmgr.resource', NULL, NULL, 3, 2, '1', NULL, NULL, NULL, '2019-06-17 14:53:05');
INSERT INTO `st_authority` VALUES (6, '权限管理', 'sysmgr.authority', NULL, NULL, 4, 2, '1', NULL, NULL, NULL, '2019-06-17 14:53:08');
INSERT INTO `st_authority` VALUES (7, '查询用户', 'sysmgr.user.query', NULL, NULL, 1, 3, '1', NULL, NULL, NULL, '2019-01-04 11:32:45');
INSERT INTO `st_authority` VALUES (8, '编辑用户', 'sysmgr.user.save', NULL, NULL, 2, 3, '1', NULL, NULL, NULL, '2019-01-04 11:32:45');
INSERT INTO `st_authority` VALUES (9, '删除用户', 'sysmgr.user.delete', NULL, NULL, 3, 3, '1', NULL, NULL, NULL, '2019-01-04 11:32:45');
INSERT INTO `st_authority` VALUES (10, '查询角色', 'sysmgr.role.query', NULL, NULL, 1, 4, '1', NULL, NULL, NULL, '2019-01-04 11:32:45');
INSERT INTO `st_authority` VALUES (11, '编辑角色', 'sysmgr.role.save', NULL, NULL, 2, 4, '1', NULL, NULL, NULL, '2019-01-04 11:32:45');
INSERT INTO `st_authority` VALUES (12, '删除角色', 'sysmgr.role.delete', NULL, NULL, 3, 4, '1', NULL, NULL, NULL, '2019-01-04 11:32:45');
INSERT INTO `st_authority` VALUES (13, '查询菜单', 'sysmgr.resource.query', NULL, NULL, 1, 5, '1', NULL, NULL, NULL, '2019-01-04 11:32:45');
INSERT INTO `st_authority` VALUES (14, '编辑菜单', 'sysmgr.resource.save', NULL, NULL, 2, 5, '1', NULL, NULL, NULL, '2019-01-04 11:32:45');
INSERT INTO `st_authority` VALUES (15, '删除菜单', 'sysmgr.resource.delete', NULL, NULL, 3, 5, '1', NULL, NULL, NULL, '2019-01-04 11:32:45');
INSERT INTO `st_authority` VALUES (16, '查询权限', 'sysmgr.authority.query', NULL, NULL, 1, 6, '1', NULL, NULL, NULL, '2019-01-04 11:32:45');
INSERT INTO `st_authority` VALUES (17, '编辑权限', 'sysmgr.authority.save', NULL, NULL, 2, 6, '1', NULL, NULL, NULL, '2019-01-04 11:32:45');
INSERT INTO `st_authority` VALUES (18, '删除权限', 'sysmgr.authority.delete', NULL, NULL, 3, 6, '1', NULL, NULL, NULL, '2019-01-04 11:32:45');

-- ----------------------------
-- Table structure for st_resource
-- ----------------------------
DROP TABLE IF EXISTS `st_resource`;
CREATE TABLE `st_resource`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
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
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_resource
-- ----------------------------
INSERT INTO `st_resource` VALUES (1, '系统管理', '0', 'nested', 1, '/sysmgr', '/layout/Layout', 1, 0, NULL, '1', NULL, 'admin', NULL, '2019-06-17 14:52:13');
INSERT INTO `st_resource` VALUES (2, '用户管理', '1', 'user', 1, 'user', '/sysmgr/user/index', 7, 1, NULL, '1', NULL, 'admin', NULL, '2019-06-17 14:52:16');
INSERT INTO `st_resource` VALUES (3, '角色管理', '1', 'peoples', 3, 'role', '/sysmgr/role/index', 10, 1, NULL, '1', NULL, 'admin', NULL, '2019-06-17 14:52:19');
INSERT INTO `st_resource` VALUES (4, '菜单管理', '1', 'list', 4, 'menu', '/sysmgr/menu/index', 13, 1, NULL, '1', NULL, 'admin', NULL, '2019-06-17 14:52:22');
INSERT INTO `st_resource` VALUES (5, '权限管理', '1', 'password', 5, 'authority', '/sysmgr/authority/index', 2, 1, NULL, '1', NULL, 'admin', NULL, '2019-06-17 14:52:24');

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 219 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色权限关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_role_authority
-- ----------------------------
INSERT INTO `st_role_authority` VALUES (40, 1, 0, '0', NULL, NULL, NULL, '2019-01-15 15:40:19');
INSERT INTO `st_role_authority` VALUES (41, 1, 1, '0', NULL, NULL, NULL, '2019-01-15 15:40:19');
INSERT INTO `st_role_authority` VALUES (42, 1, 2, '0', NULL, NULL, NULL, '2019-01-15 15:40:19');
INSERT INTO `st_role_authority` VALUES (43, 1, 3, '0', NULL, NULL, NULL, '2019-01-15 15:40:19');
INSERT INTO `st_role_authority` VALUES (44, 1, 4, '0', NULL, NULL, NULL, '2019-01-15 15:40:19');
INSERT INTO `st_role_authority` VALUES (45, 1, 5, '0', NULL, NULL, NULL, '2019-01-15 15:40:19');
INSERT INTO `st_role_authority` VALUES (46, 1, 6, '0', NULL, NULL, NULL, '2019-01-15 15:40:19');
INSERT INTO `st_role_authority` VALUES (47, 1, 7, '0', NULL, NULL, NULL, '2019-01-15 15:40:19');
INSERT INTO `st_role_authority` VALUES (48, 1, 8, '0', NULL, NULL, NULL, '2019-01-15 15:40:19');
INSERT INTO `st_role_authority` VALUES (49, 1, 9, '0', NULL, NULL, NULL, '2019-01-15 15:40:19');
INSERT INTO `st_role_authority` VALUES (50, 1, 10, '0', NULL, NULL, NULL, '2019-01-15 15:40:19');
INSERT INTO `st_role_authority` VALUES (51, 1, 11, '0', NULL, NULL, NULL, '2019-01-15 15:40:19');
INSERT INTO `st_role_authority` VALUES (52, 1, 12, '0', NULL, NULL, NULL, '2019-01-15 15:40:19');
INSERT INTO `st_role_authority` VALUES (53, 1, 13, '0', NULL, NULL, NULL, '2019-01-15 15:40:19');
INSERT INTO `st_role_authority` VALUES (54, 1, 14, '0', NULL, NULL, NULL, '2019-01-15 15:40:19');
INSERT INTO `st_role_authority` VALUES (55, 1, 15, '0', NULL, NULL, NULL, '2019-01-15 15:40:19');
INSERT INTO `st_role_authority` VALUES (56, 1, 16, '0', NULL, NULL, NULL, '2019-01-15 15:40:19');
INSERT INTO `st_role_authority` VALUES (57, 1, 17, '0', NULL, NULL, NULL, '2019-01-15 15:40:19');
INSERT INTO `st_role_authority` VALUES (58, 1, 18, '0', NULL, NULL, NULL, '2019-01-15 15:40:19');
INSERT INTO `st_role_authority` VALUES (61, 1, 0, '0', NULL, NULL, '2019-01-15 15:40:19', '2019-01-24 16:16:29');
INSERT INTO `st_role_authority` VALUES (62, 1, 1, '0', NULL, NULL, '2019-01-15 15:40:19', '2019-01-24 16:16:29');
INSERT INTO `st_role_authority` VALUES (63, 1, 2, '0', NULL, NULL, '2019-01-15 15:40:19', '2019-01-24 16:16:29');
INSERT INTO `st_role_authority` VALUES (64, 1, 3, '0', NULL, NULL, '2019-01-15 15:40:19', '2019-01-24 16:16:29');
INSERT INTO `st_role_authority` VALUES (65, 1, 4, '0', NULL, NULL, '2019-01-15 15:40:19', '2019-01-24 16:16:29');
INSERT INTO `st_role_authority` VALUES (66, 1, 5, '0', NULL, NULL, '2019-01-15 15:40:19', '2019-01-24 16:16:29');
INSERT INTO `st_role_authority` VALUES (67, 1, 6, '0', NULL, NULL, '2019-01-15 15:40:19', '2019-01-24 16:16:29');
INSERT INTO `st_role_authority` VALUES (68, 1, 7, '0', NULL, NULL, '2019-01-15 15:40:19', '2019-01-24 16:16:29');
INSERT INTO `st_role_authority` VALUES (69, 1, 8, '0', NULL, NULL, '2019-01-15 15:40:19', '2019-01-24 16:16:29');
INSERT INTO `st_role_authority` VALUES (70, 1, 9, '0', NULL, NULL, '2019-01-15 15:40:19', '2019-01-24 16:16:29');
INSERT INTO `st_role_authority` VALUES (71, 1, 10, '0', NULL, NULL, '2019-01-15 15:40:19', '2019-01-24 16:16:29');
INSERT INTO `st_role_authority` VALUES (72, 1, 11, '0', NULL, NULL, '2019-01-15 15:40:19', '2019-01-24 16:16:29');
INSERT INTO `st_role_authority` VALUES (73, 1, 12, '0', NULL, NULL, '2019-01-15 15:40:19', '2019-01-24 16:16:29');
INSERT INTO `st_role_authority` VALUES (74, 1, 13, '0', NULL, NULL, '2019-01-15 15:40:19', '2019-01-24 16:16:29');
INSERT INTO `st_role_authority` VALUES (75, 1, 14, '0', NULL, NULL, '2019-01-15 15:40:19', '2019-01-24 16:16:29');
INSERT INTO `st_role_authority` VALUES (76, 1, 15, '0', NULL, NULL, '2019-01-15 15:40:19', '2019-01-24 16:16:29');
INSERT INTO `st_role_authority` VALUES (77, 1, 16, '0', NULL, NULL, '2019-01-15 15:40:19', '2019-01-24 16:16:29');
INSERT INTO `st_role_authority` VALUES (78, 1, 17, '0', NULL, NULL, '2019-01-15 15:40:19', '2019-01-24 16:16:29');
INSERT INTO `st_role_authority` VALUES (79, 1, 18, '0', NULL, NULL, '2019-01-15 15:40:19', '2019-01-24 16:16:29');
INSERT INTO `st_role_authority` VALUES (85, 1, 1, '0', NULL, NULL, '2019-01-24 16:16:29', '2019-01-24 16:33:30');
INSERT INTO `st_role_authority` VALUES (86, 1, 2, '0', NULL, NULL, '2019-01-24 16:16:29', '2019-01-24 16:33:30');
INSERT INTO `st_role_authority` VALUES (87, 1, 3, '0', NULL, NULL, '2019-01-24 16:16:29', '2019-01-24 16:33:30');
INSERT INTO `st_role_authority` VALUES (88, 1, 4, '0', NULL, NULL, '2019-01-24 16:16:29', '2019-01-24 16:33:30');
INSERT INTO `st_role_authority` VALUES (89, 1, 5, '0', NULL, NULL, '2019-01-24 16:16:29', '2019-01-24 16:33:30');
INSERT INTO `st_role_authority` VALUES (90, 1, 6, '0', NULL, NULL, '2019-01-24 16:16:29', '2019-01-24 16:33:30');
INSERT INTO `st_role_authority` VALUES (91, 1, 7, '0', NULL, NULL, '2019-01-24 16:16:29', '2019-01-24 16:33:30');
INSERT INTO `st_role_authority` VALUES (92, 1, 8, '0', NULL, NULL, '2019-01-24 16:16:29', '2019-01-24 16:33:30');
INSERT INTO `st_role_authority` VALUES (93, 1, 9, '0', NULL, NULL, '2019-01-24 16:16:29', '2019-01-24 16:33:30');
INSERT INTO `st_role_authority` VALUES (94, 1, 10, '0', NULL, NULL, '2019-01-24 16:16:29', '2019-01-24 16:33:30');
INSERT INTO `st_role_authority` VALUES (95, 1, 11, '0', NULL, NULL, '2019-01-24 16:16:29', '2019-01-24 16:33:30');
INSERT INTO `st_role_authority` VALUES (96, 1, 12, '0', NULL, NULL, '2019-01-24 16:16:29', '2019-01-24 16:33:30');
INSERT INTO `st_role_authority` VALUES (97, 1, 13, '0', NULL, NULL, '2019-01-24 16:16:29', '2019-01-24 16:33:30');
INSERT INTO `st_role_authority` VALUES (98, 1, 14, '0', NULL, NULL, '2019-01-24 16:16:29', '2019-01-24 16:33:30');
INSERT INTO `st_role_authority` VALUES (99, 1, 15, '0', NULL, NULL, '2019-01-24 16:16:29', '2019-01-24 16:33:30');
INSERT INTO `st_role_authority` VALUES (100, 1, 16, '0', NULL, NULL, '2019-01-24 16:16:29', '2019-01-24 16:33:30');
INSERT INTO `st_role_authority` VALUES (101, 1, 17, '0', NULL, NULL, '2019-01-24 16:16:29', '2019-01-24 16:33:30');
INSERT INTO `st_role_authority` VALUES (102, 1, 18, '0', NULL, NULL, '2019-01-24 16:16:29', '2019-01-24 16:33:30');
INSERT INTO `st_role_authority` VALUES (107, 1, 0, '0', NULL, NULL, '2019-01-24 16:33:30', '2019-01-24 20:01:33');
INSERT INTO `st_role_authority` VALUES (108, 1, 1, '0', NULL, NULL, '2019-01-24 16:33:30', '2019-01-24 20:01:33');
INSERT INTO `st_role_authority` VALUES (109, 1, 2, '0', NULL, NULL, '2019-01-24 16:33:30', '2019-01-24 20:01:33');
INSERT INTO `st_role_authority` VALUES (110, 1, 3, '0', NULL, NULL, '2019-01-24 16:33:30', '2019-01-24 20:01:33');
INSERT INTO `st_role_authority` VALUES (111, 1, 4, '0', NULL, NULL, '2019-01-24 16:33:30', '2019-01-24 20:01:33');
INSERT INTO `st_role_authority` VALUES (112, 1, 5, '0', NULL, NULL, '2019-01-24 16:33:30', '2019-01-24 20:01:33');
INSERT INTO `st_role_authority` VALUES (113, 1, 6, '0', NULL, NULL, '2019-01-24 16:33:30', '2019-01-24 20:01:33');
INSERT INTO `st_role_authority` VALUES (114, 1, 7, '0', NULL, NULL, '2019-01-24 16:33:30', '2019-01-24 20:01:33');
INSERT INTO `st_role_authority` VALUES (115, 1, 8, '0', NULL, NULL, '2019-01-24 16:33:30', '2019-01-24 20:01:33');
INSERT INTO `st_role_authority` VALUES (116, 1, 9, '0', NULL, NULL, '2019-01-24 16:33:30', '2019-01-24 20:01:33');
INSERT INTO `st_role_authority` VALUES (117, 1, 10, '0', NULL, NULL, '2019-01-24 16:33:30', '2019-01-24 20:01:33');
INSERT INTO `st_role_authority` VALUES (118, 1, 11, '0', NULL, NULL, '2019-01-24 16:33:30', '2019-01-24 20:01:33');
INSERT INTO `st_role_authority` VALUES (119, 1, 12, '0', NULL, NULL, '2019-01-24 16:33:30', '2019-01-24 20:01:33');
INSERT INTO `st_role_authority` VALUES (120, 1, 13, '0', NULL, NULL, '2019-01-24 16:33:30', '2019-01-24 20:01:33');
INSERT INTO `st_role_authority` VALUES (121, 1, 14, '0', NULL, NULL, '2019-01-24 16:33:30', '2019-01-24 20:01:33');
INSERT INTO `st_role_authority` VALUES (122, 1, 15, '0', NULL, NULL, '2019-01-24 16:33:30', '2019-01-24 20:01:33');
INSERT INTO `st_role_authority` VALUES (123, 1, 16, '0', NULL, NULL, '2019-01-24 16:33:30', '2019-01-24 20:01:33');
INSERT INTO `st_role_authority` VALUES (124, 1, 17, '0', NULL, NULL, '2019-01-24 16:33:30', '2019-01-24 20:01:33');
INSERT INTO `st_role_authority` VALUES (125, 1, 18, '0', NULL, NULL, '2019-01-24 16:33:30', '2019-01-24 20:01:33');
INSERT INTO `st_role_authority` VALUES (134, 1, 0, '0', NULL, NULL, '2019-01-24 20:01:33', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (135, 1, 1, '0', NULL, NULL, '2019-01-24 20:01:33', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (136, 1, 2, '0', NULL, NULL, '2019-01-24 20:01:33', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (137, 1, 3, '0', NULL, NULL, '2019-01-24 20:01:33', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (138, 1, 4, '0', NULL, NULL, '2019-01-24 20:01:33', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (139, 1, 5, '0', NULL, NULL, '2019-01-24 20:01:33', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (140, 1, 6, '0', NULL, NULL, '2019-01-24 20:01:33', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (141, 1, 7, '0', NULL, NULL, '2019-01-24 20:01:33', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (142, 1, 8, '0', NULL, NULL, '2019-01-24 20:01:33', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (143, 1, 9, '0', NULL, NULL, '2019-01-24 20:01:33', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (144, 1, 10, '0', NULL, NULL, '2019-01-24 20:01:33', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (145, 1, 11, '0', NULL, NULL, '2019-01-24 20:01:33', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (146, 1, 12, '0', NULL, NULL, '2019-01-24 20:01:33', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (147, 1, 13, '0', NULL, NULL, '2019-01-24 20:01:33', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (148, 1, 14, '0', NULL, NULL, '2019-01-24 20:01:33', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (149, 1, 15, '0', NULL, NULL, '2019-01-24 20:01:33', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (150, 1, 16, '0', NULL, NULL, '2019-01-24 20:01:33', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (151, 1, 17, '0', NULL, NULL, '2019-01-24 20:01:33', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (152, 1, 18, '0', NULL, NULL, '2019-01-24 20:01:33', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (181, 1, 0, '1', 'admin', 'admin', '2019-01-25 10:39:38', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (182, 1, 1, '1', 'admin', 'admin', '2019-01-25 10:39:38', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (183, 1, 2, '1', 'admin', 'admin', '2019-01-25 10:39:38', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (184, 1, 3, '1', 'admin', 'admin', '2019-01-25 10:39:38', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (185, 1, 4, '1', 'admin', 'admin', '2019-01-25 10:39:38', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (186, 1, 5, '1', 'admin', 'admin', '2019-01-25 10:39:38', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (187, 1, 6, '1', 'admin', 'admin', '2019-01-25 10:39:38', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (188, 1, 7, '1', 'admin', 'admin', '2019-01-25 10:39:38', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (189, 1, 8, '1', 'admin', 'admin', '2019-01-25 10:39:38', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (190, 1, 9, '1', 'admin', 'admin', '2019-01-25 10:39:38', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (191, 1, 10, '1', 'admin', 'admin', '2019-01-25 10:39:38', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (192, 1, 11, '1', 'admin', 'admin', '2019-01-25 10:39:38', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (193, 1, 12, '1', 'admin', 'admin', '2019-01-25 10:39:38', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (194, 1, 13, '1', 'admin', 'admin', '2019-01-25 10:39:38', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (195, 1, 14, '1', 'admin', 'admin', '2019-01-25 10:39:38', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (196, 1, 15, '1', 'admin', 'admin', '2019-01-25 10:39:38', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (197, 1, 16, '1', 'admin', 'admin', '2019-01-25 10:39:38', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (198, 1, 17, '1', 'admin', 'admin', '2019-01-25 10:39:38', '2019-01-25 10:39:38');
INSERT INTO `st_role_authority` VALUES (199, 1, 18, '1', 'admin', 'admin', '2019-01-25 10:39:38', '2019-01-25 10:39:38');

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
INSERT INTO `st_user_role` VALUES (3, 1, 1, '1', NULL, NULL, NULL, '2019-01-04 10:38:31');

SET FOREIGN_KEY_CHECKS = 1;
