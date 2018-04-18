package pointOfSale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class Computer {
	
	private Map<Integer, DatabaseProduct> productDatabase = new HashMap<>();
	private Printer connectedPrinter;
	private LCD connectedLCD;
	private double totalPrice;
	
	Computer(){
		initDatabase();
	}

	private void initDatabase() {
		String line;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("database/Products.txt"));
			while((line = reader.readLine()) != null) {
				String[] elements = line.split(":",3);
				if(elements.length >= 2) {
					productDatabase.put(Integer.parseInt(elements[0]), new DatabaseProduct(elements[1], Double.parseDouble(elements[2])));
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
	
	public void connectPrinter(Printer printer) {
		try {
			if(printer == null)
				throw new NullPointerException();
			this.connectedPrinter = printer;
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	public void connectLCD(LCD lcd) {
		try {
			if(lcd == null)
				throw new NullPointerException();
			this.connectedLCD = lcd;
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	public void checkInDatabase(Integer Id) {
		if(productDatabase.containsKey(Id)) {
			DatabaseProduct tempProduct = productDatabase.get(Id);
			connectedLCD.print(tempProduct.getName() + " : " + tempProduct.getPrice() + "$");
			connectedPrinter.addToList(tempProduct.getName() + " : " + tempProduct.getPrice() + "$");
			totalPrice += tempProduct.getPrice();
		}else if(Id == null){
			connectedLCD.print("Invalid bar-code");
		}else {
			connectedLCD.print("Product not found");
		}
	}
	
	public void printReceipt() {
		connectedPrinter.printToFile(round(totalPrice,2));
	}
	
	public void printTotalPriceOnLCD() {
		connectedLCD.print("Total price: " + round(totalPrice,2) + "$");
		totalPrice = 0;
	}
	
	public void clearPrinter() {
		connectedPrinter.clear();
	}
	
	private static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
	 
	    BigDecimal bd = new BigDecimal(Double.toString(value));
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

}
