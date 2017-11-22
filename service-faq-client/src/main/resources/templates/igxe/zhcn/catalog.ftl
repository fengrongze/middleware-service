<nav id="js-catalog-content">
<#list catalogs as catalog>
    <div class="reverse">${catalog.topCatalog.name}</div>
    <#list catalog.subCatalogs as item>
        <ul class="js-sub-catalog">
            <li class="js-sub-catalog-label">
                <a href="javascript:void(0)" >${item.name}</a>
                <input type="hidden" name="catalogId" value="${item.id}">
            </li>
        </ul>
    </#list>
</#list>
</nav>