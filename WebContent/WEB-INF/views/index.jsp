<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/sources/bootstrap/css/bootstrap.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/sources/bootstrap/css/dashboard.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/sources/css/main.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>MyCP</title>
</head>
<body>
	<!-- top  -->
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle </span> <span class="icon-bar"></span>
				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>

			<a class="navbar-brand" href="#">MyCP</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#" id="refreshSP">更新当前SP</a></li>
				<li><a href="#" id="refreshResult">更新彩果</a></li>
				<li><a href="#">备注A</a></li>
				<li><a href="#">备注B</a></li>
			</ul>
			<!-- 
			<form class="navbar-form navbar-right">
				<input type="text" class="form-control" placeholder="Search...">
			</form>
			 -->
			 <span class="navbar-right navbar-text" id="term" name="term">当前为第 <span> 20170524 </span> 期</span>
			 
		</div>
	</div>
	</nav>

	<div class="container-fluid">
		<div class="row">

			<!-- 左侧菜单 -->
			<div class="col-sm-3 col-md-2 siderbar" id="left_menu">
				<ul class="nav nav-pills nav-stacked" id="nav_menu">
					<li class="active" id="li_term"><a href="term" id="a_term">当期数据</a> </li>
					<li class="" id="historyData"><a href="history">历史数据</a></li>
					<li class="" id="dataProfile"><a href="char">数据曲线</a></li>
					<li class=""><a href="reacttest">ReactTest</a></li>
				</ul>
			<div class="jisuan">
				<p>主E <input id="ze" type="text"></p>
				<p></p>
				<p>平E <input id="pe" type="text"></p>
				<p></p>
				<p>客E <input id="ke"type="text"></p>
				<p></p>
				<p><input id="bt_jisuan" type="button" value="计算"></p>
				<p>
				全E差:<label id="qec"></label></br>
				客平E差:<label id="kpec"></label></br>
				主客E差:<label id="zkec"></label></br>
				主平E差:<label id="zpec"></label></br>
				比:<label id="bi"></label></br>
				</p>
			</div>
			</div>




			<!-- 工作区域 -->
			<div class="col-sm-9  col-md-10  main">

				<div class="panel panel-default">
					<div class="panel-body" id="content">
				
					</div>
				</div>

			</div>

<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    模态框（Modal）标题
                </h4>
            </div>
            <div class="modal-body">
                在这里添加一些文本
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->






		</div>
	</div>





</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/sources/js/tools/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/sources/bootstrap/js/bootstrap.min.js"></script>
	
<script type="text/javascript"
	src="${pageContext.request.contextPath}/sources/js/general/util.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/sources/js/business/loadPanel.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/sources/js/main.js"></script>

</html>