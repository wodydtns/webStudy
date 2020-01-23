package kr.or.ddit.member.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements IMemberDAO {
	
	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory(); 
	private static MemberDAOImpl instance;
	public static MemberDAOImpl getInstance() {
		if(instance==null) {
			instance = new MemberDAOImpl();
		}
		return instance;
	}
	private MemberDAOImpl(){}
	
	@Override
	public MemberVO selectMember(String mem_id) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			// 다운캐스팅 , 오타를 막기 위한 방법 - 리플렉션 기술을 응용한 방법 - mapper namespace에 퀄리파이드 네임을 넣는 이유
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class); // 맵퍼 프록시
			return mapper.selectMember(mem_id);
		}
	}
	
	@Override
	public int insertMember(MemberVO member) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true); // 자동 커밋 실행	
		){
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			return mapper.insertMember(member);
		}
	}
		
	@Override
	public List<MemberVO> selectMemberList() {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			return sqlSession.selectList("kr.or.ddit.member.dao.IMemberDAO.selectMemberList");
		}
	}
	
	@Override
	public int updateMember(MemberVO member) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			int rowcnt =  mapper.updateMember(member);
			sqlSession.commit();
			return rowcnt;
		}
	}
	@Override
	public int deleteMember(String mem_id) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			return mapper.deleteMember(mem_id);
		}
	}
}















