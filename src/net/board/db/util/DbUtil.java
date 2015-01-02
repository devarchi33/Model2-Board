package net.board.db.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbUtil {
	static Logger logger = LoggerFactory.getLogger(DbUtil.class);
	
	public Connection getconnection() {
		try {
			Connection conn = null;
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			logger.info("DB 연결 성공!!");
			return conn = ds.getConnection();			
		}catch(Exception e) {
			logger.info("DB 연결 실패 : " + e);
			return null;
		}
	}
	
	public void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.info("SQLException : " + e.getMessage());
			}
		}
	}

	public void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.info("SQLException : " + e.getMessage());
			}
		}
	}

	public void close(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				logger.info("SQLException : " + e.getMessage());
			}
		}
	}

	public void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				logger.info("SQLException : " + e.getMessage());
			}
		}
	}

	public void commit(Connection conn) {
		try {
			conn.commit();
			logger.info("commit success!!");
		} catch (Exception e) {
			logger.debug("Exception : " + e.getMessage());
		}
	}

	public void rollback(Connection conn) {
		try {
			conn.rollback();
			logger.info("rollback success!!");
		} catch (Exception e) {
			logger.debug("Exception : " + e.getMessage());
		}
	}
	
}
