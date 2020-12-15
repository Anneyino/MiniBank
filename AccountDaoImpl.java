import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
    @Override
    public List<Account> getAccountList(int UID) {
        Connection c = null;
        Statement stmt = null;
        List<Account> result=new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Account WHERE uid="+UID+";" );
            while ( rs.next() ) {
                int aid = rs.getInt("aid");
                int uid=rs.getInt("uid");
                int type=rs.getInt("type");
                double balance=rs.getDouble("balance");
                int currency=rs.getInt("currency");
                Date start_time =rs.getDate("start_time");
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

                result.add(new Account(aid,new DigitMoney(balance,currencyWithRate),(java.util.Date) start_time,type));
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
    public Account getAccount(int AID) {
        Connection c = null;
        Statement stmt = null;
        Account result=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
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
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
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
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
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
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
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
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            account.getBalance().exchangeTo(targetCurrency);
            int currencyType=0;
            if(targetCurrency.getName().equals("USDollar")) {
            	currencyType = 1;
            }else if(targetCurrency.getName().equals("EuroDollar")) {
            	currencyType = 2;
            }else if(targetCurrency.getName().equals("CHYen")) {
            	currencyType = 3;
            }
//            switch (targetCurrency.getName()){
//                case "USDollar"->currencyType=1;
//                case "EuroDollar"->currencyType=2;
//                case "CHYen"->currencyType=3;
//            }
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

    @Override
    public void insert(int uid, int type, DigitMoney balance, java.util.Date startTime) {
        Connection c;
        Statement stmt;
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
            stmt.executeUpdate( "INSERT INTO Account (uid,type,balance,currency,start_time)"
                    +"VALUES ("+uid+","+type+","+balance.getMoney_Num()+","+currencyType+","+startTime+");" );
            c.commit();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    @Override
    public void updateBalance(int AID, DigitMoney balance) {
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
            stmt.executeUpdate( "UPDATE Account SET balance="+balance.getMoney_Num()+", currency="+currencyType+" WHERE aid="+AID+";" );
            c.commit();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

}
