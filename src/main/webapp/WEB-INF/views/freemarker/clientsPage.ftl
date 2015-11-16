<!DOCTYPE html>
<#assign userRole = "${userRole}">
<#import "*/components/navbar.ftl" as navbarRenderer/>
<#import "*/components/client-table.ftl" as clientsTable/>
<#import "*/components/search.ftl" as search/>
<html>
<header>
<#include "*/includes.ftl">
    <link rel="stylesheet" type="text/css" href="./resources/css/client.css">
</header>
<body>
<input id="userRole" type="hidden" class="hidden" value="${userRole}" />
<@navbarRenderer.renderNavbar userRole="${userRole}" activePage="clients"/>

<#if userRole == 'ADMINISTRATOR'>
<div class="container">
    <div class ="panel-group">
        <div class="h2 consultantTitle" >
            Clienti
        </div>
        <div class="clients-table generic-table">
            <@clientsTable.renderTable clientsList/>
        </div>
    </div>
</div>

<#else>
Access Denied.
</#if>
<script src="./resources/script/search.js"></script>
</body>
</html>