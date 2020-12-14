import java.util.Date;
import java.util.List;

public interface TransactionDao {
    public List<Transaction> getTransaction(int aid1,int aid2);
    public List<Transaction> getTransactionFrom(int aid);
    public List<Transaction> getTransactionTo(int aid);
    public List<Transaction> getTransaction(Date datetime);
}
