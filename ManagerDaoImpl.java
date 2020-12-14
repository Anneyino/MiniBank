import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ManagerDaoImpl implements ManagerDao {
    @Override
    public Manager getManager(String loggingID) {
        Connection c = null;
        Statement stmt = null;
        Manager result=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBaseForBank.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Manager WHERE loggingID="+loggingID+";" );
            while ( rs.next() ) {
                String name = rs.getString("name");
                String password=rs.getString("password");
                result=new Manager(name,loggingID,password);
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
            stmt.executeUpdate( "UPDATE Manager SET password="+password+" WHERE loggingID="+loggingID+";" );
            c.commit();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}
