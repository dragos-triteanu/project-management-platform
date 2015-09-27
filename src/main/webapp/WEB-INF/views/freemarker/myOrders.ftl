<!DOCTYPE html>
<#assign userRole = "${userRole}">
<#import "*/components/navbar.ftl" as navbarRenderer/>
<#import "*/components/order-table.ftl" as order/>
<html>
<head>
<#include "*/includes.ftl">
    <link rel="stylesheet" type="text/css" href="./resources/css/order.css">
</head>
<body>
<input id="userRole" type="hidden" class="hidden" value="${userRole}" />
<@navbarRenderer.renderNavbar userRole="${userRole}" activePage="myorders"/>

<#if userRole == 'CONSULTANT'>
<div class="container">
    <div class ="panel-group">
        <div class="h2" >
            Comenzile mele
        </div>
        <div class="orders-table">
            <@order.renderTable ordersList userRole "Nici o comanda activa."/>
        </div>
    </div>
</div>
<#else>
Access Denied.
</#if>
</body>
</html>