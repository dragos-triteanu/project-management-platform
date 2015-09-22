<#macro renderForm titlePage consultant="" categories="" >
<div id ="consultant-details">
    <#if consultant?has_content>
        <form class="edit-form form-horizontal" role="form" action="./projectdetails/update" method="POST" enctype="multipart/form-data">
        <input type="hidden" name="uid" value="${consultant.id}"/>
    <#else>
        <form class="create-form form-horizontal" role="form" action="./createConsultant" method="POST" enctype="multipart/form-data">
    </#if>
    <div class="form-group">
        <h2>${titlePage}</h2>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="consultantLastName"class="control-label">Nume:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if consultant?has_content>
                <input type="text" class="form-control" id="consultantLastName" value="${consultant.lastName!''}" name="lastName" placeholder="Introduceti numele consultantului.">
            <#else>
                <input type="text" class="form-control" id="consultantLastName" value="Sandu"  name="lastName" placeholder="Introduceti numele consultantului.">
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="consultantFirstName"class="control-label">Prenume:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if consultant?has_content>
                <input type="text" class="form-control" id="consultantFirstName" value="${consultant.firstName!''}" name="consultantFirstName"  placeholder="Introduceti prenumele consultantului." required>
            <#else>
                <input type="text" class="form-control" id="consultantFirstName" value="Lungu" name="firstName" placeholder="Introduceti prenumele consultantului." required>
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="consultantEmail"class="control-label">E-mail:</label>
         </div>
         <div class="col-md-6 col-xs-6">
             <#if consultant?has_content>
                 <input type="email" class="form-control" id="consultantEmail" value="${consultant.mail!''}" name="mail"  placeholder="Introduceti adresa de email a consultantului." required>
             <#else>
                <input type="email" class="form-control" id="consultantEmail"  name="mail" value="sandu.lungu@gmail.com" placeholder="Introduceti adresa de email a consultantului" required>
            </#if>
         </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="consultantPhoneNumber"class="control-label">Nr. de telefon:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if consultant?has_content>
                <input type="number" class="form-control" id="consultantPhoneNumber" value="${consultant.phoneNumber!''}" name="phoneNumber" placeholder="Introduceti numarul de telefon a consultantului." required>
            <#else>
                <input type="number" class="form-control" id="consultantPhoneNumber" value="0264444555" name="phoneNumber" placeholder="Introduceti numarul de telefon a consultantului" required>
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="consultantAddress"class="control-label">Adresa:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if consultant?has_content>
                <input type="text" class="form-control" id="consultantAddress" value="${consultant.address!''}" name="address" placeholder="Introduceti adresa consultantului." required>
            <#else>
                <input type="text" class="form-control" id="consultantAddress"  value="adresa" name="address" placeholder="Introduceti adresa consultantului" required>
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="consultant"class="control-label">Studii absolvite:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if consultant?has_content>
                <input type="text" class="form-control" id="consultantSchool" value="${consultant.studies!''}" name="studies" placeholder="Introduceti ultima scoala absolvita de consultantului." required>
            <#else>
                <input type="text" class="form-control" id="consultantSchool"  value ="studii"name="studies" placeholder="Introduceti ultima scoala absolvita de consultantului." required>
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
             <label for="consultant" class="control-label">IBAN:</label>
        </div>
        <div class="col-md-6 col-xs-6">
             <#if consultant?has_content>
                 <input type="text" class="form-control" id="consultantIBAN" value="${consultant.ibanCode!''}" name="ibanCode" placeholder="Introduceti IBAN-ul consultantului." required>
             <#else>
                <input type="text" class="form-control" id="consultantIBAN"  value="iban" name="ibanCode" placeholder="Introduceti IBAN-ulconsultantului." required>
             </#if>
         </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="consultant"class="control-label">CV:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if consultant?has_content>
                <#if consultant.cvURL??>
                <a href="${consultant.cvURL}"> ${consultant.firstName} ${consultant.lastName} </a>
            	</#if>
            <#else>
                <input type="file" name="cvFile" class="form-control">
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="category-consultant" class="control-label">Specialitate:</label>
        </div>
        <div class="col-md-4 col-xs-4" id="all-categories">
            <select id="category-dropdown" name="specialityId" class="form-control">
                <#if categories?has_content>
                    <#list categories as speciality>
                        <#if consultant?has_content>
                            <option  value="${speciality.specialityId}" name="specialityId" > ${speciality.specialityName}</option>
                        <#else>
                            <option  value="${speciality.specialityId}" name="specialityId" selected="Medicine" > ${speciality.specialityName}</option>
                        </#if>
                    </#list>
                </#if>
            </select>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-4 col-xs-4 col-md-offset-5">
             <button class="btn btn-default btn-save" type="submit">Salveaza</button>
             <button class="btn btn-default" type="button" id="cancel-btn">Anuleaza</button>
        </div>
    </div>
    </form>
</div>
</#macro>