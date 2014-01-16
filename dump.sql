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
INSERT INTO `books` VALUES (4,'Scott Oaks',1,2,'With Early Release ebooks, you get books in their earliest form—the author\'s raw and unedited content as he or she writes—so you can take advantage of these technologies long before the official release of these titles. You\'ll also receive updates when s',3,'http://akamaicovers.oreilly.com/images/0636920028499/rc_lrg.jpg',500,' O\'Reilly Media',1,0,' Larger Cover Java Performance: The Definitive Guide',2013,1),(6,'Dave Klein, Ben Klein',1,2,'Grails is a full-stack web development framework that enables you to build complete web applications in a fraction of the time and with less code than other frameworks. Grails uses the principle of convention over configuration and the dynamic Groovy pro',3,'http://akamaicovers.oreilly.com/images/9781937785772/lrg.jpg',600,'Pragmatic Bookshelf',1,0,'Grails 2: A Quick-Start Guide',2013,1),(7,'Armel Fabrice',1,2,'For several years, the development of robust, scalable, and secured applications was a headache for software companies. They had to use proprietary solutions with non-standard methods. With Java EE, many of these solutions have been standardized, simplif',3,'http://akamaicovers.oreilly.com/images/9781849699235/lrg.jpg',200,'Packt Publishing',1,0,'Java EE 7 First Look',2013,1),(8,'Donna Tartt',1,2,'he Goldfinch is a rarity that comes along perhaps half a dozen times per decade, a smartly written literary novel that connects with the heart as well as the mind....Donna Tartt has delivered an extraordinary work of fiction.\"--Stephen King, The New York',3,'http://ecx.images-amazon.com/images/I/41kMZdwHgbL.jpg',300,'some publish',1,0,'The Goldfinch',2013,3),(9,'David Baldacci',1,2,'From David Baldacci--#1 bestselling author and one of the world\'s most popular, widely read storytellers--comes the most thrilling novel of the year. THE HIT Will Robie is a master of killing. A highly skilled assassin, Robie is the man the U.S. governme',3,'http://img1.imagesbn.com/p/9781455521210_p0_v3_s260x420.JPG',300,'publishing',1,14,'The Hit',2012,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booksinuse`
--

LOCK TABLES `booksinuse` WRITE;
/*!40000 ALTER TABLE `booksinuse` DISABLE KEYS */;
INSERT INTO `booksinuse` VALUES (4,NULL,'2014-01-24 02:02:41',8,18);
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
  `changed` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bm2fin7b72b3xtjojm9da9kj6` (`pid`,`bid`),
  KEY `FK_71a4vlhrltibatg60nyuxpith` (`bid`),
  KEY `FK_hq80wuvwadnmgmjkn20xeqt2k` (`pid`),
  CONSTRAINT `FK_71a4vlhrltibatg60nyuxpith` FOREIGN KEY (`bid`) REFERENCES `books` (`bid`),
  CONSTRAINT `FK_hq80wuvwadnmgmjkn20xeqt2k` FOREIGN KEY (`pid`) REFERENCES `persons` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (51,'2014-01-25 23:00:00',7,6,0),(67,'2014-01-30 16:00:01',4,7,0),(89,'2014-01-30 09:00:01',8,19,1),(91,'2014-01-11 02:00:01',6,14,0);
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
  `img` varchar(255) DEFAULT NULL,
  `multi` int(11) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  UNIQUE KEY `UK_1x5aosta48fbss4d5b3kuu0rd` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persons`
--

LOCK TABLES `persons` WRITE;
/*!40000 ALTER TABLE `persons` DISABLE KEYS */;
INSERT INTO `persons` VALUES (6,0,'025896',0,'salt3@mail.com',0,0,'salt3','$2a$10$mDml.jxfK9YZheEaPoGLjuZABQWKoKovuQR.rUlZbrKEBKfbJfmhm','ROLE_USER',NULL,0,'salt3',0,0,NULL,2),(7,0,'3698523',0,'salt10@mail.com',0,0,'salt4_tmp','$2a$10$RryPOXvS.DEXGT8OwfhJLee62V4PZl34Sy8vuksd5OyZgbW4rLxVq','ROLE_USER',NULL,0,'salt5',0,0,NULL,2),(9,0,'333-3333-3333',0,'salt11@mail.com',0,0,'salt11','$2a$10$wUrvfmkXLBzrqrfHLRljt.l1Hm9bDZPKd9bgBd6Lclevp.wfFweD2','ROLE_USER',NULL,0,'salt11',0,0,NULL,NULL),(10,0,'000-0000-0000',0,'admin@mail.com',0,0,'admin','$2a$10$jUuzIcBNU6BqqHlQWz5prOURVNliCmpuohDwfdwwdCKYPGgEQsZ4C','ROLE_ADMIN',NULL,0,'admin',0,0,NULL,NULL),(11,0,'000-0000-0000',0,'librarian@mail.com',0,0,'librarian','$2a$10$80dvszVP58C/tQ2sSPLNruM/ni/JXiW2JW.qsR9GZgqe0fNwBy6Bu','ROLE_LIBRARIAN',NULL,0,'librarian',0,0,NULL,NULL),(13,0,'333-3333-3333',0,'user@mail.com',0,0,'user','$2a$10$rrO524AjypqwwHtkqjicve1N2XmdspqNTkxBxN36JAv79d/Ka4NQe','ROLE_USER',NULL,0,'user',0,0,NULL,1),(14,0,'444-4444-4444',0,'form3@mail.com',0,0,'form1','$2a$10$Jt/MloFDqCuvOjNCx6SqSeCBL.ahx956S.gHl79QOI4tX0ZdjAaye','ROLE_USER',NULL,0,'form',0,0,NULL,5),(15,0,'333-3333-3333',0,'form1@mail.com',0,0,'form1','$2a$10$1YU6Te95wBnPNjjKnBY5y./OCp1B0/shcnKqjhvTE8yoEf5rF9fLS','ROLE_USER',NULL,0,'form1',0,0,NULL,5),(16,0,'333-3333-3333',0,'root@mail.com',0,0,'admin','$2a$10$R0l2DyoWjmP0ug2/lffUXucY0lrqyLh3iD.pj55Vi0cpK2uRmusGS','ROLE_ADMIN',NULL,0,'root',0,0,NULL,5),(17,0,'333-3333-3333',0,'root1@mail.com',0,0,'root1','$2a$10$JBh6RfkKAZqv0OrH0m3Wg.yIgjoMu0CLDOvXKDMIcmBuqT.WGIloC','ROLE_LIBRARIAN',NULL,0,'root1',0,0,NULL,5),(18,0,'333-3333-3333',0,'concurency@mail.com',0,100,'concur','$2a$10$HRDZ3yewAM3HYSuwFgSAqeOM8GZ.CVdL8LKKGG7a.opDu7dREQx8i','ROLE_USER',NULL,0,'concur',1,0,NULL,5),(19,0,'333-3333-3333',0,'ordinary@mail.com',0,0,'ordinary','$2a$10$SRvqk475fbboz4uAny52QOBlZ9pYlVkFFDb.vaOzgZM5nrkqqqqeK','ROLE_USER',NULL,0,'ordinary',0,0,NULL,5);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
INSERT INTO `wishlist` VALUES (5,8,14),(4,9,14);
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

-- Dump completed on 2014-01-11  2:09:39
