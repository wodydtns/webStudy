package kr.or.ddit.web03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.OperatorType;

@WebServlet(urlPatterns = "/calculator.do")
public class CalculatorServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String leftParam = req.getParameter("leftOp");
		String rightParam = req.getParameter("rightOp");
		String opParam = req.getParameter("operator");
		
		int statusCode = 0;
		if(leftParam ==null || !leftParam.matches("\\d+")) {
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		if(rightParam ==null || !rightParam.matches("\\d+")) {
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		//checked exception , unchecked exception의 차이 -> 에러가 뜨는가 뜨지 않는가
		OperatorType oprator=null; 
		try {
			oprator = OperatorType.valueOf(opParam); // enum 상수는 선언한 상수와 동일한 이름을 하나 가지고 있다. 
		}catch(IllegalArgumentException e) {
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		
		if(statusCode!=0) {
			resp.sendError(statusCode);
			return;
		}
		
		int leftOp = Integer.parseInt(leftParam);
		int rightOp = Integer.parseInt(rightParam);
				
		String contents = oprator.opExpr(leftOp, rightOp);
		resp.setContentType("text/plain");
		try(
				PrintWriter out = resp.getWriter();
		){
			out.println(contents);
		}
		
	}
}
