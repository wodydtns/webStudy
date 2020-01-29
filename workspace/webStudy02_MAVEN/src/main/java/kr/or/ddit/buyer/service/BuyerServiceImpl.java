package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.buyer.dao.IBuyerDAO;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public class BuyerServiceImpl implements IBuyerService{
	
	IBuyerDAO buyerDAO = new BuyerDAOImpl();
	@Override
	public List<BuyerVO> readBuyerList(PagingVO pagingVO) {

		return buyerDAO.selectBuyerList(pagingVO);
	}
	@Override
	public int readBuyerCount(PagingVO pagingVO) {
		// TODO Auto-generated method stub
		return buyerDAO.selectBuyerCount(pagingVO);
	}

}
