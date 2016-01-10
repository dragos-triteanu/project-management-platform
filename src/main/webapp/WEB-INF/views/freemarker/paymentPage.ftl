<!DOCTYPE html>
<#assign userRole = "${userRole}">
<#import "*/components/navbar.ftl" as navbarRenderer/>
<#import "*/components/payment-order.ftl" as payment/>
<head>
<#include "*/includes.ftl">
    <link rel="stylesheet" type="text/css" href="./resources/css/payment.css">
</head>
<body>
<input id="userRole" type="hidden" class="hidden" value="${userRole}" />
<@navbarRenderer.renderNavbar userRole="${userRole}" activePage="payment"/>
<div class="container">
    <div class="h2" >
        Plata
    </div>
    <div class="col-md-10 col-xs-10">
    <@payment.renderTable orderList userRole/>
    </div>
    <script src="./resources/script/payment.js"></script>
</body>
</html>