-- phpMyAdmin SQL Dump
-- version 3.5.8
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 19, 2017 at 11:22 PM
-- Server version: 5.5.32
-- PHP Version: 5.4.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `1022681`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_outlet`
--

CREATE TABLE IF NOT EXISTS `tbl_outlet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sr_id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `owner` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mobile` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `lat` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `lon` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `verified` tinyint(4) NOT NULL,
  `token` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=62 ;

--
-- Dumping data for table `tbl_outlet`
--

INSERT INTO `tbl_outlet` (`id`, `sr_id`, `name`, `address`, `owner`, `mobile`, `lat`, `lon`, `verified`, `token`) VALUES
(1, 9, 'AL-AMIN G. STORE', 'KA-10/2 SOUTH KURIL', 'AL-AMIN', '01876560999', '23.7814755', '90.3643269', 1, '24902049808252961'),
(57, 7, 'mokbul store', 'kallanpur', 'mokbul', '01777866432', '23.7812166', '90.3642069', 1, '24909297347461121'),
(12, 9, 'ALOM G. STORE', 'SAWRA BAZAR ROAD', 'ALOM', '01765423487', '23.7814752', '90.3643225', 1, '24902049808252960'),
(11, 9, 'ANE DEPARTMENTAL STORE', 'SAWRA BAZAR ROAD', 'ANE', '01987645643', '23.7814622', '90.3642951', 1, '24902049808252964'),
(13, 9, 'ARSHAD G. STORE', 'NADDA KACHABAZAR ROAD', 'ARSHAD', '01798745632', '23.7815059', '90.3643253', 1, '24902049808252955'),
(14, 7, 'ANIK ENTERPRISE', '146 JOAR SHARA', 'ANIK', '01709867809', '23.7812166', '90.3642069', 1, '24909297347461122'),
(56, 7, 'mijan stote', 'kallanpur', 'mijan', '02987525656', '23.7812728', '90.3642297', 1, '24909297347461120'),
(55, 10, 'Tromra Gen Store', 'dhaka, kallanpur', 'majid', '017508725226', '23.7812166', '90.3642069', 1, '24902049808252966'),
(28, 7, 'BIJOY TELECOM', 'KATCHA BAZAR ROAD, NADDA', 'BIJOY', '01897876555', '23.7812166', '90.3642069', 1, '24910745892290563'),
(30, 7, 'BISMILLAH G. STORE', 'BAZAR ROAD', 'BISMILLAH', '01987654321', '0.0', '0.0', 1, '24902049808252962'),
(31, 9, 'BORISHAL TRADERS', 'BAZAR ROAD,NADDA', 'ROBI', '01798734565', '23.7814732', '90.3643153', 1, '24902049808252958'),
(32, 7, 'BRISTY G. STORE(C)', '51/1 NADDA SARKERBARI ROAD NATUN BAZAR', 'SARKER', '01709845678', '', '', 0, '24902049808252940'),
(33, 7, 'HAZI & SONS', '53/1 NADDA BAZAR', 'HAZI', '01709845322', '', '', 0, '24902049808252941'),
(34, 7, 'MIRAN STORE', 'KATCHA BAZAR,NADDA', 'MIRAN', '01809871333', '0.0', '0.0', 1, '24902049808252963'),
(35, 7, 'KHAN G. STORE', '32 JOAR SHARA ', 'SHARA', '01834646666', '', '', 0, '24902049808252943'),
(36, 7, 'MAHEDI HASAN', 'NODDA', 'MANEDI', '01956764390', '', '', 0, '24902049808252944'),
(37, 9, 'MALIHA G. STORE', '52/1 NEAR NADDA BAZAR', 'MALEK', '01788809099', '23.7814718', '90.3643161', 1, '24902049808252965'),
(51, 7, 'MAMUN G. STORE', 'NODDA DORKAR BARI', 'MAMUN', '01987654421', '23.7812166', '90.3642069', 1, '24910745892290565'),
(47, 7, 'MARJUL STORE', 'NADDA', 'MARJUL', '01789065655', '', '', 0, '24902049808252947'),
(48, 9, 'MAYAR DOA G. STORE', 'JOAR SHARA BAZAR', 'ROSHID', '01909944121', '23.7814716', '90.3643106', 1, '24902049808252957'),
(49, 9, 'MIZAN STORE', 'KA-36 SARKER ROAD NADDA', 'MIZAN', '01934234466', '23.7814727', '90.3643164', 1, '24902049808252956'),
(50, 7, 'MOLLA G. STORE', '22/1 SARKER BARI ROAD NADDA', 'MOLLA', '01898756577', '', '', 0, '24902049808252950'),
(52, 7, 'NIJHUM ENTERPRIZE', 'KA-21/2A,D. KURIL, BADDA', 'NISHAT', '01792343312', '23.7814723', '90.3643099', 1, '24902049808252951'),
(53, 9, 'RAKIB STORE', 'BAZAR ROAD NADDA', 'RAKIB', '01865645544', '23.7813869', '90.3642722', 1, '24902049808252952'),
(54, 7, 'MA G. STORE', 'NADDA BAZAR ROAD', 'MIRAN', '01898980999', '23.7814639', '90.3642835', 1, '24902049808252953'),
(58, 9, 'ABCD', 'dhaka', 'raju', '01928765422', '23.795376', '90.4258113', 1, '24910745892290560'),
(59, 7, 'mahabub store', 'kallanpur', 'mahabub', '01933476588', '23.7812166', '90.3642069', 1, '24910745892290561'),
(60, 7, 'mahamud store', 'kallanpur', 'mahamud', '01723454344', '23.7814815', '90.3643233', 1, '24910745892290562'),
(61, 7, 'zafor store', 'kallanpur', 'zafor', '01987677895', '23.781478', '90.3643516', 1, '24910745892290564');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_sales_order`
--

