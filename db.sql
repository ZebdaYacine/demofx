-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: doctorlite
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `diagnostic`
--

DROP TABLE IF EXISTS `diagnostic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diagnostic` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dateDiagnostic` date DEFAULT NULL,
  `sickness` varchar(50) DEFAULT NULL,
  `idPatient` bigint DEFAULT NULL,
  `idFollow` bigint DEFAULT NULL,
  `mediclaDiagnostic` varchar(500) DEFAULT NULL,
  `psychologyDiagnostic` varchar(500) DEFAULT NULL,
  `interviewDynamics` varchar(500) DEFAULT NULL,
  `conclusion` varchar(500) DEFAULT NULL,
  `recipePsuchologist` varchar(500) DEFAULT NULL,
  `recipeMedicale` varchar(500) DEFAULT NULL,
  `idDoctor` bigint DEFAULT NULL,
  `idPsychologist` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idPatient` (`idPatient`),
  KEY `idFollow` (`idFollow`),
  KEY `idDoctor` (`idDoctor`),
  KEY `idPsychologist` (`idPsychologist`),
  CONSTRAINT `diagnostic_ibfk_1` FOREIGN KEY (`idPatient`) REFERENCES `patient` (`id`),
  CONSTRAINT `diagnostic_ibfk_2` FOREIGN KEY (`idFollow`) REFERENCES `follow` (`id`),
  CONSTRAINT `diagnostic_ibfk_3` FOREIGN KEY (`idDoctor`) REFERENCES `user` (`id`),
  CONSTRAINT `diagnostic_ibfk_4` FOREIGN KEY (`idPsychologist`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diagnostic`
--

LOCK TABLES `diagnostic` WRITE;
/*!40000 ALTER TABLE `diagnostic` DISABLE KEYS */;
INSERT INTO `diagnostic` VALUES (2,'2023-05-03',NULL,28,9,' لم يكن الموضوع كثير التعقيد، لمن يريد تعلم هذا كل ما عليك فعله هو أن تتأكد أنّ المريض مغمى عليه ولا يوجد نبض... فتبدأ الانعاش القلبي الرئوي \"massage cardiaque\" بمعدل مئة ضغطة كل دقيقة وتنفخ في فم المريض بعد كل 30 ضغطة.\n','موقف كلما تذكرته أشعر أنّ حرارة جسمي ترتفع والدماء تصعد لوجهي ورغبة في أن أخبئ رأسي تحت الأرض... \nأثناء حصة محاكاة هدفها تعليمنا الاسعافات الأولية، توافقت الحصة مع فترة كورونا، شرحت لنا الاستاذة على دمية الطريقة المتبعة للانعاش القلبي الرئوي للمصابين بنوبة قلبية...\n لم يكن الموضوع كثير التعقيد، لمن يريد تعلم هذا كل ما عليك فعله هو أن تتأكد أنّ المريض مغمى عليه ولا يوجد نبض... فتبدأ الانعاش القلبي الرئوي \"massage cardiaque\" بمعدل مئة ضغطة كل دقيقة وتنفخ في فم المريض بعد كل 30 ضغطة.','ذكرى آخر مناوبة ليلية في الانتارنا... \nمناوبة مليئة بالحالات الحرجة والمستعجلة في قسم الجراحة... \nإلا أنّ أحد \"أصحاب الرجلة\" أضفى عليها طابع سياسي ساخر يبيّن حالة الشعب ومعاناته عندما جاء يطلب التخفيف عنه من ألم البواسير... ','أغرب أشياء فعلتها كأنتارن: \nالinternat أو الداخلية هي آخر سنة في دراسة الطب \"السنة السابعة\" وهي عبارة عن تربص يقوم به الطبيب داخل المستشفى والتي يفترض أن تكون موجهة للتطبيق والتعلم واكتساب أكبر قدر من الخبرة والمعارف... لكن للاسف في نظامنا التعليمي في الجزائر لا يحدث أغلب هذا... \nالانتارن هو شخص يستعمله نظام المستشفى الجامعي لسد النقائص وملئ الفراغات والثقوب والحفر، ووسيلة لافراغ الاساتذة لعقدهم النفسية والاجتماعية...\n ليس هذا موضوعنا الآن...',NULL,NULL,75,20),(3,'2023-05-02',NULL,28,9,'the education system in algeria is very weak because there are many reasons firstly the education\n programes is very long and few hard may be the teachers  need tow years to done\n programe of one single year.  also the quality of the teachers as i think is not very hight it is  ','the education system in algeria is very weak because there are many reasons firstly the education\n programes is very long and few hard may be the teachers  need tow years to done\n programe of one single year.  also the quality of the teachers as i think is not very hight it is  ','the education system in algeria is very weak because there are many reasons firstly the education\n programes is very long and few hard may be the teachers  need tow years to done\n programe of one single year.  also the quality of the teachers as i think is not very hight it is  ','the education system in algeria is very weak because there are many reasons firstly the education\n programes is very long and few hard may be the teachers  need tow years to done\n programe of one single year.  also the quality of the teachers as i think is not very hight it is  ',NULL,NULL,76,20),(9,'2023-05-02',NULL,29,10,'','','','',NULL,NULL,75,20),(10,'2023-05-01',NULL,29,10,NULL,NULL,NULL,NULL,NULL,NULL,78,20);
/*!40000 ALTER TABLE `diagnostic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dateEnter` date DEFAULT NULL,
  `dateGo` date DEFAULT NULL,
  `idPatient` bigint DEFAULT NULL,
  `idService` bigint DEFAULT NULL,
  `idDoctor` bigint DEFAULT NULL,
  `idPsychologist` bigint DEFAULT NULL,
  `sickness` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idPatient` (`idPatient`),
  KEY `idService` (`idService`),
  KEY `idPsychologist` (`idPsychologist`),
  KEY `idDoctor` (`idDoctor`),
  CONSTRAINT `follow_ibfk_1` FOREIGN KEY (`idPatient`) REFERENCES `patient` (`id`),
  CONSTRAINT `follow_ibfk_2` FOREIGN KEY (`idService`) REFERENCES `service` (`id`),
  CONSTRAINT `follow_ibfk_3` FOREIGN KEY (`idPsychologist`) REFERENCES `user` (`id`),
  CONSTRAINT `follow_ibfk_4` FOREIGN KEY (`idDoctor`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (1,'2023-05-03','2023-05-04',23,1,78,20,'FDFD','FDGDF'),(9,'2023-05-03','2023-05-08',28,1,75,20,'FDFD','FDGDF'),(10,'2023-04-28','2023-04-28',29,1,75,20,'FDFD','FDGDF');
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `firstName` varchar(20) DEFAULT NULL,
  `lastName` varchar(20) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `civilStatus` varchar(50) DEFAULT NULL,
  `worke` varchar(50) DEFAULT NULL,
  `scientificLevel` varchar(50) DEFAULT NULL,
  `socioEconomicLevel` varchar(50) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `height` int DEFAULT NULL,
  `wieght` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (23,'zed12','zed','0658185867','bordj','أرمل','zed',NULL,NULL,'ذكر',NULL,'2023-05-02',200,200),(28,'EGZIURG','DSF','0658185867','bordj','أرمل','zed',NULL,NULL,'ذكر',NULL,'2023-04-23',200,200),(29,'RRERE','OOO','0658185867','bordj','أرمل','zed',NULL,NULL,'ذكر',NULL,'2023-04-23',200,200);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,'cardio');
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (1,'doctor'),(2,'psychologist'),(3,'administrator');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `userName` varchar(10) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `firstName` varchar(20) DEFAULT NULL,
  `lastName` varchar(20) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `idService` bigint NOT NULL,
  `idRole` bigint NOT NULL,
  `idType` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idType` (`idType`),
  KEY `idRole` (`idRole`),
  KEY `idService` (`idService`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`idType`) REFERENCES `type` (`id`),
  CONSTRAINT `user_ibfk_2` FOREIGN KEY (`idRole`) REFERENCES `role` (`id`),
  CONSTRAINT `user_ibfk_3` FOREIGN KEY (`idService`) REFERENCES `service` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=173 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (20,'0458185867','0458185867','0458185867','0458185867','0458185867',NULL,1,1,2),(75,'0658185860','0658185860','زبدة','ياسين','0658185860',NULL,1,1,1),(76,'0585818580','0585818580','زبدة','أحمد ياسين','0585818580','medecin',1,1,1),(78,'058185867','058185867','حران ','قادري','058185867','medecin',1,1,1),(79,'0758185867','0758185867','علي','قلومة','0758185867',NULL,1,1,1),(111,'gfhfgh','gfhfgh','gg','fgh','gfhfgh',NULL,1,1,1),(112,'gfhfgh','gfhfgh','gg','fgh','gfhfgh',NULL,1,1,1),(119,'0758185867','0758185867','نسيم','سماحي','0758185867',NULL,1,1,1),(146,'0758185820','0758185820','نسيم','سماحي','0758185820','medecin',1,1,1),(148,'0458185867','0458185867','0458185867','0458185867','0458185867','doctor',1,1,1),(172,'0458185867','0458185867','بشير ','ساسي','0458185867','doctor',1,1,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-11 14:48:42
