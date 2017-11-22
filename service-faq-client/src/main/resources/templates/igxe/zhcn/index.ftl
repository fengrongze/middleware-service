<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge, chrome=1">
    <title>FAQ管理系统</title>
    
    <link rel="stylesheet" href="${resourceUrl}/css/vendors-8e5f64611a.css">
<script src="${resourceUrl}/js/vendors-7e7de9e9bb.js"></script>

    
    <link rel="stylesheet" href="${resourceUrl}/css/index-ccb81483ae.css">

</head>
<body>

    
        
<header class="faq-header">
    <div class="faq-header-wrap">
        <img src="${resourceUrl}/assets/images/logo-1282670e74.png" alt="">
        <h3>帮助中心1</h3>

   <#-- <span class="language cssarrow">
            <select>
            <#list languages as item>
            <option value ="${item.code}">${item.name}</option>
            </#list>
            </select>
    </span>-->
    <#include "language.ftl" >

    </div>
</header>
    

    <main>
       <#include "search.ftl" >

        <div class="content">
        <#include "catalog.ftl" >
            <article>
                
    <header>
        <span>交易流程</span>
    </header>

    <section>
        <header>
            <i class="icon icon-consignment"></i>
            <div>寄售模式</div>
        </header>
        <ul>
            <li>
                <i class="icon icon-put-in-storage"></i>
                <span>卖家入库</span>
            </li>
            <li>
                <i class="icon icon-shelves"></i>
                <span>卖家上架</span>
            </li>
            <li>
                <i class="icon icon-buy"></i>
                <span>买家购买</span>
            </li>
            <li>
                <i class="icon icon-retrieve"></i>
                <span>买家取回</span>
            </li>
            <li>
                <i class="icon icon-clearing"></i>
                <span>订单结算</span>
            </li>
        </ul>
    </section>

    <section>
        <header>
            <i class="icon icon-sale"></i>
            <div>自售模式</div>
        </header>
        <ul>
            <li>
                <i class="icon icon-binding-rebot"></i>
                <span>卖家绑定机器人</span>
            </li>
            <li>
                <i class="icon icon-put-in-storage"></i>
                <span>卖家入库</span>
            </li>
            <li>
                <i class="icon icon-shelves"></i>
                <span>卖家上架</span>
            </li>
            <li>
                <i class="icon icon-buy"></i>
                <span>买家购买</span>
            </li>
            <li>
                <i class="icon icon-retrieve"></i>
                <span>买家取回</span>
            </li>
            <li>
                <i class="icon icon-clearing"></i>
                <span>订单结算</span>
            </li>
        </ul>
    </section>

    <section>
        <header>
            <i class="icon icon-hangup"></i>
            <div>挂售模式</div>
        </header>
        <ul>
            <li>
                <i class="icon icon-info-input"></i>
                <span>卖家录入<br>商品信息</span>
            </li>
            <li>
                <i class="icon icon-shelves"></i>
                <span>卖家上架</span>
            </li>
            <li>
                <i class="icon icon-buy"></i>
                <span>买家购买</span>
            </li>
            <li>
                <i class="icon icon-shipping"></i>
                <span>卖家发货</span>
            </li>
            <li>
                <i class="icon icon-retrieve"></i>
                <span>买家取回</span>
            </li>
            <li>
                <i class="icon icon-clearing"></i>
                <span>订单结算</span>
            </li>
        </ul>
    </section>

    <div class="faq-list">
        <header>
            <span>常见问题</span>
        </header>
        <ul>
            <li><a href="javascript:">买了皮肤，为什么没有收到报价？</a></li>
            <li><a href="javascript:">买了皮肤，为什么没有收到报价？</a></li>
            <li><a href="javascript:">买了皮肤，为什么没有收到报价？</a></li>
            <li><a href="javascript:">买了皮肤，为什么没有收到报价？</a></li>
            <li><a href="javascript:">花儿为什么这样红就是这样红红红红？</a></li>
            <li><a href="javascript:">花儿为什么这样红就是这样红红红红？</a></li>
            <li><a href="javascript:">花儿为什么这样红翻天天天翻红？</a></li>
            <li><a href="javascript:">花儿为什么这样红翻天天天翻红？</a></li>
        </ul>
    </div>

            </article>
        </div>
    </main>
    
    
        <footer class="faq-footer">
    <p>
        Powered By Steam. 2004-2016<br>
        【网络文化经营许可证】渝网文[2015] 1973 - 022号 | 渝ICP备16006465号-2
    </p>
</footer>
    

    
    <script src="${resourceUrl}/js/index-b41fc3b984.js"></script>


<script src="${resourceUrl}/js/faqcommon.js"></script>



</body>
</html>