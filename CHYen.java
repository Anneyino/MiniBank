
public class CHYen extends Currency{
	
	private static CHYen CHYenInstance = new CHYen();

	private CHYen() {
		super("CHYen",1);
	}
	
	public static CHYen getInstance() {
		return CHYenInstance;
	}

}
