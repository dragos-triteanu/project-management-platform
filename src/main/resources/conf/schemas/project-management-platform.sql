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

CREATE TABLE IF NOT EXISTS `projectManagementDB`.`users` (
  `userId` INT(16) NOT NULL AUTO_INCREMENT,
  `lastname` VARCHAR(36) ,
  `firstname` varchar(1000) ,
  `email` varchar(1000) ,
  `phoneNumber` int(10) ,
  `address` varchar(1000) ,
  `studies` varchar(1000) ,
  `IBAN` varchar(34) ,
  `CV` LONGBLOB,
  `specialityId` INT(16),
  `password` varchar(20) NOT NULL,
  `role` varchar(13) NOT NULL,
  `lastLogin` datetime  NULL,
  PRIMARY KEY  (`userId`)
);
INSERT INTO `projectmanagementdb`.`users` (`email`, `password`, `role`) VALUES ('admin', 'admin', 'ADMINISTRATOR');


CREATE TABLE IF NOT EXISTS `projectManagementDB`.`consultantSpecialities` (
  `specialityId` INT(16) NOT NULL AUTO_INCREMENT,
  `specialityName` varchar(1000) NOT NULL,
  PRIMARY KEY  (`specialityId`)
);
INSERT INTO consultantSpecialities(specialityId,specialityName) VALUES('1','Medicine');

CREATE TABLE IF NOT EXISTS `projectManagementDB`.`orders` (
  `orderId` INT(16) NOT NULL AUTO_INCREMENT,
  `speciality` varchar(1000) NOT NULL,
  `subject` varchar(1000) NOT NULL,
  `nrOfPages` INT(4) NOT NULL,
  `tableOfContents` varchar(5000) NOT NULL,
  `bibliography` varchar(5000) NOT NULL,
  `annexes` LONGBLOB,
  `message` varchar(5000) NOT NULL,
  `status` INT(2) NOT NULL,
  `clientId` INT(16) NOT NULL,
  PRIMARY KEY  (`orderId`)
);
INSERT INTO `projectmanagementdb`.`orders` (`orderId`, `speciality`, `subject`, `nrOfPages`, `tableOfContents`, `bibliography`, `message`, `status`, `clientId`) VALUES ('1', 'domain', 'subject', '23', 'tableOfContents', 'bibliography', 'message', '1', '1');
INSERT INTO `projectmanagementdb`.`orders` (`orderId`, `speciality`, `subject`, `nrOfPages`, `tableOfContents`, `bibliography`, `message`, `status`, `clientId`) VALUES ('2', 'domain1', 'subject1', '20', 'tableOfContents1', 'bibliography1', 'message1', '1', '2');
INSERT INTO `projectmanagementdb`.`orders` (`orderId`, `speciality`, `subject`, `nrOfPages`, `tableOfContents`, `bibliography`, `message`, `status`, `clientId`) VALUES ('3', 'domain2', 'subject2', '12', 'tableOfContents2', 'bibliography2', 'message2', '1', '3');


CREATE TABLE IF NOT EXISTS `projectManagementDB`.`consultantOrders`(
  `orderId` INT(16) NOT NULL,
  `consultantId` INT(16) NOT NULL,
  `cost` DECIMAL NOT NULL,
  `nrOfDays` INT(2) NOT NULL,
  `status` INT(2),
  PRIMARY KEY (`orderId`,`consultantId`)
  );

CREATE TABLE IF NOT EXISTS `projectManagementDB`.`messages` (
  `messageId` INT(16) NOT NULL AUTO_INCREMENT,
  `clientId` INT(16)  NULL,
  `orderId` INT(16)  NULL,
  `consultantId` INT(16) NOT NULL,
  `message` nvarchar(1000) NULL,
  `dateTime` datetime  NULL,
  PRIMARY KEY  (`messageId`)
);