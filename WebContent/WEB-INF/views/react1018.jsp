<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/sources/react/react.js"></script>
<script src="${pageContext.request.contextPath}/sources/react/react-dom.js"></script>
<script src="${pageContext.request.contextPath}/sources/react/browser.min.js"></script>
<title>React test</title>
</head>
<body>
 <div id="example"></div>
    <script type="text/babel">

	const userc={
      		type:'h1',
      		props:{
      			className:'greeting',
      			children:'hello,world'
      		}
      	};
      	
      	function formate_c(userc){
      		return userc.props.className;
      	};
      	
      	const element_c=(
      		<div>
      			{formate_c(userc)}
      		</div>
      	);
      	
      	ReactDOM.render(
       		//getGreeting(),
       		//Element,
       		//element,
       		element_c,
       		//<div>{getGreeting(userc)}</div>,
        	document.getElementById('example')
      	);
    </script>
</body>
</html>