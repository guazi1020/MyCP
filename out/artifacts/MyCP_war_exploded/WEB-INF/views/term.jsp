<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/sources/bootgrid/jquery.bootgrid.css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<button id="refresh" class="btn btn-primary">重新获取比赛数据</button>
	<button id ="getData" class="btn btn-primary">预测数据</button>
	<table id="grid-basic"
		class="table table-condensed table-hover table-striped">
		<thead>
			<tr>

				<th data-column-id="gameNumber" data-identifier="true" data-header-align="center" data-align="center" >比赛编号</th>
				<th data-column-id="league" data-formatter="league" data-header-align="center" data-align="center">赛制</th>
				<th data-formatter="closeDate" data-sortable="false" data-header-align="center" data-align="center">投注结束时间</th>
				<th data-column-id="homeName" data-sortable="false" data-header-align="center" data-align="center">主队</th>
				<th data-column-id="homeRank" data-sortable="false" data-header-align="center" data-align="center">主队排名</th>
				<th data-column-id="guestName" data-sortable="false" data-header-align="center" data-align="center">客队</th>
				<th data-column-id="guestRank" data-sortable="false" data-header-align="center" data-align="center">客队排名</th>
				<th data-formatter="homeE" data-sortable="false" data-header-align="center" data-align="center">主E</th>
				<th data-formatter="pingE" data-sortable="false" data-header-align="center" data-align="center">平E</th>
				<th data-formatter="guestE" data-sortable="false" data-header-align="center" data-align="center">客E</th>
				<th data-formatter="E" data-sortable="false" data-header-align="center" data-align="left">全|平客|主客|主平</th>
				<th data-formatter="sps" data-sortable="false" data-header-align="center" data-align="center">sps</th>
 				<th data-column-id="commands" data-formatter="commands" data-sortable="false">Commands</th>

			</tr>
		</thead>
		</tbody>
	</table>
	
	
<!-- Button trigger modal -->


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>

</body>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/sources/bootgrid/jquery.bootgrid.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/sources/js/general/util.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/sources/js/tools/jfunk-0.0.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/sources/js/cp/term.js"></script>

</html>