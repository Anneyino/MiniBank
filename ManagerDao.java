public interface ManagerDao {
    public Manager getManager(String loggingID);
    public void changePassword(String loggingID,String password);
}
