<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	List<MemberVO> memberList =(List) request.getAttribute("memberList");
%>
	<table>
		<thead>
			<tr>
				<th>회원아이디</th>
				<th>회원명</th>
				<th>휴대폰</th>
				<th>이메일</th>
				<th>거주지역</th>
				<th>마일리지</th>
			</tr>
		</thead>
		<tbody>
			<%
				if(memberList.size()>0){
					for(MemberVO member:memberList){
						%>
						<tr>
							<td><%=member.getMem_id() %></td>
							<td><a href="<%=request.getContextPath() %>/member/memberView.do?who=<%= member.getMem_id()%>"><%=member.getMem_name() %></a></td>
							<td><%=member.getMem_hp() %></td>
							<td><%=member.getMem_mail() %></td>
							<td><%=member.getMem_add1() %></td>
							<td><%=member.getMem_mileage() %></td>
						</tr>
							
						<%
					}
				}else{
					%>
					<tr>
						<td colspan="6">회원 목록 없음</td>
					</tr>
					<%
				}
			%>
		</tbody>
	</table>
</body>
</html>