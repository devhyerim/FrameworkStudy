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
<title>MemberList.jsp</title>
<!-- 부트스트랩 css -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 제이쿼리 script -->
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<!-- 부트스트랩 script -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript">
	
	// 삭제: form이 아닌 get방식으로 주소 요청
	$(function()
	{
		$(".btnDelete").click(function()
		{
			if(confirm("현재 선택한 데이터를 정말 삭제하시겠습니까?"))
			{
				$(location).attr("href", "memberdelete.action?mid="
                					+  $(this).val());
			}
		});
		
		// 수정 버튼을 클릭하는 경우
		$(".btnUpdate").click(function()
		{
			// 타이틀을 회원 정보 입력에서 회원 정보 수정으로 변경
			$("#title").html("회원 정보 수정").css({"color":"red", "font-weight":"bold"});
			
			// $(this)=btnUpdate 버튼의 tr 태그의 전체에서
			// td를 찾는다. 여러 개의 td 중 0번째, 1번째, 2번째..
			// 그리고 해당 td 안에 들어가 있는 text를 가져온다.
			var mid = $(this).parents("tr").find("td:eq(0)").text();	//-- DB에서 가져온 member의 mid
			var name = $(this).parents("tr").find("td:eq(1)").text();	//-- name
			var telephone = $(this).parents("tr").find("td:eq(2)").text();	//-- telephone
			
			// form (기존 등록 폼) 에 등록되어 있는 건 name, telephone밖에 없다.
			// (Insert 할 때는 사용자가 mid를 입력하지 않기 때문에 폼이 없다)
			// 수정할 때는 mid 값도 컨트롤러에게 넘겨주어야 한다.
			//-->> 따라서 hidden 속성으로 form 안에 mid 아이디를 가지는 input 엘리먼트 추가~~!!
			
			// 위에서 가져온 변수를 value 로 넣어준다. (매개변수로 넘기면 됨)
			$("#mid").val(mid);
			$("#name").val(name);
			$("#telephone").val(telephone);
			
			// form 엘리먼트를 잡아서(아이디 부여 안하고 그냥 form으로 부른다)
			// memberupdate.action을 수행하도록 한다.
			// 원래는 memberinsert.action 이었는데 변경하는 작업!!
			$("form").attr("action", "memberupdate.action");
		});
		
		// submit 옆에 cancel 버튼을 누르는 경우
		//-- 사용자는 보통 수정 버튼을 눌렀다가 수정을 취소하기 위해 이 버튼을 누를 것
		// 그런데 아래의 작업이 없다면 한 번 수정 버튼을 눌렀기 때문에
		// 빈칸은 되어도 update를 요청하게 되어
		// insert를 하더라도 updqte로 잘못 요청하게 된다.
		$(".btnCancel").click(function()
		{
			$("#title").html("회원 정보 입력").css({"color":"black", "font-weight":"normal"});
			$("form").attr("action", "memberinsert.action");
		});
		
	});

</script>
</head>
<body>
<div>
	<h1>회원 정보</h1>
	<hr>
</div>

<div class="container">
	<div class="panel-group">
		<div class="pane-default">
			<div class="panel-heading" id="title">
				회원 정보 입력
			</div>
			
			<div class="panel-body">
				<form action="memberinsert.action" role="form" method="post">
				
					<!-- 수정 시 필요한 mid 숨겨진 엘리먼트로 추가 -->
					<input type="hidden" id="mid" name="mid">
					
					<div class="form-group">
						<label for="name">
							NAME :
						</label>
						<input type="text" class="form-control" id="name" name="name" />
					</div>
					
					<div class="form-group">
						<label for="telephone">
						 	TELEPHONE :
						</label>
						<input type="tel" class="form-control" id="telephone" name="telephone"/>
					</div>
					
					<button type="submit" class="btn btn-success btn-sm">SUBMIT</button>
					<button type="reset" class="btn btn-success btn-sm btnCancel">CANCEL</button>
				</form>
			</div>
		</div><!-- close .panel-default -->
		
		<div class="panel panel-default">
			<div class="panel-heading">
				회원 정보 출력
			</div>
			
			<div class="panle-body">
				<table class="table">
					<thead>
						<tr>
							<th>MID</th>
							<th>NAME</th>
							<th>TELEPHONE</th>
							<th>삭제 / 수정</th>
						</tr>
					</thead>
					<tbody>
						 <c:forEach var="member" items="${list }">
						 <tr>
						 	<td>${member.mid }</td>
						 	<td>${member.name }</td>
						 	<td>${member.telephone }</td>
						 	<td>
								<button type="button" class="btn btn-danger btn-xs btnDelete"
								value="${member.mid }">삭제</button>
								<button type="button" class="btn btn-primary btn-xs btnUpdate"
								value="${member.mid }">수정</button>
							</td>
						 </tr>
						 </c:forEach>
						 
					</tbody>
				</table>
				
				<button type="button" class="btn btn-default btn-sm">
					COUNT <span class="badge">${count }</span>
				</button>
			</div>
		</div>
		
	</div>
</div>
</body>
</html>