package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.prod.dao.IProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements IProdService {
	IProdDAO prodDAO = new ProdDAOImpl();

	@Override
	public ServiceResult createProd(ProdVO prod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProdVO readProd(String prod_id) {
		ProdVO prod = prodDAO.selectProd(prod_id);
		if(prod==null) {
			throw new CommonException(prod_id+"에 해당 상품이 없음.");
		}
		return prod;
	}

	@Override
	public int readProdCount(PagingVO pagingVO) {
		return prodDAO.selectProdCount(pagingVO);
	}
	
	@Override
	public List<ProdVO> readProdList(PagingVO pagingVO) {
		return prodDAO.selectProdList(pagingVO);
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		// TODO Auto-generated method stub
		return null;
	}

}
