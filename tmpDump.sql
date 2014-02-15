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
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `arrival_date` datetime DEFAULT NULL,
  `authors` varchar(255) DEFAULT NULL,
  `bookcase` int(11) DEFAULT NULL,
  `cur_quantity` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `gen_quantity` int(11) NOT NULL,
  `img` varchar(255) DEFAULT NULL,
  `pages` int(11) NOT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `shelf` int(11) NOT NULL,
  `term` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `year_public` int(11) NOT NULL,
  `orders_quantity` int(11) DEFAULT NULL,
  `gid` int(11) DEFAULT NULL,
  `rating` float DEFAULT NULL,
  `votes` int(11) DEFAULT NULL,
  PRIMARY KEY (`bid`),
  UNIQUE KEY `UK_5mtto2jcmfrwfg0p1ui8mnweu` (`title`),
  KEY `FK_k00r52dx96mgbrvv8i05saupq` (`gid`),
  CONSTRAINT `FK_k00r52dx96mgbrvv8i05saupq` FOREIGN KEY (`gid`) REFERENCES `genres` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'2014-06-09 12:00:00','Ian F. Darwin',2,2,'With Early Release ebooks, you get books in their earliest form—the author\'s raw and unedited content as he or she writes—so you can take advantage of these technologies long before the official release of these titles. You\'ll also receive updates w',4,'resources/img/69200265160.jpg',850,' O\'Reilly Media',4,14,'Java Cookbook',2014,62,1,4,4),(2,'2014-02-09 12:00:00','Richard Warburton',2,2,'With Early Release ebooks, you get books in their earliest form—the author\'s raw and unedited content as he or she writes—so you can take advantage of these technologies long before the official release of these titles. You\'ll also receive updates w',2,'resources/img/69200307169.jpg',150,' O\'Reilly Media',3,14,'Java 8 Lambdas',2014,12,1,3.66667,3),(3,'2013-05-05 12:00:00','Scott Cranton, Jakub Korab',4,2,'',2,'resources/img/97817821753.jpg',424,' Packt Publishing',5,14,'Apache Camel Developer\'s Cookbook',2013,10,1,3,1),(4,'2013-06-04 12:00:00','Weinan Li',2,3,'High availability is a system design approach and associated service implementation which ensures that a prearranged level of operational performance will be met during a contractual measurement period. High availability is usually a system combined',3,'resources/img/97817832882.jpg',166,' Packt Publishing',1,14,'JBoss EAP6 High Availability',2013,5,1,0,0),(5,'2013-09-10 12:00:00','Scott Oaks',3,9,'With Early Release ebooks, you get books in their earliest form—the author\'s raw and unedited content as he or she writes—so you can take advantage of these technologies long before the official release of these titles. You\'ll also receive updates w',9,'resources/img/69200284999.jpg',500,' O\'Reilly Media',4,14,'Java Performance: The Definitive Guide',2013,1,1,0,0),(6,'2013-09-03 12:00:00','Ryan D. Kelker',4,2,'Clojure is a very new and rapidly growing language that runs on top of the JVM. The language being hosted on the Java platform allows for Clojure applications to use existing Java components. Although there are objects in Clojure, the language is no',2,'resources/img/97817821664.jpg',268,' Packt Publishing',1,14,'Clojure for Domain-specific Languages',2013,5,1,5,1),(7,'2013-04-10 12:00:00','Dave Klein, Ben Klein',2,5,'Grails is a full-stack web development framework that enables you to build complete web applications in a fraction of the time and with less code than other frameworks. Grails uses the principle of convention over configuration and the dynamic Groov',9,'resources/img/97819377853.jpg',224,' Pragmatic Bookshelf',1,14,'Grails 2: A Quick-Start Guide',2013,8,1,0,0),(8,'2013-10-09 12:00:00','Luke VanderHart, Ryan Neufeld',5,3,'With Early Release ebooks, you get books in their earliest form — the author\'s raw and unedited content as he or she writes — so you can take advantage of these technologies long before the official release of these titles. You\'ll also receive updat',6,'resources/img/69200297884.jpg',450,' O\'Reilly Media',5,14,'Clojure Cookbook',2013,17,1,3.5,1),(9,'2013-07-09 12:00:00','Fernando Mayoral',1,1,'Password security is a critical matter when it comes to protecting the interests of application users and their data for a satisfactory user experience. With the advancement in technology, now more than ever, application developers need to be able t',1,'resources/img/97818496930.jpg',40,' Packt Publishing',3,14,'Instant Java Password and Authentication Security',2013,3,1,0,0),(10,'2013-03-02 12:00:00','Jonathan Lermitage',5,1,'JRebel is a JVM plugin that lets Java developers update code instantly and see their changes reflected in their application under development without restarting the application server. JRebel is a plugin for anything that is in Java and is also IDE ',3,'resources/img/978184969104.jpg',50,' Packt Publishing',1,14,'Instant JRebel',2013,1,1,0,0),(11,'2014-02-10 00:10:55','author',1,1,'Web applications are exposed to a variety of threats and vulnerabilities at the authentication, authorization, service, and domain object levels. Spring Security can help secure these applications against those threats.',4,'resources/img/97817821655.jpg',300,' Packt Publishing',1,14,'Spring Security 3.x Cookbook',2013,13,1,3.8,5),(12,'2013-04-05 12:00:00','NDJOBO Armel Fabrice',5,5,'',10,'resources/img/97818496964.jpg',188,' Packt Publishing',3,14,'Java EE 7 First Look',2013,3,1,0,0),(13,'2013-09-10 12:00:00','Rohit Bhat',3,3,'',7,'resources/img/978178216104.jpg',144,' Packt Publishing',1,14,'Bonita Open Solution 5.x Essentials',2013,6,1,3.75,2),(14,'2014-02-10 00:40:37','Bill Burke',1,1,'Learn how to design and develop distributed web services in Java, using RESTful architectural principles and the JAX-RS 2.0 specification in Java EE 7. By focusing on implementation rather than theory, this hands-on reference demonstrates how easy i',8,'resources/img/06369200245.jpg',500,' O\'Reilly Media',5,14,'RESTful Java with JAX-RS 2.0, 2nd Edition',2014,4,2,3.41667,6),(15,'2013-06-02 12:00:00','Jonathan Lalou',3,8,'In DetailManaging dependencies in a multi-module project is difficult. In a multi-module project, libraries need to share transitive relations with each other. Maven eliminates this need by reading the project files of dependencies to figure out the',9,'resources/img/97817832837.jpg',158,' Packt Publishing',4,14,'Apache Maven Dependency Management',2013,8,1,0,0),(16,'2013-10-05 12:00:00','Johan Edstrom, Jamie Goodyear, Heath Kesler',1,2,'',6,'resources/img/97817821749.jpg',128,' Packt Publishing',4,14,'Learning Apache Karaf',2013,3,1,0,0),(17,'2013-03-08 12:00:00','Hudson Orsine Assumpcao',3,1,'IntelliJ IDEA is a commercial Java development tool which competes with the free Eclipse and NetBeans projects. It is an integrated development environment (IDE) designed to automate parts of the coding process; to support a large number of differen',6,'resources/img/97818496949.jpg',114,' Packt Publishing',5,14,'Getting started with IntelliJ IDEA',2013,5,1,0,0),(18,'2013-07-09 12:00:00','Masoud Kalali, Bhakti Mehta',5,4,'',8,'resources/img/978178217106.jpg',128,' Packt Publishing',3,14,'Developing RESTful Services with JAX-RS 2.0, WebSockets, and JSON',2013,4,1,3,1),(19,'2013-08-04 12:00:00','Burd',3,4,'Learn to:',5,'resources/img/97811185073.jpg',456,' Wiley / For Dummies',3,14,'Java Programming for Android Developers For Dummies',2013,5,1,0,0),(20,'2013-07-10 12:00:00','Elliotte Rusty Harold',1,4,'This practical guide provides a complete introduction to developing network programs with Java. You’ll learn how to use Java’s network class library to quickly and easily accomplish common networking tasks such as writing multithreaded servers, encr',9,'resources/img/06369200285.jpg',502,' O\'Reilly Media',5,14,'Java Network Programming, 4th Edition',2013,0,1,0,0),(21,'2013-08-10 12:00:00','Hussain Pithawala',4,1,'Google Guice is an open source software framework for the Java platform released by Google under the Apache License. It provides support for dependency injection using annotations to configure Java objects.',7,'resources/img/97817832883.jpg',132,' Packt Publishing',2,14,'Learning Google Guice',2013,3,1,0,0),(22,'2013-05-03 12:00:00','Evgeniy Sharapov',2,6,'There are many ways to handle data exchange between applications and systems. Apache Camel is a lightweight integration framework that trades in complexity to focus on simplicity, elegance, and flexibility instead. This book teaches you to handle da',6,'resources/img/97817821671.jpg',78,' Packt Publishing',2,14,'Instant Apache Camel Messaging System',2013,5,1,0,0),(23,'2013-10-06 12:00:00','Jurij Laznik',4,7,'The Business Process Execution Language (BPEL) has become the de-facto standard for orchestrating web services. BPEL and web services are both clamped into Service-oriented Architecture (SOA). Development of efficient SOA composites too often requir',7,'resources/img/97818496878.jpg',382,' Packt Publishing',5,14,'BPEL and Java Cookbook',2013,15,1,4,1),(24,'2013-02-09 12:00:00','Nicolas Frankel',3,9,'Vaadin is a new Java web framework for making applications look great and perform well, making your users happy. Vaadin promises to make your user interfaces attractive and usable while easing your development efforts and boosting your productivity ',9,'resources/img/97817821664.jpg',436,' Packt Publishing',1,14,'Learning Vaadin 7: Second Edition',2013,9,1,0,0),(25,'2013-03-07 12:00:00','Paul Bakker, Bert Ertman',1,5,'If you’re an experienced Java developer in the enterprise, this practical, hands-on book shows you how to use OSGi to design, develop, and deploy modular cloud applications. You’ll quickly learn how to use OSGi, through concise code examples and a s',7,'resources/img/06369200246.jpg',210,' O\'Reilly Media',1,14,'Building Modular Cloud Apps with OSGi',2013,0,1,4,1),(26,'2013-02-08 12:00:00','Geoff Chiang',4,2,'The Spring framework has become ubiquitous in modern Java enterprise application development, providing framework solutions for every application layer. Spring Tool Suite combines the capabilities of the Spring framework with the power of the Eclips',2,'resources/img/97817821699.jpg',76,' Packt Publishing',3,14,'Instant Spring Tool Suite',2013,0,1,0,0),(27,'2013-08-07 12:00:00','Mat Johns',3,2,'Applications have become increasing agile and dynamic, reacting to the demands of their users except at the data layer. Hazelcast offers a new and innovative approach to data scalability. Unlike many of its competitors, its in-memory counterparts di',6,'resources/img/97817821673.jpg',136,' Packt Publishing',4,14,'Getting Started with Hazelcast',2013,6,1,2,1),(28,'2013-09-01 12:00:00','Martin Kalin',3,1,'Learn how to develop REST-style and SOAP-based web services and clients with this quick and thorough introduction. This hands-on book delivers a clear, pragmatic approach to web services by providing an architectural overview, complete working code ',3,'resources/img/06369200223.jpg',360,' O\'Reilly Media',3,14,'Java Web Services: Up and Running, 2nd Edition',2013,9,1,0,0),(29,'2013-01-03 12:00:00','Felipe Fedel Pinto, Joao Savio Ceregatti Longo',1,5,'Wicket is a component-based Java web framework. In Wicket, the server side state is automatically managed as it is associated with components. With proper mark-up/logic separation, a POJO data model, and a refreshing lack of XML, Apache Wicket makes',7,'resources/img/97817832893.jpg',54,' Packt Publishing',5,14,'Instant Apache Wicket 6',2013,9,1,0,0),(30,'2013-09-06 12:00:00','Simone Scarduzio',1,9,'In traditional concurrent programming, it’s hard to achieve accuracy and even harder to achieve performance. vert.x has a revolutionary approach that takes care of all the thread orchestration, message passing, and shared data in the framework. Dead',10,'resources/img/978178328107.jpg',54,' Packt Publishing',5,14,'Instant Vert.x',2013,1,1,0,0),(31,'2013-04-03 12:00:00','Ryan Mitchell',5,6,'Java is often thought of as a stuffy enterprise language, while web scraping is the often-murky domain of scripting languages. By combining the robustness and extensibility of Java with the flexibility and power of web scraping, we can create immens',6,'resources/img/97818496957.jpg',72,' Packt Publishing',5,14,'Instant Web Scraping with Java',2013,6,1,0,0),(32,'2013-01-06 12:00:00','Sandeep Kumar Patel',2,3,'GSON is a specialized Java-based library for handling JSON data, developed by Google. GSON demonstrates an efficient use of CPU time, memory efficiency, the library, and developer productivity. This book will help you implement GSON in your Java pro',4,'resources/img/97817832861.jpg',60,' Packt Publishing',1,14,'Instant GSON',2013,3,1,0,0),(33,'2013-01-04 12:00:00','Zakir Laliwala, Abdul Samad, Azaz Desai, Uchit Vyas',3,4,'Mule ESB is a lightweight Java-based enterprise service bus (ESB) and integration platform that allows developers to connect applications together quickly and easily, enabling them to efficiently exchange data. You can therefore use Mule ESB to allo',5,'resources/img/97817821631.jpg',428,' Packt Publishing',3,14,'Mule ESB Cookbook',2013,8,1,0,0),(34,'2013-05-04 12:00:00','Jamie Allen',3,1,'Avoid common mistakes when building distributed, asynchronous, high-performance software with the Akka toolkit and runtime. With this concise guide, author Jamie Allen provides a collection of best practices based on several years of using the actor',5,'resources/img/06369200277.jpg',74,' O\'Reilly Media',1,14,'Effective Akka',2013,8,1,2.75,2),(35,'2013-02-01 12:00:00','Bill Bejeck',4,1,'Java continues to maintain its popularity and is still one of the main languages used in the software industry today. But there are things in Java that are difficult to do that can be made easier; that’s where Guava comes in. Guava provides develope',5,'resources/img/97817832856.jpg',142,' Packt Publishing',5,14,'Getting started with Google Guava',2013,3,1,4,1),(36,'2013-02-01 12:00:00','Arun Gupta',3,6,'Get up to speed on the principal technologies in the Java Platform, Enterprise Edition 7, and learn how the latest version embraces HTML5, focuses on higher productivity, and provides functionality to meet enterprise demands. Written by Arun Gupta, ',6,'resources/img/06369200378.jpg',362,' O\'Reilly Media',1,14,'Java EE 7 Essentials',2013,1,1,0,0),(37,'2013-07-05 12:00:00','Elliotte Rusty Harold',2,2,'Send and receive email from Java applications by using the JavaMail API. With this concise book, you’ll learn how to communicate with existing SMTP, POP, and IMAP servers, and how to write your own.',2,'resources/img/06369200383.jpg',98,' O\'Reilly Media',3,14,'JavaMail API',2013,6,1,0,0),(38,'2013-04-03 12:00:00','Rodrigo Fraxino Araujo, Vinicius H. S. Durelli, Rafael Medeiros Teixeira',5,1,'Integrated Development Environments (IDEs) such as Eclipse are examples of tools that help developers by automating an assortment of software development-related tasks. By reading this book you will learn how to get Eclipse to automate common develo',1,'resources/img/97817821699.jpg',256,' Packt Publishing',3,14,'Getting Started with Eclipse Juno',2013,19,1,0,0),(39,'2013-07-01 12:00:00','Robert Liguori, Patricia Liguori',5,7,'When you need quick answers for developing or debugging Java programs, this pocket guide provides a handy reference to the standard features of the Java programming language and its platform. You’ll find helpful programming examples, tables, figures',10,'resources/img/06369200233.jpg',216,' O\'Reilly Media',1,14,'Java 7 Pocket Guide, 2nd Edition',2013,7,1,0,0),(40,'2013-06-01 12:00:00','Patrick Niemeyer, Daniel Leuck',5,2,'Java is the preferred language for many of today’s leading-edge technologies—everything from smartphones and game consoles to robots, massive enterprise systems, and supercomputers. If you’re new to Java, the fourth edition of this bestselling guide',2,'resources/img/06369200255.jpg',1010,' O\'Reilly Media',5,14,'Learning Java, 4th Edition',2013,4,1,0,0),(41,'2013-05-09 12:00:00','Pete Houston',4,2,'In Detail',3,'resources/img/97817821674.jpg',38,' Packt Publishing',3,14,'Instant jsoup How-to',2013,2,2,0,0),(42,'2013-10-04 12:00:00','Henryk Konsek',5,2,'In Detail',3,'resources/img/97818495126.jpg',66,' Packt Publishing',2,14,'Instant Apache ServiceMix How-to',2013,2,2,0,0),(43,'2013-04-02 12:00:00','Michal Bali',2,4,'In Detail',7,'resources/img/97817821664.jpg',338,' Packt Publishing',5,14,'Drools JBoss Rules 5.X Developer\'s Guide',2013,4,2,0,0),(44,'2013-04-08 12:00:00','Timothy Bish',4,8,'Apache ActiveMQ is a powerful and popular open source messaging and Integration Patterns server. ActiveMQ is a fully JMS 1.1 compliant Message Broker and supports many advanced features beyond the JMS specification.',8,'resources/img/97817821676.jpg',78,' Packt Publishing',4,14,'Instant Apache ActiveMQ Messaging Application Development How-to',2013,8,2,0,0),(45,'2013-07-06 12:00:00','Daniel Dietrich',3,3,'Play is a full-featured Java and Scala web framework for building modern, high-performance web applications. It is characterized by its simplicity and scalability. With its lightweight, stateless, and web-friendly architecture, Play focuses on devel',4,'resources/img/97817821672.jpg',70,' Packt Publishing',1,14,'Instant Play Framework Starter',2013,3,2,0,0),(46,'2013-02-03 12:00:00','Ram Kulkarni',3,1,'Standalone desktop applications are important in this era of web and mobile applications. Eclipse provides a rich set of APIs and tools, not just to create IDEs but also to create cross-platform standalone applications. Eclipse 4 has introduced many',1,'resources/img/978178216112.jpg',68,' Packt Publishing',3,14,'Instant Eclipse 4 RCP Development How-to',2013,0,2,0,0),(47,'2013-08-04 12:00:00','Anatoly Spektor',1,1,'In Detail',7,'resources/img/97817821642.jpg',62,' Packt Publishing',3,14,'Instant Eclipse Application Testing How-to',2013,1,2,0,0),(48,'2013-05-04 12:00:00','Steve Perkins',2,1,'Users expect software to be highly intelligent when searching data. Searches should span across multiple data points at once, and be able to spot patterns and groupings in the results found. Searches should be able to fix user typos, and use terms r',1,'resources/img/97818495175.jpg',148,' Packt Publishing',3,14,'Hibernate Search by Example',2013,9,2,0,0),(49,'2013-04-10 12:00:00','Jeremy Ary',4,9,'Drools is a popular business rule management system. The book introduces the concept of rules separation, from what to do to how to do it. This Starter guide supports your development to keep pace with your system\'s ever-changing needs, making thing',9,'resources/img/97817821667.jpg',52,' Packt Publishing',2,14,'Instant Drools Starter',2013,2,2,0,0),(50,'2013-07-06 12:00:00','Atul Palandurkar',5,1,'NetBeans is an open source IDE which is much more powerful than any other IDE available on the market for Java application development. It allows you to write and generate smart code, and utilize drag-and-drop tools. NetBeans gives complete flexibil',2,'resources/img/97817821623.jpg',70,' Packt Publishing',5,14,'Instant NetBeans IDE How-to',2013,4,2,0,0),(51,'2013-08-03 12:00:00','Jamie Goodyear, Johan Edstrom',4,3,'OSGi is a tried and true modularity standard for Java. It has in recent years gained a lot of traction and tooling; becoming frequently used in Enterprise containers and distributed software systems.',9,'resources/img/97818495154.jpg',58,' Packt Publishing',2,14,'Instant OSGi Starter',2013,0,2,0,0),(52,'2013-10-05 12:00:00','Oleg Varaksin, Mert Caliskan',4,3,'PrimeFaces is the de facto standard in the Java web development. PrimeFaces is a lightweight library with one jar, zero-configuration, and no required dependencies. You just need to download PrimeFaces, add the primefaces-{version}.jar to your class',3,'resources/img/97818495139.jpg',328,' Packt Publishing',1,14,'PrimeFaces Cookbook',2013,7,2,0,0),(53,'2012-04-05 12:00:00','Deepak Vohra',3,1,'Java EE is the industry standard on enterprise computing and Oracle WebLogic Server is the most comprehensive platform for enterprise applications. The book combines Java EE with WebLogic Server in the most commonly used Java IDE, the Eclipse IDE 3.',4,'resources/img/978178216104.jpg',426,' Packt Publishing',4,14,'Java EE Development with Eclipse',2012,2,2,0,0),(54,'2012-02-02 12:00:00','Mauricio Salatino, Esteban Aliverti',1,1,'jBPM5 is a well known open source Business Process Management System (BPMS) used for defining and executing business processes. Java developers can use jBPM5 to analyze, improve, and maintain business processes, which are integral to an organization',4,'resources/img/97818495165.jpg',364,' Packt Publishing',4,14,'jBPM5 Developer Guide',2012,6,2,0,0),(55,'2012-04-07 12:00:00','Petri Kainulainen',4,2,'Spring Framework has always had a good support for different data access technologies. However, developers had to use technology-specific APIs, which often led to a situation where a lot of boilerplate code had to be written in order to implement ev',5,'resources/img/97818495172.jpg',160,' Packt Publishing',1,14,'Spring Data',2012,0,2,0,0),(56,'2012-07-02 12:00:00','Javier Fernandez Gonzalez',2,1,'In Detail',1,'resources/img/978184968117.jpg',364,' Packt Publishing',3,14,'Java 7 Concurrency Cookbook',2012,8,2,0,0),(57,'2012-02-05 12:00:00','Munish K. Gupta',4,5,'In Detail',6,'resources/img/97818495181.jpg',334,' Packt Publishing',5,14,'Akka Essentials',2012,2,2,3,2),(58,'2012-02-08 12:00:00','Piero Giacomelli',3,1,'In Detail',1,'resources/img/97818495175.jpg',250,' Packt Publishing',5,14,'HornetQ Messaging Developer\'s Guide',2012,8,2,0,0),(59,'2012-08-07 12:00:00','Michael Ernest',3,5,'Learn Java SE 7 Quickly and Easily',5,'resources/img/97811183591.jpg',336,' Wiley / Sybex',1,14,'Java SE 7 Programming Essentials',2012,7,2,0,0),(61,'2012-07-04 12:00:00','Andrew Lee Rubinger, Aslak Knutsen',1,2,'With Early Release ebooks, you get books in their earliest form—the author\'s raw and unedited content as he or she writes—so you can take advantage of these technologies long before the official release of these titles. You\'ll also receive updates w',2,'resources/img/69200253633.jpg',222,' O\'Reilly Media',2,14,'Continuous Enterprise Development in Java',2012,5,2,4,2),(62,'2012-10-07 12:00:00','Arun Gupta',2,3,'This handy guide provides an overview of Java Enterprise Edition 6’s main technologies and includes extensive, easy-to-understand code samples that demonstrate the platform’s many improvements. You’ll quickly understand how Java EE 6 simplifies the ',4,'resources/img/06369200230.jpg',210,' O\'Reilly Media',2,14,'Java EE 6 Pocket Guide',2012,1,2,0,0),(63,'2012-06-04 12:00:00','Odili Charles Opute, Oded Nissan',3,3,'Get ready to build the next generation Gmail, Facebook, or Meebo, with HTML5 and Server Push, taking advantage of the power and versatility of Java with ExtGWT. Sencha Ext GWT takes GWT to the next level, giving you high-performance widgets, feature',3,'resources/img/97818495122.jpg',366,' Packt Publishing',3,14,'ExtGWT Rich Internet Application Cookbook',2012,3,2,0,0),(64,'2012-02-05 12:00:00','Francesco Marchioni, Manik Surtani',5,2,'In today\'s competitive business world, Enterprise systems must be able to deliver highly available, high transaction volumes with an increasing number of users. Infinispan enables you to do this as well as share and distribute data among servers in ',6,'resources/img/978184951114.jpg',150,' Packt Publishing',2,14,'Infinispan Data Grid Platform',2012,5,2,0,0),(65,'2012-08-09 12:00:00','Joe Kutner',1,2,'Deploy using the JVM\'s high performance while building your apps in the language you love. JRuby is a fast, scalable, and powerful JVM language with all the benefits of a traditional Ruby environment. See how to consolidate the many moving parts of ',9,'resources/img/97819343559.jpg',224,' Pragmatic Bookshelf',3,14,'Deploying with JRuby',2012,5,2,2.5,2),(66,'2012-09-04 12:00:00','Mick Knutson',3,1,'Java Platform, Enterprise Edition is a widely used platform for enterprise server programming in the Java programming language.',3,'resources/img/97818496838.jpg',356,' Packt Publishing',2,14,'Java EE 6 Cookbook for Securing, Tuning, and Extending Enterprise Applications',2012,7,2,0,0),(67,'2012-03-09 12:00:00','Madhusudhan Konda',4,8,'JDBC has simplified database access in Java applications, but a few nagging wrinkles remain—namely, persisting Java objects to relational databases. With this book, you’ll learn how the Spring Framework makes that job incredibly easy with dependency',8,'resources/img/063692002103.jpg',78,' O\'Reilly Media',5,14,'Just Spring Data Access',2012,6,2,0,0),(68,'2012-01-09 12:00:00','Ido Green',4,4,'Web apps would run much better if heavy calculations could be performed in the background, rather than compete with the user interface. With this book, you’ll learn how to use Web Workers to run computationally intensive JavaScript code in a thread ',4,'resources/img/06369200241.jpg',62,' O\'Reilly Media',1,14,'Web Workers',2012,0,2,0,0),(69,'2012-04-02 12:00:00','Deepak Vohra',4,1,'Web services are applications that use open, XML-based standards and transport protocols to exchange data with clients.',1,'resources/img/978184968109.jpg',64,' Packt Publishing',1,14,'Java 7 JAX-WS Web Services',2012,9,2,0,0),(70,'2012-08-09 12:00:00','Doug Lowe',4,8,'Quick answers and solutions to help you zip through Java programming',9,'resources/img/97811181671.jpg',384,' Wiley / For Dummies',3,14,'Java For Dummies Quick Reference',2012,0,2,0,0),(71,'2012-01-05 12:00:00','Madhusudhan Konda',4,6,'Get started with Spring Integration, the lightweight Java-based framework that makes designing and developing message-oriented architectures a breeze. Through numerous examples, you’ll learn how to use this open source framework’s basic building blo',6,'resources/img/06369200269.jpg',100,' O\'Reilly Media',3,14,'Just Spring Integration',2012,7,2,0,0),(72,'2012-01-02 12:00:00','Burd',2,2,'Learn Java programming the easy way and make your computer do your bidding',5,'resources/img/978047037104.jpg',456,' Wiley / For Dummies',4,14,'Beginning Programming with Java For Dummies, 3rd Edition',2012,7,2,3.5,3),(73,'2012-05-09 12:00:00','Richard Reese, Jennifer L. Reese',5,1,'Java 7 is a major update that includes a lot of exciting new language improvements such as support for type inference and improved exception handling. Other new features include the ability to work with symbolic links, a greatly simplified directory',1,'resources/img/97818496872.jpg',384,' Packt Publishing',5,14,'Java 7 New Features Cookbook',2012,6,2,0,0),(74,'2012-04-02 12:00:00','Hamidreza Sattari, Shameer Kunjumohamed',3,4,'Spring Web Services is a product of the Spring community focused on creating document-driven Web services.',6,'resources/img/978184951108.jpg',322,' Packt Publishing',4,14,'Spring Web Services 2 Cookbook',2012,8,2,0,0),(75,'2012-03-08 12:00:00','Len DiMaggio, Kevin Conner, Magesh Kumar B, Tom Cunningham',2,5,'You may often have wondered if there is a better way to integrate disparate applications than error-prone \"glue code\". JBoss ESB is just that solution as it can help solve common but difficult problems: writing new code that can be re-used and maint',6,'resources/img/97818495161.jpg',320,' Packt Publishing',4,14,'JBoss ESB',2012,3,2,0,0),(76,'2012-06-04 12:00:00','Lucas Amador',1,1,'JBoss Drools is an open source business rules engine that provides agility and flexibility to your business logic. Drools 5 has evolved to provide a unified and integrated platform for business rules, business processes, event processing and automat',10,'resources/img/97818495159.jpg',310,' Packt Publishing',2,14,'Drools Developer\'s Cookbook',2012,7,2,4,1),(77,'2011-03-03 12:00:00','Francesco Marchioni',3,4,'As the Java EE standard has evolved and matured, the API has become increasingly rich and complex. The next generation of application servers needs to be modular and configurable to run only specific services or containers. JBoss AS 7 promises to me',6,'resources/img/97818495157.jpg',380,' Packt Publishing',3,14,'JBoss AS 7 Configuration, Deployment and Administration',2011,8,2,0,0),(78,'2011-07-03 12:00:00','Nicolas Frankel',2,1,'Vaadin is a new Java web framework for making applications look great and perform well, making your users happy. Vaadin promises to make your user interfaces attractive and usable while easing your development efforts and boosting your productivity.',0,'resources/img/97818495166.jpg',412,' Packt Publishing',5,14,'Learning Vaadin',2011,9,2,0,0),(79,'2011-05-06 12:00:00','Madhusudhan Konda',5,2,'Java 7 has a number of features that will please developers. Madhusudhan Konda provides an overview of these, including strings in switch statements, multi-catch exception handling, try-with-resource statements, the new File System API, extensions o',5,'resources/img/063692002115.jpg',14,' O\'Reilly Media',2,14,'What\'s New in Java 7?',2011,3,2,0,0),(80,'2011-07-08 12:00:00','Yakov Fain',5,5,'A guru in the field gets you started programming with Java',7,'resources/img/97804708889.jpg',504,' Wiley / Wrox',3,14,'Java Programming 24-Hour Trainer',2011,4,2,0,0),(81,'2011-10-06 12:00:00','Ashish Sarin',1,5,'Spring Roo is an easy-to-use productivity tool for rapidly developing Java enterprise applications using well-recognized frameworks such as Spring, Hibernate, AspectJ, Spring Web Flow, Spring Security, GWT, and so on. Spring Roo takes care of creati',5,'resources/img/97818495170.jpg',460,' Packt Publishing',2,14,'Spring Roo 1.1 Cookbook',2011,2,3,0,0),(82,'2011-08-09 12:00:00','James McGovern, Rahim Adatia, Yakov Fain, Jason Gordon, Ethan Henry, Walter Hurst, Ashish Jain, Mark Little, Vaidyanathan Nagarajan, Harshad Oak, Lee Anne Phillips',4,3,'\"One stop shopping for J2EE.\"  Scott Ambler, Senior Consultant, Ronin International, Inc. and Author of Agile Modeling',7,'resources/img/97807645379.jpg',1008,' Wiley',5,14,'Java2 Enterprise Edition 1.4 (J2EE 1.4) Bible',2011,8,3,0,0),(83,'2011-05-01 12:00:00','Venkat Subramaniam',4,2,'More than ever, learning to program concurrency is critical to creating faster, responsive applications. Speedy and affordable multicore hardware is driving the demand for high-performing applications, and you can leverage the Java platform to bring',2,'resources/img/97819343576.jpg',280,' Pragmatic Bookshelf',5,14,'Programming Concurrency on the JVM',2011,7,3,0,0),(84,'2011-07-02 12:00:00','Ivor Horton',4,1,'The most thorough and accessible introduction to the Java language',1,'resources/img/97804704069.jpg',1150,' Wiley / Wrox',2,14,'Ivor Horton\'s Beginning Java',2011,8,3,0,0),(85,'2011-03-02 12:00:00','Srirangan',3,1,'Apache Maven is more than just build automation. When positioned at the very heart of your development strategy, Apache Maven can become a force multiplier not just for individual developers but for agile teams and managers. This book covers impleme',2,'resources/img/978184951100.jpg',224,' Packt Publishing',3,14,'Apache Maven 3 Cookbook',2011,7,3,3.83333,3),(86,'2011-10-02 12:00:00','Alexander Reelsen',4,3,'The Play framework is the new kid on the block of Java frameworks. By breaking with existing standards the play framework tries not to abstract away from HTTP as most web frameworks do, but tightly integrates with it. This means quite a shift for Ja',7,'resources/img/97818495146.jpg',292,' Packt Publishing',4,14,'Play Framework Cookbook',2011,5,3,0,0),(87,'2011-03-04 12:00:00','Josh Long, Steve Mayzak',5,5,'Spring Roo goes a step beyond the Spring Framework by bringing true Rapid Application Development to Java—just as Grails has done with Groovy. This concise introduction shows you how to build applications with Roo, using the framework\'s shell as an ',7,'resources/img/06369200263.jpg',64,' O\'Reilly Media',3,14,'Getting Started with Roo',2011,4,3,0,0),(88,'2011-01-08 12:00:00','Doug Lowe',1,3,'You too can be a Java guru! From getting started to website and mobile app-building, here\'s the scoop',5,'resources/img/97804703737.jpg',912,' Wiley / For Dummies',2,14,'Java All-in-One For Dummies, 3rd Edition',2011,4,3,0,0),(89,'2011-05-08 12:00:00','Madhusudhan Konda',2,1,'Get a concise introduction to Spring, the popular open source framework for building lightweight enterprise applications on the Java platform. This example-driven book for Java developers delves into the framework’s basic features, as well as comple',2,'resources/img/06369200269.jpg',96,' O\'Reilly Media',1,14,'Just Spring',2011,1,3,0,0),(90,'2011-02-01 12:00:00','Matthew B. Doar',4,8,'One advantage of using JIRA for issue tracking, bug tracking, or project management is the ability to extend this tool with hundreds of plugins from the JIRA community. In this concise book, software toolsmith Matt Doar—the author of Practical JIRA ',8,'resources/img/06369200259.jpg',112,' O\'Reilly Media',3,14,'Practical JIRA Plugins',2011,5,3,0,0),(91,'2011-09-02 12:00:00','Jurg van Vliet, Flavia Paganelli, Steven van Wel, Dara Dowd',4,4,'While it\'s always been possible to run Java applications on Amazon EC2, Amazon\'s Elastic Beanstalk makes the process easier—especially if you understand how it works beneath the surface. This concise, hands-on book not only walks you through Beansta',6,'resources/img/06369200250.jpg',88,' O\'Reilly Media',4,14,'Elastic Beanstalk',2011,6,3,3.66667,3),(92,'2011-10-01 12:00:00','Dean Wampler',4,9,'Software development today is embracing functional programming (FP), whether it\'s for writing concurrent programs or for managing Big Data. Where does that leave Java developers? This concise book offers a pragmatic, approachable introduction to FP ',9,'resources/img/063692002118.jpg',90,' O\'Reilly Media',4,14,'Functional Programming for Java Developers',2011,3,3,3.5,1),(93,'2011-01-06 12:00:00','Burd',4,10,'Jumpin\' Java! The bestselling Java beginner\'s book is now fully updated for Java 7!',10,'resources/img/97804703728.jpg',432,' Wiley / For Dummies',5,14,'Java For Dummies, 5th Edition',2011,5,3,0,0),(94,'2011-01-04 12:00:00','David R. Heffelfinger',4,2,'NetBeans has several features that greatly simplify Java EE development, but with many features and great flexibility, Java developers can become overwhelmed by the options available in NetBeans. This book provides step-by-step recipes that show you',5,'resources/img/97818495165.jpg',392,' Packt Publishing',5,14,'Java EE 6 Development with NetBeans 7',2011,8,3,0,0),(95,'2011-08-10 12:00:00','Richard Reese',4,5,'Enterprise Java Beans enable rapid and simplified development of secure and portable applications based on Java technology.Creating and using EJBs can be challenging and rewarding. Among the challenges are learning the EJB technology itself, learnin',7,'resources/img/97818496881.jpg',436,' Packt Publishing',3,14,'EJB 3.1 Cookbook',2011,1,3,0,0),(96,'2011-10-04 12:00:00','Matthew B. Doar',4,1,'If you\'re familiar with JIRA for issue tracking, bug tracking, and other uses, you know it can sometimes be tricky to set up and manage. In this concise book, software toolsmith Matt Doar clarifies some of the more confusing aspects by answering dif',3,'resources/img/063692002112.jpg',110,' O\'Reilly Media',4,14,'Practical JIRA Administration',2011,1,3,0,0),(97,'2011-09-05 12:00:00','Rhawi Dantas',5,1,'Java IDEs have grown bigger and more complicated with time. Some development environments even require the user to spend countless hours searching for more software to bundle with the IDE just to start working. NetBeans abstracts much of the work ne',5,'resources/img/97818495166.jpg',308,' Packt Publishing',5,14,'NetBeans IDE 7 Cookbook',2011,2,3,0,0),(98,'2011-01-10 12:00:00','David Salter',4,3,'The Seam framework from JBoss allows developers to use JSF, Facelets, EJB, and JPA to write conversational web applications. Testing, securing, and database persistence are the most important topics to learn if you want an application that runs quic',4,'resources/img/978184951117.jpg',104,' Packt Publishing',2,14,'Seam 2 Web Development: LITE',2011,3,3,0,0),(99,'2011-01-04 12:00:00','Igor Vaynberg',2,5,'Apache Wicket is one of the most famous Java web application frameworks. Wicket simplifies web development and makes it fun. Are you bored of going through countless pages of theory to find out how to get your web development done? With this book in',5,'resources/img/97818495133.jpg',312,' Packt Publishing',3,14,'Apache Wicket Cookbook',2011,0,3,3.25,2),(100,'2011-10-10 12:00:00','Ronald Mak',1,8,'Master the skills you need to build your own compilers and interpreters',9,'resources/img/97804701755.jpg',864,' Wiley',2,14,'Writing Compilers and Interpreters, 3rd Edition',2011,1,3,0,0),(101,'2011-04-05 12:00:00','Deepal Jayasinghe, Afkham Azeez',5,7,'Web services are gaining popularity and have become one of the major techniques for application integration. Due to the flexibility and advantages of using web services, you want to enable Web service support to your applications. This book is your ',7,'resources/img/97818495156.jpg',308,' Packt Publishing',3,14,'Apache Axis2 Web Services, 2nd Edition, 2nd Edition',2011,10,4,2.16667,3),(102,'2010-04-03 12:00:00','Francesco Marchioni',5,2,'Today\'s organizations need to deliver faster services to a large set of people and businesses. In order to survive this challenge, enterprises need to optimize the performance of their application server along with its components and hardware. Writi',2,'resources/img/97818495176.jpg',312,' Packt Publishing',5,14,'JBoss AS 5 Performance Tuning',2010,0,4,0,0),(103,'2010-02-03 12:00:00','Daniel Guermeur, Amy Unruh',1,3,'Google Application Engine is a cloud computing technology that lets you run web applications that are easy to build and maintain as well as being scaled and load-balanced automatically. To build interesting and interactive web applications developer',10,'resources/img/97818496997.jpg',480,' Packt Publishing',5,14,'Google App Engine Java and GWT Application Development',2010,7,4,0,0),(104,'2010-04-05 12:00:00','Walid Joseph Gedeon',2,7,'The OSGi specification is a module system and service platform that implements a complete and dynamic component model. Wasn\'t that a complicated definition! So how would you really use it in practical modular applications? Let this book break down t',10,'resources/img/97818495151.jpg',336,' Packt Publishing',5,14,'OSGi and Apache Felix 3.0 Beginner\'s Guide',2010,4,4,0,0),(105,'2010-09-06 12:00:00','Andrew Lee Rubinger, Bill Burke',4,1,'Learn how to code, package, deploy, and test functional Enterprise JavaBeans with the latest edition of this bestselling guide. Written by the developers of JBoss EJB 3.1, this book not only brings you up to speed on each component type and containe',4,'resources/img/978059615114.jpg',766,' O\'Reilly Media',5,14,'Enterprise JavaBeans 3.1, 6th Edition',2010,8,4,3.75,2),(106,'2010-04-09 12:00:00','Deepak Vohra',4,4,'EJB (Enterprise JavaBeans) 3.0 is a commonly used database persistence technology in Java EE applications. EJB 3.0 has simplified the development of EJBs with an annotations-based API that eliminates the use of remote/local interfaces, home/local ho',10,'resources/img/978184968117.jpg',448,' Packt Publishing',5,14,'EJB 3.0 Database Persistence with Oracle Fusion Middleware 11g',2010,9,4,3,2),(107,'2010-10-04 12:00:00','Vladimir Vivien',1,3,'Silverlight makes it much easier to build web applications with highly usable, interactive, and exciting user interfaces. However, with so many new options open to designers and developers, making the best use of the tools available is not always so',3,'resources/img/97818471998.jpg',332,' Packt Publishing',4,14,'JavaFX 1.2 Application Development Cookbook',2010,3,4,0,0),(108,'2010-02-01 12:00:00','Jurgen Petri',3,9,'The NetBeans Platform has many features provided out of the box for Swing desktop application developers. It can take you hours just to create menu bars, toolbars, a window system, and other typical desktop application infrastructural needs rather t',9,'resources/img/97818495153.jpg',288,' Packt Publishing',4,14,'NetBeans Platform 6.9 Developer\'s Guide',2010,3,4,0,0),(109,'2010-03-02 12:00:00','Kenneth Barclay, John Savage',1,1,'Groovy Programming is an introduction to the Java-based scripting language Groovy. Groovy has much in common with popular scripting languages such as Perl, Python, and Ruby, but is written in a Java-like syntax. And, unlike these other languages, Gr',1,'resources/img/97801237250.jpg',496,' Elsevier / Morgan Kaufmann',5,14,'Groovy Programming',2010,10,4,0,0),(110,'2010-02-03 12:00:00','Bill Foust',4,6,'BlackBerry Smartphone was once the domain of jet-setting business users with power suits. Now you can hardly go anywhere without seeing someone using a BlackBerry to check their messages or make a call. It\'s this kind of explosive growth that makes ',9,'resources/img/97818496958.jpg',368,' Packt Publishing',3,14,'BlackBerry Java Application Development',2010,0,4,3.375,4),(111,'2010-10-01 12:00:00','David R. Heffelfinger',4,4,'GlassFish is a free, open source, production ready application server. It is the environment\'s reference implementation and the first Enterprise Java server to implement Java EE6. Although GlassFish server delivers a flexible, lightweight and extens',8,'resources/img/97818495185.jpg',488,' Packt Publishing',3,14,'Java EE 6 with GlassFish 3 Application Server',2010,1,4,0,0),(112,'2010-04-10 12:00:00','Mark F. Hornick, Erik Marcadé, Sunil Venkayala',2,1,'Whether you are a software developer, systems architect, data analyst, or business analyst, if you want to take advantage of data mining in the development of advanced analytic applications, Java Data Mining, JDM, the new standard now implemented in',1,'resources/img/97801237076.jpg',544,' Elsevier / Morgan Kaufmann',5,14,'Java Data Mining: Strategy, Standard, and Practice',2010,6,4,0,0),(113,'2010-07-06 12:00:00','Anghel Leonard',4,1,'JavaServer Faces is a Java-based Web application framework intended to simplify development of user interfaces for Java EE applications. You may already be aware of the laborious search through reference guides and documentation to develop your JSF ',4,'resources/img/97818471990.jpg',396,' Packt Publishing',3,14,'JSF 2.0 Cookbook',2010,6,4,0,0),(114,'2010-09-10 12:00:00','Fergal Dearle',5,6,'You may already be aware that the Java virtual machine runs on everything from the largest mainframe to the smallest microchip and supports almost every conceivable application. What you may not realize is that to develop software in some of these s',9,'resources/img/97818471939.jpg',312,' Packt Publishing',3,14,'Groovy for Domain-Specific Languages',2010,0,4,3.5,1),(115,'2010-10-06 12:00:00','Jonas X. Yuan',3,1,'Liferay Portal is the world\'s leading open-source portal platform built on Java and Web 2.0 technologies. It was designed to not only simplify your work experience but cater to your preferences and needs. With this book in hand, you will be able to ',1,'resources/img/97818495168.jpg',692,' Packt Publishing',4,14,'Liferay Portal 6 Enterprise Intranets',2010,0,4,0,0),(116,'2010-05-04 12:00:00','Jim Waldo',1,2,'What if you could condense Java down to its very best features and build better applications with that simpler version? In this book, veteran Sun Labs engineer Jim Waldo reveals which parts of Java are most useful, and why those features make Java a',3,'resources/img/97805968073.jpg',196,' O\'Reilly Media',5,14,'Java: The Good Parts',2010,8,4,0,0),(117,'2010-07-01 12:00:00','Bart Kummel',5,1,'Hypes and trends (such as Web 2.0) cause a change in the requirements for user interfaces every now and then. While a lot of frameworks are capable of meeting those changing requirements, it often means you as a developer need in-depth knowledge of ',4,'resources/img/97818471930.jpg',408,' Packt Publishing',3,14,'Apache MyFaces 1.2',2010,8,4,3.66667,3);
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
INSERT INTO `booksinuse` VALUES (2,NULL,'2014-02-28 02:10:45',11,5);
/*!40000 ALTER TABLE `booksinuse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre_translations`
--

DROP TABLE IF EXISTS `genre_translations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genre_translations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `locale` varchar(255) DEFAULT NULL,
  `gid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gat9en2jqc5io5g9qgcypogrx` (`gid`),
  CONSTRAINT `FK_gat9en2jqc5io5g9qgcypogrx` FOREIGN KEY (`gid`) REFERENCES `genres` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre_translations`
--

LOCK TABLES `genre_translations` WRITE;
/*!40000 ALTER TABLE `genre_translations` DISABLE KEYS */;
INSERT INTO `genre_translations` VALUES (3,'Computer Science','en',1),(4,'Інформатика','ua',1),(5,'Science','en',2),(6,'Наукова Література','ua',2),(7,'Imaginative','en',3),(8,'Художня Література','ua',3),(9,'Art','en',4),(10,'Мистецтво','ua',4),(11,'Historical','en',5),(12,'Історична Література','ua',5),(15,'DeleteMe','en',7),(16,'ДляВидалення','ua',7);
/*!40000 ALTER TABLE `genre_translations` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genres`
--

LOCK TABLES `genres` WRITE;
/*!40000 ALTER TABLE `genres` DISABLE KEYS */;
INSERT INTO `genres` VALUES (1,'Computer Science'),(2,'Science'),(3,'Imaginative'),(4,'Art'),(5,'Historical'),(7,'DeleteMe');
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
  `changed` tinyint(1) DEFAULT NULL,
  `daysamount` bigint(20) DEFAULT NULL,
  `order_date` datetime DEFAULT NULL,
  `bid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `return_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bm2fin7b72b3xtjojm9da9kj6` (`pid`,`bid`),
  KEY `FK_71a4vlhrltibatg60nyuxpith` (`bid`),
  KEY `FK_hq80wuvwadnmgmjkn20xeqt2k` (`pid`),
  CONSTRAINT `FK_71a4vlhrltibatg60nyuxpith` FOREIGN KEY (`bid`) REFERENCES `books` (`bid`),
  CONSTRAINT `FK_hq80wuvwadnmgmjkn20xeqt2k` FOREIGN KEY (`pid`) REFERENCES `persons` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (153,0,NULL,'2014-02-20 12:00:00',14,8,'2014-02-26 12:00:00');
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persons`
--

