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
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
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
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
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
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
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
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
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

    @Override
    public void insert(int aid1, int aid2, DigitMoney money, Date time) {
        Connection c;
        Statement stmt;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            int currencyType=0;
            if(money.getCurrency().getName().equals("USDollar")) {
                currencyType = 1;
            }else if(money.getCurrency().getName().equals("EuroDollar")) {
                currencyType = 2;
            }else if(money.getCurrency().getName().equals("CHYen")) {
                currencyType = 3;
            }
            stmt.executeUpdate( "INSERT INTO \"Transaction\" (aid1,aid2,money,currency,time)"
                    +"VALUES ("+aid1+","+aid2+","+money.getMoney_Num()+","+currencyType+","+time+");" );
            c.commit();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

    }

    @Override
    public List<Transaction> getTransactionByUser(int uid1, int uid2) {
        Connection c;
        Statement stmt;
        List<Transaction> result=new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * From " +
                    "(SELECT * FROM \"Transaction\" Join Account A1 on A1.aid = \"Transaction\".aid1 WHERE A1.uid="+uid1+";)" +
                    " Join Account A2 on A2.aid=\"Transaction\".aid2 Where A2.uid="+uid2+";" );
            while ( rs.next() ) {
                int aid1=rs.getInt("aid1");
                int aid2=rs.getInt("aid2");
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
    public List<Transaction> getTransactionFromByUser(int uid) {
        Connection c;
        Statement stmt;
        List<Transaction> result=new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM \"Transaction\" Join Account A1 on A1.aid = \"Transaction\".aid1 WHERE A1.uid="+uid+";");
            while ( rs.next() ) {
                int aid1=rs.getInt("aid1");
                int aid2=rs.getInt("aid2");
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
    public List<Transaction> getTransactionToByUser(int uid) {
        Connection c;
        Statement stmt;
        List<Transaction> result=new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM \"Transaction\" Join Account A2 on A2.aid = \"Transaction\".aid2 WHERE A2.uid="+uid+";");
            while ( rs.next() ) {
                int aid1=rs.getInt("aid1");
                int aid2=rs.getInt("aid2");
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
}
