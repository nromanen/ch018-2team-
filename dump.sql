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
  `description` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `pages` int(11) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `shelf` int(11) DEFAULT NULL,
  `term` int(11) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `year_public` int(11) DEFAULT NULL,
  `gid` int(11) DEFAULT NULL,
  `cur_quantity` int(11) DEFAULT NULL,
  `gen_quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`bid`),
  UNIQUE KEY `UK_5mtto2jcmfrwfg0p1ui8mnweu` (`title`),
  KEY `FK_k00r52dx96mgbrvv8i05saupq` (`gid`),
  CONSTRAINT `FK_k00r52dx96mgbrvv8i05saupq` FOREIGN KEY (`gid`) REFERENCES `genres` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (2,'Ben Klein',0,'Grails is a full-stack web development framework that enables you to build complete web applications in a fraction of the time and with less code than other frameworks. Grails uses the principle of convention over configuration and the dynamic Groovy pro','http://akamaicovers.oreilly.com/images/9781937785772/lrg.jpg',222,'Pragmatic Bookshelf',0,0,'Grails 2: A Quick-Start Guide',2013,4,5,10),(3,'Ryan Neufeld',0,'With Early Release ebooks, you get books in their earliest form ÃÂÃÂÃÂÃÂ¢ÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ the author\'s raw and unedited content as he or she writes ÃÂÃÂÃÂÃÂ¢ÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ so you can take advantage of these','http://akamaicovers.oreilly.com/images/0636920029786/rc_lrg.jpg',650,'O\'Reilly Media',0,0,'Clojure Cookbook',2013,4,0,10),(4,' Bill Burke',0,'Learn how to design and develop distributed web services in Java, using RESTful architectural principles and the JAX-RS 2.0 specification in Java EE 7. By focusing on implementation rather than theory, this hands-on reference demonstrates how easy it is ','http://akamaicovers.oreilly.com/images/0636920028925/lrg.jpg',392,'O\'Reilly Media',0,0,'RESTful Java with JAX-RS 2.0, 2nd Edition',2013,4,0,10),(5,'Stephen Gallaghe',0,'From a basement office in Londonâs notorious Bethlehem Hospital, former policeman and Pinkerton agent Sebastian Becker is sent to interview Sir Owain Lancaster at his country estate. They wealthy industrialist returned alone from a disastrous scientifi','http://ecx.images-amazon.com/images/I/41ldammVtNL._SY344_PJlook-inside-v2,TopRight,1,0_SH20_BO1,204,203,200_.jpg',20,'publish',0,0,' The Bedlam Detective: A Novel',2013,9,0,10);
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
  `issue_date` date DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  `bid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3ft6fv08l2vu9djpg3ltjuhn6` (`bid`),
  KEY `FK_ovmx9ggxtncwm3ad002e6ogny` (`pid`),
  CONSTRAINT `FK_3ft6fv08l2vu9djpg3ltjuhn6` FOREIGN KEY (`bid`) REFERENCES `books` (`bid`),
  CONSTRAINT `FK_ovmx9ggxtncwm3ad002e6ogny` FOREIGN KEY (`pid`) REFERENCES `persons` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booksinuse`
--

LOCK TABLES `booksinuse` WRITE;
/*!40000 ALTER TABLE `booksinuse` DISABLE KEYS */;
INSERT INTO `booksinuse` VALUES (1,'2013-12-20','2014-01-03',3,1),(2,'2013-12-20','2014-01-03',4,1),(6,'2013-12-20','2014-01-03',2,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genres`
--

LOCK TABLES `genres` WRITE;
/*!40000 ALTER TABLE `genres` DISABLE KEYS */;
INSERT INTO `genres` VALUES (4,'programming'),(5,'science'),(6,'medical'),(7,'art'),(8,'imaginative'),(9,'detective');
/*!40000 ALTER TABLE `genres` ENABLE KEYS */;
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
  `sms` tinyint(1) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `timelyreturn` int(11) DEFAULT NULL,
  `untimelyreturn` int(11) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  UNIQUE KEY `UK_1x5aosta48fbss4d5b3kuu0rd` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persons`
--

LOCK TABLES `persons` WRITE;
/*!40000 ALTER TABLE `persons` DISABLE KEYS */;
INSERT INTO `persons` VALUES (1,0,'000000000',0,'admin@mail.com',0,0,'admin','admin',NULL,0,'admin',0,0);
/*!40000 ALTER TABLE `persons` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-12-20 13:07:56
