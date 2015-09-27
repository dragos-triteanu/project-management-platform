<#macro renderForm titlePage userRole order="">

<div consultantId="consultant-details">
    <#if order?has_content>
    <form class="edit-form form-horizontal" role="form" action="./orders/details" method="GET"
          enctype="multipart/form-data">
        <input type="hidden" name="consultantId" ide="orderId" value="${order.orderId}"/>
    <#else>
    <form class="create-form form-horizontal" role="form" action="./orders/create" method="POST"
          enctype="multipart/form-data">
    </#if>
    <div class="form-group">
        <h2>${titlePage}</h2>
    </div>

  <#if userRole == 'ADMINISTRATOR'>
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
  </#if>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="orderDomain" class="control-label">Domeniu:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if order?has_content>
                <p class="form-control-static" id="orderDomain" name="domain">${order.domain}</p>
            <#else>
                <textarea class="form-control" id="orderDomain" name="domain"></textarea>
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="subject" class="control-label">Subiect:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if order?has_content>
                <p class="form-control-static" id="subject" name="subject">${order.subject}</p>
            <#else>
                <input type="text" class="form-control" id="subject" name="subject" placeholder="Introduceti subiectul lucrarii."></input>
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="nrOfPages" class="control-label">Nr de pagini:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if order?has_content>
                <p class="form-control-static" id="nrOfPages" name="nrOfPages">${order.nrOfPages}</p>
            <#else>
                <input type="number" class="form-control" id="nrOfPages" name="nrOfPages" placeholder="Introduceti numarul de pagini ala lucrarii."></input>
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="tableOfContents" class="control-label">Continutul:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if order?has_content>
                <p  class="form-control-static" id="tableOfContents" name="tableOfContents">${order.tableOfContents}</p>
            <#else>
                <textarea class="form-control" id="tableOfContent" name="tableOfContents" placeholder="Introduceti detalii despre continutul lucrarii."></textarea>
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="bibliography" class="control-label">Bibliografie:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if order?has_content>
                <p  class="form-control-static" id="bibliography" name="bibliography">${order.bibliography}</p>
            <#else>
                <textarea class="form-control" id="bibliography" name="bibliography" placeholder="Introduceti detalii bibliografia lucrarii."></textarea>
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="annexes" class="control-label">Anexa:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if order?has_content>
                <a href="" class="form-control-static" id="annexes" name="bibliography">Anexe</a>
            <#else>
                <textarea class="form-control" id="annexes" name="annexes" placeholder="Introduceti detalii bibliografia lucrarii."></textarea>
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="message" class="control-label">Mesaj:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if order?has_content>
                <p class="form-control-static" id="message" name="message">${order.message}</p>
            <#else>
                <textarea class="form-control" id="message" name="message" placeholder="Introduceti mesaj."></textarea>
            </#if>
        </div>
    </div>
    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="status" class="control-label">Status:</label>
        </div>
        <div class="col-md-6 col-xs-6">
                <p class="form-control-static" id="message" name="message">${order.orderStatus}</p>
        </div>
    </div>
</form>
</div>
</#macro>