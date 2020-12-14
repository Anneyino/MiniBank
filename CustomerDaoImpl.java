import java.sql.*;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public Customer getCustomer(String loggingID) {
        Connection c = null;
        Statement stmt = null;
        Customer result=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Customer WHERE loggingID="+loggingID+";" );
            while ( rs.next() ) {
                String name = rs.getString("name");
                String password=rs.getString("password");
                boolean inDebt=rs.getInt("in_debt")==1;
                double loan=rs.getDouble("loan");   //not used?
                int currency=rs.getInt("currency");
                Currency currencyWithRate;
                switch (currency){
                    case 1->{
                        currencyWithRate=USDollar.getInstance();
                    }
                    case 2->{
                        currencyWithRate=EuroDollar.getInstance();
                    }
                    default->{
                        currencyWithRate=CHYen.getInstance();
                    }
                }
                result=new Customer(name,loggingID,password,"random place");
                result.set_Is_In_Debt(inDebt);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return result;

    }

    @Override
    public void changePassword(String loggingID, String password) {
        Connection c = null;
        Statement stmt = null;
        Customer result=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            stmt.executeUpdate( "UPDATE Customer SET password="+password+" WHERE loggingID="+loggingID+";" );
            c.commit();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}
