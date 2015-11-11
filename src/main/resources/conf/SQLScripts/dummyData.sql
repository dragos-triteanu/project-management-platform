/*INSERT INTO consultantspecialities (name) VALUES ('Medicina');
INSERT INTO consultantspecialities (name) VALUES ('Literatura');
INSERT INTO consultantspecialities (name) VALUES ('Chimie');
INSERT INTO consultantspecialities (name) VALUES ('Matematica');

INSERT INTO `projectmanagementdb`.`users` (`mail`, `password`, `role`) VALUES ('admin', 'admin', 'ADMINISTRATOR');

INSERT INTO users(createdOn,lastLogin,mail,password,role) VALUES (NOW(),null,'ana','ana','CLIENT');
INSERT INTO clients(lastName,firstName,clientId) VALUES ('ana','popescu', (SELECT userId FROM users WHERE mail = 'ana'));
INSERT INTO users(createdOn,lastLogin,mail,password,role) VALUES (NOW(),null,'maria','maria','CLIENT');
INSERT INTO clients(lastName,firstName,clientId) VALUES ('maria','vasilescu', (SELECT userId FROM users WHERE mail = 'maria'));
INSERT INTO users(createdOn,lastLogin,mail,password,role) VALUES (NOW(),null,'catalina','catalina','CLIENT');
INSERT INTO clients(lastName,firstName,clientId) VALUES ('catalina','marinescu', (SELECT userId FROM users WHERE mail = 'catalina'));

INSERT INTO users(createdOn,lastLogin,mail,password,role) VALUES (NOW(),null,'vasile','vasile','CONSULTANT');

SET @specialityId := (SELECT specialityId FROM consultantspecialities WHERE name = 'medicina');
SET @consultantId := (SELECT userId from users WHERE mail='vasile');
INSERT INTO consultants(address,cardOwner,firstName,lastName,ibanCode,phoneNumber,studies,specialityId,consultantId) VALUES
  ('Cluj-Napoca','Ionescu Vasile','Ionescu','Vasile','ibanCode','0741502561','studies',
   @specialityId,@consultantId);
INSERT INTO users(createdOn,lastLogin,mail,password,role) VALUES (NOW(),null,'catalin','catalin','CONSULTANT');

SET @specialityId :=  (SELECT specialityId FROM consultantspecialities WHERE name = 'literatura');
SET @consultantId := (SELECT userId from users WHERE mail='catalin');
INSERT INTO consultants(address,cardOwner,firstName,lastName,ibanCode,phoneNumber,studies,specialityId,consultantId) VALUES
  ('Cluj-Napoca','Moldovan Catalin','Moldovan','Catalin','ibanCode','0741502561','studies',
   @specialityId,@consultantId);


INSERT INTO orders(annexes,bibliography,domain,message,nrOfPages,orderStatus,subject,tableOfContents,clientId,consultantId) VALUES
  (null,'some bibliography','medicina','some message',10,0,'despre corpul uman','tables of contents',(SELECT userId FROM users WHERE mail = 'maria'),null);
INSERT INTO orders(annexes,bibliography,domain,message,nrOfPages,orderStatus,subject,tableOfContents,clientId,consultantId) VALUES
  (null,'some bibliography2','chimie','some message2',5,1,'chimie stuff','tables of contents2',(SELECT userId FROM users WHERE mail = 'ana'),null);
INSERT INTO orders(annexes,bibliography,domain,message,nrOfPages,orderStatus,subject,tableOfContents,clientId,consultantId) VALUES
  (null,'some bibliography3','Matematica','some message3',6,0,'matematica stuff','tables of contents3',(SELECT userId FROM users WHERE mail = 'catalina'),null);
INSERT INTO orders(annexes,bibliography,domain,message,nrOfPages,orderStatus,subject,tableOfContents,clientId,consultantId) VALUES
  (null,'some bibliography4','Medicina','some message3',12,5,'Medicina stuff2','tables of contents4',(SELECT userId FROM users WHERE mail = 'maria'),(SELECT userId from users WHERE mail='catalin'));

INSERT INTO consultantOrders (cost,nrOfDays,status,consultantId,orderId) VALUES(20,3,2,(SELECT userId from users WHERE mail='catalin'),
                                                                                (SELECT orderId FROM orders WHERE orderStatus=1));
INSERT INTO consultantOrders (cost,nrOfDays,status,consultantId,orderId) VALUES(90,4,2,(SELECT userId from users WHERE mail='vasile'),
                                                                                (SELECT orderId FROM orders WHERE orderStatus=1));
INSERT INTO consultantOrders (cost,nrOfDays,status,consultantId,orderId) VALUES(20,3,3,(SELECT userId from users WHERE mail='catalin'),
                                                                                (SELECT orderId FROM orders WHERE orderStatus=5));
INSERT INTO consultantOrders (cost,nrOfDays,status,consultantId,orderId) VALUES(70,3,4,(SELECT userId from users WHERE mail='vasile'),
                                                                                (SELECT orderId FROM orders WHERE orderStatus=5))*/
