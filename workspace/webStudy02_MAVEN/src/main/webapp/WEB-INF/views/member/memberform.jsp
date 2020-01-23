<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%
	String message = (String) request.getAttribute("message");
	if(StringUtils.isBlank(message)){
		message = (String) session.getAttribute("message");
		session.removeAttribute("message"); // flash attribute
	}
	if(StringUtils.isNotBlank(message)){
		out.println("<div class='error'>"+message+"</div>");
	}
%>
<jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request" />
<jsp:useBean id="errors" class="java.util.HashMap" scope="request" />
<form method="post" id="insertForm" >
	<table class="table table-bordered">
		<tr>
			<th>회원아이디</th>
			<td><input class="form-control" type="text" required
				name="mem_id" value="<%=member.getMem_id()%>" />
				<span class="error"><%=errors.get("mem_id")%></span>
				<button id="idCheckBtn" type="button">아이디 중복 검사</button>
				</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input class="form-control" type="text" required
				name="mem_pass" />
				<span class="error"><%=errors.get("mem_pass")%></span></td>
		</tr>
		<tr>
			<th>회원명</th>
			<td><input class="form-control" type="text" required
				name="mem_name" value="<%=member.getMem_name()%>" />
				<span class="error"><%=errors.get("mem_name")%></span></td>
		</tr>
		<tr>
			<th>주민번호1</th>
			<td><input class="form-control" type="text" name="mem_regno1"
				value="<%=member.getMem_regno1()%>" />
				<span class="error"><%=errors.get("mem_regno1")%></span></td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td><input class="form-control" type="text" name="mem_regno2"
				value="<%=member.getMem_regno2()%>" />
				<span class="error"><%=errors.get("mem_regno2")%></span></td>
		</tr>
		<tr>
			<th>생일</th>
			<td><input class="form-control" type="text" name="mem_bir"
				value="<%=member.getMem_bir()%>" />
				<span class="error"><%=errors.get("mem_bir")%></span></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><input class="form-control" type="text" required
				name="mem_zip" value="<%=member.getMem_zip()%>" />
				<span class="error"><%=errors.get("mem_zip")%></span></td>
		</tr>
		<tr>
			<th>주소1</th>
			<td><input class="form-control" type="text" required
				name="mem_add1" value="<%=member.getMem_add1()%>" />
				<span class="error"><%=errors.get("mem_add1")%></span></td>
		</tr>
		<tr>
			<th>주소2</th>
			<td><input class="form-control" type="text" required
				name="mem_add2" value="<%=member.getMem_add2()%>" />
				<span class="error"><%=errors.get("mem_add2")%></span></td>
		</tr>
		<tr>
			<th>집전번</th>
			<td><input class="form-control" type="text" name="mem_hometel"
				value="<%=member.getMem_hometel()%>" />
				<span class="error"><%=errors.get("mem_hometel")%></span></td>
		</tr>
		<tr>
			<th>사무실전번</th>
			<td><input class="form-control" type="text" name="mem_comtel"
				value="<%=member.getMem_comtel()%>" />
				<span class="error"><%=errors.get("mem_comtel")%></span></td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td><input class="form-control" type="text" name="mem_hp"
				value="<%=member.getMem_hp()%>" />
				<span class="error"><%=errors.get("mem_hp")%></span></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input class="form-control" type="text" required
				name="mem_mail" value="<%=member.getMem_mail()%>" />
				<span class="error"><%=errors.get("mem_mail")%></span></td>
		</tr>
		<tr>
			<th>직업</th>
			<td><input class="form-control" type="text" name="mem_job"
				value="<%=member.getMem_job()%>" />
				<span class="error"><%=errors.get("mem_job")%></span></td>
		</tr>
		<tr>
			<th>취미</th>
			<td><input class="form-control" type="text" name="mem_like"
				value="<%=member.getMem_like()%>" />
				<span class="error"><%=errors.get("mem_like")%></span></td>
		</tr>
		<tr>
			<th>기념일</th>
			<td><input class="form-control" type="text" name="mem_memorial"
				value="<%=member.getMem_memorial()%>" />
				<span class="error"><%=errors.get("mem_memorial")%></span></td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td><input class="form-control" type="text"
				name="mem_memorialday" value="<%=member.getMem_memorialday()%>" />
				<span class="error"><%=errors.get("mem_memorialday") %></span></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>3000</td>
		</tr>
		<tr>
			<td colspan="2">
				<input class="btn btn-success" type="submit" value="저장">
				<input class="btn btn-warning" type="reset" value="취소">				
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
	let insertForm = $("#insertForm").on('submit',function(){
		
		let idChecked = $(this).data("idchecked");
		if(!idChecked) {
			alert("중복 검사하세요");
			return false;
		}else{
			return true;
			
		}
	});
	let idInputTag = $("[name='mem_id']").on('change',function(){
		insertForm.data("idchecked",false);
	});
	$("#idCheckBtn").on("click",function(event){
		
		let inputId = idInputTag.val();
		if(!inputId){
			alert("중복 검사해야함");
			idInputTag.focus();
			return;
		}
		$.ajax({
			url : "<%=request.getContextPath()%>/member/checkId.do",
			data : {
				id:inputId
			},
			method : "post",
			dataType : "json",
			success : function(resp) {
				if(resp.status=="PKDUPLICATED"){
					idInputTag.val("");
					idInputTag.focus();
				}else{
					insertForm.data("idchecked",true);
				}
				idInputTag.after($("<span>").addClass("message").text(resp.message));
			},
			error : function(xhr) {
				console.log(xhr.status);
			}
		});
	});
</script>
</body>
</html>