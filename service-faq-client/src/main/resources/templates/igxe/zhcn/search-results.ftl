<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge, chrome=1">
    <title>FAQ管理系统</title>
    
    <link rel="stylesheet" href="${resourceUrl}/css/vendors-8e5f64611a.css">
<script src="${resourceUrl}/js/vendors-7e7de9e9bb.js"></script>

    
    <link rel="stylesheet" href="${resourceUrl}/css/search-results-6d3d662753.css">

</head>
<body>

    
        
<header class="faq-header">
    <div class="faq-header-wrap">
        <img src="${resourceUrl}/assets/images/logo-1282670e74.png" alt="">
        <h3>帮助中心</h3>
    <#include "language.ftl" >
       <#-- <span class="language cssarrow">
            <select>
            <#list languages as item>
                <option value ="${item.code}">${item.name}</option>
            </#list>
            </select>
    </span>-->
    </div>
</header>
    

    <main>
    <#include "search.ftl" >
    
        <div class="content">
        <#include "catalog.ftl" >
            <article>
                
    <header>
        <span>搜索</span>
    </header>
    <div>
        <i class="icon icon-remind"></i>
        搜索到有关“${searchKey}”的帮助信息${searchResult?size}条
    </div>
    <ul id="js-ul-answer">
        <#list  searchResult as item>
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
    

    
<script src="${resourceUrl}/js/search-results-b68866420e.js"></script>
<script src="${resourceUrl}/js/faqcommon.js"></script>
<script src="${resourceUrl}/js/faqtitle.js"></script>
</body>
</html>