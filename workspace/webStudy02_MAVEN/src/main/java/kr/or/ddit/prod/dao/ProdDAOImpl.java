package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.ProdVO2;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImpl implements IProdDAO {
	
	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public int insertProd(ProdVO2 prod) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ProdVO2 selectProd(String prod_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdVO> selectProdList() {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession(true);
				){
			IProdDAO mapper = sqlSession.getMapper(IProdDAO.class);
			return mapper.selectProdList();
		}
	}

	@Override
	public int updateProd(ProdVO2 prod) {
		// TODO Auto-generated method stub
		return 0;
	}


}
