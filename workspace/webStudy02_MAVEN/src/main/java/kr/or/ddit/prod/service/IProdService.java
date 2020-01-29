package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * Business Logic Layer
 *
 */
public interface IProdService {
	public ServiceResult createProd(ProdVO prod);
	public ProdVO readProd(String prod_id);
	public int readProdCount(PagingVO pagingVO);
	public List<ProdVO> readProdList(PagingVO pagingVO);
	public ServiceResult modifyProd(ProdVO prod);
}
















