package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/mypage.do")
public class MypageControllerServlet extends HttpServlet{
	
	IMemberService service = MemberServiceImpl.getInstance();
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1. 요청 접수, 분석, 검증
		
		HttpSession session = req.getSession(false);
		String goPage = null;
		boolean redirect = false;
		//세션이 한번도 생성되지 않았다면 -> 세션이 종료된 상태 -
		if(session==null) {
			redirect = true;
			goPage ="/login/login.do";
		}else {
			MemberVO authmember = (MemberVO) session.getAttribute("authMember");
			String mem_id = authmember.getMem_id();
//			2. 로직을 실행해 Model 확보
			MemberVO member = service.readMember(mem_id);
			try {
				BeanUtils.copyProperties(authmember, member);
			} catch (IllegalAccessException | InvocationTargetException e) {
				throw new RuntimeException(e);
			}
//			3. Scope를 이용해 model을 공유
			req.setAttribute("member", member);
//			4. view layer 선택
			goPage ="/WEB-INF/views/member/mypage.jsp";
		}
//		5. 이동
		if(redirect) {
			resp.sendRedirect(req.getContextPath()+goPage);
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
			
		}
	}
}
