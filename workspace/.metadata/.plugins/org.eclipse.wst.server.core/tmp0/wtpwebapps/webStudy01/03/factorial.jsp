<%@page import="org.apache.catalina.connector.Request"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	int factorial(int operand){
		if(operand <=0) throw new IllegalArgumentException(operand + "는 연산 수행 불가");
		if(operand ==1) return operand;
		return operand * factorial(operand-1);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="">
		<%
			String num = request.getParameter("operand");
			int operand = 1;
			//if(num ==null && !num.matches("[1-9]")){
				//response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				//return;
			//}
			if(num != null){
				operand = Integer.parseInt(num);
			}
			int a=1;
			for(int i=operand;i>=1;i--){
				a = a*i;
			}
		%>
		<input type="number" name="operand" value="<%=operand %>"/>!
		<input type="submit" value="="/>
		<span>
			<%= a %>
			recursive call value : <%=factorial(operand)  %>
		</span>
	</form>
</body>
</html>