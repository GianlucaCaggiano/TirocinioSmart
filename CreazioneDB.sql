CREATE DATABASE  IF NOT EXISTS `tirociniosmart` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tirociniosmart`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: tirociniosmart
-- ------------------------------------------------------
-- Server version	5.7.15-log

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
-- Table structure for table `azienda`
--

DROP TABLE IF EXISTS `azienda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `azienda` (
  `Email` char(64) NOT NULL,
  `LuogoNascita` char(30) NOT NULL,
  `DataNascita` date NOT NULL,
  `Denominazione` char(30) NOT NULL,
  `Citta` char(30) NOT NULL,
  `CAP` char(5) NOT NULL,
  `Via` char(30) NOT NULL,
  `Telefono` char(10) DEFAULT NULL,
  `SitoWeb` char(50) DEFAULT NULL,
  `ChiSiamo` varchar(255) DEFAULT NULL,
  `ConvenzioneID` int(10) DEFAULT NULL,
  `abilitato` tinyint(1) NOT NULL,
  PRIMARY KEY (`Email`),
  KEY `FKAzienda489468` (`Email`),
  KEY `sottostare_idx` (`ConvenzioneID`),
  CONSTRAINT `FKAzienda489468` FOREIGN KEY (`Email`) REFERENCES `utente` (`User`) ON DELETE CASCADE,
  CONSTRAINT `sottostare` FOREIGN KEY (`ConvenzioneID`) REFERENCES `convenzione` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `azienda`
--

LOCK TABLES `azienda` WRITE;
/*!40000 ALTER TABLE `azienda` DISABLE KEYS */;
INSERT INTO `azienda` VALUES ('accasoftware@test.it','Firenze','1956-08-04','HS s.r.l.','Bisaccia','83044','Via pioppi, 13','0827488965','www.accasoftware.it','test',7,0),('convenzione2@gmail.com','Torino','1957-10-06','C.venzione2','Ischia','81203','Via rai, 45',NULL,NULL,NULL,7,1),('convenzione@live.it','Milano','1964-02-14','C.venzione','Capri','82011','Via test, 18','0825124578','www.convenzione.it','nessuno',7,1),('testsoftware@unina.it','Napoli','1957-01-02','C.S.T.P','Ariano','83031','Via caso, 15','0825828485',NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `azienda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `convenzione`
--

DROP TABLE IF EXISTS `convenzione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `convenzione` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `Data` date NOT NULL,
  `Specifiche` char(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=176 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `convenzione`
--

LOCK TABLES `convenzione` WRITE;
/*!40000 ALTER TABLE `convenzione` DISABLE KEYS */;
INSERT INTO `convenzione` VALUES (7,'2018-01-30','Serve per il test');
/*!40000 ALTER TABLE `convenzione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professore`
--

DROP TABLE IF EXISTS `professore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professore` (
  `Email` char(64) NOT NULL,
  `Autorizzato` tinyint(1) NOT NULL DEFAULT '0',
  `Materia` char(64) DEFAULT NULL,
  PRIMARY KEY (`Email`),
  KEY `FKProfessore321420` (`Email`),
  CONSTRAINT `FKProfessore321420` FOREIGN KEY (`Email`) REFERENCES `utente` (`User`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professore`
--

LOCK TABLES `professore` WRITE;
/*!40000 ALTER TABLE `professore` DISABLE KEYS */;
INSERT INTO `professore` VALUES ('massimo@unisa.it',1,'IS'),('pino@unisa.it',1,'PW'),('rossi@unisa.it',0,'BD');
/*!40000 ALTER TABLE `professore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `progettoformativo`
--

DROP TABLE IF EXISTS `progettoformativo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `progettoformativo` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `AziendaEmail` char(64) NOT NULL,
  `SegreteriaUsername` char(64) NOT NULL,
  `StudenteMatricola` char(10) NOT NULL,
  `ProfessoreEmail` char(64) NOT NULL,
  `Obiettivi` char(255) NOT NULL,
  `DataInizio` date DEFAULT NULL,
  `DataFine` date DEFAULT NULL,
  `convalidaProf` tinyint(1) DEFAULT '0',
  `convalidaSegr` tinyint(1) DEFAULT '0',
  `sottoscrizioneStu` tinyint(1) DEFAULT '0',
  `termine` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `compilare` (`AziendaEmail`),
  KEY `sottoscrivere` (`StudenteMatricola`),
  KEY `confermare` (`ProfessoreEmail`),
  KEY `approvare` (`SegreteriaUsername`),
  CONSTRAINT `approvare` FOREIGN KEY (`SegreteriaUsername`) REFERENCES `segreteria` (`Username`),
  CONSTRAINT `compilare` FOREIGN KEY (`AziendaEmail`) REFERENCES `azienda` (`Email`),
  CONSTRAINT `confermare` FOREIGN KEY (`ProfessoreEmail`) REFERENCES `professore` (`Email`),
  CONSTRAINT `sottoscrivere` FOREIGN KEY (`StudenteMatricola`) REFERENCES `studente` (`Matricola`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `progettoformativo`
--

LOCK TABLES `progettoformativo` WRITE;
/*!40000 ALTER TABLE `progettoformativo` DISABLE KEYS */;
/*!40000 ALTER TABLE `progettoformativo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questionarioazienda`
--

DROP TABLE IF EXISTS `questionarioazienda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questionarioazienda` (
  `AziendaEmail` char(64) NOT NULL,
  `ProgettoFormativoID` int(10) NOT NULL,
  `Voto` smallint(2) NOT NULL,
  PRIMARY KEY (`AziendaEmail`,`ProgettoFormativoID`),
  KEY `compiere` (`AziendaEmail`),
  KEY `fk545_idx` (`ProgettoFormativoID`),
  CONSTRAINT `compiere` FOREIGN KEY (`AziendaEmail`) REFERENCES `azienda` (`Email`),
  CONSTRAINT `fk545` FOREIGN KEY (`ProgettoFormativoID`) REFERENCES `progettoformativo` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionarioazienda`
--

LOCK TABLES `questionarioazienda` WRITE;
/*!40000 ALTER TABLE `questionarioazienda` DISABLE KEYS */;
/*!40000 ALTER TABLE `questionarioazienda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questionariostudente`
--

DROP TABLE IF EXISTS `questionariostudente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questionariostudente` (
  `ProgettoFormativoID` int(10) NOT NULL,
  `StudenteMatricola` char(10) NOT NULL,
  `Voto` smallint(2) NOT NULL,
  `esperienzaUtile` tinyint(1) NOT NULL,
  `conoscenzeSuff` tinyint(1) NOT NULL,
  `utilita` smallint(2) NOT NULL,
  PRIMARY KEY (`ProgettoFormativoID`,`StudenteMatricola`),
  KEY `eseguire` (`StudenteMatricola`),
  KEY `FKQuestionar242182` (`ProgettoFormativoID`),
  CONSTRAINT `eseguire` FOREIGN KEY (`StudenteMatricola`) REFERENCES `studente` (`Matricola`),
  CONSTRAINT `frshbdwd45` FOREIGN KEY (`ProgettoFormativoID`) REFERENCES `progettoformativo` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionariostudente`
--

LOCK TABLES `questionariostudente` WRITE;
/*!40000 ALTER TABLE `questionariostudente` DISABLE KEYS */;
/*!40000 ALTER TABLE `questionariostudente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registrotirocinio`
--

DROP TABLE IF EXISTS `registrotirocinio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registrotirocinio` (
  `ID` int(10) NOT NULL,
  `ProgettoFormativoID` int(10) NOT NULL,
  `convalidaAzienda` tinyint(1) NOT NULL,
  `convalidaProf` tinyint(1) NOT NULL,
  `convalidaSegr` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `riferire_idx` (`ProgettoFormativoID`),
  CONSTRAINT `riferire` FOREIGN KEY (`ProgettoFormativoID`) REFERENCES `progettoformativo` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registrotirocinio`
--

LOCK TABLES `registrotirocinio` WRITE;
/*!40000 ALTER TABLE `registrotirocinio` DISABLE KEYS */;
/*!40000 ALTER TABLE `registrotirocinio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `richiestatirocinio`
--

DROP TABLE IF EXISTS `richiestatirocinio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `richiestatirocinio` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `AziendaEmail` char(64) NOT NULL,
  `ProfessoreEmail` char(64) NOT NULL,
  `ConvalidaAzienda` tinyint(1) NOT NULL DEFAULT '0',
  `ConvalidaProf` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `siglare` (`ProfessoreEmail`),
  KEY `autorizzare` (`AziendaEmail`),
  CONSTRAINT `autorizzare` FOREIGN KEY (`AziendaEmail`) REFERENCES `azienda` (`Email`),
  CONSTRAINT `siglare` FOREIGN KEY (`ProfessoreEmail`) REFERENCES `professore` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `richiestatirocinio`
--

LOCK TABLES `richiestatirocinio` WRITE;
/*!40000 ALTER TABLE `richiestatirocinio` DISABLE KEYS */;
INSERT INTO `richiestatirocinio` VALUES (4,'accasoftware@test.it','pino@unisa.it',0,0);
/*!40000 ALTER TABLE `richiestatirocinio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rigaregistro`
--

DROP TABLE IF EXISTS `rigaregistro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rigaregistro` (
  `numRiga` int(10) NOT NULL,
  `RegistroTirocinioID` int(10) NOT NULL,
  `Relazione` varchar(255) NOT NULL,
  `oraInizio` time(6) NOT NULL,
  `oraFine` time(6) NOT NULL,
  `Giorno` date NOT NULL,
  `StudenteMatricola` char(10) NOT NULL,
  PRIMARY KEY (`numRiga`,`RegistroTirocinioID`),
  KEY `comporre` (`RegistroTirocinioID`),
  KEY `stilare` (`StudenteMatricola`),
  CONSTRAINT `comporre` FOREIGN KEY (`RegistroTirocinioID`) REFERENCES `registrotirocinio` (`ID`),
  CONSTRAINT `stilare` FOREIGN KEY (`StudenteMatricola`) REFERENCES `studente` (`Matricola`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rigaregistro`
--

LOCK TABLES `rigaregistro` WRITE;
/*!40000 ALTER TABLE `rigaregistro` DISABLE KEYS */;
/*!40000 ALTER TABLE `rigaregistro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `segreteria`
--

DROP TABLE IF EXISTS `segreteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `segreteria` (
  `Username` char(64) NOT NULL,
  `Telefono` char(10) DEFAULT NULL,
  `Email` char(64) NOT NULL,
  PRIMARY KEY (`Username`),
  UNIQUE KEY `Email` (`Email`),
  KEY `amministra` (`Username`),
  CONSTRAINT `amministra` FOREIGN KEY (`Username`) REFERENCES `utente` (`User`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `segreteria`
--

LOCK TABLES `segreteria` WRITE;
/*!40000 ALTER TABLE `segreteria` DISABLE KEYS */;
INSERT INTO `segreteria` VALUES ('segreteriaUnisa','089961111','carrierestudenti.di@unisa.it');
/*!40000 ALTER TABLE `segreteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studente`
--

DROP TABLE IF EXISTS `studente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studente` (
  `Matricola` char(10) NOT NULL,
  `Email` char(64) NOT NULL,
  `DataNascita` date NOT NULL,
  `LuogoNascita` char(30) NOT NULL,
  `RichiestaTirocinioID` int(10) DEFAULT NULL,
  `abilitato` tinyint(1) NOT NULL,
  PRIMARY KEY (`Matricola`),
  UNIQUE KEY `Email` (`Email`),
  KEY `FKStudente132973` (`Email`),
  KEY `effettuare_idx` (`RichiestaTirocinioID`),
  CONSTRAINT `FKStudente132973` FOREIGN KEY (`Email`) REFERENCES `utente` (`User`) ON DELETE CASCADE,
  CONSTRAINT `effettuare` FOREIGN KEY (`RichiestaTirocinioID`) REFERENCES `richiestatirocinio` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studente`
--

LOCK TABLES `studente` WRITE;
/*!40000 ALTER TABLE `studente` DISABLE KEYS */;
INSERT INTO `studente` VALUES ('0512103699','francesco@studenti.unisa.it','1996-08-12','Nola',4,0),('0512105879','ciccio@studenti.unisa.it','1995-07-08','Bari',NULL,1);
/*!40000 ALTER TABLE `studente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utente` (
  `User` char(64) NOT NULL,
  `Password` char(32) NOT NULL,
  `Nome` char(30) NOT NULL,
  `Cognome` char(30) NOT NULL,
  `Tipo` char(2) NOT NULL,
  PRIMARY KEY (`User`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES ('accasoftware@test.it','3216549870','Pasquale','Michele','AZ'),('ciccio@studenti.unisa.it','987654321','Ciccio','Pasticcio','ST'),('convenzione2@gmail.com','1234567891','Luca','Verde','AZ'),('convenzione@live.it','1234567891','Gian','Carlo','AZ'),('francesco@studenti.unisa.it','1234567891','francesco','grasso','ST'),('massimo@unisa.it','1234567891','Massimo','Riccio','PR'),('pino@unisa.it','9876543210','Pino','Sbirillino','PR'),('rossi@unisa.it','9874563210','Luca','Rossi','PR'),('segreteriaUnisa','1234567891','Michele','Pasquale','SR'),('testsoftware@unina.it','9876543219','Paolo','Rossi','AZ');
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-17 13:14:15
