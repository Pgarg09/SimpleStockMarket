package stockmarket.util;

public class SelectionPage {
	
	public static void listAllOptions(){
		System.out.println("Select the following options");
        System.out.println("*****************************");
        System.out.println("1.  Calculate the dividend yield ");
        System.out.println("2.  Calculate the P/E Ratio");
        System.out.println("3.  Record a trade, with timestamp, quantity of shares, buy or sell indicator and"
        		+ "traded price");
        System.out.println("4.  Calculate Volume Weighted Stock Price based on trades in past 15 minutes");
        System.out.println("5.  Calculate the GBCE All Share Index using the geometric mean of prices for all stocks");
	}

}
