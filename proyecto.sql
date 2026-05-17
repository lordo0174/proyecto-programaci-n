-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-05-2026 a las 19:56:08
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
  `ID_Buy` int(11) NOT NULL,
  `ID_Merchandise` int(11) DEFAULT NULL,
  `ID_Supplier` int(11) DEFAULT NULL,
  `Cost` int(11) NOT NULL,
  `Purchase_Date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `buy`
--

INSERT INTO `buy` (`ID_Buy`, `ID_Merchandise`, `ID_Supplier`, `Cost`, `Purchase_Date`) VALUES
(5, 1, 3, 40, '2026-05-12'),
(6, 10, 2, 8, '2026-05-12');

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
(100, 'Sara', 'Alvarez', 2000, '1');

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

--
-- Volcado de datos para la tabla `merchandise`
--

INSERT INTO `merchandise` (`ID_Merchandise`, `Name`, `Type`, `Price`, `ID_Supplier`) VALUES
(1, 'Leche Entera 1L', 'Alimentación', 1, 3),
(2, 'Arroz Extra 1kg', 'Alimentación', 2, 3),
(3, 'Ratón Óptico USB', 'Electrónica', 15, 2),
(4, 'Teclado Mecánico RGB', 'Electrónica', 45, 2),
(5, 'Sartén Antiadherente 24cm', 'Hogar', 22, 1),
(6, 'Juego de Toallas (3 pzas)', 'Hogar', 18, 1),
(7, 'Auriculares Inalámbricos', 'Electrónica', 35, 2),
(8, 'Café Molido Intenso 250g', 'Alimentación', 3, 3),
(9, 'Cargador Rápido Tipo-C', 'Electrónica', 12, 4),
(10, 'Pack 4 Pilas AA Alcalinas', 'Electricidad', 5, 5);

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
-- Volcado de datos para la tabla `supplier`
--

INSERT INTO `supplier` (`ID_Supplier`, `Name`, `Phone_number`, `Address`, `Postcode`) VALUES
(1, 'Distribuciones Almería', 600112233, 'Calle Industrial 14', 4001),
(2, 'TecnoMayorista S.L.', 611223344, 'Avenida de la Tecnología 45', 28002),
(3, 'Alimentos Frescos del Sur', 622334455, 'Polígono Ind. El Pino, Nave 4', 41016),
(4, 'Importaciones Mundiales', 633445566, 'Calle del Comercio 89', 8015),
(5, 'Suministros Globales S.A.', 644556677, 'Vía Principal 102', 46005);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `buy`
--
ALTER TABLE `buy`
  ADD PRIMARY KEY (`ID_Buy`),
  ADD KEY `fk_buy_proveedor` (`ID_Supplier`),
  ADD KEY `fk_buy_mercancia` (`ID_Merchandise`);

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
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `buy`
--
ALTER TABLE `buy`
  MODIFY `ID_Buy` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `buy`
--
ALTER TABLE `buy`
  ADD CONSTRAINT `buy_ibfk_1` FOREIGN KEY (`ID_Merchandise`) REFERENCES `merchandise` (`ID_Merchandise`),
  ADD CONSTRAINT `buy_ibfk_2` FOREIGN KEY (`ID_Supplier`) REFERENCES `supplier` (`ID_Supplier`),
  ADD CONSTRAINT `fk_buy_mercancia` FOREIGN KEY (`ID_Merchandise`) REFERENCES `merchandise` (`ID_Merchandise`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_buy_proveedor` FOREIGN KEY (`ID_Supplier`) REFERENCES `supplier` (`ID_Supplier`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `merchandise`
--
ALTER TABLE `merchandise`
  ADD CONSTRAINT `fkSupplierMerch` FOREIGN KEY (`ID_Supplier`) REFERENCES `supplier` (`ID_Supplier`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
