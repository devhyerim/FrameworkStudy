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
<title>PositionUpdateForm.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/main.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/jquery-ui.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">

	$(function()
	{	
		// 직위 수정 버튼이 클릭 되었을 때 수행해야 할 코드 처리
		$("#submitBtn").click(function()
		{			
			// 폼 submit 액션 처리 수행
			$("#positionForm").submit();	 
		});
	});

</script>
</head>
<body>
<!------------------------------------------------
   PositionUpdateForm.jsp
   → 직위 데이터 수정 폼 페이지
--------------------------------------------------->

<div>
	<!-- 메뉴 영역 -->
	<div>
		<c:import url="EmployeeMenu.jsp"></c:import>
	</div>
	
	<!-- 콘텐츠 영역 -->
	<div id="content">
		<h1>[ 직위 변경 ]</h1>
		<hr>
		
		<form action="positionupdate.action" method="post" id="positionForm">
			<table>
				<tr>
					<th>직위 번호</th>
					<td>
						<input type="text" id="positionId" name="positionId" 
						value="${position.positionId}" readonly="readonly">
					</td>
				</tr>
				<tr>
					<th>직위 명</th>
					<td>
						<input type="text" id="positionName" name="positionName" placeholder="직위명"
						value="${position.positionName }"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<br><br>
						<button type="button" class="btn" id="submitBtn"
						style="width: 40%;">직위 수정</button>
						<button type="button" class="btn" id="listBtn"
						style="width: 50%;"
						onclick="location.href='positionlist.action'">직위 리스트</button>
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