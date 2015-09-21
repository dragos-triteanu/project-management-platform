<#macro renderTable ordersList userRole>
<table class="table">
    <#if ordersList?has_content>
    <thead>
         <th class="table-header">Domeniu</th>
         <th class="table-header">Subiect</th>
         <th class="table-header">Nr. de pagini</th>
         <th class="table-header">Detalii</th>
    </thead>
    <tbody>
        <#list ordersList as order>
            <tr class="tableRow">
                    <td>${order.domain}</td>
                    <td>${order.subject}</td>
                    <td>${order.nrOfPages}</td>
                    <td>
                    <form class="details-button-qaa-${order.id}" action="./orderDetails" method="GET">
                        <input type="hidden" name="id" value="${order.id}" />
                        <button id="edit${order.id}" type="submit" class="btn details-button">Detalii</button>
                    </form>
                  </td>
            </tr>
        </#list>
    <#else>
    Nici un comanda noua!
    </#if>
    </tbody>
</table>
</#macro>