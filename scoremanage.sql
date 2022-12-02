/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : scoremanage

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 02/12/2022 10:12:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `uuid` int(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `teacher_id` int(0) NULL DEFAULT NULL,
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `max_num` int(0) NULL DEFAULT NULL,
  `Compulsory` int(0) NULL DEFAULT NULL,
  `credit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isdeleted` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '数据库', 1, '计算机', 100, 0, '3', 0);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `uuid` int(0) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL,
  `teacheruuid` int(0) NULL DEFAULT NULL,
  `teachername` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isdeleted` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '放假', '放假', '2022-12-01 07:19:12', 1, 'jzh', 0);
INSERT INTO `notice` VALUES (2, '不放假', '不放假', '2022-12-01 07:27:06', 1, 'jzh', 0);
INSERT INTO `notice` VALUES (3, '不放假', '不放假', '2022-12-01 07:30:59', 1, 'jzh', 0);
INSERT INTO `notice` VALUES (4, 'dd', 'dd', '2022-12-01 20:09:00', 1, 'jzh', 0);

-- ----------------------------
-- Table structure for ns
-- ----------------------------
DROP TABLE IF EXISTS `ns`;
CREATE TABLE `ns`  (
  `noticeuuid` int(0) NOT NULL,
  `studentuuid` int(0) NOT NULL,
  `teacheruuid` int(0) NOT NULL,
  PRIMARY KEY (`noticeuuid`, `studentuuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ns
-- ----------------------------
INSERT INTO `ns` VALUES (1, 1, 1);
INSERT INTO `ns` VALUES (1, 2, 1);
INSERT INTO `ns` VALUES (2, 1, 1);
INSERT INTO `ns` VALUES (3, 1, 1);
INSERT INTO `ns` VALUES (4, 1, 1);

-- ----------------------------
-- Table structure for sc
-- ----------------------------
DROP TABLE IF EXISTS `sc`;
CREATE TABLE `sc`  (
  `studentuuid` int(0) NOT NULL,
  `courseuuid` int(0) NOT NULL,
  `score` int(0) NULL DEFAULT NULL,
  `Compulsory` int(0) NULL DEFAULT NULL,
  `examscore` int(0) NULL DEFAULT NULL,
  `dailyscore` int(0) NULL DEFAULT NULL,
  `checkscore` int(0) NULL DEFAULT NULL,
  `grade` int(0) NULL DEFAULT NULL,
  `courseName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `credit` int(0) NULL DEFAULT NULL,
  `is_updated` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`studentuuid`, `courseuuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `uuid` int(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grade` int(0) NULL DEFAULT NULL,
  `class_num` int(0) NULL DEFAULT NULL,
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isdeleted` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 'jzh', '男', 2, 6, '计算机科学与技术', '1234', 0);
INSERT INTO `student` VALUES (2, 'xrj', '男', 3, 6, '计算机', '1234', 0);
INSERT INTO `student` VALUES (3, 'lwd', '男', 3, 3, '计算机', '111', 0);
INSERT INTO `student` VALUES (4, '蒋卓航', '男', 3, 2, '计算机', '123', 0);

-- ----------------------------
-- Table structure for tc
-- ----------------------------
DROP TABLE IF EXISTS `tc`;
CREATE TABLE `tc`  (
  `teacheruuid` int(0) NOT NULL,
  `courseuuid` int(0) NOT NULL,
  PRIMARY KEY (`courseuuid`, `teacheruuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tc
-- ----------------------------
INSERT INTO `tc` VALUES (1, 1);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `uuid` int(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(0) NULL DEFAULT NULL,
  `isdeleted` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, 'jzh', '123456', '男', '计算机科学与技术', 21, 0);
INSERT INTO `teacher` VALUES (2, 'zx', '1234567', '男', '计算机', 21, 0);
INSERT INTO `teacher` VALUES (3, 'lwd', '1234567', '男', '计算机', 20, 0);
INSERT INTO `teacher` VALUES (4, 'xrj', '1234567', '男', '计算机', 20, 0);

-- ----------------------------
-- Table structure for teacherscore
-- ----------------------------
DROP TABLE IF EXISTS `teacherscore`;
CREATE TABLE `teacherscore`  (
  `studentuuid` int(0) NOT NULL,
  `teacheruuid` int(0) NOT NULL,
  `score` int(0) NULL DEFAULT NULL,
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `courseuuid` int(0) NOT NULL,
  PRIMARY KEY (`studentuuid`, `teacheruuid`, `courseuuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
