<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.vo.PagingVO"%>
<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@page import="java.util.List"%>
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
	<form id="searchForm">
		<input type="text" name="page" />
		<input type="text" name="prod_lgu" value="${param.prod_lgu }"/>
		<input type="text" name="prod_buyer" value="${param.prod_buyer }"/>
		<input type="text" name="prod_name" value="${param.prod_name }" />
	</form>
	<div id="inputForm">
	<select name="prod_lgu">
		<option value="">상품분류</option>
		<%
			List<Map<String, Object>> lprodList = (List) request.getAttribute("lprodList");
			for (Map<String, Object> lprodMap : lprodList) {
		%>
		<option value="<%=lprodMap.get("lprod_gu")%>"><%=lprodMap.get("lprod_nm")%></option>
		<%
			}
		%>
	</select>
	<select name="prod_buyer">
		<option value="">거래처</option>
		<%
			List<BuyerVO> buyerList = (List) request.getAttribute("buyerList");
			for (BuyerVO buyer : buyerList) {
		%>
		<option value="<%=buyer.getBuyer_id()%>"><%=buyer.getBuyer_name()%></option>
		<%
			}
		%>

	</select> 상품명 :
	<input type="text" name="prod_name" value="${param.prod_name}" />
	<input type="submit" value="검색" id="searchBtn"/>
</div>
	<table class="table">
		<thead>
			<tr>
				<th>번호</th>
				<th>상품코드</th>
				<th>상품명</th>
				<th>입고일</th>
				<th>구매가</th>
				<th>판매가</th>
				<th>마일리지</th>
				<th>거래처명</th>
				<th>상품분류명</th>
			</tr>
		</thead>
		<tbody id="listBody">
			
		</tbody>
		<tfoot>
			<tr>
				<td colspan="8">
					<div id="pagingArea"></div>
				</td>
			</tr>
		</tfoot>
	</table>
	<script type="text/javascript">
		let prod_buyerTag = $("[name='prod_buyer']");
		let prod_lguTag = $("[name='prod_lgu']");
		let listBody = $("#listBody");
		let pagingArea =$("#pagingArea");
		let searchForm = $("#searchForm").on("submit",function(event){
			event.preventDefault();
			let action = $(this).attr("action");
			let method = $(this).attr("method");
			if(!action) action = "<%=request.getContextPath()%>/prod/prodList.do";
			if(!method) method = "get"; 
			let queryString = $(this).serialize();
			$.ajax({
				url : action,
				method : method,
				data : queryString,
				dataType : "json",
				success : function(resp) {
					let dataList = resp.dataList;
					let trTags = [];
					if(dataList.length>0){
						$(dataList).each(function(idx,prod){
							let eachTr = $("<tr>");
							eachTr.append(
								$("<td>").text(prod.Rn),
								$("<td>").text(prod.prod_id),
								$("<td>").html(
										$("<a>").text(prod.prod_name).attr({
											href : "<%=request.getContextPath()%>/prod/prodView.do?what="+prod.prod_id		
										})
								),
								$("<td>").text(prod.prod_insdate),
								$("<td>").text(prod.prod_cost),
								$("<td>").text(prod.prod_price),
								$("<td>").text(prod.prod_mileage),
								$("<td>").text(prod.Buyer_name),
								$("<td>").text(prod.Lprod_nm)
							);
							trTags.push(eachTr);
						});
					}else{
						trTags.push(
						$("<tr>").html(
							$("<td>").attr({
								colspan:"8"
							}).text("조건에 맞는 상품이 없음.")
						)
						);
					}
					listBody.empty();
					listBody.append(trTags);
					let pagingHTML = resp.pagingHTML;
					pagingArea.empty();
					pagingArea.html(pagingHTML);
					
				},
				error : function(xhr) {
					console.log(xhr.status);
				}
			});
			return false;
			
		});
		let inputForm = $("#inputForm");
		let searchBtn = $("#searchBtn").on("click",function(){
			let inputTags = inputForm.find(":input[name]");
			$(inputTags).each(function(index,element){
				let name = $(this).attr("name");
				let value = $(this).val();
				let searchFormInput = searchForm.find("input[name='"+name+"']");
				searchFormInput.val(value);
			});
		searchForm.find("[name='page']").val(1);
		searchForm.submit();
		});
		prod_buyerTag.val("${param.prod_buyer}");
		prod_lguTag.val("${param.prod_lgu}");
		$("#pagingArea").on("click", "a", function(event) {
			event.preventDefault();
			let page = $(this).data("page");
			searchForm.find("[name='page']").val(page);
			searchForm.submit();
			return false;
		});
		searchForm.submit();
	</script>
</body>
</html>

<!-- 상품명, 거래처명, 분류명 -->






