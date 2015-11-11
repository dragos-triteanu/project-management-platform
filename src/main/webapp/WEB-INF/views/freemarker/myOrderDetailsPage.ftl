<!DOCTYPE html>
<#assign userRole = "${userRole}">
<#import "*/components/navbar.ftl" as navbarRenderer/>
<#import "*/components/order-form.ftl" as orderForm/>
<#import "*/components/chat-window.ftl" as chatRenderer />
<head>
<#include "*/includes.ftl">
    <link type="text/css" rel="stylesheet" href="./resources/css/order.css"/>
    <link type="text/css" rel="stylesheet" href="./resources/css/chat.css"/>
</head>
<body ng-app="chatApp">
    <input id="userRole" type="hidden" value="${userRole}"/>
    <input id="userId" type="hidden" value="${currentUser.userId}"/>
    <input id="orderId" type="hidden" value="${order.orderId!''}" />
    <@navbarRenderer.renderNavbar userRole="${userRole}" activePage="orders"/>
    <div class="container">
        <div class="col-md-12 col-xs-12">
            <@orderForm.renderForm titlePage userRole order />
        </div>
    </div>
    <@chatRenderer.renderChatWindow userRole />
    <script src="resources/libs/sockjs/sockjs.min.js" type="text/javascript"></script>
    <script src="resources/libs/stomp-websocket/lib/stomp.min.js" type="text/javascript"></script>
    <script src="resources/libs/angular/angular.min.js"></script>
    <script src="resources/libs/lodash/lodash.min.js"></script>
    <script src="resources/script/angular/app.js" type="text/javascript"></script>
    <script src="resources/script/angular/controllers.js" type="text/javascript"></script>
    <script src="resources/script/angular/chatService.js" type="text/javascript"></script>
    <script src="./resources/script/chat.js"></script>
</body>