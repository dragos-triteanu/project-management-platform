<#macro renderOders titlePage order="">

<div id="consultant-details">
    <#if consultant??>
    <form class="edit-form form-horizontal" role="form" action="./orders/details" method="GET"
          enctype="multipart/form-data">
        <input type="hidden" name="uid" value="${consultant.uid}"/>
    <#else>
    <form class="create-form form-horizontal" role="form" action="./orders/create" method="POST"
          enctype="multipart/form-data">
    </#if>
    <div class="form-group">
        <h2>${titlePage}</h2>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="lastName" class="control-label">Nume:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if !(order?has_content)>
                <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Introduceti numele.">
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="firstName" class="control-label">Prenume:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if !(order?has_content)>
                <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Introduceti prenumele.">
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="email" class="control-label">Adresa de e-mail:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if !(order?has_content)>
                <input type="text" class="form-control" id="email" name="email" placeholder="Introduceti adresa de mail.">
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="orderDomain" class="control-label">Domeniu:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if !(order?has_content)>
                <p class="form-control-static" id="orderDomain" name="domain">${order.domain}</p>
            <#else>
                <textarea class="form-control" id="orderDomain" name="domain"></textarea>
            </#if>
        </div>
    </div>
</#macro>