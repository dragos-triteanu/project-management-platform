<!DOCTYPE html>
<#assign userRole = "${userRole}">
<#import "*/components/navbar.ftl" as navbarRenderer/>
<#import "*/components/order-form.ftl" as orderForm/>
<head>
<#include "*/includes.ftl">

</head>
<body>
<input id="userRole" type="hidden" value="${userRole}">
<@navbarRenderer.renderNavbar userRole="${userRole}" activePage="orders"/>
<#if userRole=="CONSULTANT">
<div class="container">
    <div class="col-md-12 col-xs-12">
        <@orderForm.renderForm titlePage order/>
    </div>
</div>
</#if>

</body>
<html>