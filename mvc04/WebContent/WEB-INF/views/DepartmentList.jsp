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
<title>EmployeeList.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/main.css" />
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">

	$(function()
	{
		$(".updateBtn").click(function()
		{
			$(location).attr("href", "departmentupdateform.action?departmentId=" + $(this).val());
		});
		
		$(".deleteBtn").click(function()
		{
			if(confirm("현재 선택한 데이터를 정말 삭제하시겠습니까?"))
			{
				$(location).attr("href", "departmentdelete.action?departmentId=" +  $(this).val());
			}
		});
	});

</script>

</head>
<body>
<!------------------------------------------------
   DepartmentList.jsp
   → 부서 리스트 출력 페이지 (관리자)
--------------------------------------------------->

<div>
	<!-- 메뉴 영역 -->
	<div>
		<c:import url="EmployeeMenu.jsp"></c:import>
	</div>
	
	<!-- 콘텐츠 영역 -->
	<div id="content">
		
		<h1>[ 부서 관리 (관리자 전용) ]</h1>
		<hr>
		
		<div>
			<form action="">
				<input type="button" value="부서 추가" class="btn"
				onclick="location.href='departmentinsertform.action'">
			</form>
		</div>
		<br><br>
		
		<!-- 
		DEPARTMENTID, DEPARTMENTNAME, DELCHECK(부서 인원수)
		-->
		<table id="departments" class="table">
			<tr>
				<th>부서아이디</th>
				<th>부서명</th>
				<th>부서 인원 수</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
			<c:forEach var="dm" items="${departmentList }">
			<tr>
				<td>${dm.departmentId }</td>
				<td>${dm.departmentName }</td>
				<td>${dm.delCheck }명</td>
				<td><button type="button" class="updateBtn"
				value="${dm.departmentId }">수정</button></td>
				<td><button type="button" class="deleteBtn"
				value="${dm.departmentId }"
				${dm.delCheck==0 ? "" : "disabled=\"disabled\"" }>삭제</button></td>
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