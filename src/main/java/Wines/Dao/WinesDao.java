package Wines.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Wines.model.Winery;
import Wines.model.Wines;

public class WinesDao {
protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static WinesDao instance = null;
	protected WinesDao() {
		connectionManager = new ConnectionManager();
	}
	public static WinesDao getInstance() {
		if(instance == null) {
			instance = new WinesDao();
		}
		return instance;
	}
	
	
	
	public Wines getWinesByTitle(String title) throws SQLException {
		String selectWine ="SELECT WineTitle,WineId,Country,Price,Designation,WineryName FROM Wines WHERE WineTitle=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectWine);
				selectStmt.setString(1, title);
				results = selectStmt.executeQuery();
				WineryDao wineryDao=WineryDao.getInstance();
				if(results.next()) {
					
					String wineTitle = results.getString("WineTitle");
					int wineId=results.getInt("WineId");
					String country = results.getString("Country");
					Double price = results.getDouble("Price");
					
					String designation = results.getString("Designation");
					String wineryName = results.getString("WineryName");
					Winery winery=wineryDao.getWineryByWineryName(wineryName);
					Wines res=new Wines(wineTitle,wineId,country,price,designation,winery);
					return res;
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
	
	public List<Wines> getWinesByWineryName(String wineryNameForSeatch) throws SQLException{
		List<Wines> list=new ArrayList<>();
		String selectWine ="SELECT WineTitle,WineId,Country,Price,Designation,WineryName FROM Wines WHERE WineryName=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWine);
			selectStmt.setString(1, wineryNameForSeatch);
			results = selectStmt.executeQuery();
			WineryDao wineryDao=WineryDao.getInstance();
			while(results.next()) {
				
				String wineTitle = results.getString("WineTitle");
				int wineId=results.getInt("WineId");
				String country = results.getString("Country");
				Double price = results.getDouble("Price");
				
				String designation = results.getString("Designation");
				String wineryName = results.getString("WineryName");
				Winery winery=wineryDao.getWineryByWineryName(wineryName);
				Wines res=new Wines(wineTitle,wineId,country,price,designation,winery);
				list.add(res);
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
		
		return list;
	}
	
	public Wines delete(Wines wines) throws SQLException {
		String deleteWinery = "DELETE FROM Wines WHERE WineTitle=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteWinery);
			deleteStmt.setString(1, wines.getWineTitle());
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
