<%@ page language="java"
    pageEncoding="UTF-8"%>
<%
	response.setHeader("Content-Type", "text/plain;charset=UTF-8");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/responseDesc.jsp</title>
</head>
<body>
<h4> response (HttpServletResponse ) 활용</h4>
<pre>
	Http 에 따라 응답이 패키징 되는 구조
	1. Response Line : protocol, Status Code
		Status Code : 요청 처리 결과를 의미하는 상태 코드
		100 : ing... (Http 의 하위 프로토콜인 Websocket에서 사용) 
			Http 기본 특성 : Stateless
			Websocket : statefull(connectfull)
		200 : 성공(OK)
			  200(OK), 202(Accepted, body  없는 경우 사용, 향후 처리됨을 의미) 	 
		300 : 클라이언트의 추가 액션이 필요한 상황
			304(Not Modified, cache 사용)
			302/307(Moved, 자원의 위치가 바뀌었으므로 새로운 요청을 보내라.)
		400 : client side 문제로 fail
			404(Not Found), 400(BadRequest, 필수파라미터 누락, 파라미터 타입 오류, 데이터의 길이 오류)
			403(Forbidden)/401(UnAuthorized) - 보안처리
		500 : server side 문제로 fail (Internel server error)
		<%
// 			response.sendError(HttpServletResponse.SC_NOT_FOUND, "원하는 서비스가 제공되지 않음.");
// 			response.setStatus(HttpServletResponse.SC_OK);
// 			response.sendRedirect(request.getContextPath() + "/04/requestDesc.jsp");
		%>
		
	2. Response Header : 이름과 값의 쌍의 구조로 전송, 문자열로 전송.
		1) MIME 설정 : Content-Type
		   a) page 지시자의 contentType 속성
		   b) response.setContentType(MIME)
		   c) response.setHeader(name, value)
		2) 캐시 제어 : Pragma, Cache-Control, Expires
			<a href="<%=request.getContextPath() %>/05/cacheControl.jsp">cacheControl.jsp참고</a>
		3) Auto request : Refresh
			<a href="${pageContext.request.contextPath }/05/autoRequest.jsp">autoRequest.jsp 참고</a>
		4) 페이지 흐름 구조(페이지 이동) : Redirect(Location)
			<a href="./flowControl.jsp">flowControl.jsp</a>	
	3. Response Body
</pre>
</body>
</html>


















