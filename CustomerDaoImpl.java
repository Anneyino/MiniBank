import java.sql.*;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public Customer getCustomer(String loggingID) {
        Connection c = null;
        Statement stmt = null;
        Customer result=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Customer WHERE loggingID="+loggingID+";" );
            while ( rs.next() ) {
                String name = rs.getString("name");
                String password=rs.getString("password");
                boolean inDebt=rs.getInt("in_debt")==1;
                double loan=rs.getDouble("loan");
                String addr=rs.getString("address");
                int currency=rs.getInt("currency");
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
                result=new Customer(name,loggingID,password,addr,new DigitMoney(loan,currencyWithRate));
                //result.set_Is_In_Debt(inDebt);
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
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
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

    @Override
    public void insert(String name, String loggingID, String password, int inDebt, DigitMoney loan, String address) {
        Connection c;
        Statement stmt;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            int currencyType=0;
            if(loan.getCurrency().getName().equals("USDollar")) {
                currencyType = 1;
            }else if(loan.getCurrency().getName().equals("EuroDollar")) {
                currencyType = 2;
            }else if(loan.getCurrency().getName().equals("CHYen")) {
                currencyType = 3;
            }
//            switch (targetCurrency.getName()){
//                case "USDollar"->currencyType=1;
//                case "EuroDollar"->currencyType=2;
//                case "CHYen"->currencyType=3;
//            }
            stmt.executeUpdate( "INSERT INTO Customer (name,loggingID,password,in_debt,loan,currency,address)"
                    +"VALUES ("+name+","+loggingID+","+password+","+inDebt+","+loan.getMoney_Num()+","+currencyType+","+address+");" );
            c.commit();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    @Override
    public void updateTheLoan(String loggingID,DigitMoney loan) {
        Connection c = null;
        Statement stmt = null;
        Customer result=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            stmt.executeUpdate( "UPDATE Customer SET loan="+loan.getMoney_Num()+", currency="+loan.getCurrency()+" WHERE loggingID="+loggingID+";" );
            c.commit();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    @Override
    public void updateInDebt(String loggingID,int inDebt) {
        Connection c = null;
        Statement stmt = null;
        Customer result=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            stmt.executeUpdate( "UPDATE Customer SET in_debt="+inDebt+" WHERE loggingID="+loggingID+";" );
            c.commit();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    @Override
    public boolean checkThePassword(String loggingID, String password) {
        Connection c = null;
        Statement stmt = null;
        Customer result=null;
        String realPassword="";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT password from Customer WHERE loggingID="+loggingID+";" );
            realPassword = rs.getString("password");
            c.commit();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return realPassword.equals(password);
    }
}
