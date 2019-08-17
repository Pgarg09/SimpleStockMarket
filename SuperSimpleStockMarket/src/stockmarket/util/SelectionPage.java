package stockmarket.util;

import java.util.Scanner;

import stockmarket.services.StockService;

public class SelectionPage {
	
	public static void listAllOptions(){
		System.out.println("Select from the following options");
        System.out.println("*****************************");
        System.out.println("1.  Calculate the dividend yield ");
        System.out.println("2.  Calculate the P/E Ratio");
        System.out.println("3.  Record a trade, with timestamp, quantity of shares, buy or sell indicator and"
        		+ "traded price");
        System.out.println("4.  Calculate Volume Weighted Stock Price based on trades in past 15 minutes");
        System.out.println("5.  Calculate the GBCE All Share Index using the geometric mean of prices for all stocks");
	}
	
	public static void listStockOptions(){
		System.out.println("Select from the following Stock options");
        System.out.println("*****************************");
        System.out.println("1.  TEA ");
        System.out.println("2.  POP ");
        System.out.println("3.  ALE ");
        System.out.println("4.  GIN ");
        System.out.println("5.  JOE ");
	}
	
	public static void listTradeOptions(){
		System.out.println("Select from the following Trade options");
        System.out.println("*****************************");
        System.out.println("1.  Buy ");
        System.out.println("2.  Sell ");
	}
	
	public static void showAndSelectOptions(final Scanner scanner) {
		SelectionPage.listAllOptions();
        String input = scanner.nextLine();
        userInputSelection(input, scanner);
	}
	

	private static void userInputSelection(final String input, final Scanner scanner) {
		
		String stock;
		double price;
		int quantityOfShare;
		String buySellIndicator;
		
		switch (input) {
		case "1":
			listStockOptions();
			stock = selectStock(scanner);
			price = inputPriceAndValidate(scanner);
			String dividendYield = StockService.getInstance().calculateDividendYield(stock, price);
			System.out.println("---------------------------------------");
			System.out.println("\n Dividend Yield is : " +dividendYield);
			System.out.println("---------------------------------------\n");
			showAndSelectOptions(scanner);
			break;
		case "2":
			listStockOptions();
			stock = selectStock(scanner);
			price = inputPriceAndValidate(scanner);
			String pByERatio = StockService.getInstance().calculatePERatio(stock, price);
			System.out.println("---------------------------------------");
			System.out.println("\n P/E Ratio is : " +pByERatio);
			System.out.println("---------------------------------------\n");
			showAndSelectOptions(scanner);
			break;
		case "3":
			listStockOptions();
			stock = selectStock(scanner);
			price = inputPriceAndValidate(scanner);
			quantityOfShare = inputQuantityAndValidate(scanner);
			listTradeOptions();
			buySellIndicator = getBuySellIndicator(scanner);
			boolean isTradeSuccess = StockService.getInstance().recordTrade(stock, quantityOfShare, 
					buySellIndicator, price);
			System.out.println("---------------------------------------");
			System.out.println("\n Is Trade Successful ? : " +isTradeSuccess);
			System.out.println("---------------------------------------\n");
			showAndSelectOptions(scanner);
			
			break;
		case "4":
			String volWeightedStockPrice = StockService.getInstance().calVolWeightedStockPrice();
			System.out.println("---------------------------------------");
			System.out.println("\n Volume Weighted Stock Price based on trades in past 15 minutes : " +volWeightedStockPrice);
			System.out.println("---------------------------------------\n");
			showAndSelectOptions(scanner);
			break;
		case "5":
			String meanOfPrices = StockService.getInstance().getMeanOfPrices();
			System.out.println("---------------------------------------");
			System.out.println("\n GBCE All Share Index using the geometric mean of prices for all stocks : " +meanOfPrices);
			System.out.println("---------------------------------------\n");
			showAndSelectOptions(scanner);
			break;
		default:
			System.err.println("\nPlease select a Valid Input\n");
			showAndSelectOptions(scanner);
			break;
		}
	}
	
	private static String selectStock(final Scanner scanner){
		
		String stock = null;
		String inputStock = scanner.nextLine();
		switch (inputStock) {
		case "1":
			stock = StockMarketConstant.STOCK_TEA;
			break;
		case "2":
			stock = StockMarketConstant.STOCK_POP;
			break;
		case "3":
			stock = StockMarketConstant.STOCK_ALE;
			break;
		case "4":
			stock = StockMarketConstant.STOCK_GIN;
			break;
		case "5":
			stock = StockMarketConstant.STOCK_JOE;
			break;
		default:
			System.err.println("\nPlease select a Valid Input\n");
			stock = selectStock(scanner);
			break;
		}
		return stock;
	}
	
	private static double inputPriceAndValidate(final Scanner scanner) {
		
		System.out.println("\nEnter Price..\n");
		String price = scanner.nextLine();
		double doubleValue=0;
		try {
			doubleValue = Double.parseDouble(price);
		} catch (Exception e) {
			System.err.println("\nPlease enter a valid Price\n");
			doubleValue = inputPriceAndValidate(scanner);
		}
		return doubleValue;
		
	}
	
	private static int inputQuantityAndValidate(final Scanner scanner){
		
		System.out.println("\nEnter Quantity..\n");
		String price = scanner.nextLine();
		int intValue=0;
		try {
			intValue = Integer.parseInt(price);
		} catch (Exception e) {
			System.err.println("\nPlease enter a valid Quantity\n");
			intValue = inputQuantityAndValidate(scanner);
		}
		return intValue;
	}
	
	private static String getBuySellIndicator(final Scanner scanner) {
		
		String buySellIndicator = null;
		switch (scanner.nextLine()) {
		case "1":
			buySellIndicator = "Buy";
			break;
		case "2":
			buySellIndicator = "Sell";
			break;
		default:
			System.err.println("\nPlease select a Valid Input\n");
			buySellIndicator = getBuySellIndicator(scanner);
			break;
		}
		return buySellIndicator;
	}
	
	


}
