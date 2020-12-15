import java.util.Date;

public interface CustomerDao {
    public Customer getCustomer(String loggingID);
    public void changePassword(String loggingID,String password);
    public void insert(String name,String loggingID,String password, int inDebt, DigitMoney loan, String address);
    public void updateTheLoan(String loggingID,DigitMoney loan);
    public void updateInDebt(String loggingID,int inDebt);
    public boolean checkThePassword(String loggingID,String password);
}
