<!DOCTYPE html>
<#assign userRole = "${userRole}">
<#import "*/components/navbar.ftl" as navbarRenderer/>
<#import "*/components/order-table.ftl" as order/>
<#import "*/components/search.ftl" as search/>
<html>
<head>
    <#include "*/includes.ftl">
    <link rel="stylesheet" type="text/css" href="./resources/css/order.css">
</head>
<body>
<input id="userRole" type="hidden" class="hidden" value="${userRole}" />
<@navbarRenderer.renderNavbar userRole="${userRole}" activePage="orders"/>

<#if userRole == 'CONSULTANT'>
<div class="container">
    <div class ="panel-group">
        <div class="h2" >
            Comenzi noi
        </div>
        <div class="search">
            <@search.search "newOrders" categoryForSearch />
        </div>
        <div class="orders-table generic-table">
            <@order.renderTable ordersList userRole "Nici o comanda noua."/>
        </div>
    </div>
</div>
<#else>
Access Denied.
</#if>
<script src="./resources/script/search.js"></script>
</body>
</html>