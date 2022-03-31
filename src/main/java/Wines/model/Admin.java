package Wines.model;

import java.util.Date;

/**
 * Administrators is a simple, plain old java objects (POJO).
 * Well, almost (it extends {@link Person}).
 */
public class Admin extends Person {
	protected String password;
	protected Date lastLogin;
	
	public Admin(String userName, String firstName, String lastName, String password, Date lastLogin) {
		super(userName, firstName, lastName);
		this.password = password;
		this.lastLogin = lastLogin;
	}
	
	public Admin(String userName) {
		super(userName);
	}

	/** Getters and setters. */
	
	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}	
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Admin [password=" + password + ", lastLogin=" + lastLogin + "]";
	}
	
	

}
