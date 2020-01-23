<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


</head>
<%
	List<ProdVO> list = (List)request.getAttribute("prodList");
%>
<body>
	상품코드 상품명 입고일 구매가 판매가 마일리지,거래처명, 상품분류명 lprod_gu(lprod), prod_name,
	prod_insdate , prod_cost,prod_price, prod_mileage,(prod)
	buyer_name(buyer), lprod_name(lprod)
	<div class="container">
	<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col">상품코드</th>
				<th scope="col">상품명</th>
				<th scope="col">입고일</th>
				<th scope="col">구매가</th>
				<th scope="col">판매가</th>
				<th scope="col">마일리지</th>
				<th scope="col">거래처명</th>
				<th scope="col">상품분류명</th>
			</tr>
			
		</thead>
		<tbody>
			
			<%
				for(ProdVO tmp:list){
					%>
					<tr>
					<td scope="row"><%=tmp.getLprod_gu() %></td>
					<td><%=tmp.getProd_name() %></td>
					<td><%=tmp.getProd_insdate() %></td>
					<td><%=tmp.getProd_cost() %></td>
					<td><%=tmp.getProd_price() %></td>
					<td><%=tmp.getProd_mileage() %></td>
					<td><%=tmp.getProd_buyer() %></td>
					<td><%=tmp.getLprod_nm() %></td>
					</tr>	
					<%
				}
			%>
				
			
				
			
		</tbody>
	</table>
	</div>
</body>
</html>