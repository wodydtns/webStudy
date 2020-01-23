package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberUpdate.do")
public class MemberUpdateControllerServlet extends HttpServlet {
	IMemberService service = MemberServiceImpl.getInstance();
	/*
	 *  1. 요청 접수
	 *  2. 받은 파라미터 검증 - DAO를 넘기기 위한 검증
	 *  3. 검증에 통과하는 경우 or 통과하지 못하는 경우
	 *  4. 통과하면 비지니스 로직 사용
	 *  5. 돌려주는 데이터에 따라서 결과를 다르게 설정
	 *  6. 모델 데이터를 만들어내고 스코프 결정
	 *  7. 스코프를 들고 뷰로 이동 
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		MemberVO member = new MemberVO();
		// 기존에 클라이언트가 입력한 데이터를 넘겨주어 실패시에도 내용을 살려주도록 한다.
		req.setAttribute("member", member);

		// reflection 구성 - 데이터베이스의 컬럼 이름과 input 태그의 name 값을 동일하게 설정
		// getter 와 setter의 호출 이름의 규칙성을 이용함
		// 다수의 파라미터를 가져오는 맵 - req.getParameterMap()
		// getter/setter로 값을 세팅하는 member 
		//=> 둘 다 getter/setter 구조
		try {
			BeanUtils.populate(member, req.getParameterMap()); 
		//Illegal~ : 어플리케이션이 배열 이외의 인스턴스 작성, 필드의 설정 또는 취득, 메서드의 호출을 시도했을 경우
		// 호출자 이름이 규칙성이 어그러진 경우(invocation~exception) 발생
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		// 에러 메시지를 담기 위한 map
		Map<String,String> errors = new HashMap<String, String>();
		req.setAttribute("errors", errors);
		//클라이언트로 받아온 값의 적합성 검증
		boolean valid = validate(member,errors);
		String goPage = "";
		String message = "";
		HttpSession session = req.getSession();
		boolean redirect = false;
		if(valid) {
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			// 업데이트 성공 - 마이페이지로 -리다이렉트
			case OK:
				redirect = true;
				goPage = "/mypage.do";
				break;
			// 비밀번호 오류로 인한 업데이트 실패 - 마이페이지로 - 리다이렉트
			// 세션으로 메시지 전달
			case INVALIDPASSWORD:
				redirect = true;
				goPage = "/mypage.do";
				message = "비밀번호 오류";
				session.setAttribute("message", message);
				break;
			// 서버 오류로 인한 업데이트 실패 - 마이페이지로 - 클라이언트의 잘못이 아닌 서버의 잘못 -> dispatch방식으로 값을 그대로 살려주는 것이 더 좋음
		    // request로 전달
			case FAIL:
				message = "서버 오류, 잠시 뒤 시도해주세요.";
				req.setAttribute("message", message);
				goPage = "/WEB-INF/views/member/mypage.jsp";
				break;
			}
		}else {
			redirect = false;
			goPage = "/WEB-INF/views/member/mypage.jsp";
		}
		
		if(redirect) {
			resp.sendRedirect(req.getContextPath() + goPage);
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
		
	}

	private boolean validate(MemberVO member, Map<String, String> errors) {
		// 타당성을 검증하는 flag
		boolean valid = true;
		if (StringUtils.isBlank(member.getMem_id())) {
			valid = false;
			errors.put("mem_id", "회원명 누락");
		}
		if (StringUtils.isBlank(member.getMem_pass())) {
			valid = false;
			errors.put("mem_pass", "비밀번호 누락");
		}
		if (StringUtils.isBlank(member.getMem_name())) {
			valid = false;
			errors.put("mem_name", "이름 누락");
		}
		if (StringUtils.isBlank(member.getMem_zip())) {
			valid = false;
			errors.put("mem_zip", "우편번호 누락");
		}
		if (StringUtils.isBlank(member.getMem_add1())) {
			valid = false;
			errors.put("mem_add1", "주소1 누락");
		}
		if (StringUtils.isBlank(member.getMem_add2())) {
			valid = false;
			errors.put("mem_add2", "주소1 누락");
		}
		if (StringUtils.isBlank(member.getMem_mail())) {
			valid = false;
			errors.put("mem_mail", "이메일 누락");
		}
		// 변수가 Date타입인 값을 검증 및 재조정 
		if(StringUtils.isNotBlank(member.getMem_memorialday())) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {
				//parse 는 String -> Date형태로 변환
				//format 은 Date형태 값을 -> String으로 변환
				
				formatter.parse(member.getMem_memorialday());
			} catch (ParseException e) {
				// 에러가 날 경우 포멧의 문제
				// 문제를 클라이언트에게 확인 시키고 수정하기 위해서 
				valid = false;
				errors.put("mem_memorialday","날짜 형식 확인(1999-09-09)");
			}
		}
		return valid;
	}
}
