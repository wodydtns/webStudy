package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.IProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.ProdVO2;
import kr.or.ddit.vo.ProdVO2;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdVO> readProdList() {
		List<ProdVO> prodList = prodDAO.selectProdList();
		return prodList;
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
