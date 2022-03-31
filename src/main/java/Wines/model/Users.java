package Wines.model;

public class Users extends Person{
	
	private String password;
	private String email;
	private String phone;
	
	
	
	
	



	public Users(String userName, String firstName, String lastName, String password, String email, String phone) {
		super(userName, firstName, lastName);
		this.password = password;
		this.email = email;
		this.phone = phone;
	}
	

	
	
	








	/**
	 * @param userName
	 * @param firstName
	 * @param lastName
	 */
	public Users(String userName, String firstName, String lastName) {
		super(userName, firstName, lastName);
	}













	@Override
	public String toString() {
		return "Users [password=" + password + ", email=" + email + ", phone=" + phone + "]";
	}











	public String getPassword() {
		return password;
	}










	public void setPassword(String password) {
		this.password = password;
	}










	public String getEmail() {
		return email;
	}










	public void setEmail(String email) {
		this.email = email;
	}










	public String getPhone() {
		return phone;
	}










	public void setPhone(String phone) {
		this.phone = phone;
	}










	public Users(String userName) {
		super(userName);
	}
	
	
	
	
	
}
