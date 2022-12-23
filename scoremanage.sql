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

 Date: 22/12/2022 22:41:38
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
INSERT INTO `course` VALUES (2, '计算机', 2, '计算机', 50, 1, '2', 0);
INSERT INTO `course` VALUES (3, '大学英语', 1, '计算机', 50, 0, '3', 0);
INSERT INTO `course` VALUES (4, 'test', 5, '计算机', 22, 0, '2', 0);
INSERT INTO `course` VALUES (5, '计算机网络', 1, '计算机', 100, 1, '3', 0);
INSERT INTO `course` VALUES (6, '体系结构', 2, '计算机', 20, 1, '2', 0);

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
INSERT INTO `notice` VALUES (1, '放假', '放假', '2022-12-01 07:19:12', 1, '赵四', 0);
INSERT INTO `notice` VALUES (2, '不放假', '不放假', '2022-12-01 07:27:06', 1, '赵四', 0);
INSERT INTO `notice` VALUES (3, '不放假', '不放假', '2022-12-01 07:30:59', 1, '赵四', 0);
INSERT INTO `notice` VALUES (4, 'dd', 'dd', '2022-12-01 20:09:00', 1, '赵四', 1);
INSERT INTO `notice` VALUES (5, '考试', '明天考试', '2022-12-02 03:20:16', 1, '赵四', 1);
INSERT INTO `notice` VALUES (6, 'a', 'a', NULL, 1, '赵四', 1);
INSERT INTO `notice` VALUES (7, 'a', 'a', NULL, 1, '赵四', 1);
INSERT INTO `notice` VALUES (8, 'a', 'a', NULL, 1, '赵四', 1);
INSERT INTO `notice` VALUES (9, '放假啦', '因为新冠影响，我们放假啦', '2022-12-12 13:56:25', 1, '赵四', 0);
INSERT INTO `notice` VALUES (10, '麻了', '手机碎了', '2022-12-12 16:52:02', 1, '赵四', 1);
INSERT INTO `notice` VALUES (11, '', '', '2022-12-22 13:53:07', 2, '', 1);
INSERT INTO `notice` VALUES (12, '期末考试延期通知', '期末考试延期了', '2022-12-22 13:55:42', 2, '王五', 1);
INSERT INTO `notice` VALUES (13, '考试延期了', '考试延期了', '2022-12-22 14:03:43', 2, '王五', 0);
INSERT INTO `notice` VALUES (14, '新冠好难受', '淦', '2022-12-22 14:38:53', 1, '沸羊羊', 1);

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
INSERT INTO `ns` VALUES (3, 1, 1);
INSERT INTO `ns` VALUES (3, 2, 1);
INSERT INTO `ns` VALUES (3, 3, 1);
INSERT INTO `ns` VALUES (9, 1, 1);
INSERT INTO `ns` VALUES (9, 2, 1);

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
-- Records of sc
-- ----------------------------
INSERT INTO `sc` VALUES (1, 1, 100, 0, 100, 100, 100, 3, '数据库', 3, 1);
INSERT INTO `sc` VALUES (1, 3, 0, 0, 0, 0, 0, 3, '大学英语', 3, 0);
INSERT INTO `sc` VALUES (2, 1, 100, 0, 100, 100, 100, 3, '数据库', 3, 1);
INSERT INTO `sc` VALUES (3, 1, 0, 0, 0, 0, 0, 3, '数据库', 3, 0);
INSERT INTO `sc` VALUES (3, 2, 0, 1, 0, 0, 0, 3, '计算机', 2, 0);
INSERT INTO `sc` VALUES (3, 3, 0, 0, 0, 0, 0, 3, '大学英语', 3, 0);
INSERT INTO `sc` VALUES (3, 5, 0, 1, 0, 0, 0, 3, '计算机网络', 3, 0);

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
INSERT INTO `student` VALUES (1, '曾旭', '男', 3, 6, '计算机科学与技术', '1234', 0);
INSERT INTO `student` VALUES (2, '许睿杰', '男', 3, 6, '计算机', '1234', 0);
INSERT INTO `student` VALUES (3, '罗文典', '男', 3, 3, '计算机', '111', 0);
INSERT INTO `student` VALUES (4, '蒋卓航', '男', 3, 2, '计算机', '123', 0);
INSERT INTO `student` VALUES (5, 'jzh', '3', 4, 4, 'c', '1234', 0);

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
INSERT INTO `tc` VALUES (2, 1);

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
INSERT INTO `teacher` VALUES (1, '赵四', '123', '男', '计算机', 20, 0);
INSERT INTO `teacher` VALUES (2, '王五', '1234567', '男', '计算机', 21, 0);
INSERT INTO `teacher` VALUES (3, '赵六', '1234567', '男', '计算机', 20, 0);
INSERT INTO `teacher` VALUES (4, '王二麻子', '1234567', '男', '计算机', 20, 0);
INSERT INTO `teacher` VALUES (5, '李四', '1234', '女', '产后护理', 38, 0);

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

-- ----------------------------
-- Records of teacherscore
-- ----------------------------
INSERT INTO `teacherscore` VALUES (1, 1, 100, '好评', 1);

SET FOREIGN_KEY_CHECKS = 1;
