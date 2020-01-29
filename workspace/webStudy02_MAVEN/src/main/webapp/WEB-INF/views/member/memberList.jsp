<%@page import="kr.or.ddit.vo.PagingVO"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
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
<%
	PagingVO<MemberVO> pagingVO = (PagingVO) request.getAttribute("pagingVO");
	List<MemberVO> memberList = pagingVO.getDataList();
%>
<table class="table table-bordered">
	<thead>
		<tr>
			<th>행번호</th>
			<th>회원아이디</th>
			<th>회원명</th>
			<th>휴대폰</th>
			<th>이메일</th>
			<th>거주지역</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody id="listBody">
		
	</tbody>
	<tfoot>
		<tr>
			<td colspan="7">
				<div class="form-inline mb-3 justify-content-center">
					<select id="searchType" class="form-control mr-2">
						<option value="">전체</option>
						<option value="id">아이디</option>
						<option value="name">이름</option>
						<option value="address">거주지</option>
					</select>
					<input type="text" id="searchWord"  class="form-control mr-2"
						value="${param.searchWord }"
					/>
					<input type="button" value="검색" id="searchBtn" class="btn btn-info"/>
				</div>
				<div id="pagingArea">
				
				</div>
			</td>
		</tr>
	</tfoot>
</table>

<form id="searchForm">
	<input type="text" name="page" />
	<input type="text" name="searchType" value="${param.searchType }"/>
	<input type="text" name="searchWord" value="${param.searchWord }"/>
</form>

<script type="text/javascript">
	var searchTypeTag = $("#searchType");
	searchTypeTag.val("${param.searchType }");
	var searchWordTag = $("#searchWord");
	var listBody = $("#listBody");
	var pagingArea = $("#pagingArea");
	var searchForm = $("#searchForm").on("submit", function(event){
		event.preventDefault();
		let queryString = $(this).serialize();
		let action = $(this).attr("action");
		let method = $(this).attr("method");
		if(!action) action = "<%=request.getContextPath()%>/member/memberList.do";
		if(!method) method = "get";
		$.ajax({
			url:action,
			method:method,
			data : queryString,
			dataType : "json", //Accept,  Content-Type
			success : function(resp) {
// 				console.log(resp);
				let dataList = resp.dataList;
				let trTags = [];
				if(dataList.length>0){
					$(dataList).each(function(index, member){
						let eachTr = $("<tr>");
						eachTr.append(
							$("<td>").text(member.rnum),	
							$("<td>").text(member.mem_id),	
							$("<td>").html(
								$("<a>").attr({
											href:"<%=request.getContextPath()%>/member/memberView.do?who="+member.mem_id
										})
										.text(member.mem_name)
							),	
							$("<td>").text(member.mem_hp),	
							$("<td>").text(member.mem_mail),	
							$("<td>").text(member.mem_add1),	
							$("<td>").text(member.mem_mileage),	
						);
						trTags.push(eachTr);
					});
				}else{
					trTags.push(
						$("<tr>").html(
							$("<td>").attr({
								colspan:"7"
							}).text("회원 목록이 없음.")		
						)
					);
				}
				listBody.empty();
				listBody.append(trTags);
				let pagingHTML = resp.pagingHTML;
				pagingArea.empty(); //**
				pagingArea.html(pagingHTML);
			},
			error : function(xhr) {
				console.log(xhr.status);
			}
		});
		return false;
	});
	var searchBtn = $("#searchBtn").on("click", function(){
		let searchType = searchTypeTag.val();
		let searchWord = searchWordTag.val();
		searchForm.find("[name='searchType']").val(searchType); 
		searchForm.find("[name='searchWord']").val(searchWord); 
		searchForm.find("[name='page']").val(1);
		searchForm.submit();
	});
	pagingArea.on("click", "a", function(event){
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



















