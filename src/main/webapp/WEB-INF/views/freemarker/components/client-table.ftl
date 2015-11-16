<#macro renderTable clientsList>
<table class="table">
    <#if clientsList?has_content>
        <thead>
        <th class="table-header">Nume</th>
        <th class="table-header">Prenume</th>
        <th class="table-header">Adresa e-mail</th>
        <th class="table-header">Ultima accesare</th>
        <th class="table-header">Membru din :</th>
        </thead>
    <tbody>
        <#list clientsList as client>
        <tr class="tableRow">
            <td>${client.lastName}</td>
            <td>${client.firstName}</td>
            <td>${client.mail}</td>
            <#if client.lastLogin?has_content>
                <td>${client.lastLogin}</td>
            <#else>
            <td class="text-danger"> <span class="glyphicon glyphicon-remove" aria-hidden="true"> Neautentificat</span></td>
            </#if>
            <td>${client.createdOn?datetime?string("dd-MM-yyyy")}</td>
        </#list>
    <#else>
    Nici un client!
    </#if>
</tbody>
</table>
</#macro>