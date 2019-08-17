package stockmarket.util;

public class ConsoleUtil {

	public final static void clearConsole() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (Exception e) {
			System.err.println("\nError while clearing console\n");
		} 
	}
}
