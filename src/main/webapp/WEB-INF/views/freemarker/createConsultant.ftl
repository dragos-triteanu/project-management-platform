<html>
    <head>
	    <#include "*/includes.ftl">
	    <#import "*/components/navbar.ftl" as navbarRenderer/>
		<#import "*/components/consultant-form.ftl" as consultantformRenderer />   
    	<#import "*/components/category-table.ftl" as categoryRenderer />
		<link rel="stylesheet" type="text/css" href="./resources/css/createConsultant.css" />
	</head>

    <body>
        <input id="userRole" type="hidden" value="${userRole!'sdasd'}" />
		<@navbarRenderer.renderNavbar userRole "consultants" />
    	<div class="container">
			<div class="col-md-9 col-xs-7">
				<@consultantformRenderer.renderForm "Creeaza consultant" "" categories />
            </div>
			<div class="col-md-3 col-xs-3">
                <btn class="btn btn-default toggleCategories">Gestioneaza categorii</btn>
                <div class="categoryTableContainer">
					<@categoryRenderer.renderCategories categories />
				</div>
			</div>
		</div>
    </body>

	<footer>
		<script src="./resources/script/createConsultant.js"></script>
		<script src="./resources/script/jquery.form.js"></script>
	</footer>
</html>