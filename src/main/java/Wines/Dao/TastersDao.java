package Wines.Dao;

import Wines.model.*;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TastersDao {
	protected ConnectionManager connectionManager;

	private static TastersDao instance = null;
	protected TastersDao() {
		connectionManager = new ConnectionManager();
	}
	public static TastersDao getInstance() {
		if(instance == null) {
			instance = new TastersDao();
		}
		return instance;
	}

	public Tasters create(Tasters tasters) throws SQLException {
		String insertTaster =
			"INSERT INTO Taster(TasterName, TasterTwitter) " +
			"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			
			insertStmt = connection.prepareStatement(insertTaster,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, tasters.getTasterName());
			insertStmt.setString(3, tasters.getTasterTwitter());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int tasterId = -1;
			if(resultKey.next()) {
				tasterId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			tasters.setTasterId(tasterId);
			return tasters;
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
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}

	/**
	 * Update the content of the BlogPosts instance.
	 * This runs a UPDATE statement.
	 */
	public Tasters updateTwitter(Tasters taster, String newTwitter) throws SQLException {
		String updateTaster = "UPDATE Tasters SET TasterTwitter=? WHERE TasterId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateTaster);
			updateStmt.setString(1, newTwitter);
			// Sets the Created timestamp to the current time.
			
			updateStmt.setInt(2, taster.getTasterId());
			updateStmt.executeUpdate();

			// Update the blogPost param before returning to the caller.
			taster.setTasterTwitter(newTwitter);
			
			return taster;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	
	public Tasters delete(Tasters taster) throws SQLException {
		
		String deleteTaster = "DELETE FROM Tasters WHERE TasterId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteTaster);
			deleteStmt.setInt(1, taster.getTasterId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the BlogPosts instance.
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

	/**
	 * Get the BlogPosts record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single BlogPosts instance.
	 * Note that we use BlogUsersDao to retrieve the referenced BlogUsers instance.
	 * One alternative (possibly more efficient) is using a single SELECT statement
	 * to join the BlogPosts, BlogUsers tables and then build each object.
	 */
	public Tasters getTasterByTasterName(String tasterName) throws SQLException {
		String selectTaster =
			"SELECT TasterName, TasterId,TasterTwitter FROM Tasters WHERE TasterName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTaster);
			selectStmt.setString(1, tasterName);
			results = selectStmt.executeQuery();
			TastersDao tasterDao = TastersDao.getInstance();
			if(results.next()) {
//				String tastername = results.getString("TasterName");
				int resultTasterId = results.getInt("TasterId");
				
				String TasterTwitter = results.getString("TasterTwitter");
				
				
				Tasters taster = new Tasters(tasterName,resultTasterId, TasterTwitter);
				
				return taster;
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

	
}
