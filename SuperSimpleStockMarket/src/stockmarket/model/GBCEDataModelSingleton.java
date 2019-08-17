package stockmarket.model;

import java.util.HashMap;
import java.util.Map;

import stockmarket.util.StockMarketConstant;

public final class GBCEDataModelSingleton {
	
	private static Map<String, StockDataModel> stockDataModelMap = new HashMap<String, StockDataModel>();
	private static GBCEDataModelSingleton dataModel = null;

	private GBCEDataModelSingleton() {

	}

	public static Map<String, StockDataModel> getStockDataModel() {
		getSingletonGBCEDataModel();
		return stockDataModelMap;
	}

	private static GBCEDataModelSingleton getSingletonGBCEDataModel(){
		if(dataModel == null) {
			synchronized (GBCEDataModelSingleton.class) {
				if (dataModel == null) {
					dataModel = new GBCEDataModelSingleton();
					StockDataModel stock_TEA = new StockDataModel(StockMarketConstant.STOCK_TEA, 
							StockMarketConstant.STOCK_TYPE_COMMON, 
							0, 100);
					StockDataModel stock_POP = new StockDataModel(StockMarketConstant.STOCK_POP, 
							StockMarketConstant.STOCK_TYPE_COMMON, 
							8, 100);
					StockDataModel stock_ALE = new StockDataModel(StockMarketConstant.STOCK_ALE, 
							StockMarketConstant.STOCK_TYPE_COMMON, 
							23, 60);
					StockDataModel stock_GIN = new StockDataModel(StockMarketConstant.STOCK_GIN, 
							StockMarketConstant.STOCK_TYPE_PREFERRED, 
							8, 100).setFixedDividend(2);
					StockDataModel stock_JOE = new StockDataModel(StockMarketConstant.STOCK_JOE, 
							StockMarketConstant.STOCK_TYPE_COMMON, 
							13, 250);
					stockDataModelMap.put(StockMarketConstant.STOCK_TEA, stock_TEA);
					stockDataModelMap.put(StockMarketConstant.STOCK_POP, stock_POP);
					stockDataModelMap.put(StockMarketConstant.STOCK_ALE, stock_ALE);
					stockDataModelMap.put(StockMarketConstant.STOCK_GIN, stock_GIN);
					stockDataModelMap.put(StockMarketConstant.STOCK_JOE, stock_JOE);
				}
				
			}
		}
		return dataModel;
	}
	
}
