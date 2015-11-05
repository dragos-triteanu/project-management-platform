<!DOCTYPE html>
<#assign userRole = "${userRole}">
<#import "*/components/navbar.ftl" as navbarRenderer/>
<#import "*/components/consultant-form.ftl" as consultantForm/>
<head>
    <#include "*/includes.ftl">
    <link rel="stylesheet" type="text/css" href="./resources/css/consultant.css"/>
</head>
<body>
    <input id="userRole" type="hidden" value="${userRole}">
    <@navbarRenderer.renderNavbar userRole="${userRole}" activePage="consultant"/>
    <#if userRole=="ADMINISTRATOR">
    <div class="container">
        <div class="col-md-12 col-xs-12">
            <@consultantForm.renderForm titlePage consultant categories/>
        </div>
    </div>
    </#if>
    <script src="./resources/script/consultant.js"></script>
</body>
<html>


