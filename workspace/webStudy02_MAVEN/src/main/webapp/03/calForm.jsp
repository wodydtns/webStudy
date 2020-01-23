<%@page import="kr.or.ddit.enumpkg.OperatorType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/calForm.jsp</title>
</head>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		let resultArea = $('#resultArea');
		$("#calForm").on("submit",function(event){
			// 동기 요청을 비동기 요청으로 바꿈(submit은 동기 요청)
			event.preventDefault();
			let action = $(this).attr("action");
			let queryString = $(this).serialize();
			let httpMethod = $(this).attr('method');
			
			console.log(queryString);
 			$.ajax({
 				url :action,
 				data:queryString,
 				method:httpMethod? httpMethod:"get" ,
 				//request header : Accept === response header : Content-Type(dataType)
 				dataType:"json",
 				success:function(resp){
 					console.log(resp);
 					resultArea.empty();
 					resultArea.append(resp.contents);
 					resultArea.append(resp.now);
 				},
 				error:function(xhr){
 					console.log(xhr.status);
 				}
 			});
			return false;
		});
	});
</script>
<body>

<form id="calForm" action="<%=request.getContextPath() %>/calculator.do">
	<input type="number" name="leftOp" />
	<select name="operator">
		<%
			OperatorType[] operatorTypes = OperatorType.values();
			String ptrn = "<option value='%s'>%s</option>";
			for(OperatorType temp : operatorTypes){
				out.println(
					String.format(ptrn, temp.name(), temp.getSign())		
				);
			}
		%>
	</select>
	<input type="number" name="rightOp" />
	<button type="submit">=</button>
</form>
<div id="resultArea">

</div>
</body>
</html>










