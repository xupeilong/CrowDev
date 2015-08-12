/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50545
Source Host           : localhost:3306
Source Database       : crowdev

Target Server Type    : MYSQL
Target Server Version : 50545
File Encoding         : 65001

Date: 2015-08-12 18:01:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for arch
-- ----------------------------
DROP TABLE IF EXISTS `arch`;
CREATE TABLE `arch` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for source_file
-- ----------------------------
DROP TABLE IF EXISTS `source_file`;
CREATE TABLE `source_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `work_context_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `srcPath` varchar(255) DEFAULT NULL,
  `filePath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for source_file_imp
-- ----------------------------
DROP TABLE IF EXISTS `source_file_imp`;
CREATE TABLE `source_file_imp` (
  `id` int(11) NOT NULL,
  `source_file_id` int(11) DEFAULT NULL,
  `imp_file_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for work_context
-- ----------------------------
DROP TABLE IF EXISTS `work_context`;
CREATE TABLE `work_context` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `arch_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
