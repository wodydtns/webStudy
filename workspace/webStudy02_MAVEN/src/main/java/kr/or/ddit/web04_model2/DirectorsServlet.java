package kr.or.ddit.web04_model2;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.vo.DirectorVO;


public class DirectorsServlet extends HttpServlet{
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		Set<DirectorVO> dataSet = new LinkedHashSet<DirectorVO>();
		dataSet.add(new DirectorVO("DIRECTOR01", "봉준호", "봉보로봉봉", 
				"/WEB-INF/views/directors/bongjunho.jsp"));
		dataSet.add(new DirectorVO("DIRECTOR02", "박찬욱", null, 
				"/WEB-INF/views/directors/parkchanuk.jsp"));
		dataSet.add(new DirectorVO("DIRECTOR03", "최동훈", null, 
				"/WEB-INF/views/directors/choidonghun.jsp"));
		dataSet.add(new DirectorVO("DIRECTOR04", "김지운", null, 
				"/WEB-INF/views/directors/kimjiun.jsp"));
		dataSet.add(new DirectorVO("DIRECTOR05", "장준환", null, 
				"/WEB-INF/views/directors/jangjunhwan.jsp"));
		getServletContext().setAttribute("dataSet", dataSet);
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/directors/directorsForm.jsp")
			.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String director = req.getParameter("director");
		if(StringUtils.isBlank(director)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		Set<DirectorVO> dataSet = (Set<DirectorVO>) getServletContext().getAttribute("dataSet");
 		DirectorVO paramVO = new DirectorVO(director, null, null, null);
 		boolean searched = !dataSet.add(paramVO);
 		System.out.println(searched);
 		if(!searched) {
 			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
 			return;
 		}
 		
 		DirectorVO searchedVO = null;
 		for(DirectorVO tmp : dataSet) {
 			if(tmp.equals(paramVO)) {
 				searchedVO = tmp;
 				break;
 			}
 		}
 		System.out.println(searchedVO);
 		String bPage = searchedVO.getContentPath();
 		req.setAttribute("searchedVO", searchedVO);
 		req.getRequestDispatcher(bPage)
 			.forward(req, resp);
	}
}



















