package Wines.model;

public class Wines {
	
	private String wineTitle;
	private int wineId;
	private String country;
	private double price;
	private String designation;
	private Winery winery;
	public Wines(String wineTitle, int wineId, String country, double price, String designation, Winery winery) {
		super();
		this.wineTitle = wineTitle;
		this.wineId = wineId;
		this.country = country;
		this.price = price;
		this.designation = designation;
		this.winery = winery;
	}
	public Wines(String wineTitle) {
		super();
		this.wineTitle = wineTitle;
	}
	public Wines(int wineId, String country, double price, String designation, Winery winery) {
		super();
		this.wineId = wineId;
		this.country = country;
		this.price = price;
		this.designation = designation;
		this.winery = winery;
	}
	public String getWineTitle() {
		return wineTitle;
	}
	public void setWineTitle(String wineTitle) {
		this.wineTitle = wineTitle;
	}
	public int getWineId() {
		return wineId;
	}
	public void setWineId(int wineId) {
		this.wineId = wineId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Winery getWinery() {
		return winery;
	}
	public void setWinery(Winery winery) {
		this.winery = winery;
	}
	@Override
	public String toString() {
		return "Wines [wineTitle=" + wineTitle + ", wineId=" + wineId + ", country=" + country + ", price=" + price
				+ ", designation=" + designation + "]";
	}
	
	
	
	
	
	
	
	
	
	

}
