-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-05-2026 a las 10:49:23
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyecto`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `buy`
--

CREATE TABLE `buy` (
  `ID_Merchandise` int(11) NOT NULL,
  `ID_Buyer` int(11) NOT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `Price` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `buyers`
--

CREATE TABLE `buyers` (
  `ID_Buyer` int(11) NOT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `Last_name` varchar(50) DEFAULT NULL,
  `Salary` int(11) DEFAULT NULL,
  `Password` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `buyers`
--

INSERT INTO `buyers` (`ID_Buyer`, `Name`, `Last_name`, `Salary`, `Password`) VALUES
(100, 'Sara', 'Alvarez', 2000, 'compra_1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `merchandise`
--

CREATE TABLE `merchandise` (
  `ID_Merchandise` int(11) NOT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `Price` int(11) DEFAULT NULL,
  `ID_Supplier` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `supplier`
--

CREATE TABLE `supplier` (
  `ID_Supplier` int(11) NOT NULL,
  `Name` varchar(30) DEFAULT NULL,
  `Phone_number` int(11) DEFAULT NULL,
  `Address` varchar(50) DEFAULT NULL,
  `Postcode` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `buy`
--
ALTER TABLE `buy`
  ADD PRIMARY KEY (`ID_Merchandise`,`ID_Buyer`),
  ADD KEY `fkBuyBuyer` (`ID_Buyer`);

--
-- Indices de la tabla `buyers`
--
ALTER TABLE `buyers`
  ADD PRIMARY KEY (`ID_Buyer`);

--
-- Indices de la tabla `merchandise`
--
ALTER TABLE `merchandise`
  ADD PRIMARY KEY (`ID_Merchandise`),
  ADD KEY `fkSupplierMerch` (`ID_Supplier`);

--
-- Indices de la tabla `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`ID_Supplier`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `buy`
--
ALTER TABLE `buy`
  ADD CONSTRAINT `fkBuyBuyer` FOREIGN KEY (`ID_Buyer`) REFERENCES `buyers` (`ID_Buyer`),
  ADD CONSTRAINT `fkBuyMerch` FOREIGN KEY (`ID_Merchandise`) REFERENCES `merchandise` (`ID_Merchandise`);

--
-- Filtros para la tabla `merchandise`
--
ALTER TABLE `merchandise`
  ADD CONSTRAINT `fkSupplierMerch` FOREIGN KEY (`ID_Supplier`) REFERENCES `supplier` (`ID_Supplier`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
