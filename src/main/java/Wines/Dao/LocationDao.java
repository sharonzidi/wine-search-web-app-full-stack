package Wines.Dao;

import Wines.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LocationDao {
	protected ConnectionManager connectionManager;

	private static LocationDao instance = null;
	protected LocationDao() {
		connectionManager = new ConnectionManager();
	}
	public static LocationDao getInstance() {
		if(instance == null) {
			instance = new LocationDao();
		}
		return instance;
	}


	public Location updateLocation(Location location, String newCountry, String newProvince, String newRegin1, String newRegin2, String newWineTitle) throws SQLException {
		String updateLocation = "UPDATE Locations SET Country=?, Province=?, Regin1=?, Regin2=? WHERE WineTitle=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateLocation);
			updateStmt.setString(1, newCountry);
			updateStmt.setString(2, newProvince);
			updateStmt.setString(3, newRegin1);
			updateStmt.setString(4, newRegin2);
			updateStmt.setString(5, location.getWineTitle().toString());
			updateStmt.executeUpdate();

			location.setCountry(newCountry);
			location.setProvince(newProvince);;
			location.setRegin1(newRegin1);;
			location.setRegin2(newRegin2);;
			location.setWineTitle(new Wines(newWineTitle));
			
			return location;
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



	public Location getLocationByWineTitle(String wineTitle) throws SQLException {
		String selectLocation =
			"SELECT Country, Province, Regin1, Regin2 FROM Locations WHERE WineTitle=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		WinesDao wineDao =  WinesDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLocation);
			selectStmt.setString(1, wineTitle);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String country = results.getString("Country");
				String province = results.getString("Province");
				String regin1 = results.getString("Regin1");
				String regin2 = results.getString("Regin2");
//				String resultWineTitle = results.getString("WineTitle");
				Wines w1 = wineDao.getWinesByTitle(wineTitle);
				Location location = new Location( w1, country, province, regin1, regin2);
				return location;
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