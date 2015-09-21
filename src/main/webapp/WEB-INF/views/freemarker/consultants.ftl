<!DOCTYPE html>
<#assign userRole = "${userRole}">
<#import "*/components/navbar.ftl" as navbarRenderer/>
<#import "*/components/consultant-table.ftl" as consultantTableRenderer/>
<html>
    <header>
        <#include "*/includes.ftl">
        <link rel="stylesheet" type="text/css" href="./resources/css/consultant.css">
    </header>
    <body>
        <input id="userRole" type="hidden" class="hidden" value="${userRole}" />
        <@navbarRenderer.renderNavbar userRole="${userRole}" activePage="consultants"/>

        <#if userRole == 'ADMINISTRATOR'>
        <div class="container">
         <div class ="panel-group">
            <div class="h2 consultantTitle" >
                Consultanti
            </div>
            <div class="consultants-table">
                <@consultantTableRenderer.renderTable consultantsList/>
            </div>
          </div>
         </div>
        <div class="row">
            <div class="col-md-6 col-xs-6 col-md-offset-5 col-xs-offset-1">
                <a href="./createConsultant"><button type="submit" class="btn btn-primary">Creeaza Consultant</button></a>
            </div>
        </div>
        <#else>
         Access Denied.
        </#if>
    </body>
</html>