/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : 121.40.236.249:3306
 Source Schema         : redis

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 17/01/2022 22:45:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `age` int NOT NULL,
  `bir` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'azure', 2, '2022-01-17 21:52:12');
INSERT INTO `user` VALUES (2, 'wang', 3, '2022-01-17 21:52:12');
INSERT INTO `user` VALUES (3, 'cheng', 4, '2022-01-17 21:52:12');
INSERT INTO `user` VALUES (4, 'hao', 5, '2022-01-17 21:52:19');

SET FOREIGN_KEY_CHECKS = 1;
