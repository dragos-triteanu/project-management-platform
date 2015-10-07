<!DOCTYPE html>
<#assign userRole = "${userRole}">
<#import "*/components/navbar.ftl" as navbarRenderer/>
<#import "*/components/order-form.ftl" as orderForm/>
<head>
<#include "*/includes.ftl">
    <link type="text/css" rel="stylesheet" href="./resources/css/order.css"/>
    <link type="text/css" rel="stylesheet" href="./resources/css/chat.css"/>
</head>
<body>
<input id="userRole" type="hidden" value="${userRole}"/>
<@navbarRenderer.renderNavbar userRole="${userRole}" activePage="orders"/>
<#if userRole=="CONSULTANT">
<div class="container">
    <div class="col-md-12 col-xs-12">
        <@orderForm.renderForm titlePage userRole order />
    </div>
</div>
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
      <ol class="discussion">
       <#if messages?has_content>
              <#list messages as message>
                  <#if message.consultant.consultantId == 0>
                      <li class="other">
                          <div class="avatar">
                              <img src="./resources/images/client.png" />
                          </div>
                          <div class="messages">
                              <p>${message.message}</p>
                              <time datetime=${message.dateTime}>${message.dateTime}</time>
                          </div>
                      </li>
                  <#elseif message.client.id == 0>
                      <li class="self">
                          <div class="avatar">
                              <img src="./resources/images/consultant.png" />
                          </div>
                          <div class="messages">
                              <p>${message.message}</p>
                              <time datetime=${message.dateTime}>${message.dateTime}</time>
                          </div>
                      </li>
                  </#if>
              </#list>
       </#if>
      </ol>
    </div>
    <div id="chatFooter">
        <div class="input-group">
            <input type="text" class="form-control no-border-radius" placeholder="Introduce mesaj...">
            <span class="input-group-btn">
            <button class="btn btn-default no-border-radius" type="button">
                <span class="glyphicon glyphicon-send" aria-hidden="true"></span>
            </button>
      </span>

        </div>
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
</#if>
<script src="./resources/script/chat.js"></script>
</body>