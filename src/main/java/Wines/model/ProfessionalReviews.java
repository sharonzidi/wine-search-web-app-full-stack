package Wines.model;


import java.util.Date;

public class ProfessionalReviews {
	protected int ProfessionalReviewId;
	protected Date Created;
	protected String Content;
	protected Tasters Tasters;
	protected Wines Wines;
	
	@Override
	public String toString() {
		return "ProfessionalReviews [ProfessionalReviewId=" + ProfessionalReviewId + ", Created=" + Created
				+ ", Content=" + Content + ", Tasters=" + Tasters + ", Wines=" + Wines + "]";
	}

	/**
	 * @param professionalReviewId
	 * @param created
	 * @param content
	 * @param tasterName
	 * @param wineTitle
	 */
	public ProfessionalReviews(int professionalReviewId, Date created, String content, Tasters taster,
			Wines wine) {
		this.ProfessionalReviewId = professionalReviewId;
		this.Created = created;
		this.Content = content;
		this.Tasters = taster;
		this.Wines = wine;
	}
	
	public ProfessionalReviews(Date created, String content, Tasters taster,
			Wines wine) {
		this.Created = created;
		this.Content = content;
		this.Tasters = taster;
		this.Wines = wine;
	}
	
	public ProfessionalReviews(int professionalReviewId) {
		this.ProfessionalReviewId = professionalReviewId;
	}
	
	/**
	 * @return the professionalReviewId
	 */
	public int getProfessionalReviewId() {
		return this.ProfessionalReviewId;
	}

	/**
	 * @param professionalReviewId the professionalReviewId to set
	 */
	public void setProfessionalReviewId(int professionalReviewId) {
		this.ProfessionalReviewId = professionalReviewId;
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
	 * @return the taster
	 */
	public Tasters getTaster() {
		return this.Tasters;
	}

	/**
	 * @param taster the taster to set
	 */
	public void setTaster(Tasters taster) {
		this.Tasters = taster;
	}

	/**
	 * @return the wine
	 */
	public Wines getWine() {
		return this.Wines;
	}

	/**
	 * @param wine the wine to set
	 */
	public void setWine(Wines wine) {
		this.Wines = wine;
	}
}
