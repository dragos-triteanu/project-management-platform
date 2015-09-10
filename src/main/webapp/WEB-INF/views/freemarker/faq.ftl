<!DOCTYPE html>
<#assign userRole = "${userRole}">
<#import "*/components/navbar.ftl" as navbarRenderer />
<#import "*/components/faq-question.ftl" as faqRenderer />
<html>
	<head>
	 <#include "*/includes.ftl">
	 <link rel="stylesheet" type="text/css" href="./resources/css/home.css">
	 <link rel="stylesheet" type="text/css" href="./resources/css/faq.css">
	 <#if userRole == 'ADMINISTRATOR'>
	 	<link rel="stylesheet" type="text/css" href="./resources/css/mediaQueries/faq-media-queries-moderator.css">
	 </#if>
	</head>
	
	<body>
		<input id="userRole" type="hidden" class="hidden" value="${userRole}" />
		<@navbarRenderer.renderNavbar userRole="${userRole}" activePage="faq" userHasProjects=userHasProjects />

		<#if userRole == 'ADMINISTRATOR'>
			<div class="container">
    		<br/>

			<div class="panel-group" id="accordion">
				<div class="faqHeader">
					<h2>FAQ</h2>
				</div>
					<div class="moderateFaqContainer">
						<@faqRenderer.renderTable questionsAndAnswers />
					</div>
				</div>
			</div>
		<#else>
			<#if questionsAndAnswers??>
			<div class="container">
				<div class="col-md-12 col-xs-12">
					<div class="panel-group faqQuestionsContainer" id="accordion">
				        <div class="faqHeader">
				        	<h2>FAQ</h2>
				        </div>
				        	<#list questionsAndAnswers as qaa>
								<@faqRenderer.renderQuestion qaa.id qaa.question qaa.answer />				
							</#list>
				    	</div>
					</div>
				</div>
			</div>
				
	        <#else>
	        	There are no Questions and Answers available.This is probably an issue :(
	        </#if>	
		</#if>
		<script src="./resources/script/faq.js"></script>
	</body>
</html>