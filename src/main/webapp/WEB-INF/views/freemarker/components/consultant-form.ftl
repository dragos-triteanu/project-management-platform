<#macro renderForm titlePage consultant categories>

<div id ="consultant-details">
    <#if consultant??>
        <form class="edit-form form-horizontal" role="form" action="./projectdetails/update" method="POST" enctype="multipart/form-data">
        <input type="hidden" name="uid" value="${consultant.uid}"/>
        <#else>
        <form class="create-form form-horizontal" role="form" action="./projectdetails/create" method="POST" enctype="multipart/form-data">
    </#if>
    <div class="form-group">
        <h2>${titlePage}</h2>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="consultantLastName"class="control-label">Nume:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if consultant??>
                <input type="text" class="form-control" id="consultantLastName" value="${consultant.lastName!''}" name="lastNameName" placeholder="Introduceti numele consultantului.">
            <#else>
                <input type="text" class="form-control" id="consultantLastName"  name="lastNameName" placeholder="Introduceti numele consultantului.">
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="consultantFirstName"class="control-label">Prenume:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if consultant??>
                <input type="text" class="form-control" id="consultantFirstName" value="${consultant.firstName!''}" name="consultantFirstName" placeholder="Introduceti prenumele consultantului." required>
            <#else>
                <input type="text" class="form-control" id="consultantFirstName"  name="consultantFirstName" placeholder="Introduceti prenumele consultantului." required>
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="consultantEmail"class="control-label">E-mail:</label>
         </div>
         <div class="col-md-6 col-xs-6">
             <#if consultant??>
                 <input type="email" class="form-control" id="consultantEmail" value="${consultant.mail!''}" name="consultantEmail" placeholder="Introduceti adresa de email a consultantului." required>
             <#else>
                <input type="email" class="form-control" id="consultantEmail"  name="consultantEmail" placeholder="Introduceti adresa de email a consultantului" required>
            </#if>
         </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="consultantPhoneNumber"class="control-label">Nr. de telefon:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if consultant??>
                <input type="number" class="form-control" id="consultantPhoneNumber" value="${consultant.phoneNumer!''}" name="consultantPhoneNumber" placeholder="Introduceti numarul de telefon a consultantului." required>
            <#else>
                <input type="number" class="form-control" id="consultantPhoneNumber"  name="consultantPhoneNumber" placeholder="Introduceti numarul de telefon a consultantului" required>
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="consultantAddress"class="control-label">Adresa:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if consultant??>
                <input type="text" class="form-control" id="consultantAddress" value="${consultant.address!''}" name="consultantAddress" placeholder="Introduceti adresa consultantului." required>
            <#else>
                <input type="text" class="form-control" id="consultantAddress"  name="consultantAddress" placeholder="Introduceti adresa consultantului" required>
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="consultant"class="control-label">Studii absolvite:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if consultant??>
                <input type="text" class="form-control" id="consultantSchool" value="${consultant.school!''}" name="consultantSchool" placeholder="Introduceti ultima scoala absolvita de consultantului." required>
            <#else>
                <input type="text" class="form-control" id="consultantSchool"  name="consultantSchool" placeholder="Introduceti ultima scoala absolvita de consultantului." required>
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
             <label for="consultant" class="control-label">IBAN:</label>
        </div>
        <div class="col-md-6 col-xs-6">
             <#if consultant??>
                 <input type="text" class="form-control" id="consultantIBAN" value="${consultant.ibanCode!''}" name="consultantIBAN" placeholder="Introduceti IBAN-ul consultantului." required>
             <#else>
                <input type="text" class="form-control" id="consultantIBAN"  name="consultantIBAN" placeholder="Introduceti IBAN-ulconsultantului." required>
             </#if>
         </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="consultant"class="control-label">CV:</label>
        </div>
        <div class="col-md-6 col-xs-6">
            <#if consultant?has_content>
                <a href=""> ${consultant.firstName} ${consultant.lastName} </a>
            <#else>
                <input type="file" class="form-control">
            </#if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2 col-xs-2">
            <label for="category-consultant" class="control-label">Specialitate:</label>
        </div>
        <div class="col-md-6 col-xs-6" id="all-categories">
            <select id="category-dropdown" class="form-control">
                <#if categories??>
                    <#list categories as category>
                        <option  value="${category.uid}" <#if (category.uid == consultant.category.uid)> selected="selected" </#if>> ${category.name}</option>
                    </#list>
                </#if>
                <option value="00000000-0000-0000-0000-000000000000">Categorie Noua</option>
            </select>
        </div>
        <div class="col-md-6 col-xs-6 new-category" id="new-category">
            <input type="text" id="category-consultant" placeholder="Numele Categoriei">
            <button type="button" class="btn btn-default btn-sm" id="back-to-all-categories">
                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
            </button>
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