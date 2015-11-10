INSERT INTO `projectmanagementdb`.`users` (`userId`,`mail`, `password`, `role`) VALUES (0,'admin', 'admin', 'ADMINISTRATOR');
INSERT INTO `projectmanagementdb`.`users` (`userId`,`createdOn`,`mail`, `password`, `role`) VALUES (1,NOW(),'client', 'client', 'CLIENT');
INSERT INTO `projectmanagementdb`.`clients` (`firstName`,`lastName`,`clientId`) VALUES ('someFirstName','someLastName',1);