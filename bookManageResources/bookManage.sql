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
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '图书状态',
  `log` tinyint(1) NOT NULL DEFAULT '0' COMMENT '图书借阅状态',
  `time` datetime NOT NULL COMMENT '图书添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `book` */

insert  into `book`(`id`,`name`,`description`,`author`,`status`,`log`,`time`) values (1,'西游记','哈哈哈','吴承恩',0,1,'2021-12-20 11:00:00'),(2,'红楼梦','红楼梦','曹雪芹',0,0,'2021-12-12 12:00:00'),(3,'水浒传','hhh','施耐庵',0,1,'2021-12-20 08:00:00'),(4,'三国演义','三国演义','罗贯中',0,0,'2021-12-20 15:21:23'),(5,'三国演义','三国演义','罗贯中',1,0,'2021-12-21 12:08:48');

/*Table structure for table `book_log` */

DROP TABLE IF EXISTS `book_log`;

CREATE TABLE `book_log` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '借阅id',
  `uid` int NOT NULL COMMENT '借阅的用户id',
  `bid` int NOT NULL COMMENT '借阅的图书id',
  `timeStart` datetime NOT NULL COMMENT '借阅时间',
  `timeEnd` datetime DEFAULT NULL COMMENT '还书时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `book_log` */

insert  into `book_log`(`id`,`uid`,`bid`,`timeStart`,`timeEnd`) values (1,1,1,'2021-12-12 11:00:00',NULL),(2,1,2,'2021-11-13 14:13:00','2021-12-12 12:00:00'),(3,4,3,'2021-12-12 13:00:00','2021-12-20 14:12:12'),(4,1,3,'2021-12-20 12:00:00',NULL);

/*Table structure for table `primarytype` */

DROP TABLE IF EXISTS `primarytype`;

CREATE TABLE `primarytype` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '一级类型id',
  `name` varchar(30) NOT NULL COMMENT '一级类型名称',
  `time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `primarytype` */

/*Table structure for table `redis` */

DROP TABLE IF EXISTS `redis`;

CREATE TABLE `redis` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'redisid',
  `rid` int NOT NULL COMMENT '资源id',
  `cluterAddress` varchar(100) NOT NULL COMMENT '集群主机地址',
  `maxJumps` timestamp NOT NULL COMMENT '最大跳转次数',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `timeout` timestamp NOT NULL COMMENT '超时时间',
  `maxThreadActivities` int NOT NULL COMMENT '线程最大活动数',
  `maxThreadIdles` int NOT NULL COMMENT '线程最大空闲数',
  `minThreadIdles` int NOT NULL COMMENT '线程最小空闲数',
  `maxThreadWaits` int NOT NULL COMMENT '线程最大等待数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `redis` */

/*Table structure for table `resource` */

DROP TABLE IF EXISTS `resource`;

CREATE TABLE `resource` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '资源id',
  `sid` int NOT NULL COMMENT '二级类型id',
  `englishName` varchar(30) NOT NULL COMMENT '英文名称',
  `chineseName` varchar(30) NOT NULL COMMENT '中文名称',
  `description` text COMMENT '描述',
  `time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `resource` */

/*Table structure for table `rocketmq` */

DROP TABLE IF EXISTS `rocketmq`;

CREATE TABLE `rocketmq` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'RocketMQid',
  `rid` int NOT NULL COMMENT '资源id',
  `nameServer` varchar(100) NOT NULL COMMENT '服务地址名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `rocketmq` */

/*Table structure for table `secondarytype` */

DROP TABLE IF EXISTS `secondarytype`;

CREATE TABLE `secondarytype` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '二级类型id',
  `pid` int NOT NULL COMMENT '一级类型id',
  `name` varchar(30) NOT NULL COMMENT '类型名称',
  `image` varchar(100) NOT NULL COMMENT '类型照片',
  `time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `secondarytype` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(30) NOT NULL COMMENT '用户名',
  `nickname` varchar(30) DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户密码',
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
