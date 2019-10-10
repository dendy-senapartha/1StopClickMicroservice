/*
SQLyog Enterprise - MySQL GUI v8.14 
MySQL - 5.5.5-10.1.21-MariaDB : Database - 1stopclick_order
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`1stopclick_order` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `1stopclick_order`;

/*Table structure for table `invoice` */

DROP TABLE IF EXISTS `invoice`;

CREATE TABLE `invoice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orders_id` int(11) DEFAULT NULL,
  `user_id` bigint(255) DEFAULT NULL,
  `payment_method_id` int(11) DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Draft, Issued, Paid, Void; https://www.replicon.com/help/setting-the-status-of-an-invoice/',
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_transaction` (`orders_id`),
  KEY `FK_invoice` (`user_id`),
  KEY `FK_invoice_payment_method` (`payment_method_id`),
  CONSTRAINT `FK_invoice` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION,
  CONSTRAINT `FK_invoice_payment_method` FOREIGN KEY (`payment_method_id`) REFERENCES `payment_method` (`id`) ON DELETE NO ACTION,
  CONSTRAINT `FK_order_invoice` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `invoice` */

insert  into `invoice`(`id`,`orders_id`,`user_id`,`payment_method_id`,`description`,`status`,`created`) values (1,1,8,1,'ealah','PAID','2019-02-02 12:00:00'),(2,2,8,1,'yahooo','PAID','2019-12-06 12:00:00'),(8,9,8,1,'Ealah','VOID','2012-12-12 00:00:00'),(9,10,7,1,'yahooo','PAID','2012-12-12 00:00:00'),(12,13,8,1,'dfghndfdfgjfdg','PAID','2019-07-16 00:00:00'),(13,14,8,1,'','PAID','2019-07-17 00:00:00'),(15,16,8,1,'','PAID','2019-07-17 00:00:00'),(18,19,33,1,'','PAID','2019-07-19 00:00:00'),(19,20,33,1,'','PAID','2019-07-19 00:00:00'),(20,21,8,1,'','PAID','2019-07-25 00:00:00');

/*Table structure for table `order_item` */

DROP TABLE IF EXISTS `order_item`;

CREATE TABLE `order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `subtotal` decimal(8,2) DEFAULT NULL,
  `order_item_category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_product_order_item` (`product_id`),
  KEY `FK_order_item` (`order_id`),
  CONSTRAINT `FK_order_item` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_product_order_item` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;

/*Data for the table `order_item` */

insert  into `order_item`(`id`,`order_id`,`product_id`,`quantity`,`subtotal`,`order_item_category_id`) values (1,1,81,1,'5000.00',6),(2,1,75,1,'55000.00',3),(3,2,84,1,'5000.00',6),(11,9,82,1,'10000.00',3),(12,10,75,1,'55000.00',3),(13,10,81,1,'5000.00',6),(14,9,81,1,'5000.00',6),(15,9,75,1,'55000.00',3),(24,9,84,1,'5000.00',6),(29,13,79,1,'10000.00',3),(30,14,80,1,'10000.00',3),(33,16,85,1,'5000.00',6),(38,19,80,1,'10000.00',3),(39,20,77,1,'100000.00',3),(40,20,82,1,'10000.00',3),(41,20,86,1,'5000.00',6),(42,20,81,1,'5000.00',6),(43,21,77,1,'100000.00',3);

/*Table structure for table `order_item_category` */

DROP TABLE IF EXISTS `order_item_category`;

CREATE TABLE `order_item_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `order_item_category` */

insert  into `order_item_category`(`id`,`name`,`created`) values (3,'MOVIES','2016-10-25 08:12:06'),(4,'APPS','2016-10-25 08:14:17'),(5,'BOOKS','2016-11-02 08:37:45'),(6,'MUSICS','2016-11-02 09:37:23');

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_date` date DEFAULT NULL,
  `total_amount` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

/*Data for the table `orders` */

insert  into `orders`(`id`,`order_date`,`total_amount`) values (1,'2019-02-02','60000.00'),(2,'2019-12-06','5000.00'),(9,'2012-12-12','75000.00'),(10,'2019-02-02','60000.00'),(13,'2019-07-16','10000.00'),(14,'2019-07-17','10000.00'),(16,'2019-07-17','5000.00'),(19,'2019-07-19','10000.00'),(20,'2019-07-19','120000.00'),(21,'2019-07-25','100000.00');

/*Table structure for table `payment_method` */

DROP TABLE IF EXISTS `payment_method`;

CREATE TABLE `payment_method` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `payment_method` */

insert  into `payment_method`(`id`,`code`,`name`) values (1,'1stopWalet','1stopWalet'),(2,'test','test');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
