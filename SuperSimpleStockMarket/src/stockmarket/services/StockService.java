package stockmarket.services;

import java.text.DecimalFormat;

import stockmarket.model.GBCEDataModelSingleton;
import stockmarket.model.StockDataModel;
import stockmarket.util.StockMarketConstant;

public class StockService {
	
	private static StockService service = null;
	private DecimalFormat df = new DecimalFormat("#.##");
	
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
				return dividendValue.toString();
		} else {
				dividendValue = Double.parseDouble(df.format((dataModel.getFixedDividend()*dataModel.getPerValue())/price));
				dataModel.setLastDividend(dividendValue);
				return dividendValue.toString();
		}
	}
	
	public String CalculatePERatio(final String stock, final double price) {
		
		StockDataModel dataModel = getStockModelObject(stock);
		return df.format(price/dataModel.getLastDividend());
	}
	
	public double calVolWeightedStockPrice() {
		
		return 0.0;
	}
	
	public Boolean recordTrade(){
		 return false;
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

