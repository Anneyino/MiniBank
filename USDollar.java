
public class USDollar extends Currency{
	
	private static USDollar USDollarInstance = new USDollar();

	private USDollar() {
		super("USDollar",7);
	}
	
	public static USDollar getInstance() {
		return USDollarInstance;
	}
}
