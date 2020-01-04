<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
td, th {
	border: 1px solid black;
}

table {
	border-collapse: collapse;
}
</style>
</head>
<body>
	<% 
		String minDanStr = request.getParameter("minDan");
		String maxDanStr = request.getParameter("maxDan");
		int minDan =2;
		int maxDan =9;
		if(minDanStr !=null && minDanStr.matches("\\d")){
			minDan = Integer.parseInt(minDanStr);
		}
		if(maxDanStr !=null && maxDanStr.matches("\\d")){
			maxDan = Integer.parseInt(maxDanStr);
		}
	%>
	
	<table>
		<thead>
			<tr>
				<%
					for(int dan=minDan;dan<=maxDan;dan++){
					%>
				<th><%=dan %>단</th>
				<%
						}
					%>
			</tr>
		</thead>
		<tbody>
			<%
				for(int mul=1;mul<=9;mul++){
			%>
			<%!
				String ptrn = "%d*%d=%d";
				String operate(int dan, int mul){
					String res = String.format(ptrn,dan,mul,(dan*mul));
					return res;
			}
				
			%>
			<tr>
				<%
				for(int dan=minDan;dan<=maxDan;dan++){
			%>
				<td><%=operate(dan,mul) %></td>
				<%
				}
			%>
			</tr>
			<%
				}
			%>

		</tbody>
	</table>
</body>
</html>