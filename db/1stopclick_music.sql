/*
SQLyog Enterprise - MySQL GUI v8.14 
MySQL - 5.5.5-10.1.21-MariaDB : Database - 1stopclick_music
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`1stopclick_music` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `1stopclick_music`;

/*Table structure for table `albums` */

DROP TABLE IF EXISTS `albums`;

CREATE TABLE `albums` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(2000) DEFAULT NULL,
  `release_date` date DEFAULT NULL,
  `album_image_url` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `albums` */

insert  into `albums`(`id`,`name`,`release_date`,`album_image_url`) values (1,'By The Way','2002-01-01','https://upload.wikimedia.org/wikipedia/en/2/23/Rhcp9.jpg'),(2,'RHCP Compilation','2002-01-01','https://cps-static.rovicorp.com/3/JPG_500/MI0001/787/MI0001787862.jpg');

/*Table structure for table `artist` */

DROP TABLE IF EXISTS `artist`;

CREATE TABLE `artist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(2000) DEFAULT NULL,
  `last_name` varchar(2000) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `artist` */

insert  into `artist`(`id`,`first_name`,`last_name`,`dob`) values (1,'Anthony','Kiedis',NULL),(2,'John ','Frusciante ',NULL),(3,'Flea ',NULL,NULL),(4,'Chad ','Smith ',NULL);

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `target` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `created` datetime NOT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `category` */

insert  into `category`(`id`,`name`,`target`,`priority`,`created`,`is_active`) values (4,'Aplications','#aplications',2,'2016-10-25 08:14:17',''),(5,'Books','#books',3,'2016-11-02 08:37:45',''),(6,'Musics','#musics',4,'2016-11-02 09:37:23','');

/*Table structure for table `image_type_product` */

DROP TABLE IF EXISTS `image_type_product`;

CREATE TABLE `image_type_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `image_type_product` */

insert  into `image_type_product`(`id`,`code`,`name`) values (1,'MovieArt','Movie Art'),(2,'MovieBackdrop','Movie Backdrop'),(3,'test','test');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `package_code` decimal(10,0) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `description` text COLLATE utf8_unicode_ci,
  `compatibility` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `product` */

insert  into `product`(`id`,`product_name`,`package_code`,`price`,`description`,`compatibility`,`status`,`created`) values (81,'RHCP Dosed','335514','5000.00','eah\r\n','All Platform','ACTIVE','2018-04-23 13:06:02'),(84,'The Zephyr Song','339429','5000.00','[2002] - By the Way','All Platform','ACTIVE','2018-04-23 13:06:02'),(85,'By The Way','339430','5000.00','By The way','All Platform','ACTIVE','2018-04-23 13:06:02'),(86,'Throw Away Your Television','339431','5000.00','throw away your television','All Platform','ACTIVE','2018-04-23 13:06:02');

/*Table structure for table `product_image` */

DROP TABLE IF EXISTS `product_image`;

CREATE TABLE `product_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `image_type_id` int(11) NOT NULL,
  `image_url` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_64617f034584665a` (`product_id`),
  KEY `FK_image_image_type` (`image_type_id`),
  CONSTRAINT `FK_image_image_type` FOREIGN KEY (`image_type_id`) REFERENCES `image_type_product` (`id`),
  CONSTRAINT `FK_product_image` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `product_image` */

insert  into `product_image`(`id`,`product_id`,`image_type_id`,`image_url`) values (73,81,1,'https://upload.wikimedia.org/wikipedia/en/2/23/Rhcp9.jpg'),(74,84,1,'https://upload.wikimedia.org/wikipedia/en/3/3d/Zephyrsong.jpg');

/*Table structure for table `product_subcategory` */

DROP TABLE IF EXISTS `product_subcategory`;

CREATE TABLE `product_subcategory` (
  `product_id` int(11) NOT NULL,
  `subcategory_id` int(11) NOT NULL,
  PRIMARY KEY (`subcategory_id`,`product_id`),
  KEY `FK_product_subcategory` (`product_id`),
  CONSTRAINT `FK_product_subcategory` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_subcategory_product` FOREIGN KEY (`subcategory_id`) REFERENCES `subcategory` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `product_subcategory` */

/*Table structure for table `subcategory` */

DROP TABLE IF EXISTS `subcategory`;

CREATE TABLE `subcategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `target` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT NULL,
  `created` datetime NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_ddca44812469de2` (`category_id`),
  CONSTRAINT `FK_subcategory_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `subcategory` */

insert  into `subcategory`(`id`,`name`,`target`,`priority`,`is_active`,`created`,`category_id`) values (4,'Pop','#mscpop',1,1,'2016-11-02 09:38:45',6),(5,'Rock','#mscrock',2,1,'2016-11-02 09:49:47',6);

/*Table structure for table `track_artist` */

DROP TABLE IF EXISTS `track_artist`;

CREATE TABLE `track_artist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `track_id` int(11) DEFAULT NULL,
  `artist_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_album_track_artist_artist` (`artist_id`),
  KEY `FK_track_artist` (`track_id`),
  CONSTRAINT `FK_album_track_artist_artist` FOREIGN KEY (`artist_id`) REFERENCES `artist` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_track_artist` FOREIGN KEY (`track_id`) REFERENCES `tracks` (`id`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `track_artist` */

insert  into `track_artist`(`id`,`track_id`,`artist_id`) values (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,2,1),(6,2,2),(7,2,3),(8,2,4);

/*Table structure for table `track_type` */

DROP TABLE IF EXISTS `track_type`;

CREATE TABLE `track_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(200) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `track_type` */

insert  into `track_type`(`id`,`code`,`name`) values (1,'music','music main'),(2,'preview','preview music');

/*Table structure for table `tracks` */

DROP TABLE IF EXISTS `tracks`;

CREATE TABLE `tracks` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `track_type_id` int(11) DEFAULT NULL,
  `stream_url` varchar(2000) DEFAULT NULL,
  `length` int(11) DEFAULT NULL,
  `album_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tracks` (`product_id`),
  KEY `FK_tracks_track_type` (`track_type_id`),
  KEY `FK_tracks_album` (`album_id`),
  CONSTRAINT `FK_tracks` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_tracks_album` FOREIGN KEY (`album_id`) REFERENCES `albums` (`id`) ON DELETE NO ACTION,
  CONSTRAINT `FK_tracks_track_type` FOREIGN KEY (`track_type_id`) REFERENCES `track_type` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `tracks` */

insert  into `tracks`(`id`,`product_id`,`track_type_id`,`stream_url`,`length`,`album_id`) values (1,81,1,'http://13.59.254.174/1stopclickdata/music/rhcp_Dosed.mp3',20,1),(2,84,1,'http://13.59.254.174/1stopclickdata/music/TheZephyrSong.mp3',5,2),(3,85,1,'http://13.59.254.174/1stopclickdata/music/By_the_Way.mp3',5,1),(4,86,1,'http://13.59.254.174/1stopclickdata/music/ThrowAwayYourTelevision.mp3',5,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
