package Wines.model;

public class Variety {
	protected String type;
	protected Wines wineTitle;

	public Variety(String type, Wines wineTitle) {
		this.type = type;
		this.wineTitle = wineTitle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Wines getWineTitle() {
		return wineTitle;
	}

	public void setWineTitle(Wines wineTitle) {
		this.wineTitle = wineTitle;
	}
	
	
}
