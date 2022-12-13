/*
 Navicat Premium Data Transfer

 Source Server         : local-mysql
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : funbank

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 11/12/2022 23:33:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `balance` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for transaction
-- ----------------------------
DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` bigint NOT NULL,
  `from_account_id` int NOT NULL,
  `to_account_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `transaction_FK` (`from_account_id`),
  KEY `transaction_FK_1` (`to_account_id`),
  CONSTRAINT `transaction_FK_from` FOREIGN KEY (`from_account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `transaction_FK_to` FOREIGN KEY (`to_account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
