<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>StudentInsertForm.jsp</title>
<!-- 부트스트랩 css -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 제이쿼리 script -->
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<!-- 부트스트랩 script -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">학생 등록</div>
		<div class="panle-body">
			<form action="studentinsert.action" method="post">
				<table class="table">
					<tr>
						<th>NAME</th>
						<td><input type="text" id="name" name="name"></td>
					</tr>
					<tr>
						<th>TELEPHONE</th>
						<td><input type="text" id="tel" name="tel"></td>
					</tr>
				</table>
				<button type="submit" class="btn btn-default btn-sm">학생등록</button>
			</form>
		 </div>
	</div>
</body>
</html>