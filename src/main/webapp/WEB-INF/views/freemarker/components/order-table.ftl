<#macro renderTable ordersList userRole message>
    <#import "*/components/statusRow.ftl" as statusRow />
<table class="table">
    <#if ordersList?has_content>
    <thead>
         <th class="table-header">Domeniu</th>
         <th class="table-header">Subiect</th>
         <th class="table-header">Nr. de pagini</th>
         <th class="table-header">Status </th>
         <th class="table-header">Detalii</th>
    </thead>
    <tbody>
        <#list ordersList as order>
            <tr class="tableRow">
                    <td>${order.domain}</td>
                    <td>${order.subject}</td>
                    <td>${order.nrOfPages}</td>
                    <td><@statusRow.renderRow order.orderStatus/></td>
                    <td>
                     <#if  order.orderStatus == "REJECTED">
                         <form class="details-button-qaa-${order.orderId}" action="./deleteBid" method="POST">
                             <input type="hidden" name="orderId" value="${order.orderId}" />
                             <button id="edit${order.orderId}" type="submit" class="btn btn-danger">Sterge</button>
                     <#elseif order.orderStatus == "APPROVED">
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
     ${message}
    </#if>
    </tbody>
</table>
</#macro>