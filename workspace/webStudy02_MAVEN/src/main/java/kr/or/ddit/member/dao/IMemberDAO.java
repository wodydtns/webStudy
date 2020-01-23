package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.vo.MemberVO;

/**
 * 회원관리(CRUD)에서 사용할 Persistence Layer
 * @author pc04
 *
 */
public interface IMemberDAO {
	/**
	 * 한명의 회원 조회
	 * @param mem_id : 조회할 회원의 PK 
	 * @return 존재하지 않으면, null 반환
	 */
	public MemberVO selectMember(String mem_id);
	
	/**
	 * 신규회원등록
	 * @param member 등록할 회원 정보
	 * @return 등록성공(rowcnt>0) 실패 (rowcnt<=0)
	 */
	public int insertMember(MemberVO member);
	
	/**
	 * 회원 목록 조회
	 * @return 존재하지 않는다면 , size() == 0
	 */
	public List<MemberVO> selectMemberList();
	
	/**
	 * @param member 수정할 정보
	 * @return 수정성공(rowcnt>0) 실패 (rowcnt<=0)
	 */
	public int updateMember(MemberVO member);
	/**
	 * @param mem_id 삭제할 정보의 아이디
	 * @return 삭제성공(rowcnt>0) 실패 (rowcnt<=0)
	 */
	public int deleteMember(String mem_id);
}
