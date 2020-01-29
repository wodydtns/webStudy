package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.SearchVO;

@WebServlet("/prod/prodList.do")
public class ProdListControllerServlet extends HttpServlet {
	IProdService service = new ProdServiceImpl();
	IOthersDAO othersDAO = new OthersDAOImpl();
	
	private void addAttribute(HttpServletRequest req){
		req.setAttribute("lprodList", othersDAO.selectLprodList());
		req.setAttribute("buyerList", othersDAO.selectBuyerList());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String accept = req.getHeader("Accept");
		
		addAttribute(req);
		
		String pageParam = req.getParameter("page");
		
		ProdVO searchDetail = new ProdVO();
		try {
			BeanUtils.populate(searchDetail, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		PagingVO<ProdVO> pagingVO = new PagingVO<>(5, 3);
		pagingVO.setSearchDetail(searchDetail);
		int totalRecord = service.readProdCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		List<ProdVO> prodList = service.readProdList(pagingVO);
		pagingVO.setDataList(prodList);
		
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(resp.getWriter(), pagingVO);
		}else {
			req.setAttribute("pagingVO", pagingVO);
			String view = "/WEB-INF/views/prod/prodList.jsp";
			req.getRequestDispatcher(view).forward(req, resp);
			
		}
		
	}
}