CREATE TABLE IF NOT EXISTS `tbl_sales_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `local_so_id` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `order_time` date NOT NULL,
  `order_time_stamp` datetime NOT NULL,
  `outlet_id` int(11) NOT NULL,
  `sr_id` int(11) NOT NULL,
  `order_amount` double NOT NULL,
  `distance` double NOT NULL DEFAULT '-1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=17 ;

--
-- Dumping data for table `tbl_sales_order`
--

INSERT INTO `tbl_sales_order` (`id`, `local_so_id`, `order_time`, `order_time_stamp`, `outlet_id`, `sr_id`, `order_amount`, `distance`) VALUES
(1, '55_17-01-14-01:21:18', '2017-01-14', '2017-01-14 01:21:18', 55, 10, 2694, 31.1583709716797),
(2, '55_17-01-16-01:11:12', '2017-01-16', '2017-01-16 01:11:12', 55, 10, 120, 0),
(3, '55_17-01-17-00:22:17', '2017-01-17', '2017-01-17 00:22:17', 55, 10, 330, 38.4503021240234),
(4, '55_17-01-17-00:23:12', '2017-01-17', '2017-01-17 00:23:12', 55, 10, 1020, 38.4936103820801),
(5, '56_17-01-19-01:16:17', '2017-01-19', '2017-01-19 01:16:17', 56, 7, 1254, 6.64408349990845),
(6, '57_17-01-19-01:20:27', '2017-01-19', '2017-01-19 01:20:27', 57, 7, 0, 0),
(7, '57_17-01-19-01:20:41', '2017-01-19', '2017-01-19 01:20:41', 57, 7, 3960, 0),
(8, '14_17-01-19-01:21:56', '2017-01-19', '2017-01-19 01:21:56', 14, 7, 750, 10053159),
(9, '37_17-01-19-15:16:24', '2017-01-19', '2017-01-19 15:16:24', 37, 9, 3180, 6453.8515625),
(10, '59_17-01-19-18:31:21', '2017-01-19', '2017-01-19 18:31:21', 59, 7, 2923, 0),
(11, '60_17-01-19-18:35:39', '2017-01-19', '2017-01-19 18:35:39', 60, 7, 957, 9.55915069580078),
(12, '56_17-01-19-18:37:20', '2017-01-19', '2017-01-19 18:37:20', 56, 7, 2146, 24.8746757507324),
(13, '30_17-01-19-18:37:43', '2017-01-19', '2017-01-19 18:37:43', 30, 7, 2356, 10053171),
(14, '28_17-01-19-18:38:25', '2017-01-19', '2017-01-19 18:38:25', 28, 7, 3630, 10053159),
(15, '57_17-01-19-18:38:56', '2017-01-19', '2017-01-19 18:38:56', 57, 7, 4680, 0),
(16, '51_17-01-19-21:14:28', '2017-01-19', '2017-01-19 21:14:28', 51, 7, 600, 10053159);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_sales_order_line`
--

