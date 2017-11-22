<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
	<jsp:include page="../common/header.jsp"></jsp:include>

<title>意见反馈</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 评论管理 <span class="c-gray en">&gt;</span> 意见反馈 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c"> 日期范围：
		<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" style="width:120px;">
		<input type="text" class="input-text" style="width:250px" placeholder="输入关键词" id="" name="">
		<button type="submit" class="btn btn-success radius" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜意见</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"> <a class="btn btn-primary radius" data-title="添加问题" data-href="article-add.html" onclick="member_edit('类型编辑','/catalog/add','10001')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加问题</a></span> </div>
	<div class="mt-20">
		<table id="table_report" class="table table-border table-bordered table-hover table-bg table-sort">

		</table>
	</div>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>
<script type="text/javascript">
	_tableList =$('#table_report').DataTable({
		"showRowNumber":true,
		"bServerSide": true,
		"bJQueryUI": false,
		"bSort":false,
		"sDom": '<"">t<"row-fluid"<"span6"i><"span6"p>>',
		"sAjaxSource": "catalog/query.json",
		"aoColumnDefs": [{sDefaultContent: '',aTargets: [ '_all' ]}],
		"iDisplayLength" : 10, //默认显示的记录数
		"order": [[1, 'asc']],
		"columnDefs": [{
			"searchable": false,
			"orderable": false,
			"targets": 0
		}],
		"aoColumns": [
			{"sTitle": "序号"},
			{ "sTitle" : "默认名称","mData": "description"},
			{ "sTitle" : "名词","mData": "content"},
			{ "sTitle" : "语言","mData": "language"},
			{ "sTitle" : "页面类型","mData": "type", "mRender" : function ( data, type, full ) {
				if(data == 1){
					return  "页面";
				}else{
					return "目录"
				}
			}},
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
				return "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"member_edit('类型编辑','/catalog/edit?id="+data+"','10001')\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe6df;</i></a> <a style=\"text-decoration:none\" onClick=\"member_stop(this,'10001')\" href=\"javascript:;\" title=\"停用\"><i class=\"Hui-iconfont\">&#xe631;</i></a><a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"article_del(this,'10001')\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
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


/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*用户-查看*/
function member_show(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*用户-停用*/
function member_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_start(this,id)" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
		$(obj).remove();
		layer.msg('已停用!',{icon: 5,time:1000});
	});
}

/*用户-启用*/
function member_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_stop(this,id)" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		$(obj).remove();
		layer.msg('已启用!',{icon: 6,time:1000});
	});
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
/*用户-编辑*/
function member_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*密码-修改*/
function change_password(title,url,id,w,h){
	layer_show(title,url,w,h);	
}
/*用户-删除*/
function member_del(obj,id){
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
</script>
</body>
</html>