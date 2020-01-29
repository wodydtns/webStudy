package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 회원관리(CRUD)를 위한 Business Logic Layer
 * @author pc04
 *
 */

public interface IMemberService {
	/**
	 * 신규 회원 가입(등록)
	 * @param member
	 * @return PKDUPLICATED,OK, FAIL
	 */
	public ServiceResult createMember(MemberVO member);
	/**
	 * 회원목록 조회
	 * @return 
	 */
	public List<MemberVO> readMemberList(PagingVO pagingVO);
	/**
	 * 회원정보 상세 조회
	 * @param mem_id 조회할 회원의 아이디
	 * @return 존재하지 않는다면, UserNotFoundException 발생
	 */
	public MemberVO readMember(String mem_id);
	/**
	 * 회원정보 수정
	 * @param member 
	 * @return 존재하지 않는다면, UserNotFoundException 발생,
	 * 		    INVALIDPASSWORD,OK,FAIL
	 */
	public ServiceResult modifyMember(MemberVO member);
	/**
	 * @param member
	 * @return 존재하지 않는다면, UserNotFoundException 발생,
	 *  	   INVALIDPASSWORD,OK,FAIL
	 */
	public ServiceResult removeMember(MemberVO member);
	public int readMemberCount(PagingVO pagingVO);
	
}
