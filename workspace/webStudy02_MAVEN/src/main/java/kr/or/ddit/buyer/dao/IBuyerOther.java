package kr.or.ddit.buyer.dao;

import java.util.List;
import java.util.Map;

public interface IBuyerOther {
	List<Map<String,Object>> selectBuyerName();
	List<Map<String,Object>> selectBuyerBank();
}
