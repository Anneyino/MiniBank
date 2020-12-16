import java.sql.*;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public Customer getCustomer(String loggingID) {
        Connection c = null;
        //Statement stmt = null;
        Customer result=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            String sql="SELECT * FROM Customer WHERE loggingID=?";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setString(1,loggingID);
            ResultSet rs=preparedStatement.executeQuery();
            while ( rs.next() ) {
                String name = rs.getString("name");
                String password=rs.getString("password");
                boolean inDebt=rs.getInt("in_debt")==1;
                double loan=rs.getDouble("loan");
                String addr=rs.getString("address");
                int uid=rs.getInt("uid");
                int currency=rs.getInt("currency");
                Currency currencyWithRate = null;
                if(currency==1) {
                	currencyWithRate=USDollar.getInstance();
                }else if(currency==2) {
                	currencyWithRate=EuroDollar.getInstance();
                }else if(currency==3) {
                	currencyWithRate=CHYen.getInstance();
                }
                result=new Customer(name,loggingID,password,addr,new DigitMoney(loan,currencyWithRate),uid);
            }
            rs.close();
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
        Customer result=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            PreparedStatement preparedStatement=c.prepareStatement("UPDATE Customer SET password=? WHERE loggingID=?");
            preparedStatement.setString(1,password);
            preparedStatement.setString(2,loggingID);
            preparedStatement.executeUpdate();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    @Override
    public void insert(String name, String loggingID, String password, int inDebt, DigitMoney loan, String address) {
        Connection c;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            int currencyType=0;
            if(loan.getCurrency().getName().equals("USDollar")) {
                currencyType = 1;
            }else if(loan.getCurrency().getName().equals("EuroDollar")) {
                currencyType = 2;
            }else if(loan.getCurrency().getName().equals("CHYen")) {
                currencyType = 3;
            }
            try (PreparedStatement insert = c.prepareStatement(
                    "insert into Customer(name, loggingID, password, in_debt, loan, currency, address) values(?,?,?,?,?,?,?)")) {
                insert.setString(1, name);
                insert.setString(2, loggingID);
                insert.setString(3,password);
                insert.setInt(4,inDebt);
                insert.setDouble(5,loan.getMoney_Num());
                insert.setInt(6,currencyType);
                insert.setString(7,address);

                insert.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Cannot insert the customer into SQL Database.", e);
            }
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    @Override
    public void updateTheLoan(String loggingID,DigitMoney loan) {
        Connection c = null;
        Customer result=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            int currencyType=0;
            if(loan.getCurrency().getName().equals("USDollar")) {
                currencyType = 1;
            }else if(loan.getCurrency().getName().equals("EuroDollar")) {
                currencyType = 2;
            }else if(loan.getCurrency().getName().equals("CHYen")) {
                currencyType = 3;
            }
            PreparedStatement ps=c.prepareStatement("UPDATE Customer SET loan=?, currency=? WHERE loggingID=?");
            ps.setDouble(1,loan.getMoney_Num());
            ps.setInt(2,currencyType);
            ps.setString(3,loggingID);
            ps.executeUpdate();
            c.commit();
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
            PreparedStatement ps=c.prepareStatement("UPDATE Customer SET in_debt=? WHERE loggingID=?");
            ps.setInt(1,inDebt);
            ps.setString(2,loggingID);
            ps.executeUpdate();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    @Override
    public boolean checkThePassword(String loggingID, String password) {
        Connection c = null;
        String realPassword="";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            PreparedStatement ps=c.prepareStatement("SELECT password from Customer WHERE loggingID=?");
            ps.setString(1,loggingID);
            ResultSet rs=ps.executeQuery();
            realPassword = rs.getString("password");
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return realPassword.equals(password);
    }
}
