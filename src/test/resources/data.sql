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
-- Records of transaction
-- ----------------------------
BEGIN;
INSERT INTO `transaction` (`id`, `amount`, `from_account_id`, `to_account_id`) VALUES (1, 10, 1, 2);
INSERT INTO `transaction` (`id`, `amount`, `from_account_id`, `to_account_id`) VALUES (2, 50, 3, 4);
COMMIT;
