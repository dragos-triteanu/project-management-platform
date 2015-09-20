<#macro renderCategories categories isShown="false">
    <#if categories??>
        <div class="categoryContainer" style="${(isShown == "false")?string('display: none;','')}">
            <table class="table">
                <thead>
                    <th class="table-header">Categorie</th>
                    <th class="table-header">Sterge</th>
                </thead>
                <tbody>
                    <#list categories as category>
                    <tr>
                       <td class="table-data"><p class="categoryName">${category.specialityName}</p>
                           <input type="hidden" class="categoryId" value="${category.specialityId}"/></td>
                       <td>
                           <button class="btn btn-default removeCategory" deleteId="${category.specialityId}">
                               <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                           </button>
                       </td>
                    </tr>
                    </#list>
                    <tr>
                        <td><input type="text" name="speciality" class="newCategoryNameInput" placeholder="Categorie noua" /></td>
                        <td>
                            <button class="btn btn-primary addCategoryBtn" type="submit">Adauga</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </#if>
</#macro>