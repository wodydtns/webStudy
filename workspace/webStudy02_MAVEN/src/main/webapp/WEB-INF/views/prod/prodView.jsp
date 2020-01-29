<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
	<%
		ProdVO prod = (ProdVO) request.getAttribute("prod");
	%>
	<table class="table table-bordered">
		<tr>
			<th>상품아이디</th>
			<td><%=prod.getProd_id()%></td>
		</tr>
		<tr>
			<th>상품명</th>
			<td><%=prod.getProd_name()%></td>
		</tr>
		<tr>
			<th>상품분류명</th>
			<td><%=prod.getLprod_nm()%></td>
		</tr>
		<tr>
			<th>거래처정보</th>
			<td>
				<table class="table-bordered">
					<thead class="thead-dark">
						<tr>
							<th>거래처명</th>
							<th>담당자명</th>
							<th>담당자연락처</th>
							<th>소재지</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><%=prod.getBuyer().getBuyer_name() %></td>
							<td><%=prod.getBuyer().getBuyer_charger() %></td>
							<td><%=prod.getBuyer().getBuyer_comtel() %></td>
							<td><%=prod.getBuyer().getBuyer_add1() %></td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<th>구매가</th>
			<td><%=prod.getProd_cost()%></td>
		</tr>
		<tr>
			<th>판매가</th>
			<td><%=prod.getProd_price()%></td>
		</tr>
		<tr>
			<th>세일가</th>
			<td><%=prod.getProd_sale()%></td>
		</tr>
		<tr>
			<th>간략정보</th>
			<td><%=prod.getProd_outline()%></td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td><%=prod.getProd_detail()%></td>
		</tr>
		<tr>
			<th>상품이미지경로</th>
			<td><%=prod.getProd_img()%></td>
		</tr>
		<tr>
			<th>상품재고</th>
			<td><%=prod.getProd_totalstock()%></td>
		</tr>
		<tr>
			<th>상품입고일</th>
			<td><%=prod.getProd_insdate()%></td>
		</tr>
		<tr>
			<th>상품적정재고</th>
			<td><%=prod.getProd_properstock()%></td>
		</tr>
		<tr>
			<th>상품크기</th>
			<td><%=prod.getProd_size()%></td>
		</tr>
		<tr>
			<th>상품색상</th>
			<td><%=prod.getProd_color()%></td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td><%=prod.getProd_delivery()%></td>
		</tr>
		<tr>
			<th>상품단위</th>
			<td><%=prod.getProd_unit()%></td>
		</tr>
		<tr>
			<th>상품입고량</th>
			<td><%=prod.getProd_qtyin()%></td>
		</tr>
		<tr>
			<th>상품판매량</th>
			<td><%=prod.getProd_qtysale()%></td>
		</tr>
		<tr>
			<th>상품마일리지</th>
			<td><%=prod.getProd_mileage()%></td>
		</tr>
		<tr>
			<th>상품구매자목록</th>
			<td>
				<table class="table-bordered">
					<thead class="thead-dark">
						<tr>
							<th>구매자아이디</th>
							<th>이름</th>
							<th>휴대폰</th>
							<th>이메일</th>
							<th>거주지</th>
							<th>마일리지</th>
						</tr>
					</thead>
					<tbody>
					<%
						List<MemberVO> memberList = prod.getMemberList();
						if(memberList !=null && memberList.size()>0){
							for(MemberVO member:memberList){
								%>
								<tr>
									<td><%=member.getMem_id() %></td>
									<td><%=member.getMem_name() %></td>
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
								<td colspan="6">자료 없음</td>
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










