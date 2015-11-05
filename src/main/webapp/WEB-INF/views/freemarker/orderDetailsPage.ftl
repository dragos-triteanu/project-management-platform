<!DOCTYPE html>
<#assign userRole = "${userRole}">
<#import "*/components/navbar.ftl" as navbarRenderer/>
<#import "*/components/order-form.ftl" as orderForm/>
<head>
<#include "*/includes.ftl">
    <link type="text/css" rel="stylesheet" href="./resources/css/order.css"/>
</head>
<body>
<input id="userRole" type="hidden" value="${userRole}"/>
<@navbarRenderer.renderNavbar userRole="${userRole}" activePage="orders"/>

<div class="container">
    <div class="col-md-12 col-xs-12">
        <@orderForm.renderForm titlePage userRole order />
    </div>
</div>

<#if userRole=="CONSULTANT">
<#if order.orderStatus == "ACCEPTED">
<button type="button" id="bidBtn" class="btn btn-default col-md-offset-4">Liciteaza</button>
<form id="bid" class="create-form bid" role="form" action="/placeBid" method="POST" >
    <input type="hidden" name="orderId" id="orderId" value="${order.orderId}"/>
    <div class="col-md-5 col-md-offset-4">
        <label for="nrOfDays" class="control-label">Numarul de zile</label>
        <select id="nrOfDays" name="nrOfDays">
        <#list 1..40 as x>
            <option>${x}</option>
        </#list>
        </select>
    </div>
    <div class="form-group col-md-5 col-md-offset-4">
        <label for="cost" class="control-label">Cost</label>
        <input type="text" id="cost" name="cost" placeholder="Introduceti suma in RON" required="Introduceti suma." />
    </div>
    <div class="form-group col-md-5 col-md-offset-4">
        <button id="sendBid" type="submit" class="btn btn-default">Trimite</button>
        <button id="cancelBid" type="button" class="btn btn-default">Anuleaza</button>
    </div>
</form>
<#elseif order.orderStatus == "PENDING" >
<form id="viewBid" role="form" action="/getBid" method="GET">
    <input type="hidden" name="orderId" id="orderId" value="${order.orderId}"/>
    <div class="alert alert-success container" role="alert">
        <span>Ai aplicat deja pentru acesta comanda.<br/>
              Numar de zile : ${bid.nrOfDays} <br/>
              Cost : ${bid.cost} RON
          </span>
    </div>
</form>

</#if>
</#if>
<script src="./resources/script/order.js"></script>
</body>
<html>