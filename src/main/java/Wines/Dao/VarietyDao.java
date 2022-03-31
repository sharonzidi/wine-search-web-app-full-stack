package Wines.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Wines.model.*;


public class VarietyDao {
	private static VarietyDao instance = null;
	private ConnectionManager connectionManager;
	protected VarietyDao() {
		connectionManager = new ConnectionManager();
	}
	public static VarietyDao getInstance() {
		if(instance == null) {
			instance = new VarietyDao();
		}
		return instance;
	}
	
	public Variety create(Variety variety) throws SQLException {
		String insertVariety = "INSERT INTO Variety(Type, WineTitle) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertVariety);
			insertStmt.setString(1, variety.getType());
			insertStmt.setString(2, variety.getWineTitle().getWineTitle());
			insertStmt.executeUpdate();
			return variety;
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
	
	public Variety getVarietyByType(String typeName) throws SQLException {
		String selectVariety = "SELECT Type,WineTitle FROM Variety WHERE Type=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectVariety);
			selectStmt.setString(1, typeName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultType = results.getString("Type");
				String resultWineTitle = results.getString("WineTitle");
				
				Variety variety = new Variety(resultType, new Wines(resultWineTitle));
				return variety;
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
	
	public Variety delete(Variety variety) throws SQLException {
		String deleteVariety = "DELETE FROM Variety WHERE Type=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteVariety);
			deleteStmt.setString(1, variety.getType());
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
