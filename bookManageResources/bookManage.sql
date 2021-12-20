/*
SQLyog Ultimate - MySQL GUI v8.2 
MySQL - 8.0.26 : Database - finallytest
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`finallytest` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `finallytest`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '图书id',
  `name` varchar(30) NOT NULL COMMENT '图书名称',
  `description` text COMMENT '图书描述',
  `author` varchar(20) NOT NULL COMMENT '图书作者',
  `status` tinyint(1) NOT NULL COMMENT '图书状态',
  `time` datetime NOT NULL COMMENT '图书添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `book` */

/*Table structure for table `book_log` */

DROP TABLE IF EXISTS `book_log`;

CREATE TABLE `book_log` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '借阅id',
  `uid` int NOT NULL COMMENT '借阅的用户id',
  `bid` int NOT NULL COMMENT '借阅的图书id',
  `timeStart` datetime NOT NULL COMMENT '借阅时间',
  `timeEnd` datetime DEFAULT NULL COMMENT '还书时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `book_log` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(30) NOT NULL COMMENT '用户名',
  `nickname` varchar(30) DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `head` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `tel` varchar(30) DEFAULT NULL COMMENT '用户电话',
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户邮箱',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户状态',
  `is_super` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否为超级用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`nickname`,`password`,`head`,`tel`,`email`,`status`,`is_super`) values (1,'root',NULL,'63a9f0ea7bb98050796b649e85481845',NULL,NULL,NULL,0,1),(4,'wang','sb','e08392bb89dedb8ed6fb298f8e729c15',NULL,NULL,NULL,0,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
