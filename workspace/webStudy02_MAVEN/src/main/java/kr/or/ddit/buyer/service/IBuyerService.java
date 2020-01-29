package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public interface IBuyerService {
	public List<BuyerVO> readBuyerList(PagingVO pagingVO);
	
	public int readBuyerCount(PagingVO pagingVO);
}
