<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type='text/css'>
	td,th{
		border:1px solid black;
	}
	table{
		border-collapse:collapse;
	}
</style>
</head>
<body>
<%
	String minDanStr = request.getParameter("minDan");
	String maxDanStr = request.getParameter("maxDan");
	
	int minDan = 2;
	int maxDan = 9;
	if(minDanStr!=null && minDanStr.matches("\\d")){
		minDan = Integer.parseInt(minDanStr);
	}
	if(maxDanStr!=null && maxDanStr.matches("\\d")){
		maxDan = Integer.parseInt(maxDanStr);
	}
%>
<table>
	<thead>
		<tr>
			<%
				for(int dan=minDan; dan<=maxDan; dan++){
			%>		
				<th><%=dan %>단</th>
			<%		
				}
			%>
		</tr>
	</thead>
	<tbody>
		<%!
			public String ptrn = "%d*%d=%d";
			public String operate(int dan, int mul){
				return String.format(ptrn, dan, mul, (dan*mul));
			}
		%>
		<%
			for(int mul=1; mul<=9; mul++){
		%>
		<tr>
		<%		
				for(int dan=minDan; dan<=maxDan; dan++){
		%>			
			<td><%=operate(dan, mul) %></td>
		<%			
				}
		%>
		</tr>
		<%				
			}
		%>
	</tbody>
</table>
<body>
</html>












