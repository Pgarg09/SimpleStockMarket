package stockmarket;

import java.io.IOException;
import java.util.Scanner;

import stockmarket.model.GBCEDataModelSingleton;
import stockmarket.util.SelectionPage;

public class Main {

	static {
		GBCEDataModelSingleton.getStockDataModel();
	}

	public static void main(String[] args) throws IOException, InterruptedException {

		System.out.println("Welcome TO Stock Market");
		System.out.println("\nPress Any TO Proceed ... \n");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		SelectionPage.showAndSelectOptions(scanner);

	}

}
