<#macro renderRow statusRow>
    <#switch statusRow>
    <#case "NEW">
        Noua
        <#break>
    <#case "ACCEPTED">
        Acceptata
        <#break>
    <#case "PENDING">
         In asteptare
        <#break>
    <#case "APPROVED">
        Aprobata
            <#break>
    <#case "REJECTED">
        Respinsa
            <#break>
    <#case "ASSIGNED">
        Asignata
            <#break>
    <#case "INPROGRESS">
        In progres
            <#break>
    <#case "DONE">
        Finalizata
            <#break>
    </#switch>
</#macro>