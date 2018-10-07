<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>history</h1>
<table id="grid-basic"
		class="table table-condensed table-hover table-striped">
		<thead>
			<tr>

				<th data-column-id="gameNumber" >比赛编号</th>
				<th data-column-id="league" data-formatter="league">赛制</th>
				<th data-formatter="gameDate" data-sortable="false">投注结束时间</th>
				<th data-column-id="homeName">主队名称</th>>
				<th data-column-id="homeRank">主队排名</th>
				<th data-column-id="guestName">客队名称</th>
				<th data-column-id="guestRank">客队排名</th>
				


			</tr>
		</thead>
		</tbody>
	</table>

</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/sources/bootgrid/jquery.bootgrid.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/sources/js/cp/history.js"></script>
</html>