<#macro renderTable consultantsList>
    <table class="table">
        <thead>
             <th class="table-header">Nume</th>
             <th class="table-header">Prenume</th>
             <th class="table-header">Numar Telefon</th>
             <th class="table-header">Adresa e-mail</th>
             <th class="table-header">Specialitate</th>
             <th class="table-header">Nr. Proiecte Active</th>
             <th class="table-header">Detalii</th>
        </thead>
        <tbody>
        <#if consultantsList??>
            <#list consultantsList as consultant>
                <tr class="tableRow">
                    <td>${consultant.lastName}</td>
                    <td>${consultant.firstName}</td>
                    <td>${consultant.phoneNumber}</td>
                    <td>${consultant.mail}</td>
                    <td>${consultant.speciality.specialityName}</td>
                    <td>${consultant.numberOfActiveProjects}</td>
                    <td>
                        <form class="details-button-qaa-${consultant.consultantId}" action="./consultantDetails" method="GET">
                            <input type="hidden" name="consultantId" value="${consultant.consultantId}" />
                            <button id="edit${consultant.consultantId}" type="submit" class="btn details-button">Detatlii</button>
                        </form>
                    </td>
                </tr>
           </#list>
           <#else>
            Nici un consultant adaugat!
        </#if>
        </tbody>
    </table>
</#macro>