public interface ManagerDao {
    public Manager getManager(String loggingID);
    public void changePassword(String loggingID,String password);
    public void insert(String name,String loggingID,String password);
}
