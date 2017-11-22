<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge, chrome=1">
    <title>FAQ管理系统</title>
    
    <link rel="stylesheet" href="${resourceUrl}/css/vendors-8e5f64611a.css">
    <script src="${resourceUrl}/js/vendors-7e7de9e9bb.js"></script>

    
    <link rel="stylesheet" href="${resourceUrl}/css/catalogue-5ca0510602.css">

</head>
<body>

    
        
<header class="faq-header">
    <div class="faq-header-wrap">
        <img src="${resourceUrl}/assets/images/logo-1282670e74.png" alt="">
        <h3>帮助中心</h3>
        <#include "language.ftl" >
    </div>
</header>
    

    <main>
    <#include "search.ftl" >
        <div class="content">
        <#include "catalog.ftl" >
            <article>
                
    <header>
        <span>
             <#list superCatalogs?reverse as item>
             <#if item_index=0><em>${item.name}</em>

             <#elseIf item_has_next>
             <em> > ${item.name}</em>

             <#else>
             <a href="javascript:" id="js-catalog-last"> > ${item.name} </a>
             <input type="hidden" id="last-catalog-id" value="${item.id}">
             </#if>

             </#list>
           </span>
    </header>
    <!-- <div>
        <i class="icon icon-remind"></i>
        搜索到有关“皮肤”的帮助信息23条
    </div> -->
    <ul id="js-ul-answer">
        <#list answers as item>
            <li>
                <a href="javascript:">${item.title}</a>
                <input type="hidden" name="id" value="${item.id}">
            </li>

        </#list>

    </ul>

            </article>
        </div>
    </main>
    
    
        <footer class="faq-footer">
    <p>
        Powered By Steam. 2004-2016<br>
        【网络文化经营许可证】渝网文[2015] 1973 - 022号 | 渝ICP备16006465号-2
    </p>
</footer>
    

    
    <script src="${resourceUrl}/js/catalogue-71984d624e.js"></script>

    <script src="${resourceUrl}/js/faqcommon.js"></script>
   <script src="${resourceUrl}/js/faqtitle.js"></script>

<script>
    $('#js-catalog-last').on('click',function(){

        var language=$('#js-site-language').val();

        var catalogId = $('#last-catalog-id').val();

        window.location="/catalogue?cid="+catalogId+"&language="+language;

    });
</script>


</body>
</html>