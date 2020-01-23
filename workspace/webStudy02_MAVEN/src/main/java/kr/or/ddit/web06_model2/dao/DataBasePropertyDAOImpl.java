package kr.or.ddit.web06_model2.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyDAOImpl implements IDataBasePropertyDAO {
	public List<DataBasePropertyVO> selectDataBasePropertyList(Map<String,Object> model){
		try(
				//3. Connection 생성
				Connection conn = ConnectionFactory.getConnection();
				
				//4. Query 객체
				Statement stmt = conn.createStatement();			
				){
				
				StringBuffer sql = new StringBuffer("SELECT property_name, property_value, description FROM database_properties");
				
				
				//5. Query 실행
				ResultSet rs = null;
				rs = stmt.executeQuery(sql.toString());
				
				ResultSetMetaData rsmd = rs.getMetaData();
				
				String[] headers = new String[rsmd.getColumnCount()];
				
				for(int idx=1;idx<=headers.length;idx++){
					headers[idx-1] = rsmd.getColumnName(idx);
				}
				
				//6. ResultSet 핸들링
				List<DataBasePropertyVO> list = new ArrayList<>();
				while(rs.next()){
					DataBasePropertyVO vo = new DataBasePropertyVO();
					list.add(vo);
					vo.setProperty_name(rs.getString("PROPERTY_NAME"));//TODO
					vo.setProperty_value(rs.getString(1));
					vo.setDescription(rs.getString("DESCRIPTION"));
				}
				// Map을 파라미터로 받아서 Map에 값을 넣어주기 
				// call by reference 구조 -> 
				model.put("headers",headers);
				model.put("list",list);
				return list;
			}catch(SQLException e){
				throw new RuntimeException(e);
			}
	}
	
}
