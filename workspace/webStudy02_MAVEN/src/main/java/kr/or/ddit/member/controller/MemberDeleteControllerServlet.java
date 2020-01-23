package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberDelete.do")
public class MemberDeleteControllerServlet extends HttpServlet {
	IMemberService service = MemberServiceImpl.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 파라미터 받아오기
		HttpSession session = req.getSession();
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		String mem_id = authMember.getMem_id();
		String mem_pass = req.getParameter("mem_pass");
		if (StringUtils.isBlank(mem_id) || StringUtils.isBlank(mem_pass)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		ServiceResult result = service.removeMember(new MemberVO(mem_id, mem_pass));
		String goPage = null;
		String message = null;
		boolean redirect = false;
		switch (result) {
		case OK:
			// 로그아웃을 진행하는 서블릿으로 보내기
			goPage = "/login/logout.do";
			redirect = true;
			break;
		case INVALIDPASSWORD:
			// 잘못된 비밀번호 -> 굳이 정보를 가져갈 필요가 없음 -> 리다이렉트
			goPage = "/mypage.do";
			redirect = true;
			message = "비번 오류";
			break;
		default://FAIL
			goPage = "/mypage.do";
			redirect = true;
			message = "서버 오류";
			break;
		}
		session.setAttribute("message", message);
		// flag 변수를 통해 경로를 변경
		if(redirect) {
			resp.sendRedirect(req.getContextPath()+goPage);
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}

	}
}
