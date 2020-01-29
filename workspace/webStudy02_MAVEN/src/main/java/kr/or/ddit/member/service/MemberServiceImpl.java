package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class MemberServiceImpl implements IMemberService {
	IMemberDAO memberDAO = MemberDAOImpl.getInstance();
	IAuthenticateService authService = AuthenticateServiceImpl.getInstance();
	
	private static MemberServiceImpl instance;
	public static MemberServiceImpl getInstance(){
		if(instance==null) instance = new MemberServiceImpl();
		return instance;
	}
	private MemberServiceImpl() {}

	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		if(memberDAO.selectMember(member.getMem_id())==null) {
			int rowcnt = memberDAO.insertMember(member);
			if(rowcnt>0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}// inner if end
		}else {
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
	}
	
	@Override
	public int readMemberCount(PagingVO pagingVO) {
		return memberDAO.selectMemberCount(pagingVO);
	}

	@Override
	public List<MemberVO> readMemberList(PagingVO pagingVO) {
		List<MemberVO> memberList = memberDAO.selectMemberList(pagingVO);
		for(MemberVO member:memberList) {
			System.out.println(member.getMem_mail()+"발송");
		}
		return memberList;
	}

	@Override
	public MemberVO readMember(String mem_id){
		MemberVO member = memberDAO.selectMember(mem_id);
		if(member==null) {
			throw new UserNotFoundException();
		}
		return member;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		ServiceResult result = null;
 		Object authResult = authService.authenticate(member);
 		if(authResult instanceof ServiceResult) {
 			result = (ServiceResult) authResult;
 		}else {
 			int rowcnt = memberDAO.updateMember(member);
 			if(rowcnt>0) {
 				result = ServiceResult.OK;
 			}else {
 				result = ServiceResult.FAIL;
 			}
 		}
		return result;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		ServiceResult result = null;
		Object authResult = authService.authenticate(member);
		if(authResult instanceof MemberVO) {
			int rowcnt = memberDAO.deleteMember(member.getMem_id());
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		}else {
			result = (ServiceResult) authResult;
		}		
		return result;
	}

}









