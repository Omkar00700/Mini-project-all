/*
SQLyog Community v9.30 
MySQL - 5.6.25-log : Database - timetable
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`timetable` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `timetable`;

/*Table structure for table `st_faculty` */

DROP TABLE IF EXISTS `st_faculty`;

CREATE TABLE `st_faculty` (
  `Id` bigint(20) NOT NULL,
  `FACULTY_NAME` varchar(50) DEFAULT NULL,
  `DEPARTMENT` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `st_faculty` */

insert  into `st_faculty`(`Id`,`FACULTY_NAME`,`DEPARTMENT`) values (1,'Hariom Mukati','Computer'),(2,'Manoj Patidar','Commercial'),(3,'Rohan Jain','Programing'),(4,'Manish Shah','Technical'),(5,'Bhupendra Patidar','Production'),(6,'Bhuvnesh Shrivastava','Devlopment');

/*Table structure for table `st_subject` */

DROP TABLE IF EXISTS `st_subject`;

CREATE TABLE `st_subject` (
  `Id` bigint(20) NOT NULL,
  `Subject_name` varchar(50) DEFAULT NULL,
  `Subject_code` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `st_subject` */

insert  into `st_subject`(`Id`,`Subject_name`,`Subject_code`) values (1,'Java',10011),(2,'C++',10012),(3,'Data Structure',10013),(4,'Programming Language',10014),(5,'Softwere Engineering',10015),(6,'Tecnical Communication',10016);

/*Table structure for table `st_timetable` */

DROP TABLE IF EXISTS `st_timetable`;

CREATE TABLE `st_timetable` (
  `ID` bigint(20) DEFAULT NULL,
  `Faculty_Name` varchar(50) DEFAULT NULL,
  `Subject_Name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `st_timetable` */

insert  into `st_timetable`(`ID`,`Faculty_Name`,`Subject_Name`) values (1,'Bhupendra Patidar','Tecnical Communication'),(2,'Bhuvnesh Shrivastava','Programming Language'),(3,'Hariom Mukati','Java'),(4,'Manish Shah','Java'),(5,'Manoj Patidar','Data Structure'),(6,'Rohan Jain','C++');

/*Table structure for table `st_user` */

DROP TABLE IF EXISTS `st_user`;

CREATE TABLE `st_user` (
  `Id` bigint(20) NOT NULL,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL,
  `PhoneNo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `st_user` */

insert  into `st_user`(`Id`,`FirstName`,`LastName`,`Email`,`Password`,`PhoneNo`) values (1,'Hariom','Mukati','Hariom@gmail.com','123',55855812);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
