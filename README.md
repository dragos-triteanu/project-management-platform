Business scope:
This project refers to the implementation of an online project research managing platform, for the realization and delivery of high quality project document papers, or documentation for clients.
A simple flow would be the following:

Business Flow:
A client orders from one of our website, a project paper about ‘The effect of nicotine on the human body’.He specifies, the number of pages that the document must have, the reference from where our consultants must document in order to realize the project, and can provide annexes/subject etc (SEE ROLE CLIENT/first dot).After a client has submitted a request, then the manager’s role comes into action.The manager will place this project request in a form of bidding system, in which multiple specialized consultants can place bids with the amount of money they want to receive in order to finish the client’s project, and the number of work days that this will take.After multiple consultants have placed bids, the manager will select the one that wins the bid, to finalize the project.From that moment on, the consultant will have direct(anonymous) conversation with the client, that will guide him, towards finalizing the research document.The consultant will then have the possibility of  sending messages/files to the client, so that they can have a shared perspective, of what the research paper will look like in the end. A Manager, has the possibility, to split a payments needed for the project, in multiple tranches that the client can sequentially pay, thus making the project delivery,made in multiple iterations(the iteration count is the same as the number of tranches). A consultant will only start his work, when the manager comfirmes that the client has made a deposit for the specified project.The client will then confirm his payment, by sending a receipt to the manager.An EXTENDED SCOPE will be automatization of this process, by online bank payment, so that the process can go on without the manager’s intervention, and all payments towards all consultants can be automated.

ROLE CLIENT
As a CLIENT, I want to be able to submit an order on the platform.This will be done by completing a form on a third party site, so the platform should accepts some sort of endpoint like ‘./orders’’ where the orders will be posted.The form information on the 3rd party sites is the following:
NAME → The name of the client
EMAIL → The email of the client
DOMAIN(SPECIALITY) → The domain of the order.This denotes what speciality the order will have, so that later on, specialized CONSULTANTs can process it.
SUBJECT →  The subject of the project order.
NR OF PAGES → The number of pages that the project must have.
TABLE OF CONTENTS → This should be a textarea in which the client can specify the table of contents for the project.
BIBLIOGRAFY → A textarea where the client, can specify the books to be used in fulfilling the project order.
ANEXES → This can be a file .
MESSAGE → A message for the administrator of the platform.
T&C → A checkbox field where the client can accept the terms&conditions.

As a CLIENT, I want to receive an email on the mailbox I have provided, so that I may receive pricing information for my project request.This email should contain :
	PRICING INFORMATION → Information about the costs of my order
	USERNAME → a generated username, provided by the platform.
	PASSWORD → a generated password, provided by the platform.
	LINK TO LOGIN → a link to get to the login section
	
	After receiving the credentials, I will have to login into the platform, within 5(120 HOURS) working days to confirm the order.
As a CLIENT, I want to be able to be notified when I must make down payments on the project I have ordered.I Also want to see the number of down payments that I have to make for my project(probably 2 or 3 tranches)
As a CLIENT,I must also be able to send payment receipts/messages to the MANAGER, so that he may acknowledge that I have concluded a down payment (IMPROVED SCOPE: this can be done via online payment, thus making the whole process automatic) I want to be able to also submit messages to the MANAGER, without having to necessarily upload receipts(same form, image is not mandatory).
As a CLIENT, I want to be able to see all the payments that I have made for an ordered project.
As a CLIENT, I want to be able to rate the CONSULTANT that is processing my project order(from 1 to 5 stars).
As a CLIENT, I want to be able to receive my finished project parts via the platform.The project parts will be sequentially submitted by a CONSULTANT.
ROLE MANAGER
As a MANAGER, I want to be able to login to the platform using administrator like credentials.
As a MANAGER, I want to be able to create categories, so that I might assign CONSULTANTS to categories, based on the CONSULTANT’S speciality.	
As a MANAGER, I want to be able to manage accounts for the CONSULTANTS.When creating a CONSULTANT account, I want to be able to place him into a specific category depending on his skillset.For example, if the CONSULTANT has a proficiency in ‘medicine’, I want to be able to place the CONSULTANT’S account in the medicine category.When creating a CONSULTANT account I want to have the following predefined fields to enter for a CONSULTANT account:
	
		FIRSTNAME(mandatory) → the firstname of the CONSULTANT.
		LASTNAME(mandatory) → The lastname of the CONSULTANT.
		EMAIL(mandatory) → the user’s email
		PHONE NUMBER(mandatory) → the user’s phone number.
		ADDRESS → The user’s address.
		SCHOOL\UNIVERSITY→ The user’s school or university.
		IBAN code→ The IBAN code for a user’s account.
