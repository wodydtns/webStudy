package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.ProdVO2;


/**
 * 상품관리 Persistance Layer
 * @author pc04
 *
 */
public interface IProdDAO {
	public int insertProd(ProdVO2 prod);
	
	public ProdVO2 selectProd(String prod_id);
	
	public List<ProdVO> selectProdList();
	
	public int updateProd(ProdVO2 prod);
	
	
}
