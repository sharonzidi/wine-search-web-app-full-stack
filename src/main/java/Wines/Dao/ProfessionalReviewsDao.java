package Wines.Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import Wines.model.*;


public class ProfessionalReviewsDao {
	protected ConnectionManager connectionManager;
	 
	 // Single pattern: instantiation is limited to one object.
	 private static ProfessionalReviewsDao instance = null;
	 protected ProfessionalReviewsDao() {
	  connectionManager = new ConnectionManager();
	 }
	 public static ProfessionalReviewsDao getInstance() {
	  if(instance == null) {
	   instance = new ProfessionalReviewsDao();
	  }
	  return instance;
	 }

	 /**
	  * Save the Users instance by storing it in your MySQL instance.
	  * This runs a INSERT statement.
	  */
	 public ProfessionalReviews create(ProfessionalReviews professionalReview) throws SQLException {
	  String insertProfessionalReview = "INSERT INTO ProfessionalReviews(ProfessionalReviewsId, Created, Content, TasterName, WineTitle) VALUES(?,?,?,?,?);";
	  Connection connection = null;
	  PreparedStatement insertStmt = null;
	  try {
	   connection = connectionManager.getConnection();
	   insertStmt = connection.prepareStatement(insertProfessionalReview);
	   // PreparedStatement allows us to substitute specific types into the query template.
	   // For an overview, see:
	   // http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
	   // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
	   // For nullable fields, you can check the property first and then call setNull()
	   // as applicable.
	   insertStmt.setInt(1, professionalReview.getProfessionalReviewId());
	   insertStmt.setTimestamp(2, new Timestamp(professionalReview.getCreated().getTime()));
	   insertStmt.setString(3, professionalReview.getContent());
	   insertStmt.setString(4, professionalReview.getTaster().getTasterName());
	   insertStmt.setString(5, professionalReview.getWine().getWineTitle());
	   
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
	   return professionalReview;
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
	 
	 public ProfessionalReviews getProfessionalReviewFromProfessionalReviewId(int professionalReviewId) throws SQLException {
	  String selectProfessionalReview = "SELECT ProfessionalReviewsId, Created, Content, TasterName, WineTitle FROM ProfessionalReviews WHERE ProfessionalReviewsId=?;";
	  Connection connection = null;
	  PreparedStatement selectStmt = null;
	  ResultSet results = null;
	  try {
	   connection = connectionManager.getConnection();
	   selectStmt = connection.prepareStatement(selectProfessionalReview);
	   selectStmt.setInt(1, professionalReviewId);
	   // Note that we call executeQuery(). This is used for a SELECT statement
	   // because it returns a result set. For more information, see:
	   // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
	   // http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
	   results = selectStmt.executeQuery();
	   // You can iterate the result set (although the example below only retrieves 
	   // the first record). The cursor is initially positioned before the row.
	   // Furthermore, you can retrieve fields by name and by type.
	   TastersDao tasersDao = TastersDao.getInstance();
		WinesDao winesDao = WinesDao.getInstance();
	   
	   if(results.next()) {
	
	    Date created = new Date(results.getTimestamp("Created").getTime());
	    String content = results.getString("Content");
	    String taster_name = results.getString("TasterName");
	    Tasters tasters = tasersDao.getTasterByTasterName(taster_name);
	    String wine_title = results.getString("WineTitle");
	    Wines wines = winesDao.getWinesByTitle(wine_title);
	    ProfessionalReviews professionalReview = new ProfessionalReviews(professionalReviewId,created, content, tasters, wines);
	    return professionalReview;
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
	 
	 
	 
	 public ProfessionalReviews delete(ProfessionalReviews professionalReview)throws SQLException {
	  String deleteProfessionalReview = "DELETE FROM ProfessionalReviews WHERE ProfessionalReviewsId=?;";
	  Connection connection = null;
	  PreparedStatement deleteStmt = null;
	  try {
	   connection = connectionManager.getConnection();
	   deleteStmt = connection.prepareStatement(deleteProfessionalReview);
	   deleteStmt.setInt(1, professionalReview.getProfessionalReviewId());
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