`		CARD OWNER NAME → The name from the IBAN account card.
            CV→ The user’s CV, that should be PDF or DOC (should be stored in the database as a BLOB)
		RECRUITMENT STATUS → This field can have 2 values:
							NEW_CONSULTANT
							RECOMENDED
		CATEGORY → The category of expertise that the CONSULTANT has.I should be able to choose from the categories I have created.
		
             I should be able to add extra fields by clicking on a ‘+’ button at the bottom of the form(all newly added fields should be text).
As a MANAGER, I want to be able to manage a landing page for the CONSULTANTS.This page can contain images/text(WYSYWYG).
As a MANAGER, I want to be able to receive project order requests from CLIENTS from third party sites.
As a MANAGER, I want to be able to decline a specific order for a specific reason.
As a MANAGER, I want to be able to send the orders I have received from CLIENTS, to CONSULTANTS, so that they can place a bid(price and work days) for when the project will be realised.
As a MANAGER, I want to be able to see all the CONSULTANTS that have placed bids for the order, so that I may assign the project order on to him for solving.By doing this, the selected CONSULTANT will be received that he has won the bid, and he will be able to see the bid in progress.The other CONSULTANTS that have placed bids, and lost, will be informed that they willnot receive the project order.
As a MANAGER, I want to be able to adjust pricing information for the CLIENT.I want to be able to add pricing value, over the CONSULTANT’S bid, before the pricing information reaches the CLIENT.I also want to be able to split the amount of money into multiple tranches/down payments(I can select the number of tranches from 1 to 5).(EXTENDED SCOPE: This can be done automatically by online payment method)
As a MANAGER, I want to be able to send an automated email to the CLIENT, in which to inform him that his order will be processed, and to send him the following information:
Username → a auto generated username, that the user will use to login to the platform.
Password → auto generated password, that the user will use to login to the platform.
Pricing information → This will be the pricing information that the client will see.This can be separated into multiple down payments(multiple tranches).
Link to login→ An URL at which the user can perform the login in order to confirm the project order.

As a MANAGER, I want the CLIENT to have to confirm and accept the order, after receiving the pricing information via email.For the CLIENT to confirm the order, he must login into the platform in a time period of 5 days(120 hour).The CLIENT has to send a proof of payment/receipt to the MANAGER, in those 5 days , so that me as a MANAGER, can forward the CLIENT’s order for processing.If the payment is not made within 5 days, the order is deleted, and the generated account is also deleted.
As a MANAGER, I want to be able to forward an order to the CONSULTANT, so that he may work on it.I want to also see how many days does the CONSULTANT still have until finishing the project order.
As a MANAGER, I want to be able to see all the information/messages that the CONSULTANT sends to the CLIENT.In this chat, both the CONSULTANT and the CLIENT’s identities will be obfuscated, by a platfort specific identifier(example: Consultant003 and Client 001).
As a MANAGER, I want to be able to enable the CONSULTANT to work on the next part(tranche) of the project, after the CONSULTANT has finished delivering a tranche 
For example, if the project is split into 3 working tranches(parts), and the CONSULTANT finished the 1st one, I will be notified about his finish, and I will enable him to continue working on the second tranche.I will want to enable the CONSULTANT to work, only when the CLIENT has submitted the second down payment.	
As a MANAGER, I want to be able to be notified, when the CONSULTANT has finished delivering the project order.
As a Manager, I want to be able to view the status of each CONSULTANT, and know how many orders each of the CONSULTANTs has.

ROLE CONSULTANT

As a CONSULTANT, I want to be able to login on the platform using the credentials provided to me by a manager.
As a CONSULTANT, after I login to the platform with my provided credentials, I want to be able to view a landing page where I can see advice on how to use the platform.This section will be administered by the manager.
As a CONSULTANT, I want to be able to view the orders that exist in the system, and have been placed by a manager.
As a CONSULTANT, I want to be able to bid for an order, so that I can begin working on it.For this I must be  able to provide a bid amount(in virtual coin) and a bid time(the time that will take a CONSULTANT to finish the order, expressed in days)
As a CONSULTANT, I want to be notified if I have received the order or not.This will be provided via a message on the platform.If the bid has been won, then the order status will become active.
As a CONSULTANT, I want to be able to send documents such as (DOC, PDF, RAR) via the platform, as a form of service to the client that placed the order.
As a CONSULTANT, I want to be able to see each order in multiple iterations(set by manager) so that I might start working on a sub-part of the order.I want to be aware of the status of subparts in the system, and of any user complaints/improvement requirements on submited part.
As a CONSULTANT, I want to be able to send a message to the manager.
As A CONSULTANT, I want to be able to sent messages to the client, but I do not want to know his details.Instead, I want to see him represented by a platform specific denominator(‘Ex.Client8123’).
As a CONSULTANT, I want to be able to view my overall rating.This rating is calculated depending of the number of stars(from 1 to 5) I receive from clients.
As a CONSULTANT, I want to be able to see all the previous orders that I have fulfilled, and to have calculated a total amount of money earned from making orders.
	
