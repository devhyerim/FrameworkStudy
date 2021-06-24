<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PositionList.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/main.css" />
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">

	$(function()
	{
		$(".updateBtn").click(function()
		{
			$(location).attr("href", "positionupdateform.action?positionId=" + $(this).val());
		});
		
		$(".deleteBtn").click(function()
		{
			if(confirm("현재 선택한 데이터를 정말 삭제하시겠습니까?"))
			{
				$(location).attr("href", "positiondelete.action?positionId=" +  $(this).val());
			}
		});
	});

</script>

</head>
<body>
<!------------------------------------------------
   PositionList.jsp
   → 직위 리스트 출력 페이지 (관리자)
--------------------------------------------------->

<div>
	<!-- 메뉴 영역 -->
	<div>
		<c:import url="EmployeeMenu.jsp"></c:import>
	</div>
	
	<!-- 콘텐츠 영역 -->
	<div id="content">
		
		<h1>[ 직위 관리 (관리자 전용) ]</h1>
		<hr>
		
		<div>
			<form action="">
				<input type="button" value="직위 추가" class="btn"
				onclick="location.href='positioninsertform.action'">
			</form>
		</div>
		<br><br>
	
		<table id="positions" class="table">
			<tr>
				<th>직위아이디</th>
				<th>직위명</th>
				<th>직위 인원 수</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
			<c:forEach var="position" items="${positionList }">
			<tr>
				<td>${position.positionId }</td>
				<td>${position.positionName }</td>
				<td>${position.delCheck }명</td>
				<td><button type="button" class="updateBtn"
				value="${position.positionId }">수정</button></td>
				<td><button type="button" class="deleteBtn"
				value="${position.positionId }"
				${position.delCheck==0 ? "" : "disabled=\"disabled\"" }>삭제</button></td>
			</tr>
			</c:forEach>
		</table>
	</div>
	
	<!-- 회사 소개 및 어플리케이션 소개 영역 -->
	<div id="footer">
		
	</div>
</div>
</body>
</html>