package stockmarket.model;

public class StockDataModel {
	
	private String stock;
	private String type;
	private int lastDividend;
	private int fixedDividend;
	private int perValue;
	
	public StockDataModel(String stock, String type, int lastDividend, int perValue) {
		super();
		this.stock = stock;
		this.type = type;
		this.lastDividend = lastDividend;
		this.perValue = perValue;
	}

	public int getFixedDividend() {
		return fixedDividend;
	}

	public StockDataModel setFixedDividend(int fixedDividend) {
		this.fixedDividend = fixedDividend;
		return this;
	}

	public String getStock() {
		return stock;
	}

	public String getType() {
		return type;
	}

	public int getLastDividend() {
		return lastDividend;
	}

	public int getPerValue() {
		return perValue;
	}
	
	
	

	
}