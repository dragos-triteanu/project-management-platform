DROP DATABASE `projectManagementDB`;

CREATE DATABASE IF NOT EXISTS `projectManagementDB` CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `projectManagementDB`;

CREATE TABLE IF NOT EXISTS `projectManagementDB`.`home` (
  `id` int(2) NOT NULL,
  `html` varchar(1000) NOT NULL,
  PRIMARY KEY  (`id`)
);
INSERT INTO `projectmanagementdb`.`home` (`id`, `html`) VALUES (1, '<p>This is a paragraph</p>');


CREATE TABLE IF NOT EXISTS `projectManagementDB`.`faq` (
  `id` VARCHAR(36) NOT NULL ,
  `question` varchar(1000) NOT NULL,
  `answer` varchar(1000) NOT NULL,
  PRIMARY KEY  (`id`)
);

CREATE TABLE IF NOT EXISTS `projectManagementDB`.`consultant-categories` (
  `id` VARCHAR(36) NOT NULL ,
  `name` varchar(1000) NOT NULL,
  PRIMARY KEY  (`id`)
);
