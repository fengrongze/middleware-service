<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
	<jsp:include page="../common/header.jsp"></jsp:include>
<!--/meta 作为公共模版分离出去-->

<title>添加用户 - H-ui.admin v3.1</title>
<meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<article class="page-container">
	<form action="/catalog/add" method="post" class="form form-horizontal" id="form-member-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">上级目录：</label>
			<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select class="select" size="1" name="superId">
					<option value="0">顶级目录</option>
					<c:forEach var="item" items="${catalogQueryItems}">
						<option value="${item.id}" >${item.description}</option>
					</c:forEach>
				</select>
				</span> </div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">类型：</label>
			<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select class="select" size="1" name="catalogType">
					<option value="0" selected>目录</option>
					<option value="1">页面</option>
				</select>
				</span> </div>
		</div>


		<%--<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">语言：</label>
			<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select class="select" size="1" name="language">
					<c:forEach var="item" items="${languages}">
						<option value="${item.code}" >${item.name}</option>
					</c:forEach>
				</select>
				</span> </div>
		</div>--%>

<c:choose>
	<c:when test="${function eq 'add'}">
		<c:forEach var="item" items="${languages}">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">${item.name}-名称：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<textarea name=${item.code}_name cols="" rows="" class="textarea"  placeholder="说点什么...最少输入10个字符" onKeyUp="$.Huitextarealength(this,100)"></textarea>
					<p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
				</div>
			</div>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<c:forEach var="item" items="${catalogLanguageItems}">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">${item.language}-名称：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<textarea name=${item.languageCode}_name cols="" rows="" class="textarea"  placeholder="说点什么...最少输入10个字符" onKeyUp="$.Huitextarealength(this,100)">${item.content}</textarea>
					<p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
				</div>
			</div>
		</c:forEach>

	</c:otherwise>
</c:choose>





		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<button onClick="article_save();" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 提交</button>
			</div>
		</div>
	</form>
</article>

<!--_footer 作为公共模版分离出去-->
<jsp:include page="../common/footer.jsp"></jsp:include>
<script type="text/javascript">
	function article_save(){
		document.getElementById("form-member-add").submit()
		window.parent.location.reload();
	}


</script> 
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>