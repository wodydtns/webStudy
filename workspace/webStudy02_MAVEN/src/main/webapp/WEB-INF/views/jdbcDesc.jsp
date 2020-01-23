<%@page import="java.util.Map"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.ddit.vo.DataBasePropertyVO"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/jdbcDesc.jsp</title>
<style type="text/css">
	table, th, td{
		border: 1px solid black;
	}
	
	table{
		border-collapse: collapse;
	}
</style>
</head>
<body>
<h4>JDBC(Java DataBase Connectivity)</h4>

<pre>
	1. 벤더가 제공하는 드라이버를 빌드패스에 추가(ojdbc6.jar)
	2. driver class loading
	3. Connection 생성
	4. Query 객체
		1) Statement(기본)
		2) PreparedStatement(미리 컴파일된 쿼리 객체)
		3) CallableStatement(프로시저 쿼리 객체)
	5. Query 실행
		1) executeQuery(sql) - return ResultSet
		2) executeUpdate(sql) - return Integer
	6. ResultSet 핸들링
	7. 자원 해제
</pre>
<table>
	<thead>
	<%
		Map<String,Object> model = (Map)request.getAttribute("model");
		String[] headers =(String[]) model.get("headers");
		List<DataBasePropertyVO> list = (List) model.get("list");
	%>
		<%
			String thPatrn = "<th>%s</th>";
			out.print("<tr>");
			for(int i=0;i<headers.length;i++)
			{
				out.print(String.format(thPatrn, headers[i]));
			}
			out.print("</tr>");
		%>
	</thead>
	<tbody>
		<%
			String tdPatrn = "<td>%s</td>";
			for(int i=0;i<list.size();i++){
				out.print("<tr>");
				out.print(String.format(tdPatrn, list.get(i).getProperty_name()));
				out.print(String.format(tdPatrn, list.get(i).getProperty_value()));
				out.print(String.format(tdPatrn, list.get(i).getDescription()));
				out.print("</tr>");
			}
		%>
	</tbody>
</table>
</body>
</html>
