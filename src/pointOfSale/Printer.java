package pointOfSale;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class Printer extends OutputDevice{
	
	private List<String> productList = new ArrayList<>();
	
	@Override
	public void print(String message) {
		System.out.println("Printer: " + message);
	}
	
	public void printToFile(double totalPrice) {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
			new FileOutputStream("PrinterReceipt.txt"), "utf-8"))) {
				writer.write("Selected products:\n");
				for(String s : productList) {
					writer.write(s + "\n");
				}
				writer.write("Total price: " + totalPrice + "$");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addToList(String message) {
		productList.add(message);
	}
	public void clear() {
		productList.clear();
	}

}
