package stockmarket.model;

import java.util.ArrayList;
import java.util.List;

import stockmarket.util.StockMarketConstant;

public final class GBCEDataModelSingleton {
	
	private static List<StockDataModel> stockDataModelList = new ArrayList<StockDataModel>();
	private static GBCEDataModelSingleton dataModel = null;

	private GBCEDataModelSingleton() {

	}

	public static List<StockDataModel> getStockDataModel() {
		getSingletonGBCEDataModel();
		return stockDataModelList;
	}

	private static GBCEDataModelSingleton getSingletonGBCEDataModel(){
		if(dataModel == null) {
			synchronized (GBCEDataModelSingleton.class) {
				if (dataModel == null) {
					dataModel = new GBCEDataModelSingleton();
					StockDataModel stock_TEA = new StockDataModel("TEA", StockMarketConstant.STOCK_TYPE_COMMON, 
							0, 100);
					StockDataModel stock_POP = new StockDataModel("POP", StockMarketConstant.STOCK_TYPE_COMMON, 
							8, 100);
					StockDataModel stock_ALE = new StockDataModel("ALE", StockMarketConstant.STOCK_TYPE_COMMON, 
							23, 60);
					StockDataModel stock_GIN = new StockDataModel("GIN", StockMarketConstant.STOCK_TYPE_PREFERRED, 
							8, 100).setFixedDividend(2);
					StockDataModel stock_JOE = new StockDataModel("JOE", StockMarketConstant.STOCK_TYPE_COMMON, 
							13, 250);
					stockDataModelList.add(stock_TEA);
					stockDataModelList.add(stock_POP);
					stockDataModelList.add(stock_ALE);
					stockDataModelList.add(stock_GIN);
					stockDataModelList.add(stock_JOE);
				}
				
			}
		}
		return dataModel;
	}
	
}
