package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.SearchVO;

@WebServlet("/member/memberList.do")
public class MemberListControllerServlet extends HttpServlet{
	IMemberService service = MemberServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String accept = req.getHeader("Accept");
		String pageParam = req.getParameter("page");
		String searchType = req.getParameter("searchType");
		String searchWord = req.getParameter("searchWord");
		SearchVO searchVO = new SearchVO(searchType,searchWord);
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		PagingVO<MemberVO> pagingVO = new PagingVO<MemberVO>(5,3);
		pagingVO.setSearchVO(searchVO);
		int totalRecord = service.readMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		List<MemberVO> memberList = service.readMemberList(pagingVO);
		pagingVO.setDataList(memberList);
		//데이터가 json이라면
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(resp.getWriter(), pagingVO);
		}else {
			req.setAttribute("pagingVO", pagingVO);
			String view = "/WEB-INF/views/member/memberList.jsp";
			req.getRequestDispatcher(view).forward(req, resp);
		}
		
		
	}
}
