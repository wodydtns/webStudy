package kr.or.ddit.web06_model2.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.DataBasePropertyVO;
import kr.or.ddit.web06_model2.dao.DataBasePropertyDAOImpl;
import kr.or.ddit.web06_model2.dao.IDataBasePropertyDAO;


//서비스 객체가 dao에 의존성을 가진다.
public class DataBasePropertyServiceImpl implements IDataBasePropertyService{
	IDataBasePropertyDAO dao = new DataBasePropertyDAOImpl();

	@Override
	public List<DataBasePropertyVO> readDataBasePropertyList(Map<String, Object> model) {
		List<DataBasePropertyVO> list = dao.selectDataBasePropertyList(model);
		for(DataBasePropertyVO vo:list) {
			
			
			vo.setProperty_value(vo.getProperty_value().toLowerCase());
		}
		
		return list;
	}
}
