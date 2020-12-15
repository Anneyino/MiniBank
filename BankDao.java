public interface BankDao {
    public Bank getBank();
    public void insert(DigitMoney profit,DigitMoney balance);
    public void updateProfit(DigitMoney profit);
    public void updateBalance(DigitMoney balance);
}
