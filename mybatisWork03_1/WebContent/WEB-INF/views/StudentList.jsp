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
<title>StudentList.jsp</title>
<!-- 부트스트랩 css -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 제이쿼리 script -->
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<!-- 부트스트랩 script -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript">
	
	// 성적 조회 버튼 클릭 시
	$(function()
	{
		$(".btnGrade").click(function()
		{
			$(location).attr("href", "gradelist.action?sid="
                				+  $(this).val());
		});
	});

</script>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">학생 정보 출력</div>
		<div class="panle-body">
			<table class="table">
				<thead>
					<tr>
						<th>MID</th>
						<th>NAME</th>
						<th>TELEPHONE</th>
						<th>성적 조회</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="student" items="${list }">
						<tr>
							<td>${student.sid }</td>
							<td>${student.name }</td>
							<td>${student.tel }</td>
							<td>
								<button type="button" class="btn btn-danger btn-xs btnGrade"
									value="${student.sid }">성적조회</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<button type="button" class="btn btn-default btn-sm"
			 onclick="location.href='studentinsertform.action'">학생등록</button>
		 </div>
	</div>
</body>
</html>