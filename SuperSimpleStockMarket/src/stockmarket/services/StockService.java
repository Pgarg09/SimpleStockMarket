package stockmarket.services;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
		StockDataModel dataModel = getStockModelObject(stock);
		if(dataModel.getType().equalsIgnoreCase(StockMarketConstant.STOCK_TYPE_COMMON)) {
				dividendValue = Double.parseDouble(df.format(dataModel.getLastDividend()/price));
				dataModel.setLastDividend(dividendValue);
				dataModel.setPrice(price);
				return dividendValue.toString();
		} else {
				dividendValue = Double.parseDouble(df.format((dataModel.getFixedDividend()*dataModel.getPerValue())/price));
				dataModel.setLastDividend(dividendValue);
				dataModel.setPrice(price);
				return dividendValue.toString();
		}
	}
	
	public String CalculatePERatio(final String stock, final double price) {
		
		StockDataModel dataModel = getStockModelObject(stock);
		dataModel.setPrice(price);
		return df.format(price/dataModel.getLastDividend());
	}
	
	public boolean recordTrade(final String stock, final int quantity, final String buySell, final double tradePrice) {
		TradingModel tradingModel = new TradingModel();
		tradingModel.setStock(stock).setShareQuantity(quantity).setBuySell(buySell).setTradePrice(tradePrice)
				.setTradeTime(System.currentTimeMillis());
		tradingModelList.add(tradingModel);
		return true;
	}
	
	public String calVolWeightedStockPrice() {
		
		if(!tradingModelList.isEmpty()) {
			long fifteenMinAgo = System.currentTimeMillis() -FIFTEEN_MINUTES;
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
	
	
	private StockDataModel getStockModelObject(final String stock) {
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
		}
			
		return stockDataModel;
			
	}

}

