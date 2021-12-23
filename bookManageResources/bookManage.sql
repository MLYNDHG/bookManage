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

insert  into `book`(`id`,`name`,`description`,`author`,`status`,`log`,`time`) values (1,'西游记','哈哈哈','吴承恩',0,1,'2021-12-20 11:00:00'),(2,'红楼梦','红楼梦','曹雪芹',0,0,'2021-12-12 12:00:00'),(3,'水浒传','hhh','施耐庵',0,0,'2021-12-20 08:00:00'),(4,'三国演义','三国演义','罗贯中',0,0,'2021-12-20 15:21:23'),(5,'三国演义','三国演义','罗贯中',1,0,'2021-12-21 12:08:48');

/*Table structure for table `book_log` */

DROP TABLE IF EXISTS `book_log`;

CREATE TABLE `book_log` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '借阅id',
  `uid` int NOT NULL COMMENT '借阅的用户id',
  `bid` int NOT NULL COMMENT '借阅的图书id',
  `timeStart` datetime NOT NULL COMMENT '借阅时间',
  `timeEnd` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '还书时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `book_log` */

insert  into `book_log`(`id`,`uid`,`bid`,`timeStart`,`timeEnd`) values (1,1,1,'2021-12-12 11:00:00','0000-00-00 00:00:00'),(2,1,2,'2021-11-13 14:13:00','2021-12-12 12:00:00'),(3,4,3,'2021-12-12 13:00:00','2021-12-22 11:00:06'),(4,1,3,'2021-12-20 12:00:00','2021-12-21 14:18:54'),(5,4,3,'2021-12-21 14:19:35','2021-12-22 11:00:06'),(7,4,3,'2021-12-22 10:55:35','2021-12-22 11:00:06'),(8,4,3,'2021-12-22 11:04:22','2021-12-22 11:12:25'),(10,1,4,'2021-12-22 11:20:51','2021-12-22 11:21:03');

/*Table structure for table `primarytype` */

DROP TABLE IF EXISTS `primarytype`;

CREATE TABLE `primarytype` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '一级类型id',
  `name` varchar(30) NOT NULL COMMENT '一级类型名称',
  `time` datetime NOT NULL COMMENT '添加时间',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '一级类型是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `primarytype` */

insert  into `primarytype`(`id`,`name`,`time`,`status`) values (1,'内存数据库','2021-12-12 12:00:00',0),(2,'数据库','2021-12-12 13:12:00',0),(3,'信息中间件','2021-12-10 23:00:02',0),(4,'文件服务器','2021-10-13 13:03:33',0),(5,'应用资源','2021-12-02 08:14:11',0);

/*Table structure for table `redis` */

DROP TABLE IF EXISTS `redis`;

CREATE TABLE `redis` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'redisid',
  `rid` int NOT NULL COMMENT '资源id',
  `cluterAddress` varchar(100) NOT NULL COMMENT '集群主机地址',
  `maxJumps` int NOT NULL COMMENT '最大跳转次数',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `timeout` int NOT NULL COMMENT '超时时间',
  `maxThreadActivities` int NOT NULL COMMENT '线程最大活动数',
  `maxThreadIdles` int NOT NULL COMMENT '线程最大空闲数',
  `minThreadIdles` int NOT NULL COMMENT '线程最小空闲数',
  `maxThreadWaits` int NOT NULL COMMENT '线程最大等待数',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '配置是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `redis` */

insert  into `redis`(`id`,`rid`,`cluterAddress`,`maxJumps`,`password`,`timeout`,`maxThreadActivities`,`maxThreadIdles`,`minThreadIdles`,`maxThreadWaits`,`status`) values (1,6,'127.0.0.1:6379',3,'63a9f0ea7bb98050796b649e85481845',323434,45665,4322,3232,3532,0);

/*Table structure for table `resources` */

DROP TABLE IF EXISTS `resources`;

CREATE TABLE `resources` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '资源id',
  `sid` int NOT NULL COMMENT '二级类型id',
  `englishName` varchar(30) NOT NULL COMMENT '英文名称',
  `chineseName` varchar(30) NOT NULL COMMENT '中文名称',
  `description` text COMMENT '描述',
  `time` datetime NOT NULL COMMENT '添加时间',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '资源是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `resources` */

insert  into `resources`(`id`,`sid`,`englishName`,`chineseName`,`description`,`time`,`status`) values (1,15,'tomcat配置','tomcat配置','tomcat配置','2021-12-21 12:12:00',0),(2,14,'dubbo配置','dubbo配置','dubbo配置','2021-12-21 14:00:12',0),(3,4,'Mysql配置','Mysql配置','mysql配置','2021-12-21 15:13:14',0),(4,5,'tdsql配置','tdsql配置','tdsql配置','2021-12-21 15:14:12',0),(5,4,'ddd','ddd','ddd','2021-12-21 15:15:21',0),(6,10,'redis集群','redis集群','redis集群','2021-12-22 14:12:12',0),(7,16,'rocketMQ','rocketMQ','rocketMQ','2021-12-22 15:12:00',0);

/*Table structure for table `rocketmq` */

DROP TABLE IF EXISTS `rocketmq`;

CREATE TABLE `rocketmq` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'RocketMQid',
  `rid` int NOT NULL COMMENT '资源id',
  `nameServer` varchar(100) NOT NULL COMMENT '服务地址名称',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '配置是否删除',
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
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '二级类型是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `secondarytype` */

insert  into `secondarytype`(`id`,`pid`,`name`,`image`,`time`,`status`) values (1,1,'sqLite','/images/sqlite.png','2021-12-10 12:00:00',0),(2,1,'altibase','/images/altibase.png','2021-12-10 13:00:01',0),(3,1,'timesten','/images/timesten.png','2021-12-10 13:00:02',0),(4,2,'mysql','/images/mysql.png','2021-12-20 12:00:02',0),(5,2,'tdsql','/images/tdsql.png','2021-12-21 14:13:00',0),(6,2,'oracle','/images/oracle.png','2021-12-21 14:14:13',0),(7,2,'postgres11','/images/postgres11.png','2021-12-21 14:13:00',0),(8,3,'zeroMQ','/images/zeromq.png','2021-12-21 14:13:21',0),(9,3,'rabbitMQ','/images/rabbitmq.png','2021-12-21 14:14:13',0),(10,3,'redis','/images/redis.png','2021-12-21 15:14:00',0),(11,4,'FTP','/images/ftp.png','2021-12-21 15:15:13',0),(12,4,'NFS','/images/nfs.png','2021-12-21 15:20:00',0),(13,4,'samba','/images/samba.png','2021-12-21 15:21:22',0),(14,5,'dubbo','/images/dubbo.png','2021-12-21 15:32:34',0),(15,5,'tomcat','/images/tomcat.png','2021-12-21 15:45:32',0),(16,3,'rocketMQ','/images/rocketmq.png','2021-12-22 16:13:12',0);

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
