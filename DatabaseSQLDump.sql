/*
SQLyog Enterprise - MySQL GUI v7.15 
MySQL - 5.6.23 : Database - mywebbudget
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`mywebbudget` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE USER 'admin'@'localhost' IDENTIFIED BY 'secretpassword';

GRANT ALL ON mywebbudget.* to 'admin'@'localhost';

FLUSH PRIVILEGES;

USE `mywebbudget`;

/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `account_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `money` decimal(15,2) NOT NULL,
  `user` int(11) unsigned NOT NULL,
  PRIMARY KEY (`account_id`),
  KEY `FK_account_user_user_id` (`user`),
  CONSTRAINT `FK_account_user_user_id` FOREIGN KEY (`user`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `category_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `user` int(11) unsigned NOT NULL,
  PRIMARY KEY (`category_id`),
  KEY `FK_category_user_user_id` (`user`),
  CONSTRAINT `FK_category_user_user_id` FOREIGN KEY (`user`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `transaction` */

DROP TABLE IF EXISTS `transaction`;

CREATE TABLE `transaction` (
  `transaction_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `amount` decimal(15,2) NOT NULL,
  `date` date NOT NULL,
  `account` int(11) unsigned NOT NULL,
  `category` int(11) unsigned NOT NULL,
  `comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `FK_transaction_account_account_id` (`account`),
  KEY `FK_transaction_category_category_id` (`category`),
  CONSTRAINT `FK_transaction_account_account_id` FOREIGN KEY (`account`) REFERENCES `account` (`account_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_transaction_category_category_id` FOREIGN KEY (`category`) REFERENCES `category` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(15) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
