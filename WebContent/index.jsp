<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<%@page import="com.brtbeacon.HttpPostData.HttpPostDataServlet"%>
<html>
	<head>
		<title>接收Beacon扫描数据范例_5秒刷新一次</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>
	<body>
		<div><%=HttpPostDataServlet.beaconData%></div>
		
		<div>
			<form action="/JavaHttpSample/HttpPostDataServlet" method="post" id="TestForm">
				
			</form>
		</div>
	</body>
</html>

<script type="text/javascript">

setInterval("submitForm()", 1000);
//setInterval("reloadData()",1000*5); //5秒刷新一次	

function submitForm()
{
	document.getElementById("TestForm").submit();
}
	
function reloadData()
{
	window.location.reload();
}

</script>	