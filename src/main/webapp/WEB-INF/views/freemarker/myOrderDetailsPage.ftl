<!DOCTYPE html>
<#assign userRole = "${userRole}">
<#import "*/components/navbar.ftl" as navbarRenderer/>
<#import "*/components/order-form.ftl" as orderForm/>
<#import "*/components/chat-window.ftl" as chatRenderer />
<#import "*/components/consultant-rating-form.ftl" as consultantRatingFormRenderer />
<head>
<#include "*/includes.ftl">
    <link type="text/css" rel="stylesheet" href="./resources/css/order.css"/>
    <link type="text/css" rel="stylesheet" href="./resources/libs/bootstrap-star-rating/css/star-rating.min.css"/>
    <link type="text/css" rel="stylesheet" href="./resources/css/chat.css"/>
</head>
<body ng-app="chatApp">
    <input id="userRole" type="hidden" value="${userRole}"/>
    <input id="userId" type="hidden" value="${currentUser.userId}"/>
    <input id="orderId" type="hidden" value="${order.orderId!''}" />
    <@navbarRenderer.renderNavbar userRole="${userRole}" activePage="orders"/>
    <div class="myOrderPage">
    <div class="container">
        <div class="col-md-12 col-xs-12">
            <@orderForm.renderForm titlePage userRole order />
        </div>
    </div>
    <#if order.orderStatus == 'DONE'>
        <#if order.rated?string("true","false") == 'false'>
            <@consultantRatingFormRenderer.renderRatingForm order />
        </#if>
     <#elseif order.orderStatus == 'INPROGRESS'>
        <@chatRenderer.renderChatWindow userRole />
    </#if>
    </div>
    <script src="resources/libs/sockjs/sockjs.min.js" type="text/javascript"></script>
    <script src="resources/libs/stomp-websocket/lib/stomp.min.js" type="text/javascript"></script>
    <script src="resources/libs/angular/angular.min.js"></script>
    <script src="resources/libs/lodash/lodash.min.js"></script>
    <script src="resources/libs/bootstrap-star-rating/js/star-rating.min.js"></script>
    <script src="resources/script/angular/app.js" type="text/javascript"></script>
    <script src="resources/script/angular/chatController.js" type="text/javascript"></script>
    <script src="resources/script/angular/chatService.js" type="text/javascript"></script>
    <script src="resources/script/chat.js"></script>
    <script src="./resources/script/order.js"></script>
</body>