LOCK TABLES `persons` WRITE;
/*!40000 ALTER TABLE `persons` DISABLE KEYS */;
INSERT INTO `persons` VALUES (1,10,'546-464-6465',0,'user1@mail.com',4,69,1,NULL,-2,'user1','$2a$10$ySB3H7VENMF3vXxYzCEjVe7koJp7dunjRO7HvdzeytrKhtERM7YV.','ROLE_USER',1,'user1',9,0),(2,10,'156-444-6846',0,'admin@mail.com',0,0,1,NULL,0,'admin','$2a$10$akXMOgwHID8.72TbcidNr.y9JGBOyClz/Ep0OnlPXsrAKwTsIREka','ROLE_ADMIN',1,'admin',0,0),(3,10,'561-464-6464',0,'lib@mail.com',0,0,1,NULL,0,'lib','$2a$10$VFZwTLdoO6fGVoawFNgnlurXdt3btMahfYGHIMDPYpI3Jac4DLIpC','ROLE_LIBRARIAN',1,'lib',0,0),(5,10,'564-646-4644',0,'user3@mail.com',0,0,1,NULL,1,'user3','$2a$10$ltxnygpTa0GF43usv/JVPOJl9XaqCGc9ddt99oA8zjdyXb0QQeW3y','ROLE_USER',1,'user3',0,0),(6,10,'458-449-8494',0,'user4@mail.com',1,0,1,NULL,0,'user4','$2a$10$JJlk4g9NoIq4S6Yda3/QEOsROjb9pmoS6tasipf4j6jLF.BjP5V1e','ROLE_USER',0,'user4',0,0),(7,10,'656-565-6161',0,'user5@mail.com',0,100,1,NULL,0,'user5','$2a$10$wOITZ6KonnpyYlXiS38juOkSOcnGBJ6NxN0sbwSvxOZ3MToKyAcQm','ROLE_USER',0,'user5',3,0),(8,10,'545-645-6456',0,'user2@mail.com',1,66,1,NULL,0,'user3','$2a$10$fbrSzCAFgkhANy1QYYEe4uht5hBp52PtNHF3BKd/vbpZMt/IYOgrS','ROLE_USER',0,'user2',2,0);
/*!40000 ALTER TABLE `persons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rates`
--

DROP TABLE IF EXISTS `rates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL,
  `rate_date` datetime DEFAULT NULL,
  `score` float DEFAULT NULL,
  `bid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_eetf7m6f7tqgxd0bf9k48xlxo` (`pid`,`bid`),
  KEY `FK_97ppl99xg9wk6atf43banvda7` (`bid`),
  KEY `FK_snwwre5m9dccbdl0w04u9dxl0` (`pid`),
  CONSTRAINT `FK_97ppl99xg9wk6atf43banvda7` FOREIGN KEY (`bid`) REFERENCES `books` (`bid`),
  CONSTRAINT `FK_snwwre5m9dccbdl0w04u9dxl0` FOREIGN KEY (`pid`) REFERENCES `persons` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rates`
--

LOCK TABLES `rates` WRITE;
/*!40000 ALTER TABLE `rates` DISABLE KEYS */;
INSERT INTO `rates` VALUES (33,'','2014-02-15 17:14:05',3.5,1,1),(34,'','2014-02-15 17:14:12',3.5,14,1),(35,'','2014-02-15 17:14:19',3.5,2,1),(36,'','2014-02-15 17:14:36',4.5,1,8),(37,'','2014-02-15 17:14:42',4,14,8),(38,'','2014-02-15 17:15:24',4.5,11,8),(39,'','2014-02-15 17:15:35',4,2,8),(40,'','2014-02-15 17:15:56',3.5,14,5),(41,'','2014-02-15 17:16:03',4.5,11,5),(42,'','2014-02-15 17:16:19',4,1,6),(43,'','2014-02-15 17:16:23',4,11,6),(44,'','2014-02-15 17:16:28',3.5,2,6),(46,'','2014-02-15 17:16:56',2.5,14,7),(47,'','2014-02-15 17:17:04',2.5,11,7),(48,'','2014-02-15 17:22:41',3.5,57,1),(49,'','2014-02-15 17:23:15',3,3,1),(50,'','2014-02-15 17:23:25',4,99,1),(51,'','2014-02-15 17:23:31',4,13,1),(52,'','2014-02-15 17:23:37',4,72,1),(53,'','2014-02-15 17:23:44',2.5,110,1),(54,'','2014-02-15 17:23:53',5,6,1),(55,'','2014-02-15 17:23:58',3.5,91,1),(56,'','2014-02-15 17:24:05',2.5,106,1),(57,'','2014-02-15 17:24:11',2.5,34,1),(58,'','2014-02-15 17:24:20',3.5,92,1),(59,'','2014-02-15 17:24:26',3.5,114,1),(60,'','2014-02-15 17:24:33',2,27,1),(61,'','2014-02-15 17:24:37',4,35,1),(62,'','2014-02-15 17:26:57',2.5,57,8),(63,'','2014-02-15 17:27:04',3.5,85,8),(64,'','2014-02-15 17:27:11',3.5,117,8),(65,'','2014-02-15 17:27:17',2.5,99,8),(66,'','2014-02-15 17:27:24',3.5,72,8),(67,'','2014-02-15 17:27:30',3.5,110,8),(68,'','2014-02-15 17:27:35',4,25,8),(69,'','2014-02-15 17:27:42',3.5,8,8),(70,'','2014-02-15 17:27:49',4.5,61,8),(71,'','2014-02-15 17:27:54',3,65,8),(72,'','2014-02-15 17:28:01',4,76,8),(73,'','2014-02-15 17:28:06',3,34,8),(74,'','2014-02-15 17:28:10',3.5,106,8),(75,'','2014-02-15 17:28:15',3.5,91,8),(76,'','2014-02-15 17:28:20',3,18,8),(77,'','2014-02-15 17:28:52',4,101,5),(78,'','2014-02-15 17:28:58',3.5,85,5),(79,'','2014-02-15 17:29:03',3.5,117,5),(80,'','2014-02-15 17:29:08',4,23,5),(81,'','2014-02-15 17:29:13',3.5,110,5),(82,'','2014-02-15 17:29:19',3.5,61,5),(83,'','2014-02-15 17:29:28',4,105,5),(84,'','2014-02-15 17:29:33',4,91,5),(85,'','2014-02-15 17:29:56',2.5,101,6),(86,'','2014-02-15 17:30:00',3.5,13,6),(87,'','2014-02-15 17:30:04',3,72,6),(88,'','2014-02-15 17:30:30',3.5,105,6),(89,'','2014-02-15 17:30:35',2,65,6),(97,NULL,NULL,4,61,6),(98,NULL,NULL,4,14,6),(99,'','2014-02-15 21:53:37',4,117,6),(100,'','2014-02-15 21:53:49',4,110,6),(101,'','2014-02-15 21:54:41',4.5,85,1),(102,'','2014-02-15 22:16:25',3.5,11,1),(112,'','2014-02-16 01:13:11',0,101,1);
/*!40000 ALTER TABLE `rates` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
INSERT INTO `wishlist` VALUES (25,38,1),(8,38,7),(21,23,8);
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

-- Dump completed on 2014-02-16  1:35:58
