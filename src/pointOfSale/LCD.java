package pointOfSale;

public class LCD extends OutputDevice{
	
	@Override
	public void print(String message) {
		System.out.println("LCD: " + message);
	}
}
