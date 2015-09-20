DROP DATABASE `projectManagementDB`;

CREATE DATABASE IF NOT EXISTS `projectManagementDB` CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `projectManagementDB`;

CREATE TABLE IF NOT EXISTS `projectManagementDB`.`home` (
  `id` INT(2) NOT NULL,
  `html` varchar(1000) NOT NULL,
  PRIMARY KEY  (`id`)
);
INSERT INTO `projectmanagementdb`.`home` (`id`, `html`) VALUES (1, '<p>This is a paragraph</p>');


CREATE TABLE IF NOT EXISTS `projectManagementDB`.`faq` (
  `id` INT (16) NOT NULL AUTO_INCREMENT,
  `question` varchar(1000) NOT NULL,
  `answer` varchar(1000) NOT NULL,
  PRIMARY KEY  (`id`)
);

CREATE TABLE IF NOT EXISTS `projectManagementDB`.`consultants` (
  `id` INT(16) NOT NULL AUTO_INCREMENT,
  `lastname` VARCHAR(36) NOT NULL ,
  `firstname` varchar(1000) NOT NULL,
  `email` varchar(1000) NOT NULL,
  `phoneNumber` int(10) NOT NULL,
  `address` varchar(1000) NOT NULL,
  `studies` varchar(1000) NOT NULL,
  `IBAN` varchar(34) NOT NULL,
  `CV` LONGBLOB,
  `specialityId` varchar(36) NOT NULL,
  PRIMARY KEY  (`id`)
);

CREATE TABLE IF NOT EXISTS `projectManagementDB`.`consultantSpecialities` (
  `specialityId` INT(16) NOT NULL AUTO_INCREMENT,
  `specialityName` varchar(1000) NOT NULL,
  PRIMARY KEY  (`specialityId`)
);
INSERT INTO consultantSpecialities(specialityId,specialityName) VALUES('1','Medicine');