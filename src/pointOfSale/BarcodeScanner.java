package pointOfSale;

import java.util.Map;

public class BarcodeScanner extends InputDevice{
	private Map<String, Integer> currentShelf;
	
	BarcodeScanner(Map<String, Integer> currentShelf){
		this.currentShelf = currentShelf;
	}
	void connect(Computer computer) {
		this.setConnectedComputer(computer);
	}
	
	void scan(String name){
		getConnectedComputer().checkInDatabase(currentShelf.get(name));
	}
	
	void exit() {
		getConnectedComputer().printReceipt();
		getConnectedComputer().printTotalPriceOnLCD();
	}
}
