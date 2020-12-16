
public class LoginController {
	
	public int Signup(String name, String loggingID, String password, int inDebt, double loanNum, String address) {
		
		DigitMoney loanMoney = new DigitMoney(0, USDollar.getInstance());
		
		CustomerDaoImpl customerDao = new CustomerDaoImpl();
		
		customerDao.insert(name, loggingID, password, inDebt, loanMoney, address);
		
		
		return 1;
	}
	
	public Customer Login(String loggingID, String password) {
		// init the return customer
		Customer selectedCustomer = null;
		CustomerDaoImpl customerDao = new CustomerDaoImpl();
		boolean success = customerDao.checkThePassword(loggingID, password);
		
		if(success) {
		   selectedCustomer = customerDao.getCustomer(loggingID);
		}
		
		return selectedCustomer;
	}

}
