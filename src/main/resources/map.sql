/*
 Navicat Premium Data Transfer

 Source Server         : localhost@mysql
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : map

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 29/05/2020 15:49:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for black_list
-- ----------------------------
DROP TABLE IF EXISTS `black_list`;
CREATE TABLE `black_list`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户IP',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for event
-- ----------------------------
DROP TABLE IF EXISTS `event`;
CREATE TABLE `event`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `lng` double(255, 8) NOT NULL,
  `lat` double(255, 8) NOT NULL,
  `time` datetime(0) NOT NULL,
  `event_type_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '事件类型ID',
  `flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '事件处理标志位0未处理1已处理',
  `details` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '事件细节',
  `remark` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `事件名`(`event_type_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of event
-- ----------------------------
INSERT INTO `event` VALUES ('1001', 118.10388605, 24.48923061, '2020-05-26 00:00:00', '1', '0', '地址：北京市东城区王府井大街88号乐天银泰百货八层', '周二');
INSERT INTO `event` VALUES ('1002', 118.10988608, 24.48923065, '2020-05-25 14:03:45', '2', '0', '地址：厦门', '周一');
INSERT INTO `event` VALUES ('1003', 118.11088605, 24.49023061, '2020-04-01 14:39:33', '3', '0', '地址：厦门思明区 ', '4月份');
INSERT INTO `event` VALUES ('1004', 120.11088605, 26.49023061, '2020-05-26 16:41:45', '4', '0', '地址：测试 ', '非厦门周二');
INSERT INTO `event` VALUES ('1005', 118.15158000, 24.54927200, '2020-05-24 17:59:50', '5', '0', '地址：测试', '周天');
INSERT INTO `event` VALUES ('1006', 118.15186800, 24.53375700, '2020-05-23 18:02:04', '6', '0', '地址：测试', '周六');
INSERT INTO `event` VALUES ('1007', 118.17400200, 24.52823400, '2020-05-22 18:04:29', '7', '0', '地址：测试', '周五');
INSERT INTO `event` VALUES ('1008', 118.16279100, 24.51192800, '2020-04-23 18:04:46', '8', '0', '地址：测试', '周四');
INSERT INTO `event` VALUES ('1009', 118.18147600, 24.50219500, '2020-04-22 18:05:53', '9', '0', '地址：测试', '周三');
INSERT INTO `event` VALUES ('1010', 118.14726900, 24.45931100, '2020-04-21 18:07:13', '10', '0', '地址：测试', '上一周周二');
INSERT INTO `event` VALUES ('1011', 118.17601400, 24.48535900, '2020-05-29 18:04:29', '11', '0', '地址：测试', '周五第二件');
INSERT INTO `event` VALUES ('1012', 118.11191100, 24.52218500, '2020-05-26 18:39:02', '1', '0', '地址：测试', '周二第二件同样的');
INSERT INTO `event` VALUES ('1013', 118.12254700, 24.50508900, '2020-05-25 21:20:18', '1', '0', '地址：测试', '周一第二件不同的');

-- ----------------------------
-- Table structure for event_tmp
-- ----------------------------
DROP TABLE IF EXISTS `event_tmp`;
CREATE TABLE `event_tmp`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `lng` double(255, 0) NOT NULL,
  `lat` double(255, 0) NOT NULL,
  `time` datetime(0) NOT NULL,
  `event_type_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '提交事件的用户的联系方式',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '提交事件的用户的IP',
  `flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '事件审核位0未审核1已审核',
  `details` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '事件细节',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for event_type
-- ----------------------------
DROP TABLE IF EXISTS `event_type`;
CREATE TABLE `event_type`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `event_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '事件名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of event_type
-- ----------------------------
INSERT INTO `event_type` VALUES ('1', '纵火');
INSERT INTO `event_type` VALUES ('10', '抢劫');
INSERT INTO `event_type` VALUES ('11', '性犯罪');
INSERT INTO `event_type` VALUES ('12', '盗窃/盗窃罪');
INSERT INTO `event_type` VALUES ('13', '故意破坏');
INSERT INTO `event_type` VALUES ('14', '车辆盗窃案');
INSERT INTO `event_type` VALUES ('15', '武器');
INSERT INTO `event_type` VALUES ('16', 'Sex Offender');
INSERT INTO `event_type` VALUES ('17', 'Sexual Predator');
INSERT INTO `event_type` VALUES ('2', '攻击');
INSERT INTO `event_type` VALUES ('3', '入室盗窃');
INSERT INTO `event_type` VALUES ('4', '扰乱治安');
INSERT INTO `event_type` VALUES ('5', '药物/酒精违规');
INSERT INTO `event_type` VALUES ('6', '酒后驾车');
INSERT INTO `event_type` VALUES ('7', '欺诈');
INSERT INTO `event_type` VALUES ('8', '凶杀案');
INSERT INTO `event_type` VALUES ('9', '汽车盗窃');

-- ----------------------------
-- Table structure for sys_admin
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin`;
CREATE TABLE `sys_admin`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `permission_level` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_admin
-- ----------------------------
INSERT INTO `sys_admin` VALUES ('1', 'admin@qq.com', '123456', '0');
INSERT INTO `sys_admin` VALUES ('2', 'third@test.com', '123', '1');

-- ----------------------------
-- Table structure for sys_admin_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_log`;
CREATE TABLE `sys_admin_log`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `sys_admin_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `operation` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