CREATE TABLE IF NOT EXISTS `tbl_sales_order_line` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `so_id` int(11) NOT NULL,
  `sku_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `unit_price` double NOT NULL,
  `total_price` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=50 ;

--
-- Dumping data for table `tbl_sales_order_line`
--

INSERT INTO `tbl_sales_order_line` (`id`, `so_id`, `sku_id`, `quantity`, `unit_price`, `total_price`) VALUES
(1, 1, 8, 6, 120, 720),
(2, 1, 3, 1, 540, 540),
(3, 1, 9, 1, 60, 60),
(4, 1, 4, 4, 150, 600),
(5, 1, 7, 6, 79, 474),
(6, 1, 6, 2, 150, 300),
(7, 2, 9, 2, 60, 120),
(8, 3, 9, 3, 60, 180),
(9, 3, 4, 1, 150, 150),
(10, 4, 4, 6, 150, 900),
(11, 4, 9, 2, 60, 120),
(12, 5, 9, 5, 60, 300),
(13, 5, 8, 4, 120, 480),
(14, 5, 7, 6, 79, 474),
(15, 7, 8, 2, 120, 240),
(16, 7, 3, 3, 540, 1620),
(17, 7, 4, 5, 150, 750),
(18, 7, 6, 9, 150, 1350),
(19, 8, 4, 5, 150, 750),
(20, 9, 9, 8, 60, 480),
(21, 9, 3, 5, 540, 2700),
(22, 10, 8, 5, 120, 600),
(23, 10, 3, 2, 540, 1080),
(24, 10, 9, 4, 60, 240),
(25, 10, 7, 6, 79, 474),
(26, 10, 6, 3, 150, 450),
(27, 10, 7, 1, 79, 79),
(28, 11, 9, 2, 60, 120),
(29, 11, 7, 3, 79, 237),
(30, 11, 6, 4, 150, 600),
(31, 12, 3, 2, 540, 1080),
(32, 12, 4, 5, 150, 750),
(33, 12, 7, 4, 79, 316),
(34, 13, 8, 5, 120, 600),
(35, 13, 9, 4, 60, 240),
(36, 13, 4, 2, 150, 300),
(37, 13, 6, 6, 150, 900),
(38, 13, 7, 4, 79, 316),
(39, 14, 8, 2, 120, 240),
(40, 14, 3, 3, 540, 1620),
(41, 14, 4, 4, 150, 600),
(42, 14, 6, 5, 150, 750),
(43, 14, 9, 7, 60, 420),
(44, 15, 3, 5, 540, 2700),
(45, 15, 9, 3, 60, 180),
(46, 15, 6, 4, 150, 600),
(47, 15, 4, 8, 150, 1200),
(48, 16, 8, 2, 120, 240),
(49, 16, 9, 6, 60, 360);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_sku`
--

CREATE TABLE IF NOT EXISTS `tbl_sku` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` double NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `token` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=10 ;

--
-- Dumping data for table `tbl_sku`
--

INSERT INTO `tbl_sku` (`id`, `price`, `name`, `token`) VALUES
(3, 540, 'dano 500GM', '24902049808252970'),
(4, 150, 'maggi sup 400gm', '24902049808252971'),
(6, 150, 'nido 500gm', '24902049808252972'),
(7, 79, 'horlicks 300gm', '24902049808252973'),
(8, 120, 'Maggi 8 pack 62GM', '24902049808252967'),
(9, 60, 'Maggi 4 pack 62GM', '24902049808252968');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE IF NOT EXISTS `tbl_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `full_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=11 ;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`id`, `user_name`, `password`, `full_name`) VALUES
(7, 'ritu', '12345', 'Mahabuba Khatun'),
(9, 'mahdi', '12345', 'Md Mahdi'),
(10, 'sr001', '12345', 'Md. Majidur Rahman');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
