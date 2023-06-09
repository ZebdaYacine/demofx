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
  PRIMARY KEY (`id`),
  KEY `idPatient` (`idPatient`),
  KEY `idFollow` (`idFollow`),
  CONSTRAINT `diagnostic_ibfk_1` FOREIGN KEY (`idPatient`) REFERENCES `patient` (`id`),
  CONSTRAINT `diagnostic_ibfk_2` FOREIGN KEY (`idFollow`) REFERENCES `follow` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-23 12:12:26
