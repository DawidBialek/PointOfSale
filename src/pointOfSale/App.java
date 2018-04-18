package pointOfSale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
	
	private Map<String, Integer> shelf;
	private Printer printer;
	private LCD lcd;
	private Computer computer;
	private BarcodeScanner BarcodeScanner;
	private Scanner userInputScanner;
	
	public App() {
		initVariables();
		initConnections();
		initRealProductDatabase();
	}
	
	public void run() {
		String input;
		System.out.print("Welcome to Product Management System! ");
		do {
			System.out.println("List of available products:");
			for(String s : shelf.keySet()) {
				System.out.print(s + ", ");
			}
			System.out.println("\n\nPlease enter name of the product to scan it. To finish scanning enter 'exit' command.");
			
			do{
				input = userInputScanner.next();
				if(!input.equals("exit"))
					BarcodeScanner.scan(input);
			}while(!input.equals("exit"));
			BarcodeScanner.exit();
			
			System.out.println("Would You like to scan some more items? (Y/N)");
			do {
				input = userInputScanner.next();
			}while(!input.equals("Y") && !input.equals("N"));
			computer.clearPrinter();
			
		}while(input.equals("Y"));
	}
	
	private void initVariables() {
		shelf = new HashMap<>();
		printer = new Printer();
		lcd = new LCD();
		computer = new Computer();
		BarcodeScanner = new BarcodeScanner(shelf);
		userInputScanner = new Scanner(System.in);
	}
	
	private void initConnections() {
		computer.connectLCD(lcd);
		computer.connectPrinter(printer);
		BarcodeScanner.connect(computer);
	}
	
	private void initRealProductDatabase() {
		String line;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("database/RealProducts.txt"));
			while((line = reader.readLine()) != null) {
				String[] elements = line.split(":",2);
				if(elements.length >= 2) {
					if(!elements[1].equals("null")) {
						shelf.put(elements[0],Integer.parseInt(elements[1]));	
					} else {
						shelf.put(elements[0],null);
					}
				}
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
