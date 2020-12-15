import java.sql.*;
// this class is just for test
public class testDatabase {
	public static void main( String args[] )
	  {
	    Connection c = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
	      CustomerDao customerDao=new CustomerDaoImpl();
	      Customer newCustomer=customerDao.getCustomer("12345");
	      System.out.println(newCustomer.getAddress());
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Opened database successfully");
	  }
}
