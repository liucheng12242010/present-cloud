-- --------------------------------------------------------
-- 主机:                           129.204.242.132
-- 服务器版本:                        10.3.7-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 presentcloud 的数据库结构
CREATE DATABASE IF NOT EXISTS `presentcloud` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `presentcloud`;

-- 导出  表 presentcloud.classes 结构
CREATE TABLE IF NOT EXISTS `classes` (
  `CLASSID` varchar(64) DEFAULT NULL,
  `CLASSNAME` varchar(64) DEFAULT NULL,
  `TEACHER` varchar(64) DEFAULT NULL,
  `CLASSBEGINDATE` date DEFAULT NULL,
  `CLASSENDDATE` date DEFAULT NULL,
  `CREATETIME` date DEFAULT NULL,
  `UPDATETIME` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  presentcloud.classes 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` (`CLASSID`, `CLASSNAME`, `TEACHER`, `CLASSBEGINDATE`, `CLASSENDDATE`, `CREATETIME`, `UPDATETIME`) VALUES
	('001', '工程实践', '池老标', '2020-03-01', '2020-06-05', NULL, NULL),
	('002', '工程训练', '池老标', '2019-06-05', '2019-10-30', NULL, NULL);
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;

-- 导出  表 presentcloud.dict 结构
CREATE TABLE IF NOT EXISTS `dict` (
  `dicttype` varchar(64) DEFAULT NULL,
  `dictname` varchar(64) DEFAULT NULL,
  `dictorder` varchar(64) DEFAULT NULL,
  `dictstatus` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  presentcloud.dict 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `dict` DISABLE KEYS */;
INSERT INTO `dict` (`dicttype`, `dictname`, `dictorder`, `dictstatus`) VALUES
	('用户角色', '学生', '001', 'T'),
	('用户角色', '老师', '002', 'T'),
	('用户角色', '助教', '003', 'T'),
	('学校分类', '福州大学', '001', 'T'),
	('学校分类', '福州师范大学', '002', 'T'),
	('学院分类', '数计学院', '001', 'T'),
	('学院分类', '软件学院', '002', 'T'),
	('学院分类', '艺术学院', '003', 'T'),
	('课程状态', '注册', '001', 'T'),
	('课程状态', '签到中', '002', 'T'),
	('课程状态', '结课', '003', 'T');
/*!40000 ALTER TABLE `dict` ENABLE KEYS */;

-- 导出  表 presentcloud.organization 结构
CREATE TABLE IF NOT EXISTS `organization` (
  `ORGID` varchar(64) DEFAULT NULL,
  `SCHOOL` varchar(64) DEFAULT NULL,
  `COLLEGE` varchar(64) DEFAULT NULL,
  `MAJOR` varchar(64) DEFAULT NULL,
  `GRADE` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  presentcloud.organization 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
INSERT INTO `organization` (`ORGID`, `SCHOOL`, `COLLEGE`, `MAJOR`, `GRADE`) VALUES
	('1001', '福州大学', '数计学院', '计算机技术', '2017'),
	('1002', '福州大学', '数计学院', '数学专业', '2018'),
	('2001', '福州大学', '软件学院', '软件工程', '2019'),
	('3003', '福州师范大学', '艺术学院', '书法专业', '2019');
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;

-- 导出  表 presentcloud.sysparam 结构
CREATE TABLE IF NOT EXISTS `sysparam` (
  `PKID` varchar(64) DEFAULT NULL,
  `EXPERIENCE` varchar(64) DEFAULT NULL,
  `DISTANCE` varchar(64) DEFAULT NULL,
  `DURATION` varchar(64) DEFAULT NULL,
  `ADMINID` varchar(64) DEFAULT NULL,
  `ADMINPASSWORD` varchar(64) DEFAULT NULL,
  `CREATETIME` date DEFAULT NULL,
  `UPDATETIME` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  presentcloud.sysparam 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `sysparam` DISABLE KEYS */;
INSERT INTO `sysparam` (`PKID`, `EXPERIENCE`, `DISTANCE`, `DURATION`, `ADMINID`, `ADMINPASSWORD`, `CREATETIME`, `UPDATETIME`) VALUES
	('202006041443', '10', '100', '60', 'ADMIN', 'ADMINPASSWORD', '2020-06-04', '2020-06-04');
/*!40000 ALTER TABLE `sysparam` ENABLE KEYS */;

-- 导出  表 presentcloud.tokentable 结构
CREATE TABLE IF NOT EXISTS `tokentable` (
  `userid` varchar(64) DEFAULT NULL,
  `token` varchar(286) DEFAULT NULL,
  `createTime` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  presentcloud.tokentable 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tokentable` DISABLE KEYS */;
/*!40000 ALTER TABLE `tokentable` ENABLE KEYS */;

-- 导出  表 presentcloud.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `USERID` varchar(64) DEFAULT NULL,
  `USERNAME` varchar(64) DEFAULT NULL,
  `PASSWORD` varchar(64) DEFAULT NULL,
  `ROLE` varchar(64) DEFAULT NULL,
  `REMARKS` varchar(64) DEFAULT NULL,
  `CREATETIME` date DEFAULT NULL,
  `UPDATETIME` date DEFAULT NULL,
  `SCHOOL` varchar(64) DEFAULT NULL,
  `GENDER` varchar(64) DEFAULT NULL,
  `COLLEGE` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  presentcloud.user 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`USERID`, `USERNAME`, `PASSWORD`, `ROLE`, `REMARKS`, `CREATETIME`, `UPDATETIME`, `SCHOOL`, `GENDER`, `COLLEGE`) VALUES
	('170325002', '陈思杰', '123456', '0', '123', NULL, NULL, '福州大学', '男', '数计学院'),
	('TEST', 'TEST', '123456', '1', '123', NULL, NULL, '福州中学', '女', '艺术学院');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
