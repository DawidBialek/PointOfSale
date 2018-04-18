package pointOfSale;

public abstract class InputDevice {
	private Computer connectedComputer;

	public Computer getConnectedComputer() {
		return connectedComputer;
	}

	public void setConnectedComputer(Computer connectedComputer) {
		this.connectedComputer = connectedComputer;
	}
	
}
