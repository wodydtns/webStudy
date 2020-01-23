package kr.or.ddit.web05_model2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/contextbrowse.do")
public class ContextBrowsingServlet extends HttpServlet{
	ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	 	application = getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1. 요청분석
		// 파일일 경우 파일의 url, 폴더일 경우 폴더의 url
		String rootUrl = req.getParameter("browsingURL");
		System.out.println("유저 파일" +rootUrl);
		if(StringUtils.isBlank(rootUrl)) {
			rootUrl = "";
		}
		// 유저가 선택한 사항을 받는 파라미터 생성
		String userSelect = req.getParameter("userSelect");
		System.out.println("유저 선택" + userSelect);
		// null checking
		// 값이 들어와 있지 않으면 원래 페이지로 돌리기
		if(StringUtils.isBlank(userSelect)) {
			rootUrl = "";
		}
		// 파일 객체 생성 
		File selectedFile = new File(rootUrl); // 원본 path;
						
		System.out.println("유저가 선택한 파일 " +selectedFile);
		
		// 이것이 파일 + 유저 선택에 따라 선택 
		// 복사할 경우 : 동일한 파일을 특정 경로의 폴더에 두어야한다.
		String tempFolder = application.getRealPath("/temp");
		System.out.println("temp 폴더" + tempFolder);
		System.out.println("경로" + tempFolder+selectedFile);
		// 파일 객체이므로 fileinput,outputstream 사용
		FileInputStream fis = null;
		FileOutputStream fos = null;
		if(selectedFile.isFile() && "copy".equals(userSelect)) {
			fis = new FileInputStream(selectedFile);
			fos = new FileOutputStream(tempFolder+selectedFile);
			
			byte[] b = new byte[1024];
			int cnt = 0;
			while((cnt=fis.read(b)) != -1){
				fos.write(b, 0, cnt);
			}
			System.out.println("파일이 복사되었습니다.");
			fis.close();
            fos.close();
		// 삭제의 경우 -> 폴더에서 리스트를 가져온다-> 리스트에서 선택한 파일의 이름과 동일할 경우 삭제
		}else if(selectedFile.isFile()&& "delete".equals(userSelect)) {
			String userSelectFile = userSelect.substring(1, 5);
			System.out.println("substring한 파일 이름" + userSelectFile);
			File temp = new File(tempFolder);
			File[] tempList = temp.listFiles();
			for(File tmp:tempList) {
				if(tmp.getName() == userSelectFile) {
					tmp.delete();
				}
			}
			
		}
		// 파일을 복사 or 이동 or 삭제
		
		String contextRootRP = application.getRealPath("");
//		2. 로직 운영
		// 받아온 url이 파일인지 폴더인지 구별
		// 파일이면 바로 파일 객체에 받아서 input stream 개방
		String folderRP = application.getRealPath(rootUrl);
		System.out.println(folderRP);
		File folder = new File(folderRP);
		int status = 200;
		if(!folder.exists()) {
			status = HttpServletResponse.SC_NOT_FOUND;
		}else {
			if(folder.isFile()) {
				status = HttpServletResponse.SC_BAD_REQUEST;
			}
		}
		if(status==200) {
			File[] listFiles = folder.listFiles();
			// wrapper pattern , object adapter pattern
			FileWrapper[] listWrappers = new FileWrapper[listFiles.length];
			int idx = 0;
			for(File tmp : listFiles) {
				listWrappers[idx++] = new FileWrapper(tmp, application);
			}
			
			String accept = req.getHeader("Accept");
			if(StringUtils.containsIgnoreCase(accept, "json")) {
				resp.setContentType("application/json;charset=UTF-8");
				ObjectMapper mapper = new ObjectMapper();
//				1. marshalling : target(listWrappers)
//				2. serialization
				mapper.writeValue(resp.getWriter(), listWrappers);
			}else {
				req.setAttribute("listWrappers", listWrappers);
				String view = "/WEB-INF/views/browser/browseView.jsp";
				req.getRequestDispatcher(view).forward(req, resp);
			}
		}else {
			resp.sendError(status);
		}
	}
}















