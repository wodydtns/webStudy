package kr.or.ddit.buyer.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.prod.dao.IOthersDAO;

public class BuyerOtherImpl implements IBuyerOther{
	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	@Override
	public List<Map<String, Object>> selectBuyerName() {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
				// 다운캐스팅 , 오타를 막기 위한 방법 - 리플렉션 기술을 응용한 방법 - mapper namespace에 퀄리파이드 네임을 넣는 이유
			IBuyerOther mapper = sqlSession.getMapper(IBuyerOther.class); // 맵퍼 프록시
				return mapper.selectBuyerName();
			}
	}

	@Override
	public List<Map<String, Object>> selectBuyerBank() {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
				// 다운캐스팅 , 오타를 막기 위한 방법 - 리플렉션 기술을 응용한 방법 - mapper namespace에 퀄리파이드 네임을 넣는 이유
			IBuyerOther mapper = sqlSession.getMapper(IBuyerOther.class); // 맵퍼 프록시
				return mapper.selectBuyerBank(); 
			}
	}

}
