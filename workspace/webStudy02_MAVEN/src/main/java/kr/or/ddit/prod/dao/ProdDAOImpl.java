package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImpl implements IProdDAO {
	SqlSessionFactory sqlSessionFactory =
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public int insertProd(ProdVO prod) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ProdVO selectProd(String prod_id) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			IProdDAO mapper = sqlSession.getMapper(IProdDAO.class);
			return mapper.selectProd(prod_id);		
		}
	}

	@Override
	public int selectProdCount(PagingVO pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			IProdDAO mapper = sqlSession.getMapper(IProdDAO.class);
			return mapper.selectProdCount(pagingVO);		
		}
	}
	
	@Override
	public List<ProdVO> selectProdList(PagingVO pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			IProdDAO mapper = sqlSession.getMapper(IProdDAO.class);
			return mapper.selectProdList(pagingVO);			
		}
	}

	@Override
	public int updateProd(ProdVO prod) {
		// TODO Auto-generated method stub
		return 0;
	}

}
