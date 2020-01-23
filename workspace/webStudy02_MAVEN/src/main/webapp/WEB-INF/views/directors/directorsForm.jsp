<%@page import="java.util.Objects"%>
<%@page import="kr.or.ddit.vo.DirectorVO"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function changeHandler(){
		document.forms[0].submit();
	}
</script>
</head>
<body>
<form method="post" name="directorForm">
	<%
		Set<DirectorVO> dataSet =(Set)application.getAttribute("dataSet");
	
	%>
	<select name="director" required onchange="changeHandler();">
		<option value="">영화감독</option>
		<%
			String ptrn = "<option value='%s'>%s</option>";
			for( DirectorVO tmp : dataSet ){
				out.println(String.format(ptrn, tmp.getCode(), 
						Objects.toString(tmp.getNickname(), tmp.getName())));
			}
		%>
	</select>
</form>
</body>
</html>








