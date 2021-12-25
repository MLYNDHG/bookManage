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

/*Table structure for table `firstitemsystem` */

DROP TABLE IF EXISTS `firstitemsystem`;

CREATE TABLE `firstitemsystem` (
  `mid` int NOT NULL AUTO_INCREMENT,
  `systemName` varchar(30) DEFAULT NULL,
  `systemCOde` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;

/*Data for the table `firstitemsystem` */

insert  into `firstitemsystem`(`mid`,`systemName`,`systemCOde`) values (1,'渠道整合平台系统','SYS-CIP'),(2,'开放平台系统','SYS-OPENAPI'),(3,'电子渠道系统','SYS-CHANEL'),(4,'建瓯市','jos'),(7,'芜湖','哈哈'),(8,'呀呀呀','来来来'),(9,'name','');

/*Table structure for table `firstitemsysteminfo` */

DROP TABLE IF EXISTS `firstitemsysteminfo`;

CREATE TABLE `firstitemsysteminfo` (
  `wid` int NOT NULL AUTO_INCREMENT,
  `code` varchar(30) DEFAULT NULL,
  `chinese` varchar(30) DEFAULT NULL,
  `english` varchar(30) DEFAULT NULL,
  `fanTi` varchar(30) DEFAULT NULL,
  `InfoDate` varchar(30) DEFAULT NULL,
  `mid` int NOT NULL,
  PRIMARY KEY (`wid`),
  KEY `fk_SystemInfo` (`mid`),
  CONSTRAINT `fk_SystemInfo` FOREIGN KEY (`mid`) REFERENCES `firstitemsystem` (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3;

/*Data for the table `firstitemsysteminfo` */

insert  into `firstitemsysteminfo`(`wid`,`code`,`chinese`,`english`,`fanTi`,`InfoDate`,`mid`) values (1,'CIP000023','测试储存1','test1','繁体1','2020-1-1',1),(2,'CIP000024','测试储存2','test2','繁2体','2020-1-2',2),(3,'CIP000025','测试储存3','test3','繁3体','2020-1-3',3),(7,'CIP000029','芜湖芜湖','jos','建瓯市','43831',1),(12,'123','444','123','123','123',2),(13,'123','444','123','123','123',2),(14,'123','444','123','123','123',2),(15,'123','444','123','123','123',2),(16,'123','444','123','123','123',2),(17,'123','444','123','123','123',2),(18,'123','444','123','123','123',2),(19,'123','444','123','123','123',2),(20,'123','444','123','123','123',2),(21,'123','444','123','123','123',2),(22,'123','444','123','123','123',2);

/*Table structure for table `flow_source` */

DROP TABLE IF EXISTS `flow_source`;

CREATE TABLE `flow_source` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `fid` int NOT NULL COMMENT '流控id',
  `sid` int NOT NULL COMMENT '源列表id',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `flow_source` */

insert  into `flow_source`(`id`,`fid`,`sid`,`status`,`time`) values (1,1,1,0,'2021-12-12 12:00:00'),(2,1,3,0,'2021-12-20 13:00:00'),(3,6,1,1,'2021-12-24 19:38:58'),(4,6,4,1,'2021-12-24 19:38:58'),(5,6,6,1,'2021-12-24 19:38:58'),(6,7,1,1,'2021-12-24 19:41:16'),(7,7,4,1,'2021-12-24 19:41:16'),(8,7,5,1,'2021-12-24 19:41:16'),(9,8,1,0,'2021-12-24 23:43:15'),(10,8,4,0,'2021-12-24 23:43:15'),(11,8,5,0,'2021-12-24 23:43:15'),(12,9,1,1,'2021-12-24 23:47:42'),(13,9,2,1,'2021-12-24 23:47:42'),(14,9,4,1,'2021-12-24 23:47:42'),(15,9,5,1,'2021-12-24 23:47:42'),(16,9,6,1,'2021-12-24 23:47:42'),(17,9,7,1,'2021-12-24 23:47:42'),(18,10,1,0,'2021-12-24 23:49:25'),(19,10,3,0,'2021-12-24 23:49:25'),(20,10,4,0,'2021-12-24 23:49:25'),(21,10,7,0,'2021-12-25 00:10:51'),(22,10,8,0,'2021-12-25 00:10:51'),(23,10,9,0,'2021-12-25 00:10:51'),(24,10,11,0,'2021-12-25 00:10:51'),(25,11,1,0,'2021-12-25 00:18:41'),(26,11,2,0,'2021-12-25 00:18:41'),(27,11,3,0,'2021-12-25 00:18:41'),(28,11,4,0,'2021-12-25 00:18:41'),(29,11,5,0,'2021-12-25 00:18:41'),(30,11,6,0,'2021-12-25 00:18:41'),(31,11,7,0,'2021-12-25 00:18:41'),(32,11,8,0,'2021-12-25 00:18:41'),(33,11,9,0,'2021-12-25 00:18:41'),(34,11,10,0,'2021-12-25 00:18:41'),(35,11,11,0,'2021-12-25 00:18:41'),(36,11,12,0,'2021-12-25 00:18:41'),(37,11,13,0,'2021-12-25 00:18:41'),(38,11,14,0,'2021-12-25 00:18:41'),(39,11,15,0,'2021-12-25 00:18:41'),(40,11,16,0,'2021-12-25 00:18:41'),(41,11,17,0,'2021-12-25 00:18:41'),(42,11,18,0,'2021-12-25 00:18:41'),(43,11,19,0,'2021-12-25 00:18:41'),(44,11,20,0,'2021-12-25 00:18:41'),(45,11,21,0,'2021-12-25 00:18:41'),(46,11,22,0,'2021-12-25 00:18:41'),(47,11,23,0,'2021-12-25 00:18:41'),(48,11,24,0,'2021-12-25 00:18:41'),(49,11,25,0,'2021-12-25 00:18:41'),(50,11,26,0,'2021-12-25 00:18:41'),(51,11,27,0,'2021-12-25 00:18:41'),(52,11,28,0,'2021-12-25 00:18:41'),(53,11,29,0,'2021-12-25 00:18:41'),(54,11,30,0,'2021-12-25 00:18:41');

/*Table structure for table `flowrule` */

DROP TABLE IF EXISTS `flowrule`;

CREATE TABLE `flowrule` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '流控id',
  `name` varchar(30) NOT NULL COMMENT '规则名称',
  `flowPattern` varchar(30) NOT NULL COMMENT '流控模式',
  `thresholdType` varchar(30) NOT NULL COMMENT '阈值类型',
  `threshold` int NOT NULL COMMENT '阈值',
  `thresholdPattern` varchar(30) NOT NULL COMMENT '阈值模式',
  `flowEffect` varchar(30) NOT NULL COMMENT '流控效果',
  `requestSource` varchar(30) NOT NULL COMMENT '请求来源',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '流控是否删除',
  `time` datetime NOT NULL COMMENT '流控创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `flowrule` */

insert  into `flowrule`(`id`,`name`,`flowPattern`,`thresholdType`,`threshold`,`thresholdPattern`,`flowEffect`,`requestSource`,`status`,`time`) values (1,'sdasdf','rga','sadfa',0,'asdf','asdf','adsf',0,'2021-12-12 12:00:00'),(3,'fdgg','dfag','dfsghfsdfh',0,'dfgsdg','dfsgsdgsdf','hsdfhsd',0,'2021-12-20 13:00:00'),(6,'gdsfg','dfssg','asdgg',23,'单机','sdgsg','dfsgag',0,'2021-12-24 19:38:58'),(7,'gdsfg','dfssg','asdgg',23,'sadfag','sdgsg','dfsgag',1,'2021-12-24 19:41:16'),(8,'gdsfg','dfssg','asdgg',23,'sadfag','sdgsg','dfsgag',0,'2021-12-24 23:43:15'),(9,'123','直接','QPS',123,'单机','快速失败','xxx系统',1,'2021-12-24 23:47:42'),(10,'芜湖','链路','并发线程数',123,'单机','排队等候','xxx系统',0,'2021-12-24 23:49:25'),(11,'酷酷酷酷酷酷222','直接','QPS',123,'单机','排队等候','xxx系统',0,'2021-12-25 00:18:41'),(12,'1','直接','QPS',1,'单机','快速失败','xxx系统',1,'2021-12-25 00:25:32');

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
  `time` datetime NOT NULL COMMENT '配置添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `redis` */

insert  into `redis`(`id`,`rid`,`cluterAddress`,`maxJumps`,`password`,`timeout`,`maxThreadActivities`,`maxThreadIdles`,`minThreadIdles`,`maxThreadWaits`,`status`,`time`) values (1,6,'127.0.0.1:6379',4,'dfdfdfdfdfdfdf',323434,45665,4322,3232,3532,0,'2021-12-12 13:00:00'),(2,3,'127.0.0.1:2342',4,'sdagg',12314,123,123,123,13,0,'2021-12-23 13:24:45'),(3,2,'127.0.0.1:2342',4,'sdagg',12314,123,123,123,13,0,'2021-12-23 13:35:18');

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `resources` */

insert  into `resources`(`id`,`sid`,`englishName`,`chineseName`,`description`,`time`,`status`) values (1,15,'tomcat配置','tomcat配置','tomcat配置','2021-12-21 12:12:00',0),(2,14,'dubbo配置','dubbo配置','dubbo配置','2021-12-21 14:00:12',0),(3,4,'Mysql配置','Mysql配置','mysql配置','2021-12-21 15:13:14',0),(4,5,'tdsql配置','tdsql配置','tdsql配置','2021-12-21 15:14:12',1),(5,5,'qwer','qwer','qwer','2021-12-21 15:15:21',0),(6,10,'redis集群','redis集群','redis集群','2021-12-22 14:12:12',0),(7,16,'rocketMQ','rocketMQ','rocketMQ','2021-12-22 15:12:00',0),(8,5,'adsf','爱的色放','adsf','2021-12-23 11:12:29',0),(9,1,'asd','asd','asd','2021-12-24 20:09:31',0);

/*Table structure for table `rocketmq` */

DROP TABLE IF EXISTS `rocketmq`;

CREATE TABLE `rocketmq` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'RocketMQid',
  `rid` int NOT NULL COMMENT '资源id',
  `nameServer` varchar(100) NOT NULL COMMENT '服务地址名称',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '配置是否删除',
  `time` datetime NOT NULL COMMENT '配置添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `rocketmq` */

insert  into `rocketmq`(`id`,`rid`,`nameServer`,`status`,`time`) values (1,7,'127.0.0.1:9090;127.0.0.1:8334',0,'2021-12-23 13:45:55');

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

/*Table structure for table `sourcelist` */

DROP TABLE IF EXISTS `sourcelist`;

CREATE TABLE `sourcelist` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '源id',
  `name` varchar(100) NOT NULL COMMENT '源名称',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '源是否删除',
  `time` datetime NOT NULL COMMENT '源创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sourcelist` */

insert  into `sourcelist`(`id`,`name`,`status`,`time`) values (1,'selectBookList',0,'2021-12-24 16:26:33'),(2,'saveBook',0,'2021-12-24 16:26:33'),(3,'deleteBook',0,'2021-12-24 16:26:33'),(4,'selectBookLogList',0,'2021-12-24 16:26:33'),(5,'saveBookLog',0,'2021-12-24 16:26:33'),(6,'insertSystemOfExcel',0,'2021-12-24 16:26:33'),(7,'insertSystem',0,'2021-12-24 16:26:33'),(8,'selectSystemList',0,'2021-12-24 16:26:33'),(9,'deleteByPrimaryKeySystemInfo',0,'2021-12-24 16:26:33'),(10,'deleteSystemInfoOfList',0,'2021-12-24 16:26:33'),(11,'selectSystemInfoList',0,'2021-12-24 16:26:33'),(12,'insertSystemInfoOfExcel',0,'2021-12-24 16:26:33'),(13,'insertSystemInfo',0,'2021-12-24 16:26:33'),(14,'selectRedisByResourcesId',0,'2021-12-24 16:26:33'),(15,'saveRedis',0,'2021-12-24 16:26:33'),(16,'selectResourceBasicList',0,'2021-12-24 16:26:33'),(17,'saveResourcesBasic',0,'2021-12-24 16:26:33'),(18,'selectTypeList',0,'2021-12-24 16:26:33'),(19,'deleteResources',0,'2021-12-24 16:26:33'),(20,'selectRocketmqByResourcesId',0,'2021-12-24 16:26:34'),(21,'saveRocketmq',0,'2021-12-24 16:26:34'),(22,'login',0,'2021-12-24 16:26:34'),(23,'uploadImage',0,'2021-12-24 16:26:34'),(24,'selectUserList',0,'2021-12-24 16:26:34'),(25,'saveUser',0,'2021-12-24 16:26:34'),(26,'updatePassword',0,'2021-12-24 16:26:34'),(27,'selectFlowRuleList',0,'2021-12-24 16:45:03'),(28,'saveFlowRule',0,'2021-12-24 19:17:16'),(29,'deleteFlowRule',0,'2021-12-24 19:50:04'),(30,'selectSourcelist',0,'2021-12-24 20:42:29');

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

insert  into `user`(`id`,`username`,`nickname`,`password`,`head`,`tel`,`email`,`status`,`is_super`) values (1,'root',NULL,'63a9f0ea7bb98050796b649e85481845','/images/1640361814015.png',NULL,NULL,0,1),(4,'wang','sb','e08392bb89dedb8ed6fb298f8e729c15',NULL,NULL,NULL,0,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
