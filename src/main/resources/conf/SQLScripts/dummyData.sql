INSERT INTO consultantspecialities (name) VALUES ('Medicina');
INSERT INTO consultantspecialities (name) VALUES ('Literatura');
INSERT INTO consultantspecialities (name) VALUES ('Chimie');
INSERT INTO consultantspecialities (name) VALUES ('Matematica');

INSERT INTO users(createdOn,lastLogin,mail,password,role) VALUES (NOW(),null,'admin','admin','ADMINISTRATOR');
INSERT INTO users(createdOn,lastLogin,mail,password,role) VALUES (NOW(),null,'consultant','consultant','CONSULTANT');
INSERT INTO users(createdOn,lastLogin,mail,password,role) VALUES (NOW(),null,'client','client','CLIENT');

INSERT INTO clients(lastName,firstName,clientId) VALUES ('some','client', (SELECT userId FROM users WHERE mail = 'client'));


INSERT INTO consultants(address,cardOwner,firstName,lastName,ibanCode,phoneNumber,studies,specialityId,consultantId)
VALUES ('Cluj-Napoca','Ionescu Vasile','Ionescu','Vasile','ibanCode','0741502561','studies',1, 2);

INSERT INTO orders(annexes,bibliography,domain,message,nrOfPages,orderStatus,subject,tableOfContents,clientId,consultantId)
VALUES (null,'some bibliography','Medicina','some content',10,0,'despre corpul uman','tables of contents',(SELECT userId FROM users WHERE mail = 'client'),(SELECT userId FROM users WHERE mail = 'consultant'));

INSERT INTO consultantOrders (cost,nrOfDays,status,consultantId,orderId) VALUES(20,3,2,(SELECT userId from users WHERE mail='consultant'),1);