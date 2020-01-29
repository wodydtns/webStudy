package kr.or.ddit.buyer.dao;

import java.util.List;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public interface IBuyerDAO {
	public List<BuyerVO> selectBuyerList(PagingVO<BuyerVO> pagingVO);
	
	public int selectBuyerCount(PagingVO<BuyerVO> pagingVO);
}
