import java.util.List;

public class TransactionController {
	// this method only for manager
	public List<Transaction> showAllTransactions(){
		TransactionDaoImpl transactionDao = new TransactionDaoImpl();
		
		List<Transaction> results = transactionDao.getAllTransaction();
		
		return results;
	}
	
	// this method for customers to show all their transaction records
	public List<Transaction> showCustomerTransactions(int uid){
        TransactionDaoImpl transactionDao = new TransactionDaoImpl();
		
		List<Transaction> result1 = transactionDao.getTransactionFromByUser(uid);
		
		List<Transaction> result2 = transactionDao.getTransactionToByUser(uid);
		
		result1.addAll(result2);

		
	    return result1;
	}
	
	// this method to check specific account's transaction records
	public List<Transaction> showAccountTransactions(int aid){
        TransactionDaoImpl transactionDao = new TransactionDaoImpl();
		
		List<Transaction> result1 = transactionDao.getTransactionFrom(aid);
		
		List<Transaction> result2 = transactionDao.getTransactionTo(aid);
		
		result1.addAll(result2);

		
	    return result1;
	}

}
