<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
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
		MemberVO member = (MemberVO) request.getAttribute("member");
	%>
	<table>
		<tr>
			<th>회원명</th>
			<td><%=member.getMem_id()%></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><%=member.getMem_pass()%></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><%=member.getMem_name()%></td>
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
			<td><%=member.getMem_zip()%></td>
		</tr>
		<tr>
			<th>주소1</th>
			<td><%=member.getMem_add1()%></td>
		</tr>
		<tr>
			<th>주소1</th>
			<td><%=member.getMem_add2()%></td>
		</tr>
		<tr>
			<th>집전번</th>
			<td><%=member.getMem_hometel()%></td>
		</tr>
		<tr>
			<th>사무실전번</th>
			<td><%=member.getMem_comtel()%></td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td><%=member.getMem_hp()%></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><%=member.getMem_mail()%></td>
		</tr>
		<tr>
			<th>직업</th>
			<td><%=member.getMem_job()%></td>
		</tr>
		<tr>
			<th>취미</th>
			<td><%=member.getMem_like()%></td>
		</tr>
		<tr>
			<th>기념일</th>
			<td><%=member.getMem_memorial()%></td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td><%=member.getMem_memorialday()%></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><%=member.getMem_mileage()%></td>
		</tr>
		<tr>
			<th>탈퇴여부</th>
			<td><%=member.getMem_delete()%></td>
		</tr>
		<tr>
			<th>구매기록</th>
			<td>
				<table>
					<thead>
						<tr>
							<th>상품명</th>
							<th>상품분류명</th>
							<th>거래처명</th>
							<th>구매가</th>
							<th>판매가</th>
							<th>마일리지</th>
						</tr>
					</thead>
					<tbody>
						<%
							List<ProdVO> prodList = member.getProdList();
							if(prodList!= null && prodList.size()>0){
								for(ProdVO prod:prodList){
									%>
									<tr>
									<td><%=prod.getProd_name() %></td>
									<td><%=prod.getLprod_nm() %></td>
									<td><%=prod.getBuyer_name() %></td>
									<td><%=prod.getProd_cost() %></td>
									<td><%=prod.getProd_price() %></td>
									<td><%=prod.getProd_mileage() %></td>
									</tr>
									<%
								}
							}else{
								%>
								<tr>
									<td colspan="6">
										구매기록 없음
									</td>
								</tr>
								<% 
							}
						%>
					</tbody>
				</table>
			</td>
			
		</tr>
	</table>
</body>
</html>