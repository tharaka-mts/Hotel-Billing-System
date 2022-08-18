-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 31, 2022 at 05:49 PM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel_and_resturant_billing_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `0`
--

CREATE TABLE `0` (
  `id` int(64) NOT NULL,
  `foodName` varchar(100) DEFAULT NULL,
  `unitPrice` double DEFAULT NULL,
  `noOfQuantity` double DEFAULT NULL,
  `total` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `0`
--

INSERT INTO `0` (`id`, `foodName`, `unitPrice`, `noOfQuantity`, `total`) VALUES
(1, 'Fried Rice - Vegetable', 2, 3, 6),
(2, 'Fried Rice - Vegetable', 2, 3, 6),
(3, 'Kottu - Egg', 3.5, 3, 10.5),
(4, 'Kottu - Egg', 3.5, 2, 7),
(5, 'Salad - Vegetable', 2.6, 4, 10.4),
(6, 'Salad - Vegetable', 2.6, 2, 5.2);

-- --------------------------------------------------------

--
-- Table structure for table `1`
--

CREATE TABLE `1` (
  `id` int(64) NOT NULL,
  `foodName` varchar(100) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `total` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `1`
--

INSERT INTO `1` (`id`, `foodName`, `price`, `quantity`, `total`) VALUES
(1, 'Kottu - Egg', 3.5, 2, 7),
(2, 'Salad - Vegetable', 2.6, 5, 13),
(3, 'Noodles - Sea food', 3.5, 4, 14),
(4, 'Sprite - 750ml', 1.8, 3, 5.4);

-- --------------------------------------------------------

--
-- Table structure for table `2`
--

CREATE TABLE `2` (
  `id` int(64) NOT NULL,
  `foodName` varchar(100) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `total` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `2`
--

INSERT INTO `2` (`id`, `foodName`, `price`, `quantity`, `total`) VALUES
(1, 'Noodles - Vegetable', 2.5, 3, 7.5),
(2, 'Soup - Chicken', 3, 5, 15);

-- --------------------------------------------------------

--
-- Table structure for table `3`
--

CREATE TABLE `3` (
  `id` int(64) NOT NULL,
  `foodName` varchar(100) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `total` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `3`
--

INSERT INTO `3` (`id`, `foodName`, `price`, `quantity`, `total`) VALUES
(1, 'Kottu - Cheese', 3.5, 4, 14),
(2, 'Sprite - 750ml', 1.8, 4, 7.2);

-- --------------------------------------------------------

--
-- Table structure for table `4`
--

CREATE TABLE `4` (
  `id` int(64) NOT NULL,
  `foodName` varchar(100) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `total` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `4`
--

INSERT INTO `4` (`id`, `foodName`, `price`, `quantity`, `total`) VALUES
(1, 'Soup - Chicken', 3, 1, 3),
(2, 'Fried Rice - Mixed', 3, 1, 3),
(3, 'Salad - Vegetable', 2.6, 2, 5.2),
(4, 'Sprite - 750ml', 1.8, 2, 3.6);

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `id` bigint(20) NOT NULL,
  `foodName` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` double NOT NULL,
  `total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `billdetail`
--

CREATE TABLE `billdetail` (
  `invoice` bigint(20) NOT NULL,
  `customerName` varchar(255) DEFAULT NULL,
  `givenMoney` double NOT NULL,
  `mobileNumber` int(11) NOT NULL,
  `totalMoney` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `billdetail`
--

INSERT INTO `billdetail` (`invoice`, `customerName`, `givenMoney`, `mobileNumber`, `totalMoney`) VALUES
(1, 'Lahiru Madushanka', 45, 765259905, 39.4),
(2, 'Jeevake Perera', 32, 705678987, 22.5),
(3, 'Tharaka Sadaruwan', 31, 756789456, 21.2),
(4, 'Geeth Sheheran', 20, 763456789, 14.799999999999999);

-- --------------------------------------------------------

--
-- Table structure for table `cashier`
--

CREATE TABLE `cashier` (
  `cashierId` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `dateOfBirth` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `nic` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phoneNumber` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cashier`
--

INSERT INTO `cashier` (`cashierId`, `address`, `dateOfBirth`, `email`, `firstName`, `gender`, `lastName`, `nic`, `password`, `phoneNumber`, `username`) VALUES
(1, 'Premananda, Mawatha, Madiha, Kamburugamuwa, Matara.', '1998-7-19', 'lahirumadushandl@gmail.com', 'Lahiru', 'Male', 'Madushanka', '982011574V', 'c1', 765259905, 'c'),
(2, 'Meegamuwa, Gampaha.', '199-5-28', 'jeevakePerera@gmail.com', 'Jeevake', 'Male', 'Perera', '992012576V', 'a', 765259897, 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `customerdetail`
--

CREATE TABLE `customerdetail` (
  `id` bigint(20) NOT NULL,
  `customerName` varchar(255) DEFAULT NULL,
  `givenMoney` double NOT NULL,
  `mobileNumber` int(11) NOT NULL,
  `totalMoney` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `food`
--

CREATE TABLE `food` (
  `id` bigint(20) NOT NULL,
  `foodName` varchar(255) DEFAULT NULL,
  `foodPrice` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `food`
--

INSERT INTO `food` (`id`, `foodName`, `foodPrice`) VALUES
(1, 'Fried Rice - Vegetable', 2),
(2, 'Fried Rice - Egg', 2.5),
(3, 'Fried Rice - Sea Food', 3.5),
(4, 'Fried Rice - Mixed', 3),
(5, 'Fried Rice - Chicken', 3.2),
(6, 'Noodles - Sea food', 3.5),
(7, 'Noodles - Vegetable', 2.5),
(8, 'Noodles - Chicken', 3.2),
(9, 'Noodles - Egg', 2.5),
(10, 'Noodles - Mixed', 3),
(11, 'Kottu - Egg', 3.5),
(12, 'Kottu - Chicken', 2.5),
(13, 'Kottu - Vegetable', 2.6),
(14, 'Kottu - Cheese', 3.5),
(15, 'Chicken Deviled', 4),
(16, 'Fish Deviled', 3.8),
(17, 'Fried Chicken', 4.2),
(18, 'Fried Fish', 4),
(19, 'Cuttle-fish', 3.9),
(20, 'Prawn', 4),
(21, 'Chopsuey - Vegetable', 3.2),
(22, 'Chopsuey - Seafood', 4.5),
(23, 'Salad - Vegetable', 2.6),
(24, 'Soup - Chicken', 3),
(25, 'Soup - Vegetable', 2.5),
(26, 'Cocacola - 750ml', 2),
(27, 'Sprite - 750ml', 1.8),
(28, 'Mountain Dew - 750ml', 2),
(29, 'Fried Rice - Vegetable', 2),
(39, 'gh', 1.3),
(43, 'ffg', 12);

-- --------------------------------------------------------

--
-- Table structure for table `foods`
--

CREATE TABLE `foods` (
  `foodID` int(6) NOT NULL,
  `foodName` varchar(50) NOT NULL,
  `foodPrice` double NOT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `foods`
--

INSERT INTO `foods` (`foodID`, `foodName`, `foodPrice`, `id`) VALUES
(1, 'Fried Rice - Vegetable ', 2, 0),
(2, 'Fried Rice - Egg', 2.5, 0),
(3, 'Fried Rice - Sea Food ', 3.5, 0),
(4, 'Fried Rice - Mixed', 3, 0),
(5, 'Fried Rice - Chicken', 3.2, 0),
(6, 'Noodles - Sea food', 3.5, 0),
(7, 'Noodles - Vegetable', 2.5, 0),
(8, 'Noodles - Chicken', 3.2, 0),
(9, 'Noodles - Egg', 2.5, 0),
(10, 'Noodles - Mixed', 3, 0),
(11, 'Kottu - Egg', 3.5, 0),
(12, 'Kottu - Chicken', 2.5, 0),
(13, 'Kottu - Vegetable', 2.6, 0),
(14, 'Kottu - Cheese', 3.5, 0),
(15, 'Chicken Deviled', 4, 0),
(16, 'Fish Deviled', 3.8, 0),
(17, 'Fried Chicken', 4.2, 0),
(18, 'Fried Fish', 4, 0),
(19, 'Cuttle-fish', 3.9, 0),
(20, 'Prawn', 4, 0),
(21, 'Chopsuey - Vegetable', 3.2, 0),
(22, 'Chopsuey - Seafood', 4.5, 0),
(23, 'Salad - Vegetable', 2.6, 0),
(24, 'Soup - Chicken', 3, 0),
(25, 'Soup - Vegetable', 2.5, 0),
(26, 'Cocacola - 750ml', 2, 0),
(27, 'Sprite - 750ml', 1.8, 0),
(28, 'Mountain Dew - 750ml', 2, 0),
(40, 'Fried Rice - Vegetable ', 2, 0);

-- --------------------------------------------------------

--
-- Table structure for table `guestdetail`
--

CREATE TABLE `guestdetail` (
  `geustID` bigint(20) NOT NULL,
  `additionalInfo` varchar(255) DEFAULT NULL,
  `arrivalDate` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `departureDate` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `noOfRooms` int(11) NOT NULL,
  `phone` int(11) NOT NULL,
  `roomType` varchar(255) DEFAULT NULL,
  `specialRequirements` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `guestdetail`
--

INSERT INTO `guestdetail` (`geustID`, `additionalInfo`, `arrivalDate`, `country`, `departureDate`, `email`, `firstName`, `lastName`, `noOfRooms`, `phone`, `roomType`, `specialRequirements`) VALUES
(1, 'cfdd', '1998', 'hth', '1994', 'rtgth', 'awer', 'ggh', 2, 1233, 'fffg', 'fghh');

-- --------------------------------------------------------

--
-- Table structure for table `hotelrooms`
--

CREATE TABLE `hotelrooms` (
  `id` varchar(4) NOT NULL,
  `roomType` varchar(50) NOT NULL,
  `rooms` varchar(10) NOT NULL,
  `perNight` double NOT NULL,
  `withBreakfast` double NOT NULL,
  `hBoard` double NOT NULL,
  `fBoard` double NOT NULL,
  `numOfRooms` int(11) NOT NULL,
  `currentAvailability` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hotelrooms`
--

INSERT INTO `hotelrooms` (`id`, `roomType`, `rooms`, `perNight`, `withBreakfast`, `hBoard`, `fBoard`, `numOfRooms`, `currentAvailability`) VALUES
('C001', 'classic', 'single', 30, 40, 60, 90, 4, 4),
('C002', 'classic', 'double', 50, 60, 90, 120, 6, 6),
('C003', 'classic', 'family', 80, 90, 120, 150, 3, 3),
('D001', 'deluxe', 'single', 50, 60, 80, 110, 5, 5),
('D002', 'deluxe', 'double', 70, 90, 120, 140, 4, 4),
('D003', 'deluxe', 'family', 100, 110, 140, 170, 2, 2),
('P001', 'presidential', 'single', 70, 80, 100, 130, 2, 2),
('P002', 'presidential', 'double', 90, 110, 150, 190, 2, 2),
('P003', 'presidential', 'family', 120, 150, 190, 250, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `purchasefood`
--

CREATE TABLE `purchasefood` (
  `invoice` int(11) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `mobileNo` int(10) NOT NULL,
  `food` varchar(60) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `rcusdetail`
--

CREATE TABLE `rcusdetail` (
  `id` int(11) NOT NULL,
  `customerName` varchar(60) NOT NULL,
  `mNumber` int(10) NOT NULL,
  `givenMoney` double NOT NULL,
  `rprice` double NOT NULL,
  `totalMoney` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `0`
--
ALTER TABLE `0`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `1`
--
ALTER TABLE `1`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `2`
--
ALTER TABLE `2`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `3`
--
ALTER TABLE `3`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `4`
--
ALTER TABLE `4`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `billdetail`
--
ALTER TABLE `billdetail`
  ADD PRIMARY KEY (`invoice`);

--
-- Indexes for table `cashier`
--
ALTER TABLE `cashier`
  ADD PRIMARY KEY (`cashierId`);

--
-- Indexes for table `customerdetail`
--
ALTER TABLE `customerdetail`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `food`
--
ALTER TABLE `food`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `foods`
--
ALTER TABLE `foods`
  ADD PRIMARY KEY (`foodID`);

--
-- Indexes for table `guestdetail`
--
ALTER TABLE `guestdetail`
  ADD PRIMARY KEY (`geustID`);

--
-- Indexes for table `hotelrooms`
--
ALTER TABLE `hotelrooms`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rcusdetail`
--
ALTER TABLE `rcusdetail`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `0`
--
ALTER TABLE `0`
  MODIFY `id` int(64) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `1`
--
ALTER TABLE `1`
  MODIFY `id` int(64) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `2`
--
ALTER TABLE `2`
  MODIFY `id` int(64) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `3`
--
ALTER TABLE `3`
  MODIFY `id` int(64) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `4`
--
ALTER TABLE `4`
  MODIFY `id` int(64) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;
--
-- AUTO_INCREMENT for table `billdetail`
--
ALTER TABLE `billdetail`
  MODIFY `invoice` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `cashier`
--
ALTER TABLE `cashier`
  MODIFY `cashierId` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `customerdetail`
--
ALTER TABLE `customerdetail`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `food`
--
ALTER TABLE `food`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;
--
-- AUTO_INCREMENT for table `foods`
--
ALTER TABLE `foods`
  MODIFY `foodID` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;
--
-- AUTO_INCREMENT for table `guestdetail`
--
ALTER TABLE `guestdetail`
  MODIFY `geustID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `rcusdetail`
--
ALTER TABLE `rcusdetail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
