import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BankDaoImpl implements BankDao {
    @Override
    public Bank getBank() {
        Connection c = null;
        Statement stmt = null;
        Bank result=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Bank WHERE bid=1;" );
            while ( rs.next() ) {
                double profit=rs.getDouble("profit");
                double balance=rs.getDouble("balance");
                int currency=rs.getInt("currency");
                Currency currencyWithRate = null;
                if(currency==1) {
                	currencyWithRate=USDollar.getInstance();
                }else if(currency==2) {
                	currencyWithRate=EuroDollar.getInstance();
                }else if(currency==3) {
                	currencyWithRate=CHYen.getInstance();
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

    @Override
    public void insert(DigitMoney profit, DigitMoney balance) {
        Connection c;
        Statement stmt;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            if(!profit.getCurrency().equals(balance.getCurrency())){
                System.out.println("the profit and the balance of a bank should have same currency type!");
            }
            int currencyType=0;
            if(profit.getCurrency().getName().equals("USDollar")) {
                currencyType = 1;
            }else if(profit.getCurrency().getName().equals("EuroDollar")) {
                currencyType = 2;
            }else if(profit.getCurrency().getName().equals("CHYen")) {
                currencyType = 3;
            }
            stmt.executeUpdate( "INSERT INTO Bank (profit,balance,currency)"
                    +"VALUES ("+profit.getMoney_Num()+","+balance.getMoney_Num()+","+currencyType+");" );
            c.commit();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    @Override
    public void updateProfit(DigitMoney profit) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            int currencyType=0;
            if(profit.getCurrency().getName().equals("USDollar")) {
                currencyType = 1;
            }else if(profit.getCurrency().getName().equals("EuroDollar")) {
                currencyType = 2;
            }else if(profit.getCurrency().getName().equals("CHYen")) {
                currencyType = 3;
            }
            stmt.executeUpdate( "UPDATE Bank SET profit="+profit.getMoney_Num()+", currency="+currencyType+" WHERE bid=1;" );
            c.commit();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    @Override
    public void updateBalance(DigitMoney balance) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            int currencyType=0;
            if(balance.getCurrency().getName().equals("USDollar")) {
                currencyType = 1;
            }else if(balance.getCurrency().getName().equals("EuroDollar")) {
                currencyType = 2;
            }else if(balance.getCurrency().getName().equals("CHYen")) {
                currencyType = 3;
            }
            stmt.executeUpdate( "UPDATE Bank SET balance="+balance.getMoney_Num()+", currency="+currencyType+" WHERE bid=1;" );
            c.commit();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}
