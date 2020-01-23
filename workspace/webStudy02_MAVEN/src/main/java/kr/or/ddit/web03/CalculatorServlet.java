package kr.or.ddit.web03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import kr.or.ddit.enumpkg.OperatorType;

@WebServlet("/calculator.do")
public class CalculatorServlet extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String leftParam = req.getParameter("leftOp");
		String rightParam = req.getParameter("rightOp");
		String opParam = req.getParameter("operator");
		String accept = req.getHeader("Accept");		
		int statusCode = 0;
		if(leftParam==null || !leftParam.matches("\\d+")) {
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		if(rightParam==null || !rightParam.matches("\\d+")) {
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		OperatorType oprator = null;
		try {
			oprator = OperatorType.valueOf(opParam);
		}catch (IllegalArgumentException e) {
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		if(accept.toLowerCase().contains("xml")) {
			statusCode = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
		}
		if(statusCode!=0) {
			resp.sendError(statusCode);
			return;
		}
		
		int leftOp = Integer.parseInt(leftParam);
		int rightOp = Integer.parseInt(rightParam);
		
		String contents = oprator.opExpr(leftOp, rightOp);
		
		String mime = "text/plain";
		
		if(accept.toLowerCase().contains("json")) {
			mime = "application/json";
			Date now = new Date();
			Map<String,Object> resultMap = new HashMap<String, Object>();
			resultMap.put("contents",contents);
			resultMap.put("now",now);
			contents = marshalling(resultMap);
			//마샬링 작업  
//			contents = "{\"result\":\""+contents+"\", \"now\":\""+now+"\"}";
		}
		resp.setContentType(mime);
		try(
			PrintWriter out = resp.getWriter();
		){
			out.println(contents);
		}
	}

	private String marshalling(Map<String, Object> resultMap) {
		StringBuffer json = new StringBuffer();
		json.append("{");
		String ptrn = "\"%s\":\"%s\",";
		for(Entry<String, Object> entry: resultMap.entrySet()) {
			String name = entry.getKey();
			Object value =entry.getValue();
			json.append(String.format(ptrn,name,value.toString()));
		}
		json.deleteCharAt(json.lastIndexOf(","));
		json.append("}");
		return json.toString();
	}
}














