$('#js-bet-search').on('click',function(){
    var searchRecord = $('#js-search-key').val();

    var language=$('#js-site-language').val();

    window.location="/search?key="+searchRecord+"&language="+language
});


//为目录问题添加事件
$('#js-catalog-content').on('click', '.js-sub-catalog-label', function() {
    var language=$('#js-site-language').val();
    //获取该label中的问题号
    var that = $(this);
    var catalogId = that.find("input[name='catalogId']").val();

    window.location="/catalogue?cid="+catalogId+"&language="+language;
});




