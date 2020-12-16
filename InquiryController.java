import java.util.ArrayList;
import java.util.List;

// This class is used to show all customers or specific customer and search for accounts
public class InquiryController {
	
	
	// this method for manager to get all customers
	public List<Customer> showAllCusomer(){
		List<Customer> customerlist = new ArrayList<>();
		
		CustomerDaoImpl customerDao = new CustomerDaoImpl();
		
		customerlist = customerDao.getAllCustomer();
	    
		return customerlist;
	}
	
	// get a customer by uid 
	public Customer showCustomer(int uid) {
		Customer selectedCustomer = null;
		
		CustomerDaoImpl customerDao = new CustomerDaoImpl();
		
		selectedCustomer = customerDao.getCustomerByUid(uid);
		
		return selectedCustomer;
	}
	
	// get all accounts of a customer
	public List<Account> getAccountOfCustomer(int uid) {
		List<Account> accountlist = new ArrayList<>();
		
		AccountDaoImpl accountDao = new AccountDaoImpl();
		
		accountlist = accountDao.getAccountList(uid);
		
		
		return accountlist;
	}
	
	// method for a customer to search for account using account id
	// if return null means not found.
	public Account getSpecifcAccountOfCustomer(int uid, int aid) {
        List<Account> accountlist = new ArrayList<>();
        Account resultAccount = null;
		
		AccountDaoImpl accountDao = new AccountDaoImpl();
		
		accountlist = accountDao.getAccountList(uid);
		
		for(Account a : accountlist) {
			if(a.getAccountId()==aid) {
				resultAccount = a;
			}
		}
		
		
		return resultAccount;
	}

}
