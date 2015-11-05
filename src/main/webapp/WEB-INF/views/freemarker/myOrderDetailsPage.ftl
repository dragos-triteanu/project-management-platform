<!DOCTYPE html>
<#assign userRole = "${userRole}">
<#import "*/components/navbar.ftl" as navbarRenderer/>
<#import "*/components/order-form.ftl" as orderForm/>
<head>
<#include "*/includes.ftl">
    <link type="text/css" rel="stylesheet" href="./resources/css/order.css"/>
    <link type="text/css" rel="stylesheet" href="./resources/css/chat.css"/>
</head>
<body ng-app="chatApp">
    <input id="userRole" type="hidden" value="${userRole}"/>
    <input id="orderId" type="hidden" value="${order.orderId!''}" />
    <@navbarRenderer.renderNavbar userRole="${userRole}" activePage="orders"/>
    <#if userRole=="CONSULTANT">
    <div class="container">
        <div class="col-md-12 col-xs-12">
            <@orderForm.renderForm titlePage userRole order />
        </div>
    </div>
    <div class="chatContainer" ng-controller="ChatCtrl">
        <section id="openChat" class="module col-md-5 col-md-offset-7">
            <header class="top-bar">
                <div class="left">
                    <h1>Chat</h1>
                </div>
                <div class="right">
                    <button id="btnHide" type="button" class="btn-chat btn-xs">
                        <span class="glyphicon glyphicon-menu-down" aria-hidden="true"></span>
                    </button>
                </div>
            </header>
            <div id="chatBody">
                <p ng-repeat="message in messages | orderBy:'time':reverse" class="message">
                    <time class="chatTime">{{message.time | date:'HH:mm'}}</time>
                    <span>C${currentUser.consultantId} said: </span>
                    <span ng-class="{self: message.self}">{{message.content}}</span>
                </p>
            </div>
            <div id="chatFooter">
                <form ng-submit="addMessage()" name="messageForm">
                    <div class="input-group">
                        <input type="text" placeholder="Introduceti mesaj..." class="form-control no-border-radius" ng-model="message" />
                        <span class="input-group-btn">
                            <button class="btn btn-default no-border-radius" type="submit" type="button">
                                <span class="glyphicon glyphicon-send" aria-hidden="true"></span>
                            </button>
                        </span>
                    </div>
                </form>
            </div>
        </section>
        <section id="minimizeChat" class="module col-md-5 col-md-offset-7 display-none">
            <header class="top-bar minimize-header">
                <div class="left">
                    <h1>Chat</h1>
                </div>
                <div class="right">
                    <button id="btnShow" type="button" class="btn-chat btn-xs ">
                        <span class="glyphicon glyphicon-menu-up" aria-hidden="true"></span>
                    </button>
                </div>
            </header>
        </section>
    </div>
    </#if>
    <script src="resources/libs/sockjs/sockjs.min.js" type="text/javascript"></script>
    <script src="resources/libs/stomp-websocket/lib/stomp.min.js" type="text/javascript"></script>
    <script src="resources/libs/angular/angular.min.js"></script>
    <script src="resources/libs/lodash/lodash.min.js"></script>
    <script src="resources/script/angular/app.js" type="text/javascript"></script>
    <script src="resources/script/angular/controllers.js" type="text/javascript"></script>
    <script src="resources/script/angular/services.js" type="text/javascript"></script>
    <script src="./resources/script/chat.js"></script>
</body>