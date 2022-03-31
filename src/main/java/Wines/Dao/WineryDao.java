package Wines.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Wines.model.*;

public class WineryDao {
	// Single pattern: instantiation is limited to one object.
	protected ConnectionManager connectionManager;
	private static WineryDao instance = null;
	protected WineryDao() {
		connectionManager = new ConnectionManager();
	}
	public static WineryDao getInstance() {
		if(instance == null) {
			instance = new WineryDao();
		}
		return instance;
	}
	
	public Winery create(Winery winery) throws SQLException {
		String insertWinery = "INSERT INTO winery(WineryName) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertWinery);
			insertStmt.setString(1, winery.getWineryName());
			insertStmt.executeUpdate();
			return winery;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	public Winery getWineryByWineryName(String wineryName) throws SQLException {
		String selectWinery = "SELECT WineryName FROM Winery WHERE WineryName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWinery);
			selectStmt.setString(1, wineryName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultWineryName = results.getString("WineryName");
				Winery winery = new Winery(wineryName);
				return winery;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	public Winery delete(Winery winery) throws SQLException {
		String deleteWinery = "DELETE FROM winery WHERE WineryName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteWinery);
			deleteStmt.setString(1, winery.getWineryName());
			deleteStmt.executeUpdate();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
