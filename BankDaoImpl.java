import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BankDaoImpl implements BankDao {
    @Override
    public Bank getBank(int bid) {
        Connection c = null;
        Statement stmt = null;
        Bank result=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Bank WHERE bid="+bid+";" );
            while ( rs.next() ) {
                double profit=rs.getDouble("profit");
                double balance=rs.getDouble("balance");
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
                result=new Bank(new DigitMoney(profit,currencyWithRate),new DigitMoney(balance,currencyWithRate));
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
}
