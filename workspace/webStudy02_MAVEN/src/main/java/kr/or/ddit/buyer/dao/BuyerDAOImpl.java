package kr.or.ddit.buyer.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public class BuyerDAOImpl implements IBuyerDAO {

	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	@Override
	public List<BuyerVO> selectBuyerList(PagingVO<BuyerVO> pagingVO) {
		SqlSession session = sqlSessionFactory.openSession();
		IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
		
		return mapper.selectBuyerList(pagingVO);
	}
	@Override
	public int selectBuyerCount(PagingVO<BuyerVO> pagingVO) {
		SqlSession session = sqlSessionFactory.openSession();
		IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
		
		return mapper.selectBuyerCount(pagingVO);
	}

}
