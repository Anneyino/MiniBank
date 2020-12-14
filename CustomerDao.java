public interface CustomerDao {
    public Customer getCustomer(String loggingID);
    public void changePassword(String loggingID,String password);
}
