-- MySQL dump 10.13  Distrib 5.6.14, for Win64 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	5.6.14

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
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `authors` varchar(255) DEFAULT NULL,
  `bookcase` int(11) DEFAULT NULL,
  `cur_quantity` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `gen_quantity` int(11) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `pages` int(11) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `shelf` int(11) DEFAULT NULL,
  `term` int(11) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `year_public` int(11) DEFAULT NULL,
  `gid` int(11) DEFAULT NULL,
  PRIMARY KEY (`bid`),
  UNIQUE KEY `UK_5mtto2jcmfrwfg0p1ui8mnweu` (`title`),
  KEY `FK_k00r52dx96mgbrvv8i05saupq` (`gid`),
  CONSTRAINT `FK_k00r52dx96mgbrvv8i05saupq` FOREIGN KEY (`gid`) REFERENCES `genres` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (4,'Scott Oaks',0,0,'With Early Release ebooks, you get books in their earliest form—the author\'s raw and unedited content as he or she writes—so you can take advantage of these technologies long before the official release of these titles. You\'ll also receive updates when s',0,'http://akamaicovers.oreilly.com/images/0636920028499/rc_lrg.jpg',500,' O\'Reilly Media',0,0,' Larger Cover Java Performance: The Definitive Guide',2013,1),(6,'Dave Klein, Ben Klein',0,0,'Grails is a full-stack web development framework that enables you to build complete web applications in a fraction of the time and with less code than other frameworks. Grails uses the principle of convention over configuration and the dynamic Groovy pro',0,'http://akamaicovers.oreilly.com/images/9781937785772/lrg.jpg',600,'Pragmatic Bookshelf',0,0,'Grails 2: A Quick-Start Guide',2013,1),(7,'Armel Fabrice',0,0,'For several years, the development of robust, scalable, and secured applications was a headache for software companies. They had to use proprietary solutions with non-standard methods. With Java EE, many of these solutions have been standardized, simplif',0,'http://akamaicovers.oreilly.com/images/9781849699235/lrg.jpg',200,'Packt Publishing',0,0,'Java EE 7 First Look',2013,1),(8,'Donna Tartt',0,0,'he Goldfinch is a rarity that comes along perhaps half a dozen times per decade, a smartly written literary novel that connects with the heart as well as the mind....Donna Tartt has delivered an extraordinary work of fiction.\"--Stephen King, The New York',0,'http://ecx.images-amazon.com/images/I/41kMZdwHgbL.jpg',300,'some publish',0,0,'The Goldfinch',2013,3),(9,'David Baldacci',0,0,'From David Baldacci--#1 bestselling author and one of the world\'s most popular, widely read storytellers--comes the most thrilling novel of the year. THE HIT Will Robie is a master of killing. A highly skilled assassin, Robie is the man the U.S. governme',0,'http://img1.imagesbn.com/p/9781455521210_p0_v3_s260x420.JPG',300,'publishing',0,0,'The Hit',2012,3);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booksinuse`
--

DROP TABLE IF EXISTS `booksinuse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booksinuse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `issue_date` datetime DEFAULT NULL,
  `return_date` datetime DEFAULT NULL,
  `bid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ipeloh4bxcbef8au6s991tvb5` (`pid`,`bid`),
  KEY `FK_3ft6fv08l2vu9djpg3ltjuhn6` (`bid`),
  KEY `FK_ovmx9ggxtncwm3ad002e6ogny` (`pid`),
  CONSTRAINT `FK_3ft6fv08l2vu9djpg3ltjuhn6` FOREIGN KEY (`bid`) REFERENCES `books` (`bid`),
  CONSTRAINT `FK_ovmx9ggxtncwm3ad002e6ogny` FOREIGN KEY (`pid`) REFERENCES `persons` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booksinuse`
--

LOCK TABLES `booksinuse` WRITE;
/*!40000 ALTER TABLE `booksinuse` DISABLE KEYS */;
INSERT INTO `booksinuse` VALUES (2,NULL,'2014-01-20 14:00:00',6,2);
/*!40000 ALTER TABLE `booksinuse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genres`
--

DROP TABLE IF EXISTS `genres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genres` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genres`
--

LOCK TABLES `genres` WRITE;
/*!40000 ALTER TABLE `genres` DISABLE KEYS */;
INSERT INTO `genres` VALUES (1,'CS'),(3,'Imaginative'),(4,'Science'),(5,'Art');
/*!40000 ALTER TABLE `genres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_date` datetime DEFAULT NULL,
  `bid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bm2fin7b72b3xtjojm9da9kj6` (`pid`,`bid`),
  KEY `FK_71a4vlhrltibatg60nyuxpith` (`bid`),
  KEY `FK_hq80wuvwadnmgmjkn20xeqt2k` (`pid`),
  CONSTRAINT `FK_71a4vlhrltibatg60nyuxpith` FOREIGN KEY (`bid`) REFERENCES `books` (`bid`),
  CONSTRAINT `FK_hq80wuvwadnmgmjkn20xeqt2k` FOREIGN KEY (`pid`) REFERENCES `persons` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (3,'2013-12-25 05:00:01',4,1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persons`
--

DROP TABLE IF EXISTS `persons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persons` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `booksallowed` int(11) DEFAULT NULL,
  `cellphone` varchar(255) DEFAULT NULL,
  `confirm` tinyint(1) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `failedorders` int(11) DEFAULT NULL,
  `generalratio` float DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `prole` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `sms` tinyint(1) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `timelyreturn` int(11) DEFAULT NULL,
  `untimelyreturn` int(11) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  UNIQUE KEY `UK_1x5aosta48fbss4d5b3kuu0rd` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persons`
--

LOCK TABLES `persons` WRITE;
/*!40000 ALTER TABLE `persons` DISABLE KEYS */;
INSERT INTO `persons` VALUES (1,0,'025',1,'user5@mail.com',0,0,'us','5555','ROLE_USER',NULL,0,'us',0,0),(2,0,'025896',1,'user2@mail.com',0,0,'user2','2222','ROLE_USER',NULL,0,'user2',0,0),(4,0,'0258',1,'user3@mail.com',0,0,'user','3333','ROLE_USER',NULL,0,'us',0,0);
/*!40000 ALTER TABLE `persons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wishlist`
--

DROP TABLE IF EXISTS `wishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wishlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ce6x9dsrfajhrfrr4xe3tuoar` (`pid`,`bid`),
  KEY `FK_qy6i9cn8qxtw4ieo8hxxm7743` (`bid`),
  KEY `FK_ljn2qif6symgl210krcs6jf3i` (`pid`),
  CONSTRAINT `FK_ljn2qif6symgl210krcs6jf3i` FOREIGN KEY (`pid`) REFERENCES `persons` (`pid`),
  CONSTRAINT `FK_qy6i9cn8qxtw4ieo8hxxm7743` FOREIGN KEY (`bid`) REFERENCES `books` (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `wishlist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-12-23  2:49:07
