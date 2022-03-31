package Wines.model;

import java.util.Date;



public class UserRatings {

	protected int userRaingId;
	protected int rating;
	protected Users users;
	protected Wines wines;
	public UserRatings(int userRaingId, int rating, Users users, Wines wines) {
		super();
		this.userRaingId = userRaingId;
		this.rating = rating;
		this.users = users;
		this.wines = wines;
	}
	
	public UserRatings(int rating, Users users, Wines wines) {
		super();
		this.rating = rating;
		this.users = users;
		this.wines = wines;
	}

	public int getUserRaingId() {
		return userRaingId;
	}

	public void setUserRaingId(int userRaingId) {
		this.userRaingId = userRaingId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Wines getWines() {
		return wines;
	}

	public void setWines(Wines wines) {
		this.wines = wines;
	}
	
	
	
	
}
