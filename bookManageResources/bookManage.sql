# Host: 127.0.0.1  (Version 8.0.16)
# Date: 2021-12-23 22:52:02
# Generator: MySQL-Front 6.0  (Build 1.57)


#
# Structure for table "book"
#

DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '图书id',
  `name` varchar(30) NOT NULL COMMENT '图书名称',
  `description` text COMMENT '图书描述',
  `author` varchar(20) NOT NULL COMMENT '图书作者',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '图书状态',
  `log` tinyint(1) NOT NULL DEFAULT '0' COMMENT '图书借阅状态',
  `time` datetime NOT NULL COMMENT '图书添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

#
# Data for table "book"
#

INSERT INTO `book` VALUES (1,'西游记','哈哈哈','吴承恩',0,1,'2021-12-20 11:00:00'),(2,'红楼梦','红楼梦','曹雪芹',0,0,'2021-12-12 12:00:00'),(3,'水浒传','hhh','施耐庵',0,0,'2021-12-20 08:00:00'),(4,'三国演义','三国演义','罗贯中',0,0,'2021-12-20 15:21:23'),(5,'三国演义','三国演义','罗贯中',1,0,'2021-12-21 12:08:48');

#
# Structure for table "book_log"
#

DROP TABLE IF EXISTS `book_log`;
CREATE TABLE `book_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '借阅id',
  `uid` int(11) NOT NULL COMMENT '借阅的用户id',
  `bid` int(11) NOT NULL COMMENT '借阅的图书id',
  `timeStart` datetime NOT NULL COMMENT '借阅时间',
  `timeEnd` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '还书时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

#
# Data for table "book_log"
#

INSERT INTO `book_log` VALUES (1,1,1,'2021-12-12 11:00:00','0000-00-00 00:00:00'),(2,1,2,'2021-11-13 14:13:00','2021-12-12 12:00:00'),(3,4,3,'2021-12-12 13:00:00','2021-12-22 11:00:06'),(4,1,3,'2021-12-20 12:00:00','2021-12-21 14:18:54'),(5,4,3,'2021-12-21 14:19:35','2021-12-22 11:00:06'),(7,4,3,'2021-12-22 10:55:35','2021-12-22 11:00:06'),(8,4,3,'2021-12-22 11:04:22','2021-12-22 11:12:25'),(10,1,4,'2021-12-22 11:20:51','2021-12-22 11:21:03');

#
# Structure for table "firstitemsystem"
#

DROP TABLE IF EXISTS `firstitemsystem`;
CREATE TABLE `firstitemsystem` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `systemName` varchar(30) DEFAULT NULL,
  `systemCOde` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

#
# Data for table "firstitemsystem"
#

INSERT INTO `firstitemsystem` VALUES (1,'渠道整合平台系统','SYS-CIP'),(2,'开放平台系统','SYS-OPENAPI'),(3,'电子渠道系统','SYS-CHANEL'),(4,'建瓯市','jos'),(7,'芜湖','哈哈'),(8,'呀呀呀','来来来'),(9,'name','');

#
# Structure for table "firstitemsysteminfo"
#

DROP TABLE IF EXISTS `firstitemsysteminfo`;
CREATE TABLE `firstitemsysteminfo` (
  `wid` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(30) DEFAULT NULL,
  `chinese` varchar(30) DEFAULT NULL,
  `english` varchar(30) DEFAULT NULL,
  `fanTi` varchar(30) DEFAULT NULL,
  `InfoDate` varchar(30) DEFAULT NULL,
  `mid` int(11) NOT NULL,
  PRIMARY KEY (`wid`),
  KEY `fk_SystemInfo` (`mid`),
  CONSTRAINT `fk_SystemInfo` FOREIGN KEY (`mid`) REFERENCES `firstitemsystem` (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

#
# Data for table "firstitemsysteminfo"
#

INSERT INTO `firstitemsysteminfo` VALUES (1,'CIP000023','测试储存1','test1','繁体1','2020-1-1',1),(2,'CIP000024','测试储存2','test2','繁2体','2020-1-2',2),(3,'CIP000025','测试储存3','test3','繁3体','2020-1-3',3),(7,'CIP000029','芜湖芜湖','jos','建瓯市','43831',1),(12,'123','444','123','123','123',2),(13,'123','444','123','123','123',2),(14,'123','444','123','123','123',2),(15,'123','444','123','123','123',2),(16,'123','444','123','123','123',2),(17,'123','444','123','123','123',2),(18,'123','444','123','123','123',2),(19,'123','444','123','123','123',2),(20,'123','444','123','123','123',2),(21,'123','444','123','123','123',2),(22,'123','444','123','123','123',2);

#
# Structure for table "primarytype"
#

DROP TABLE IF EXISTS `primarytype`;
CREATE TABLE `primarytype` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '一级类型id',
  `name` varchar(30) NOT NULL COMMENT '一级类型名称',
  `time` datetime NOT NULL COMMENT '添加时间',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '一级类型是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

#
# Data for table "primarytype"
#

INSERT INTO `primarytype` VALUES (1,'内存数据库','2021-12-12 12:00:00',0),(2,'数据库','2021-12-12 13:12:00',0),(3,'信息中间件','2021-12-10 23:00:02',0),(4,'文件服务器','2021-10-13 13:03:33',0),(5,'应用资源','2021-12-02 08:14:11',0);

#
# Structure for table "redis"
#

DROP TABLE IF EXISTS `redis`;
CREATE TABLE `redis` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'redisid',
  `rid` int(11) NOT NULL COMMENT '资源id',
  `cluterAddress` varchar(100) NOT NULL COMMENT '集群主机地址',
  `maxJumps` int(11) NOT NULL COMMENT '最大跳转次数',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `timeout` int(11) NOT NULL COMMENT '超时时间',
  `maxThreadActivities` int(11) NOT NULL COMMENT '线程最大活动数',
  `maxThreadIdles` int(11) NOT NULL COMMENT '线程最大空闲数',
  `minThreadIdles` int(11) NOT NULL COMMENT '线程最小空闲数',
  `maxThreadWaits` int(11) NOT NULL COMMENT '线程最大等待数',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '配置是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

#
# Data for table "redis"
#

INSERT INTO `redis` VALUES (1,6,'127.0.0.1:6379',3,'63a9f0ea7bb98050796b649e85481845',323434,45665,4322,3232,3532,0);

#
# Structure for table "resource"
#

DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源id',
  `sid` int(11) NOT NULL COMMENT '二级类型id',
  `englishName` varchar(30) NOT NULL COMMENT '英文名称',
  `chineseName` varchar(30) NOT NULL COMMENT '中文名称',
  `description` text COMMENT '描述',
  `time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

