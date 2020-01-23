<%@page import="kr.or.ddit.web05_model2.FileWrapper"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.4.1.min.js"></script>
</head>
<body>
<ul id="leftUL">
<%
	String contextRootRP = (String) request.getAttribute("contextRootRP");
	FileWrapper[] wrappers = (FileWrapper[]) request.getAttribute("listWrappers");
	for(FileWrapper tmp : wrappers){
		
		
		%>
		<li class="<%=tmp.getClassName()%>" id="<%=tmp.getId() %>">
			<%=tmp.getName() %></li>
		<%
	}
%>
</ul>
<div id="userOption"></div>
<div id="result"></div>
<form id="brForm">
	<input type="hidden" name="browsingURL" />
	<input type="hidden" name="selectedFile" />
</form>
<script type="text/javascript">
	var brForm = $("#brForm");
	var leftUL = $("#leftUL");
	brForm.on("submit", function(event){
		event.preventDefault();
		let queryString = $(this).serialize();
		$.ajax({
			data : queryString,
			dataType : "json",
			success : function(resp) {
				if(!resp) return;
				let liTags = [];
				$(resp).each(function(index, fileWrapper){
					console.log(fileWrapper);
					liTags.push($("<li>").attr({
									"id":fileWrapper.id,
									"class":fileWrapper.className
								}).text(fileWrapper.name));
				});
				leftUL.empty();
				leftUL.append(liTags);
			},
			error : function(xhr) {
				console.log(xhr.status);
			}
		});
		
	});
	var browsingURL = brForm.find("[name='browsingURL']");
	leftUL.on("dblclick", ".dir", function(){
		var path = $(this).prop("id");
		browsingURL.val(path);
		brForm.submit();
	});
	//선택한 것이 파일일 경우
	//하려는 것 : 파일 복사 혹은 이동
	//해야할 일 
	// 1. 선택한 것이 파일인지 폴더인지 구별 
	leftUL.on("dblclick",".file",function(){
		//2. 파일이면 path를 받아서 servlet에 전달
		let fileName = $(this).prop("id");
		//3. 파일을 가지고 어떻게 할 것인지 선택하는 창이 필요함
		let selectBar= $("<select>",{
			name : "userSelect"
		});
		let optionBar1 = $("<option>",{
			value : "copy",
			text : "copy"
			});
		let optionBar2 = $("<option>",{
			value : "move",
			text : "move"
		});
		let optionBar3 = $("<option>",{
			value : "delete",
			text : "delete"
		});
		
		selectBar.append(optionBar1);
		selectBar.append(optionBar2);
		selectBar.append(optionBar3);
		$('#userOption').append(selectBar);
		browsingURL.val(fileName);
		brForm.append(selectBar);
 		brForm.submit();
	});
</script>

</body>
</html>










