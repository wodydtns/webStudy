package kr.or.ddit.buyer.controller;

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

import kr.or.ddit.buyer.dao.BuyerOtherImpl;
import kr.or.ddit.buyer.dao.IBuyerOther;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/buyer/buyer.do")
public class BuyerListController extends HttpServlet{
	
	IBuyerService service = new BuyerServiceImpl();
	
	IBuyerOther dao = new BuyerOtherImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
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
		PagingVO<BuyerVO> pagingVO = new PagingVO<>(5,3);
		
		int totalRecord = service.readBuyerCount(pagingVO);
		System.out.println(totalRecord);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		List<BuyerVO> buyerList = service.readBuyerList(pagingVO);
		System.out.println(buyerList.toString());
		pagingVO.setDataList(buyerList);
		req.setAttribute("BuyerName", dao.selectBuyerName());
		req.setAttribute("BuyerBank", dao.selectBuyerBank());
		req.setAttribute("pagingVO", pagingVO);
		String view = "/WEB-INF/views/buyer/buyerList.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}
}
