<#macro renderTable ordersList userRole content>
    <#import "*/components/statusRow.ftl" as statusRow />
<table class="table">
    <#if ordersList?has_content>
    <thead>
         <#if userRole == "ADMINISTRATOR">
            <th class="table-header">Client</th>
            <th class="table-header">Consultant</th>
         </#if>
         <th class="table-header">Domeniu</th>
         <th class="table-header">Subiect</th>
         <#if userRole == "CONSULTANT">
             <th class="table-header">Nr.pagini</th>
         </#if>
         <th class="table-header">Status </th>
         <th class="table-header">Detalii</th>
    </thead>
    <tbody>
        <#list ordersList as order>
            <tr class="tableRow">
                    <#if userRole == "ADMINISTRATOR">
                        <td><a href="./clientDetails?userId=${order.client.userId}"> ${order.client.firstName} ${order.client.lastName}</a></td>
                        <td>
                            <#if order.consultant??>
                                <a href="./consultantDetails?userId=${order.consultant.userId}"> ${order.consultant.firstName} ${order.consultant.lastName}</a>
                            <#else>
                                N/A
                            </#if>
                        </td>
                    </#if>
                    <td>${order.domain}</td>
                    <td>${order.subject}</td>
                    <#if userRole == "CONSULTANT">
                        <td>${order.nrOfPages}</td>
                    </#if>
                    <td><@statusRow.renderRow order.orderStatus/></td>
                    <td>
                     <#if  order.orderStatus == "REJECTED">
                         <form class="details-button-qaa-${order.orderId}" action="./deleteBid" method="POST">
                             <input type="hidden" name="orderId" value="${order.orderId}" />
                             <button id="edit${order.orderId}" type="submit" class="btn btn-danger">Sterge</button>
                     <#elseif order.orderStatus == "APPROVED" >
                        <form class="details-button-qaa-${order.orderId}" action="./myOrderDetails" method="GET">
                            <input type="hidden" name="orderId" value="${order.orderId}" />
                            <button id="edit${order.orderId}" type="submit" class="btn details-button">Detalii</button>
                     <#else>
                         <form class="details-button-qaa-${order.orderId}" action="./orderDetails" method="GET">
                             <input type="hidden" name="orderId" value="${order.orderId}" />
                             <button id="edit${order.orderId}" type="submit" class="btn details-button">Detalii</button>
                     </#if>
                    </form>
                    </td>
            </tr>
        </#list>
    <#else>
     ${content}
    </#if>
    </tbody>
</table>
</#macro>

<#macro renderTableForClient ordersList content>
    <#import "*/components/statusRow.ftl" as statusRow />
<table class="table">
    <#if ordersList?has_content>
        <thead>
            <th class="table-header">Domeniu</th>
            <th class="table-header">Subiect</th>
            <th class="table-header">Status </th>
            <th class="table-header">Detalii</th>
        </thead>
        <tbody>
            <#list ordersList as order>
            <tr class="tableRow">
                <td>${order.domain}</td>
                <td>${order.subject}</td>
                <td><@statusRow.renderRow order.orderStatus/></td>
                <td>
                    <form class="details-button-qaa-${order.orderId}" action="./myOrderDetails" method="GET">
                        <input type="hidden" name="orderId" value="${order.orderId}" />
                        <button id="edit${order.orderId}" type="submit" class="btn details-button">Detalii</button>
                    </form>
                </td>
            </tr>
            </#list>
        </tbody>
    <#else>
        s${content}
    </#if>
</table>
</#macro>