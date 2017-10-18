-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 11, 2017 at 11:13 AM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";



/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `alumnos_adat`
--
CREATE DATABASE IF NOT EXISTS `alumnos_adat` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `alumnos_adat`;

-- --------------------------------------------------------

--
-- Table structure for table `alumnos`
--

DROP TABLE IF EXISTS `alumnos`;
CREATE TABLE IF NOT EXISTS `alumnos` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellido` varchar(20) NOT NULL,
  `telefono` int(9) NOT NULL,
  `nacionalidad` varchar(15) NOT NULL,
  `titulacion` int(11) NOT NULL,
  PRIMARY KEY (`cod`),
  UNIQUE KEY `dni` (`dni`),
  KEY `titulacion` (`titulacion`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `cursos`
--

DROP TABLE IF EXISTS `cursos`;
CREATE TABLE IF NOT EXISTS `cursos` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(40) NOT NULL,
  PRIMARY KEY (`cod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `titulaciones`
--

DROP TABLE IF EXISTS `titulaciones`;
CREATE TABLE IF NOT EXISTS `titulaciones` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(40) NOT NULL,
  PRIMARY KEY (`cod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `titulaciones_cursos`
--

DROP TABLE IF EXISTS `titulaciones_cursos`;
CREATE TABLE IF NOT EXISTS `titulaciones_cursos` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `cod_curso` int(11) NOT NULL,
  `cod_titulacion` int(11) NOT NULL,
  PRIMARY KEY (`cod`),
  UNIQUE KEY `cod_curso` (`cod_curso`,`cod_titulacion`),
  KEY `cod_titulacion` (`cod_titulacion`),
  KEY `cod` (`cod`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

-- 
-- Constraints for table `alumnos`
--
ALTER TABLE `alumnos`
  ADD CONSTRAINT `alumnos_ibfk_1` FOREIGN KEY (`titulacion`) REFERENCES `titulaciones_cursos` (`cod`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `titulaciones_cursos`
--
ALTER TABLE `titulaciones_cursos`
  ADD CONSTRAINT `titulaciones_cursos_ibfk_1` FOREIGN KEY (`cod_titulacion`) REFERENCES `titulaciones` (`cod`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `titulaciones_cursos_ibfk_2` FOREIGN KEY (`cod_curso`) REFERENCES `cursos` (`cod`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
