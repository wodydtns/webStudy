package kr.or.ddit.web04_model2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login/logout.do")
public class LogoutControllerServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		// 로그인을 안한 상태에서 로그아웃 시도
		if(session.isNew()) {
			resp.sendError(400);
		}else {
			// 모든 속성 지워주고, 현재 세션을 명시적으로 종료
			session.invalidate();
			resp.sendRedirect(req.getContextPath() + "/");
		}
		
	}
}












