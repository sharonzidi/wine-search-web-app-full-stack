package Wines.model;

public class ProfessionalRatings {
	
	protected int professionalRaingId;
	protected int rating;
	protected Tasters taster;
	protected Wines wine;
	public ProfessionalRatings(int professionalRaingId, int rating, Tasters taster, Wines wine) {
		super();
		this.professionalRaingId = professionalRaingId;
		this.rating = rating;
		this.taster = taster;
		this.wine = wine;
	}
	
	public ProfessionalRatings( int rating, Tasters taster, Wines wine) {
		super();
		this.rating = rating;
		this.taster = taster;
		this.wine = wine;
	}

	public int getProfessionalRaingId() {
		return professionalRaingId;
	}

	public void setProfessionalRaingId(int professionalRaingId) {
		this.professionalRaingId = professionalRaingId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Tasters getTaster() {
		return taster;
	}

	public void setTaster(Tasters taster) {
		this.taster = taster;
	}

	public Wines getWine() {
		return wine;
	}

	public void setWine(Wines wine) {
		this.wine = wine;
	}
	
	
	
	

}
