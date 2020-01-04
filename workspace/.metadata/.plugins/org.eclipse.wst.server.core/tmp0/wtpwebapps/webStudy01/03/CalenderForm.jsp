<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Calendar cal = Calendar.getInstance();
	
	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH);
	int day = cal.get(Calendar.DATE);
	
	Calendar daySet = Calendar.getInstance();
	daySet.set(year, month, 1);
	int weekofDay = daySet.get(Calendar.DAY_OF_WEEK); // 첫번째 요일
	int lastDay = daySet.getActualMaximum(Calendar.DATE); // 마지막 날
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<script  src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<body>
<form action="">
	<select name="year_select">
		<option value="">연도 입력</option>
		<%
			for(int i=2010;i<=year;i++){%>
				<option value="<%=i %>"><%=i %></option>
				<%
			}
		 %>
		
	</select>
	<select name="month_select">
		<option value="">월 입력</option>
		<%
			for(int i=1;i<=12;i++){%>
				<option value="<%=i %>"><%=i %></option>
				<%
			}
		 %>
	</select>
		<input type="submit" value="전송"/>
	</form>
	<table border="1">
		<tr>
			<%
				String[] a = {"sun","mon","tues","weds","thurs","fri","sat"};
				for(int i=0;i<a.length;i++){
					%>
					<td><%=a[i] %></td>
					<%
				}
			%>
		</tr>
		<tr>
			<%for (int k = 1; k < weekofDay; k++) {%>

			<td></td>

			<%}%>

			<%for (int j = 1; j <= lastDay; j++) {%>

			<td><%=j%>

				<%if ((weekofDay+j-1) % 7 == 0) {%>

				</td></tr><tr>

			<%}}for(int e=1;e<(7-weekofDay-1);e++){%>

			<td></td>

			<%} %>
		</tr>
	</table>
</body>
</html>