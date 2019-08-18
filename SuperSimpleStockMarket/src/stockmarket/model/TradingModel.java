package stockmarket.model;

public class TradingModel {
	
	
	private String stock;
	private int shareQuantity;
	private long tradeTime;
	private String buySell;
	private double tradePrice;
	
	
	public String getStock() {
		return stock;
	}
	public TradingModel setStock(String stock) {
		this.stock = stock;
		return this;
	}
	public int getShareQuantity() {
		return shareQuantity;
	}
	public TradingModel setShareQuantity(int shareQuantity) {
		this.shareQuantity = shareQuantity;
		return this;
	}
	public long getTradeTime() {
		return tradeTime;
	}
	public TradingModel setTradeTime(long tradeTime) {
		this.tradeTime = tradeTime;
		return this;
	}
	public String getBuySell() {
		return buySell;
	}
	public TradingModel setBuySell(String buySell) {
		this.buySell = buySell;
		return this;
	}
	public double getTradePrice() {
		return tradePrice;
	}
	public TradingModel setTradePrice(double tradePrice) {
		this.tradePrice = tradePrice;
		return this;
	}	

}
