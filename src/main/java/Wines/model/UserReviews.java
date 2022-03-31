package Wines.model;


import java.util.Date;

public class UserReviews {
	protected int UserReviewId;
	protected Date Created;
	protected String Content;
	protected Users Users;
	protected Wines Wines;
	
	
	/**
	 * @param userReviewId
	 * @param created
	 * @param content
	 * @param user
	 * @param wine
	 */
	public UserReviews(int userReviewId, Date created, String content, Users user, Wines wine) {
		this.UserReviewId = userReviewId;
		this.Created = created;
		this.Content = content;
		this.Users = user;
		this.Wines = wine;
	}
	
	public UserReviews(Date created, String content, Users user, Wines wine) {
		this.Created = created;
		this.Content = content;
		this.Users = user;
		this.Wines = wine;
	}
	
	public UserReviews(int userReviewId) {
		this.UserReviewId = userReviewId;
	}
	
	/**
	 * @return the userReviewId
	 */
	public int getUserReviewId() {
		return this.UserReviewId;
	}

	/**
	 * @param userReviewId the userReviewId to set
	 */
	public void setUserReviewId(int userReviewId) {
		this.UserReviewId = userReviewId;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return this.Created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.Created = created;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return this.Content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.Content = content;
	}

	/**
	 * @return the userName
	 */
	public Users getUser() {
		return this.Users;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUser(Users user) {
		this.Users = user;
	}

	@Override
	public String toString() {
		return "UserReviews [UserReviewId=" + UserReviewId + ", Created=" + Created + ", Content=" + Content
				+ ", Users=" + Users + ", Wines=" + Wines + "]";
	}

	/**
	 * @return the wineTitle
	 */
	public Wines getWine() {
		return this.Wines;
	}

	/**
	 * @param wineTitle the wineTitle to set
	 */
	public void setWine(Wines wine) {
		this.Wines = wine;
	}
}
