<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="1kb" autoFlush="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/outDesc.jsp</title>
</head>
<body>
<h4>JspWriter out</h4>
<pre>
	: 응답 데이터를 기록하기 위한 출력스트림, 출력 버퍼 제어 관리자.
	버퍼의 크기 : <%=out.getBufferSize() %>
	버퍼의 잔여 크기 : <%=out.getRemaining() %>
	자동방출 여부 : <%=out.isAutoFlush() %>
	<%
		for(int i=1; i<=100; i++){
			if(out.getRemaining()<20){
// 				out.flush(); // response commit
// 				out.clear();  ; response commit;
// 				out.clearBuffer();
			}
			out.println(i+"번째 데이터 기록");
			if(i==99){
				throw new RuntimeException("강제 발생 예외");
			}
		}
	%>
</pre>
</body>
</html>












