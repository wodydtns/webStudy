package kr.or.ddit.web03;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calender.do")
public class CalenderServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String yearParam = req.getParameter("year_select");
		String monthParam = req.getParameter("month_select");
		
		int statusCode =0;
		if(yearParam == null || !yearParam.matches("\\d+")) {
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		if(monthParam== null || !monthParam.matches("\\d+")) {
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		if(statusCode !=0) {
			resp.sendError(statusCode);
			return;
		}
		int year = Integer.parseInt(yearParam);
		int month = Integer.parseInt(monthParam);
		
		
	}
}