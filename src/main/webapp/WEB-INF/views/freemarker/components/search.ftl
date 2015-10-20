<#macro search searchType categoriesForSearch>
<link rel="stylesheet" type="text/css" href="./resources/css/search.css">
<div class="input-group group-search">
    <input id="searchType" type="hidden" class="hidden" value="${searchType}" />
    <div class="float">
         <input id="inputSearch" type="text"  placeholder="Cauta in ...">
    </div>
    <div class="float">
        <select id="searchCategory" name="searchCategory" class="category-search">
            <#list categoriesForSearch as category >
                <option value="${category}"> ${category}</option>
            </#list>
        </select>
    </div>
    <div class="float">
        <button class="btn-search" type="button">
            <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
       </button>
    </div>
</div>
</#macro>