<#macro renderForm titlePage userRole order="">
    <#import "*/components/statusRow.ftl" as statusRow />
 <div consultantId="consultant-details">
    <#assign isEditable =  userRole == "ADMINISTRATOR" && (order.orderStatus=="NEW")>
     <h2>${titlePage}</h2>
    <#if isEditable==true>
       <form class="create-form form-horizontal" role="form" action="./orders/update" method="POST"
          enctype="multipart/form-data">
    <#else>
       <form class="edit-form form-horizontal" role="form" action="./orders/details" method="GET"
          enctype="multipart/form-data">
    </#if>
     <input type="hidden" name="orderId" id="orderId" value="${order.orderId}"/>
    <#if userRole == "ADMINISTRATOR">
    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="lastName" class="control-label">Client:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <a href="." class="form-control-static" id="lastName">${order.client.lastName} ${order.client.firstName}</a>
        </div>
     </div>
     </#if>
     <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="orderDomain" class="control-label">Domeniu:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if isEditable==true>
                <textarea class="form-control" id="orderDomain" name="domain">${order.domain}</textarea>
            <#else>
                <p class="form-control-static" id="orderDomain" name="domain">${order.domain}</p>
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="subject" class="control-label">Subiect:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if isEditable==true>
                <input type="text" class="form-control" id="subject" name="subject" value="${order.subject}"></input>
            <#else>
                <p class="form-control-static" id="subject" name="subject">${order.subject}</p>
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="nrOfPages" class="control-label">Nr de pagini:</label>
        </div>
        <div class="col-md-6 col-xs-6">
             <#if isEditable==true>
                <input type="number" class="form-control" id="subject" name="nrOfPages" value="${order.nrOfPages}"></input>
             <#else>
                 <p class="form-control-static" id="nrOfPages" name="nrOfPages">${order.nrOfPages}</p>
             </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="tableOfContents" class="control-label">Continutul:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if isEditable==true>
                <textarea class="form-control" id="tableOfContent" name="tableOfContents">${order.tableOfContents}</textarea>
            <#else>
                <p  class="form-control-static" id="tableOfContents" name="tableOfContents">${order.tableOfContents}</p>
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="bibliography" class="control-label">Bibliografie:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if isEditable==true>
                <textarea class="form-control" id="bibliography" name="bibliography">${order.bibliography}</textarea>
            <#else>
                <p  class="form-control-static" id="bibliography" name="bibliography">${order.bibliography}</p>
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="annexes" class="control-label">Anexa:</label>
        </div>
        <div class="col-md-6 col-xs-6">
           <a href="" class="form-control-static" id="annexes" name="annexes">Anexe</a>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="content" class="control-label">Mesaj:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if isEditable==true>
                <textarea class="form-control" id="content" name="content">${order.message}</textarea>
            <#else>
                <p class="form-control-static" id="content" name="content">${order.message}</p>
            </#if>
        </div>
    </div>
    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="status" class="control-label">Status:</label>
        </div>
        <div class="col-md-6 col-xs-6">
          <#if isEditable==true>
              <label class="radio-inline"><input type="radio" name="orderStatus" checked value="NEW">Noua</label>
              <label class="radio-inline"><input type="radio" name="orderStatus" value="ACCEPTED">Accepta</label>
              <label class="radio-inline"><input type="radio" name="orderStatus" value="REJECTED">Respinge</label>
          <#else>
            <p class="form-control-static" id="content" name="content"><@statusRow.renderRow order.orderStatus/></p>
          </#if>
        </div>
    </div>

     <#if userRole == "CLIENT">
         <div class="form-group">
             <div class="col-md-2 col-xs-2">
                 <label for="status" class="control-label">Consultant alocat:</label>
             </div>
             <div class="col-md-6 col-xs-6">
                 <p class="form-control-static" id="assignedTo" name="content">C${order.consultant.userId}</p>
             </div>
         </div>
     </#if>

     <#if userRole=="ADMINISTRATOR">
         <#if order.orderStatus=="NEW">
             <div class="form-group">
                 <div class="col-md-4 col-xs-4">
                     <button class="btn btn-default btn-save" type="submit">Salveaza</button>
                     <a href="/orders"><button class="btn btn-default" type="button" id="cancel-btn">Anuleaza</button></a>
                 </div>
             </div>
         </#if>
     </#if>
</form>
</div>
</#macro>