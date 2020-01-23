package kr.or.ddit.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Factory Object[Method] Pattern
 * 객체를 대신 생성해주는 패턴
 * @author pc04
 *
 */
public class ConnectionFactory {
	static String url; 
	static String user;
	static String password;
	static DataSource dataSource;
	//2. driver class loading
	static	{
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.db.dbInfo"); //getBundle(basename); basename 
		String driverClassName = bundle.getString("driverClassName");
		url = bundle.getString("url");
		user = bundle.getString("user");
		password = bundle.getString("password");
//		try {
//		Class.forName(driverClassName);
//	} catch (ClassNotFoundException e1) {
//		throw new RuntimeException(e1);
//		}
		// connection을 대신 생성해주는 방법 - pooling 시스템
		BasicDataSource bds = new BasicDataSource();
		dataSource = bds;
		bds.setDriverClassName(driverClassName);
		bds.setUrl(url);
		bds.setUsername(user);
		bds.setPassword(password);
		int initialSize = Integer.parseInt(bundle.getString("initialSize"));
		int maxTotal = Integer.parseInt(bundle.getString("maxTotal"));
		Long maxWait = Long.parseLong(bundle.getString("maxWait"));
		bds.setInitialSize(initialSize);
		bds.setMaxTotal(maxTotal);
		bds.setMaxWaitMillis(maxWait);
	}
	public static Connection getConnection() throws SQLException {
		
		return dataSource.getConnection();
	}
}
