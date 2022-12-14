SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `balance` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of account
-- ----------------------------
BEGIN;
INSERT INTO `account` (`id`, `balance`) VALUES (1, 90);
INSERT INTO `account` (`id`, `balance`) VALUES (2, 110);
INSERT INTO `account` (`id`, `balance`) VALUES (3, 50);
INSERT INTO `account` (`id`, `balance`) VALUES (4, 150);
INSERT INTO `account` (`id`, `balance`) VALUES (5, 100);
COMMIT;

-- ----------------------------
-- Table structure for transaction
-- ----------------------------
DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` bigint(20) NOT NULL,
  `from_account_id` int(11) NOT NULL,
  `to_account_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `transaction_FK` (`from_account_id`),
  KEY `transaction_FK_1` (`to_account_id`),
  CONSTRAINT `transaction_FK_from` FOREIGN KEY (`from_account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `transaction_FK_to` FOREIGN KEY (`to_account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of transaction
-- ----------------------------
BEGIN;
INSERT INTO `transaction` (`id`, `amount`, `from_account_id`, `to_account_id`) VALUES (1, 10, 1, 2);
INSERT INTO `transaction` (`id`, `amount`, `from_account_id`, `to_account_id`) VALUES (2, 50, 3, 4);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
