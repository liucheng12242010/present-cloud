
CREATE DATABASE IF NOT EXISTS `db_t_daoyun` DEFAULT CHARACTER SET utf8 ;

USE `db_t_daoyun`;

/*Table structure for table `t_department` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(20) DEFAULT NULL,
  `roleDesc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Data for the table `t_department` */

insert  into `t_role`(`roleId`,`roleName`,`roleDesc`) values (1,'教师','负责课程教学工作'),(2,'学生','管理个人学习业务'),(3,'助教','辅助教师的教学任务');

/*Table structure for table `t_employee` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_employee` (
  `employeeId` int(11) NOT NULL AUTO_INCREMENT,
  `employeeNo` varchar(10) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `nationality` varchar(10) DEFAULT NULL,
  `education` varchar(10) DEFAULT NULL,
  `profession` varchar(20) DEFAULT NULL,
  `departmentId` int(11) DEFAULT NULL,
  `position` varchar(20) DEFAULT NULL,
  `baseMoney` decimal(5,2) DEFAULT NULL,
  `overtime` decimal(5,2) DEFAULT NULL,
  `age` decimal(5,2) DEFAULT NULL,
  `check1` decimal(5,2) DEFAULT NULL,
  `absent` decimal(5,2) DEFAULT NULL,
  `safety` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`employeeId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `t_employee` */


/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`userId`,`userName`,`password`) values (1,'lkj','123456');


