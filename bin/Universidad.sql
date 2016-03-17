-- MySQL dump 10.13  Distrib 5.5.41, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: Universidad
-- ------------------------------------------------------
-- Server version	5.5.41-0ubuntu0.14.04.1

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
-- Table structure for table `Alumno`
--

DROP TABLE IF EXISTS `Alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Alumno` (
  `matricula` varchar(10) NOT NULL,
  `nombre` varchar(35) NOT NULL,
  `domicilio` varchar(70) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `carrera` varchar(3) NOT NULL,
  `plan` int(11) NOT NULL,
  PRIMARY KEY (`matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Alumno`
--

LOCK TABLES `Alumno` WRITE;
/*!40000 ALTER TABLE `Alumno` DISABLE KEYS */;
INSERT INTO `Alumno` VALUES ('148','Carlos','Morelos','121212','IMA',2010),('A01123400','Manuel Paz Lara','Tollocan #102','(722) 318-90-94','LDI',2008),('A0124675','Daniel Armando Carrillo Dimus','Privada Timo #342 B','(722) 174-16-72','ISD',2011),('A01363206','Mario Jacob García Navarro','La Besana #200','(722) 133-28-97','ISC',2011),('A013632800','Luis Arturo Mendoza Reyes','Av. Tecnológico #790','(722) 318-66-55','ARQ',2011),('A123','Rosa','Brisas','3245','LPA',-2011),('A987','David','Isra','3213','LOD',2011);
/*!40000 ALTER TABLE `Alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Curso`
--

DROP TABLE IF EXISTS `Curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Curso` (
  `clave` varchar(6) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `semestre` int(11) NOT NULL,
  `ndepto` int(11) NOT NULL,
  PRIMARY KEY (`clave`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`),
  KEY `fk_ndeptoC_idx` (`ndepto`),
  KEY `ix_clave` (`clave`),
  CONSTRAINT `fk_ndeptoC` FOREIGN KEY (`ndepto`) REFERENCES `Departamento` (`ndepto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Curso`
--

LOCK TABLES `Curso` WRITE;
/*!40000 ALTER TABLE `Curso` DISABLE KEYS */;
INSERT INTO `Curso` VALUES ('AR3019','Proyectos Inmobiliarios',9,180319),('DL3015','Diseño de Productos y Sistemas II',8,232405),('TC1018','Estructura de Datos',3,180307),('TC1024','Fundamentos de Programación',1,180307),('TC2017','Análisis y Diseño de Algoritmos',4,180307),('TI25','Redes I',3,48);
/*!40000 ALTER TABLE `Curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Departamento`
--

DROP TABLE IF EXISTS `Departamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Departamento` (
  `ndepto` int(11) NOT NULL,
  `nombre` varchar(55) NOT NULL,
  `clave_admin` varchar(10) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  PRIMARY KEY (`ndepto`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`),
  KEY `fk_clave_admin_idx` (`clave_admin`),
  CONSTRAINT `fk_clave_admin` FOREIGN KEY (`clave_admin`) REFERENCES `Profesor` (`clave`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Departamento`
--

LOCK TABLES `Departamento` WRITE;
/*!40000 ALTER TABLE `Departamento` DISABLE KEYS */;
INSERT INTO `Departamento` VALUES (48,'Contabilidad','L00','2015-04-07'),(10014,'Caja de Ahorro','L00165787','2015-04-01'),(18769,'Sistemas Digitales','L148','2015-04-07'),(180307,'Ciencias Computacionales','L00248237','2010-05-30'),(180319,'Arquitectura','L00640284','2008-07-20'),(232405,'Diseño Industrial','L01432419','2014-02-18'),(322414,'Diseño y Arquitectura','L00165787','2015-04-01');
/*!40000 ALTER TABLE `Departamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Grado`
--

DROP TABLE IF EXISTS `Grado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Grado` (
  `clave_profesor` varchar(10) NOT NULL,
  `grado_academico` varchar(45) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`clave_profesor`,`grado_academico`),
  CONSTRAINT `fk_clave_profesor` FOREIGN KEY (`clave_profesor`) REFERENCES `Profesor` (`clave`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Grado`
--

LOCK TABLES `Grado` WRITE;
/*!40000 ALTER TABLE `Grado` DISABLE KEYS */;
INSERT INTO `Grado` VALUES ('L00165787','Doctorado en Ciencias Computacionales','Universidad de Stanford'),('L00165787','Maestría en Física Cuántica','Universidad de Oxford'),('L00248237','Doctorado en Ciencias Computacionales','Universidad de Michigan'),('L00248237','Ingeniería en Mecánica','Universidad Nacional Autónoma de México'),('L00640284','Maestría en Diseño','Universidad Autónoma del Estado de México'),('L01432419','Maestría en Análisis de Materiales','Universidad Autónoma del Estado de Jalisco');
/*!40000 ALTER TABLE `Grado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Imparte`
--

DROP TABLE IF EXISTS `Imparte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Imparte` (
  `clave_profesor` varchar(10) NOT NULL,
  `clave_curso` varchar(6) NOT NULL,
  `grupo` int(11) NOT NULL,
  `horario` varchar(15) NOT NULL,
  PRIMARY KEY (`clave_curso`,`grupo`),
  KEY `fk_clave_curso_idx` (`clave_curso`),
  KEY `fk_clave_profesorI` (`clave_profesor`),
  CONSTRAINT `fk_clave_curso` FOREIGN KEY (`clave_curso`) REFERENCES `Curso` (`clave`),
  CONSTRAINT `fk_clave_profesorI` FOREIGN KEY (`clave_profesor`) REFERENCES `Profesor` (`clave`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Imparte`
--

LOCK TABLES `Imparte` WRITE;
/*!40000 ALTER TABLE `Imparte` DISABLE KEYS */;
INSERT INTO `Imparte` VALUES ('L148','AR3019',3,'13:30-14:30'),('L148','TC1018',1,'12:00-13:30'),('L00165787','TC1018',3,'07:30-09:00'),('L148','TC1024',1,'10:30-12:00'),('L148','TC1024',2,'09:00-10:30');
/*!40000 ALTER TABLE `Imparte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Profesor`
--

DROP TABLE IF EXISTS `Profesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Profesor` (
  `clave` varchar(10) NOT NULL,
  `nombre` varchar(35) NOT NULL,
  `domicilio` varchar(70) NOT NULL,
  `salario` float NOT NULL,
  `fecha_nac` date NOT NULL,
  `sexo` varchar(1) DEFAULT NULL,
  `ndepto` int(11) NOT NULL,
  PRIMARY KEY (`clave`),
  KEY `fk_ndepto_idx` (`ndepto`),
  KEY `ix_clave` (`clave`),
  CONSTRAINT `fk_ndepto` FOREIGN KEY (`ndepto`) REFERENCES `Departamento` (`ndepto`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Profesor`
--

LOCK TABLES `Profesor` WRITE;
/*!40000 ALTER TABLE `Profesor` DISABLE KEYS */;
INSERT INTO `Profesor` VALUES ('L00','Liam','Gallagher #432',3450,'1980-07-05','M',180307),('L00165787','Jaime Salazar Támez','Auorora Boreal #600',40000,'1950-05-14','M',180307),('L00248237','Jesús Gutiérrez Gómez','Venustiano Carranza #350',50000,'1968-04-08','M',180307),('L00640284','Jorge Víctor Loo Valverde','Relámpago #50',45000,'1986-08-04','M',180319),('L01432419','Rodrigo Cervantes Ramírez','Lomas del Campo #108 C',30000,'1989-11-29','M',232405),('L148','Eduardo','San Buena',1000,'1989-09-09','M',48);
/*!40000 ALTER TABLE `Profesor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Toma`
--

DROP TABLE IF EXISTS `Toma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Toma` (
  `matricula` varchar(10) NOT NULL,
  `clave_curso` varchar(6) NOT NULL,
  `grupo` int(11) NOT NULL,
  PRIMARY KEY (`matricula`,`clave_curso`),
  KEY `fk_clave_curso_idx` (`clave_curso`),
  CONSTRAINT `fk_curso` FOREIGN KEY (`clave_curso`) REFERENCES `Curso` (`clave`),
  CONSTRAINT `fk_matricula` FOREIGN KEY (`matricula`) REFERENCES `Alumno` (`matricula`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Toma`
--

LOCK TABLES `Toma` WRITE;
/*!40000 ALTER TABLE `Toma` DISABLE KEYS */;
INSERT INTO `Toma` VALUES ('A01123400','DL3015',0),('A0124675','TC1018',0),('A01363206','AR3019',2),('A01363206','TC1024',1),('A01363206','TC2017',0),('A013632800','AR3019',0),('A013632800','TC1024',1);
/*!40000 ALTER TABLE `Toma` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-09 23:37:39
