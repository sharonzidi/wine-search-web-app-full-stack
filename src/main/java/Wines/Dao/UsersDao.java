package Wines.Dao;

import Wines.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Wines.model.Users;


/**
 * Data access object (DAO) class to interact with the underlying Users table in your
 * MySQL instance. This is used to store {@link Users} into your MySQL instance and 
 * retrieve {@link Users} from MySQL instance.
 */
public class UsersDao extends PersonDao {
	// Single pattern: instantiation is limited to one object.
	private static UsersDao instance = null;
	protected UsersDao() {
		super();
	}
	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}

	public Users create(Users user) throws SQLException {
		// Insert into the superclass table first.
		create(new Person(user.getUserName(), user.getFirstName(),
			user.getLastName()));

		String insertUser = "Insert into Users (UserName,Password,FirstName,LastName,Email,PhoneNum) values (?,?,?,?,?,?);;";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);
			insertStmt.setString(1, user.getUserName());
			insertStmt.setString(2, user.getPassword());
			insertStmt.setString(3, user.getFirstName());
			insertStmt.setString(4, user.getLastName());
			insertStmt.setString(5, user.getEmail());
			insertStmt.setString(6, user.getPhone());
			
			
			insertStmt.executeUpdate();
			return user;
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

	

	/**
	 * Delete the Users instance.
	 * This runs a DELETE statement.
	 */
	public Users delete(Users user) throws SQLException {
		String deleteBlogUser = "DELETE FROM Users WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBlogUser);
			deleteStmt.setString(1, user.getUserName());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for UserName=" + user.getUserName());
			}

			// Then also delete from the superclass.
			// Notes:
			// 1. Due to the fk constraint (ON DELETE CASCADE), we could simply call
			//    super.delete() without even needing to delete from Administrators first.
			// 2. BlogPosts has a fk constraint on Users with the reference option
			//    ON DELETE SET NULL. If the BlogPosts fk reference option was instead
			//    ON DELETE RESTRICT, then the caller would need to delete the referencing
			//    BlogPosts before this BlogUser can be deleted.
			//    Example to delete the referencing BlogPosts:
			//    List<BlogPosts> posts = BlogPostsDao.getBlogPostsForUser(blogUser.getUserName());
			//    for(BlogPosts p : posts) BlogPostsDao.delete(p);
			super.delete(user);

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

	public Users getUserFromUserName(String userName) throws SQLException {
		// To build an BlogUser object, we need the Person record, too.
		String selectUser =
			"SELECT Users.UserName AS UserName, Password, Users.FirstName, Users.LastName, Users.Email, Users.PhoneNum " +
			"FROM Users INNER JOIN Person " +
			"  ON Users.UserName = Person.UserName " +
			"WHERE Users.UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String password = results.getString("Password");
				String email = results.getString("Email");
				String phoneNum = results.getString("PhoneNum");
				Users user = new Users(resultUserName, firstName, lastName, password, email, phoneNum);
				return user;
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
	
	public Users updateDetails(Users user, String newEmail) throws SQLException {
		  String updateUser = "UPDATE Users SET Email=? WHERE UserName=?;";
		  Connection connection = null;
		  PreparedStatement updateStmt = null;
		  try {
		   connection = connectionManager.getConnection();
		   updateStmt = connection.prepareStatement(updateUser);
		   updateStmt.setString(1, newEmail);
		   updateStmt.setString(2, user.getUserName());
		   updateStmt.executeUpdate();
		   
		   // Update the person param before returning to the caller.
		   user.setEmail(newEmail);
		   return user;
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

}
