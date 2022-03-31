package Wines.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Wines.model.ProfessionalRatings;
import Wines.model.Tasters;
import Wines.model.UserRatings;
import Wines.model.Wines;

public class ProfessionalRatingsDao {
	protected ConnectionManager connectionManager;

	private static ProfessionalRatingsDao instance = null;
	protected ProfessionalRatingsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ProfessionalRatingsDao getInstance() {
		if(instance == null) {
			instance = new ProfessionalRatingsDao();
		}
		return instance;
	}

	public ProfessionalRatings create(ProfessionalRatings professionalRatings) throws SQLException {
		String insertProfessionalRatings =
			"INSERT INTO ProfessionalRatings(ProfessionalRatingsId,WineTitle,TasterName,Points) " +
			"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertProfessionalRatings,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, professionalRatings.getWine().getWineTitle());
			insertStmt.setString(2, professionalRatings.getTaster().getTasterName());
			insertStmt.setInt(3, professionalRatings.getRating());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int ratingId = -1;
			if(resultKey.next()) {
				ratingId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			professionalRatings.setProfessionalRaingId(ratingId);
			return professionalRatings;
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

	
	public ProfessionalRatings delete(ProfessionalRatings professionalRatings) throws SQLException {
		String deleteProfessionalRating = "DELETE FROM ProfessionalRatings WHERE ProfessionalRatingsId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteProfessionalRating);
			deleteStmt.setInt(1, professionalRatings.getProfessionalRaingId());
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

	
	public ProfessionalRatings getProfessionalRatingsById(int ratingId) throws SQLException {
		String selectProfessionalRatings =
			"SELECT ProfessionalRatingsId,WineTitle,TasterName,Points " +
			"FROM ProfessionalRatings " +
			"WHERE ProfessionalRatingsId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProfessionalRatings);
			selectStmt.setInt(1, ratingId);
			results = selectStmt.executeQuery();
			TastersDao tasersDao = TastersDao.getInstance();
			WinesDao winesDao = WinesDao.getInstance();
			if(results.next()) {
				int resultProfessionalRatingId = results.getInt("ProfessionalRatingsId");
				int rating = results.getInt("Points");
				String wineTitle = results.getString("WineTitle");
				String tasterName = results.getString("TasterName");
				
				
				Tasters tasters = tasersDao.getTasterByTasterName(tasterName);
				Wines wines = winesDao.getWinesByTitle(wineTitle);
				ProfessionalRatings professionalRatings = new ProfessionalRatings(resultProfessionalRatingId, rating, tasters, wines);
				return professionalRatings;
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

	
	public  List<ProfessionalRatings> getProfessionalRatingsByTasterName(String tasterName)  throws SQLException {
		List<ProfessionalRatings> professionalRatings = new ArrayList<ProfessionalRatings>();
		String selectProfessionalRatings =
			"SELECT ProfessionalRatingsId,WineTitle,TasterName,Points " +
			"FROM ProfessionalRatings " +
			"WHERE TasterName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProfessionalRatings);
			selectStmt.setString(1, tasterName);
			results = selectStmt.executeQuery();
			TastersDao tasersDao = TastersDao.getInstance();
			WinesDao winesDao = WinesDao.getInstance();
			while(results.next()) {
				int resultProfessionalRatingId = results.getInt("ProfessionalRatingsId");
				int rating = results.getInt("Points");
				String wineTitle = results.getString("WineTitle");
				String TasterName = results.getString("TasterName");

				Tasters tasters = tasersDao.getTasterByTasterName(tasterName);
				Wines wines = winesDao.getWinesByTitle(wineTitle);
				ProfessionalRatings professionalRating = new ProfessionalRatings(resultProfessionalRatingId, rating, tasters, wines);
				
				professionalRatings.add(professionalRating);
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
		return professionalRatings;
	}
	
	
	
	public List<ProfessionalRatings> getProfessionalRatingsByUserWineTitle(String wineTitle) throws SQLException {
		List<ProfessionalRatings> professionalRatings = new ArrayList<ProfessionalRatings>();
		String selectProfessionalRatings =
			"SELECT ProfessionalRatingsId,WineTitle,TasterName,Points " +
			"FROM ProfessionalRatings " +
			"WHERE WineTitle=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProfessionalRatings);
			selectStmt.setString(1, wineTitle);
			results = selectStmt.executeQuery();
			TastersDao tasersDao = TastersDao.getInstance();
			WinesDao winesDao = WinesDao.getInstance();
			while(results.next()) {
				int resultProfessionalRatingId = results.getInt("ProfessionalRatingsId");
				int rating = results.getInt("Points");
				String WineTitle = results.getString("WineTitle");
				String tasterName = results.getString("TasterName");

				Tasters tasters = tasersDao.getTasterByTasterName(tasterName);
				Wines wines = winesDao.getWinesByTitle(wineTitle);
				ProfessionalRatings professionalRating = new ProfessionalRatings(resultProfessionalRatingId, rating, tasters, wines);
				
				professionalRatings.add(professionalRating);
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
		return professionalRatings;
	}

}
