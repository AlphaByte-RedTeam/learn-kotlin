-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 30, 2022 at 08:32 AM
-- Server version: 10.5.16-MariaDB
-- PHP Version: 7.3.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id17750432_db_android`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_absenkaryawan`
--

CREATE TABLE `tb_absenkaryawan` (
  `idabsen` int(11) NOT NULL,
  `tanggal` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `waktumasuk` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `waktukeluar` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nik` varchar(11) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_absenkaryawan`
--
ALTER TABLE `tb_absenkaryawan`
  ADD PRIMARY KEY (`idabsen`);


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
