package Wines.model;



/**
 * BlogUsers is a simple, plain old java objects (POJO).
 * Well, almost (it extends {@link Persons}).
 */
public class Tasters {
	protected String TasterName;
	protected String TasterTwitter;
	protected int TasterId;
	
	public Tasters(String TasterName, int TasterId, String TasterTwitter) {
		this.TasterName = TasterName;
		this.TasterId = TasterId;
		this.TasterTwitter = TasterTwitter;
	}
	
	public Tasters(String TasterName, String TasterTwitter) {
		this.TasterName = TasterName;
		this.TasterTwitter = TasterTwitter;
	}
	
	public Tasters(int TasterId) {
		this.TasterId = TasterId;
		
	}

	/**
	 * @return the tasterName
	 */
	public String getTasterName() {
		return TasterName;
	}

	/**
	 * @param tasterName the tasterName to set
	 */
	public void setTasterName(String tasterName) {
		TasterName = tasterName;
	}

	/**
	 * @return the tasterTwitter
	 */
	public String getTasterTwitter() {
		return TasterTwitter;
	}

	/**
	 * @param tasterTwitter the tasterTwitter to set
	 */
	public void setTasterTwitter(String tasterTwitter) {
		TasterTwitter = tasterTwitter;
	}

	/**
	 * @return the tasterId
	 */
	public int getTasterId() {
		return TasterId;
	}

	/**
	 * @param tasterId the tasterId to set
	 */
	public void setTasterId(int tasterId) {
		TasterId = tasterId;
	}
		
	
	

	
}
