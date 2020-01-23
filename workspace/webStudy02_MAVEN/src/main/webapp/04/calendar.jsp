<%@page import="org.apache.tomcat.jni.Local"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page import="java.util.Calendar"%>
<%@page import="static java.util.Calendar.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/calendar.jsp</title>
<style type="text/css">
	td,th{
		border:1px solid black;
	}
	table{
		border-collapse: collapse;
		width: 100%;
		height: 500px;
	}
</style>
<script type="text/javascript">
	function clickHandler(year, month){
		console.log( document.calendarForm );
		var form = document.calendarForm;
		console.log( form.year );
		console.log( form.month );
		form.year.value = year;
		form.month.value = month;
		form.submit();
	}
</script>
</head>
<%
	String yearParam = request.getParameter("year");
	String monthParam = request.getParameter("month");
	String language = request.getParameter("language");
	
	Calendar cal = getInstance();
	if(yearParam!=null && yearParam.matches("\\d{4}")
			&& monthParam!=null && monthParam.matches("\\d{1,2}")){
		cal.set(YEAR, Integer.parseInt(yearParam));
		cal.set(MONTH, Integer.parseInt(monthParam));
	}
	int maxDay = cal.getActualMaximum(DAY_OF_MONTH);
	int year = cal.get(YEAR);
	int month = cal.get(MONTH);
	cal.set(DAY_OF_MONTH , 1);
	int offset = cal.get(DAY_OF_WEEK) - 1;
	cal.add(MONTH, -1);
	int beforeYear = cal.get(YEAR);
	int beforeMonth = cal.get(MONTH);
	cal.add(MONTH, 2);
	int nextYear = cal.get(YEAR);
	int nextMonth = cal.get(MONTH);
	
	Locale locale = request.getLocale();
	if(language!=null && language.trim().length()>0){
		locale = Locale.forLanguageTag(language);
	}
	DateFormatSymbols dfs = new DateFormatSymbols(locale);
	String[] weekdays = dfs.getShortWeekdays();
	String[] months = dfs.getMonths();
%>
<body>
<h4>
<a href="#" onclick="clickHandler(<%=beforeYear%>, <%=beforeMonth %>);">이전달</a>
========
<%=year %>년 <%=month+1 %>월
========
<a href="#" onclick="clickHandler(<%=nextYear%>, <%=nextMonth %>);">다음달</a>
</h4>
<form name="calendarForm">
	<input type="hidden" name="command" value="CALENDAR" />
	<input type="number" name="year" value="<%=year %>"/>년
	<select name="month">
		<%
			for(int i=0; i<months.length-1; i++){
		%>
		<option value="<%=i %>" <%=i==month?"selected":"" %>><%=months[i] %></option>
		<%
			}
		%>
	</select>
	<select name="language">
		<%
			Locale[] locales = Locale.getAvailableLocales();
			for(Locale tmp : locales){
				String text = tmp.getDisplayLanguage(tmp);
				if(text==null || text.trim().length()==0) continue;
				String code = tmp.toLanguageTag();
				String selected = tmp.equals(locale) ? "selected":"";
		%>
			<option value="<%=code%>" <%=selected %>><%=text %></option>
		<%
			}
		%>
	</select>
	<input type="submit" value="달력" />
</form>
<table>
	<thead>
		<tr>
			<%
				for(int col=1; col<=7; col++){
					%>
					<th><%=weekdays[col] %></th>
					<%
				}
			%>
		</tr>
	</thead>
	<tbody>
		<%
			int count = 1;
			String ptrn = "<td>%s</td>";
			for(int row=1; row<=6; row++){
				out.println("<tr>");
				for(int col=1; col<=7; col++){
					int dayCount = count++ - offset;
					String display = dayCount>0 && dayCount<=maxDay ?
										(dayCount+""):"&nbsp;";
					out.println(String.format(ptrn, display));
				}
				out.println("</tr>");
			}
		%>
	</tbody>
</table>
</body>
</html>











