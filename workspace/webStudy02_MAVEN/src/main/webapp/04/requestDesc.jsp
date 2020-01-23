<%@page import="kr.or.ddit.enumpkg.BrowserType"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" 
	src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<title>04/requestDesc.jsp</title>
</head>
<body>
<!-- 1. RequestDescServlet 과 동일한 결과로 응답 생성 -->
<!-- 2. Map -> enum 으로 변경 -->
<!-- 3. 클라이언트가 원하는 헤더를 선택할 수 있도록. -->
<!-- headerName 파라미터의 존재 여부에 따라 두번째 table 태그가 변경됨. -->
<form>
	<select name="headerName">
		<option value="">헤더명 선택</option>
		<%
			Enumeration<String> names = request.getHeaderNames();
			String param = request.getParameter("headerName");
			String ptrn = "<option %s>%s</option>";
			Map<String, String> headers = new LinkedHashMap<>();
			while(names.hasMoreElements()){
				String name = names.nextElement();
				String value = request.getHeader(name);
				headers.put(name, value);
				String selected = name.equals(param)?"selected":"";				
				out.println(String.format(ptrn, selected, name));
			}
		%>
	</select>
</form>
<table>
	<thead>
		<tr>
			<th>데이터명</th>
			<th>데이터값</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<th>프로토콜(Line)</th>
			<td><%=request.getProtocol() %></td>
		</tr>
		<tr>
			<th>request URI(Line)</th>
			<td><%=request.getRequestURI() %></td>
		</tr>
		<tr>
			<th>Http method(Line)</th>
			<td><%=request.getMethod() %></td>
		</tr>
		<tr>
			<th>only POST meta data(request Body 있을때)</th>
			<td>
				<%=request.getCharacterEncoding() %>, 
				<%=request.getContentLength() %>,
				<%=request.getContentType() %>
			</td>
		</tr>
		<tr>
			<th>context Path(client side absolute path)</th>
			<td><%=request.getContextPath() %></td>
		</tr>
	</tbody>
</table>
<table>
	<thead>
		<tr>
			<th>헤더명</th>
			<th>헤더값</th>
		</tr>
	</thead>
	<tbody>
		<%
			String specific = null;
			String trPtrn = "<tr><th>%s</th><td>%s</td></tr>";
			if(param!=null && param.trim().length()>0){
				specific = request.getHeader(param);
			}
			if(specific!=null){
				out.println(String.format(trPtrn, param, specific));
			}else{
				for(Entry<String,String> entry : headers.entrySet()){
					out.println(String.format(trPtrn, 
									entry.getKey(), entry.getValue()));
				}
			}
		%>
	</tbody>
</table>
<%
	BrowserType browser = BrowserType.findBrowser(headers.get("user-agent"));
	String browserName = browser.getData().getName();
%>
<script type="text/javascript">
	alert("당신의 브라우저는 <%=browserName %> 입니다.");
	$("[name='headerName']").on("change", function(){
		var form = $(this).parent("form");
		form.submit();
	});
</script>
</body>
</html>
























