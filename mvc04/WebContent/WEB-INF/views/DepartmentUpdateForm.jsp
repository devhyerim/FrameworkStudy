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
<title>EmployeeUpdateForm.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/main.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/jquery-ui.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">

	$(function()
	{	
		// 부서 수정 버튼이 클릭 되었을 때 수행해야 할 코드 처리
		$("#submitBtn").click(function()
		{			
			// 폼 submit 액션 처리 수행
			$("#departmentForm").submit();	 
		});
	});

</script>
</head>
<body>
<!------------------------------------------------
   DepartmentUpdateForm.jsp
   → 부서 데이터 수정 폼 페이지
--------------------------------------------------->

<div>
	<!-- 메뉴 영역 -->
	<div>
		<c:import url="EmployeeMenu.jsp"></c:import>
	</div>
	
	<!-- 콘텐츠 영역 -->
	<div id="content">
		<h1>[ 부서 변경 ]</h1>
		<hr>
		
		<form action="departmentupdate.action" method="post" id="departmentForm">
			<table>
				<tr>
					<th>부서 번호</th>
					<td>
						<input type="text" id="departmentId" name="departmentId" 
						value="${department.departmentId}" readonly="readonly">
					</td>
				</tr>
				<tr>
					<th>부서 명</th>
					<td>
						<input type="text" id="departmentName" name="departmentName" placeholder="부서명"
						value="${department.departmentName }"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<br><br>
						<button type="button" class="btn" id="submitBtn"
						style="width: 40%;">부서 수정</button>
						<button type="button" class="btn" id="listBtn"
						style="width: 50%;"
						onclick="location.href='departmentlist.action'">부서 리스트</button>
						<br><br>
						
						<span id="err" style="color: red; font-weight: bold; display: none;"></span>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<!-- 회사 소개 및 어플리케이션 소개 영역 -->
	<div id="footer">
		
	</div>
</div>

</body>
</html>