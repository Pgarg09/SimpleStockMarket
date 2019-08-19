package stockmarket.services;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import stockmarket.exceptions.BusinessException;
import stockmarket.exceptions.InvalidValueException;
import stockmarket.model.GBCEDataModelSingleton;
import stockmarket.model.StockDataModel;
import stockmarket.model.TradingModel;
import stockmarket.util.StockMarketConstant;

public class StockService {
	
	private static StockService service = null;
	private DecimalFormat df = new DecimalFormat("#.##");
	private List<TradingModel> tradingModelList = new ArrayList<TradingModel>();
	private static final long FIFTEEN_MINUTES = 15 * 60 * 1000;
	
	private StockService(){
		
	}
	
	public static StockService getInstance() {
		if(service == null) {
			synchronized (StockService.class) {
				if(service == null) {
					service = new StockService();
				}
			}
		}
		return service;
	}
	
	public String calculateDividendYield(final String stock, final double price) {
		
		Double dividendValue;
		checkValidPrice(price);
		StockDataModel dataModel = validateAndGetStockModelObj(stock);
		if(dataModel.getType().equalsIgnoreCase(StockMarketConstant.STOCK_TYPE_COMMON)) {
				dividendValue = Double.parseDouble(df.format(dataModel.getLastDividend()/price));
				dataModel.setDividend(dividendValue);
				dataModel.setPrice(price);
				return dividendValue.toString();
		} else {
				dividendValue = Double.parseDouble(df.format((dataModel.getFixedDividend()*dataModel.getPerValue())/price));
				dataModel.setDividend(dividendValue);
				dataModel.setPrice(price);
				return dividendValue.toString();
		}
	}
	
	public String calculatePERatio(final String stock, final double price) {
		checkValidPrice(price);
		StockDataModel dataModel = validateAndGetStockModelObj(stock);
		if(dataModel.getDividend()>0) {
			dataModel.setPrice(price);
			return df.format(price/dataModel.getDividend());
		} else {
			dataModel.setPrice(price);
			return df.format(price/dataModel.getLastDividend());
		}
		
	}
	
	public boolean recordTrade(final String stock, final int quantity, final String buySell
			,final Date time, final double tradePrice) {
		validateAndGetStockModelObj(stock);
		checkValidPrice(tradePrice);
		checkBuySellIndicator(buySell);
		checkValidQuantity(quantity);
		TradingModel tradingModel = new TradingModel();
		tradingModel.setStock(stock).setShareQuantity(quantity).setBuySell(buySell).setTradePrice(tradePrice)
				.setTradeTime(time.getTime());
		tradingModelList.add(tradingModel);
		return true;
	}
	
	public String calVolWeightedStockPrice() {
		
		if(!tradingModelList.isEmpty()) {
			long fifteenMinAgo = System.currentTimeMillis() - FIFTEEN_MINUTES;
			List<TradingModel> filterModel = tradingModelList.stream().filter(i->i.getTradeTime()>fifteenMinAgo)
					.collect(Collectors.toList());
			if(!filterModel.isEmpty()) {
				double sumTradePriceAndQuantity = filterModel.stream().map(a->a.getShareQuantity()*a.getTradePrice())
						.reduce(0.0, Double::sum);
				int sumOfQuantity = filterModel.stream().map(i->i.getShareQuantity())
						.reduce(0, Integer::sum);
				return df.format(sumTradePriceAndQuantity/sumOfQuantity);
			} else {
				return "0.00";
			}
		} else {
			return "0.00";   
		}
	}
	
	public String getMeanOfPrices(){
		
		Collection<StockDataModel> stockDataModelCollection = GBCEDataModelSingleton.getStockDataModel().values();
		double meanOfPrices = stockDataModelCollection.stream().filter(i->i.getPrice()>0).
				map(a->a.getPrice()).reduce(1.0, (a,b)->a*b);
		return df.format(Math.sqrt(meanOfPrices));
	}
	
	
	private StockDataModel validateAndGetStockModelObj(final String stock) {
		StockDataModel stockDataModel = null;
		switch (stock) {
		case StockMarketConstant.STOCK_TEA:
			stockDataModel = GBCEDataModelSingleton.getStockDataModel().get(StockMarketConstant.STOCK_TEA);
			break;
		case StockMarketConstant.STOCK_POP:
			stockDataModel = GBCEDataModelSingleton.getStockDataModel().get(StockMarketConstant.STOCK_POP);
			break;
		case StockMarketConstant.STOCK_ALE:
			stockDataModel = GBCEDataModelSingleton.getStockDataModel().get(StockMarketConstant.STOCK_ALE);
			break;
		case StockMarketConstant.STOCK_GIN:
			stockDataModel = GBCEDataModelSingleton.getStockDataModel().get(StockMarketConstant.STOCK_GIN);
			break;
		case StockMarketConstant.STOCK_JOE:
			stockDataModel = GBCEDataModelSingleton.getStockDataModel().get(StockMarketConstant.STOCK_JOE);
			break;
		default:
			throw new BusinessException(stock+ "stock not found. Please select a valid stock");
		}
		return stockDataModel;
			
	}
	
	private void checkValidPrice(final double price) {
		if(price<0.0) {
			throw new InvalidValueException(price+" is a negetive value");
		}
	}
	
	private void checkBuySellIndicator(final String buySellIndicator) {
		if(buySellIndicator!=StockMarketConstant.BUY_INDICATOR
				&& buySellIndicator!=StockMarketConstant.SELL_INDICATOR) {
			throw new InvalidValueException("Trade must be either Buy or Sell");
		}
	}
	
	private void checkValidQuantity(final int quantity) {
		if(quantity<0) {
			throw new InvalidValueException(quantity+" is a negetive value");
		}
	}

	public List<TradingModel> getTradingModelList() {
		return tradingModelList;
	}
	
}

