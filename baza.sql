/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 10.4.28-MariaDB : Database - prosoftprojekat
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`prosoftprojekat` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `prosoftprojekat`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `AdminID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) NOT NULL,
  `Lastname` varchar(20) NOT NULL,
  `Username` varchar(25) NOT NULL,
  `Password` varchar(100) NOT NULL,
  PRIMARY KEY (`AdminID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `admin` */

insert  into `admin`(`AdminID`,`Name`,`Lastname`,`Username`,`Password`) values 
(1,'Anastasija','Cvetkovic','ana249','ana249!');

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `CommentID` int(11) NOT NULL AUTO_INCREMENT,
  `Text` varchar(250) NOT NULL,
  `IDProduct` int(11) NOT NULL,
  `IDUser` int(11) NOT NULL,
  PRIMARY KEY (`CommentID`),
  KEY `comment_ibfk_2` (`IDUser`),
  KEY `comment_ibfk_1` (`IDProduct`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`IDProduct`) REFERENCES `product` (`ProductID`) ON UPDATE CASCADE,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`IDUser`) REFERENCES `user` (`UserID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `comment` */

/*Table structure for table `invoice` */

DROP TABLE IF EXISTS `invoice`;

CREATE TABLE `invoice` (
  `InvoiceID` int(11) NOT NULL AUTO_INCREMENT,
  `TotalAmountPrice` double NOT NULL,
  `SentToUser` tinyint(1) NOT NULL,
  `IDOrder` int(11) NOT NULL,
  `IDAdmin` int(11) NOT NULL,
  PRIMARY KEY (`InvoiceID`),
  KEY `IDOrder` (`IDOrder`),
  KEY `invoice_ibfk_2` (`IDAdmin`),
  CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`IDOrder`) REFERENCES `ordert` (`OrderID`),
  CONSTRAINT `invoice_ibfk_2` FOREIGN KEY (`IDAdmin`) REFERENCES `admin` (`AdminID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `invoice` */

insert  into `invoice`(`InvoiceID`,`TotalAmountPrice`,`SentToUser`,`IDOrder`,`IDAdmin`) values 
(2,35998,1,43,1),
(3,89997,1,41,1),
(4,62899,1,42,1),
(5,42998,1,40,1),
(6,62899,1,39,1),
(7,137498,1,45,1),
(8,130000,1,44,1),
(9,35998,1,34,1),
(10,42998,1,37,1),
(11,119996,1,49,1),
(12,119996,1,48,1),
(13,42998,1,36,1),
(14,29999,1,52,1),
(15,29999,1,51,1);

/*Table structure for table `orderitem` */

DROP TABLE IF EXISTS `orderitem`;

CREATE TABLE `orderitem` (
  `OrderItemID` int(11) NOT NULL AUTO_INCREMENT,
  `IDOrder` int(11) NOT NULL,
  `IDProduct` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  PRIMARY KEY (`OrderItemID`,`IDOrder`),
  KEY `IDInvoice` (`IDOrder`),
  KEY `IDProduct` (`IDProduct`),
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`IDOrder`) REFERENCES `ordert` (`OrderID`),
  CONSTRAINT `orderitem_ibfk_3` FOREIGN KEY (`IDProduct`) REFERENCES `product` (`ProductID`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `orderitem` */

insert  into `orderitem`(`OrderItemID`,`IDOrder`,`IDProduct`,`Quantity`) values 
(5,26,8,1),
(6,26,10,1),
(7,27,10,1),
(8,27,5,1),
(9,28,6,1),
(10,28,5,1),
(11,29,10,1),
(13,30,4,1),
(14,30,5,1),
(15,30,6,1),
(17,31,4,1),
(18,32,5,1),
(19,32,6,1),
(22,34,8,1),
(23,35,8,1),
(24,35,10,1),
(25,36,8,1),
(26,36,10,1),
(27,37,8,1),
(28,37,10,1),
(29,38,10,1),
(30,38,6,1),
(31,39,5,1),
(32,39,8,1),
(33,40,8,1),
(34,40,10,1),
(35,41,4,3),
(36,42,8,1),
(37,42,5,1),
(39,43,8,1),
(40,44,12,1),
(41,45,12,1),
(42,45,8,1),
(43,45,6,1),
(44,46,5,1),
(45,46,10,1),
(46,47,5,1),
(47,47,6,1),
(48,48,4,4),
(49,49,4,4),
(50,50,13,2),
(51,50,10,2),
(52,51,15,1),
(53,52,15,1);

/*Table structure for table `ordert` */

DROP TABLE IF EXISTS `ordert`;

CREATE TABLE `ordert` (
  `OrderID` int(11) NOT NULL AUTO_INCREMENT,
  `TotalAmountPrice` double(20,2) unsigned DEFAULT NULL,
  `IDAdmin` int(11) DEFAULT NULL,
  `IDUser` int(11) NOT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `ordert_ibfk_1` (`IDAdmin`),
  KEY `ordert_ibfk_2` (`IDUser`),
  CONSTRAINT `ordert_ibfk_1` FOREIGN KEY (`IDAdmin`) REFERENCES `admin` (`AdminID`) ON UPDATE CASCADE,
  CONSTRAINT `ordert_ibfk_2` FOREIGN KEY (`IDUser`) REFERENCES `user` (`UserID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `ordert` */

insert  into `ordert`(`OrderID`,`TotalAmountPrice`,`IDAdmin`,`IDUser`) values 
(24,5999.00,1,1),
(25,35998.00,1,1),
(26,42998.00,NULL,1),
(27,93899.00,NULL,1),
(28,58399.00,NULL,1),
(29,66998.00,NULL,1),
(30,88398.00,NULL,1),
(31,59998.00,NULL,1),
(32,58399.00,NULL,1),
(34,35998.00,1,1),
(35,42998.00,NULL,1),
(36,42998.00,1,1),
(37,42998.00,1,1),
(38,38498.00,NULL,1),
(39,62899.00,1,1),
(40,42998.00,1,1),
(41,89997.00,1,1),
(42,62899.00,1,1),
(43,35998.00,1,1),
(44,130000.00,1,1),
(45,137498.00,1,1),
(46,93899.00,NULL,1),
(47,58399.00,NULL,1),
(48,119996.00,1,1),
(49,119996.00,1,1),
(50,163996.00,NULL,1),
(51,29999.00,1,1),
(52,29999.00,1,1);

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `ProductID` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(100) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `Price` double NOT NULL,
  `Stock` int(11) DEFAULT NULL,
  `Reservation` int(11) DEFAULT NULL,
  PRIMARY KEY (`ProductID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `product` */

insert  into `product`(`ProductID`,`Title`,`Description`,`Price`,`Stock`,`Reservation`) values 
(4,'Asus Laptop','13\"/Intel  I5 N5030/8 GB DDR4/256 GB SSD/Intel UHD 605/FreeDOS',29999,-4,-4),
(5,'HP Laptop','G8 32M37EA 15,6\" FHD/Intel i5-1135G7/8 GB DDR4/512 GB SSD/Intel Iris Xe/FreeDOS',56900,3,3),
(6,'Genius Keyboard KB-118','Connectiong through USB connector on 1,4 m long cable',1499,13,7),
(8,'Wireless mouse','Logitech LightSpeed G305 - Red',5999,16,1),
(10,'Hard drive','HDD 3,5\" SATA 12 TB Toshiba MG07ACA12TE',36999,28,7),
(12,'Apple Book Laptop ','Apple 13\'\', IOS...',130000,73,0),
(13,'EPSON - Printer','Printer -  EcoTank L5290 C11CJ65403\nColor: black\nFormat A4',44999,61,2),
(15,'Lenovo Laptop V15 IGL','82C3002RYA 15,6\"/Intel Pentium Silver N5030/4 GB DDR4/256 GB SSD/Intel UHD 605/FreeDOS',29999,48,0),
(16,'Dell Laptop ','82C3002RYA 15,6\"/Intel Pentium Silver N5030/4 GB DDR4/256 GB SSD/Intel UHD 605/FreeDOS',34999,20,0),
(18,'Lenovo Laptop V200','17\"/Intel Pentium Silver N5030/4 GB DDR4/256 GB SSD/Intel UHD 605/FreeDOS',39999,70,0);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) NOT NULL,
  `Lastname` varchar(20) NOT NULL,
  `Username` varchar(25) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `PhoneNumber` varchar(25) DEFAULT NULL,
  `Address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `user` */

insert  into `user`(`UserID`,`Name`,`Lastname`,`Username`,`Password`,`PhoneNumber`,`Address`) values 
(1,'Miljana','Pavlovic','mika','mika1','0652911099','Romanijska 44'),
(3,'Tamara','Radosavljevic','tamara','tami2','0651745678','Jove Ilica 147'),
(4,'Petar','Jockovic','peca','peca1','061746985','Brace Jerkovic 22'),
(5,'Aleksandra','Stankovic','anci175','anci2','0688785423','Crnogorska 95'),
(7,'Milica','Mikic','mila','mila1','651745678','Jove Ilica 147'),
(8,'Nikolina','Nikic','nina','nina','065175267877','Jove Ilica 10');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
