package Wines.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Wines.model.UserRatings;
import Wines.model.Users;
import Wines.model.Wines;


public class UserRatingsDao {
	protected ConnectionManager connectionManager;

	private static UserRatingsDao instance = null;
	protected UserRatingsDao() {
		connectionManager = new ConnectionManager();
	}
	public static UserRatingsDao getInstance() {
		if(instance == null) {
			instance = new UserRatingsDao();
		}
		return instance;
	}

	public UserRatings create(UserRatings userRatings) throws SQLException {
		String insertUserRatings =
			"INSERT INTO UserRatings(WineTitle,UserName,Points) " +
			"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUserRatings,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, userRatings.getWines().getWineTitle());
			insertStmt.setString(2, userRatings.getUsers().getUserName());
			insertStmt.setInt(3, userRatings.getRating());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int ratingId = -1;
			if(resultKey.next()) {
				ratingId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			userRatings.setUserRaingId(ratingId);
			return userRatings;
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

	
	public UserRatings delete(UserRatings userRatings) throws SQLException {
		String deleteUserRating = "DELETE FROM UserRatings WHERE UserRatingsId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUserRating);
			deleteStmt.setInt(1, userRatings.getUserRaingId());
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

	
	public UserRatings getUserRatingsById(int ratingId) throws SQLException {
		String selectUserRatings =
			"SELECT UserRatingsId,WineTitle,UserName,Points " +
			"FROM UserRatings " +
			"WHERE UserRatingsId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUserRatings);
			selectStmt.setInt(1, ratingId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			WinesDao winesDao = WinesDao.getInstance();
			if(results.next()) {
				int resultUserRatingId = results.getInt("UserRatingsId");
				int rating = results.getInt("Points");
				String wineTitle = results.getString("WineTitle");
				String userName = results.getString("UserName");
				
				
				Users user = usersDao.getUserFromUserName(userName);
				Wines wines = winesDao.getWinesByTitle(wineTitle);
				UserRatings userRatings = new UserRatings(resultUserRatingId, rating, user, wines);
				return userRatings;
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

	
	public  List<UserRatings> getUserRatingsByUserName(String userName)  throws SQLException {
		List<UserRatings> userRatings = new ArrayList<UserRatings>();
		String selectUserRatings =
			"SELECT UserRatingsId,WineTitle,UserName,Points " +
			"FROM UserRatings " +
			"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUserRatings);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			WinesDao winesDao = WinesDao.getInstance();
			while(results.next()) {
				int resultUserRatingId = results.getInt("UserRatingsId");
				int rating = results.getInt("Points");
				String wineTitle = results.getString("WineTitle");
				String UserName = results.getString("UserName");

				Users user = usersDao.getUserFromUserName(userName);
				Wines wines = winesDao.getWinesByTitle(wineTitle);
				UserRatings userRating = new UserRatings(resultUserRatingId, rating, user, wines);
				
				userRatings.add(userRating);
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
		return userRatings;
	}
	
	
	
	public List<UserRatings> getUserRatingsByUserWineTitle(String wineTitle) throws SQLException {
		List<UserRatings> userRatings = new ArrayList<UserRatings>();
		String selectUserRatings =
			"SELECT UserRatingsId,WineTitle,UserName,Points " +
			"FROM UserRatings " +
			"WHERE WineTitle=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUserRatings);
			selectStmt.setString(1, wineTitle);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			WinesDao winesDao = WinesDao.getInstance();
			while(results.next()) {
				int resultUserRatingId = results.getInt("UserRatingsId");
				int rating = results.getInt("Points");
				String WineTitle = results.getString("WineTitle");
				String userName = results.getString("UserName");

				Users user = usersDao.getUserFromUserName(userName);
				Wines wines = winesDao.getWinesByTitle(wineTitle);
				UserRatings userRating = new UserRatings(resultUserRatingId, rating, user, wines);
				userRatings.add(userRating);
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
		return userRatings;
	}


}
