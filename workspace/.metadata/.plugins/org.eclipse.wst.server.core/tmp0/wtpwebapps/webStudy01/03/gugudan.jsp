<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<form action="<%=request.getContextPath() %>/03/gugudanProcess.jsp" method="post">
			최소단 : <input name="minDan" type="number" min="2" max="9">
			최대단 : 
			<select name="maxDan">
				<option value="">단 선택</option>
				<%
					for(int dan=2;dan<=9;dan++){
						%>
						<option value="<%=dan %>"><%=dan %>단</option>
						<%
					}
				%>
			</select>
			<input type="submit" value="구구단">
		</form>
	</body>
</html>