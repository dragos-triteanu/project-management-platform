<html>
    <head>
	    <#include "*/includes.ftl">
	    <#import "*/components/navbar.ftl" as navbarRenderer/>
		<#import "*/components/consultant-form.ftl" as consultantformRenderer />   
    </head>

    <body>
        <input id="userRole" type="hidden" value="${userRole!'sdasd'}" />
		<@navbarRenderer.renderNavbar userRole "consultants" />
    	<div class="container">
			<@consultantformRenderer.renderForm "Creeaza consultant" />    
    	</div>
    </body>
</html>