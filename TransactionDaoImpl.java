import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao{
    @Override
    public List<Transaction> getTransaction(int aid1, int aid2) {
        Connection c;
        Statement stmt;
        List<Transaction> result=new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM \"Transaction\" WHERE aid1="+aid1+" and aid2="+aid2+";" );
            while ( rs.next() ) {
                int money=rs.getInt("money");
                int currency=rs.getInt("currency");
                java.util.Date date = rs.getDate("time");
                Currency currencyWithRate = null;
                
                if(currency==1) {
                	currencyWithRate=USDollar.getInstance();
                }else if(currency==2) {
                	currencyWithRate=EuroDollar.getInstance();
                }else if(currency==3) {
                	currencyWithRate=CHYen.getInstance();
                }
                
//                switch (currency){
//                    case 1->{
//                        currencyWithRate=USDollar.getInstance();
//                    }
//                    case 2->{
//                        currencyWithRate=EuroDollar.getInstance();
//                    }
//                    default->{
//                        currencyWithRate=CHYen.getInstance();
//                    }
//                }
                AccountDao accountDao=new AccountDaoImpl();

                result.add(new Transaction(accountDao.getAccount(aid1),accountDao.getAccount(aid2),new DigitMoney(money,currencyWithRate),date));
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
    public List<Transaction> getTransactionFrom(int aid) {
        Connection c;
        Statement stmt;
        List<Transaction> result=new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM \"Transaction\" WHERE aid1="+aid+";" );
            while ( rs.next() ) {
                int money=rs.getInt("money");
                int currency=rs.getInt("currency");
                int aid2=rs.getInt("aid2");
                java.util.Date date = rs.getDate("time");
                Currency currencyWithRate = null;
                
                if(currency==1) {
                	currencyWithRate=USDollar.getInstance();
                }else if(currency==2) {
                	currencyWithRate=EuroDollar.getInstance();
                }else if(currency==3) {
                	currencyWithRate=CHYen.getInstance();
                }
                
//                switch (currency){
//                    case 1->{
//                        currencyWithRate=USDollar.getInstance();
//                    }
//                    case 2->{
//                        currencyWithRate=EuroDollar.getInstance();
//                    }
//                    default->{
//                        currencyWithRate=CHYen.getInstance();
//                    }
//                }
                AccountDao accountDao=new AccountDaoImpl();

                result.add(new Transaction(accountDao.getAccount(aid),accountDao.getAccount(aid2),new DigitMoney(money,currencyWithRate),date));
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
    public List<Transaction> getTransactionTo(int aid) {
        Connection c;
        Statement stmt;
        List<Transaction> result=new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM \"Transaction\" WHERE aid2="+aid+";" );
            while ( rs.next() ) {
                int money=rs.getInt("money");
                int currency=rs.getInt("currency");
                int aid1=rs.getInt("aid1");
                java.util.Date date = rs.getDate("time");
                Currency currencyWithRate = null;
                
                if(currency==1) {
                	currencyWithRate=USDollar.getInstance();
                }else if(currency==2) {
                	currencyWithRate=EuroDollar.getInstance();
                }else if(currency==3) {
                	currencyWithRate=CHYen.getInstance();
                }
                
//                switch (currency){
//                    case 1->{
//                        currencyWithRate=USDollar.getInstance();
//                    }
//                    case 2->{
//                        currencyWithRate=EuroDollar.getInstance();
//                    }
//                    default->{
//                        currencyWithRate=CHYen.getInstance();
//                    }
//                }
                AccountDao accountDao=new AccountDaoImpl();

                result.add(new Transaction(accountDao.getAccount(aid1),accountDao.getAccount(aid),new DigitMoney(money,currencyWithRate),date));
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
    public List<Transaction> getTransaction(Date datetime) {
        Connection c;
        Statement stmt;
        List<Transaction> result=new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM \"Transaction\" WHERE time="+(java.sql.Date)datetime+";" );
            while ( rs.next() ) {
                int money=rs.getInt("money");
                int currency=rs.getInt("currency");
                int aid1=rs.getInt("aid1");
                int aid2=rs.getInt("aid2");
                Currency currencyWithRate = null;
                
                if(currency==1) {
                	currencyWithRate=USDollar.getInstance();
                }else if(currency==2) {
                	currencyWithRate=EuroDollar.getInstance();
                }else if(currency==3) {
                	currencyWithRate=CHYen.getInstance();
                }
                
//                switch (currency){
//                    case 1->{
//                        currencyWithRate=USDollar.getInstance();
//                    }
//                    case 2->{
//                        currencyWithRate=EuroDollar.getInstance();
//                    }
//                    default->{
//                        currencyWithRate=CHYen.getInstance();
//                    }
//                }
                AccountDao accountDao=new AccountDaoImpl();

                result.add(new Transaction(accountDao.getAccount(aid1),accountDao.getAccount(aid2),new DigitMoney(money,currencyWithRate),datetime));
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
