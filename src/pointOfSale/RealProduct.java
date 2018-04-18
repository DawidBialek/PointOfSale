package pointOfSale;

public class RealProduct extends AbstractProduct{
	
	private int barcode;
	
	RealProduct(String name, double price, int barcode) {
		super(name, price);
		this.barcode = barcode;
	}
	
	int getBarcode() {
		return barcode;
	}
	
}
