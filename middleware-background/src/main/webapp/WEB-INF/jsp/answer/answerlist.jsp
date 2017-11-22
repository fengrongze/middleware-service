<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
	<jsp:include page="../common/header.jsp"></jsp:include>
<title>资讯列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 资讯管理 <span class="c-gray en">&gt;</span> 资讯列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
	 <span class="select-box inline">
		<select name="language" class="select">
			<option value="0">全部语言</option>
			<option value="1">中文</option>
			<option value="2">英文</option>
		</select>
		</span>
		<input type="text" name="" id="" placeholder=" 标题名称" style="width:250px" class="input-text">

		<button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜问题</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"> <a class="btn btn-primary radius" data-title="添加问题" data-href="/answer/add" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加问题</a></span> </div>
	<div class="mt-20">
		<%--<table id="js-datatable-answer" class="table table-border table-bordered table-bg table-hover table-sort table-responsive">

		</table>--%>

			<table id="table_report" class="table table-border table-bordered table-bg table-hover table-sort table-responsive">

			</table>
	</div>
	<div class="space-24ger"></div>
	<!-- PAGE CONTENT ENDS HERE -->
	</div><!--/row-->
	</div>
<!--_footer 作为公共模版分离出去-->

<jsp:include page="../common/footer.jsp"></jsp:include>

<!--请在下方写此页面业务相关的脚本-->

<script type="text/javascript">


	_tableList =$('#table_report').DataTable({
			"showRowNumber":true,
			"bServerSide": true,
			"bJQueryUI": false,
			"bSort":false,
			"sDom": '<"">t<"row-fluid"<"span6"i><"span6"p>>',
			"sAjaxSource": "answer/query.json",
			"aoColumnDefs": [{sDefaultContent: '',aTargets: [ '_all' ]}],

			"order": [[1, 'asc']],
			"columnDefs": [{
				"searchable": false,
				"orderable": false,
				"targets": 0
			}],
			"aoColumns": [
				{"sTitle": "序号"},
				{ "sTitle" : "编号","mData": "id"},
				{ "sTitle" : "类别","mData": "catalog"},
				{ "sTitle" : "标题","mData": "title"},
				{ "sTitle" : "状态","mData": "status", "mRender" : function ( status, type, full ) {
					if(status == 1){
						return  "有效";
					}else if (status == 2){
						return "无效"
					}else{
						return "未知"
					}
				}},
				{ "sTitle" : "操作","mData": "id"  , "mRender" : function ( data, type, full ) {
					return "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"article_edit('资讯编辑','/answer/add?id="+data+"','10001')\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe6df;</i></a> <a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"article_del(this,'10001')\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
				} }
			],
			"oLanguage" : {
				"sProcessing" : "正在加载中......",
				"sLengthMenu" : "每页显示 _MENU_ 条记录",
				"sZeroRecords" : "没有数据！",
				"sEmptyTable" : "表中无数据存在！",
				"sInfo" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
				"sInfoEmpty" : "显示0到0条记录",
				"sInfoFiltered" : "数据表中共为 _MAX_ 条记录",
				"oPaginate" : {
					"sFirst" : "首页",
					"sPrevious" : "上一页",
					"sNext" : "下一页",
					"sLast" : "末页"
				}
			},"fnDrawCallback": function(){ //解决序号列没法生成的问题
				var api = this.api();
				var startIndex= api.context[0]._iDisplayStart;//获取到本页开始的条数
				api.column(0).nodes().each(function(cell, i) {
					cell.innerHTML =i + 1;
				});
			},"fnServerData" 	: function ( sSource, aoData, fnCallback, oSettings ) {
				oSettings.jqXHR = $.ajax( {
					"dataType" 	: 'json',
					"type" 		: "POST",
					"url" 		: sSource,
					"data" 		: {
						'pageIndex': aoData[3].value/aoData[4].value+1,
						'pageSize' 	: aoData[4].value,
					},
					"success"	: function (data) {
						alert(JSON.stringify(data))
						fnCallback(data);
					}
				} );
			},
		});

/*资讯-添加*/
function article_add(title,url,w,h){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*资讯-编辑*/
function article_edit(title,url,id,w,h){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*资讯-删除*/
function article_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '',
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").remove();
				layer.msg('已删除!',{icon:1,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}

/*资讯-审核*/
function article_shenhe(obj,id){
	layer.confirm('审核文章？', {
		btn: ['通过','不通过','取消'], 
		shade: false,
		closeBtn: 0
	},
	function(){
		$(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="article_start(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
		$(obj).remove();
		layer.msg('已发布', {icon:6,time:1000});
	},
	function(){
		$(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="article_shenqing(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-danger radius">未通过</span>');
		$(obj).remove();
    	layer.msg('未通过', {icon:5,time:1000});
	});	
}
/*资讯-下架*/
function article_stop(obj,id){
	layer.confirm('确认要下架吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="article_start(this,id)" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe603;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已下架</span>');
		$(obj).remove();
		layer.msg('已下架!',{icon: 5,time:1000});
	});
}

/*资讯-发布*/
function article_start(obj,id){
	layer.confirm('确认要发布吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="article_stop(this,id)" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
		$(obj).remove();
		layer.msg('已发布!',{icon: 6,time:1000});
	});
}
/*资讯-申请上线*/
function article_shenqing(obj,id){
	$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">待审核</span>');
	$(obj).parents("tr").find(".td-manage").html("");
	layer.msg('已提交申请，耐心等待审核!', {icon: 1,time:2000});
}

</script> 
</body>
</html>