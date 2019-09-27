/*
SQLyog Enterprise - MySQL GUI v8.14 
MySQL - 5.5.5-10.1.21-MariaDB : Database - 1stopclick_auth
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`1stopclick_auth` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `1stopclick_auth`;

/*Table structure for table `roles` */

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `modified_on` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `roles` */

insert  into `roles`(`id`,`created_on`,`description`,`modified_on`,`name`) values (1,NULL,'Admin',NULL,'ADMIN'),(2,NULL,'User',NULL,'USER');

/*Table structure for table `user_profile` */

DROP TABLE IF EXISTS `user_profile`;

CREATE TABLE `user_profile` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `dob` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `user_id` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

/*Data for the table `user_profile` */

insert  into `user_profile`(`id`,`name`,`dob`,`phone`,`image_url`,`user_id`) values (7,'dendy prtha','12-12-2012','0123456','Ealah',8),(8,'wadsd','12-12-2012','DRAFT','Ealah',7),(14,'semprul','12-12-2012','123456l','asu.bjg',25),(15,'stop test satu','20/6/2019','085243494846','tt',27),(17,'dendy prtha',NULL,NULL,NULL,31),(18,'One Stop Click',NULL,NULL,NULL,32),(19,'kamfrettt','24-07-19','0123456','Ealah',33);

/*Table structure for table `user_roles` */

DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_roles` */

insert  into `user_roles`(`user_id`,`role_id`) values (7,2),(8,1),(8,2),(25,2);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `email_verified` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `provider` varchar(255) DEFAULT NULL,
  `provider_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`id`,`email`,`email_verified`,`password`,`provider`,`provider_id`) values (7,'local','','$2a$10$vFudbPTChkLMxs1RZ/nCY.9xksoo2qauQzE60b8Izrz4D7orWpwy6','local-account','116984227318604993526'),(8,'prtha.dendy@gmail.com','','$2a$10$bNiVA9BH9huhvQwcH8ibFuuKJMAC.7NZaTJuZzmXtUE92yqYpbs7i','google','116984227318604997150'),(25,'semprul','\0','$2a$10$UcZb4UyAtkBtkUkqclMnSOD7xyirqeKaTG/KIQ6nkNL0QVlsYbTtq','local-account',NULL),(27,'stoptest1@yopmail.com','\0','P@ssw0rd11','local-account','41483015074826100724'),(31,'dendyprtha@gmail.com','',NULL,'google','102715366000470368630'),(32,'testonestopclick@gmail.com','',NULL,'google','100837819285246640790'),(33,'stopclick1@yopmail.com','\0','P@ssw0rd11','local-account','11250530825020281151');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
