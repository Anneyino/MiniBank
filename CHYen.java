
public class CHYen extends Currency{
	
	private static final CHYen CHYenInstance = new CHYen();

	private CHYen() {
		super("Yen",1);
	}
	
	public static CHYen getInstance() {
		return CHYenInstance;
	}

}
