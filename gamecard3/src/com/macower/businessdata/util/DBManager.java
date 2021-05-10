package com.macower.businessdata.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.macower.businessdata.biz.Configuration;

public class DBManager {

	protected Logger log = LoggerFactory.getLogger(getClass());

	private Connection conn = null;
	private ResultSet rs = null;
	private Statement stmt = null;

	public Connection getConnection() throws SQLException, IOException,
			Exception {
		Configuration rc = new Configuration(Configuration.class.getResource(
				"/").getPath()
				+ "jdbc.properties");

		String drivers = rc.getValue("jdbc.driver");
		String url = rc.getValue("jdbc.url");
		String username = rc.getValue("jdbc.username");
		String password = rc.getValue("jdbc.password");
		Class.forName(drivers);
		return DriverManager.getConnection(url, username, password);
	}

	public DBManager() {
		try {
			conn = getConnection();
			stmt = conn.createStatement();  
			System.out.println("Connect Database is Ok!");
		} catch (Exception e) {
			log.error("Connect Database Failed!:" + e);
		}
	}

	public ResultSet executeQuery(String sqlwords) {
		try {
			log.info(sqlwords) ; 
			rs = stmt.executeQuery(sqlwords);
		} catch (SQLException ex) {
			log.error("Execute Query Sql Failed!:" + ex.getMessage());
		}
		return rs;
	}

	public boolean executeUpdate(String sqlwords) {
		try {
			log.info(sqlwords) ;
			stmt.executeUpdate(sqlwords);
			return true;
		} catch (SQLException ex) {
			log.error("Execute Update Sql Failed!: " + ex.getMessage());
			return false;
		}
	}

	public boolean close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
			return true;
		} catch (Exception e) {
			log.error("Clost Database Connect Failed!:" + e);
			return false;
		}
	}
}
