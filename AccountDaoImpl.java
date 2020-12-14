import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;

public class AccountDaoImpl implements AccountDao {
    @Override
    public Account getAccount(int AID) {
        Connection c = null;
        Statement stmt = null;
        Account result=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Account WHERE aid="+AID+";" );
            while ( rs.next() ) {
                int aid = rs.getInt("aid");
                int uid=rs.getInt("uid");
                int type=rs.getInt("type");
                double balance=rs.getDouble("balance");
                int currency=rs.getInt("currency");
                Date start_time =rs.getDate("start_time");
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

                result=new Account(aid,new DigitMoney(balance,currencyWithRate),(java.util.Date) start_time,type);
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
    public void deposit(int AID, DigitMoney money) {
        Account account= getAccount(AID);
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            account.getBalance().add(money);
            stmt.executeUpdate( "UPDATE Account SET balance="+account.getBalance().getMoney_Num()+" WHERE aid="+AID+";" );
            c.commit();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    @Override
    public void withdraw(int AID, DigitMoney money) {
        Account account= getAccount(AID);
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            account.getBalance().decrease(money);
            stmt.executeUpdate( "UPDATE Account SET balance="+account.getBalance().getMoney_Num()+" WHERE aid="+AID+";" );
            c.commit();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    @Override
    public void transfer(int AID1, int AID2, DigitMoney money) {
        Account account1= getAccount(AID1);
        Account account2= getAccount(AID2);
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            account1.getBalance().decrease(money);
            account2.getBalance().add(money);
            stmt.executeUpdate( "UPDATE Account SET balance="+account1.getBalance().getMoney_Num()+" WHERE aid="+AID1+";" );
            stmt.executeUpdate( "UPDATE Account SET balance="+account2.getBalance().getMoney_Num()+" WHERE aid="+AID2+";" );
            c.commit();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    @Override
    public void exchangeCurrency(int AID, Currency targetCurrency) {
        Account account= getAccount(AID);
        Connection c;
        Statement stmt;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            account.getBalance().exchangeTo(targetCurrency);
            int currencyType=0;
            switch (targetCurrency.getName()){
                case "USDollar"->currencyType=1;
                case "EuroDollar"->currencyType=2;
                case "CHYen"->currencyType=3;
            }
            stmt.executeUpdate( "UPDATE Account SET balance="+account.getBalance().getMoney_Num()+" WHERE aid="+AID+";" );
            stmt.executeUpdate( "UPDATE Account SET currency="+currencyType+" WHERE aid="+AID+";" );
            c.commit();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

    }
}
