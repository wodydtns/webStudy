package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품관리 Persistence Layer
 *
 */
public interface IProdDAO {
	public int insertProd(ProdVO prod);
	public ProdVO selectProd(String prod_id);
	public int selectProdCount(PagingVO pagingVO);
	public List<ProdVO> selectProdList(PagingVO pagingVO);
	public int updateProd(ProdVO prod);
	
}
