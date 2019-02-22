/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.18-log : Database - mini_soc
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mini_soc` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mini_soc`;

/*Table structure for table `friend_request` */

DROP TABLE IF EXISTS `friend_request`;

CREATE TABLE `friend_request` (
  `from_id` int(11) NOT NULL,
  `to_id` int(11) NOT NULL,
  PRIMARY KEY (`from_id`,`to_id`),
  KEY `to_id` (`to_id`),
  CONSTRAINT `friend_request_ibfk_1` FOREIGN KEY (`from_id`) REFERENCES `user` (`id`),
  CONSTRAINT `friend_request_ibfk_2` FOREIGN KEY (`to_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `friend_request` */

insert  into `friend_request`(`from_id`,`to_id`) values (4,3),(6,3),(7,5),(2,6);

/*Table structure for table `messages` */

DROP TABLE IF EXISTS `messages`;

CREATE TABLE `messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_id` int(11) NOT NULL,
  `to_id` int(11) NOT NULL,
  `text` text,
  `sent_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`,`from_id`,`to_id`),
  KEY `from_id` (`from_id`),
  KEY `to_id` (`to_id`),
  CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`from_id`) REFERENCES `user` (`id`),
  CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`to_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `messages` */

insert  into `messages`(`id`,`from_id`,`to_id`,`text`,`sent_time`) values (5,2,7,'qwerty','2019-02-22 03:04:44'),(6,4,6,'Hello!','2019-02-22 03:25:08'),(7,4,2,'Hy Vzgo!','2019-02-22 03:27:55'),(8,4,2,'Hy Vzgo','2019-02-22 03:32:25'),(9,4,2,'dwq','2019-02-22 03:36:20'),(10,2,7,'axchi dzen hane','2019-02-22 03:43:22'),(11,2,4,'inch ka co\r\n','2019-02-22 08:21:19'),(12,4,2,'ban che du sa','2019-02-22 08:25:33');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `pic_url` varchar(255) DEFAULT NULL,
  `message_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `message_id` (`message_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`message_id`) REFERENCES `messages` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`surname`,`email`,`password`,`pic_url`,`message_id`) values (2,'Vzgo','Vzgoyan','vzgo@mail.ru','123','1550446187309_51915510_2128125197253919_7499592221184753664_o.jpg',NULL),(3,'Vlod','Vlodyan','vlo@mail','123','1550526912702_euclidean-vector-boy-cartoon-foreign-curly-boy.jpg',NULL),(4,'Exo','Exoyan','exo@mail','123','1550581165760_clipart-swimming-material-11.png',NULL),(5,'Pxo','Pxoyan','pxo@mail','123','1550581210712_2060a6f3aa.png',NULL),(6,'Alla','Alloyan','all@mail','123','1550581226761_0_19f474_7a8a9c6_orig.png',NULL),(7,'Anna','Annoyan','anna@mail','123','1550581241765_Girl_3D_png_14.png',NULL);

/*Table structure for table `user_friend` */

DROP TABLE IF EXISTS `user_friend`;

CREATE TABLE `user_friend` (
  `user_id` int(11) NOT NULL,
  `friend_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`friend_id`),
  KEY `friend_id` (`friend_id`),
  CONSTRAINT `user_friend_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_friend_ibfk_2` FOREIGN KEY (`friend_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_friend` */

insert  into `user_friend`(`user_id`,`friend_id`) values (4,2),(4,3),(7,5),(4,6),(2,7);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
