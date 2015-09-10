DROP DATABASE `projectManagementDB`;

CREATE DATABASE IF NOT EXISTS `projectManagementDB` CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `projectManagementDB`;

CREATE TABLE IF NOT EXISTS `crowdfundingDB`.`howItWorks` (
  `id` int(7) NOT NULL,
  `html` varchar(1000) NOT NULL,
  PRIMARY KEY  (`id`)
);

CREATE TABLE IF NOT EXISTS `crowdfundingDB`.`faq` (
  `id` int(7) NOT NULL AUTO_INCREMENT,
  `question` varchar(1000) NOT NULL,
  `answer` varchar(1000) NOT NULL,
  PRIMARY KEY  (`id`)
);
