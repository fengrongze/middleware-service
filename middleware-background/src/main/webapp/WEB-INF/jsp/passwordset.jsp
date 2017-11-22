﻿<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<!--/meta 作为公共模版分离出去-->
<title>基本设置</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
	<span class="c-gray en">&gt;</span>
	个人信息管理
	<span class="c-gray en">&gt;</span>
	登录密码管理
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>
<div class="page-container">
	<form class="form form-horizontal" id="form-article-add">
		<div id="tab-system" class="HuiTab">
			<div class="tabCon">
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						旧密码：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="password" style="width: 30%" id="oldpassowrd" name="oldpassowrd" placeholder="" value="" class="input-text">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						新密码：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="password" style="width: 30%" id="newpassword" name="newpassword" placeholder="" value="" class="input-text">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						确认新密码：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="password" style="width: 30%" id="confirmpwd" name="confirmpwd" placeholder="" value="" class="input-text">
					</div>
				</div>
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button class="btn btn-primary radius"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
			</div>
		</div>
	</form>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="/lib/jQuery.md5.js"></script>
<script type="text/javascript">
$().ready(function() {

	$("#tab-system").Huitab({
		index:0
	});

	//表单验证
	$("#form-article-add").validate({

		rules:{
			oldpassowrd:{
				required:true,
			},
			newpassword:{
				required:true,
			},
			confirmpwd:{
				required:true,
				equalTo: "#newpassword"
			}
		},

		messages: {
			oldpassowrd: "请输入旧密码",
			newpassword: "请输入新密码",
			confirmpwd: {
				required: "请确认新密码",
				equalTo: "两次密码输入不一致"
			}
		},
		onkeyup:false,
		focusCleanup:false,
		success:"valid",
		submitHandler:function(form){
			$.ajax({
				type: 'post',
				dataType: 'text',
				url: '/user/updatepwd',
				data:
				{
					oldpassowrd: $.md5($("#oldpassowrd").val()),
					newpassword: $.md5($("#newpassword").val()),
				},
				success:function(data) {
					if(data == '-1')
					{
						alert("旧密码错误!");
					}
					else if(data == 1)
					{
						alert("密码修改成功!");
					}
				},
				error:function(e){
					alert(e)
				},

			});
		}

	});
});

</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
