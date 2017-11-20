-- MySQL dump 10.16  Distrib 10.1.26-MariaDB, for Win32 (AMD64)
--
-- Host: localhost    Database: r_dim
-- ------------------------------------------------------
-- Server version	10.1.26-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `r_event_category`
--

DROP TABLE IF EXISTS `r_event_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `r_event_category` (
  `r_event_category_cd` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '事件种类编码',
  `r_event_category_desc` varchar(500) NOT NULL DEFAULT '?' COMMENT '事件种类定义',
  `create_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' COMMENT '系统创建时间',
  `update_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
  PRIMARY KEY (`r_event_category_cd`),
  KEY `idx_create_ts` (`create_ts`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='事件种类关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `r_event_category`
--

LOCK TABLES `r_event_category` WRITE;
/*!40000 ALTER TABLE `r_event_category` DISABLE KEYS */;
INSERT INTO `r_event_category` VALUES (0,'未定义','1999-12-31 16:00:00','2017-10-16 09:28:11'),(1,'乐谣活动','1999-12-31 16:00:00','1999-12-31 16:00:00'),(2,'音乐教学','1999-12-31 16:00:00','1999-12-31 16:00:00'),(3,'艺人分享','1999-12-31 16:00:00','1999-12-31 16:00:00'),(4,'音乐专辑','1999-12-31 16:00:00','1999-12-31 16:00:00');
/*!40000 ALTER TABLE `r_event_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `r_event_position`
--

DROP TABLE IF EXISTS `r_event_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `r_event_position` (
  `r_event_position_cd` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '事件位置编码',
  `r_event_position_desc` varchar(500) NOT NULL DEFAULT '?' COMMENT '事件位置定义',
  `create_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' COMMENT '系统创建时间',
  `update_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
  PRIMARY KEY (`r_event_position_cd`),
  KEY `idx_create_ts` (`create_ts`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='事件位置关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `r_event_position`
--

LOCK TABLES `r_event_position` WRITE;
/*!40000 ALTER TABLE `r_event_position` DISABLE KEYS */;
INSERT INTO `r_event_position` VALUES (1,'1','1999-12-31 16:00:00','1999-12-31 16:00:00'),(2,'2','1999-12-31 16:00:00','1999-12-31 16:00:00'),(3,'3','1999-12-31 16:00:00','1999-12-31 16:00:00'),(4,'4','1999-12-31 16:00:00','1999-12-31 16:00:00'),(5,'5','1999-12-31 16:00:00','1999-12-31 16:00:00'),(6,'6','1999-12-31 16:00:00','1999-12-31 16:00:00');
/*!40000 ALTER TABLE `r_event_position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `r_event_type`
--

DROP TABLE IF EXISTS `r_event_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `r_event_type` (
  `r_event_type_cd` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '事件类型编码',
  `r_event_type_desc` varchar(500) NOT NULL DEFAULT '?' COMMENT '事件类型定义',
  `create_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' COMMENT '系统创建时间',
  `update_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
  PRIMARY KEY (`r_event_type_cd`),
  KEY `idx_create_ts` (`create_ts`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='事件类型关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `r_event_type`
--

LOCK TABLES `r_event_type` WRITE;
/*!40000 ALTER TABLE `r_event_type` DISABLE KEYS */;
INSERT INTO `r_event_type` VALUES (0,'未定义','1999-12-31 16:00:00','2017-10-16 09:30:52'),(1,'图片','1999-12-31 16:00:00','1999-12-31 16:00:00'),(2,'音频','1999-12-31 16:00:00','1999-12-31 16:00:00'),(3,'视频','1999-12-31 16:00:00','1999-12-31 16:00:00');
/*!40000 ALTER TABLE `r_event_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `r_message_category`
--

DROP TABLE IF EXISTS `r_message_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `r_message_category` (
  `r_message_category_cd` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '消息种类编码',
  `r_message_category_desc` varchar(500) NOT NULL DEFAULT '?' COMMENT '消息种类定义',
  `create_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' COMMENT '系统创建时间',
  `update_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
  PRIMARY KEY (`r_message_category_cd`),
  KEY `idx_create_ts` (`create_ts`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='消息种类关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `r_message_category`
--

LOCK TABLES `r_message_category` WRITE;
/*!40000 ALTER TABLE `r_message_category` DISABLE KEYS */;
INSERT INTO `r_message_category` VALUES (1,'系统消息','1999-12-31 16:00:00','1999-12-31 16:00:00');
/*!40000 ALTER TABLE `r_message_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `r_user_gender`
--

DROP TABLE IF EXISTS `r_user_gender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `r_user_gender` (
  `r_user_gender_cd` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户性别编码',
  `r_user_gender_desc` varchar(500) NOT NULL DEFAULT '?' COMMENT '用户性别定义',
  `create_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' COMMENT '系统创建时间',
  `update_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
  PRIMARY KEY (`r_user_gender_cd`),
  KEY `idx_create_ts` (`create_ts`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='用户性别关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `r_user_gender`
--

LOCK TABLES `r_user_gender` WRITE;
/*!40000 ALTER TABLE `r_user_gender` DISABLE KEYS */;
INSERT INTO `r_user_gender` VALUES (0,'未定义','1999-12-31 16:00:00','2017-10-16 09:32:09'),(1,'男','1999-12-31 16:00:00','1999-12-31 16:00:00'),(2,'女','1999-12-31 16:00:00','1999-12-31 16:00:00');
/*!40000 ALTER TABLE `r_user_gender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'r_dim'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-05  9:22:46
