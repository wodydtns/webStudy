<%@page import="kr.or.ddit.vo.PagingVO"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>

</head>
<form>
	<input type="text" name="page">
	<select name="buyer_bank">
		<option value="">은행별</option>
		<%
			List<Map<String,Object>> BuyerName =(List)request.getAttribute("BuyerName");
			for(Map<String,Object> nameMap:BuyerName){
				%>
				<option value=""><%=nameMap.get("buyer_name") %></option>
				<%
			}
		%>
	</select>
	<select name="buyer_name">
		<option value="">구매자별</option>
		<%
			List<Map<String,Object>> BuyerBank =(List)request.getAttribute("BuyerBank");
			for(Map<String,Object> bankMap:BuyerBank){
				%>
				<option value=""><%=bankMap.get("buyer_bank") %></option>
				<%
			}
		%>
	</select>
	상품명 : <input type="text" name="prod_name"/>
	<input type="submit" value="검색"/>
</form>
<body>
<%
	PagingVO<BuyerVO> pagingVO = (PagingVO) request.getAttribute("pagingVO");
	List<BuyerVO> buyerList = pagingVO.getDataList();
%>
	<table class="table">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>제품번호</th>
				<th>은행</th>
				<th>계좌번호</th>
				<th>회사명</th>
				<th>우편번호</th>
				<th>주소1</th>
				<th>주소2</th>
				<th>전화번호</th>
				<th>팩스번호</th>
				<th>메일</th>
				<th>계좌주</th>
				<th>특이사항</th>
				<th>구매물품</th>
			</tr>
		</thead>
		<tbody>

			<%
				if(buyerList.size()>0){
									
					for(BuyerVO tmp:buyerList){
						%>
			<tr>
				<td><%=tmp.getBuyer_id() %></td>
				<td><%=tmp.getBuyer_name() %></td>
				<td><%=tmp.getBuyer_lgu() %></td>
				<td><%=tmp.getBuyer_bank() %></td>
				<td><%=tmp.getBuyer_bankno() %></td>
				<td><%=tmp.getBuyer_bankname() %></td>
				<td><%=tmp.getBuyer_zip() %></td>
				<td><%=tmp.getBuyer_add1() %></td>
				<td><%=tmp.getBuyer_add2() %></td>
				<td><%=tmp.getBuyer_comtel() %></td>
				<td><%=tmp.getBuyer_fax() %></td>
				<td><%=tmp.getBuyer_mail() %></td>
				<td><%=tmp.getBuyer_charger() %></td>
				<td><%=tmp.getBuyer_telext() %></td>
				<td><%=tmp.getProd_name() %></td>
			</tr>
			<%
					}
				%>
			<%
				}else{
					%>
			<tr>
				<td colspan="13">정보 없음</td>
			</tr>
			<%

				}
				 %>

		</tbody>
		<tfoot>
		<tr>
			<td colspan="13">
			<div>
				<%=pagingVO.getPagingHTML() %>
			</div>
				
			</td>
			</tr>
		</tfoot>
	
	</table>
</body>
</html>