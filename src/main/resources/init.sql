/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80100
 Source Host           : localhost:3306
 Source Schema         : device_test

 Target Server Type    : MySQL
 Target Server Version : 80100
 File Encoding         : 65001

 Date: 02/10/2023 17:00:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sz_data_record
-- ----------------------------
DROP TABLE IF EXISTS `sz_data_record`;
CREATE TABLE `sz_data_record` (
  `id` int NOT NULL AUTO_INCREMENT,
  `upload_user` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '上传用户',
  `upload_device_id` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '上传设备id',
  `upload_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  `create_id` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_id` int NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `file_path` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `upload_server_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '同步状态,NOT_UPLOAD,UPLOAD',
  `upload_device_type` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '上传设备类型',
  `upload_serial_id` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '部品序列号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- ----------------------------
-- Table structure for sz_device
-- ----------------------------
DROP TABLE IF EXISTS `sz_device`;
CREATE TABLE `sz_device` (
  `id` int NOT NULL AUTO_INCREMENT,
  `device_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '设备id',
  `device_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '设备类型',
  `device_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '设备名',
  `device_desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '设备描述',
  `create_id` int NOT NULL DEFAULT '1' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_id` int NOT NULL DEFAULT '1' COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `upper_threshold` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '阈值上限',
  `lower_threshold` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '阈值下限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for sz_device_type
-- ----------------------------
DROP TABLE IF EXISTS `sz_device_type`;
CREATE TABLE `sz_device_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `device_type` varchar(255) NOT NULL DEFAULT '' COMMENT '设备类型code',
  `device_type_name` varchar(255) NOT NULL DEFAULT '' COMMENT '设备类型name',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sz_device_type
-- ----------------------------
BEGIN;
-- 测试数据，需要删除
INSERT INTO `sz_device_type` VALUES (1, 'device_0', 'device_0');
COMMIT;

-- ----------------------------
-- Table structure for sz_user
-- ----------------------------
DROP TABLE IF EXISTS `sz_user`;
CREATE TABLE `sz_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '账号名',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '姓名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '密码',
  `create_id` int NOT NULL DEFAULT '1' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_id` int NOT NULL DEFAULT '1' COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `account_role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'NORMAL' COMMENT '角色类型,NORMAL,ADMIN',
  `enabled` tinyint NOT NULL DEFAULT '1' COMMENT '是否启用',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username_uq` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sz_user
-- ----------------------------
BEGIN;
INSERT INTO `sz_user` VALUES (1, 'admin', 'admin', 'admin', 1, NOW(), 1, NOW(), 'ADMIN', 1, NOW());
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;