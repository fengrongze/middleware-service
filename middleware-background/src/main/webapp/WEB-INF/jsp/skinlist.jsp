<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="/lib/html5shiv.js"></script>
<script type="text/javascript" src="/lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>栏目管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
	<span class="c-gray en">&gt;</span>
	皮肤管理
	<span class="c-gray en">&gt;</span>
	皮肤列表
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>
<div class="page-container">
	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="l">
		<a class="btn btn-primary radius" onclick="system_category_add('添加新皮肤','/skin/add','',450)" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加新皮肤</a>
		</span>
		<span class="r">共有数据：
			<strong>
				<c:choose>
					<c:when test="${siteSkinsList==null || siteSkinsList.size() == 0}">
						0
					</c:when>
					<c:otherwise>${siteSkinsList.size()}</c:otherwise>
				</c:choose>
			</strong>
			条</span>
	</div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th width="80">皮肤名称</th>
					<th width="80">上传时间</th>
					<th width="80">状态</th>
					<th width="80">皮肤管理</th>
					<th width="80">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${siteSkinsList==null || siteSkinsList.size() == 0}">
					<tr class="text-c">
						<td>没有皮肤</td>
					</tr>
				</c:if>
				<c:forEach items="${siteSkinsList}" var="item">
					<tr class="text-c">
						<td>${item.skinName}</td>
						<td>
							<fmt:formatDate value="${item.created}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<c:choose>
							<c:when test="${item.status == 1}">
								启用
							</c:when>
							<c:otherwise>禁用</c:otherwise>
							</c:choose>
						</td>
						<td class="f-14 td-manage">
							<a  onClick="article_shenhe(this,'10001')" href="javascript:;" title="更新">更新</a>
							<a  onClick="article_shenhe(this,'10001')" href="javascript:;" title="下载">下载</a>
							<a  onClick="article_shenhe(this,'10001')" href="javascript:;" title="查看">查看</a>
						</td>
						<td class="f-14 td-manage">
							<a  onClick="article_shenhe(this,'10001')" href="javascript:;" title="编辑">编辑</a>
							<a  onClick="skin_operator('确认要禁用吗？',this,'10001')" href="javascript:;" title="<c:choose><c:when test="${item.status == 1}">禁用</c:when><c:otherwise>启用</c:otherwise></c:choose>">
								<c:choose>
									<c:when test="${item.status == 1}">
										禁用
									</c:when>
									<c:otherwise>启用</c:otherwise>
								</c:choose>
							</a>
							<a onClick="skin_operator('确认要删除吗？',this,'10001')" href="javascript:;" title="删除">删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<%--<script type="text/javascript" src="/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>--%>
<script type="text/javascript" src="/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
$('.table-sort').dataTable({
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"aoColumnDefs": [
	  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
	  {"orderable":false,"aTargets":[0,4]}// 制定列不参与排序
	]
});
/*系统-栏目-添加*/
function system_category_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*系统-栏目-编辑*/
function system_category_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}

function skin_operator(msg,obj,id){
	layer.confirm(msg,function(index){
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