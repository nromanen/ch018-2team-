-- MySQL dump 10.13  Distrib 5.6.14, for Win64 (x86_64)
--
-- Host: localhost    Database: libraryteam2
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
-- Table structure for table `book_genre`
--

DROP TABLE IF EXISTS `book_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book_genre` (
  `bId` int(11) NOT NULL,
  `gid` int(11) NOT NULL,
  PRIMARY KEY (`bId`,`gid`),
  KEY `FK_k6vmcrjq2ewhkjqy23ovrg24p` (`gid`),
  KEY `FK_m81c7jixhxmkupby2lu57ff82` (`bId`),
  CONSTRAINT `FK_k6vmcrjq2ewhkjqy23ovrg24p` FOREIGN KEY (`gid`) REFERENCES `genretranslations` (`gid`),
  CONSTRAINT `FK_m81c7jixhxmkupby2lu57ff82` FOREIGN KEY (`bId`) REFERENCES `books` (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_genre`
--

LOCK TABLES `book_genre` WRITE;
/*!40000 ALTER TABLE `book_genre` DISABLE KEYS */;
INSERT INTO `book_genre` VALUES (19,6),(30,6),(56,6),(58,6),(61,6),(62,6),(65,6),(16,8),(23,8),(31,8),(35,8),(41,8),(50,8),(51,8),(60,8),(15,10),(20,10),(22,10),(26,10),(27,10),(29,10),(36,10),(39,10),(47,10),(48,10),(54,10),(55,10),(59,10),(63,10),(64,10),(17,12),(18,12),(24,12),(25,12),(28,12),(32,12),(43,12),(44,12),(46,12),(49,12),(53,12),(21,14),(33,14),(34,14),(37,14),(38,14),(40,14),(42,14),(45,14),(52,14),(57,14);
/*!40000 ALTER TABLE `book_genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `authors` varchar(255) NOT NULL,
  `bookcase` int(11) DEFAULT NULL,
  `cur_quantity` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `gen_quantity` int(11) NOT NULL,
  `img` varchar(255) DEFAULT NULL,
  `pages` int(11) NOT NULL,
  `publisher` varchar(255) NOT NULL,
  `shelf` int(11) NOT NULL,
  `term` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `year_public` int(11) NOT NULL,
  `gid` int(11) DEFAULT NULL,
  `arrival_date` datetime DEFAULT NULL,
  PRIMARY KEY (`bid`),
  UNIQUE KEY `UK_5mtto2jcmfrwfg0p1ui8mnweu` (`title`),
  KEY `FK_k00r52dx96mgbrvv8i05saupq` (`gid`),
  CONSTRAINT `FK_k00r52dx96mgbrvv8i05saupq` FOREIGN KEY (`gid`) REFERENCES `genres` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (15,'Richard Warburton',4,3,'With Early Release ebooks, you get books in their earliest form?the author\'s raw and unedited content as he or she writes?so you can take advantage of these technologies long before the official release of these titles. You\'ll also receive updates wh',6,'http://akamaicovers.oreilly.com/images/0636920030713/rc_lrg.jpg',150,' O\'Reilly Media',4,14,'Java 8 Lambdas',2014,3,'2014-10-03 15:00:00'),(16,'Scott Cranton, Jakub Korab',2,1,'',8,'http://akamaicovers.oreilly.com/images/9781782170303/lrg.jpg',424,' Packt Publishing',3,14,'Apache Camel Developer\'s Cookbook',2013,1,'2013-08-01 15:00:00'),(17,'Weinan Li',0,2,'High availability is a system design approach and associated service implementation which ensures that a prearranged level of operational performance will be met during a contractual measurement period. High availability is usually a system combined',4,'http://akamaicovers.oreilly.com/images/9781783282432/lrg.jpg',166,' Packt Publishing',1,14,'JBoss EAP6 High Availability',2013,2,'2013-02-13 15:00:00'),(18,'Scott Oaks',2,2,'With Early Release ebooks, you get books in their earliest form?the author\'s raw and unedited content as he or she writes?so you can take advantage of these technologies long before the official release of these titles. You\'ll also receive updates w',3,'http://akamaicovers.oreilly.com/images/0636920028499/rc_lrg.jpg',500,' O\'Reilly Media',1,14,'Java Performance: The Definitive Guide',2013,2,'2013-09-14 15:00:00'),(19,'Ryan D. Kelker',2,1,'Clojure is a very new and rapidly growing language that runs on top of the JVM. The language being hosted on the Java platform allows for Clojure applications to use existing Java components. Although there are objects in Clojure, the language is no',7,'http://akamaicovers.oreilly.com/images/9781782166504/lrg.jpg',268,' Packt Publishing',0,14,'Clojure for Domain-specific Languages',2013,3,'2013-04-25 15:00:00'),(20,'Dave Klein, Ben Klein',4,1,'Grails is a full-stack web development framework that enables you to build complete web applications in a fraction of the time and with less code than other frameworks. Grails uses the principle of convention over configuration and the dynamic Groov',1,'http://akamaicovers.oreilly.com/images/9781937785772/lrg.jpg',224,' Pragmatic Bookshelf',4,14,'Grails 2: A Quick-Start Guide',2013,3,'2013-03-23 15:00:00'),(21,'Luke VanderHart, Ryan Neufeld',4,1,'With Early Release ebooks, you get books in their earliest form ? the author\'s raw and unedited content as he or she writes ? so you can take advantage of these technologies long before the official release of these titles. You\'ll also receive updat',2,'http://akamaicovers.oreilly.com/images/0636920029786/rc_lrg.jpg',450,' O\'Reilly Media',2,14,'Clojure Cookbook',2013,3,'2013-10-03 15:00:00'),(22,'Jonathan Lermitage',3,4,'JRebel is a JVM plugin that lets Java developers update code instantly and see their changes reflected in their application under development without restarting the application server. JRebel is a plugin for anything that is in Java and is also IDE ',9,'http://akamaicovers.oreilly.com/images/9781849698801/lrg.jpg',50,' Packt Publishing',0,14,'Instant JRebel',2013,1,'2013-06-01 15:00:00'),(23,'Fernando Mayoral',2,7,'Password security is a critical matter when it comes to protecting the interests of application users and their data for a satisfactory user experience. With the advancement in technology, now more than ever, application developers need to be able t',8,'http://akamaicovers.oreilly.com/images/9781849697767/lrg.jpg',40,' Packt Publishing',3,14,'Instant Java Password and Authentication Security',2013,3,'2013-05-07 15:00:00'),(24,'Anjana Mankale',4,0,'Web applications are exposed to a variety of threats and vulnerabilities at the authentication, authorization, service, and domain object levels. Spring Security can help secure these applications against those threats.',1,'http://akamaicovers.oreilly.com/images/9781782167525/lrg.jpg',300,' Packt Publishing',2,14,'Spring Security 3.x Cookbook',2013,3,'2013-10-16 15:00:00'),(25,'NDJOBO Armel Fabrice',4,3,'',4,'http://akamaicovers.oreilly.com/images/9781849699235/lrg.jpg',188,' Packt Publishing',2,14,'Java EE 7 First Look',2013,3,'2013-07-19 15:00:00'),(26,'Rohit Bhat',4,7,'',8,'http://akamaicovers.oreilly.com/images/9781782167082/lrg.jpg',144,' Packt Publishing',4,14,'Bonita Open Solution 5.x Essentials',2013,2,'2013-05-07 15:00:00'),(27,'Bill Burke',0,0,'Learn how to design and develop distributed web services in Java, using RESTful architectural principles and the JAX-RS 2.0 specification in Java EE 7. By focusing on implementation rather than theory, this hands-on reference demonstrates how easy i',4,'http://akamaicovers.oreilly.com/images/0636920028925/lrg.jpg',392,' O\'Reilly Media',2,14,'RESTful Java with JAX-RS 2.0, 2nd Edition',2013,3,'2013-02-01 15:00:00'),(28,'Johan Edstrom, Jamie Goodyear, Heath Kesler',0,1,'',2,'http://akamaicovers.oreilly.com/images/9781782172048/lrg.jpg',128,' Packt Publishing',2,14,'Learning Apache Karaf',2013,3,'2013-07-02 15:00:00'),(29,'Jonathan Lalou',0,8,'In DetailManaging dependencies in a multi-module project is difficult. In a multi-module project, libraries need to share transitive relations with each other. Maven eliminates this need by reading the project files of dependencies to figure out the',10,'http://akamaicovers.oreilly.com/images/9781783283019/lrg.jpg',158,' Packt Publishing',4,14,'Apache Maven Dependency Management',2013,3,'2013-08-04 15:00:00'),(30,'Hudson Orsine Assumpcao',2,1,'IntelliJ IDEA is a commercial Java development tool which competes with the free Eclipse and NetBeans projects. It is an integrated development environment (IDE) designed to automate parts of the coding process; to support a large number of differen',6,'http://akamaicovers.oreilly.com/images/9781849699617/lrg.jpg',114,' Packt Publishing',1,14,'Getting started with IntelliJ IDEA',2013,1,'2013-10-10 15:00:00'),(31,'Masoud Kalali, Bhakti Mehta',3,1,'',3,'http://akamaicovers.oreilly.com/images/9781782178125/lrg.jpg',128,' Packt Publishing',1,14,'Developing RESTful Services with JAX-RS 2.0, WebSockets, and JSON',2013,3,'2013-10-06 15:00:00'),(32,'Burd',2,2,'Learn to:',3,'http://akamaicovers.oreilly.com/images/9781118504383/lrg.jpg',456,' Wiley / For Dummies',0,14,'Java Programming for Android Developers For Dummies',2013,2,'2013-07-24 15:00:00'),(33,'Elliotte Rusty Harold',1,0,'This practical guide provides a complete introduction to developing network programs with Java. You?ll learn how to use Java?s network class library to quickly and easily accomplish common networking tasks such as writing multithreaded servers, encr',1,'http://akamaicovers.oreilly.com/images/0636920028420/lrg.jpg',502,' O\'Reilly Media',2,14,'Java Network Programming, 4th Edition',2013,1,'2013-08-25 15:00:00'),(34,'Hussain Pithawala',0,2,'Google Guice is an open source software framework for the Java platform released by Google under the Apache License. It provides support for dependency injection using annotations to configure Java objects.',8,'http://akamaicovers.oreilly.com/images/9781783281893/lrg.jpg',132,' Packt Publishing',4,14,'Learning Google Guice',2013,2,'2013-04-06 15:00:00'),(35,'Evgeniy Sharapov',4,5,'There are many ways to handle data exchange between applications and systems. Apache Camel is a lightweight integration framework that trades in complexity to focus on simplicity, elegance, and flexibility instead. This book teaches you to handle da',6,'http://akamaicovers.oreilly.com/images/9781782165347/lrg.jpg',78,' Packt Publishing',2,14,'Instant Apache Camel Messaging System',2013,2,'2013-04-09 15:00:00'),(36,'Jurij Laznik',4,3,'The Business Process Execution Language (BPEL) has become the de-facto standard for orchestrating web services. BPEL and web services are both clamped into Service-oriented Architecture (SOA). Development of efficient SOA composites too often requir',9,'http://akamaicovers.oreilly.com/images/9781849689205/lrg.jpg',382,' Packt Publishing',4,14,'BPEL and Java Cookbook',2013,3,'2013-07-15 15:00:00'),(37,'Nicolas Frankel',1,0,'Vaadin is a new Java web framework for making applications look great and perform well, making your users happy. Vaadin promises to make your user interfaces attractive and usable while easing your development efforts and boosting your productivity ',5,'http://akamaicovers.oreilly.com/images/9781782169772/lrg.jpg',436,' Packt Publishing',0,14,'Learning Vaadin 7: Second Edition',2013,2,'2013-09-23 15:00:00'),(38,'Paul Bakker, Bert Ertman',1,0,'If you?re an experienced Java developer in the enterprise, this practical, hands-on book shows you how to use OSGi to design, develop, and deploy modular cloud applications. You?ll quickly learn how to use OSGi, through concise code examples and a s',1,'http://akamaicovers.oreilly.com/images/0636920028086/lrg.jpg',210,' O\'Reilly Media',3,14,'Building Modular Cloud Apps with OSGi',2013,3,'2013-09-19 15:00:00'),(39,'Geoff Chiang',1,1,'The Spring framework has become ubiquitous in modern Java enterprise application development, providing framework solutions for every application layer. Spring Tool Suite combines the capabilities of the Spring framework with the power of the Eclips',10,'http://akamaicovers.oreilly.com/images/9781782164142/lrg.jpg',76,' Packt Publishing',3,14,'Instant Spring Tool Suite',2013,1,'2013-09-24 15:00:00'),(40,'Martin Kalin',2,4,'Learn how to develop REST-style and SOAP-based web services and clients with this quick and thorough introduction. This hands-on book delivers a clear, pragmatic approach to web services by providing an architectural overview, complete working code ',5,'http://akamaicovers.oreilly.com/images/0636920029571/lrg.jpg',360,' O\'Reilly Media',4,14,'Java Web Services: Up and Running, 2nd Edition',2013,1,'2013-09-06 15:00:00'),(41,'Mat Johns',2,3,'Applications have become increasing agile and dynamic, reacting to the demands of their users except at the data layer. Hazelcast offers a new and innovative approach to data scalability. Unlike many of its competitors, its in-memory counterparts di',6,'http://akamaicovers.oreilly.com/images/9781782167303/lrg.jpg',136,' Packt Publishing',0,14,'Getting Started with Hazelcast',2013,2,'2013-07-20 15:00:00'),(42,'Ryan Mitchell',0,0,'Java is often thought of as a stuffy enterprise language, while web scraping is the often-murky domain of scripting languages. By combining the robustness and extensibility of Java with the flexibility and power of web scraping, we can create immens',8,'http://akamaicovers.oreilly.com/images/9781849696883/lrg.jpg',72,' Packt Publishing',3,14,'Instant Web Scraping with Java',2013,3,'2013-02-21 15:00:00'),(43,'Simone Scarduzio',0,0,'In traditional concurrent programming, it?s hard to achieve accuracy and even harder to achieve performance. vert.x has a revolutionary approach that takes care of all the thread orchestration, message passing, and shared data in the framework. Dead',1,'http://akamaicovers.oreilly.com/images/9781783282913/lrg.jpg',54,' Packt Publishing',0,14,'Instant Vert.x',2013,2,'2013-04-21 15:00:00'),(44,'Sandeep Kumar Patel',3,3,'GSON is a specialized Java-based library for handling JSON data, developed by Google. GSON demonstrates an efficient use of CPU time, memory efficiency, the library, and developer productivity. This book will help you implement GSON in your Java pro',7,'http://akamaicovers.oreilly.com/images/9781783282036/lrg.jpg',60,' Packt Publishing',2,14,'Instant GSON',2013,2,'2013-06-10 15:00:00'),(45,'Felipe Fedel Pinto, Joao Savio Ceregatti Longo',2,1,'Wicket is a component-based Java web framework. In Wicket, the server side state is automatically managed as it is associated with components. With proper mark-up/logic separation, a POJO data model, and a refreshing lack of XML, Apache Wicket makes',4,'http://akamaicovers.oreilly.com/images/9781783280018/lrg.jpg',54,' Packt Publishing',2,14,'Instant Apache Wicket 6',2013,2,'2013-05-04 15:00:00'),(46,'Zakir Laliwala, Abdul Samad, Azaz Desai, Uchit Vyas',3,2,'Mule ESB is a lightweight Java-based enterprise service bus (ESB) and integration platform that allows developers to connect applications together quickly and easily, enabling them to efficiently exchange data. You can therefore use Mule ESB to allo',3,'http://akamaicovers.oreilly.com/images/9781782164401/lrg.jpg',428,' Packt Publishing',3,14,'Mule ESB Cookbook',2013,1,'2013-10-14 15:00:00'),(47,'Jamie Allen',4,1,'Avoid common mistakes when building distributed, asynchronous, high-performance software with the Akka toolkit and runtime. With this concise guide, author Jamie Allen provides a collection of best practices based on several years of using the actor',4,'http://akamaicovers.oreilly.com/images/0636920028789/lrg.jpg',74,' O\'Reilly Media',3,14,'Effective Akka',2013,1,'2013-05-02 15:00:00'),(48,'Bill Bejeck',1,0,'Java continues to maintain its popularity and is still one of the main languages used in the software industry today. But there are things in Java that are difficult to do that can be made easier; that?s where Guava comes in. Guava provides develope',3,'http://akamaicovers.oreilly.com/images/9781783280155/lrg.jpg',142,' Packt Publishing',0,14,'Getting started with Google Guava',2013,2,'2013-09-14 15:00:00'),(49,'Arun Gupta',0,3,'Get up to speed on the principal technologies in the Java Platform, Enterprise Edition 7, and learn how the latest version embraces HTML5, focuses on higher productivity, and provides functionality to meet enterprise demands. Written by Arun Gupta, ',10,'http://akamaicovers.oreilly.com/images/0636920030614/lrg.jpg',362,' O\'Reilly Media',1,14,'Java EE 7 Essentials',2013,2,'2013-03-19 15:00:00'),(50,'Elliotte Rusty Harold',0,0,'Send and receive email from Java applications by using the JavaMail API. With this concise book, you?ll learn how to communicate with existing SMTP, POP, and IMAP servers, and how to write your own.',1,'http://akamaicovers.oreilly.com/images/0636920030133/lrg.jpg',98,' O\'Reilly Media',3,14,'JavaMail API',2013,2,'2013-06-21 15:00:00'),(51,'Rodrigo Fraxino Araujo, Vinicius H. S. Durelli, Rafael Medeiros Teixeira',0,2,'Integrated Development Environments (IDEs) such as Eclipse are examples of tools that help developers by automating an assortment of software development-related tasks. By reading this book you will learn how to get Eclipse to automate common develo',8,'http://akamaicovers.oreilly.com/images/9781782160946/lrg.jpg',256,' Packt Publishing',2,14,'Getting Started with Eclipse Juno',2013,3,'2013-08-22 15:00:00'),(52,'Robert Liguori, Patricia Liguori',1,1,'When you need quick answers for developing or debugging Java programs, this pocket guide provides a handy reference to the standard features of the Java programming language and its platform. You?ll find helpful programming examples, tables, figures',5,'http://akamaicovers.oreilly.com/images/0636920027829/lrg.jpg',216,' O\'Reilly Media',1,14,'Java 7 Pocket Guide, 2nd Edition',2013,1,'2013-02-25 15:00:00'),(53,'Patrick Niemeyer, Daniel Leuck',1,2,'Java is the preferred language for many of today?s leading-edge technologies?everything from smartphones and game consoles to robots, massive enterprise systems, and supercomputers. If you?re new to Java, the fourth edition of this bestselling guide',4,'http://akamaicovers.oreilly.com/images/0636920023463/lrg.jpg',1010,' O\'Reilly Media',0,14,'Learning Java, 4th Edition',2013,3,'2013-03-25 15:00:00'),(54,'Pete Houston',2,5,'In Detail',10,'http://akamaicovers.oreilly.com/images/9781782167990/lrg.jpg',38,' Packt Publishing',3,14,'Instant jsoup How-to',2013,3,'2013-07-20 15:00:00'),(55,'Henryk Konsek',2,0,'In Detail',5,'http://akamaicovers.oreilly.com/images/9781849519663/lrg.jpg',66,' Packt Publishing',0,14,'Instant Apache ServiceMix How-to',2013,3,'2013-06-18 15:00:00'),(56,'Michal Bali',0,1,'In Detail',3,'http://akamaicovers.oreilly.com/images/9781782161264/lrg.jpg',338,' Packt Publishing',2,14,'Drools JBoss Rules 5.X Developer\'s Guide',2013,3,'2013-10-06 15:00:00'),(57,'Daniel Dietrich',2,0,'Play is a full-featured Java and Scala web framework for building modern, high-performance web applications. It is characterized by its simplicity and scalability. With its lightweight, stateless, and web-friendly architecture, Play focuses on devel',3,'http://akamaicovers.oreilly.com/images/9781782162902/lrg.jpg',70,' Packt Publishing',2,14,'Instant Play Framework Starter',2013,3,'2013-03-18 15:00:00'),(58,'Timothy Bish',1,2,'Apache ActiveMQ is a powerful and popular open source messaging and Integration Patterns server. ActiveMQ is a fully JMS 1.1 compliant Message Broker and supports many advanced features beyond the JMS specification.',9,'http://akamaicovers.oreilly.com/images/9781782169413/lrg.jpg',78,' Packt Publishing',0,14,'Instant Apache ActiveMQ Messaging Application Development How-to',2013,2,'2013-03-01 15:00:00'),(59,'Ram Kulkarni',0,2,'Standalone desktop applications are important in this era of web and mobile applications. Eclipse provides a rich set of APIs and tools, not just to create IDEs but also to create cross-platform standalone applications. Eclipse 4 has introduced many',8,'http://akamaicovers.oreilly.com/images/9781782169529/lrg.jpg',68,' Packt Publishing',1,14,'Instant Eclipse 4 RCP Development How-to',2013,2,'2013-02-02 15:00:00'),(60,'Anatoly Spektor',3,2,'In Detail',5,'http://akamaicovers.oreilly.com/images/9781782163244/lrg.jpg',62,' Packt Publishing',2,14,'Instant Eclipse Application Testing How-to',2013,3,'2013-09-03 15:00:00'),(61,'Steve Perkins',0,7,'Users expect software to be highly intelligent when searching data. Searches should span across multiple data points at once, and be able to spot patterns and groupings in the results found. Searches should be able to fix user typos, and use terms r',9,'http://akamaicovers.oreilly.com/images/9781849519205/lrg.jpg',148,' Packt Publishing',2,14,'Hibernate Search by Example',2013,3,'2013-04-08 15:00:00'),(62,'Jeremy Ary',4,1,'Drools is a popular business rule management system. The book introduces the concept of rules separation, from what to do to how to do it. This Starter guide supports your development to keep pace with your system\'s ever-changing needs, making thing',3,'http://akamaicovers.oreilly.com/images/9781782165545/lrg.jpg',52,' Packt Publishing',3,14,'Instant Drools Starter',2013,2,'2013-08-03 15:00:00'),(63,'Atul Palandurkar',4,0,'NetBeans is an open source IDE which is much more powerful than any other IDE available on the market for Java application development. It allows you to write and generate smart code, and utilize drag-and-drop tools. NetBeans gives complete flexibil',2,'http://akamaicovers.oreilly.com/images/9781782163442/lrg.jpg',70,' Packt Publishing',1,14,'Instant NetBeans IDE How-to',2013,3,'2013-03-15 15:00:00'),(64,'Jamie Goodyear, Johan Edstrom',1,2,'OSGi is a tried and true modularity standard for Java. It has in recent years gained a lot of traction and tooling; becoming frequently used in Enterprise containers and distributed software systems.',3,'http://akamaicovers.oreilly.com/images/9781849519922/lrg.jpg',58,' Packt Publishing',2,14,'Instant OSGi Starter',2013,3,'2013-01-24 15:00:00'),(65,'Oleg Varaksin, Mert Caliskan',2,7,'PrimeFaces is the de facto standard in the Java web development. PrimeFaces is a lightweight library with one jar, zero-configuration, and no required dependencies. You just need to download PrimeFaces, add the primefaces-{version}.jar to your class',8,'http://akamaicovers.oreilly.com/images/9781849519281/lrg.jpg',328,' Packt Publishing',4,14,'PrimeFaces Cookbook',2013,2,'2013-07-17 15:00:00');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booksinuse`
--

LOCK TABLES `booksinuse` WRITE;
/*!40000 ALTER TABLE `booksinuse` DISABLE KEYS */;
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
INSERT INTO `genres` VALUES (1,'CS'),(2,'Social'),(3,'Imaginative'),(4,'Science'),(5,'Art');
/*!40000 ALTER TABLE `genres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genretranslations`
--

DROP TABLE IF EXISTS `genretranslations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genretranslations` (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `genreId` int(11) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `langId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`gid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genretranslations`
--

LOCK TABLES `genretranslations` WRITE;
/*!40000 ALTER TABLE `genretranslations` DISABLE KEYS */;
INSERT INTO `genretranslations` VALUES (5,1,'Інформатика','ua'),(6,1,'Computer Science','en'),(7,2,'Художня література','ua'),(8,2,'Imaginative','en'),(9,3,'Наукова література','ua'),(10,3,'Science','en'),(11,4,'Мистецтво','ua'),(12,4,'Art','en'),(13,5,'Історична література','ua'),(14,5,'Historical','en');
/*!40000 ALTER TABLE `genretranslations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `languages`
--

DROP TABLE IF EXISTS `languages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `languages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `language` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `languages`
--

LOCK TABLES `languages` WRITE;
/*!40000 ALTER TABLE `languages` DISABLE KEYS */;
/*!40000 ALTER TABLE `languages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `changed` tinyint(1) DEFAULT NULL,
  `daysamount` bigint(20) DEFAULT NULL,
  `order_date` datetime DEFAULT NULL,
  `bid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bm2fin7b72b3xtjojm9da9kj6` (`pid`,`bid`),
  KEY `FK_71a4vlhrltibatg60nyuxpith` (`bid`),
  KEY `FK_hq80wuvwadnmgmjkn20xeqt2k` (`pid`),
  CONSTRAINT `FK_71a4vlhrltibatg60nyuxpith` FOREIGN KEY (`bid`) REFERENCES `books` (`bid`),
  CONSTRAINT `FK_hq80wuvwadnmgmjkn20xeqt2k` FOREIGN KEY (`pid`) REFERENCES `persons` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,0,14,'2014-03-03 14:00:00',20,30),(2,0,3,'2014-02-28 14:00:00',20,31);
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
  `booksallowed` int(11) NOT NULL,
  `cellphone` varchar(255) NOT NULL,
  `confirm` tinyint(1) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `failedorders` int(11) DEFAULT NULL,
  `generalratio` double NOT NULL,
  `img` varchar(255) DEFAULT NULL,
  `mailconfirm` tinyint(1) DEFAULT NULL,
  `mailkey` varchar(255) DEFAULT NULL,
  `multi` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `prole` varchar(255) DEFAULT NULL,
  `sms` tinyint(1) DEFAULT NULL,
  `surname` varchar(255) NOT NULL,
  `timelyreturn` int(11) DEFAULT NULL,
  `untimelyreturn` int(11) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  UNIQUE KEY `UK_1x5aosta48fbss4d5b3kuu0rd` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persons`
--

LOCK TABLES `persons` WRITE;
/*!40000 ALTER TABLE `persons` DISABLE KEYS */;
INSERT INTO `persons` VALUES (1,10,'958-525-4665',0,'confirm5@mail.com',0,0,NULL,1,NULL,9,'name','$2a$10$srYyv.Dsf1e.6adEhyrOF.a7oyFwoMeIJEJn.YILzyiwh5rkzpxKi','ROLE_USER',1,'admin',0,0),(2,10,'333-333-3333',0,'longlonglonglong@mail.com',0,0,NULL,1,NULL,10,'longh','$2a$10$aQaG49Hv5dSaKYERXEF1Ne5KA.h/FhAKtJSZYIuqjBgKqO6RX/1TC','ROLE_USER',0,'long',0,0),(3,10,'333-333-3333',0,'conc@mail.com',0,0,NULL,1,NULL,10,'concurency','$2a$10$Ksx.fOC7PqoCIyJpwZkBWOeiCgoD3zcIyzt4booV/uk/xVETOMn6u','ROLE_USER',0,'concurency',1,0),(4,10,'363-333-3333',0,'lib@mail.com',0,0,NULL,1,NULL,10,'lib','$2a$10$ptMrkaSED..E/QBJxWpSsO.HGJwYMd2YOsDNSsOSieYdcq6VP7jMm','ROLE_LIBRARIAN',0,'lib',0,0),(5,10,'546-985-8554',0,'tet@mail.com',0,0,NULL,1,NULL,10,'tetso6','$2a$10$b9lbPIZc8jLAEWBYNC2y5OEcQOYtHLxaetLI8Xdf9KsUsK87VKlOy','ROLE_USER',1,'tetso',0,0),(6,10,'326-325-1412',0,'auto@mail.com',0,0,NULL,0,'6ea91b0287953859863a2c537e987b0ab70408d3',10,'auto','$2a$10$Q1E6YskEI4.JXuKaAV1WcOQccziq9fImOg6qj/nJHt0Vl3Ka75Q2u','ROLE_USER',0,'auto',0,0),(7,10,'362-256-8445',0,'auto1@mail.com',0,0,NULL,0,'228c0552bc53b26916905180a74d906fd94bc482',10,'auto1','$2a$10$FVzXNfVI2kfHSwnma6h7wevq4d4i5K0WLbgpa9Zu6gjFrYB/us7oK','ROLE_USER',0,'auto1',0,0),(8,10,'564-641-4644',0,'auto2@mail.com',0,0,NULL,0,'9fee68d8832eeb35cfc770f8afedbb1f8241048c',10,'auto2','$2a$10$oInJVzkCmEQxjj8/lBSlGupgADPhuEpzHQxgIQoVKnIT./i1mch.6','ROLE_USER',0,'auto2',0,0),(9,10,'584-584-7849',0,'auto4@mail.com',0,0,NULL,0,'c6c7470f574854de98ea254f064ee235ecb0212a',10,'auto4','$2a$10$UrJeN91bT50GIOIryAXWuuGX7Ag5C0bdwqHVYyNI/oVPbGxvKlqGW','ROLE_USER',0,'auto4',0,0),(10,10,'351-611-1141',0,'auto5@mail.com',0,0,NULL,0,'ac7c9a8f28123040afb8d5e3a2667dc2c89580ca',10,'auto5','$2a$10$/7W9FRpAFn.qytcTjQFwAeGLmLDGOrQ3VE74YkXf7squiU3Wx3i9C','ROLE_USER',0,'auto5',0,0),(11,10,'356-561-4444',0,'auto6@mail.com',0,0,NULL,1,NULL,10,'auto6@mail.com','$2a$10$.JqPS59ztoLv8urlFa7EvO4UqYqna70kHczrG.AU6XIbs0bOnsiNK','ROLE_USER',0,'auto6@mail.com',0,0),(12,10,'351-615-6161',0,'auto7@mail.com',0,0,NULL,0,'926a76ebddb836687c508a9b4cfe5eaeab681aed',10,'auto6','$2a$10$gnoQ5vlahWGXn8tGc3WlVuAC.GLl3/5WNHjCtb1I3z2s5wDcSD9Xq','ROLE_USER',0,'auto6',0,0),(13,10,'356-146-1611',0,'auto10@mail.com',0,0,NULL,0,'38dddcb604e835451c395c675071d8e36b17f725',10,'auto10','$2a$10$L3UZ4CbGDD6rjVPwHJHgnuYu8f.rkME4pPU1dtvJ4Cr8FfJ0J6UzG','ROLE_USER',0,'auto10',0,0),(14,10,'365-235-5569',0,'some@mail.com',0,0,NULL,0,'dee8e7bb3d1a3ad294bb17cba756b24add0593a1',10,'some','$2a$10$dm1UdAHTDRfnoiHG0qsgSeDJDBGyOtlozPb2KQxUsPoMQPmPgm5Jm','ROLE_USER',0,'some',0,0),(15,10,'466-746-8746',0,'some1@mail.com',0,0,NULL,0,'aded7edfc61771fb8a033d955d7a5e5c87e050a2',10,'some1','$2a$10$Yv/9cjOINsGa5vXWtGuifOzXRpFuRVfO4ckaZSLA9LUz1s6aE6/7.','ROLE_USER',0,'some1',0,0),(16,10,'456-464-6464',0,'some2@mail.com',0,0,NULL,1,NULL,10,'some2','$2a$10$V8yJGI2Kp34tjF713nrQ4.gAQ65ml1f4ZGstyL5iFzRSOG5/V.Cf2','ROLE_USER',0,'some2',0,0),(17,10,'356-566-4844',0,'some3@mail.com',0,0,NULL,1,NULL,10,'some3','$2a$10$OEV1SNVFF5QxiBRFAVJc8.lCRbD07qWiHNlXnC6yULRrSSvndpMzq','ROLE_USER',0,'some3',0,0),(18,10,'546-444-6464',0,'some4@mail.com',0,0,NULL,1,NULL,10,'some4','$2a$10$Q8R55w9T7pee0icf0o7Oi.tCRM2y0VZHhDkc05IRkiItdbAaL8gO2','ROLE_USER',0,'some4',0,0),(19,10,'355-322-3666',0,'some5@mail.com',0,0,NULL,1,NULL,10,'some5','$2a$10$s06yejqd/DILT3QLucgD7un6M01EVVW45oupcnCAjb5jTGDI0yXru','ROLE_USER',0,'some5',0,0),(20,10,'256-654-6965',0,'some7@mail.com',0,0,NULL,1,NULL,10,'some6','$2a$10$KT39aps81XtGER52ChHiJeWAjuAT2tKVDctYzT9SFHxxN1AJc79.y','ROLE_USER',0,'some6',0,0),(21,10,'754-484-4464',0,'some9@mail.com',0,0,NULL,1,NULL,10,'some9','$2a$10$iQkjd4hy/oHpn7lJjwl2peC/.sZubF4DbKcBiLe8edYVG2Z1X1whO','ROLE_USER',0,'some9',0,0),(22,10,'546-646-4646',0,'some10@mail.com',0,0,NULL,1,NULL,9,'some10','$2a$10$R2a7aNOkmPdMWVhX7eF0kOc8XNZI3YnUyMOvyFfxu5wRZL.YYD8ZK','ROLE_USER',1,'so',0,0),(23,10,'464-161-8681',0,'corr@mail.com',0,0,NULL,0,'0eb5b12951aaff98b9bbd81172a8ee9dde52cf53',10,'corr','$2a$10$yqHsqaivLd7hGzKW5NQwBeuqsw2jQm9Un19l1rw1f/ExqB1Scz7WC','ROLE_USER',0,'corr',0,0),(24,10,'351-616-1681',0,'corr2@mail.com',0,0,NULL,0,'54dc1a2919368e496c7f381a9e7f6e638202fea5',10,'corr','$2a$10$7ieXh.FIViS2W53.oUcSneDnOH0OUS6S/Y700.x.aaIZIJNK43rcm','ROLE_USER',0,'corr',0,0),(25,10,'466-161-6161',0,'corr3@mail.com',0,0,NULL,0,'71ad009a52b0bc77131689b925d6a6de2afc7175',10,'corr','$2a$10$diTinRzbKy/evjKzTfE4w.R2ghvguz3LsCgUm90r.8EM878ywSbFa','ROLE_USER',0,'corr',0,0),(26,10,'351-611-6116',0,'corr4@mail.com',0,0,NULL,1,'83d8d13ccc473c68060dd6d2ec017b60687fbdd5',10,'corr','$2a$10$uBnB/ekL5vBbxzx.Z6RHqu7AP5Nn0B.VN0GVXvSG9tvIK2mTdnVtW','ROLE_LIBRARIAN',0,'corr',0,0),(27,10,'464-641-6645',0,'corr5@mail.com',0,0,NULL,1,NULL,10,'corr','$2a$10$H9FlcOI.RvN5enuh2vbf0OZp4XV4FsJb5EORg5wncq0hMPep7xh5.','ROLE_USER',0,'corr',0,0),(28,10,'564-464-9687',0,'user22@mail.com',0,0,NULL,1,NULL,10,'user22','$2a$10$i95TlcrLRFfCIACdCZxf2.ziwsk6zXxfoxaAqaoPz3WKe0C0c6AZ.','ROLE_USER',0,'user22',0,0),(29,10,'564-664-6464',0,'user5@mail.com',0,0,NULL,1,NULL,10,'user5','$2a$10$c3aqReJNuLbtLjAFLIbj2uLWJMwj16S7boVJlgsV/mca1W1lR21gW','ROLE_USER',0,'user5',0,0),(30,10,'256-446-4646',0,'user1@mail.com',0,0,NULL,1,NULL,10,'user1','$2a$10$9NIHmZZbj9rMxva6aQCIDeRp/BHqPPuJSH0zpG4prFXO8FMAukQ/.','ROLE_USER',0,'user1',0,0),(31,10,'754-446-4646',0,'user2@mail.com',0,0,NULL,1,NULL,10,'user2','$2a$10$Jc4PvJ8/6uDZKI2f3YMRT.IMJxRdRPiuEaFlrC95Ega9bLfJJSUK6','ROLE_USER',0,'user2',0,0),(32,10,'584-564-4445',0,'user3@mail.com',0,0,NULL,1,NULL,10,'user3','$2a$10$Sp2YwpQhQVDviJO6O8ZpD.6VWr1734zmCoHhOdaUVOv/LF4.0uPsq','ROLE_USER',0,'user3',1,0),(33,10,'216-546-4664',0,'user4@mail.com',0,0,NULL,1,NULL,10,'user4','$2a$10$a6CVrDe4t04NXvtEzhOS8usN4hHrwjP0AzFS5WuGDNIpkB2qMST4.','ROLE_USER',0,'user4',0,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
INSERT INTO `wishlist` VALUES (1,16,29);
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

-- Dump completed on 2014-01-27 15:06:17
