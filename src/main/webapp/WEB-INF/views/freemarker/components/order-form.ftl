<#macro renderForm titlePage userRole order="">
    <#import "*/components/statusRow.ftl" as statusRow />
 <div id="order-details">
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
                <textarea class="form-control" id="message" name="message">${order.message}</textarea>
            <#else>
                <p class="form-control-static" id="message" name="message">${order.message}</p>
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
         <#if order.orderStatus=="NEW">
             <div class="alert alert-info col-md-5 col-xs-5" role="alert"><p>Comanda dumneavoastra nu a fost procesata de administrator.</p></div>
         <#elseif order.orderStatus="ACCEPTED">
             <div class="alert alert-info col-md-5 col-xs-5" role="alert"><p>Comanda dumneavoastra a fost acceptata de catre administrator.Nici un consultant asignat.</p></div>
         <#elseif order.orderStatus="ASSIGNED">
             <div class="alert alert-info col-md-5 col-xs-5" role="alert" id="assignedTo" name="content">
                 <p>Comanda dumneavoastra este asignata consultantului C${order.consultant.userId}.</p>
                 <p>Comanda va fi realizata in <b>${assignedConsultant.nrOfDays} </b> zile , la pretul de <b>${assignedConsultant.cost}</b> RON.</p>
                 <p>Consultantul va intra in legura cu dumeavoastra in cel mai scurt timp.</p>
                 <p>Va multumesc.</p>
             </div>
         <#elseif order.orderStatus="INPROGRESS">
             <div class="alert alert-info col-md-5 col-xs-5" role="alert" id="assignedTo" name="content">
                 <p>Comanda este in desfasurare. Mai aveti de achitat suma de .</p>
                 <p>Pentru efectuarea platii va rugam apasati <button id="startWork" class="btn btn-info btn-xs">Plata</button>.</p>
                 <p>Folositi chat-ul pentru a lua legatura cu consultantul dumeavoastra.</p>
                 <p>Va multumesc.</p>
             </div>
         </#if>
     </div>
     </#if>

     <#if userRole=="CONSULTANT">
         <#if order.orderStatus == "PENDING" >
             <div class="alert alert-success container" role="alert">
             <span>Ai aplicat deja pentru acesta comanda.<br/>
                        Numar de zile : ${consultantBid.nrOfDays} <br/>
                          Cost : ${consultantBid.cost} RON
             </span>
             </div>
       <#elseif order.orderStatus == 'APPROVED'>
         <div class="alert alert-info container" role="alert">
             <p>Comanda va este asignata.</p>
             <p>Pentru a intra in legatura cu clientul dumneavoastra va rugam apasati
                 <button id="starOrder" class="btn btn-info btn-xs">Start</button></p>
         </div>
        </#if>
     </#if>

     <#if userRole=="ADMINISTRATOR">
         <div class="form-group">
             <div class="col-md-2 col-xs-2">
                 <label for="assignedConsultant" class="control-label">Consultant:</label>
             </div>
             <#if order.orderStatus=="ACCEPTED">
                 <#if orderBids?has_content>
                     <div class="col-md-5 col-xs-5">
                         <input id="assignedConsultant" class="form-control" name="assignedConsultant"/>
                     </div>
                     <div class="col-md-1 col-xs-1">
                         <button id="assignConsultant" class="btn btn-default btn-save">Asigneaza</button>
                     </div>
                     <div class="col-md-offset-2 col-md-5 col-xs-5">
                         <table id="orderBids" class="table">
                             <thead>
                             <th class="table-header">Consultant</th>
                             <th class="table-header">Nr zile</th>
                             <th class="table-header">Pret </th>
                             </thead>
                             <tbody>
                                 <#list orderBids as orderBid>
                                 <tr class="tableRow" id="${orderBid.consultant.userId}">
                                     <td> <a href="./consultantDetails?userId=${orderBid.consultant.userId}"> ${orderBid.consultant.firstName} ${orderBid.consultant.lastName} </a></td>
                                     <td>${orderBid.nrOfDays}</td>
                                     <td>${orderBid.cost}</td>
                                 </tr>
                                 </#list>
                             </tbody>
                         </table>
                     </div>
                 <#else>
                     <div class="col-md-offset-1 col-md-5 col-xs-5">
                         <div class="alert alert-info" role="alert"><p>Nici un consultant nu a aplicat pentru comanda.</p></div>
                 </div>
                 </#if>
             <#elseif order.orderStatus=="ASSIGNED">
                 <div class="col-md-6 col-xs-6">
                     <p id="assignedConsultant" class="form-control-static">
                         <a href="./consultantDetails?userId=${order.consultant.userId}"> ${order.consultant.firstName} ${order.consultant.lastName} </a>
                         :  ${assignedConsultant.nrOfDays} zile | ${assignedConsultant.cost} RON
                     </p>
                 </div>
             <#elseif order.orderStatus=="NEW">
                 <div class="col-md-6 col-xs-6">
                     <div class="alert alert-info" role="alert"><p>Neasignat.</p></div>
                 </div>
                 <div class="col-md-5 col-xs-5">
                     <button class="btn btn-default btn-save" type="submit">Salveaza</button>
                     <a href="/orders"><button class="btn btn-default" type="button" id="cancel-btn">Anuleaza</button></a>
                 </div>
             </#if>
         </div>
     </#if>
</form>
</div>
</#macro>