package stockmarket;



import java.io.IOException;
import java.util.Scanner;

import stockmarket.model.GBCEDataModelSingleton;
import stockmarket.util.ConsoleUtil;
import stockmarket.util.SelectionPage;

public class Main {
	
	public static void  main (String [] args) throws IOException, InterruptedException {
		
		initializeGBCEDetails();
		ConsoleUtil.clearConsole();
		System.out.println("Welcome TO Stock Market");
		System.out.println("\nPress Any TO Proceed ... \n");
		Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        showAndSelectOptions(scanner);
          
	}
	
	private static void showAndSelectOptions(final Scanner scanner) {
		ConsoleUtil.clearConsole();
		SelectionPage.listAllOptions();
        String input = scanner.nextLine();
        userInputSelection(input, scanner);
	}
	
	private static void initializeGBCEDetails(){
		GBCEDataModelSingleton.getStockDataModel();
		
	}
	
	private static void userInputSelection(final String input, final Scanner scanner) {
		
		switch (input) {
		case "1":
			System.out.println("In Case 1");
			showAndSelectOptions(scanner);
			break;
		case "2":
			System.out.println("In Case 2");
			showAndSelectOptions(scanner);
			break;
		case "3":
			System.out.println("In Case 3");
			showAndSelectOptions(scanner);
			break;
		case "4":
			System.out.println("In Case 4");
			showAndSelectOptions(scanner);
			break;
		case "5":
			System.out.println("In Case 5");
			showAndSelectOptions(scanner);
			break;
		default:
			System.err.println("\nPlease select a Valid Input\n");
			showAndSelectOptions(scanner);
			break;
		}
	}

}
