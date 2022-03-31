package Wines.Dao;
import Wines.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;


public class UserReviewsDao {
	protected ConnectionManager connectionManager;
	 
	 // Single pattern: instantiation is limited to one object.
	 private static UserReviewsDao instance = null;
	 protected UserReviewsDao() {
	  connectionManager = new ConnectionManager();
	 }
	 public static UserReviewsDao getInstance() {
	  if(instance == null) {
	   instance = new UserReviewsDao();
	  }
	  return instance;
	 }

	 /**
	  * Save the Users instance by storing it in your MySQL instance.
	  * This runs a INSERT statement.
	  */
	 public UserReviews create(UserReviews userReview) throws SQLException {
	  String insertUserReview = "INSERT INTO UserReviews(UserReviewId, Created, Content, UserName, WineTitle) VALUES(?,?,?,?,?);";
	  Connection connection = null;
	  PreparedStatement insertStmt = null;
	  try {
	   connection = connectionManager.getConnection();
	   insertStmt = connection.prepareStatement(insertUserReview);
	   // PreparedStatement allows us to substitute specific types into the query template.
	   // For an overview, see:
	   // http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
	   // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
	   // For nullable fields, you can check the property first and then call setNull()
	   // as applicable.
	   insertStmt.setInt(1, userReview.getUserReviewId());
	   insertStmt.setTimestamp(2, new Timestamp(userReview.getCreated().getTime()));
	   insertStmt.setString(3, userReview.getContent());
	   insertStmt.setString(4, userReview.getUser().getUserName());
	   insertStmt.setString(5, userReview.getWine().getWineTitle());
	   
	   // Note that we call executeUpdate(). This is used for a INSERT/UPDATE/DELETE
	   // statements, and it returns an int for the row counts affected (or 0 if the
	   // statement returns nothing). For more information, see:
	   // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
	   // I'll leave it as an exercise for you to write UPDATE/DELETE methods.
	   insertStmt.executeUpdate();
	   
	   // Note 1: if this was an UPDATE statement, then the person fields should be
	   // updated before returning to the caller.
	   // Note 2: there are no auto-generated keys, so no update to perform on the
	   // input param person.
	   return userReview;
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
	 
	 public UserReviews getUserReviewFromUserReviewId(int userReviewId) throws SQLException {
	  String selectUserReview = "SELECT UserReviewId, Created, Content, UserName, WineTitle FROM UserReviews WHERE UserReviewId=?;";
	  Connection connection = null;
	  PreparedStatement selectStmt = null;
	  ResultSet results = null;
	  try {
	   connection = connectionManager.getConnection();
	   selectStmt = connection.prepareStatement(selectUserReview);
	   selectStmt.setInt(1, userReviewId);
	   // Note that we call executeQuery(). This is used for a SELECT statement
	   // because it returns a result set. For more information, see:
	   // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
	   // http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
	   results = selectStmt.executeQuery();
	   // You can iterate the result set (although the example below only retrieves 
	   // the first record). The cursor is initially positioned before the row.
	   // Furthermore, you can retrieve fields by name and by type.
	   WinesDao winesDao = WinesDao.getInstance();
	   UsersDao userDao = UsersDao.getInstance();
	   if(results.next()) {
	    int user_review_id = results.getInt(userReviewId);
	    Date created = new Date(results.getTimestamp("Created").getTime());
	    String content = results.getString("Content");
	    String username = results.getString("UserName");
	    String wine_title = results.getString("WineTitle");
	    
	    Users user = userDao.getUserFromUserName(username);
	    Wines wines = winesDao.getWinesByTitle(wine_title);
	    UserReviews userReview = new UserReviews(user_review_id,created, content, user,wines);
	    return userReview;
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
	 
	 
	 
	 public UserReviews delete(UserReviews userReview)throws SQLException {
	  String deleteUserReview = "DELETE FROM UserReviews WHERE UserReviewId=?;";
	  Connection connection = null;
	  PreparedStatement deleteStmt = null;
	  try {
	   connection = connectionManager.getConnection();
	   deleteStmt = connection.prepareStatement(deleteUserReview);
	   deleteStmt.setInt(1, userReview.getUserReviewId());
	   deleteStmt.executeUpdate();

	   // Return null so the caller can no longer operate on the Persons instance.
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
