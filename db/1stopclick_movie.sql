/*
SQLyog Enterprise - MySQL GUI v8.14 
MySQL - 5.5.5-10.1.21-MariaDB : Database - 1stopclick_movie
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`1stopclick_movie` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `1stopclick_movie`;

/*Table structure for table `actor` */

DROP TABLE IF EXISTS `actor`;

CREATE TABLE `actor` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(1000) DEFAULT NULL,
  `last_name` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `actor` */

insert  into `actor`(`id`,`first_name`,`last_name`) values (1,'Peter','Dinklage'),(2,'Emilia ','Clarke'),(3,'Kit','Harington '),(4,'Charlie','Chaplin'),(5,'Tom','Hardy'),(6,'Charlize','Theron'),(7,'Shia','LaBeouf'),(8,'Rosie','Huntington-Whiteley');

/*Table structure for table `actor_video` */

DROP TABLE IF EXISTS `actor_video`;

CREATE TABLE `actor_video` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `actor_id` int(100) DEFAULT NULL,
  `video_id` int(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_actor_video_actor` (`actor_id`),
  KEY `FK_video_actor_video` (`video_id`),
  CONSTRAINT `FK_actor_video_actor` FOREIGN KEY (`actor_id`) REFERENCES `actor` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_video_actor_video` FOREIGN KEY (`video_id`) REFERENCES `video` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;

/*Data for the table `actor_video` */

insert  into `actor_video`(`id`,`actor_id`,`video_id`) values (1,4,1),(2,4,2),(3,1,7),(4,2,7),(5,3,7),(6,1,8),(7,2,8),(8,3,8),(9,1,9),(10,2,9),(11,3,9),(12,1,10),(13,2,10),(14,3,10),(15,1,11),(16,2,11),(17,3,11),(18,1,12),(19,2,12),(20,3,12),(21,1,13),(22,2,13),(23,3,13),(24,1,14),(25,2,14),(26,3,14),(27,5,3),(28,6,3),(29,5,4),(30,6,4),(31,7,5),(32,8,5),(33,7,6),(34,NULL,6);

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

insert  into `category`(`id`,`name`,`target`,`priority`,`created`,`is_active`) values (3,'Movies','#movies',1,'2016-10-25 08:12:06',''),(4,'Aplications','#aplications',2,'2016-10-25 08:14:17',''),(5,'Books','#books',3,'2016-11-02 08:37:45',''),(6,'Musics','#musics',4,'2016-11-02 09:37:23','');

/*Table structure for table `director` */

DROP TABLE IF EXISTS `director`;

CREATE TABLE `director` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(2000) DEFAULT NULL,
  `last_name` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `director` */

insert  into `director`(`id`,`first_name`,`last_name`) values (1,'David','Nutter'),(2,'Miguel','Sapochnik'),(3,'David','Benioff '),(4,'Charlie','Chaplin'),(5,'George','Miller'),(6,'Michael','Bay');

/*Table structure for table `director_video` */

DROP TABLE IF EXISTS `director_video`;

CREATE TABLE `director_video` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `director_id` int(100) DEFAULT NULL,
  `video_id` int(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_director_video_director` (`director_id`),
  KEY `FK_video_director_video` (`video_id`),
  CONSTRAINT `FK_director_video_director` FOREIGN KEY (`director_id`) REFERENCES `director` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_video_director_video` FOREIGN KEY (`video_id`) REFERENCES `video` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `director_video` */

insert  into `director_video`(`id`,`director_id`,`video_id`) values (1,4,1),(2,4,2),(3,1,7),(4,2,8),(5,3,9),(6,1,10),(7,2,11),(8,3,12),(9,1,13),(10,2,14),(12,5,3),(13,5,4),(14,6,5),(15,6,6);

/*Table structure for table `genre` */

DROP TABLE IF EXISTS `genre`;

CREATE TABLE `genre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `genre` */

insert  into `genre`(`id`,`name`) values (1,'Comedy'),(2,'Action');

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
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `product` */

insert  into `product`(`id`,`product_name`,`package_code`,`price`,`description`,`compatibility`,`status`,`created`) values (75,'Charlie Chaplin - Pawn Shop','35252','55000.00','The Pawnshop was Charlie Chaplin\'s sixth film for Mutual Film Corporation. Released on October 2, 1916, it stars Chaplin in the role of assistant to the pawnshop owner, played by Henry Bergman. Edna Purviance plays the owner\'s daughter, while Albert Austin appears as an alarm clock owner who watches Chaplin in dismay as he dismantles the clock; the massive Eric Campbell\'s character attempts to rob the shop.','All Platform','ACTIVE','2016-11-02 11:24:00'),(77,'Mad Max : Fury Road','347824','100000.00','Years after the collapse of civilization, the tyrannical Immortan Joe enslaves apocalypse survivors inside the desert fortress the Citadel. When the warrior Imperator Furiosa (Charlize Theron) leads the despot\'s five wives in a daring escape, she forges an alliance with Max Rockatansky (Tom Hardy), a loner and former captive. Fortified in the massive, armored truck the War Rig, they try to outrun the ruthless warlord and his henchmen in a deadly high-speed chase through the Wasteland.','All Platform','ACTIVE','2016-11-02 12:59:42'),(78,'Transformers 3: Dark of the Moon','342414','50000.00','This is new movie release in the end of 2016','All Platform','ACTIVE','2016-11-02 14:18:27'),(79,'Game Of Thrones: Season 8 Eps 1','353124','10000.00','u yhea','All Platform','ACTIVE','2018-04-23 13:06:02'),(80,'Game Of Thrones: Season 8 Eps 2','353125','10000.00','Hae Hae','All Platform','ACTIVE','2016-11-02 14:18:27'),(82,'Game Of Thrones: Season 8 Eps 3','335515','10000.00','eah','All Platofrm','ACTIVE','2018-04-23 13:06:02'),(83,'Game Of Thrones: Season 8 Eps 4','335516','10000.00','hh\r\n','All Platform','ACTIVE','2018-04-23 13:06:02');

/*Table structure for table `product_genre` */

DROP TABLE IF EXISTS `product_genre`;

CREATE TABLE `product_genre` (
  `product_id` int(11) NOT NULL,
  `genre_id` int(11) NOT NULL,
  PRIMARY KEY (`product_id`,`genre_id`),
  KEY `FK_product_genre` (`genre_id`),
  CONSTRAINT `FK_genre_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION,
  CONSTRAINT `FK_product_genre` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `product_genre` */

insert  into `product_genre`(`product_id`,`genre_id`) values (75,1),(77,2),(78,2);

/*Table structure for table `product_image` */

DROP TABLE IF EXISTS `product_image`;

CREATE TABLE `product_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `image_url` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_64617f034584665a` (`product_id`),
  CONSTRAINT `FK_product_image` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `product_image` */

insert  into `product_image`(`id`,`product_id`,`image_url`) values (44,77,'http://13.59.254.174/1stopclickdata/images/prodart/madmax_furryroad.jpg'),(59,75,'https://upload.wikimedia.org/wikipedia/commons/3/37/%27The_Pawnshop%27.jpg'),(61,78,'http://cdn.collider.com/wp-content/uploads/T3-IMAX-One-Sheet-FINAL.jpg'),(62,78,'https://stmed.net/sites/default/files/transformers%3A-dark-of-the-moon-wallpapers-30151-9400750.jpg'),(63,79,'http://13.59.254.174/1stopclickdata/images/prodart/GOT_poster.jpg'),(64,79,'http://13.59.254.174/1stopclickdata/images/backdrop/game-of-thrones-season-8-social.jpeg'),(65,80,'http://13.59.254.174/1stopclickdata/images/prodart/GOT_poster.jpg'),(66,80,'http://13.59.254.174/1stopclickdata/images/backdrop/game-of-thrones-season-8-social.jpeg'),(67,82,'http://13.59.254.174/1stopclickdata/images/prodart/GOT_poster.jpg'),(68,82,'http://13.59.254.174/1stopclickdata/images/backdrop/game-of-thrones-season-8-social.jpeg'),(69,83,'http://13.59.254.174/1stopclickdata/images/prodart/GOT_poster.jpg'),(70,83,'http://13.59.254.174/1stopclickdata/images/backdrop/GOT2.jpg'),(71,77,'http://13.59.254.174/1stopclickdata/images/backdrop/MadMaxFuryRoad.jpg'),(72,75,'https://centuryfilmproject.files.wordpress.com/2016/01/pawnshop_lobby_card_1916.jpg');

/*Table structure for table `product_preview` */

DROP TABLE IF EXISTS `product_preview`;

CREATE TABLE `product_preview` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `link` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_preview_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `product_preview` */

/*Table structure for table `video` */

DROP TABLE IF EXISTS `video`;

CREATE TABLE `video` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `video_type_id` int(100) DEFAULT NULL,
  `release_date` date NOT NULL,
  `studio` varchar(50) NOT NULL,
  `age_rating` int(11) NOT NULL,
  `avg_rating` float DEFAULT NULL,
  `overall_rank` int(11) DEFAULT NULL,
  `stream_url` varchar(2000) NOT NULL,
  `duration` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_product_video` (`product_id`),
  KEY `FK_video_video_type` (`video_type_id`),
  CONSTRAINT `FK_product_video` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FK_video_video_type` FOREIGN KEY (`video_type_id`) REFERENCES `video_type` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `video` */

insert  into `video`(`id`,`product_id`,`video_type_id`,`release_date`,`studio`,`age_rating`,`avg_rating`,`overall_rank`,`stream_url`,`duration`) values (1,75,1,'1916-10-02','1',1,1,1,'http://13.59.254.174/1stopclickdata/movie/ThePawnshop.mp4',26),(2,75,2,'1916-10-02','1',1,1,1,'8UAy9ynS-l4',2),(3,77,1,'2014-01-01','1',1,1,1,'http://13.59.254.174/1stopclickdata/movie/MAMAFUR1572264SG-RENAME.mkv',200),(4,77,2,'2014-01-01','1',1,1,1,'hEJnMQG9ev8',4),(5,78,1,'2014-01-01','1',1,1,1,'https://ia802609.us.archive.org/4/items/darkmoontrailerilp/transformdarkmoontrail_512kb.mp4',20),(6,78,2,'2014-01-01','1',1,1,1,'3H8bnKdf654',20),(7,79,1,'2019-01-01','1',1,1,1,'http://13.59.254.174/1stopclickdata/movie/GOT08E01.mkv',80),(8,79,2,'2019-01-01','1',1,1,1,'rlR4PJn8b8I',20),(9,80,1,'2019-02-02','1',1,1,1,'http://13.59.254.174/1stopclickdata/movie/GOT08E02.mkv',80),(10,80,2,'2019-02-02','1',1,1,1,'R6YCfVe4eR0',20),(11,82,1,'2019-03-03','1',1,1,1,'http://13.59.254.174/1stopclickdata/movie/GOT08E03.mkv',808),(12,82,2,'2019-03-03','1',1,1,1,'TdkS4Xazz7Q',20),(13,83,1,'2019-04-04','1',1,1,1,'http://13.59.254.174/1stopclickdata/movie/GOT08E04.mkv',90),(14,83,2,'2019-04-04','1',1,1,1,'ksTqLXLUvQ4',20);

/*Table structure for table `video_type` */

DROP TABLE IF EXISTS `video_type`;

CREATE TABLE `video_type` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `code` varchar(2000) DEFAULT NULL,
  `name` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `video_type` */

insert  into `video_type`(`id`,`code`,`name`) values (1,'movie','movie'),(2,'trailer','trailer'),(3,'test','test');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
