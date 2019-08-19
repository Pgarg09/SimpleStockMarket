package stockmarket.model;

public class StockDataModel {
	
	private String stock;
	private String type;
	private double lastDividend;
	private int fixedDividend;
	private int perValue;
	private double price;
	private double dividend;
	
	public StockDataModel(String stock, String type, double lastDividend, int perValue) {
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

	public double getLastDividend() {
		return lastDividend;
	}

	public int getPerValue() {
		return perValue;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDividend() {
		return dividend;
	}

	public void setDividend(double dividend) {
		this.dividend = dividend;
	}
	
}