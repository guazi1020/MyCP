<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(document).ready(function () {
	console.log("this is begiin");
	window.setInterval(function(){
		$.ajax({
			url:'http://localhost:8080/MyCP/getAjaxData1',
			type:'get',
			success:function(result){
				console.log(result);
				console.log(new Date());
			},
			//timeout: 10000,
			error: function(err){
				console.log(err);
			}
		})
	},10000)
	
})
</script>
</head>
<body>
HELLO 
</body>
</html>