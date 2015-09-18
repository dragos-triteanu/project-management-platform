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
            <label for="clientLastName" class="control-label">Nume:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if !(order?has_content)>
                <input type="text" class="form-control" id="clientLastName" name="clientLastName"
                       placeholder="Introduceti numele.">
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="clientFirstName" class="control-label">Prenume:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if !(order?has_content)>
                <input type="text" class="form-control" id="clientFirstName" name="clientFirstName"
                       placeholder="Introduceti prenumele.">
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="clientEmail" class="control-label">Adresa de e-mail:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if !(order?has_content)>
                <input type="text" class="form-control" id="clientEmail" name="clientEmail"
                       placeholder="Introduceti adresa de mail.">
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="orderDomain" class="control-label">Domeniu:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if !(order?has_content)>
                <p class="form-control-static" id="orderDomain" name="orderDomain">${order.domain}</p>
            <#else>
                <textarea class="form-control" id="orderDomain" name="orderDomain"></textarea>
            </#if>
        </div>
    </div>
</#macro>