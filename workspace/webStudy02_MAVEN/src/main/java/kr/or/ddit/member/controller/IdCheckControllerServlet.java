package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/checkId.do")
public class IdCheckControllerServlet extends HttpServlet {
	IMemberService service = MemberServiceImpl.getInstance();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if (StringUtils.isBlank(id)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		ServiceResult result = null;
		String message = "";
		try {
			MemberVO savedMember = service.readMember(id);
			result = ServiceResult.PKDUPLICATED;
			message = "아이디 중복, 사용 불가";
		}catch(UserNotFoundException e) {
			result = ServiceResult.OK; 
			message = "사용가능";
			
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status",result.name());
		resultMap.put("message",message);
		resp.setContentType("application/json;charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		// 마샬링과 직렬화 동시 수행
		mapper.writeValue(resp.getWriter(), resultMap);
	}
}
