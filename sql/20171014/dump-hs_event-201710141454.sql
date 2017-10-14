-- MySQL dump 10.16  Distrib 10.1.26-MariaDB, for Win32 (AMD64)
--
-- Host: localhost    Database: hs_event
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
-- Table structure for table `hs_event`
--

DROP TABLE IF EXISTS `hs_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hs_event` (
  `h_event_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '事件录入后生成的代理键,自增长',
  `create_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' COMMENT '系统创建时间',
  `update_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
  PRIMARY KEY (`h_event_id`),
  KEY `idx_create_ts` (`create_ts`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='乐谣事件记录表，乐谣创建事件时记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hs_event`
--

LOCK TABLES `hs_event` WRITE;
/*!40000 ALTER TABLE `hs_event` DISABLE KEYS */;
INSERT INTO `hs_event` VALUES (1,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(2,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(3,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(4,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(5,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(6,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(7,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(8,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(9,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(10,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(11,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(12,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(13,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(14,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(15,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(16,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(17,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(18,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(19,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(20,'2017-09-15 14:55:00','2017-10-12 07:23:30');
/*!40000 ALTER TABLE `hs_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_event_active`
--

DROP TABLE IF EXISTS `s_event_active`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_event_active` (
  `s_event_active_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '事件激活相关记录序列代理键',
  `h_event_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '事件录入后生成的代理键',
  `s_event_active_ind` int(20) NOT NULL DEFAULT '0' COMMENT '事件激活状态',
  `create_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' COMMENT '系统创建时间',
  `update_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
  PRIMARY KEY (`s_event_active_id`),
  KEY `idx_h_event_id` (`h_event_id`),
  KEY `idx_create_ts` (`create_ts`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='事件记录表，乐谣创建事件时记录，写入默认值';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_event_active`
--

LOCK TABLES `s_event_active` WRITE;
/*!40000 ALTER TABLE `s_event_active` DISABLE KEYS */;
INSERT INTO `s_event_active` VALUES (1,1,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(2,2,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(3,3,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(4,4,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(5,5,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(6,6,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(7,7,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(8,8,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(9,9,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(10,10,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(11,11,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(12,12,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(13,13,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(14,14,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(15,15,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(16,16,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(17,17,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(18,0,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(19,19,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(20,20,0,'2017-09-15 14:55:00','2017-10-12 07:23:30');
/*!40000 ALTER TABLE `s_event_active` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_event_banner`
--

DROP TABLE IF EXISTS `s_event_banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_event_banner` (
  `s_event_banner_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '事件banner页信息相关记录序列代理键',
  `h_event_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '事件录入后生成的代理键',
  `s_event_banner_position_cd` int(20) NOT NULL DEFAULT '0' COMMENT '事件banner页位置编码',
  `s_event_banner_active_ind` int(20) NOT NULL DEFAULT '0' COMMENT '事件banner页激活状态',
  `create_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' COMMENT '系统创建时间',
  `update_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
  PRIMARY KEY (`s_event_banner_id`),
  KEY `idx_h_event_id` (`h_event_id`),
  KEY `idx_create_ts` (`create_ts`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='事件banner页信息表，乐谣创建事件时记录，后台管理可更新，存放事件web view在banner上的位置信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_event_banner`
--

LOCK TABLES `s_event_banner` WRITE;
/*!40000 ALTER TABLE `s_event_banner` DISABLE KEYS */;
INSERT INTO `s_event_banner` VALUES (1,1,1,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(2,2,2,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(3,3,3,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(4,4,4,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(5,5,5,0,'2017-09-15 14:55:00','2017-10-13 11:30:27');
/*!40000 ALTER TABLE `s_event_banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_event_category`
--

DROP TABLE IF EXISTS `s_event_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_event_category` (
  `s_event_category_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '事件种类相关记录序列代理键',
  `h_event_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '事件录入后生成的代理键',
  `s_event_category_cd` int(20) NOT NULL DEFAULT '0' COMMENT '事件种类编码',
  `create_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' COMMENT '系统创建时间',
  `update_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
  PRIMARY KEY (`s_event_category_id`),
  KEY `idx_h_event_id` (`h_event_id`),
  KEY `idx_create_ts` (`create_ts`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='事件种类表，乐谣创建事件时记录，存放事件种类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_event_category`
--

LOCK TABLES `s_event_category` WRITE;
/*!40000 ALTER TABLE `s_event_category` DISABLE KEYS */;
INSERT INTO `s_event_category` VALUES (1,1,1,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(2,2,1,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(3,3,1,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(4,4,1,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(5,5,1,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(6,6,1,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(7,7,2,'2017-09-15 14:55:00','2017-10-12 02:51:36'),(8,8,2,'2017-09-15 14:55:00','2017-10-12 02:51:36'),(9,9,2,'2017-09-15 14:55:00','2017-10-12 02:51:36'),(10,10,2,'2017-09-15 14:55:00','2017-10-12 02:51:36'),(11,11,2,'2017-09-15 14:55:00','2017-10-12 02:51:36'),(12,12,2,'2017-09-15 14:55:00','2017-10-12 02:51:36'),(13,13,3,'2017-09-15 14:55:00','2017-10-12 02:51:36'),(14,14,3,'2017-09-15 14:55:00','2017-10-12 02:51:36'),(15,15,3,'2017-09-15 14:55:00','2017-10-12 02:51:36'),(16,16,3,'2017-09-15 14:55:00','2017-10-12 02:51:36'),(17,17,3,'2017-09-15 14:55:00','2017-10-12 02:51:36'),(18,18,3,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(19,19,4,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(20,20,4,'2017-09-15 14:55:00','2017-10-12 07:23:30');
/*!40000 ALTER TABLE `s_event_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_event_content`
--

DROP TABLE IF EXISTS `s_event_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_event_content` (
  `s_event_content_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '事件类型相关记录序列代理键',
  `h_event_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '事件录入后生成的代理键',
  `s_event_title_url` varchar(255) NOT NULL DEFAULT '?' COMMENT '事件标题url',
  `s_event_content_url` varchar(255) NOT NULL DEFAULT '?' COMMENT '事件内容url',
  `create_ts` timestamp NOT NULL DEFAULT '1999-12-31 16:00:00' COMMENT '系统创建时间',
  `update_ts` timestamp NOT NULL DEFAULT '1999-12-31 16:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
  PRIMARY KEY (`s_event_content_id`),
  KEY `idx_h_event_id` (`h_event_id`),
  KEY `idx_create_ts` (`create_ts`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='事件内容表，乐谣创建事件时记录，存放事件web view的标题和内容url地址';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_event_content`
--

LOCK TABLES `s_event_content` WRITE;
/*!40000 ALTER TABLE `s_event_content` DISABLE KEYS */;
INSERT INTO `s_event_content` VALUES (1,1,'image/1.jpg','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','2017-09-15 14:55:00','2017-09-21 13:46:24'),(2,2,'image/2.jpg','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','2017-09-15 14:55:00','2017-09-21 13:46:24'),(3,3,'image/3.jpg','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','2017-09-15 14:55:00','2017-09-21 13:46:24'),(4,4,'image/4.jpg','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','2017-09-15 14:55:00','2017-09-21 13:46:24'),(5,5,'image/5.jpg','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','2017-09-15 14:55:00','2017-09-21 13:46:24'),(6,6,'image/6.jpg','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','2017-09-15 14:55:00','2017-09-21 13:46:24'),(7,7,'vedio/1.mp4','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','2017-09-15 14:55:00','2017-09-21 13:46:24'),(8,8,'vedio/2.mp4','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','2017-09-15 14:55:00','2017-09-21 13:46:24'),(9,9,'vedio/3.mp4','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','2017-09-15 14:55:00','2017-09-21 13:46:24'),(10,10,'vedio/4.mp4','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','2017-09-15 14:55:00','2017-09-21 13:46:24'),(11,11,'vedio/5.mp4','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','2017-09-15 14:55:00','2017-09-21 13:46:24'),(12,12,'vedio/6.mp4','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','2017-09-15 14:55:00','2017-09-21 13:46:24'),(13,13,'audio/1.mp3','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','2017-09-15 14:55:00','2017-09-21 13:46:24'),(14,14,'audio/2.mp3','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','2017-09-15 14:55:00','2017-09-21 13:46:24'),(15,15,'audio/3.mp3','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','2017-09-15 14:55:00','2017-09-21 13:46:24'),(16,16,'audio/4.mp3','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','2017-09-15 14:55:00','2017-09-21 13:46:24'),(17,17,'audio/5.mp3','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','2017-09-15 14:55:00','2017-09-21 13:46:24'),(18,18,'audio/6.mp3','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','2017-09-15 15:28:19','2017-09-21 13:46:24'),(19,19,'image/1.jpg','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','2017-09-15 14:55:00','2017-09-21 13:46:24'),(20,20,'static/image/2.jpg','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','2017-09-15 14:55:00','2017-10-12 07:23:30');
/*!40000 ALTER TABLE `s_event_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_event_recom_1`
--

DROP TABLE IF EXISTS `s_event_recom_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_event_recom_1` (
  `s_event_recom_1_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '事件banner页信息相关记录序列代理键',
  `h_event_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '事件录入后生成的代理键',
  `s_event_recom_position_cd` int(20) NOT NULL DEFAULT '0' COMMENT '事件推荐1号位位置编码',
  `s_event_recom_active_ind` int(20) NOT NULL DEFAULT '0' COMMENT '事件推荐1号位激活状态',
  `create_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' COMMENT '系统创建时间',
  `update_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
  PRIMARY KEY (`s_event_recom_1_id`),
  KEY `idx_h_event_id` (`h_event_id`),
  KEY `idx_create_ts` (`create_ts`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='事件推荐1号位信息表，乐谣创建事件时记录，后台管理可更新，存放事件web view在推荐菜单1上的位置信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_event_recom_1`
--

LOCK TABLES `s_event_recom_1` WRITE;
/*!40000 ALTER TABLE `s_event_recom_1` DISABLE KEYS */;
INSERT INTO `s_event_recom_1` VALUES (1,13,1,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(2,14,2,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(3,15,3,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(4,16,4,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(5,17,5,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(6,18,6,0,'2017-09-15 14:55:00','2017-09-15 14:55:00');
/*!40000 ALTER TABLE `s_event_recom_1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_event_recom_2`
--

DROP TABLE IF EXISTS `s_event_recom_2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_event_recom_2` (
  `s_event_recom_2_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '事件banner页信息相关记录序列代理键',
  `h_event_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '事件录入后生成的代理键',
  `s_event_recom_position_cd` int(20) NOT NULL DEFAULT '0' COMMENT '事件推荐2号位位置编码',
  `s_event_recom_active_ind` int(20) NOT NULL DEFAULT '0' COMMENT '事件推荐2号位激活状态',
  `create_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' COMMENT '系统创建时间',
  `update_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
  PRIMARY KEY (`s_event_recom_2_id`),
  KEY `idx_h_event_id` (`h_event_id`),
  KEY `idx_create_ts` (`create_ts`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='事件推荐2号位信息表，乐谣创建事件时记录，后台管理可更新，存放事件web view在推荐菜单2上的位置信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_event_recom_2`
--

LOCK TABLES `s_event_recom_2` WRITE;
/*!40000 ALTER TABLE `s_event_recom_2` DISABLE KEYS */;
INSERT INTO `s_event_recom_2` VALUES (1,7,1,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(2,8,2,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(3,9,3,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(4,10,4,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(5,11,5,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(6,11,6,0,'2017-09-15 14:55:00','2017-09-15 14:55:00');
/*!40000 ALTER TABLE `s_event_recom_2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_event_recom_3`
--

DROP TABLE IF EXISTS `s_event_recom_3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_event_recom_3` (
  `s_event_recom_3_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '事件banner页信息相关记录序列代理键',
  `h_event_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '事件录入后生成的代理键',
  `s_event_recom_position_cd` int(20) NOT NULL DEFAULT '0' COMMENT '事件推荐3号位位置编码',
  `s_event_recom_active_ind` int(20) NOT NULL DEFAULT '0' COMMENT '事件推荐3号位激活状态',
  `create_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' COMMENT '系统创建时间',
  `update_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
  PRIMARY KEY (`s_event_recom_3_id`),
  KEY `idx_h_event_id` (`h_event_id`),
  KEY `idx_create_ts` (`create_ts`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='事件推荐3号位信息表，乐谣创建事件时记录，后台管理可更新，存放事件web view在推荐菜单3上的位置信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_event_recom_3`
--

LOCK TABLES `s_event_recom_3` WRITE;
/*!40000 ALTER TABLE `s_event_recom_3` DISABLE KEYS */;
INSERT INTO `s_event_recom_3` VALUES (1,1,1,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(2,2,2,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(3,3,3,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(4,4,4,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(5,5,5,0,'2017-09-15 14:55:00','2017-09-15 14:55:00'),(6,6,6,0,'2017-09-15 14:55:00','2017-09-15 14:55:00');
/*!40000 ALTER TABLE `s_event_recom_3` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_event_sub_content_1`
--

DROP TABLE IF EXISTS `s_event_sub_content_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_event_sub_content_1` (
  `s_event_sub_content_1_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '事件子内容1相关记录序列代理键',
  `h_event_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '事件录入后生成的代理键',
  `s_event_sub_content_1_url` varchar(255) NOT NULL DEFAULT '?' COMMENT '事件子内容1相关url',
  `create_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' COMMENT '系统创建时间',
  `update_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
  PRIMARY KEY (`s_event_sub_content_1_id`),
  KEY `idx_h_event_id` (`h_event_id`),
  KEY `idx_create_ts` (`create_ts`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COMMENT='事件子内容1表，乐谣创建事件时记录，存放事件的子内容(音频链接)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_event_sub_content_1`
--

LOCK TABLES `s_event_sub_content_1` WRITE;
/*!40000 ALTER TABLE `s_event_sub_content_1` DISABLE KEYS */;
INSERT INTO `s_event_sub_content_1` VALUES (1,19,'audio/1.mp3','2017-09-15 14:55:00','2017-09-15 14:55:00'),(2,19,'audio/2.mp3','2017-09-15 14:55:00','2017-09-15 14:55:00'),(3,19,'audio/3.mp3','2017-09-15 14:55:00','2017-09-15 14:55:00'),(10,20,'audio/1.mp3','1999-12-31 16:00:00','2017-10-12 07:23:30'),(11,20,'audio/2.mp3','1999-12-31 16:00:00','2017-10-12 07:23:30'),(12,20,'audio/3.mp3','1999-12-31 16:00:00','2017-10-12 07:23:30');
/*!40000 ALTER TABLE `s_event_sub_content_1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_event_sub_content_2`
--

DROP TABLE IF EXISTS `s_event_sub_content_2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_event_sub_content_2` (
  `s_event_sub_content_2_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '事件子内容2相关记录序列代理键',
  `h_event_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '事件录入后生成的代理键',
  `s_event_sub_content_2_str` varchar(255) NOT NULL DEFAULT '?' COMMENT '事件子内容2相关内容描述',
  `create_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' COMMENT '系统创建时间',
  `update_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
  PRIMARY KEY (`s_event_sub_content_2_id`),
  KEY `idx_h_event_id` (`h_event_id`),
  KEY `idx_create_ts` (`create_ts`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COMMENT='事件子内容2表，乐谣创建事件时记录，存放事件的子内容描述';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_event_sub_content_2`
--

LOCK TABLES `s_event_sub_content_2` WRITE;
/*!40000 ALTER TABLE `s_event_sub_content_2` DISABLE KEYS */;
INSERT INTO `s_event_sub_content_2` VALUES (1,19,'{\"lyricist\":\"peter\",\"composer\":\"tom\",\"singer\":\"linda\"}','2017-09-15 14:55:00','2017-10-10 08:48:01'),(2,19,'{\"lyricist\":\"peter\",\"composer\":\"tom\",\"singer\":\"linda\"}','2017-09-15 14:55:00','2017-10-10 08:48:01'),(3,19,'{\"lyricist\":\"peter\",\"composer\":\"tom\",\"singer\":\"linda\"}','2017-09-15 14:55:00','2017-10-10 08:48:01'),(10,20,'{\"lyricist\":\"jj\",\"composer\":\"tom\",\"singer\":\"linda\"}','1999-12-31 16:00:00','2017-10-12 07:23:30'),(11,20,'{\"lyricist\":\"peter\",\"composer\":\"tom\",\"singer\":\"linda\"}','1999-12-31 16:00:00','2017-10-12 07:23:30'),(12,20,'{\"lyricist\":\"peter\",\"composer\":\"tom\",\"singer\":\"linda\"}','1999-12-31 16:00:00','2017-10-12 07:23:30');
/*!40000 ALTER TABLE `s_event_sub_content_2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_event_type`
--

DROP TABLE IF EXISTS `s_event_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_event_type` (
  `S_EVENT_TYPE_ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '事件类型相关记录序列代理键',
  `H_EVENT_ID` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '事件录入后生成的代理键',
  `S_EVENT_TYPE_CD` int(20) NOT NULL DEFAULT '0' COMMENT '事件类型编码',
  `create_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' COMMENT '系统创建时间',
  `update_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
  PRIMARY KEY (`S_EVENT_TYPE_ID`),
  KEY `idx_S_EVENT_TYPE_ID` (`H_EVENT_ID`),
  KEY `idx_create_ts` (`create_ts`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='事件类型表，乐谣创建事件时记录，存放事件类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_event_type`
--

LOCK TABLES `s_event_type` WRITE;
/*!40000 ALTER TABLE `s_event_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `s_event_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_event_page`
--

DROP TABLE IF EXISTS `t_event_page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_event_page` (
  `h_event_id` bigint(20) unsigned NOT NULL COMMENT '事件banner页信息相关记录序列代理键',
  `s_event_category_cd` int(20) NOT NULL DEFAULT '0' COMMENT '事件种类编码',
  `r_event_category_desc` varchar(500) NOT NULL DEFAULT '?' COMMENT '事件种类定义',
  `s_event_type_cd` int(20) NOT NULL DEFAULT '0' COMMENT '事件类型编码',
  `r_event_type_desc` varchar(500) NOT NULL DEFAULT '?' COMMENT '事件类型定义',
  `s_event_title_url` varchar(255) NOT NULL DEFAULT '?' COMMENT '事件标题url',
  `s_event_content_url` varchar(255) NOT NULL DEFAULT '?' COMMENT '事件内容url',
  `s_event_sub_content_1_url` varchar(255) NOT NULL DEFAULT '?' COMMENT '事件子内容1相关url',
  `s_event_sub_content_2_str` varchar(255) NOT NULL DEFAULT '?' COMMENT '事件子内容2相关内容描述',
  `s_event_search_content_txt` text NOT NULL COMMENT '事件搜索内容，存放搜索相关关键字集合',
  `s_event_active_ind` int(20) NOT NULL DEFAULT '0' COMMENT '事件激活状态',
  `create_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' COMMENT '系统创建时间',
  `update_ts` timestamp NULL DEFAULT '1999-12-31 16:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
  PRIMARY KEY (`h_event_id`),
  KEY `idx_create_ts` (`create_ts`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='乐谣创建以及更新事件时更新，提供搜索引擎信息入口，将所有事件相关关键词全部存放于字段内s_event_search_content_txt';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_event_page`
--

LOCK TABLES `t_event_page` WRITE;
/*!40000 ALTER TABLE `t_event_page` DISABLE KEYS */;
INSERT INTO `t_event_page` VALUES (1,1,'d1',1,'image','static/image/1.jpg','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','','?','1',0,'2017-09-15 14:55:00','2017-09-21 13:46:24'),(2,1,'d1',1,'image','static/image/2.jpg','http://mp.weixin.qq.com/s/VABeuW3CNwcU_kGWEbufRA','','?','2',0,'2017-09-15 14:55:00','2017-09-21 13:46:24'),(3,1,'d1',1,'image','static/image/3.jpg','http://mp.weixin.qq.com/s/YxYsMqTQOHn9j0yhS0XqGQ','','?','3',0,'2017-09-15 14:55:00','2017-10-12 02:46:29'),(4,1,'d1',1,'image','static/image/4.jpg','http://mp.weixin.qq.com/s/0i_4wHBcs-bJS8eCoPuSZg','','?','4',0,'2017-09-15 14:55:00','2017-10-12 02:46:29'),(5,1,'d1',1,'image','static/image/5.jpg','http://mp.weixin.qq.com/s/w6qAt6MAc2cSXJhQgSMMQw','','?','5',0,'2017-09-15 14:55:00','2017-10-12 02:46:29'),(6,1,'d1',1,'image','static/image/6.jpg','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','','?','6',0,'2017-09-15 14:55:00','2017-10-12 02:46:29'),(7,1,'d1',3,'vedio','static/vedio/1.mp4','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','','?','7',0,'2017-09-15 14:55:00','2017-10-12 02:46:29'),(8,1,'d1',3,'vedio','static/vedio/2.mp4','http://mp.weixin.qq.com/s/VABeuW3CNwcU_kGWEbufRA','','?','8',0,'2017-09-15 14:55:00','2017-10-12 02:46:29'),(9,1,'d1',3,'vedio','static/vedio/3.mp4','http://mp.weixin.qq.com/s/YxYsMqTQOHn9j0yhS0XqGQ','','?','9',0,'2017-09-15 14:55:00','2017-10-12 02:46:29'),(10,1,'d1',3,'vedio','static/vedio/4.mp4','http://mp.weixin.qq.com/s/0i_4wHBcs-bJS8eCoPuSZg','','?','10',0,'2017-09-15 14:55:00','2017-10-12 02:46:30'),(11,1,'d1',3,'vedio','static/vedio/5.mp4','http://mp.weixin.qq.com/s/w6qAt6MAc2cSXJhQgSMMQw','','?','11',0,'2017-09-15 14:55:00','2017-10-12 02:46:30'),(12,1,'d1',3,'vedio','static/vedio/6.mp4','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','','?','12',0,'2017-09-15 14:55:00','2017-10-12 02:46:30'),(13,1,'d1',2,'audio','static/audio/1.mp3','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','','?','13',0,'2017-09-15 14:55:00','2017-10-12 02:46:30'),(14,1,'d1',2,'audio','static/audio/2.mp3','http://mp.weixin.qq.com/s/VABeuW3CNwcU_kGWEbufRA','','?','14',0,'2017-09-15 14:55:00','2017-10-12 02:46:30'),(15,1,'d1',2,'audio','static/audio/3.mp3','http://mp.weixin.qq.com/s/YxYsMqTQOHn9j0yhS0XqGQ','','?','15',0,'2017-09-15 14:55:00','2017-10-12 02:46:30'),(16,1,'d1',2,'audio','static/audio/4.mp3','http://mp.weixin.qq.com/s/0i_4wHBcs-bJS8eCoPuSZg','','?','16',0,'2017-09-15 14:55:00','2017-10-12 02:46:30'),(17,1,'d1',2,'audio','static/audio/5.mp3','http://mp.weixin.qq.com/s/w6qAt6MAc2cSXJhQgSMMQw','','?','17',0,'2017-09-15 14:55:00','2017-10-12 02:46:30'),(18,1,'d1',2,'audio','static/audio/6.mp3','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','','?','18',0,'2017-09-15 15:28:19','2017-10-12 02:46:30'),(19,4,'d4',1,'image','static/image/1.jpg','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','audio/1.mp3','{\"lyricist\":\"peter\",\"composer\":\"tom\",\"singer\":\"linda\"}','{\"url\":\"audio/a.mp3\",\"lyricist\":\"peter\",\"composer\":\"tom\",\"singer\":\"linda\"}',0,'2017-09-15 14:55:00','2017-10-10 08:47:41'),(20,4,'Album',1,'Image','static/image/2.jpg','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','com.leyao.app_service.entity.hs_event.SEventSubContent1@5db0eeee','com.leyao.app_service.entity.hs_event.SEventSubContent2@6441a4fd','?',0,'2017-09-15 14:55:00','2017-10-12 07:23:30'),(21,2,'d1',1,'image','static/image/1.jpg','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','','?','1',0,'2017-09-15 14:55:00','2017-09-21 13:46:24'),(22,2,'d1',1,'image','static/image/2.jpg','http://mp.weixin.qq.com/s/VABeuW3CNwcU_kGWEbufRA','','?','2',0,'2017-09-15 14:55:00','2017-09-21 13:46:24'),(23,2,'d1',1,'image','static/image/3.jpg','http://mp.weixin.qq.com/s/YxYsMqTQOHn9j0yhS0XqGQ','','?','3',0,'2017-09-15 14:55:00','2017-10-12 02:46:29'),(24,2,'d1',1,'image','static/image/4.jpg','http://mp.weixin.qq.com/s/0i_4wHBcs-bJS8eCoPuSZg','','?','4',0,'2017-09-15 14:55:00','2017-10-12 02:46:29'),(25,2,'d1',1,'image','static/image/5.jpg','http://mp.weixin.qq.com/s/w6qAt6MAc2cSXJhQgSMMQw','','?','5',0,'2017-09-15 14:55:00','2017-10-12 02:46:29'),(26,2,'d1',1,'image','static/image/6.jpg','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','','?','6',0,'2017-09-15 14:55:00','2017-10-12 02:46:29'),(27,3,'d1',1,'image','static/image/1.jpg','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','','?','1',0,'2017-09-15 14:55:00','2017-09-21 13:46:24'),(28,3,'d1',1,'image','static/image/2.jpg','http://mp.weixin.qq.com/s/VABeuW3CNwcU_kGWEbufRA','','?','2',0,'2017-09-15 14:55:00','2017-09-21 13:46:24'),(29,3,'d1',1,'image','static/image/3.jpg','http://mp.weixin.qq.com/s/YxYsMqTQOHn9j0yhS0XqGQ','','?','3',0,'2017-09-15 14:55:00','2017-10-12 02:46:29'),(30,3,'d1',1,'image','static/image/4.jpg','http://mp.weixin.qq.com/s/0i_4wHBcs-bJS8eCoPuSZg','','?','4',0,'2017-09-15 14:55:00','2017-10-12 02:46:29'),(31,3,'d1',1,'image','static/image/5.jpg','http://mp.weixin.qq.com/s/w6qAt6MAc2cSXJhQgSMMQw','','?','5',0,'2017-09-15 14:55:00','2017-10-12 02:46:29'),(32,3,'d1',1,'image','static/image/6.jpg','http://mp.weixin.qq.com/s/pczRcFhP97l-u8u_SpNgpw','','?','6',0,'2017-09-15 14:55:00','2017-10-12 02:46:29');
/*!40000 ALTER TABLE `t_event_page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'hs_event'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-14 14:54:50