#
# Data for table "resource"
#


#
# Structure for table "resources"
#

DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源id',
  `sid` int(11) NOT NULL COMMENT '二级类型id',
  `englishName` varchar(30) NOT NULL COMMENT '英文名称',
  `chineseName` varchar(30) NOT NULL COMMENT '中文名称',
  `description` text COMMENT '描述',
  `time` datetime NOT NULL COMMENT '添加时间',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '资源是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

#
# Data for table "resources"
#

INSERT INTO `resources` VALUES (1,15,'tomcat配置','tomcat配置','tomcat配置','2021-12-21 12:12:00',0),(2,14,'dubbo配置','dubbo配置','dubbo配置','2021-12-21 14:00:12',0),(3,4,'Mysql配置','Mysql配置','mysql配置','2021-12-21 15:13:14',0),(4,5,'tdsql配置','tdsql配置','tdsql配置','2021-12-21 15:14:12',0),(5,4,'ddd','ddd','ddd','2021-12-21 15:15:21',0),(6,10,'redis集群','redis集群','redis集群','2021-12-22 14:12:12',0),(7,16,'rocketMQ','rocketMQ','rocketMQ','2021-12-22 15:12:00',0);

#
# Structure for table "rocketmq"
#

DROP TABLE IF EXISTS `rocketmq`;
CREATE TABLE `rocketmq` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'RocketMQid',
  `rid` int(11) NOT NULL COMMENT '资源id',
  `nameServer` varchar(100) NOT NULL COMMENT '服务地址名称',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '配置是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

#
# Data for table "rocketmq"
#


#
# Structure for table "secondarytype"
#

DROP TABLE IF EXISTS `secondarytype`;
CREATE TABLE `secondarytype` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '二级类型id',
  `pid` int(11) NOT NULL COMMENT '一级类型id',
  `name` varchar(30) NOT NULL COMMENT '类型名称',
  `image` varchar(100) NOT NULL COMMENT '类型照片',
  `time` datetime NOT NULL COMMENT '添加时间',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '二级类型是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

#
# Data for table "secondarytype"
#

INSERT INTO `secondarytype` VALUES (1,1,'sqLite','/images/sqlite.png','2021-12-10 12:00:00',0),(2,1,'altibase','/images/altibase.png','2021-12-10 13:00:01',0),(3,1,'timesten','/images/timesten.png','2021-12-10 13:00:02',0),(4,2,'mysql','/images/mysql.png','2021-12-20 12:00:02',0),(5,2,'tdsql','/images/tdsql.png','2021-12-21 14:13:00',0),(6,2,'oracle','/images/oracle.png','2021-12-21 14:14:13',0),(7,2,'postgres11','/images/postgres11.png','2021-12-21 14:13:00',0),(8,3,'zeroMQ','/images/zeromq.png','2021-12-21 14:13:21',0),(9,3,'rabbitMQ','/images/rabbitmq.png','2021-12-21 14:14:13',0),(10,3,'redis','/images/redis.png','2021-12-21 15:14:00',0),(11,4,'FTP','/images/ftp.png','2021-12-21 15:15:13',0),(12,4,'NFS','/images/nfs.png','2021-12-21 15:20:00',0),(13,4,'samba','/images/samba.png','2021-12-21 15:21:22',0),(14,5,'dubbo','/images/dubbo.png','2021-12-21 15:32:34',0),(15,5,'tomcat','/images/tomcat.png','2021-12-21 15:45:32',0),(16,3,'rocketMQ','/images/rocketmq.png','2021-12-22 16:13:12',0);

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
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

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1,'root',NULL,'63a9f0ea7bb98050796b649e85481845',NULL,NULL,NULL,0,1),(4,'wang','sb','e08392bb89dedb8ed6fb298f8e729c15',NULL,NULL,NULL,0,1);
