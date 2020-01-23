package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ProdVO2;
import kr.or.ddit.vo.ProdVO;

/**
 * Business Logic Layer
 * @author pc04
 *
 */
public interface IProdService {
	public ServiceResult createProd(ProdVO prod);
	public ProdVO readProd(String prod_id);
	public List<ProdVO> readProdList();
	public ServiceResult modifyProd(ProdVO prod);
	
}
