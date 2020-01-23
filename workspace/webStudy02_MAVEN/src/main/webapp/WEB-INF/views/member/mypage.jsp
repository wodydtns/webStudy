<%@page import="java.util.Objects"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" >
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<%
		MemberVO member = (MemberVO) request.getAttribute("member");
// 		Map<String,String> errors = (Map)request.getAttribute("errors");
// 		if(errors==null){
// 			errors =new HashMap<>();
// 		}

		String message = (String)request.getAttribute("message");
		if(StringUtils.isBlank(message)){
			message = (String) session.getAttribute("message");
			session.removeAttribute("message"); // flash attribute
		}
		if(StringUtils.isNotBlank(message)){
			out.println("<div class='error'>"+message+"</div>");
		}
	%>
<!-- 	리퀘스트 스코프 확인 -> error라는 것 찾는 액션 태그 -->
	<jsp:useBean id="errors" class="java.util.HashMap" scope="request"></jsp:useBean>
	<%= errors %>
<script type="text/javascript">
	
</script>

<form id="updateForm" method="post" action="<%=request.getContextPath()%>/member/memberUpdate.do">
	<table class="table table-bordered">
		<tr>
			<th>회원아이디</th>
			<td>
			<%=member.getMem_id()%>
			<input class="form-control"  type="hidden" name="mem_id" required
				value="<%=member.getMem_id()%>" /></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input class="form-control" type="text" name="mem_pass" required/>
				<span class="error"><%=errors.get("mem_pass") %></span>
			</td>
				
		</tr>
		<tr>
			<th>이름</th>
			<td><input class="form-control" type="text" name="mem_name"
				value="<%=member.getMem_name()%>" required/>
				<span class="error"><%=errors.get("mem_name") %></span>
				</td>
				
		</tr>
		<tr>
			<th>주민번호1</th>
			<td><%=member.getMem_regno1()%></td>
				
		</tr>
		<tr>
			<th>주민번호2</th>
			<td><%=member.getMem_regno2()%></td>
				
		</tr>
		<tr>
			<th>생일</th>
			<td><%=member.getMem_bir()%></td>
				
		</tr>
		<tr>
			<th>우편번호</th>
			<td><input class="form-control" type="text" name="mem_zip"
				value="<%=member.getMem_zip()%>" required/>
					<span class="error"><%=Objects.toString(errors.get("mem_zip"),"") %></span>
				</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td><input class="form-control" type="text" name="mem_add1"
				value="<%=member.getMem_add1()%>" required />
				<span class="error">${errors.mem_add1}</span>
				</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td><input class="form-control" type="text" name="mem_add2"
				value="<%=member.getMem_add2()%>" required/>
				<span class="error">${errors.mem_add2}</span></td>
		</tr>
		<tr>
			<th>집전번</th>
			<td><input class="form-control" type="text" name="mem_hometel"
				value="<%=member.getMem_hometel()%>" /></td>
		</tr>
		<tr>
			<th>사무실전번</th>
			<td><input class="form-control" type="text" name="mem_comtel"
				value="<%=member.getMem_comtel()%>" /></td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td><input class="form-control" type="text" name="mem_hp"
				value="<%=member.getMem_hp()%>" /></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input class="form-control" type="text" name="mem_mail"
				value="<%=member.getMem_mail()%>" /></td>
		</tr>
		<tr>
			<th>직업</th>
			<td><input class="form-control" type="text" name="mem_job"
				value="<%=member.getMem_job()%>" /></td>
		</tr>
		<tr>
			<th>취미</th>
			<td><input class="form-control" type="text" name="mem_like"
				value="<%=member.getMem_like()%>" /></td>
		</tr>
		<tr>
			<th>기념일</th>
			<td><input class="form-control" type="text" name="mem_memorial"
				value="<%=member.getMem_memorial()%>" /></td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td><input class="form-control" type="text" name="mem_memorialday"
				value="<%=member.getMem_memorialday() %>"
				placeholder="1999-09-09"
				pattern="[0-9]{4}-\d{2}-\d{2}" />
				<span class="error">${errors.mem_memorialday}</span>
				</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><%=member.getMem_mileage() %></td>
		</tr>
		<tr>
			<th>탈퇴여부</th>
			<td><%=member.getMem_delete() %></td>
		</tr>
		<tr>
			<td colspan="2">
				<input class="btn btn-success" type="submit" value="저장"/>
				<input class="btn btn-warning" type="reset" value="취소"/>
				<input class="btn btn-info" type="button" id="delete" value="탈퇴" id="delBtn"/>
			</td>
		</tr>
	</table>
	</form>
	<form id="deleteForm" action="${pageContext.request.contextPath}/member/memberDelete.do" method="post">
		<input class="form-control" type="hidden" name="mem_pass"/>
	</form>
	<script>
		let deleteForm = $("#deleteForm");
		let inputPassTag =$('#updateForm input:[name='mem_pass']');
		let delPassTag = deleteForm.find('[name='mem_pass']');
		$("#delBtn").on('click',function(){
			let inputPass = inputPassTag.val();
// 			패스워드가 입력이 됐으면
			if(inputPass){
				delPassTag.val(inputPass);
				let result = confirm("탈퇴 할래?");
				if(result){
					deleteForm.submit();	
				}
				inputPassTag.val("");
				delPassTag.val("");
			}else{
				alert("비번 입력하세요");
				inputPassTag.focus();
			}
			
		});
	</script>
</body>
</html>