package kr.or.ddit.web04_model2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.utils.CookieUtils;
import kr.or.ddit.utils.CookieUtils.TextType;
import kr.or.ddit.vo.DataBasePropertyVO;
import kr.or.ddit.vo.LoginVO;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/login/login.do")
public class LoginControllerServlet extends HttpServlet {
	IAuthenticateService service = AuthenticateServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/login/loginForm.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1. 파라미터 확보
		req.setCharacterEncoding("UTF-8");
		String mem_id = req.getParameter("mem_id");
		String mem_pass = req.getParameter("mem_pass");

		// 저장할 아이디 확보
		String idSave = req.getParameter("idSave");
		System.out.println(idSave);
//		2. 검증
		if (mem_id == null || mem_id.trim().length() == 0 || mem_pass == null || mem_pass.trim().length() == 0) {
//			3-1. 불통 : 400
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

//		   3-2. 통과
//		4. 인증
		boolean redirect = false;
		String goPage = null;
		try {
			Object result = service.authenticate(new MemberVO(mem_id, mem_pass));

			if (result instanceof MemberVO) {
				Cookie idCookie = CookieUtils.createCookie("idCookie", mem_id, req.getContextPath(), TextType.PATH);
				int maxAge = 0;
				if ("save".equals(idSave)) {
					maxAge = 60 * 60 * 24 * 3;
				}
				idCookie.setMaxAge(maxAge);
				resp.addCookie(idCookie);
//			5-1. 성공 : redirect, POST-Redirect-GET
				redirect = true;
//			String encoded = URLEncoder.encode(mem_id, "UTF-8");
//			goPage = "/?mem_id="+encoded;
				req.getSession().setAttribute("authMember", result);

				goPage = "/";
			} else {

//			5-2. 실패
				redirect = true;
				String msg = null;
				if (result.equals(ServiceResult.INVALIDPASSWORD)) {
					msg = "비밀번호 오류.";

				} else if (result.equals(ServiceResult.REMOVED)) {
					msg = "탈퇴한 회원은 같은 계정으로 로그인이 불가합니다.";
				}
				req.getSession().setAttribute("message", msg);
				goPage = "/login/login.do";
			} // if end
		} catch (UserNotFoundException e) {
			redirect = true;
			String msg = "존재 하지 않는 유저";
			req.getSession().setAttribute("message", msg);
			goPage = "/login/login.do";
		}

		// 이동 코드
		if (redirect) {
			resp.sendRedirect(req.getContextPath() + goPage);
		} else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}

	}

}
