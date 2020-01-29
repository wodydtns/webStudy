package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodView.do")
public class ProdViewControllerServlet extends HttpServlet{
	IProdService service = new ProdServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String prod_id = req.getParameter("what");
		if(StringUtils.isBlank(prod_id)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		ProdVO prod = service.readProd(prod_id);
		req.setAttribute("prod", prod);
		String view = "/WEB-INF/views/prod/prodView.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}
}














