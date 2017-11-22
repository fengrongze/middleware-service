//为问题title添加事件
$('#js-ul-answer').on('click', 'li', function() {
    var language=$('#js-site-language').val();
    //获取该label中的问题号
    var that = $(this);
    var answerId = that.find("input[name='id']").val();
    window.location="/detail?id="+answerId+"&language="+language;
});







